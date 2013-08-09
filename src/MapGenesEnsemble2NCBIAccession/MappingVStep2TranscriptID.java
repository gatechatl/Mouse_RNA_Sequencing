package MapGenesEnsemble2NCBIAccession;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MappingVStep2TranscriptID {
    public static void main(String[] args) {
    	try {
            
    		String[] list = {"ENSMUST00000131375", "ENSMUST00000139267", "ENSMUST00000141071", "ENSMUST00000155070"};
    		String fileName = args[0];
    		
    		HashMap map = new HashMap();
            FileInputStream fstream2 = new FileInputStream(fileName);
            DataInputStream din2 = new DataInputStream(fstream2);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));           
			
            while (in2.ready()) {
            	String str = in2.readLine();
            	String[] split = str.split("\t");
            	for (int i = 0; i < list.length; i++) {
            	    if (split[1].equals(list[i])) {
            	    	System.out.println(str);
            	    }
            	}
            }
            in2.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
