package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class MergeVSteps {

	public static void main(String[] args) {	    
    	try {                		
    		String fileName = args[0];
    		String fileName2 = args[1];
    		String fileName3 = args[2];
    		String fileName4 = args[4];
    		String output = args[5];
    		HashMap map = new HashMap();
    		
    		FileWriter fwriter = new FileWriter(output);
			BufferedWriter out = new BufferedWriter(fwriter);
			
            FileInputStream fstream2 = new FileInputStream(fileName);
            DataInputStream din2 = new DataInputStream(fstream2);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));            
            while (in2.ready()) {
            	String str = in2.readLine();
            	String[] split = str.split("\t");
            	String key = split[0] + "\t" + split[1] + "\t" + split[2] + "\t" + split[3] + "\t" + split[4] + "\t" + split[5] + "\t" + split[6] + "\t" + split[7];
            	int reads = new Integer(split[8]);
            	if (map.containsKey(key)) {
            		System.out.println("Problem");
            	}
            	map.put(key, reads);
            }
            in2.close();
            
            fstream2 = new FileInputStream(fileName2);
            din2 = new DataInputStream(fstream2);
            in2 = new BufferedReader(new InputStreamReader(din2));            
            while (in2.ready()) {
            	String str = in2.readLine();
            	String[] split = str.split("\t");
            	String key = split[0] + "\t" + split[1] + "\t" + split[2] + "\t" + split[3] + "\t" + split[4] + "\t" + split[5] + "\t" + split[6] + "\t" + split[7];
            	int reads = new Integer(split[8]);
            	if (map.containsKey(key)) {
            		int previous_reads = (Integer)map.get(key);
            		reads = previous_reads + reads;
            		map.put(key, reads);
            	} else {         	
            	    map.put(key, reads);
            	}
            }
            in2.close();

            fstream2 = new FileInputStream(fileName3);
            din2 = new DataInputStream(fstream2);
            in2 = new BufferedReader(new InputStreamReader(din2));            
            while (in2.ready()) {
            	String str = in2.readLine();
            	String[] split = str.split("\t");
            	String key = split[0] + "\t" + split[1] + "\t" + split[2] + "\t" + split[3] + "\t" + split[4] + "\t" + split[5] + "\t" + split[6] + "\t" + split[7];
            	int reads = new Integer(split[8]);
            	if (map.containsKey(key)) {
            		int previous_reads = (Integer)map.get(key);
            		reads = previous_reads + reads;
            		map.put(key, reads);
            	} else {         	
            	    map.put(key, reads);
            	}
            }
            in2.close();
            
            fstream2 = new FileInputStream(fileName4);
            din2 = new DataInputStream(fstream2);
            in2 = new BufferedReader(new InputStreamReader(din2));            
            while (in2.ready()) {
            	String str = in2.readLine();
            	String[] split = str.split("\t");
            	String key = split[0] + "\t" + split[1] + "\t" + split[2] + "\t" + split[3] + "\t" + split[4] + "\t" + split[5] + "\t" + split[6] + "\t" + split[7];
            	int reads = new Integer(split[8]);
            	if (map.containsKey(key)) {
            		int previous_reads = (Integer)map.get(key);
            		reads = previous_reads + reads;
            		map.put(key, reads);
            	} else {         	
            	    map.put(key, reads);
            	}
            }
            in2.close();
            
            Iterator itr = map.keySet().iterator();
            while (itr.hasNext()) {
            	String str = (String)itr.next();
            	int reads = (Integer)map.get(str);
            	out.write(str + "\t" + reads);
            }
            out.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
}
