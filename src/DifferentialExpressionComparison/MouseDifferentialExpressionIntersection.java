package DifferentialExpressionComparison;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class MouseDifferentialExpressionIntersection {

	public static HashMap geneName2geneID = null;
	public static HashMap geneID2geneName = null;
	public static void main(String[] args) {
		
		try {
			

		   
			HashMap[] m = getEnsemblandGeneID();
			geneName2geneID = m[0];
			geneID2geneName = m[1];
	
	
		    
			HashMap Coding = grabCategoryList("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Annotation\\Mouse_Coding_List.txt");
			HashMap ncRNA = grabCategoryList("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Annotation\\Mouse_ncRNA_List.txt");
			HashMap Other = grabCategoryList("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Annotation\\Mouse_Other_List.txt");
			
			HashMap mapk = grabPathway("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\KEGG_Pathways\\MAPK_pathway.txt");
			HashMap notch = grabPathway("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\KEGG_Pathways\\NOTCH_pathway.txt");
			HashMap cancer = grabPathway("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\KEGG_Pathways\\Pathways_in_cancer.txt");
			HashMap tgf = grabPathway("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\KEGG_Pathways\\TGF-beta_pathway.txt");
			HashMap wnt = grabPathway("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\KEGG_Pathways\\Wnt_pathway.txt");
			HashMap hedge = grabPathway("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\KEGG_Pathways\\Hedgehog_pathway.txt");
			
			HashMap[] bayseq = getBaySetOutputSig();
			HashMap[] edgeR = getEdgeROutput();
			HashMap[] DEseq = getDEseqOutputSig();
			HashMap[] cufflink = getCufflinkOutput();
			
			
			HashMap[] edgeR_data = getEdgeRData();
			HashMap[] DEseq_data = getDEseqData();
			HashMap[] cufflink_data = getCufflinkData();
			
			HashMap Coding_all = new HashMap();
			HashMap ncRNA_all = new HashMap();
			HashMap Other_all = new HashMap();
			
			HashMap three_all_coding = new HashMap();
			HashMap three_all_ncRNA = new HashMap();
			HashMap bayseq_all = new HashMap();
			HashMap edgeR_all = new HashMap();
			HashMap DEseq_all = new HashMap();
			HashMap cufflink_all = new HashMap();
	

			
			
			HashMap bayseq_coding = new HashMap();
			HashMap edgeR_coding = new HashMap();
			HashMap DEseq_coding = new HashMap();
			HashMap cufflink_coding = new HashMap();
	
			HashMap bayseq_ncRNA = new HashMap();
			HashMap edgeR_ncRNA = new HashMap();
			HashMap DEseq_ncRNA = new HashMap();
			HashMap cufflink_ncRNA = new HashMap();

			HashMap three_coding = new HashMap();
			HashMap merge_coding = new HashMap();
			
			
			HashMap three_ncRNA = new HashMap();
			HashMap merge_ncRNA = new HashMap();
			

			
			
			System.out.println();
			System.out.print("Bayseq\t");
			
			//HashMap[] overlap = new HashMap[7];
			
			for (int i = 0; i < 7; i++) {
				System.out.print("\t" + bayseq[i].size());			
				Iterator itr = bayseq[i].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					bayseq_all.put(key,  key);
					
					if (Coding.containsKey(key)) {
						bayseq_coding.put(key, key);
						three_all_coding.put(key, key);
					}
					if (ncRNA.containsKey(key)) {
						bayseq_ncRNA.put(key, key);
						three_all_ncRNA.put(key, key);
					}
				}
	
			}
			System.out.println();
			System.out.print("EdgeR\t");
			for (int i = 0; i < 7; i++) {
				System.out.print("\t" + edgeR[i].size());
				Iterator itr = edgeR[i].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					edgeR_all.put(key, key);
					
					if (Coding.containsKey(key)) {
						edgeR_coding.put(key, key);
						three_all_coding.put(key, key);
					}
					if (ncRNA.containsKey(key)) {
						edgeR_ncRNA.put(key, key);
						three_all_ncRNA.put(key, key);
					}
				}
			}
			System.out.println();
			System.out.print("DESeq");
			for (int i = 0; i < 7; i++) {
				System.out.print("\t" + DEseq[i].size());
				Iterator itr = DEseq[i].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					DEseq_all.put(key,  key);
					
					if (Coding.containsKey(key)) {
						DEseq_coding.put(key, key);
						three_all_coding.put(key, key);
					}
					if (ncRNA.containsKey(key)) {
						DEseq_ncRNA.put(key, key);
						three_all_ncRNA.put(key, key);
					}
				}
			}
			System.out.println();
			System.out.print("Cufflink");
			for (int i = 0; i < 7; i++) {
				System.out.print("\t" + cufflink[i].size());
				Iterator itr = cufflink[i].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					cufflink_all.put(key, key);
					if (Coding.containsKey(key)) {
						cufflink_coding.put(key, key);
					}
					if (ncRNA.containsKey(key)) {
						cufflink_ncRNA.put(key, key);
					}
				}
			}
			/*
			int n1 = cufflink_coding.size();
			int n2 = bayseq_coding.size();
			int n3 = DEseq_coding.size();
			int n4 = edgeR_coding.size();
			int n12 = intersect(cufflink_coding, bayseq_coding).size();
			int n13 = intersect(cufflink_coding, DEseq_coding).size();
			int n14 = intersect(cufflink_coding, edgeR_coding).size();			
			int n23 = intersect(bayseq_coding, DEseq_coding).size();
			int n24 = intersect(bayseq_coding, edgeR_coding).size();
			int n34 = intersect(DEseq_coding, edgeR_coding).size();
			int n123 = intersect(cufflink_coding, bayseq_coding, DEseq_coding).size();
			int n124 = intersect(cufflink_coding, bayseq_coding, edgeR_coding).size();
			int n134 = intersect(cufflink_coding, DEseq_coding, edgeR_coding).size();
			int n234 = intersect(bayseq_coding, DEseq_coding, edgeR_coding).size();
			int n1234 = intersect(cufflink_coding, bayseq_coding, DEseq_coding, edgeR_coding).size();
			*/
			
			int n1 = cufflink_ncRNA.size();
			int n2 = bayseq_ncRNA.size();
			int n3 = DEseq_ncRNA.size();
			int n4 = edgeR_ncRNA.size();
			int n12 = intersect(cufflink_ncRNA, bayseq_ncRNA).size();
			int n13 = intersect(cufflink_ncRNA, DEseq_ncRNA).size();
			int n14 = intersect(cufflink_ncRNA, edgeR_ncRNA).size();			
			int n23 = intersect(bayseq_ncRNA, DEseq_ncRNA).size();
			int n24 = intersect(bayseq_ncRNA, edgeR_ncRNA).size();
			int n34 = intersect(DEseq_ncRNA, edgeR_ncRNA).size();
			int n123 = intersect(cufflink_ncRNA, bayseq_ncRNA, DEseq_ncRNA).size();
			int n124 = intersect(cufflink_ncRNA, bayseq_ncRNA, edgeR_ncRNA).size();
			int n134 = intersect(cufflink_ncRNA, DEseq_ncRNA, edgeR_ncRNA).size();
			int n234 = intersect(bayseq_ncRNA, DEseq_ncRNA, edgeR_ncRNA).size();
			int n1234 = intersect(cufflink_ncRNA, bayseq_ncRNA, DEseq_ncRNA, edgeR_ncRNA).size();
			
			


			System.out.println("venn.plot <- draw.quad.venn(area1 = " + n1 + ",area2 = " + n2 + ",area3 = " + n3 + ",area4 = " + n4 + ",n12 = " + n12 + ",n13 = " + n13 + ",n14 = " + n14 + ",n23 = " + n23 + ",n24 = " + n24 + ",n34 = " + n34 + ",n123 = " + n123 + ",n124 = " + n124 + ",n134 = " + n134 + ",n234 = " + n234 + ",n1234 = " + n1234 + ",category = c('Cuffdiff', 'bayseq', 'DESeq', 'edgeR'),fill = c('orange', 'red', 'green', 'blue'),lty = 'dashed',cex = 2,cat.cex = 2,cat.col = c('orange', 'red', 'green', 'blue'));");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	public static HashMap intersect(HashMap map1, HashMap map2, HashMap map3, HashMap map4) {		
		HashMap map12 = intersect(map1, map2);
		HashMap map123 = intersect(map12, map3);
		return intersect(map123, map4);
	}
	public static HashMap intersect(HashMap map1, HashMap map2, HashMap map3) {		
		HashMap map12 = intersect(map1, map2);
		return intersect(map12, map3);
	}
	public static HashMap intersect(HashMap map1, HashMap map2) {
		HashMap result = new HashMap();
		Iterator itr = map1.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String)itr.next();
			if (map2.containsKey(key)) {
				result.put(key,  key);			
			}
		}
		return result;
	}
	public static HashMap removeSomeHashMap(HashMap original, HashMap remove) {
		HashMap whatleft = new HashMap();
		Iterator itr = original.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String)itr.next();
			if (!remove.containsKey(key)) {
				whatleft.put(key, key);
			}
		}
		return whatleft;
	}
	public static HashMap intersectHashMap(HashMap map1, HashMap map2) {
		HashMap intersect = new HashMap();
		Iterator itr = map1.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String)itr.next();
			if (map2.containsKey(key)) {
				intersect.put(key, key);
			}
		}
		return intersect;
	}
	public static HashMap grabCategoryList(String fileName) {
		HashMap map = new HashMap();
		try {
			
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[0], split[1] + "\t" + split[2] + "\t" + split[3] + "\t" + split[4] + "\t" + split[5]);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;		
	}
	
	public static HashMap grabPathway(String fileName) {
		HashMap map = new HashMap();
		try {
			
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));			
			while (in.ready()) {
				String str = in.readLine();
				String ensembl = (String)geneName2geneID.get(str.trim());
				map.put(ensembl, ensembl);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
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
	
	
	
	public static HashMap[] getEnsemblandGeneID() {
		HashMap[] map = new HashMap[2];
		map[0] = new HashMap();
		map[1] = new HashMap();
		try {
			
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Mus_musculus.NCBIM37.67.CHR.gtf";
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));			
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String geneID = grabMeta(split[8], "gene_id");
				String geneName = grabMeta(split[8], "gene_name");
				String otherInformation = split[0] + "\t" + 
				map[0].put(geneName,  geneID);
				map[1].put(geneID, geneName);
			}
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static HashMap[] getCufflinkOutput() {
		HashMap[] map = new HashMap[7];
		try {
			String[] files = {"Hour0vsHour2_gene_exp.diff", "Hour0vsHour4_gene_exp.diff", "Hour0vsHour6_gene_exp.diff", "Hour0vsHour12_gene_exp.diff", "Hour0vsHour16_gene_exp.diff", "Hour0vsHour24_gene_exp.diff", "Hour0vsHour48_gene_exp.diff"};  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\CufflinkOutput\\";
			int i = 0;
			for (String file: files) {
				map[i] = new HashMap();
								
				FileInputStream fstream = new FileInputStream(fileName + file);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split("\t").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");					
					String geneName = split[0].replaceAll("\"", "");	
					if (split[num].equals("yes")) {
						map[i].put(geneName, split[num - 1]);
					}
				}
				in.close();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static HashMap[] getCufflinkData() {
		HashMap[] map = new HashMap[7];
		try {
			String[] files = {"Hour0vsHour2_gene_exp.diff", "Hour0vsHour4_gene_exp.diff", "Hour0vsHour6_gene_exp.diff", "Hour0vsHour12_gene_exp.diff", "Hour0vsHour16_gene_exp.diff", "Hour0vsHour24_gene_exp.diff", "Hour0vsHour48_gene_exp.diff"};  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\CufflinkOutput\\";
			int i = 0;
			for (String file: files) {
				map[i] = new HashMap();
								
				FileInputStream fstream = new FileInputStream(fileName + file);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split("\t").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");					
					String geneName = split[0].replaceAll("\"", "");	
					map[i].put(geneName, split[9]);
				}
				in.close();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}	
	public static HashMap[] getEdgeRData() {
		HashMap[] map = new HashMap[7];
		try {
			String[] files = {"Hour0_vs_Hour2.txt", "Hour0_vs_Hour4.txt", "Hour0_vs_Hour6.txt", "Hour0_vs_Hour12.txt", "Hour0_vs_Hour16.txt", "Hour0_vs_Hour24.txt", "Hour0_vs_Hour48.txt"};  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\edgeROutput\\";
			int i = 0;
			for (String file: files) {
				map[i] = new HashMap();
								
				FileInputStream fstream = new FileInputStream(fileName + file);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split(" ").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split(" ");
					
					String geneName = split[0].replaceAll("\"", "");								
					map[i].put(geneName, split[1]);
					
				}
				in.close();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}	
	
	public static HashMap[] getEdgeROutput() {
		HashMap[] map = new HashMap[7];
		try {
			String[] files = {"Hour0_vs_Hour2.txt", "Hour0_vs_Hour4.txt", "Hour0_vs_Hour6.txt", "Hour0_vs_Hour12.txt", "Hour0_vs_Hour16.txt", "Hour0_vs_Hour24.txt", "Hour0_vs_Hour48.txt"};  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\edgeROutput\\";
			int i = 0;
			for (String file: files) {
				map[i] = new HashMap();
								
				FileInputStream fstream = new FileInputStream(fileName + file);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split(" ").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split(" ");					
					String geneName = split[0].replaceAll("\"", "");
					//System.out.println(fileName + file + "\t" + str);
					double fdr = new Double(split[num]);
					if (fdr < 0.05) {										
						map[i].put(geneName, split[1]);
					}
				}
				in.close();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static HashMap[] getDEseqOutputSig() {
		HashMap[] map = new HashMap[7];
		try {
			String[] files = {"Hour0vsHour2.txt", "Hour0vsHour4.txt", "Hour0vsHour6.txt", "Hour0vsHour12.txt", "Hour0vsHour16.txt", "Hour0vsHour24.txt", "Hour0vsHour48.txt"};  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\DESeqOutput\\";
			int i = 0;
			for (String file: files) {
				map[i] = new HashMap();
								
				FileInputStream fstream = new FileInputStream(fileName + file);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split("\t").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					
					String geneName = split[0].replaceAll("\"", "");
					//System.out.println(fileName + file + "\t" + str);
					if (!split[num].equals("NA")) {
					double fdr = new Double(split[num]);
						if (fdr < 0.05) {
											
							map[i].put(geneName, fdr);
						}
					}
				}
				in.close();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static HashMap[] getDEseqData() {
		HashMap[] map = new HashMap[7];
		try {
			String[] files = {"Hour0vsHour2.txt", "Hour0vsHour4.txt", "Hour0vsHour6.txt", "Hour0vsHour12.txt", "Hour0vsHour16.txt", "Hour0vsHour24.txt", "Hour0vsHour48.txt"};  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\DESeqOutput\\";
			int i = 0;
			for (String file: files) {
				map[i] = new HashMap();
								
				FileInputStream fstream = new FileInputStream(fileName + file);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split("\t").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");					
					String geneName = split[0].replaceAll("\"", "");
					//System.out.println(fileName + file + "\t" + str);
					
										
					map[i].put(geneName, split[5]);						
				
				}
				in.close();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static HashMap[] getBaySetOutputSig() {
		HashMap[] map = new HashMap[7];
		
		try {
			String[] files = {"bayseqHour0vsHour2.txt", "bayseqHour0vsHour4.txt", "bayseqHour0vsHour6.txt", "bayseqHour0vsHour12.txt", "bayseqHour0vsHour16.txt", "bayseqHour0vsHour24.txt", "bayseqHour0vsHour48.txt"};  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\bayseqOutput\\";
			int i = 0;
			for (String file: files) {
				map[i] = new HashMap();
				
				
				FileInputStream fstream = new FileInputStream(fileName + file);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split("\t").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					
					String geneName = split[0].replaceAll("\"", "");
					//System.out.println(fileName + file + "\t" + str);
					double fdr = new Double(split[num]);
					if (fdr < 0.05) {
						if (!geneName2geneID.containsKey(geneName)) {
							System.out.println("Problem in bayseq: " + geneName);
							System.exit(0);
						}
						String ensembl = (String)geneName2geneID.get(geneName);						
						map[i].put(ensembl, fdr);
					}
				}
				in.close();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
