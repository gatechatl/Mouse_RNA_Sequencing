package CuffDiff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FPKMPathwayFilter {

	public static void main(String[] args) {
		
		try {
			HashMap map = new HashMap();
			String inputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\FPKM_Result.output";
		    //String queryPathway = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\Hedge_Hog_Signaling_Gene.txt";
			//String queryPathway = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\Notch_Signaling_Pathway_Gene.txt";
			//String queryPathway = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\Wnt_Signaling_Pathway_Gene.txt";
			String queryPathway = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\Hedge_Hog_Signaling_Gene.txt";
			String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\Hedge_Hog_Signaling_Gene_Timeseries.output";
			//
			
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			FileInputStream fstream = new FileInputStream(queryPathway);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {				
				String str = in.readLine();
				String gene = str.split("\t")[1].split(";")[0];
				
				map.put(gene, str);
			}
			in.close();
			
		    fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {				
				String str = in.readLine();
				String[] split = str.split("\t");
				String gene = split[1];
				if (map.containsKey(gene)) {
					out.write(gene);
					for (int i = 5; i < split.length; i++) {
						out.write("\t" + split[i]);
					}
					out.write("\n");
				}
				
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
