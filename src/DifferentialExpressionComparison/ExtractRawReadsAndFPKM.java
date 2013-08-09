package DifferentialExpressionComparison;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class ExtractRawReadsAndFPKM {

	public static void main(String[] args) {
		
		try {
			HashMap codingSet = getDifferentialExpressedGene("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\RawReadCount\\Significant_protein_Comprehensive_Overlap_GeneList.txt");
			HashMap noncodingSet = getDifferentialExpressedGene("C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\RawReadCount\\Significant_ncRNA_Comprehensive_Overlap_GeneList.txt");
		
			HashMap[] cufflink = getCufflinkOutput();
			HashMap[] rawreads = getTopHatOutput();
			HashMap[] geneLength = getTopHatOutputGeneLength();
			String outputCoding = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\RawReadCount\\CodingValues_Cufflink.txt";
		    FileWriter fwriter_coding = new FileWriter(outputCoding);
		    BufferedWriter out_coding  = new BufferedWriter(fwriter_coding);
		    
		    String outputNonCoding = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\RawReadCount\\NonCodingValues_Cufflink.txt";
		    FileWriter fwriter_noncoding = new FileWriter(outputNonCoding);
		    BufferedWriter out_noncoding  = new BufferedWriter(fwriter_noncoding);
		    
		    
		    String outputCoding_Read = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\RawReadCount\\CodingValues_BEDtools.txt";
		    FileWriter fwriter_coding_Read = new FileWriter(outputCoding_Read);
		    BufferedWriter out_coding_Read  = new BufferedWriter(fwriter_coding_Read);
		    
		    String outputNonCoding_Read = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\RawReadCount\\NonCodingValues_BEDtools.txt";
		    FileWriter fwriter_noncoding_Read = new FileWriter(outputNonCoding_Read);
		    BufferedWriter out_noncoding_Read  = new BufferedWriter(fwriter_noncoding_Read);

		    
		    String outputCoding_Length = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\RawReadCount\\CodingValues_BEDtools_GeneLength.txt";
		    FileWriter fwriter_coding_Length = new FileWriter(outputCoding_Length);
		    BufferedWriter out_coding_Length  = new BufferedWriter(fwriter_coding_Length);
		    
		    String outputNonCoding_Length = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\Final Report Results\\RawReadCount\\NonCodingValues_BEDtools_GeneLength.txt";
		    FileWriter fwriter_noncoding_Length = new FileWriter(outputNonCoding_Length);
		    BufferedWriter out_noncoding_Length  = new BufferedWriter(fwriter_noncoding_Length);

		    out_coding.write("GeneName\tHour0\tHour2\tHour4\tHour6\tHour12\tHour16\tDay1\tDay2\n");
		    out_noncoding.write("GeneName\tHour0\tHour2\tHour4\tHour6\tHour12\tHour16\tDay1\tDay2\n");
		    
		    out_coding_Read.write("GeneName\tHour0_AYAD21\tHour0_AYAD38\tHour0_AYAD39\tHour2_AYAD22\tHour2_AYAD40\tHour2_AYAD41\tHour4_AYAD23\t" + 
		    		"Hour6_AYAD24\tHour6_AYAD42\tHour6_AYAD43\tHour12_AYAD25\tHour12_AYAD44\tHour12_AYAD45\tHour16_AYAD26\tHour16_AYAD46\tHour_16_AYAD47\t" + 
		    		"Day1_AYAD36\tDay1_AYAD37\tDay2_AYAD28\tDay2_AYAD49\n");
		    
		    out_noncoding_Read.write("GeneName\tHour0_AYAD21\tHour0_AYAD38\tHour0_AYAD39\tHour2_AYAD22\tHour2_AYAD40\tHour2_AYAD41\tHour4_AYAD23\t" + 
		    		"Hour6_AYAD24\tHour6_AYAD42\tHour6_AYAD43\tHour12_AYAD25\tHour12_AYAD44\tHour12_AYAD45\tHour16_AYAD26\tHour16_AYAD46\tHour_16_AYAD47\t" + 
		    		"Day1_AYAD36\tDay1_AYAD37\tDay2_AYAD28\tDay2_AYAD49\n");
		    out_coding_Length.write("GeneName\tHour0_AYAD21\tHour0_AYAD38\tHour0_AYAD39\tHour2_AYAD22\tHour2_AYAD40\tHour2_AYAD41\tHour4_AYAD23\t" + 
		    		"Hour6_AYAD24\tHour6_AYAD42\tHour6_AYAD43\tHour12_AYAD25\tHour12_AYAD44\tHour12_AYAD45\tHour16_AYAD26\tHour16_AYAD46\tHour_16_AYAD47\t" + 
		    		"Day1_AYAD36\tDay1_AYAD37\tDay2_AYAD28\tDay2_AYAD49\n");
		    
		    out_noncoding_Length.write("GeneName\tHour0_AYAD21\tHour0_AYAD38\tHour0_AYAD39\tHour2_AYAD22\tHour2_AYAD40\tHour2_AYAD41\tHour4_AYAD23\t" + 
		    		"Hour6_AYAD24\tHour6_AYAD42\tHour6_AYAD43\tHour12_AYAD25\tHour12_AYAD44\tHour12_AYAD45\tHour16_AYAD26\tHour16_AYAD46\tHour_16_AYAD47\t" + 
		    		"Day1_AYAD36\tDay1_AYAD37\tDay2_AYAD28\tDay2_AYAD49\n");
		    
		    Iterator itr = codingSet.keySet().iterator();
		    while (itr.hasNext()) {
		    	String geneName = (String)itr.next();
		    	out_coding.write(geneName);
		    	for (int i = 0; i < cufflink.length; i++) {
		    		String value = (String)cufflink[i].get(geneName);
		    		out_coding.write("\t" + value);
		    	}
		    	out_coding.write("\n");
		    	out_coding_Read.write(geneName);
		    	for (int i = 0; i < rawreads.length; i++) {
		    		String value = (String)rawreads[i].get(geneName);
		    		out_coding_Read.write("\t" + value);
		    	}
		    	out_coding_Read.write("\n");
		    	
		    	out_coding_Length.write(geneName);
		    	for (int i = 0; i < geneLength.length; i++) {
		    		String value = (String)geneLength[i].get(geneName);
		    		out_coding_Length.write("\t" + value);
		    	}
		    	out_coding_Length.write("\n");
		    }
		    out_coding.close();
		    out_coding_Read.close();
		    out_coding_Length.close();
		    
		    itr = noncodingSet.keySet().iterator();
		    while (itr.hasNext()) {
		    	String geneName = (String)itr.next();
		    	out_noncoding.write(geneName);
		    	for (int i = 0; i < cufflink.length; i++) {
		    		String value = (String)cufflink[i].get(geneName);
		    		out_noncoding.write("\t" + value);
		    	}
		    	out_noncoding.write("\n");
		    	out_noncoding_Read.write(geneName);
		    	for (int i = 0; i < rawreads.length; i++) {
		    		String value = (String)rawreads[i].get(geneName);
		    		out_noncoding_Read.write("\t" + value);
		    	}
		    	out_noncoding_Read.write("\n");
		    	
		    	out_noncoding_Length.write(geneName);
		    	for (int i = 0; i < geneLength.length; i++) {
		    		String value = (String)geneLength[i].get(geneName);
		    		out_noncoding_Length.write("\t" + value);
		    	}
		    	out_noncoding_Length.write("\n");
		    }
		    out_noncoding.close();
		    out_noncoding_Read.close();
		    out_noncoding_Length.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap getDifferentialExpressedGene(String fileName) {
		HashMap map = new HashMap();
		try {
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[0], split[0]);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static HashMap[] getCufflinkOutput() {
		HashMap[] map = new HashMap[8];
		try {
			String[] files = {"Hour0vsHour2_gene_exp.diff", "Hour0vsHour4_gene_exp.diff", "Hour0vsHour6_gene_exp.diff", "Hour0vsHour12_gene_exp.diff", "Hour0vsHour16_gene_exp.diff", "Hour0vsHour24_gene_exp.diff", "Hour0vsHour48_gene_exp.diff"};  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\CufflinkOutput\\";
			int i = 0;
			for (String file: files) {
				
				if (i == 0) {
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
						map[i].put(geneName, split[7]);
					}
					in.close();
				}
				map[i + 1] = new HashMap();
								
				FileInputStream fstream = new FileInputStream(fileName + file);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split("\t").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");					
					String geneName = split[0].replaceAll("\"", "");	
					map[i + 1].put(geneName, split[8]);
				}
				in.close();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static HashMap[] getTopHatOutput() {
		String [] files = {"AYAD21_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD38_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD39_NoNovelJuncs_CoverageBED_Sort_Uniq", 
				"AYAD22_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD40_NoNovelJuncs_CoverageBED_Sort_Uniq","AYAD41_NoNovelJuncs_CoverageBED_Sort_Uniq", 
				"AYAD23_NoNovelJuncs_CoverageBED_Sort_Uniq", 
				"AYAD24_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD42_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD43_NoNovelJuncs_CoverageBED_Sort_Uniq",   
				"AYAD25_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD44_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD45_NoNovelJuncs_CoverageBED_Sort_Uniq",  
				"AYAD26_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD46_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD47_NoNovelJuncs_CoverageBED_Sort_Uniq",
				"AYAD36_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD37_NoNovelJuncs_CoverageBED_Sort_Uniq", 
				"AYAD28_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD49_NoNovelJuncs_CoverageBED_Sort_Uniq"
				
		};
				
		HashMap[] map = new HashMap[files.length];
		try {
			  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\TopHatOutput\\";
			int i = 0;
			for (String file: files) {
				map[i] = new HashMap();
								
				FileInputStream fstream = new FileInputStream(fileName + file + ".txt");
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split("\t").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");										
					String geneName = split[3].replaceAll("\"", "");	
					map[i].put(geneName, split[4]);					
				}
				in.close();
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static HashMap[] getTopHatOutputGeneLength() {
		String [] files = {"AYAD21_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD38_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD39_NoNovelJuncs_CoverageBED_Sort_Uniq", 
				"AYAD22_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD40_NoNovelJuncs_CoverageBED_Sort_Uniq","AYAD41_NoNovelJuncs_CoverageBED_Sort_Uniq", 
				"AYAD23_NoNovelJuncs_CoverageBED_Sort_Uniq", 
				"AYAD24_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD42_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD43_NoNovelJuncs_CoverageBED_Sort_Uniq",   
				"AYAD25_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD44_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD45_NoNovelJuncs_CoverageBED_Sort_Uniq",  
				"AYAD26_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD46_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD47_NoNovelJuncs_CoverageBED_Sort_Uniq",
				"AYAD36_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD37_NoNovelJuncs_CoverageBED_Sort_Uniq", 
				"AYAD28_NoNovelJuncs_CoverageBED_Sort_Uniq", "AYAD49_NoNovelJuncs_CoverageBED_Sort_Uniq",
				
		};
				
		HashMap[] map = new HashMap[files.length];
		try {
			  
			String fileName = "C:\\School Notes\\RNAseqCancer\\Final_DE_AnalysisPipeline\\TopHatOutput\\";
			int i = 0;
			for (String file: files) {
				map[i] = new HashMap();
								
				FileInputStream fstream = new FileInputStream(fileName + file + ".txt");
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				String stuff = in.readLine();
				int num = stuff.split("\t").length - 1;
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");										
					String geneName = split[3].replaceAll("\"", "");	
					map[i].put(geneName, split[6]);					
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
