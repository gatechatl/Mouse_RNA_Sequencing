package CuffDiff;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FindGeneInPathway {

	public static void main(String[] args) {
		
		try {
			HashMap map = new HashMap();
			String inputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\differential_expression_result.output";
		    //String queryPathway = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\Hedge_Hog_Signaling_Gene.txt";
			//String queryPathway = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\Notch_Signaling_Pathway_Gene.txt";
			//String queryPathway = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\Wnt_Signaling_Pathway_Gene.txt";
			String queryPathway = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\MAPK_Signaling_Pathway_Gene.txt";
			//
			FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {				
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[1], split[1]);
			}
			in.close();
			
		    fstream = new FileInputStream(queryPathway);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {				
				String str = in.readLine();
				String gene = str.split("\t")[1].split(";")[0];
				if (map.containsKey(gene)) {
					System.out.println(gene);
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
