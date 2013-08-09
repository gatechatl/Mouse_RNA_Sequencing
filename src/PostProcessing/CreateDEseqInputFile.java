package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CreateDEseqInputFile {

	public static void main(String[] args) {
		String tag = "28";
		try {
			String inputFile1 = "C:\\School Notes\\RNAseqCancer\\DEseq Mouse Data\\AYAD21_CoverageBED_NonCoding_Unique.txt";
			String inputFile2 = "C:\\School Notes\\RNAseqCancer\\DEseq Mouse Data\\AYAD" + tag + "_CoverageBED_NonCoding_Unique.txt";
			String outputFile = "C:\\School Notes\\RNAseqCancer\\DEseq Mouse Data\\AYAD21" + tag + "_NonCoding.txt";
			HashMap map = new HashMap();
			
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			FileInputStream fstream = new FileInputStream(inputFile1);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[3], split[4]);
			}
			in.close();
			
			out.write("genes\tAYAD21\tAYAD" + tag + "\n");
			
			fstream = new FileInputStream(inputFile2);
			din = new DataInputStream(fstream);
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String value = (String)map.get(split[3]);
				out.write(split[3] + "\t" + value + "\t" + split[4] + "\n");
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
