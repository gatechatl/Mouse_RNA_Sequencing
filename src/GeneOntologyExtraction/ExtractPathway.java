package GeneOntologyExtraction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class ExtractPathway {

	
	public static void main(String[] args) {
		
		try {
			
			
			HashMap map = new HashMap();
			
		    //FileInputStream fstream = new FileInputStream("C:\\School Notes\\GeneOntologyPathways\\Annotation\\Mus_musculus.NCBIM37.67.CHR.gtf");
		    FileInputStream fstream = new FileInputStream("C:\\School Notes\\GeneOntologyPathways\\Annotation\\Homo_sapiens.GRCh37.67.CHR.gtf");
		    
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String geneID = grabMeta(split[8], "gene_id");
				String geneName = grabMeta(split[8], "gene_name");
				map.put(geneName, geneID);
			}
			in.close();
			
			String[] name = {"DNA demethylation", "DNA methylation", "dosage compensation", "negative regulation of gene expression, epigenetic", "positive regulation of gene expression, epigenetic", "posttranscriptional gene silencing", "regulation of gene expression by genetic imprinting"};
			String[] goterms = {"GO:0080111", "GO:0006306", "GO:0007549", "GO:0045814", "GO:0045815", "GO:0016441", "GO:0006349"};
			
			for (int i = 0; i < goterms.length; i++) {
				HashMap goMap = new HashMap();
				String outputFile = "C:\\School Notes\\GeneOntologyPathways\\Epigenetics\\" + name[i] + ".Human.GO.txt";
				FileWriter fwriter = new FileWriter(outputFile);
				BufferedWriter out = new BufferedWriter(fwriter);
				
			    fstream = new FileInputStream("C:\\School Notes\\GeneOntologyPathways\\gene_association.goa_human.txt");
				din = new DataInputStream(fstream); 
				in = new BufferedReader(new InputStreamReader(din));			
				while (in.ready()) {
					String str = in.readLine();
					while (str.contains("\t\t")) {
						str = str.replaceAll("\t\t", "\t");
					}
					String[] split = str.split("\t");
					if (split.length > 3) {
						if (split[3].equals(goterms[i])) {
							goMap.put(split[2], map.get(split[2]));
							//out.write(split[2] + "\t" + map.get(split[2]) + "\n");
						}
					}
				}
				in.close();
				Iterator itr = goMap.keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					String ensembl = (String)goMap.get(key);
					out.write(key + "\t" + ensembl + "\n");
				}
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String grabMeta(String text, String id) {
		String returnval = "";
		if (text.contains(id)) {
			String val = text.split(id)[1].split(";")[0].trim();
			val = val.replaceAll("\"", "");
			val.trim();
			returnval = val;				
		}
		return returnval;
	}
}
