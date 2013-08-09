package DifferentialExpressionComparison;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CreateCodingNcRNAOtherList {

	public static void main(String[] args) {
		
		try {
			String type = "Coding"; // change this Other ncRNA Coding
			HashMap geneDirection = getGeneIDDirection();
			
			HashMap geneInfo = new HashMap();
			String fileName = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult\\Coding\\AYAD21_NoNovelJuncs_CoverageBED_Sort_Uniq_Coding.txt";
		    FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				geneInfo.put(split[3], split[0] + "\t" + split[1] + "\t" + split[2]);
			}
			in.close();
			fileName = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult\\Other\\AYAD21_NoNovelJuncs_CoverageBED_Sort_Uniq_Other.txt";
		    fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				geneInfo.put(split[3], split[0] + "\t" + split[1] + "\t" + split[2]);
			}
			fileName = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult\\ncRNA\\AYAD21_NoNovelJuncs_CoverageBED_Sort_Uniq_ncRNA.txt";
		    fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				geneInfo.put(split[3], split[0] + "\t" + split[1] + "\t" + split[2]);
			}
			in.close();
			HashMap map = new HashMap();
			String outputFile = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Annotation\\Mouse_" + type + "_List.txt";
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			fileName = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult\\20120821_TopHat_coverageBED_" + type + ".txt";
		    fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				//map.put(split[1], split[0]);
				out.write(split[1] + "\t" + split[0] + "\t" + geneInfo.get(split[1]) + "\t" + geneDirection.get(split[1]) + "\n");
			}
			in.close();
			

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap getGeneIDDirection() {
		HashMap map = new HashMap();
		try {			
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Mus_musculus.NCBIM37.67.CHR.gtf";
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String geneID = grabMeta(split[8], "gene_id");								 
				map.put(geneID,  split[6]);				
			}
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
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
