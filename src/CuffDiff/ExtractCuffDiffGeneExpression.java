package CuffDiff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class ExtractCuffDiffGeneExpression {

	public static void main(String[] args) {
		
		try {
			String outputFile = args[0];
			HashMap geneDiff = new HashMap();

			
			String[] files = {"diff_out_21_22", "diff_out_21_23", "diff_out_21_24", "diff_out_21_25", "diff_out_21_26", "diff_out_21_28", "diff_out_21_36"};
			for (String file: files) {
				String inputFile = file + "/gene_exp.diff";
			    FileInputStream fstream = new FileInputStream(inputFile);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					if (split[13].equals("yes")) {
						String key = split[1] + "\t" + split[2];
						if (geneDiff.containsKey(key)) {
							String list = (String)geneDiff.get(key);
							list += "\t" + file;
							geneDiff.put(key, list);
						} else {
							geneDiff.put(key, file);
						}
					}
				}
				in.close();
			}
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			Iterator itr = geneDiff.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				String timepoints = (String)geneDiff.get(key);
				out.write(key);
				int countDiffExpress = 0;
				if (timepoints.contains("diff_out_21_22")) {
					out.write("\tyes");
					countDiffExpress++;
				} else {
					out.write("\tno");
				}
				if (timepoints.contains("diff_out_21_23")) {
					out.write("\tyes");
					countDiffExpress++;
				} else {
					out.write("\tno");
				}
				if (timepoints.contains("diff_out_21_24")) {
					out.write("\tyes");
					countDiffExpress++;
				} else {
					out.write("\tno");
				}
				if (timepoints.contains("diff_out_21_25")) {
					out.write("\tyes");
					countDiffExpress++;
				} else {
					out.write("\tno");
				}
				if (timepoints.contains("diff_out_21_26")) {
					out.write("\tyes");
					countDiffExpress++;
				} else {
					out.write("\tno");
				}
				if (timepoints.contains("diff_out_21_28")) {
					out.write("\tyes");
					countDiffExpress++;
				} else {
					out.write("\tno");
				}
				if (timepoints.contains("diff_out_21_36")) {
					out.write("\tyes");
					countDiffExpress++;
				} else {
					out.write("\tno");
				}
				out.write("\t" + countDiffExpress + "\n");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
