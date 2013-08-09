package MapGenesEnsemble2NCBIAccession;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConvertEnsemblGene2Transcript {

	public static void main(String[] args) {
		String fileName = args[0];
		
		String newFileName = args[1];
		try {

			FileWriter fwriter1 = new FileWriter(newFileName);
		    BufferedWriter out = new BufferedWriter(fwriter1);
		    
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String ensembl_id = split[0].replaceAll(";", "");
				String transcript_id = getEnsemblID(ensembl_id).split("\t")[1].trim();
				out.write(transcript_id + "\t" + str + "\n");
				System.out.println(transcript_id + "\t" + str);
				out.flush();
				
			}
			
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
		
	public static String getEnsemblID(String peptide_name) {
		
		String returnStr = "";
		try {
			FileWriter fwriter1 = new FileWriter("ensembl.R");
		    BufferedWriter out = new BufferedWriter(fwriter1);
		    
		    out.write("library(biomaRt);\n");
		    out.write("mart <- useMart(biomart = \"ensembl\", dataset = \"mmusculus_gene_ensembl\");\n");
		    out.write("results <- getBM(attributes = c(\"ensembl_gene_id\", \"ensembl_transcript_id\", \"ensembl_peptide_id\"),\n");		    
		    
		    out.write("filters = \"ensembl_peptide_id\", values = \"" + peptide_name + "\", mart = mart);\n");
		    out.write("write(paste(results[1],results[2],results[3]), file=\"ensembl.txt\");\n");
		    
		    out.close();
	
			fwriter1 = new FileWriter("ensembl.sh");
		    out = new BufferedWriter(fwriter1);
		    out.write("R --vanilla < ensembl.R");
		    out.close();
	    	int exitValue = -1;
	    	try {
	    	Process process = Runtime.getRuntime().exec("sh ensembl.sh");
	            while (true) {	                	
	                try {
	                    exitValue = process.exitValue();
	
	                    break;
	                } catch (IllegalThreadStateException e) {	                        
	                    try {
	                    	Thread.sleep(10);
	                    } catch (InterruptedException e1) {
	
	                    }
	                }
	            }
	    	} catch (IOException ex) {
	    		ex.printStackTrace();
	    	}
	    	FileInputStream fstream = new FileInputStream("ensembl.txt");
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
		    while (in.ready()) {
		    	String str = in.readLine();
		    	if (!str.equals("")) {
		    	    returnStr = str;
		    	}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnStr;
	}
}
