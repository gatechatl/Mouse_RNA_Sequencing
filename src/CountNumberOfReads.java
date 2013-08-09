import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public class CountNumberOfReads {
    public static void main(String[] args) {
    	try {
            
    		
    		String fileName = args[0];
    		int totalCount = 0;
            FileInputStream fstream2 = new FileInputStream(fileName);
            DataInputStream din2 = new DataInputStream(fstream2);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));
            while (in2.ready()) {
            	String str = in2.readLine();
            	String[] split = str.split("\t");
            	if (split.length > 1) {
            		totalCount = totalCount + new Integer(split[1]);
            	}
            }
            in2.close();
            System.out.println(totalCount);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
