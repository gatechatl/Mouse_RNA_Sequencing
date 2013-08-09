package DifferentialExpressionComparison;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class FilterFoldChangeCreateHeatMap {

	public static void main(String[] args) {		
		try {

			String type = "Cufflink";
			if (args[0].equals("DESeq")) {
				type = "DESeq";
			}
			type = type + "_";
			
			
			String[] files = {"FoldChange_Cancer_Pathway.txt", "FoldChange_Hedgehog.txt", "FoldChange_MAPK.txt",
					"FoldChange_NOTCH.txt", "FoldChange_TGF.txt", "FoldChange_WNT.txt"};
			
			double max = Double.NEGATIVE_INFINITY;
			double min = Double.POSITIVE_INFINITY;
			for (int k = 0; k < files.length; k++) {
				String fileName = type + files[k];				
			    FileInputStream fstream = new FileInputStream(fileName);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					boolean foundNaN = false;
					
					try {
						for (int i = 1; i < split.length; i++) {					
								double num = new Double(split[i]);
								if (!(num >= 1e300 || num <= -1e300)) {
									if (max < num) {
										max = num;
									}
									if (min > num) {
										min = num;
									}
								}
						}
					} catch (Exception e) {
						foundNaN = true;
					}
					
				}
				in.close();
			}
			for (int k = 0; k < files.length; k++) {
				String fileName = type + files[k];				
			    String goodFile = type + files[k] + ".good";
			    String badFile = type + files[k] + ".bad";
			    
			    FileWriter fwriter_good = new FileWriter(goodFile);
			    BufferedWriter out_good = new BufferedWriter(fwriter_good);
			    		    			    
			    FileWriter fwriter_bad = new FileWriter(badFile);
			    BufferedWriter out_bad = new BufferedWriter(fwriter_bad);	
			    
			    FileInputStream fstream = new FileInputStream(fileName);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					boolean foundNaN = false;
					try {
						for (int i = 1; i < split.length; i++) {					
								double num = new Double(split[i]);
								if (num >= 1e300 || num <= -1e300) {
									foundNaN = true;
								}
						}
					} catch (Exception e) {
						foundNaN = true;
					}
					if (!foundNaN) {
						out_good.write(str + "\n");
					} else {
						out_bad.write(str + "\n");
					}
				}
				in.close();
				out_good.write("REFERENCE_MIN");
				for (int l = 0; l < 7; l++) {
					out_good.write("\t" + min);
				}
				out_good.write("\n");
				
				out_good.write("REFERENCE_MAZ");
				for (int l = 0; l < 7; l++) {
					out_good.write("\t" + max);
				}
				out_good.write("\n");
				
				out_good.close();
				out_bad.close();
				
				String RScript = "library(pheatmap);\nallDat = read.table('" + goodFile + "', header = FALSE, row.names = 1);\npng('" + fileName + ".png');\n" +
						"print(pheatmap(allDat, cluster_col = FALSE, clustering_distance_rows='euclidean', clustering_method='war',legend=TRUE,fontsize_row=2,fontsize_col=4" +
						", cellwidth=50, main = '" + files[k].replaceAll(".txt", "") + "'));dev.off;"; 
				
				writeFile("Rscript.r", RScript);
				executeCommand("R --vanilla < Rscript.r");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void executeCommand(String executeThis) {
		try {
			writeFile("tempexecuteCommand.sh", executeThis);
	        String[] command = {"sh", "tempexecuteCommand.sh"};
	        Process p1 = Runtime.getRuntime().exec(command);		        
            BufferedReader inputn = new BufferedReader(new InputStreamReader(p1.getInputStream()));            
            String line=null;
            while((line=inputn.readLine()) != null) {}                        
            inputn.close();
             
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void writeFile(String fileName, String command) {
		try {
		    FileWriter fwriter2 = new FileWriter(fileName);
		    BufferedWriter out2 = new BufferedWriter(fwriter2);
		    out2.write(command + "\n");		    		
		    out2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
