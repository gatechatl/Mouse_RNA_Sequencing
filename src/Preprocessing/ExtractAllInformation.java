package Preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class ExtractAllInformation {

	
	public static void main(String[] args) {
		try {
			//String[] fileNames =  {"21", "22", "23", "24", "25", "26", "28", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "49"};
			String[] fileNames =  {"21", "38", "39", "22", "40", "41", "23", "24", "42", "43", "25", "44", "45", "26", "46", "47", "36", "37", "28", "49"};
			
			String type = "Coding";
			HashMap map = new HashMap();
			String gtfFile = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult2\\Mus_musculus.NCBIM37.67.CHR.gtf";
			FileInputStream fstream = new FileInputStream(gtfFile);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String metadata = split[8];
				String geneID = grabMeta(metadata, "gene_id");
				String geneName = grabMeta(metadata, "gene_name");
				map.put(geneID, geneName);
			}
			in.close();
			
			HashMap bigGiant = new HashMap();
			String header = "GeneID\tEnsemblID\tGeneLength";
			String path = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult2\\" + type + "\\";
			for (String fileID: fileNames) {
				
				String fileName = path + "AYAD" + fileID + "_NoNovelJuncs_CoverageBED_Sort_Uniq_" + type + ".txt";
				header += "\t" + "AYAD" + fileID;
				fstream = new FileInputStream(fileName);
				din = new DataInputStream(fstream); 			
				in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					String ensemblID = split[3];
					String geneName = (String)map.get(ensemblID);
					String id = (ensemblID + "\t" + geneName);
					if (bigGiant.containsKey(id)) {
						String data = (String)bigGiant.get(id);
						data = data + "\t" + split[4];
						bigGiant.put(id, data);
					} else {
						bigGiant.put(id, split[6] + "\t" + split[4]);
					}
					 
				}
				in.close();								
			}
			String oneBigFile = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult2\\OrderCoverageBEDOneFile" + type + ".txt";
			FileWriter fwriter = new FileWriter(oneBigFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			out.write(header + "\n");
			Iterator itr = bigGiant.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				
				out.write(key + "\t" + bigGiant.get(key) + "\n");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String grabMeta(String text, String id) {
		String returnval = "";
		if (text.contains(id)) {
			String val = text.split(id)[1].split(";")[0].trim();
			val = val.replaceAll("\"", "");
			val.trim();
			returnval = val;				
		}
		return returnval;
	}
}
