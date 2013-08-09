import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;


public class CombineReadAndGeneOutput {
	public static void main(String[] args) {
		String fileName1 = args[0];
		String fileName2 = args[1];
		String fileName3 = args[2];
		String outputFile = args[3];
	    try {
	    	HashMap map = new HashMap();
    		FileInputStream fstream = new FileInputStream(fileName1);
	        DataInputStream din = new DataInputStream(fstream);
	        BufferedReader in = new BufferedReader(new InputStreamReader(din));
	        while (in.ready()) {
	            String str2 = in.readLine();
	            String[] split = str2.split("\t");
	            map.put(split[0], str2);
	        }
	        in.close();
    		
			FileWriter fwriter2 = new FileWriter(outputFile);
            BufferedWriter out2 = new BufferedWriter(fwriter2);
            
            HashMap map2 = new HashMap();
    		FileInputStream fstream2 = new FileInputStream(fileName2);
	        DataInputStream din2 = new DataInputStream(fstream2);
	        BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));
	        while (in2.ready()) {
	        	String str = in2.readLine();
	        	String[] split = str.split("\t");	    
	        	map2.put(split[0], new Integer(split[1]));
	        }
	        in2.close();
    		fstream2 = new FileInputStream(fileName3);
	        din2 = new DataInputStream(fstream2);
	        in2 = new BufferedReader(new InputStreamReader(din2));
	        while (in2.ready()) {
	        	String str = in2.readLine();
	        	String[] split = str.split("\t");
	        	if (map.containsKey(split[0])) {
	        		String str2 = (String)map.get(split[0]);
	        		str2 = str2.replace(split[0] + "\t", "");
	        		str2 = str2.replace(";", "");
	        		int readNum = (Integer)map2.get(split[0]);
	        		readNum = readNum + new Integer(split[1]);
	        		out2.write(split[0] + "\t" + readNum + "\t" + str2 + "\n");
	        	} else {
	        		System.out.println("Something Bad Happned");	        		
	        	}
	        }
	        in2.close();
	        out2.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
