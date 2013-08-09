package DEseq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ExtractCodingncRNAOther {

	
	public static void main(String[] args) {
		
		
		String fileNameProtein = args[0];
		String fileNamencRNA = args[1];
		String fileNameOther = args[2];
		
		String separateThis = args[3];
		
		HashMap mapProtein = new HashMap();
		HashMap mapncRNA = new HashMap();
		HashMap mapOther = new HashMap();
		
		String outfileProtein = args[4];
		String outfilencRNA = args[5];
		String outfileOther = args[6];
		
		try {
			
			FileWriter fwriter1 = new FileWriter(outfileProtein);
			BufferedWriter outProtein = new BufferedWriter(fwriter1);

			FileWriter fwriter2 = new FileWriter(outfilencRNA);
			BufferedWriter outncRNA = new BufferedWriter(fwriter2);
		
			FileWriter fwriter3 = new FileWriter(outfileOther);
			BufferedWriter outOther = new BufferedWriter(fwriter3);

			
			FileInputStream fstream = new FileInputStream(fileNameProtein);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				mapProtein.put(split[3], split[3]);

				
			}
			in.close();
			
			fstream = new FileInputStream(fileNamencRNA);
			din = new DataInputStream(fstream); 			
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				mapncRNA.put(split[3], split[3]);				
			}
			in.close();
			
			fstream = new FileInputStream(fileNameOther);
			din = new DataInputStream(fstream); 			
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");				
				mapOther.put(split[3], split[3]);				
			}
			in.close();
			
			fstream = new FileInputStream(separateThis);
			din = new DataInputStream(fstream); 			
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (mapProtein.containsKey(split[3])) {
					outProtein.write(str + "\n");
				}
				if (mapncRNA.containsKey(split[3])) {
					outncRNA.write(str + "\n");
				}
				if (mapOther.containsKey(split[3])) {
					outOther.write(str + "\n");
				}
			}
			
			in.close();
			
			outProtein.close();
			outncRNA.close();
			outOther.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
