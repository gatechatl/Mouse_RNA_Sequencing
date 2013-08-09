package PostProcessing;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class ExtractGTFCoordinatesPerGene {

	public static void main(String[] args) {
		String inputFile = args[0];
		
		try {
			HashMap map = new HashMap();
			HashMap map2 = new HashMap();
		    FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String chr = split[0];
				String type = split[1];
				int start = new Integer(split[3]);
				int end = new Integer(split[4]);
				String data = split[8];
				String ensemblGene = data.split(";")[0].replaceAll("gene_id", "").replaceAll("\"", "").trim();
				if (type.equals("protein_coding")) {
					if (map.containsKey(ensemblGene)) {
						String stuff = (String)map.get(ensemblGene);
						String[] split2 = stuff.split("\t");
						int oldstart = new Integer(split2[1]);
						int oldend = new Integer(split2[2]);
						
						int newstart = 0;
						int newend = 0;
						
						if (start < oldstart) {
							newstart = start;
						} else {
							newstart = oldstart;
						}
						if (end < oldend) {
							newend = end;
						} else {
							newend = oldend;
						}
						map.put(ensemblGene, chr + "\t" + newstart + "\t" + newend + "\t" + ensemblGene);
					} else {
					    map.put(ensemblGene, chr + "\t" + start + "\t" + end + "\t" + ensemblGene);
					}
				}	
			}
			in.close();
			Object[] objects = map.values().toArray();
			Arrays.sort(objects);
			for (int i = 0; i < objects.length; i++) {
				System.out.println(objects[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
