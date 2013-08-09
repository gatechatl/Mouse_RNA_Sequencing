package PlotTimeSeries;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class GenerateTimeSeriesMicroRNA {

	public static void main(String[] args) {
		
		try {
			
			HashMap map = new HashMap();
			String ensembl_file = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\General_Information\\Mouse_Gene_Info\\Mouse_ncRNA_List.txt";
		    FileInputStream fstream = new FileInputStream(ensembl_file);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[1], split[0]);
			}
			in.close();

			
			HashMap finalMap = new HashMap();
			ensembl_file = "C:\\School Notes\\RNAseqCancer\\Gene_Analysis\\microRNA_GYS2.txt";
		    fstream = new FileInputStream(ensembl_file);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				
				String newstr = str.replaceAll("hsa-m", "M");
				newstr = newstr.replaceAll("-", "");
				newstr = newstr.replaceAll("R", "r");
				
				if (map.containsKey(newstr)) {
					System.out.println(str + "\t" + newstr + "\t" + map.get(newstr));
					finalMap.put(str, map.get(newstr));
				} else { 
					if (map.containsKey(newstr + "-1")) {
						System.out.println(str + "\t" + newstr + "\t" + map.get(newstr + "-1"));
						finalMap.put(str, map.get(newstr + "-1"));
					}
					if (map.containsKey(newstr + "-2")) {
						System.out.println(str + "\t" + newstr + "\t" + map.get(newstr + "-2"));
						finalMap.put(str, map.get(newstr + "-2"));
					}
					newstr = newstr.replaceAll("a", "");
					newstr = newstr.replaceAll("b", "");
					if (map.containsKey(newstr)) {
						System.out.println(str + "\t" + newstr + "\t" + map.get(newstr));
						finalMap.put(str, map.get(newstr));
					}										
				}			
			}
			in.close();
			
			HashMap data = new HashMap();
			ensembl_file = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\DE Intersection Genes\\Fold_Change\\Cuffdiff_Fold_Change\\Cuffdiff_FoldChange_ncRNA.txt";
		    fstream = new FileInputStream(ensembl_file);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				data.put(split[0], str);				
			}
			in.close();
			
			Iterator itr = finalMap.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				String ensemblID = (String)finalMap.get(key);
				System.out.println(key + "\t" + data.get(ensemblID));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
