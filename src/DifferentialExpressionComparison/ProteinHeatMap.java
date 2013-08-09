package DifferentialExpressionComparison;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import java.util.HashMap;

public class ProteinHeatMap {

	public static void main(String[] args) {		
		try {

			
			
			
			double max = Double.NEGATIVE_INFINITY;
			double min = Double.POSITIVE_INFINITY;
			
			String fileName2 = args[1];
			HashMap map = new HashMap();
		    FileInputStream fstream = new FileInputStream(fileName2);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				map.put(split[0], split[1]);
			}
			in.close();
			
			String fileName = args[0];				
		    fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				boolean foundNaN = false;
				
				try {
					for (int i = 1; i < split.length; i++) {					
						double num = new Double(split[i]);
					}
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
		
		
						
		    String goodFile = fileName + ".good";
		    String badFile = fileName + ".bad";
		    
		    FileWriter fwriter_good = new FileWriter(goodFile);
		    BufferedWriter out_good = new BufferedWriter(fwriter_good);
		    		    			    
		    FileWriter fwriter_bad = new FileWriter(badFile);
		    BufferedWriter out_bad = new BufferedWriter(fwriter_bad);	
		    
		    fstream = new FileInputStream(fileName);
			din = new DataInputStream(fstream); 
			in = new BufferedReader(new InputStreamReader(din));
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
					if (map.containsKey(split[0])) {
						out_good.write(map.get(split[0]) + "_" + str + "\n");
					}
				} else {
					out_bad.write(str + "\n");
				}
			}
			in.close();
			
			out_good.close();
			out_bad.close();
			
			String RScript = "library(pheatmap);\nallDat = read.table('" + goodFile + "', header = TRUE, row.names = 1);\npng('" + fileName + ".png');\n" +
					"print(pheatmap(allDat, cluster_col = FALSE, clustering_distance_rows='euclidean', clustering_method='ward',legend=TRUE,fontsize_row=2,fontsize_col=4" +
					", cellwidth=50, main = 'ncRNA DE Genes'));dev.off();"; 
			
			writeFile("Rscript.r", RScript);
			executeCommand("R --vanilla < Rscript.r");
			
		
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
