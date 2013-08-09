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

public class CompareExpressionTimeSeries {

	public static HashMap geneName2geneID = null;
	public static HashMap geneID2geneName = null;
	public static void main(String[] args) {
		
		try {
			
			String threemethod_vs_cufflink = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\ThreeMethod_Vs_Cufflink.txt";
		    FileWriter fwriter_threemethod_vs_cufflink = new FileWriter(threemethod_vs_cufflink);
		    BufferedWriter out_threemethod_vs_cufflink  = new BufferedWriter(fwriter_threemethod_vs_cufflink);
		    
		    String threemethodonly = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\ThreeMethodOnly.txt";
		    FileWriter fwriter_threemethodonly = new FileWriter(threemethodonly);
		    BufferedWriter out_threemethodonly = new BufferedWriter(fwriter_threemethodonly);
		    		    
		    String sig_ncRNA_threeMethod = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Significant_ncRNA_Three_GeneList.txt";
		    FileWriter fwriter_sig_ncRNA_three = new FileWriter(sig_ncRNA_threeMethod);
		    BufferedWriter out_sig_ncRNA_three = new BufferedWriter(fwriter_sig_ncRNA_three);		    
		    
		    String sig_protein_threeMethod = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Significant_protein_Three_GeneList.txt";
		    FileWriter fwriter_sig_protein_three = new FileWriter(sig_protein_threeMethod);
		    BufferedWriter out_sig_protein_three = new BufferedWriter(fwriter_sig_protein_three);
		    
		    String sig_ncRNA_CufflinkMethod = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Significant_ncRNA_Cufflink_GeneList.txt";
		    FileWriter fwriter_sig_ncRNA_Cufflink = new FileWriter(sig_ncRNA_CufflinkMethod);
		    BufferedWriter out_sig_ncRNA_Cufflink = new BufferedWriter(fwriter_sig_ncRNA_Cufflink);
		    		    
		    String sig_protein_CufflinkMethod = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Significant_protein_Cufflink_GeneList.txt";
		    FileWriter fwriter_sig_protein_Cufflink = new FileWriter(sig_protein_CufflinkMethod);
		    BufferedWriter out_sig_protein_Cufflink = new BufferedWriter(fwriter_sig_protein_Cufflink);
		    
		    String sig_ncRNA_MergeMethod = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Significant_ncRNA_merge_GeneList.txt";
		    FileWriter fwriter_sig_ncRNA_merge = new FileWriter(sig_ncRNA_MergeMethod);
		    BufferedWriter out_sig_ncRNA_merge = new BufferedWriter(fwriter_sig_ncRNA_merge);
		    		    
		    String sig_protein_MergeMethod = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Significant_protein_merge_GeneList.txt";
		    FileWriter fwriter_sig_protein_merge = new FileWriter(sig_protein_MergeMethod);
		    BufferedWriter out_sig_protein_merge = new BufferedWriter(fwriter_sig_protein_merge);

		   
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
			System.out.println();
			System.out.print("Three Method Merged");
			for (int i = 0; i < 7; i++) {
				int count = 0;
				Iterator itr = bayseq[i].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					if (edgeR[i].containsKey(key) && DEseq[i].containsKey(key)) {
						count++;
					}
				}
				System.out.print("\t" + count);
			}
			System.out.println();

			HashMap map_time = new HashMap();
			for (int i = 0; i < 7; i++) {
			    String maptime_file = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Super_Intersect_Genes\\ncRNA_MappedTime" + (i + 1) + ".txt";
			    FileWriter fwriter_maptime = new FileWriter(maptime_file);
			    BufferedWriter out_maptime = new BufferedWriter(fwriter_maptime);
				
				
				int count = 0;
				Iterator itr = bayseq[i].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					if (ncRNA.containsKey(key) && edgeR[i].containsKey(key) && DEseq[i].containsKey(key) && cufflink[i].containsKey(key)) {
						
						if (map_time.containsKey(key)) {
							int val = (Integer) map_time.get(key);
							val = val + 1;
							map_time.put(key,  val);
						} else {
							map_time.put(key,  1);
						}
						out_maptime.write(key + "\t" + ncRNA.get(key) + "\n");

					}
				}
				out_maptime.close();
				
				maptime_file = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Super_Intersect_Genes\\ncRNA_DE_AcrossAllTimepoint.txt";
			    fwriter_maptime = new FileWriter(maptime_file);
			    out_maptime = new BufferedWriter(fwriter_maptime);
				itr = map_time.keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					int val = (Integer) map_time.get(key);
					if (val == 7) {
						out_maptime.write(key + "\t" + ncRNA.get(key) + "\n");
					}
				}
				out_maptime.close();
				//System.out.print("\t" + count);
			}
			//System.out.println();
			for (int i = 0; i < 7; i++) {
				
			}
			//System.exit(0);
			
			System.out.print("Everything Merged");
			for (int i = 0; i < 7; i++) {
				int count = 0;
				Iterator itr = bayseq[i].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					if (edgeR[i].containsKey(key) && DEseq[i].containsKey(key) && cufflink[i].containsKey(key)) {
						count++;
					}
				}
				System.out.print("\t" + count);
			}
			System.out.println();
			
			String SignificantPathwayList = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\All_Method_Merged_KEGGPathway.txt";
		    FileWriter fwriter_sig_kegg = new FileWriter(SignificantPathwayList);
		    BufferedWriter out_sig_kegg  = new BufferedWriter(fwriter_sig_kegg);
			
			int countCoding = 0;
			int countncRNA = 0;
			int count = 0;
			Iterator itr = bayseq_all.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				if (edgeR_all.containsKey(key) && DEseq_all.containsKey(key) && cufflink_all.containsKey(key)) {
				//if (edgeR_all.containsKey(key) && DEseq_all.containsKey(key)) {
					count++;
					if (mapk.containsKey(key)) {
						out_sig_kegg.write("MAPK\t" + key + "\t" + Coding.get(key) + "\n");
					}
					if (notch.containsKey(key)) {
						out_sig_kegg.write("NOTCH\t" + key + "\t" + Coding.get(key) + "\n");					
					}
					if (cancer.containsKey(key)) {
						out_sig_kegg.write("Cancer Pathway\t" + key + "\t" + Coding.get(key) + "\n");					
					}
					if (tgf.containsKey(key)) {
						out_sig_kegg.write("TGF signaling Pathway\t" + key + "\t" + Coding.get(key) + "\n");
					}
					if (wnt.containsKey(key)) {
						out_sig_kegg.write("WNT Signaling Pathway\t" + key + "\t" + Coding.get(key) + "\n");
					}
					if (hedge.containsKey(key)) {
						out_sig_kegg.write("Hedgehog Signaling Pathway\t" + key + "\t" + Coding.get(key) + "\n");
					}
					
					if (Coding.containsKey(key)) {
						countCoding++;
						Coding_all.put(key, key);
					}
					if (ncRNA.containsKey(key)) {
						countncRNA++;
						ncRNA_all.put(key, key);
						System.out.println("ncRNA: " + key + "\t" + geneID2geneName.get(key));
					}
				}
			}
			
			
			System.out.println("Total Overlap: " + count);
			System.out.println("Coding Count: " + countCoding);
			System.out.println("ncRNA Count: " + countncRNA);


			String Three_SignificantPathwayList = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Three_Method_Merged_KEGGPathway.txt";
		    FileWriter fwriter_sig_kegg_three = new FileWriter(Three_SignificantPathwayList);
		    BufferedWriter out_sig_kegg_three  = new BufferedWriter(fwriter_sig_kegg_three);
			

			itr = bayseq_all.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				if (edgeR_all.containsKey(key) && DEseq_all.containsKey(key)) {

					if (mapk.containsKey(key)) {
						out_sig_kegg_three.write("MAPK\t" + key + "\t" + Coding.get(key) + "\n");
					}
					if (notch.containsKey(key)) {
						out_sig_kegg_three.write("NOTCH\t" + key + "\t" + Coding.get(key) + "\n");					
					}
					if (cancer.containsKey(key)) {
						out_sig_kegg_three.write("Cancer Pathway\t" + key + "\t" + Coding.get(key) + "\n");					
					}
					if (tgf.containsKey(key)) {
						out_sig_kegg_three.write("TGF signaling Pathway\t" + key + "\t" + Coding.get(key) + "\n");
					}
					if (wnt.containsKey(key)) {
						out_sig_kegg_three.write("WNT Signaling Pathway\t" + key + "\t" + Coding.get(key) + "\n");
					}
					if (hedge.containsKey(key)) {
						out_sig_kegg_three.write("Hedgehog Signaling Pathway\t" + key + "\t" + Coding.get(key) + "\n");
					}					
				}
			}

			String Cufflink_SignificantPathwayList = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cufflink_Method_KEGGPathway.txt";
		    FileWriter fwriter_sig_kegg_cufflink = new FileWriter(Cufflink_SignificantPathwayList);
		    BufferedWriter out_sig_kegg_cufflink  = new BufferedWriter(fwriter_sig_kegg_cufflink);
						
			itr = cufflink_coding.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();			
				if (mapk.containsKey(key)) {
					out_sig_kegg_cufflink.write("MAPK\t" + key + "\t" + Coding.get(key) + "\n");
				}
				if (notch.containsKey(key)) {
					out_sig_kegg_cufflink.write("NOTCH\t" + key + "\t" + Coding.get(key) + "\n");					
				}
				if (cancer.containsKey(key)) {
					out_sig_kegg_cufflink.write("Cancer Pathway\t" + key + "\t" + Coding.get(key) + "\n");					
				}
				if (tgf.containsKey(key)) {
					out_sig_kegg_cufflink.write("TGF signaling Pathway\t" + key + "\t" + Coding.get(key) + "\n");
				}
				if (wnt.containsKey(key)) {
					out_sig_kegg_cufflink.write("WNT Signaling Pathway\t" + key + "\t" + Coding.get(key) + "\n");
				}
				if (hedge.containsKey(key)) {
					out_sig_kegg_cufflink.write("Hedgehog Signaling Pathway\t" + key + "\t" + Coding.get(key) + "\n");
				}								
			}
		
		
			itr = bayseq_all.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				
				if (edgeR_all.containsKey(key) && DEseq_all.containsKey(key)) {
					count++;
		
					if (Coding.containsKey(key)) {
						three_coding.put(key, key);
					}
					if (ncRNA.containsKey(key)) {
						three_ncRNA.put(key, key);
					}
				}
			}

			itr = bayseq_all.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				if (edgeR_all.containsKey(key) && DEseq_all.containsKey(key) && cufflink_all.containsKey(key)) {
				//if (edgeR_all.containsKey(key) && DEseq_all.containsKey(key)) {
					count++;
		
					if (Coding.containsKey(key)) {
						merge_coding.put(key, key);
					}
					if (ncRNA.containsKey(key)) {
						merge_ncRNA.put(key, key);
					}
				}
			}
			
			
			//Generate Report from here
			HashMap three_vs_cufflink_coding = new HashMap();
			HashMap three_vs_cufflink_ncRNA = new HashMap();
			
			itr = three_coding.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				out_sig_protein_three.write(key + "\t" + Coding.get(key) + "\n");
			}
			
			itr = three_ncRNA.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				out_sig_ncRNA_three.write(key + "\t" + ncRNA.get(key) + "\n");
			}
			
			itr = merge_coding.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				out_sig_protein_merge.write(key + "\t" + Coding.get(key) + "\n");
			}
			
			itr = merge_ncRNA.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				out_sig_ncRNA_merge.write(key + "\t" + ncRNA.get(key) + "\n");
			}
			
			itr = cufflink_coding.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				out_sig_protein_Cufflink.write(key + "\t" + Coding.get(key) + "\n");
			}
			
			itr = cufflink_ncRNA.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				out_sig_ncRNA_Cufflink.write(key + "\t" + ncRNA.get(key) + "\n");
			}
			
			
			out_threemethod_vs_cufflink.write((three_coding.size() - merge_coding.size()) + "\t" + merge_coding.size() + "\t" + (cufflink_coding.size() - merge_coding.size()) + "\n"); 
			out_threemethod_vs_cufflink.write((three_ncRNA.size() - merge_ncRNA.size()) + "\t" + merge_ncRNA.size() + "\t" + (cufflink_ncRNA.size() - merge_ncRNA.size()) + "\n"); 
			out_threemethod_vs_cufflink.close();
			
			
			HashMap bayseq_vs_deseq_coding = intersectHashMap(bayseq_coding, DEseq_coding);
			HashMap deseq_vs_edgeR_coding = intersectHashMap(DEseq_coding, edgeR_coding);
			HashMap edgeR_vs_bayseq_coding = intersectHashMap(edgeR_coding, bayseq_coding);
			HashMap bayseq_vs_deseq_coding_filtered = removeSomeHashMap(bayseq_vs_deseq_coding, three_coding);
			HashMap deseq_vs_edgeR_coding_filtered = removeSomeHashMap(deseq_vs_edgeR_coding, three_coding);
			HashMap edgeR_vs_bayseq_coding_filtered = removeSomeHashMap(edgeR_vs_bayseq_coding, three_coding);
			
			out_threemethodonly.write(DEseq_coding.size() + "\t" + edgeR_coding.size() + "\t" + bayseq_coding.size() + "\t" + deseq_vs_edgeR_coding.size() +
					"\t" + edgeR_vs_bayseq_coding.size() + "\t" + bayseq_vs_deseq_coding.size() + "\t" + three_coding.size() + "\n");
			

			out_threemethodonly.write((DEseq_coding.size() - (bayseq_vs_deseq_coding.size() + deseq_vs_edgeR_coding.size() - three_coding.size())) + "\t" +
					deseq_vs_edgeR_coding_filtered.size() + "\t" + (edgeR_coding.size() - (deseq_vs_edgeR_coding.size() + edgeR_vs_bayseq_coding.size() - three_coding.size())) +
					"\t" + three_coding.size() + "\t" + bayseq_vs_deseq_coding_filtered.size() + "\t" + edgeR_vs_bayseq_coding_filtered.size() + "\t" +
					(bayseq_coding.size() - (bayseq_vs_deseq_coding.size() + edgeR_vs_bayseq_coding.size() - three_coding.size())) + "\n");

			HashMap bayseq_vs_deseq_ncRNA = intersectHashMap(bayseq_ncRNA, DEseq_ncRNA);
			HashMap deseq_vs_edgeR_ncRNA = intersectHashMap(DEseq_ncRNA, edgeR_ncRNA);
			HashMap edgeR_vs_bayseq_ncRNA = intersectHashMap(edgeR_ncRNA, bayseq_ncRNA);
			HashMap bayseq_vs_deseq_ncRNA_filtered = removeSomeHashMap(bayseq_vs_deseq_ncRNA, three_ncRNA);
			HashMap deseq_vs_edgeR_ncRNA_filtered = removeSomeHashMap(deseq_vs_edgeR_ncRNA, three_ncRNA);
			HashMap edgeR_vs_bayseq_ncRNA_filtered = removeSomeHashMap(edgeR_vs_bayseq_ncRNA, three_ncRNA);

			out_threemethodonly.write(DEseq_ncRNA.size() + "\t" + edgeR_ncRNA.size() + "\t" + bayseq_ncRNA.size() + "\t" + deseq_vs_edgeR_ncRNA.size() +
					"\t" + edgeR_vs_bayseq_ncRNA.size() + "\t" + bayseq_vs_deseq_ncRNA.size() + "\t" + three_ncRNA.size() + "\n");


			out_threemethodonly.write((DEseq_ncRNA.size() - (bayseq_vs_deseq_ncRNA.size() + deseq_vs_edgeR_ncRNA.size() - three_ncRNA.size())) + "\t" +
					deseq_vs_edgeR_ncRNA_filtered.size() + "\t" + (edgeR_ncRNA.size() - (deseq_vs_edgeR_ncRNA.size() + edgeR_vs_bayseq_ncRNA.size() - three_ncRNA.size())) +
					"\t" + three_ncRNA.size() + "\t" + bayseq_vs_deseq_ncRNA_filtered.size() + "\t" + edgeR_vs_bayseq_ncRNA_filtered.size() + "\t" +
					(bayseq_ncRNA.size() - (bayseq_vs_deseq_ncRNA.size() + edgeR_vs_bayseq_ncRNA.size() - three_ncRNA.size())) + "\n");


			out_threemethodonly.close();
			
			out_sig_ncRNA_three.close();
			out_sig_protein_three.close();
			out_sig_ncRNA_Cufflink.close();
			out_sig_protein_Cufflink.close();
			out_sig_ncRNA_merge.close();
			out_sig_protein_merge.close();

			out_sig_kegg.close();
			out_sig_kegg_three.close();
			out_sig_kegg_cufflink.close();
			
			
			// create KEGG pathway Files
			String[] KEGG = {"MAPK", "NOTCH", "Cancer Pathway", "TGF", "WNT", "Hedgehog"};
			
			HashMap[] map_list = new HashMap[6]; 
			map_list[0] = (mapk);
			map_list[1] = (notch);
			map_list[2] = (cancer);
			map_list[3] = (tgf);
			map_list[4] = (wnt);
			map_list[5] = (hedge);
			
			HashMap edgeR_complete_data = new HashMap();
			HashMap deseq_complete_data = new HashMap();
			HashMap cufflink_complete_data = new HashMap();
			
			// grabbing data
			for (int j = 0; j < 7; j++) {
				itr = edgeR_data[j].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					String fc = (String)edgeR_data[j].get(key);
					if (edgeR_complete_data.containsKey(key)) {
						String values = (String)edgeR_complete_data.get(key);
						values = values + "\t" + fc;
						edgeR_complete_data.put(key, values);
					} else {
						edgeR_complete_data.put(key, fc + "");
					}
				}
				itr = DEseq_data[j].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					String fc = (String)DEseq_data[j].get(key);
					if (deseq_complete_data.containsKey(key)) {
						String values = (String)deseq_complete_data.get(key);
						values = values + "\t" + fc;
						deseq_complete_data.put(key, values);
					} else {
						deseq_complete_data.put(key, fc + "");
					}
				}
				itr = cufflink_data[j].keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();
					String fc = (String)cufflink_data[j].get(key);
					if (cufflink_complete_data.containsKey(key)) {
						String values = (String)cufflink_complete_data.get(key);
						values = values + "\t" + fc;
						cufflink_complete_data.put(key, values);
					} else {
						cufflink_complete_data.put(key, fc + "");
					}
				}				
			}
			
			for (int i = 0; i < KEGG.length; i++) {
									
			    String fc_KEGG_cufflink = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cufflink_FoldChange_" + KEGG[i] + ".txt";
			    FileWriter fwriter_fc_cufflink = new FileWriter(fc_KEGG_cufflink);
			    BufferedWriter out_fc_cufflink = new BufferedWriter(fwriter_fc_cufflink);					

			    String fc_KEGG_deseq = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\DESeq_FoldChange_" + KEGG[i] + ".txt";
			    FileWriter fwriter_fc_deseq = new FileWriter(fc_KEGG_deseq);
			    BufferedWriter out_fc_deseq = new BufferedWriter(fwriter_fc_deseq);
			    
				Iterator itr2 = map_list[i].keySet().iterator();
				while(itr2.hasNext()) {
					String key = (String)itr2.next();
					String geneName = (String)geneID2geneName.get(key);
					if (cufflink_complete_data.containsKey(key)) {						
						out_fc_cufflink.write(geneName + "\t" + cufflink_complete_data.get(key) + "\n");
					}
					if (deseq_complete_data.containsKey(key)) {
						out_fc_deseq.write(geneName + "\t" + deseq_complete_data.get(key) + "\n");
					}

				}
				out_fc_cufflink.close();
				out_fc_deseq.close();

			}
			
		    String fc_protein_cufflink = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cufflink_FoldChange_Protein.txt";
		    FileWriter fwriter_fc_cufflink_protein = new FileWriter(fc_protein_cufflink);
		    BufferedWriter out_fc_cufflink_protein = new BufferedWriter(fwriter_fc_cufflink_protein);

		    String fc_ncRNA_cufflink = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\Cufflink_FoldChange_ncRNA.txt";
		    FileWriter fwriter_fc_cufflink_ncRNA = new FileWriter(fc_ncRNA_cufflink);
		    BufferedWriter out_fc_cufflink_ncRNA = new BufferedWriter(fwriter_fc_cufflink_ncRNA);
		    
		    String fc_protein_DEseq = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\DESeq_FoldChange_Protein.txt";
		    FileWriter fwriter_fc_DEseq_protein = new FileWriter(fc_protein_DEseq);
		    BufferedWriter out_fc_DEseq_protein = new BufferedWriter(fwriter_fc_DEseq_protein);

		    String fc_ncRNA_DEseq = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\DESeq_FoldChange_ncRNA.txt";
		    FileWriter fwriter_fc_DEseq_ncRNA = new FileWriter(fc_ncRNA_DEseq);
		    BufferedWriter out_fc_DEseq_ncRNA = new BufferedWriter(fwriter_fc_DEseq_ncRNA);
		    	
			Iterator itr2 = Coding.keySet().iterator();
			while(itr2.hasNext()) {
				String key = (String)itr2.next();
				if (cufflink_complete_data.containsKey(key)) {
					out_fc_cufflink_protein.write(key + "\t" + cufflink_complete_data.get(key) + "\n");
				}
				if (deseq_complete_data.containsKey(key)) {
					out_fc_DEseq_protein.write(key + "\t" + deseq_complete_data.get(key) + "\n");
				}
			}		    
		    
			itr2 = ncRNA.keySet().iterator();
			while(itr2.hasNext()) {
				String key = (String)itr2.next();
				if (cufflink_complete_data.containsKey(key)) {
					out_fc_cufflink_ncRNA.write(key + "\t" + cufflink_complete_data.get(key) + "\n");
				}
				if (deseq_complete_data.containsKey(key)) {
					out_fc_DEseq_ncRNA.write(key + "\t" + deseq_complete_data.get(key) + "\n");
				}
			}	
			out_fc_cufflink_protein.close();
			out_fc_cufflink_ncRNA.close();
			out_fc_DEseq_protein.close();
			out_fc_DEseq_ncRNA.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
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
