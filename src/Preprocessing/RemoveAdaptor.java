package Preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class RemoveAdaptor {

	public static void main(String[] args) {
		String fileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\RNA-seq Experiment Cancer\\SampleInformation.txt";
		try {
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			String outputFile = "remove_adapter_0.sh";
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			int count = 0;
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				out.close();
				outputFile = "remove_adapter_" + count + ".sh";
				fwriter = new FileWriter(outputFile);
				out = new BufferedWriter(fwriter);
				String clip = "cutadapt -f 'fastq'";
				for (int i = 11; i < split.length; i++) {
					if (!split[i].trim().equals("")) {
						
						
						clip += " -b " + split[i];
						
						
					}
				}
				clip += " -q 15 -o Step1RemoveAdapter/" + split[0] + ".clipped" + ".fastq.gz " + split[0] + ".txt";
				System.out.println(clip);
				String gzip = "gzip " + split[0] + ".txt";
				System.out.println(gzip);
				out.write(clip + "\n");
				out.write(gzip + "\n");						
				count++;
				out.close();
				
				//System.out.println("nohup sh " + outputFile + " &");
			}
			
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
