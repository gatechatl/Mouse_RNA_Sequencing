package GeneOntologyExtraction;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ExtractSignificantGene {

	
	public static void main(String[] args) {
		
		
		try {
			
			String[] name = {"DNA demethylation", "DNA methylation", "dosage compensation", "negative regulation of gene expression, epigenetic", "positive regulation of gene expression, epigenetic", "posttranscriptional gene silencing", "regulation of gene expression by genetic imprinting"};
			
			HashMap map = new HashMap();
			//FileInputStream fstream = new FileInputStream("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\Protein_GeneList\\Significant_protein_Comprehensive_Overlap_GeneList.txt");
			//FileInputStream fstream = new FileInputStream("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\ncRNA_GeneList\\Significant_ncRNA_Comprehensive_Overlap_GeneList.txt");
			FileInputStream fstream = new FileInputStream("C:\\School Notes\\Glioma\\Report\\DE Summary\\Protein\\coding_differential_expression_comprehensive_foldchange.txt");
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[0], str);
			}
			in.close();
			
			for (int i = 0; i < name.length; i++) {
				//fstream = new FileInputStream("C:\\School Notes\\GeneOntologyPathways\\Epigenetics\\" + name[i] + ".Mouse.GO.txt");
				fstream = new FileInputStream("C:\\School Notes\\GeneOntologyPathways\\Epigenetics\\" + name[i] + ".Human.GO.txt");
				din = new DataInputStream(fstream); 
				in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					if (map.containsKey(split[1])) {
						System.out.println(map.get(split[1]));
					}
				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
