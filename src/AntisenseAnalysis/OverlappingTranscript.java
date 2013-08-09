package AntisenseAnalysis;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Grab all transcript that is overlapping with the defined genes
 * @author gatech
 *
 */
public class OverlappingTranscript {

	public static void main(String[] args) {
		
		String queryGenes = args[0];
		String gtfFile = args[1];
		try {
			HashMap map = new HashMap();
			HashMap chrMap = new HashMap();
			FileInputStream fstream = new FileInputStream(queryGenes);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String chr = split[1].split(":")[0];
				String start = split[1].split(":")[1].split("-")[0];
				String end = split[1].split(":")[1].split("-")[1];
				map.put(split[2], chr + "\t" + start + "\t" + end);
				chrMap.put(chr,  chr);
			}
			in.close();
			
			fstream = new FileInputStream(gtfFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String chr = split[0];
				if (chrMap.containsKey(chr)) {
					String gene_id = split[8].split(";")[0];
					if (map.containsKey(gene_id)) {
						System.out.println(str);
					} else {
						gene_id = gene_id.split(" ")[1];
						gene_id = gene_id.replaceAll("\"", "").trim();
						int start = new Integer(split[3]);
						int end = new Integer(split[4]);
						Iterator itr = map.keySet().iterator();
						while (itr.hasNext()) {
							String str2 = (String)itr.next();
							String[] split2 = str2.split("\t");
							String qchr = split2[0];
							int qstart = new Integer(split2[1]);
							int qend = new Integer(split2[2]);
							if (chr.equals(qchr) && overlap(start, end, qstart, qend)) {
								System.out.println(str);
							}
							
						}
					}
				}
			}
			in.close();
			System.out.println(map.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static boolean overlap(int q1, int q2, int d1, int d2) {
		if (q1 <= d1 && d1 <= q2) {
			return true;
		}
		if (q1 <= d2 && d2 <= q2) {
			return true;
		}
		if (d1 <= q1 && q1 <= d2) {
			return true;
		}
		if (d1 <= q2 && q2 <= d2) {
			return true;
		}
		return false;
	}
}
