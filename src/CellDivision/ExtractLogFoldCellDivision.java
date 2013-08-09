package CellDivision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class ExtractLogFoldCellDivision {

	
	public static void main(String[] args) {
		
		try {
						
			HashMap mousemap = new HashMap();					    
			FileInputStream fstream = new FileInputStream("C:\\School Notes\\GeneOntologyPathways\\Annotation\\Mus_musculus.NCBIM37.67.CHR.gtf");					    
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String geneID = grabMeta(split[8], "gene_id");
				String geneName = grabMeta(split[8], "gene_name");
				mousemap.put(geneName.toUpperCase(), geneID);
			}
			in.close();

			
			HashMap geneInfo = new HashMap();
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_Significant DE Expression\\Compare DE Genes\\Significant_protein_Comprehensive_Overlap_GeneList.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				geneInfo.put(split[0], str);
			}
			in.close();
			
			
			/*HashMap GOterm = new HashMap();
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\gene_association.goa_mouse";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				
				if (split.length > 2) {					
					split[2] = split[2].toUpperCase().trim();
					split[4] = split[4].trim();
					if (GOterm.containsKey(split[4])) {
						String GO = (String)GOterm.get(split[4]);
						GO += "," + split[2];
						
						GOterm.put(split[4], GO);
					} else {
						GOterm.put(split[4], split[2]);
					}
				}
			}
			in.close();
			
			*/
			
			HashMap GOterm = new HashMap();
			fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\gene_association.mgi";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				
				if (split.length > 2) {					
					split[2] = split[2].toUpperCase().trim();
					split[4] = split[4].trim();
					if (GOterm.containsKey(split[4])) {
						String GO = (String)GOterm.get(split[4]);
						GO += "," + split[2];
						
						GOterm.put(split[4], GO);
					} else {
						GOterm.put(split[4], split[2]);
					}
				}
			}
			in.close();
			
			/*Iterator itr = GOterm.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				System.out.println(GOterm.get(key) + "\t" + key);
			}*/
			
			
			HashMap humanmap = new HashMap();					    
			fstream = new FileInputStream("C:\\School Notes\\GeneOntologyPathways\\Annotation\\Homo_sapiens.GRCh37.67.CHR.gtf");					    
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String geneID = grabMeta(split[8], "gene_id");
				String geneName = grabMeta(split[8], "gene_name");
				humanmap.put(geneID, geneName.toUpperCase());
			}
			in.close();
			
			HashMap DE_logfold = new HashMap();
			fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_FoldChange\\DESeq_FoldChange_Protein.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (geneInfo.containsKey(split[0])) {
					DE_logfold.put(split[0], str);
				}
			}
			in.close();

			HashMap Cuff_logfold = new HashMap();
			fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Mouse_RNAseq_Cancer_FoldChange\\Cufflink_FoldChange_Protein.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (geneInfo.containsKey(split[0])) {
					Cuff_logfold.put(split[0], str);
				}
			}
			in.close();
						
			
			

			
			fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\AmiGO_Cell_Cycle_Phase.txt";
			//fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\AmiGO.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				split[0] = split[0].replaceAll(" ", "_");
				String outputFile = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\SigDE\\AmigoCellCyclePhase\\DESeq_LogFold_AmiGO_" + split[0] + "_SigDE.txt";
				FileWriter fwriter = new FileWriter(outputFile);
				BufferedWriter out = new BufferedWriter(fwriter);
				
				String outputFile2 = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\SigDE\\AmigoCellCyclePhase\\Cuffdiff_LogFold_AmiGO_" + split[0] + "_SigDE.txt";
				FileWriter fwriter2 = new FileWriter(outputFile2);
				BufferedWriter out2 = new BufferedWriter(fwriter2);
				//System.out.println(split[1]);
				
				HashMap writeOnce = new HashMap();
				if (GOterm.containsKey(split[1])) {
					//System.out.println(GOterm.get(split[1]) + "\t" + split[1]);
					String[] genes = ((String)GOterm.get(split[1])).split(",");
					for (String gene: genes) {
						gene = gene.toUpperCase();
						if (mousemap.containsKey(gene)) {
							if (!writeOnce.containsKey(gene)) {
								String mouseID = (String)mousemap.get(gene);
								if (Cuff_logfold.containsKey(mouseID)) {
									
									out2.write(gene + "\t" + "\t" + Cuff_logfold.get(mouseID) + "\n");
								}
								if (DE_logfold.containsKey(mouseID)) {
									out.write(gene + "\t" + DE_logfold.get(mouseID) + "\n");
								}
							}
							writeOnce.put(gene, gene);
						}
					}				
				}
				out.close();
				out2.close();
			}
			in.close();
	

			
			
			//fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\AmiGO_Cell_Cycle_Phase.txt";
			fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\AmiGO_Other_Cell_Division.txt";
			fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				split[0] = split[0].replaceAll(" ", "_");
				String outputFile = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\SigDE\\AmigoCellDivision\\DESeq_LogFold_AmiGO_" + split[0] + "_SigDE.txt";
				FileWriter fwriter = new FileWriter(outputFile);
				BufferedWriter out = new BufferedWriter(fwriter);
				
				String outputFile2 = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\SigDE\\AmigoCellDivision\\Cuffdiff_LogFold_AmiGO_" + split[0] + "_SigDE.txt";
				FileWriter fwriter2 = new FileWriter(outputFile2);
				BufferedWriter out2 = new BufferedWriter(fwriter2);
				//System.out.println(split[1]);
				
				HashMap writeOnce = new HashMap();
				if (GOterm.containsKey(split[1])) {
					//System.out.println(GOterm.get(split[1]) + "\t" + split[1]);
					String[] genes = ((String)GOterm.get(split[1])).split(",");
					for (String gene: genes) {
						gene = gene.toUpperCase();
						if (mousemap.containsKey(gene)) {
							if (!writeOnce.containsKey(gene)) {
								String mouseID = (String)mousemap.get(gene);
								if (Cuff_logfold.containsKey(mouseID)) {
									
									out2.write(gene + "\t" + "\t" + Cuff_logfold.get(mouseID) + "\n");
								}
								if (DE_logfold.containsKey(mouseID)) {
									out.write(gene + "\t" + DE_logfold.get(mouseID) + "\n");
								}
							}
							writeOnce.put(gene, gene);
						}
					}				
				}
				out.close();
				out2.close();
			}
			in.close();
			
			
			String[] tags = {"Cell_Division_Defect.txt", "G2_arrest.txt", "G0_1_arrest.txt", "S_arrest.txt"};
			for (String tag: tags) {
				
				String outputFile = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\SigDE\\PaperCellDivisionGene\\DESeq_LogFold_Paper_SigDE_" + tag;
				FileWriter fwriter = new FileWriter(outputFile);
				BufferedWriter out = new BufferedWriter(fwriter);				

				String outputFile2 = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\SigDE\\PaperCellDivisionGene\\Cuffdiff_LogFold_Paper_SigDE_" + tag;
				FileWriter fwriter2 = new FileWriter(outputFile2);
				BufferedWriter out2 = new BufferedWriter(fwriter2);				
				
				fstream = new FileInputStream("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cell Division Profiling\\Cell_Division\\Paper Annotation\\" + tag);
				din = new DataInputStream(fstream); 
				in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					if (humanmap.containsKey(split[0])) {
						
					
						String humanName = ((String)humanmap.get(split[0])).toUpperCase();
						if (mousemap.containsKey(humanName)) {
							String mouseID = (String)mousemap.get(humanName);
							if (Cuff_logfold.containsKey(mouseID)) {
								out2.write(humanName + "\t" + "\t" + Cuff_logfold.get(mouseID) + "\n");
							}
							if (DE_logfold.containsKey(mouseID)) {
								out.write(humanName + "\t" + DE_logfold.get(mouseID) + "\n");
							}
						}
					}
				}
				in.close();
				out2.close();
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
