package Preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RandomBlastForContamination {

	public static void main(String[] args) {
		
		String fileName1 = args[0];
		String blastInput = args[1];
		double cutoff = new Double(args[2]);
		try {
			FileWriter fwriter = new FileWriter(blastInput);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			int count = 0;
		    FileInputStream fstream = new FileInputStream(fileName1);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready() && count < cutoff) {
				String name = in.readLine();
				String sequence = in.readLine();
				String plus = in.readLine();
				String quality = in.readLine();
				out.write(">" + name + "\n" + sequence + "\n");
				count++;
			}
			in.close();
			out.close();
			
			executeCommand("blastall -p blastn -d Mycoplasm_hyorhinis_GDL -i " + blastInput + " -e 1e-5 -o temp.out -a 4 -m8 -v 20");
			
			HashMap map = new HashMap();
		    fstream = new FileInputStream("temp.out");
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (new Double(split[2]) > 0.95) {
					map.put(split[0], split[0]);
				}
			}
			
			in.close();
			double number = map.size();
			System.out.println(blastInput + "\t" + (number / cutoff));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void executeCommand(String executeThis) {
		try {
			writeFile("tempexecuteCommand.sh", executeThis);
	        String[] command = {"sh", "tempexecuteCommand.sh"};
	        Process p1 = Runtime.getRuntime().exec(command);		        
            BufferedReader inputn = new BufferedReader(new InputStreamReader(p1.getInputStream()));            
            String line=null;
            while((line=inputn.readLine()) != null) {}                        
            inputn.close();
             
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void writeFile(String fileName, String command) {
		try {
		    FileWriter fwriter2 = new FileWriter(fileName);
		    BufferedWriter out2 = new BufferedWriter(fwriter2);
		    out2.write(command + "\n");		    		
		    out2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
