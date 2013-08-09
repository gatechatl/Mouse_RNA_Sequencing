package ForEric;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class GenerateDiff {

	static HashMap hour0_2 = new HashMap();
	static 	HashMap hour0_4 = new HashMap();
	static HashMap hour0_6 = new HashMap();
	static HashMap hour0_12 = new HashMap();
	static HashMap hour0_16 = new HashMap();
	static HashMap hour0_24 = new HashMap();
	static HashMap hour0_48 = new HashMap();
	
	static HashMap hour0_fpkm = new HashMap();
	static HashMap hour2_fpkm = new HashMap();
	static HashMap hour4_fpkm = new HashMap();
	static HashMap hour6_fpkm = new HashMap();
	static HashMap hour12_fpkm = new HashMap();
	static HashMap hour16_fpkm = new HashMap();
	static HashMap hour24_fpkm = new HashMap();
	static HashMap hour48_fpkm = new HashMap();
	
	public static void main(String[] args) {
		
		try {
			HashMap map = new HashMap();
			String inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\Cuffdiff_FoldChange_ncRNA.txt";
		    FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[0], split[0]);
			}
			in.close();
			
			diff(11);
			diff_fpkm();
			
			int count = 0;
			String outputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\NonCoding_Pval.txt";
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			out.write("GeneName\tHour0_2\tHour0_4\tHour0_6\tHour0_12\tHour0_16\tHour0_24\tHour0_48\n");
			Iterator itr = map.keySet().iterator();
			while (itr.hasNext()) {
				
				String key = (String)itr.next();
			
				count++;
				out.write(key);
				String pval = (String)hour0_2.get(key);
				out.write("\t" + pval);
				
				pval = (String)hour0_4.get(key);
				out.write("\t" + pval);

				pval = (String)hour0_6.get(key);
				out.write("\t" + pval);
				
				pval = (String)hour0_12.get(key);
				out.write("\t" + pval);
				
				pval = (String)hour0_16.get(key);
				out.write("\t" + pval);
				
				pval = (String)hour0_24.get(key);
				out.write("\t" + pval);
				
				pval = (String)hour0_48.get(key);
				out.write("\t" + pval);
				
				out.write("\n");
				
			}
			out.close();
			
			
			outputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\NonCoding_FPKM.txt";
			fwriter = new FileWriter(outputFile);
			out = new BufferedWriter(fwriter);
			
			out.write("GeneName\tHour0\tHour2\tHour4\tHour6\tHour12\tHour16\tHour24\tHour48\n");
			itr = map.keySet().iterator();
			while (itr.hasNext()) {			
				String key = (String)itr.next();
				out.write(key);
				String fpkm = (String)hour0_fpkm.get(key);
				out.write("\t" + fpkm);
				
				fpkm = (String)hour2_fpkm.get(key);
				out.write("\t" + fpkm);
				
				fpkm = (String)hour4_fpkm.get(key);
				out.write("\t" + fpkm);

				fpkm = (String)hour6_fpkm.get(key);
				out.write("\t" + fpkm);
				
				fpkm = (String)hour12_fpkm.get(key);
				out.write("\t" + fpkm);
				
				fpkm = (String)hour16_fpkm.get(key);
				out.write("\t" + fpkm);
				
				fpkm = (String)hour24_fpkm.get(key);
				out.write("\t" + fpkm);
				
				fpkm = (String)hour48_fpkm.get(key);
				out.write("\t" + fpkm);
				
				out.write("\n");				
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void diff_fpkm() {
		try {
			

			
			String inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_2\\gene_exp.diff";
			FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour0_fpkm.put(split[0], split[7]);
				hour2_fpkm.put(split[0], split[8]);
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_4\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour4_fpkm.put(split[0], split[8]);								
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_6\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour6_fpkm.put(split[0], split[8]);													
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_12\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour12_fpkm.put(split[0], split[8]);													
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_16\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour16_fpkm.put(split[0], split[8]);													
			}
			in.close();
						
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_24\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour24_fpkm.put(split[0], split[8]);													
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_48\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour48_fpkm.put(split[0], split[8]);													
			}
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void diff(int num) {
		try {
			

			
			String inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_2\\gene_exp.diff";
			FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour0_2.put(split[0], split[num]);								
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_4\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour0_4.put(split[0], split[num]);								
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_6\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour0_6.put(split[0], split[num]);													
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_12\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour0_12.put(split[0], split[num]);													
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_16\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour0_16.put(split[0], split[num]);													
			}
			in.close();
						
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_24\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour0_24.put(split[0], split[num]);													
			}
			in.close();
			
			inputFile = "C:\\School Notes\\RNAseq_Mouse_Eric\\CuffDiff\\Hour0_48\\gene_exp.diff";
			fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				hour0_48.put(split[0], split[num]);													
			}
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
