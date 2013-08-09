package AntisenseAnalysis;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

import java.util.Iterator;

public class ExtractUpDownRunBLAST {

	public static void main(String[] args) {
		String inputFile = args[0];
		String outputFile = args[1];
		
		try {
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
	        FileInputStream fstream = new FileInputStream(inputFile);
	        DataInputStream din = new DataInputStream(fstream);
	        BufferedReader in = new BufferedReader(new InputStreamReader(din));
	        int count = 0;
	        while (in.ready()) {
                String str = in.readLine();
                String[] split = str.split("\t");
                String geneName = split[0];
                String chr = split[1];
                int start = new Integer(split[2]);
                int end = new Integer(split[3]);
                String direction = split[4];
                if (count < 1) {                		                			                                         	
                	if (direction.equals("+")) {
                		sequence_extraction("MouseSequences_mm9/" + chr + ".fa", start, (end - start + 1), geneName, "original.fa");                		
                		sequence_extraction("MouseSequences_mm9/" + chr + ".fa", start - 5000, 5000, "upstream", "upstream.fa");                		
                		sequence_extraction("MouseSequences_mm9/" + chr + ".fa", end, 5000, "downstream", "downstream.fa");                		
                		String command = "formatdb -p F -i original.fa";
                		executeCommand(command);
                		command = "blastall -p blastn -i upstream.fa -d original.fa -S 2 -m8 -o upstreamprogram.output";
                		executeCommand(command);
                		HashMap upstreamhit = getSequenceHit("upstreamprogram.output", "upstream.fa", start, end, true);                      		
                		Iterator itr = upstreamhit.keySet().iterator();
                		while (itr.hasNext()) {
                			int index = (Integer)itr.next();
                			System.out.println(chr + "\t" + index + "\t" + upstreamhit.get(index));
                		}
                		command = "blastall -p blastn -i downstream.fa -d original.fa -S 2 -m8 -o downstreamprogram.output";
                        executeCommand(command);
                		HashMap downstreamhit = getSequenceHit("downstreamprogram.output", "downstream.fa", start, end, false);
                		itr = downstreamhit.keySet().iterator();
                		while (itr.hasNext()) {
                			int index = (Integer)itr.next();
                			System.out.println(chr + "\t" + index + "\t" + downstreamhit.get(index));
                		}
                        System.out.println("Finish blast: " + count);                                                
                	} 
                }
                count++;
	        }
	        in.close();	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void sequence_extraction(String file, double location, double size, String tag, String outputFile) {
		
		try {
			int index = 0;
			String finalstr = "";
			
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);
			
	        FileInputStream fstream = new FileInputStream(file);
	        DataInputStream din = new DataInputStream(fstream);
	        BufferedReader in = new BufferedReader(new InputStreamReader(din));
	        boolean writeFile = false;
	        while (in.ready()) {
	        	String str = in.readLine();
	        	if (!str.contains(">")) {
	        		if (writeFile) {
	        			finalstr += str.trim();
	        		}
	        		if (index <= location && location <= index + str.trim().length()) {
	        			finalstr += str.substring((int)location - index, str.length());	    
	        			writeFile = true;
	        		}
	        		index += str.trim().length();
	        		if (finalstr.length() > size) {
	        			
	        			out.write(">" + tag + "\n" + finalstr.substring(0, (int)size));
	        			break;
	        		}
	        	}
	        }
	        in.close();
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String readLineFile(String file) {
		String sequence = "";
		try {			
	        FileInputStream fstream = new FileInputStream(file);
	        DataInputStream din = new DataInputStream(fstream);
	        BufferedReader in = new BufferedReader(new InputStreamReader(din));
	        in.readLine();
	        String str = in.readLine();
	        sequence = str;
	        in.close();
	        return sequence;
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sequence;
	}
	public static HashMap getSequenceHit(String file, String source, int startcoord, int endcoord, boolean isUpstream) {
        HashMap map = new HashMap();
		try {
			
	        FileInputStream fstream = new FileInputStream(file);
	        DataInputStream din = new DataInputStream(fstream);
	        BufferedReader in = new BufferedReader(new InputStreamReader(din));
	        int count = 0;
	        String title = "";
	        String sequence = "";
	        while (in.ready()) {
                String str = in.readLine();
                String[] split = str.split("\t");
                int match = new Integer(split[3]);
                int mismatch = new Integer(split[4]);
                if (match >= 25 && mismatch <= 1) {
                	int qstart = new Integer(split[6]);
                	int qend = new Integer(split[7]);
                	int dstart = new Integer(split[9]);
                	int dend = new Integer(split[8]);

                	sequence_extraction(source, qstart, (qend - qstart + 1), "other", "other.sequence");
            		String upstream = readLineFile("other.sequence");
            		
            		sequence_extraction("original.fa", dstart, (dend - dstart + 1), "original", "original.sequence");
            		String original = readLineFile("original.sequence");
                    
            		String matching = match25(original, upstream);
            		if (!matching.equals("")) {
                		/*System.out.println(qstart + "\t" +qend + "\t" + upstream);
                		System.out.println(dstart + "\t" + dend + "\t" + (dend - dstart + 1) + "\t" + original);*/
            			if (isUpstream) {
            				map.put((startcoord + dstart - 1) + "\t" + (startcoord - 5000 + qstart - 1), matching);
            			} else {
            				map.put((startcoord + dstart - 1) + "\t" + (endcoord + qstart - 1), matching);
            			}
            		}
                }
	        }
	        in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static String match25(String original, String other) {
		for (int i = 0; i <= original.length() - 25; i++) {
			int totalmatch = 0;
			String matchingStr = "";
			for (int j = i; j < i + 25; j++) {
				if (original.substring(j, j + 1).equals(other.substring(j, j + 1))) {
					totalmatch++;
					matchingStr += original.substring(j, j + 1);
				}
			}
			if (totalmatch >= 24) {
				return matchingStr;
			}
        }		
		return "";
	}
	public static void convertRevCompliment(String file) {
		try {
			
			FileWriter fwriter = new FileWriter(file + "_revcomp.fa");
			BufferedWriter out = new BufferedWriter(fwriter);
			
	        FileInputStream fstream = new FileInputStream(file);
	        DataInputStream din = new DataInputStream(fstream);
	        BufferedReader in = new BufferedReader(new InputStreamReader(din));
	        int count = 0;
	        String title = "";
	        String sequence = "";
	        while (in.ready()) {
                String str = in.readLine();
                if (!str.contains(">")) {
                	sequence += str;
                } else {
                	title = str;
                }
	        }
	        in.close();
	        String newseq = "";
	        for (int i = sequence.length() - 1; i >= 0; i--) {
	        	newseq += complement(sequence.substring(i, i + 1).toUpperCase());
	        }
	        out.write(title + "\n" + newseq);
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String complement(String chr) {
		chr = chr.toUpperCase();
		if (chr.equals("G")) {
			return "C";
		}
		if (chr.equals("C")) {
			return "G";
		}
		if (chr.equals("A")) {
			return "T";
		}
		if (chr.equals("T")) {
			return "A";
		}
		return chr;
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


