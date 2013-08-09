package CheckInSpecialGeneList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CheckForOverlap {

	public static void main(String[] args) {
		
		String fileName = "C:\\School Notes\\Overlapper\\Other List\\BarrISG database.csv";
		try {
			
			
			String outputFile = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\Protein_GeneList\\Special List\\InterferonInducedGene_Overlap_Comprehensive_Diff_Protein_Mouse_TimeSeries.txt";
		    FileWriter fwriter = new FileWriter(outputFile);
		    BufferedWriter out  = new BufferedWriter(fwriter);
		    
		    
			HashMap map = new HashMap();
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split(",");
				String geneName = split[2].replaceAll("'", "");
				map.put(geneName,  geneName);
				
			}
			in.close();
			
			fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\Protein_GeneList\\Significant_protein_Comprehensive_Overlap_GeneList.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				
				if (map.containsKey(split[1].toUpperCase())) {
					System.out.println(str);
					out.write(str + "\n");
				}
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
