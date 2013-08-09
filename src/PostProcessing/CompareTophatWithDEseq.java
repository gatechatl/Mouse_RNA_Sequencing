package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class CompareTophatWithDEseq {

	
	public static void main(String[] args) {
		HashMap metadata = grabEnsembl2MetaData("C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\Cufflink_FPKM_LogRatio_result.output");
		try {
			
			String TophatfileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Significant Differential Expression\\Cufflink_significant_differential_expression_result.output";
			String withDEseqFileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Significant Differential Expression\\DEseq_significant_differential_expression_result.output";
			
			String TopHatLogRatioFileName = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\Cufflink_FPKM_LogRatio_result.output";
			
			/* merge time point*/
			HashMap[] mapDEseq = new HashMap[7];
			HashMap[] totalMerge = new HashMap[7];
			HashMap[] overlapMerge = new HashMap[7];
			HashMap[] tophatTotal = new HashMap[7];
			
			HashMap totalMergeResult = new HashMap();
			
			HashMap tophatResult = new HashMap();
			for (int i = 2; i <= 8; i++) {
				totalMerge[i - 2] = new HashMap();
				overlapMerge[i - 2] = new HashMap();
				tophatTotal[i - 2] = new HashMap();
				mapDEseq[i - 2] = grabDEseqResult(i, withDEseqFileName);
			}
			FileInputStream fstream3 = new FileInputStream(TopHatLogRatioFileName);
			DataInputStream din3 = new DataInputStream(fstream3);
			BufferedReader in3 = new BufferedReader(new InputStreamReader(din3));
			while (in3.ready()) {
				String str = in3.readLine();
				String[] split = str.split("\t");
				tophatResult.put(split[1], str);
				
			}
			in3.close();
			FileInputStream fstream = new FileInputStream(TophatfileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String geneName = split[1];
				
				String location = split[2];
				String ensembl = split[3];
				String biotype = split[4];
				for (int j = 5; j < split.length; j++) {
					if (split[j].contains("yes")) {
						if (mapDEseq[j - 5].containsKey(ensembl)) {
							overlapMerge[j - 5].put(geneName + "\t" + location + "\t" + ensembl + "\t" + biotype, geneName + "\t" + location + "\t" + ensembl + "\t" + biotype);							
						}
						totalMerge[j - 5].put(geneName + "\t" + location + "\t" + ensembl + "\t" + biotype, geneName + "\t" + location + "\t" + ensembl + "\t" + biotype);
						tophatTotal[j - 5].put(geneName + "\t" + location + "\t" + ensembl + "\t" + biotype, geneName + "\t" + location + "\t" + ensembl + "\t" + biotype);
						totalMergeResult.put(geneName, geneName);
					}
				}
					
			}
			in.close();

			System.out.println(totalMergeResult.size());
			fstream = new FileInputStream(withDEseqFileName);
			din = new DataInputStream(fstream);
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				if (!split[0].equals("id")) {
					int id = new Integer(split[1]);
					String key = (String)metadata.get(split[0]);
					totalMerge[id - 2].put(key, key);
					if (key != null) {
						//System.out.println(key);
						totalMergeResult.put(key.split("\t")[0], key.split("\t")[0]);
					}
					
				}
				
			}
			in.close();
			
			for (int i = 0; i < 7; i++) {
				System.out.print(tophatTotal[i].size() + "\t");
			}
			System.out.println();
			for (int i = 0; i < 7; i++) {
				System.out.print(mapDEseq[i].size() + "\t");
			}
			System.out.println();
			HashMap overlap = new HashMap();
			for (int i = 0; i < 7; i++) {
				System.out.print(overlapMerge[i].size() + "\t");
				if (i >= 0) {
					Iterator itr = overlapMerge[i].keySet().iterator();
					while (itr.hasNext()) {
						String key = (String)itr.next();
						
						overlap.put(key, key);
					}
				}
			}
			
			/*for (int i = 3; i < 7; i++) {
				if (i != 5 && i != 5) {
				HashMap newOverlap = new HashMap();
				Iterator itr = overlap.keySet().iterator();
				while (itr.hasNext()) {
					String key = (String)itr.next();					
					if (overlapMerge[i].containsKey(key)) {
						newOverlap.put(key, key);
					}
				}			
				overlap = newOverlap;
				}
			}*/
			
			
			System.out.println();
			for (int i = 0; i < 7; i++) {
				System.out.print(totalMerge[i].size() + "\t");
			}
			System.out.println();
			
			System.out.println(overlap.size());
			/*Iterator itr = overlap.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();					
				String[] split = key.split("\t");
				String result = (String)tophatResult.get(split[0]);
				System.out.println(result);
			}			*/
			
			
			String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Final Report\\Differential Expression\\Cufflink_DESeq_Merge_Differential_Expressed_Gene.txt";
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
			Iterator itr = totalMergeResult.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();		
				//System.out.println(key);
				String[] split = key.split("\t");
				String result = (String)tophatResult.get(split[0]);
				//System.out.println(result);
				out.write(result + "\n");
			}			
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static HashMap grabDEseqResult(int num, String fileName) {
		HashMap map = new HashMap();
		try {

			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				String index = (new Integer(num)).toString();
				if (split[1].equals(index)) {
					
					map.put(split[0], split[0]);
				}				
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return map;
	}
	public static HashMap grabEnsembl2GeneName (String fileName) {
		HashMap map = new HashMap();
		try {

			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[3], split[1]);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}
	public static HashMap grabEnsembl2MetaData (String fileName) {
		HashMap map = new HashMap();
		try {

			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream);
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[3], split[1] + "\t" + split[2] + "\t" + split[3] + "\t" + split[4]);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}
}
