package CuffDiff;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class HashMapProteinGeneIDGeneName {

	public static void main(String[] args) {
		

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
				String type = split[1];
				String data = split[8];
				String ensembl = data.split("gene_id")[1].split(";")[0].replaceAll("\"", "").trim();
				String geneName = data.split("gene_name")[1].split(";")[0].replaceAll("\"", "").trim();
				
				map.put(geneName, ensembl + "\t" + type);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
