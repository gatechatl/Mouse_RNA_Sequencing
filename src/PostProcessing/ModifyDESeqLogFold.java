package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ModifyDESeqLogFold {

	public static void main(String[] args) {
		HashMap metadataMap = getGTFInformation("C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Tophat Result\\Mus_musculus.NCBIM37.67.CHR.gtf");
		HashMap coordMap = getCoordInformation("C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\DESeqCoordinateMap.txt");
		//HashMap metadata = grabEnsembl2MetaData("C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\Cufflink_FPKM_LogRatio_result.output");
		try {
			
			String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\DEseq_merged_fold_change_metadata.output";
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			String withDEseqFileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\DEseq_merged_fold_change.output";
			FileInputStream fstream3 = new FileInputStream(withDEseqFileName);
			DataInputStream din3 = new DataInputStream(fstream3);
			BufferedReader in3 = new BufferedReader(new InputStreamReader(din3));
			while (in3.ready()) {
				String str = in3.readLine();
				String[] split = str.split("\t");
				String geneName = split[0].replaceAll("\"", "");
				String stuff = geneName + "\t" + (String)metadataMap.get(geneName) + "\t" + (String)coordMap.get(geneName);
				out.write(stuff + "\t" + split[2] + "\t" + split[4] + "\t" + split[6] + "\t" + split[8] + "\t" + split[10] + "\t" + split[12] + "\t" + split[14] + "\n");
				
			}
			in3.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static HashMap getGTFInformation(String fileName) {
	        HashMap map = new HashMap();
	        
	        try {



	                FileInputStream fstream = new FileInputStream(fileName);
	                DataInputStream din = new DataInputStream(fstream);
	                BufferedReader in = new BufferedReader(new InputStreamReader(din));
	                while (in.ready()) {
	                        String str = in.readLine();
	                        String[] split = str.split("\t");
	                        String data = split[8];
	                        String type = data.split("gene_biotype")[1].split(";")[0].replaceAll("\"", "").trim();
	                        String ensembl = data.split("gene_id")[1].split(";")[0].replaceAll("\"", "").trim();
	                        String geneName = data.split("gene_name")[1].split(";")[0].replaceAll("\"", "").trim();
	                        
	                        map.put(ensembl, geneName + "\t" + type);
	                }
	                in.close();
	        } catch (Exception e) {
	                e.printStackTrace();
	        }
	        return map;
	}
	public static HashMap getCoordInformation(String fileName) {
		HashMap map = new HashMap();
		try {

			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[0], split[1]);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}

}
