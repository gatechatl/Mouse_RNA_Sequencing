package PostProcessing;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ConvertEnsembl2Gene {


	public static HashMap getGeneList(String fileName) {
		HashMap map = new HashMap();
		try {

			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				map.put(str.trim(), str.trim());
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static void main(String[] args) {
		
		try {
			
			String fileName = args[0];
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
