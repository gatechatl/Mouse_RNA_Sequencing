package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ExtractTimeSeriesOfGenes {

	public static void main(String[] args) {

		try {

			String[] geneNames = {"Pathways in Cancer", "Hedgehog Signaling Pathway", "MAPK Signaling Pathway", "NOTCH Signaling Pathway", "TGF-beta Signaling Pathway", "Wnt Signaling Pathway"};
			
			for (String geneName: geneNames) {
				//String geneName = "Pathways in Cancer";
				//String geneName = "Hedgehog Signaling Pathway";
				//String fileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Gene Expression Measure\\Cufflink_FPKM_Result.output";			
				//String fileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\Cufflink_FPKM_LogRatio_result.output";
				//String fileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\Cufflink_DESeq_Intersecting_Differential_Expressed_Gene.txt";
				String fileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\Cufflink_DESeq_Merge_Differential_Expressed_Gene.txt";
				
				//
				String geneFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Pathway Genes\\" + geneName + ".txt";
				
				HashMap geneNameMap = getGeneList(geneFile);
				
				//String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Pathway Genes\\Cufflink_Log2_" + geneName + ".txt";
				String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Pathway Genes\\Cufflink_DESeq_Merge_Log2_" + geneName + ".txt";
				
				
				
				//String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Gene Pathway Time Series\\Cufflink_Log2_"
				//String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Pathway Genes\\DEseq_Log2_" + geneName + ".txt";
	
				FileWriter fwriter = new FileWriter(outputFile);
				BufferedWriter out = new BufferedWriter(fwriter);
	
				FileInputStream fstream = new FileInputStream(fileName);
				DataInputStream din = new DataInputStream(fstream);
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					String gene = split[0];
					String stuff = split[1];
					if (geneNameMap.containsKey(stuff)) {
						stuff += "\t" + split[2] + "\t" + split[3];
						for (int i = 5; i < split.length; i++) {
							stuff += "\t" + split[i];
						}
						out.write(stuff + "\n");
					}
				}
				in.close();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HashMap grabInformation (String fileName) {
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
