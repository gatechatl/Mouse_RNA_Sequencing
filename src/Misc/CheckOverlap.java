package Misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CheckOverlap {

	public static void main(String[] args) {
		
		try {
			HashMap map1 = new HashMap();
			String inputFile = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult2\\ncRNA.txt";
		    FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				map1.put(str,  str);
			}
			in.close();

			String outputFile = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult2\\OrderCoverageBEDOneFileOther_Filtered.txt";
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			inputFile = "C:\\School Notes\\RNAseqCancer\\RawTopHatResult2\\OrderCoverageBEDOneFileOther.txt";
		    fstream = new FileInputStream(inputFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (!map1.containsKey(split[0])) {
					out.write(str + "\n");
				} 
			}
			in.close();
			out.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
