package PostProcessing.CompareExpression;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CompareDESeqVsCufflink {

	
	public static void main(String[] args) {
		
		try {
			
            HashMap map = new HashMap();
            HashMap overlapSig = new HashMap();
            String DEseqFile= args[0];
            String CuffdiffFile = args[1];
            String outputCuffDiff = args[2];
            String outputCuffDiffSig = args[3];
            String overlap_sig = args[4];
            
			FileWriter fwriter = new FileWriter(outputCuffDiff);
			BufferedWriter out = new BufferedWriter(fwriter);

			FileWriter fwriter_sig = new FileWriter(outputCuffDiffSig);
			BufferedWriter out_sig = new BufferedWriter(fwriter_sig);

			FileWriter fwriter_overlap = new FileWriter(overlap_sig);
			BufferedWriter out_overlap = new BufferedWriter(fwriter_overlap);
			
            FileInputStream fstream = new FileInputStream(DEseqFile);
            DataInputStream din = new DataInputStream(fstream);
            BufferedReader in = new BufferedReader(new InputStreamReader(din));     
            in.readLine();
            while (in.ready()) {
            	String str = in.readLine();
            	String[] split = str.split("\t");
            	String ensembl = split[0].replaceAll("\"", "");
            	double pval = new Double(split[6]);
            	double qval = new Double(split[7]);
            	if (pval < 0.05 && qval < 0.05) {
            		overlapSig.put(ensembl,  ensembl);
            	}
            	map.put(ensembl, ensembl);
            }
            in.close();

            fstream = new FileInputStream(CuffdiffFile);
            din = new DataInputStream(fstream);
            in = new BufferedReader(new InputStreamReader(din));
            while (in.ready()) {
            	String str = in.readLine();
            	String[] split = str.split("\t");
            	if (map.containsKey(split[0])) {            		
            		out.write(str + "\n");
            		if (split[13].equals("yes")) {
            			out_sig.write(str + "\n");
            		}            		
            	}
            	if (split[13].equals("yes")) {
            		if (overlapSig.containsKey(split[0])) {
            			out_overlap.write(str + "\n");
            		}
            	}
            }
            
            in.close();
            out.close();
            out_overlap.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
