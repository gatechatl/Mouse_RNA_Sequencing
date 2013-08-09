package PlotTimeSeries;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class GenerateTimeSeries {

	public static void main(String[] args) {
		
		try {
			
			HashMap map = new HashMap();
			String ensembl_file = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\General_Information\\Mouse_Gene_Info\\Mouse_Coding_List.txt";
		    FileInputStream fstream = new FileInputStream(ensembl_file);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[1].toUpperCase(), split[0]);
			}
			in.close();

			HashMap data = new HashMap();
			ensembl_file = "C:\\School Notes\\RNAseqCancer\\Analysis Result Report\\Mouse_Time_Series\\DE Intersection Genes\\Fold_Change\\Cuffdiff_Fold_Change\\Cuffdiff_FoldChange_Protein.txt";
		    fstream = new FileInputStream(ensembl_file);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				data.put(split[0], str);
			}
			in.close();
			
			String[] lists = {"GBE1", "AMY1", "AMY2A5", "AGL", "AMY2A3", "AMY2A4", "PYGM", "PYGL", "PYGB", "GYS1", "GYS2", "HIF1A"};
			for (String list: lists) {
				String ensembl = (String)map.get(list);
				System.out.println(list + "\t" + data.get(ensembl));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
