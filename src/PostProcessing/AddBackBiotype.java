package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AddBackBiotype {
    public static void main(String[] args) {
    	try {                		
    		String fileName = args[0];
    		String fileName2 = args[1];
    		String output = args[2];
    		HashMap map = new HashMap();
    		
    		FileWriter fwriter = new FileWriter(output);
			BufferedWriter out = new BufferedWriter(fwriter);
			
            FileInputStream fstream2 = new FileInputStream(fileName);
            DataInputStream din2 = new DataInputStream(fstream2);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));            
            while (in2.ready()) {
            	String str = in2.readLine();
            	String[] split = str.split("\t");
            	String biotype = split[1];
            	String gene_info = split[8];
            	String transcript_id = gene_info.split(";")[1].replaceAll("transcript_id", "").replaceAll("\"", "").trim();
            	if (map.containsKey(transcript_id)) {
            		System.out.println("Got duplicate transcript id");
            	}
            	map.put(transcript_id, biotype);
            	
            }
            in2.close();
            
            fstream2 = new FileInputStream(fileName2);
            din2 = new DataInputStream(fstream2);
            in2 = new BufferedReader(new InputStreamReader(din2));
            while (in2.ready()) {
            	String str = in2.readLine();
            	String[] split = str.split("\t");
            	String biotype = (String)map.get(split[1]);
            	out.write(str + "\t" + biotype);
            }
            in2.close();
            out.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
