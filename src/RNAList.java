import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Get list of RNA sequences from the output
 * @author Tim Shaw
 *
 */
public class RNAList {
    public static void main(String[] args) {
    	try {
            
    		
    		String fileName = args[0];
    		String output = args[1];
    		HashMap map = new HashMap();
            FileInputStream fstream2 = new FileInputStream(fileName);
            DataInputStream din2 = new DataInputStream(fstream2);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));
            
    		FileWriter fwriter = new FileWriter(output);
			BufferedWriter out = new BufferedWriter(fwriter);
			
            while (in2.ready()) {
            	String str = in2.readLine();
            	String[] split = str.split("\t");
            	String chromosome = split[1];
            	String name = split[5];
            	String start = split[6];
            	String end = split[7];
            	String direction = split[8];
            	String geneInfo = split[9];
            	if (geneInfo.contains("_name")) {
            		geneInfo = geneInfo.split("_name")[1].trim();
            	}
            	String key = chromosome + "\t" + name + "\t" + start + "\t" + end + "\t" + direction + "\t" + geneInfo;
            	if (!map.containsKey(key)) {
            		map.put(key, 1);
            	} else {
            		int countKey = (Integer)map.get(key);
            		countKey++;
            		map.put(key, countKey);
            	}
            	
            }
            in2.close();
            Iterator itr = map.keySet().iterator();
            int count = 0;
            while (itr.hasNext()) {
            	String str = (String)itr.next();
            	int countKey = (Integer)map.get(str);
            	out.write(countKey + "\t" + str + "\n");
            	
            	count++;
            	if (count % 1000 == 0) {
            		out.flush();
            	}            	
            }
            out.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
}
