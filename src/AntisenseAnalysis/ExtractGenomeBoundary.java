package AntisenseAnalysis;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Examined the gtf file and identify the start and stop for each gene
 * @author Tim Shaw
 *
 */
public class ExtractGenomeBoundary {
	public static void main(String[] args) {				
		try {
			HashMap map = new HashMap();
			String fileName = "";
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String chr = split[0];
				int start = new Integer(split[3]);
				int end = new Integer(split[4]);
				String direction = split[6];
				String meta = split[8];
				String geneName = grabMeta(meta, "gene_name");
				String Ensembl = grabMeta(meta, "oId");
				if (Ensembl.contains("ENSMUST")) {
					if (map.containsKey(Ensembl)) {
						String get = (String)map.get(Ensembl);
						String[] msplit = get.split("\t");
						String mgeneName = msplit[0];
						String mchr = msplit[1];
						int mstart = new Integer(msplit[2]);
						int mend = new Integer(msplit[3]);
						String mdirection = msplit[4];
						if (mstart < start) {
							mstart = start;
						}
						if (mend > end) {
							mend = end;
						}
						String stuff = mgeneName + "\t" + mchr + "\t" + mstart + "\t" + mend + "\t" + mdirection;
						map.put(Ensembl, stuff);
					} else {
						String stuff = geneName + "\t" + chr + "\t" + start + "\t" + end + "\t" + direction; 
								
						map.put(Ensembl, stuff);
					}
				}								
				
			}
			in.close();
			
			System.out.println(map.size());
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
