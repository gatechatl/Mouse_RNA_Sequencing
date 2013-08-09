package KEGG;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CreateKEGGWebsite {

	public static void main(String[] args) {
		
		try {
			
			
			HashMap map = new HashMap();
			String inputFile = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\DE Intersection Genes\\Coordinates\\Significant_protein_Comprehensive_Overlap_GeneList.txt";
			FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {				
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[1], split[1]);
			}
			in.close();
			
			String[] names = {"pathway_in_cancer", "hedgehog", "MAPK", "NOTCH", "TGF", "WNT"};
			String[] tags = {"mmu05200", "mmu04340", "mmu04010", "mmu04330", "mmu04350", "mmu04310"};
			String path = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\DE All Genes\\KEGG Cancer Pathways\\KeggPathwayInfo\\";
			for (int i = 0; i < names.length; i++) {
				String name = names[i];
				String web = "http://www.genome.jp/dbget-bin/show_pathway?" + tags[i];
				inputFile = path + names[i] + ".txt";
				fstream = new FileInputStream(inputFile);
				din = new DataInputStream(fstream); 
				in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {				
					String str = in.readLine();
					String[] split = str.split("\t");
					String key = split[1].split(";")[0];
					String id = split[0];
					if (map.containsKey(key)) {
						web += "+mmu" + ":" + id.replaceAll("\t", "").trim().replaceAll(" ", "");
					}
				}
				in.close();
				System.out.println(names[i] + "\t" + web);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
