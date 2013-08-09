package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class GenerateDESeqEnsembl2CoordinateMapping {

	public static void main(String[] args) {
		HashMap map = new HashMap();
		try {
			String basepath = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Tophat Result";
			String[] fileNames = {"AYAD21_CoverageBED_Coding_Unique.txt", "AYAD22_CoverageBED_Coding_Unique.txt", "AYAD23_CoverageBED_Coding_Unique.txt"
					, "AYAD24_CoverageBED_Coding_Unique.txt", "AYAD25_CoverageBED_Coding_Unique.txt", "AYAD26_CoverageBED_Coding_Unique.txt"
					, "AYAD28_CoverageBED_Coding_Unique.txt", "AYAD36_CoverageBED_Coding_Unique.txt"};
			for (String fileName : fileNames) {
				FileInputStream fstream = new FileInputStream(basepath + "\\" + fileName);
				DataInputStream din = new DataInputStream(fstream);
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					String coord = split[0] + ":" + split[1] + "-" + split[2];
					map.put(split[3], coord);
				}
				in.close();
			}
			String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Tophat Result\\DESeqCoordinateMap.txt";
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			Iterator itr = map.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				String stuff = (String)map.get(key);
				out.write(key + "\t" + stuff + "\n");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
