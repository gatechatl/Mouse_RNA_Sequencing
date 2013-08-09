package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ExtractTimeSeriesOfGenesDEseq {

	public static void main(String[] args) {

		try {

			String[] geneNames = {"Pathways in Cancer", "Hedgehog Signaling Pathway", "MAPK Signaling Pathway", "NOTCH Signaling Pathway", "TGF-beta Signaling Pathway", "Wnt Signaling Pathway"};
			HashMap metadata = grabEnsembl2MetaData("C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\Cufflink_FPKM_LogRatio_result.output");
			for (String geneName: geneNames) {
				//String geneName = "Pathways in Cancer";
				//String geneName = "Hedgehog Signaling Pathway";
				//String fileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Gene Expression Measure\\Cufflink_FPKM_Result.output";			
				String fileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\DEseq_merged_fold_change.output";
				String geneFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Pathway Genes\\" + geneName + ".txt";
				
				HashMap geneNameMap = getGeneList(geneFile);
				//String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Gene Pathway Time Series\\Cufflink_Log2_"
				String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Pathway Genes\\DEseq_Log2_" + geneName + ".txt";
				//String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Pathway Genes\\DEseq_Log2_" + geneName + ".txt";
	
				FileWriter fwriter = new FileWriter(outputFile);
				BufferedWriter out = new BufferedWriter(fwriter);
	
				FileInputStream fstream = new FileInputStream(fileName);
				DataInputStream din = new DataInputStream(fstream);
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					String gene = split[0].replaceAll("\"", "");
					//System.out.println(gene);
					String stuff = (String)metadata.get(gene);
					if (stuff != null) {
						String[] split2 = stuff.split("\t");
						if (geneNameMap.containsKey(split2[0])) {
							//stuff += "\t" + split[2] + "\t" + split[3];
							out.write(stuff);
							int[] listnum = {2, 4, 6, 8, 10, 12, 14};
							for (int i: listnum) {
								System.out.println(i);
								//stuff += "\t" + split[i];
								out.write("\t" + split[i]);
							}
							out.write("\n");
						}
					}
				}
				in.close();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HashMap grabEnsembl2MetaData (String fileName) {
		HashMap map = new HashMap();
		try {

			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[3], split[1] + "\t" + split[2] + "\t" + split[3]);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}
	public static HashMap getGeneList(String fileName) {
		HashMap map = new HashMap();
		try {

			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				map.put(str.trim(), str.trim());
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
