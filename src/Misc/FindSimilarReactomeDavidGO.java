package Misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class FindSimilarReactomeDavidGO {

	
	public static void main(String[] args) {
		
		
		try {
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\Compare DE Genes\\Protein_DE_Comprehensive_DAVID_Table.txt";
			HashMap GO = new HashMap();
			HashMap KEGG = new HashMap();
			HashMap Reactome = new HashMap();
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (split.length > 9) {
					GO.put(split[0], split[5]);
					KEGG.put(split[0], split[9]);
				}
			}
			in.close();

			HashMap geneInfo = new HashMap();
			fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\Compare DE Genes\\Significant_protein_Comprehensive_Overlap_GeneList.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				geneInfo.put(split[0], str);
			}
			in.close();
			
			fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\Compare DE Genes\\Protein_DE_Comprehensive_Reactome_Table.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");			
				if (split.length > 3) {
					Reactome.put(split[0], split[3]);
				}
			}
			in.close();
			
			String outputFile = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\Compare DE Genes\\Comprehensive_DE_Gene_Annotation_Matching_Small_Gene_List.txt";
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			out.write("DE_Gene_EnsemblID\tDE_GeneName\tDE_Chromosome\tDE_Start\tDE_End\tDE_Direction\tAnnotation_Match\tQuery_Gene\tDatabase_Source\n");
			
			fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\Compare DE Genes\\small_gene_list_david_reactome.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));		
			in.readLine();
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (split.length > 5) {
					String geneName = split[0];
					String[] reactome = split[3].split(";");
					String[] go = split[4].split(",");
					String[] kegg = split[5].split(",");
					
					for (int i = 0; i < reactome.length; i++) {
						
						Iterator itr = Reactome.keySet().iterator();
						while (itr.hasNext()) {
							String key = (String)itr.next();
							
							String r = (String)Reactome.get(key);
							//System.out.println(key + "\t" + r);
							String[] rs = r.split(";");
							for (int j = 0; j < rs.length; j++) {
								if (rs[j].equals(reactome[i])) {
									out.write(geneInfo.get(key) + "\t" + rs[j] + "\t" + geneName + "\tReactome" + "\n");
									//System.out.println(geneInfo.get(key) + "\t" + rs[j] + "\t" + geneName + "\tReactome");
								}
							}
						}
					}
					
					for (int i = 0; i < go.length; i++) {
						Iterator itr = GO.keySet().iterator();
						while (itr.hasNext()) {
							String key = (String)itr.next();
							
							String r = (String)GO.get(key);
							//System.out.println(key + "\t" + r);
							String[] rs = r.split(",");
							for (int j = 0; j < rs.length; j++) {
								if (rs[j].equals(go[i])) {
									out.write(geneInfo.get(key) + "\t" + rs[j] + "\t" + geneName + "\tGO_FAT_BC\n");
									//System.out.println(geneInfo.get(key) + "\t" + rs[j] + "\t" + geneName + "\tGO_FAT_BC");
								}
							}
						}						
					}
					
					for (int i = 0; i < kegg.length; i++) {
						Iterator itr = KEGG.keySet().iterator();
						while (itr.hasNext()) {
							String key = (String)itr.next();
							
							String r = (String)KEGG.get(key);
							//System.out.println(key + "\t" + r);
							
							String[] rs = r.split(",");
							
							for (int j = 0; j < rs.length; j++) {
								if (rs[j].split(":")[0].replaceAll("mmu", "hsa").equals(kegg[i].split(":")[0]) && !rs[j].split(":")[0].replaceAll("mmu", "hsa").equals("")) {
									out.write(geneInfo.get(key) + "\t" + rs[j] + "\t" + geneName + "\tKEGG\n");
									
									System.out.println(geneInfo.get(key) + "\t" + rs[j] + "\t" + geneName + "\tKEGG");
								}
							}
						}									
					}
				}
				
				
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
