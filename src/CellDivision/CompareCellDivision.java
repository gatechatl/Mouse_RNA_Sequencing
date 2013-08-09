package CellDivision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CompareCellDivision {

	public static void main(String[] args) {
		
		try {
			String inputFile = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\CellCycle\\Cell_Cycle_DAVID.txt";
			HashMap map = new HashMap();
			FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));		
			while (in.ready()) {				
				String str = in.readLine();
				String[] split = str.split("\t");
				String[] genes = split[2].split(",");
				for (int i = 0; i < genes.length; i++) {
					if (map.containsKey(genes[i].trim())) {
						String stuff = (String) map.get(genes[i].trim());
						stuff += "\t" + split[0] + ":" + split[1];
						map.put(genes[i].trim(), stuff);
					} else {
						map.put(genes[i].trim(), split[0] + ":" + split[1]);
					}
					//System.out.println(genes[i].trim());
				}
			}
			in.close();					

			HashMap reactome = new HashMap();
			inputFile = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\DE Intersection Genes\\Pathway_Function_Analysis\\Reactome\\Protein_DE_Comprehensive_Reactome_Table.txt";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (split.length >= 4) {
					reactome.put(split[0], split[3]);
				}
				
			}
			in.close();
			
			HashMap kegg = new HashMap();
			inputFile = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\CellCycle\\Cell_Cycle_KEGG.txt";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String[] split2 = split[5].split(",");
				for (int i = 0; i < split2.length; i++) {
					kegg.put(split2[i].trim(), "KEGG:Cell Cycle");					
				}				
			}
			in.close();
			
			HashMap nonoverlap = new HashMap();
			String[] files = {"Cuffdiff_LogFold_Paper_Cell_Division_Defect.txt", "Cuffdiff_LogFold_Paper_G2_arrest.txt", "Cuffdiff_LogFold_Paper_GO_1_arrest.txt", "Cuffdiff_LogFold_Paper_S_arrest.txt"};
			for (String file: files) {
				
				String outputFile = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\CellCycle\\Merge\\DAVID_" + file.replaceAll("Cuffdiff_LogFold_", "");
				FileWriter fwriter = new FileWriter(outputFile);
				BufferedWriter out = new BufferedWriter(fwriter);

				String outputFile2 = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\CellCycle\\Merge\\NoOverlap_DAVID_" + file.replaceAll("Cuffdiff_LogFold_", "");
				FileWriter fwriter2 = new FileWriter(outputFile2);
				BufferedWriter out2 = new BufferedWriter(fwriter2);
				
				inputFile = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\CellCycle\\Paper\\" + file;
				fstream = new FileInputStream(inputFile);
				din = new DataInputStream(fstream); 
				in = new BufferedReader(new InputStreamReader(din));		
				while (in.ready()) {	
					String str = in.readLine();
										
					String[] split = str.split("\t");
					boolean davidboo = false;
					boolean keggboo = false;
					boolean reactomeboo = false;
					boolean atleastone = false;
					//System.out.println(split[1]);
					if (map.containsKey(split[2])) {
						davidboo = true;
						atleastone = true;
						//System.out.println(split[2]);
					}
					if (reactome.containsKey(split[2])) {
						reactomeboo = true;
						atleastone = true;
					}
					
					if (kegg.containsKey(split[2])) {
						keggboo = true;
						atleastone = true;
					}
					
					if (atleastone) {
						out.write(split[2] + "\t" + davidboo + "\t" + reactomeboo + "\t" + keggboo + "\n");
					} else {
						out2.write(split[2] + "\n");
					}
					// out.write(split[2] + "\t" + reactome.get(split[2]) + "\t" + map.get(split[2]) + "\n");

				}
				in.close();
				out.close();
				
				out2.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
