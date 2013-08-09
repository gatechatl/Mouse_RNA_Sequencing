package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class GrabUniqueGenes {

	public static void main(String[] args) {
		
		String inputFile = args[0];
		String outputFile = args[1];
		try {
			HashMap map = new HashMap();
			
			String chr = "";
			double start = 999999999;
			double end = 0;
			double reads = 0;
			double read_length = 0;
			double total_length = 0;
			

			HashMap geneNames = new HashMap();
			
			HashMap lastAdded = new HashMap();
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
		    FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String key = split[1] + split[2] + split[3];
				String gene = split[3].replaceAll("\"", "");
				
				if (geneNames.containsKey(gene)) {
				    String newchr = split[0];
					double newstart = new Double(split[1]);
					double newend = new Double(split[2]);
					
					double newreads = new Double(split[4]);
					double newread_length = new Double(split[5]);
					double newtotal_length = new Double(split[6]);

					
					String[] split3 = ((String)lastAdded.get(gene)).split("\t");/*put(gene, new Double(start).intValue() + "\t" + new Double(end).intValue() + "\t" + reads + "\t" + read_length + "\t" + total_length);*/
					double last_start = new Double(split3[0]);
					double last_end = new Double(split3[1]);
					double lastadded_reads = new Double(split3[2]);
					double lastadded_length = new Double(split3[3]);
					double lastadded_total = new Double(split3[4]);
					
					if (!map.containsKey(key)) {				
						String data = (String)geneNames.get(gene);
						String[] split2 = data.split("\t");

					    chr = split2[0];
						start = new Double(split2[1]);
						end = new Double(split2[2]);
												
						
						/* if overlap*/
						if (newstart < end) {
							/* if larger than previous */
							if (last_end - last_start < newend - newstart) {
								reads = reads - lastadded_reads;
								read_length = read_length - lastadded_length;
								total_length = total_length - lastadded_total;
								
								reads = new Double(split2[4]) + newreads;
								read_length = new Double(split2[5]) + newread_length;
								total_length = new Double(split2[6]) + newtotal_length;
								
								lastAdded.put(gene, new Double(start).intValue() + "\t" + new Double(newend).intValue() + "\t" + newreads + "\t" + newread_length + "\t" + newtotal_length);
								geneNames.put(gene, chr + "\t" + new Double(start).intValue() + "\t" + new Double(newend).intValue() + "\t" + gene + "\t" + reads + "\t" + read_length + "\t" + total_length + "\t" + (read_length / total_length));
							}
							
						/* if no overlap */
						} else {
							
							reads = new Double(split2[4]) + newreads;
							read_length = new Double(split2[5]) + newread_length;
							total_length = new Double(split2[6]) + newtotal_length;					
							lastAdded.put(gene, new Double(start).intValue() + "\t" + new Double(newend).intValue() + "\t" + newreads + "\t" + newread_length + "\t" + newtotal_length);
							geneNames.put(gene, chr + "\t" + new Double(start).intValue() + "\t" + new Double(newend).intValue() + "\t" + gene + "\t" + reads + "\t" + read_length + "\t" + total_length + "\t" + (read_length / total_length));
							
							
						}
					}
				} else {
					
				    chr = split[0];
					start = new Double(split[1]);
					end = new Double(split[2]);
					reads = new Double(split[4]);
					read_length = new Double(split[5]);
					total_length = new Double(split[6]);										
					geneNames.put(gene, chr + "\t" + new Double(start).intValue() + "\t" + new Double(end).intValue() + "\t" + gene + "\t" + reads + "\t" + read_length + "\t" + total_length + "\t" + (read_length / total_length));
					lastAdded.put(gene, new Double(start).intValue() + "\t" + new Double(end).intValue() + "\t" + reads + "\t" + read_length + "\t" + total_length);
					
					map.put(key, key);
				}
			}
			in.close();
		    
			Iterator itr = geneNames.keySet().iterator();
			while (itr.hasNext()) {
				String geneName = (String)itr.next();
				String stuff = (String)geneNames.get(geneName);
				out.write(stuff + "\n");
				
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
