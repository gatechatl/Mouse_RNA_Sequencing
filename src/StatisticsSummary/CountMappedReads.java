package StatisticsSummary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CountMappedReads {

	public static void main(String[] args) {
		String fileName = args[0];
		try {

			HashMap mapPaired = new HashMap();
			HashMap mapSingle = new HashMap();
            FileInputStream fstream = new FileInputStream(fileName);
            DataInputStream din = new DataInputStream(fstream);
            BufferedReader in = new BufferedReader(new InputStreamReader(din));
            
            while (in.ready()) {
            	String str = in.readLine();
            	String[] split = str.split("\t");
    	        int flag = new Integer(split[1]); 
    	        String direction = "+";
    	        boolean pairend = false;
    	        boolean reverse = false;
    	        
    	        if (flag >= 128) {
    	        	flag = flag - 128;
    	            reverse = true;	
    	        }
    	        if (flag >= 64) {
    	        	flag = flag - 64;
    	        	reverse = false;
    	        }
    	        if (flag >= 32) {
    	        	flag = flag - 32;
    	        	direction = "+";
    	        }
    	        if (flag >= 16) {
    	        	flag = flag - 16;
    	        	direction = "-";
    	        }
    	        
    	        if (flag >= 8) {
    	        	flag = flag - 8;	                                	
    	        }
    	        if (flag >= 4) {
    	        	flag = flag - 4;	                                	
    	        }
    	        if (flag >= 2) {
    	        	flag = flag - 2;
    	        	pairend = true;
    	        }
    	        if (flag >= 1) {
    	        	flag = flag - 1;
    	        }
    	        
    	        if (pairend) {
    	        	mapPaired.put(split[0], split[0]);
    	        	
    	        } else {
    	        	mapSingle.put(split[0], split[0]);
    	        }
            }
            in.close();

            System.out.println("Unique Pairs: " + mapPaired.size());
            System.out.println("Single Mapped: " + mapSingle.size());
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
