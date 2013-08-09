package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class SeparateCodingNonCoding {

	public static void main(String[] args) {
		
		String gtf_file = args[0];
		String protein_coding = args[1];
		String other_noncoding = args[2];
		try {
			FileWriter fwriter = new FileWriter(protein_coding);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			FileWriter fwriter2 = new FileWriter(other_noncoding);
			BufferedWriter out2 = new BufferedWriter(fwriter2);
			
		    FileInputStream fstream = new FileInputStream(gtf_file);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				if (str.split("\t")[1].equals("protein_coding")) {
					out.write(str + "\n");
				} else {
					out2.write(str + "\n");
				}
				
			
			}
			in.close();
			out.close();
			out2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
