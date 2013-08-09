package MapGenesEnsemble2NCBIAccession;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;


/**
 * Input genes that were mapped
 * Input column that contains the ensembl information
 * Input knownToEnsembl and knownToRefSeq files
 * Output new Dataset that append acecssion information at the end of the alignment dataset
 * @author Tim Shaw
 *
 */
public class MapEnsemble2NCBIAccession {

	public static void main(String[] args) {
    	try {
    		
    		String geneFile = args[0];
    		int ensemblColumnNumber = new Integer(args[1]);
    		String ensemblFile = args[2];
    		String refSeqFile = args[3];
    		String outFile = args[4];
    		
    		HashMap ensembl_map = new HashMap();
    		HashMap refSeq_map = new HashMap();
		    FileInputStream fstream = new FileInputStream(ensemblFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (ensembl_map.containsKey(split[1])) {
					String value = (String)ensembl_map.get(split[1]) + "\t" + split[0];
				} else {
				    ensembl_map.put(split[1], split[0]);
				}
			}
			in.close();
			
		    fstream = new FileInputStream(refSeqFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (refSeq_map.containsKey(split[0])) {
					String value = (String)refSeq_map.get(split[0]) + "," + split[1];
				} else {
					refSeq_map.put(split[0], split[1]);
				}
			}
			in.close();
			
			
			FileWriter fwriter = new FileWriter(outFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
		    fstream = new FileInputStream(geneFile);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String ensembl_id = split[ensemblColumnNumber];
				
				String ensembl_list = (String)ensembl_map.get(ensembl_id);
				String[] ensembl_split = ensembl_list.split("\t");
				String ncbi_id = "";
				for (int i = 0; i < ensembl_split.length; i++) {
					if (ncbi_id.equals("")) {
					    ncbi_id = (String)refSeq_map.get(ensembl_split[i]);
					} else {
						ncbi_id += "," + (String)refSeq_map.get(ensembl_split[i]);
					}					
				}
				out.write(str + "\t" + ncbi_id + "\n");
				
			}
			out.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	
}
