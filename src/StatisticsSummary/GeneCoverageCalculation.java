package StatisticsSummary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class GeneCoverageCalculation {

	public static void main(String[] args) {
		
		String[] ayads = {"AYAD21", "AYAD22", "AYAD23", "AYAD24", "AYAD25", "AYAD26", "AYAD28", "AYAD36", "AYAD37", "AYAD38",
				"AYAD39", "AYAD40", "AYAD41", "AYAD42", "AYAD43", "AYAD44", "AYAD45", "AYAD46", "AYAD47", "AYAD49"};
		
		for (String ayad: ayads) {
			String script = "x = read.csv(\"" + ayad + "_NoNovelJuncs_GeneCoverage.txt\", sep = \"\\t\");\n" + 
					"png(\"GeneCoverage_" + ayad + ".png\");\n" + 
					"hist(x[,2], main=\"Gene Coverage Histogram for " + ayad + "\", xlab = \"Gene Coverage %\", ylab = \"# of Genes\");\n" + 
					"dev.off();";
			
			writeFile("script.R", script);
			executeCommand("R --vanilla < script.R");
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
