import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;


public class GrabSpecificGTF {

	public static void main(String[] args) {
		String fileName = args[0];
		String outputFile = args[1];
		try {
			
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
		    FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			
			while (in.ready()) {
				String str = in.readLine();
				String geneInfo = str.split("\t")[8];
				String geneID = geneInfo.split(";")[0].replaceAll("gene_id", "").replaceAll("\"", "").trim();
				if (geneID.equals("ENSMUSG00000025162")) {
					out.write(str + "\n");
				}
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
