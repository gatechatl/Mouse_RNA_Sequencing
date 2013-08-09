package CuffDiff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class CombineFPKMLogRatio {
	
	
	public static void main(String[] args) {
		try {
			String outputFile = args[0];
			HashMap map = new HashMap();
			String[] files = {"diff_out_21_22", "diff_out_21_23", "diff_out_21_24", "diff_out_21_25", "diff_out_21_26", "diff_out_21_28", "diff_out_21_36"};
			for (String file: files) {
				String inputFile = file + "/gene_exp.diff";
			    FileInputStream fstream = new FileInputStream(inputFile);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					
					String[] split = str.split("\t");
					String key = split[1] + "\t" + split[2] + "\t" + split[3];
					String status = split[6];
					if (status.equals("NOTEST")) {
						split[9] = "NaN";
					}
					if (map.containsKey(key)) {
						String result = (String)map.get(key);
						result += "\t" + split[9];
						map.put(key, result);
					} else {
						map.put(key, split[9]);
					}
				}
				in.close();
				
			}
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			Iterator itr = map.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				String result = (String)map.get(key);
				out.write(key + "\t" + result + "\n");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
