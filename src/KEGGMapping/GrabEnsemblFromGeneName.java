package KEGGMapping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class GrabEnsemblFromGeneName {

	public static void main(String[] args) {		
		try {			
			String fileName = "C:\\School Notes\\RNAseqCancer\\DEseq Human Data\\DEseq Result\\sample31_bioreps_0.05.txt";
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));								
			HashMap map = new HashMap();			
			String fileName2 = "C:\\School Notes\\RNAseqCancer\\DEseq Human Data\\DEseq Result\\sample31_bioreps_0.05.txt";
			FileInputStream fstream2 = new FileInputStream(fileName2);
			DataInputStream din2 = new DataInputStream(fstream2);
			BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));			
			while (in.ready()) {				
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[0], split[0]);
			}
			in.close();									
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
