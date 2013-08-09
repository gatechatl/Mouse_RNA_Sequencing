import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;


public class PeromyscusData {

	
	public static void main(String[] args) {
		
		
		try {
			
			
			String inputFile1 = args[0];
			String inputFile2 = args[1];
			String inputFile3 = args[2];
			String inputFile4 = args[3];
			String inputFile6 = args[4];
			String inputFile7 = args[5];
			String inputFile8 = args[6];
			String inputFile9 = args[7];
			String outputFile = args[8];
						
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			HashMap map1 = grabFile(inputFile1);
			HashMap map2 = grabFile(inputFile2);
			HashMap map3 = grabFile(inputFile3);
			HashMap map4 = grabFile(inputFile4);
			HashMap map6 = grabFile(inputFile6);
			HashMap map7 = grabFile(inputFile7);
			HashMap map8 = grabFile(inputFile8);
			HashMap map9 = grabFile(inputFile9);
			out.write("GeneID\tEnsemblID\tGeneLength\tA1\tA2\tA3\tA4\tS1\tS2\tS3\tS4\n");
			Iterator itr = map1.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				String data1 = (String)map1.get(key);
				String[] split1 = data1.split("\t");
				String ensembl = split1[3];
				String length = split1[6];				
				
				
				String data2 = (String)map2.get(key);
				String data3 = (String)map3.get(key);
				String data4 = (String)map4.get(key);
				String data5 = (String)map6.get(key);
				String data6 = (String)map7.get(key);
				String data7 = (String)map8.get(key);
				String data8 = (String)map9.get(key);
				out.write(ensembl + "\t" + ensembl + "\t" + length + "\t" + data1.split("\t")[4] + "\t" + data2.split("\t")[4] + "\t" + data3.split("\t")[4] + "\t" + data4.split("\t")[4] +  "\t" + data5.split("\t")[4] +  "\t" + data6.split("\t")[4] +  "\t" + data7.split("\t")[4] +  "\t" + data8.split("\t")[4] + "\n");
			}
		    
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static HashMap grabFile(String fileName) {
		HashMap map = new HashMap();
		try {
						
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[3], str);
			}
			in.close();
			
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
