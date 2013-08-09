import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;


public class CreateUCSCBrowserWiggleTrack {
    public static void main(String[] args) {
    	
    	try {
    		String fileName = args[0];    	
    		String outputFile = args[1];    		
    		String outputFile2 = args[2];
    		int count = 0;
    								  
    		int[] chr1Array = new int[25000000];
    		int[] chr2Array = new int[25000000];
    		int[] chr3Array = new int[25000000];
    		int[] chr4Array = new int[25000000];
    		int[] chr5Array = new int[25000000];
    		int[] chr6Array = new int[25000000];
    		int[] chr7Array = new int[25000000];
    		int[] chr8Array = new int[25000000];
    		int[] chr9Array = new int[25000000];
    		int[] chr10Array = new int[25000000];
    		int[] chr11Array = new int[25000000];
    		int[] chr12Array = new int[25000000];
    		int[] chr13Array = new int[25000000];
    		int[] chr14Array = new int[25000000];
    		int[] chr15Array = new int[25000000];
    		int[] chr16Array = new int[25000000];
    		int[] chr17Array = new int[25000000];
    		int[] chr18Array = new int[25000000];
    		int[] chr19Array = new int[25000000];
    		int[] chrMArray = new int[25000000];
    		int[] chrXArray = new int[250000000];
    		int[] chrYArray = new int[250000000];
    		
    		int[] chr1Array_Rev = new int[25000000];
    		int[] chr2Array_Rev = new int[25000000];
    		int[] chr3Array_Rev = new int[25000000];
    		int[] chr4Array_Rev = new int[25000000];
    		int[] chr5Array_Rev = new int[25000000];
    		int[] chr6Array_Rev = new int[25000000];
    		int[] chr7Array_Rev = new int[25000000];
    		int[] chr8Array_Rev = new int[25000000];
    		int[] chr9Array_Rev = new int[25000000];
    		int[] chr10Array_Rev = new int[25000000];
    		int[] chr11Array_Rev = new int[25000000];
    		int[] chr12Array_Rev = new int[25000000];
    		int[] chr13Array_Rev = new int[25000000];
    		int[] chr14Array_Rev = new int[25000000];
    		int[] chr15Array_Rev = new int[25000000];
    		int[] chr16Array_Rev = new int[25000000];
    		int[] chr17Array_Rev = new int[25000000];
    		int[] chr18Array_Rev = new int[25000000];
    		int[] chr19Array_Rev = new int[25000000];
    		int[] chrMArray_Rev = new int[25000000];
    		int[] chrXArray_Rev = new int[250000000];
    		int[] chrYArray_Rev = new int[250000000];
    		


    		for (int i = 0; i < 25000000; i++) {
    			chr1Array[i] = 0;
    			chr2Array[i] = 0;
    			chr3Array[i] = 0;
    			chr4Array[i] = 0;
    			chr5Array[i] = 0;
    			chr6Array[i] = 0;
    			chr7Array[i] = 0;
    			chr8Array[i] = 0;
    			chr9Array[i] = 0;
    			chr10Array[i] = 0;
    			chr11Array[i] = 0;
    			chr12Array[i] = 0;
    			chr13Array[i] = 0;
    			chr14Array[i] = 0;
    			chr15Array[i] = 0;
    			chr16Array[i] = 0;
    			chr17Array[i] = 0;
    			chr18Array[i] = 0;
    			chr19Array[i] = 0;
    			chrMArray[i] = 0;
    			chrXArray[i] = 0;
    			chrYArray[i] = 0;
    			chr1Array_Rev[i] = 0;
    			chr2Array_Rev[i] = 0;
    			chr3Array_Rev[i] = 0;
    			chr4Array_Rev[i] = 0;
    			chr5Array_Rev[i] = 0;
    			chr6Array_Rev[i] = 0;
    			chr7Array_Rev[i] = 0;
    			chr8Array_Rev[i] = 0;
    			chr9Array_Rev[i] = 0;
    			chr10Array_Rev[i] = 0;
    			chr11Array_Rev[i] = 0;
    			chr12Array_Rev[i] = 0;
    			chr13Array_Rev[i] = 0;
    			chr14Array_Rev[i] = 0;
    			chr15Array_Rev[i] = 0;
    			chr16Array_Rev[i] = 0;
    			chr17Array_Rev[i] = 0;
    			chr18Array_Rev[i] = 0;
    			chr19Array_Rev[i] = 0;
    			chrMArray_Rev[i] = 0;
    			chrXArray_Rev[i] = 0;
    			chrYArray_Rev[i] = 0;


    		}
    		
		    FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));						
			while (in.ready()) {
				String str = in.readLine();
				String[] split = str.split("\t");
				int length = new Integer(split[5]);
				String direction = split[6];
				String chromosome = split[7];
				int location = new Integer(split[8]);
				String name = split[0];
                boolean reverse = false;
                if (name.contains("/2")) {                    
                    reverse = true;
                }
                if (direction.equals("+") && reverse) {
                    direction = "-";
                } else if (direction.equals("-") && reverse) {
                    direction = "+";
                }
				
				if (chromosome.equals("chr1") && direction.equals("+")) {
					int index = location / 10;						
					chr1Array[index]++;
					
				}
				if (chromosome.equals("chr1") && direction.equals("-")) {					
					int index = location / 10;						
					chr1Array_Rev[index]++;					
				}
				if (chromosome.equals("chr2") && direction.equals("+")) {
					int index = location / 10;						
					chr2Array[index]++;
					
				}
				if (chromosome.equals("chr2") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr2Array_Rev[index]++;					
				}
				if (chromosome.equals("chr3") && direction.equals("+")) {
					int index = location / 10;						
					chr3Array[index]++;
					
				}
				if (chromosome.equals("chr3") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr3Array_Rev[index]++;					
				}
				if (chromosome.equals("chr4") && direction.equals("+")) {
					int index = location / 10;						
					chr4Array[index]++;
					
				}
				if (chromosome.equals("chr4") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr4Array_Rev[index]++;					
				}
				if (chromosome.equals("chr5") && direction.equals("+")) {
					int index = location / 10;						
					chr5Array[index]++;
					
				}
				if (chromosome.equals("chr5") && direction.equals("-")) {					
					int index = (location - 100) / 10;						
					chr5Array_Rev[index]++;					
				}
				if (chromosome.equals("chr6") && direction.equals("+")) {
					int index = location / 10;						
					chr6Array[index]++;
					
				}
				if (chromosome.equals("chr6") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr6Array_Rev[index]++;					
				}
				if (chromosome.equals("chr7") && direction.equals("+")) {
					int index = location / 10;						
					chr7Array[index]++;
					
				}
				if (chromosome.equals("chr7") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr7Array_Rev[index]++;					
				}
				if (chromosome.equals("chr8") && direction.equals("+")) {
					int index = location / 10;						
					chr8Array[index]++;
					
				}
				if (chromosome.equals("chr8") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr8Array_Rev[index]++;					
				}
				if (chromosome.equals("chr9") && direction.equals("+")) {
					int index = location / 10;						
					chr9Array[index]++;
					
				}
				if (chromosome.equals("chr9") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr9Array_Rev[index]++;					
				}
				if (chromosome.equals("chr10") && direction.equals("+")) {
					int index = location / 10;						
					chr10Array[index]++;
					
				}
				if (chromosome.equals("chr10") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr10Array_Rev[index]++;					
				}
				if (chromosome.equals("chr11") && direction.equals("+")) {
					int index = location / 10;						
					chr11Array[index]++;
					
				}
				if (chromosome.equals("chr11") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr11Array_Rev[index]++;					
				}
				if (chromosome.equals("chr12") && direction.equals("+")) {
					int index = location / 10;						
					chr12Array[index]++;
					
				}
				if (chromosome.equals("chr12") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr12Array_Rev[index]++;					
				}
				if (chromosome.equals("chr13") && direction.equals("+")) {
					int index = location / 10;						
					chr13Array[index]++;
					
				}
				if (chromosome.equals("chr13") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr13Array_Rev[index]++;					
				}
				if (chromosome.equals("chr14") && direction.equals("+")) {
					int index = location / 10;						
					chr14Array[index]++;
					
				}
				if (chromosome.equals("chr14") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr14Array_Rev[index]++;					
				}
				if (chromosome.equals("chr15") && direction.equals("+")) {
					int index = location / 10;						
					chr15Array[index]++;
					
				}
				if (chromosome.equals("chr15") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr15Array_Rev[index]++;					
				}
				if (chromosome.equals("chr16") && direction.equals("+")) {
					int index = location / 10;						
					chr16Array[index]++;
					
				}
				if (chromosome.equals("chr16") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr16Array_Rev[index]++;					
				}
				if (chromosome.equals("chr17") && direction.equals("+")) {
					int index = location / 10;						
					chr17Array[index]++;
					
				}
				if (chromosome.equals("chr17") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr17Array_Rev[index]++;					
				}
				if (chromosome.equals("chr18") && direction.equals("+")) {
					int index = location / 10;						
					chr18Array[index]++;
					
				}
				if (chromosome.equals("chr18") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr18Array_Rev[index]++;					
				}
				if (chromosome.equals("chr19") && direction.equals("+")) {
					int index = location / 10;						
					chr19Array[index]++;
					
				}
				if (chromosome.equals("chr19") && direction.equals("-")) {					
					int index = (location) / 10;						
					chr19Array_Rev[index]++;					
				}
				if (chromosome.equals("chrM") && direction.equals("+")) {
					int index = location / 10;						
					chrMArray[index]++;
					
				}
				if (chromosome.equals("chrM") && direction.equals("-")) {					
					int index = (location) / 10;						
					chrMArray_Rev[index]++;					
				}
				if (chromosome.equals("chrX") && direction.equals("+")) {
					int index = location / 10;						
					chrXArray[index]++;
					
				}
				if (chromosome.equals("chrX") && direction.equals("-")) {					
					int index = (location) / 10;						
					chrXArray_Rev[index]++;					
				}
				if (chromosome.equals("chrY") && direction.equals("+")) {
					int index = location / 10;						
					chrYArray[index]++;
					
				}				
				if (chromosome.equals("chrY") && direction.equals("-")) {
					int index = location / 10;						
					chrYArray_Rev[index]++;
					
				}			
                count++;
                if (count % 1000000 == 0) {
                    System.out.println(count);
                }

			}
			in.close();

			/*FileWriter fwriter = new FileWriter("chr1_list.txt");*/
			FileWriter fwriter2 = new FileWriter(outputFile2);
            BufferedWriter out2 = new BufferedWriter(fwriter2);
            out2.write("track type=wiggle_0 name=\"Forward\" description=\"Forward\"\n");
            out2.write("variableStep chrom=chr1 span=100\n");
            
			FileWriter fwriter = new FileWriter(outputFile);
            BufferedWriter out = new BufferedWriter(fwriter);
            out.write("track type=wiggle_0 name=\"Reverse\" description=\"Reverse\"\n");
            out.write("variableStep chrom=chr1 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr1Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr1Array[i] + "\n");
                }                
            }
            out.write("variableStep chrom=chr2 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr2Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr2Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr3 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr3Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr3Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr4 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr4Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr4Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr5 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr5Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr5Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr6 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr6Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr6Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr7 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr7Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr7Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr8 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr8Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr8Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr9 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr9Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr9Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr10 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr10Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr10Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr11 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr11Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr11Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr12 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr12Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr12Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr13 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr13Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr13Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr14 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr14Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr14Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr15 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr15Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr15Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr16 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr16Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr16Array[i] + "\n");
                }
            }
            
            out.write("variableStep chrom=chr17 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr17Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr17Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr18 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr18Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr18Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chr19 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr19Array[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chr19Array[i] + "\n");
                }
            }
            out.write("variableStep chrom=chrM span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chrMArray[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chrMArray[i] + "\n");
                }
            }
            out.write("variableStep chrom=chrX span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chrXArray[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chrXArray[i] + "\n");
                }
            }
            out.write("variableStep chrom=chrY span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chrYArray[i] > 0) {
                    out.write((i * 10 + 1) + "\t" + chrYArray[i] + "\n");
                }
            }
            out.close();
            
            
            for (int i = 0; i < 25000000; i++) {
                if (chr1Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr1Array_Rev[i] + "\n");
                }                
            }
            out2.write("variableStep chrom=chr2 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr2Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr2Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr3 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr3Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr3Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr4 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr4Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr4Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr5 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr5Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr5Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr6 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr6Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr6Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr7 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr7Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr7Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr8 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr8Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr8Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr9 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr9Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr9Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr10 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr10Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr10Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr11 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr11Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr11Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr12 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr12Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr12Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr13 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr13Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr13Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr14 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr14Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr14Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr15 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr15Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr15Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr16 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr16Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr16Array_Rev[i] + "\n");
                }
            }
            
            out2.write("variableStep chrom=chr17 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr17Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr17Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr18 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr18Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr18Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chr19 span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chr19Array_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chr19Array_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chrM span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chrMArray_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chrMArray_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chrX span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chrXArray_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chrXArray_Rev[i] + "\n");
                }
            }
            out2.write("variableStep chrom=chrY span=100\n");
            for (int i = 0; i < 25000000; i++) {
                if (chrYArray_Rev[i] > 0) {
                    out2.write((i * 10 + 1) + "\t" + chrYArray_Rev[i] + "\n");
                }
            }
            out2.close();


    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}

