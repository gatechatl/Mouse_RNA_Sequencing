package Preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class EnsurePairedEnd {

	public static void main(String[] args) {
		
		String fileName1 = args[0];
		String fileName2 = args[1];
		String outputFile1 = args[2];
		String outputFile2 = args[3];
		try {
			int countFirstFile = 0;
			HashMap map = new HashMap();
		    FileInputStream fstream = new FileInputStream(fileName1);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String name = in.readLine().split(" ")[0];
				String sequence = in.readLine();
				String plus = in.readLine();
				String quality = in.readLine();
				if (sequence.length() < 5) {
					map.put(name, name);
				}
				countFirstFile++;
			}
			in.close();
			
			int countSecondFile = 0;
		    FileInputStream fstream2 = new FileInputStream(fileName2);
			DataInputStream din2 = new DataInputStream(fstream2); 
			BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));
			while (in2.ready()) {
				String name = in2.readLine().split(" ")[0];
				String sequence = in2.readLine();
				String plus = in2.readLine();
				String quality = in2.readLine();
				if (sequence.length() < 5) {
					map.put(name, name);
				}
				countSecondFile++;
			}
			in2.close();
			
			if (countFirstFile == countSecondFile) {
				System.out.println("File1 and File2 same length");
			} else {
				System.out.println("File1 and File2 not same length");
				System.exit(0);
			}
			
			FileWriter fwriter = new FileWriter(outputFile1);
			BufferedWriter out = new BufferedWriter(fwriter);
			
		    fstream = new FileInputStream(fileName1);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String name = in.readLine();
				String key = name.split(" ")[0];
				String sequence = in.readLine();
				String plus = in.readLine();
				String quality = in.readLine();
				if (!map.containsKey(key)) {
					out.write(name + "\n" + sequence + "\n" + plus + "\n" + quality + "\n");
				}
			}
			in.close();
			out.close();
			
			fwriter = new FileWriter(outputFile2);
			out = new BufferedWriter(fwriter);
			
		    fstream = new FileInputStream(fileName2);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String name = in.readLine();
				String key = name.split(" ")[0];
				String sequence = in.readLine();
				String plus = in.readLine();
				String quality = in.readLine();
				if (!map.containsKey(key)) {
					out.write(name + "\n" + sequence + "\n" + plus + "\n" + quality + "\n");
				}
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
