package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;


public class MapBowtieFormat2GeneList {
    public static void main(String[] args) {
    	
    	try {
    		String fileName = args[0];    	
    		String fileName2 = args[1];
    		String output = args[2];
    		
    		HashMap chr1_map = parseGeneList(fileName2, "chr1");
    		HashMap chr2_map = parseGeneList(fileName2, "chr2");
    		HashMap chr3_map = parseGeneList(fileName2, "chr3");
    		HashMap chr4_map = parseGeneList(fileName2, "chr4");
    		HashMap chr5_map = parseGeneList(fileName2, "chr5");
    		HashMap chr6_map = parseGeneList(fileName2, "chr6");
    		HashMap chr7_map = parseGeneList(fileName2, "chr7");
    		HashMap chr8_map = parseGeneList(fileName2, "chr8");
    		HashMap chr9_map = parseGeneList(fileName2, "chr9");
    		HashMap chr10_map = parseGeneList(fileName2, "chr10");
    		HashMap chr11_map = parseGeneList(fileName2, "chr11");
    		HashMap chr12_map = parseGeneList(fileName2, "chr12");
    		HashMap chr13_map = parseGeneList(fileName2, "chr13");
    		HashMap chr14_map = parseGeneList(fileName2, "chr14");
    		HashMap chr15_map = parseGeneList(fileName2, "chr15");
    		HashMap chr16_map = parseGeneList(fileName2, "chr16");
    		HashMap chr17_map = parseGeneList(fileName2, "chr17");
    		HashMap chr18_map = parseGeneList(fileName2, "chr18");
    		HashMap chr19_map = parseGeneList(fileName2, "chr19");
    		HashMap chrX_map = parseGeneList(fileName2, "chrX");
    		HashMap chrY_map = parseGeneList(fileName2, "chrY");
    		
    		int count = 0;
    		
    		HashMap keepHitList = new HashMap();
    		
			FileWriter fwriter2 = new FileWriter(output);
            BufferedWriter out = new BufferedWriter(fwriter2);
    		
		    FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream din = new DataInputStream(fstream); 			
			BufferedReader in = new BufferedReader(new InputStreamReader(din));						
			while (in.ready()) {
				String str = in.readLine();
                if (!(str.contains("@SQ") || str.contains("@HD") || str.contains("@PG"))) {
                    String[] split = str.split("\t");
                    String name = split[0]; 
                    int flag = new Integer(split[1]); 
                    String direction = "+";
                    boolean pairend = false;
                    boolean reverse = false;
                    
                    if (flag >= 128) {
                    	flag = flag - 128;
                        reverse = true;	
                    }
                    if (flag >= 64) {
                    	flag = flag - 64;
                    	reverse = false;
                    }
                    if (flag >= 32) {
                    	flag = flag - 32;
                    	direction = "+";
                    }
                    if (flag >= 16) {
                    	flag = flag - 16;
                    	direction = "-";
                    }
                    
                    if (flag >= 8) {
                    	flag = flag - 8;	                                	
                    }
                    if (flag >= 4) {
                    	flag = flag - 4;	                                	
                    }
                    if (flag >= 2) {
                    	flag = flag - 2;
                    	pairend = true;
                    }
                    if (flag >= 1) {
                    	flag = flag - 1;
                    }
                    
                                       
                    
                    String orig_direction = direction;                
                    if (direction.equals("+") && reverse) {
                        direction = "-";
                    } else if (direction.equals("-") && reverse) {
                        direction = "+";
                    }
                    
                    int buffer = 100;
                    if (direction.equals("-")) {
                    	buffer = -100;
                    }
                     
                    String chr = split[2];
                    int location = new Integer(split[3]);	                                	                                	                                 
                    if (pairend) {
            
    	        		if (chr.equals("chr1")) {
    	        			reomveHashMap(chr1_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr2")) {
    	        			reomveHashMap(chr2_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr3")) {
    	        			reomveHashMap(chr3_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr4")) {
    	        			reomveHashMap(chr4_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr5")) {
    	        			reomveHashMap(chr5_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr6")) {
    	        			reomveHashMap(chr6_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr7")) {
    	        			reomveHashMap(chr7_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr8")) {
    	        			reomveHashMap(chr8_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr9")) {
    	        			reomveHashMap(chr9_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr10")) {
    	        			reomveHashMap(chr10_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr11")) {
    	        			reomveHashMap(chr11_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr12")) {
    	        			reomveHashMap(chr12_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr13")) {
    	        			reomveHashMap(chr13_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr14")) {
    	        			reomveHashMap(chr14_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr15")) {
    	        			reomveHashMap(chr15_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr16")) {
    	        			reomveHashMap(chr16_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr17")) {
    	        			reomveHashMap(chr17_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr18")) {
    	        			reomveHashMap(chr18_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chr19")) {
    	        			reomveHashMap(chr19_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chrX")) {
    	        			reomveHashMap(chrX_map, chr, location, location + buffer, 1, keepHitList);
    	        		} else if (chr.equals("chrY")) {
    	        			reomveHashMap(chrY_map, chr, location, location + buffer, 1, keepHitList);
    	        		}
    	    	        count++;
    	        		/*int total_size = chr1_map.size() + chr2_map.size() + chr3_map.size() + chr4_map.size()
    	        		+ chr5_map.size() + chr6_map.size() + chr7_map.size() + chr8_map.size() + chr9_map.size() + chr10_map.size()
    	        		+ chr11_map.size() + chr12_map.size() + chr13_map.size() + chr14_map.size() + chr15_map.size() + chr16_map.size()
    	        		+ chr17_map.size() + chr18_map.size() + chr19_map.size() + chrX_map.size() + chrY_map.size();
    	        		*/
    	        		if (count % 10000 == 0) {
    	        			System.out.println("Progress: " + count + "\t" + keepHitList.size());	
    	        		}
                    }
	                
                }
			}
			in.close();
	        Iterator itr = keepHitList.keySet().iterator();
	        while (itr.hasNext()) {
	        	String key = (String)itr.next();
	        	int hitNumber = (Integer)keepHitList.get(key);
	        	out.write(key + "\t" + hitNumber + "\n");
	        }
	        out.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public static HashMap parseGeneList(String fileName, String chr) {
    	HashMap map = new HashMap();
    	try {
	        FileInputStream fstream2 = new FileInputStream(chr + fileName);
	        DataInputStream din2 = new DataInputStream(fstream2);
	        BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));
	        while (in2.ready()) {
	        	String str = in2.readLine();
	        	String[] split = str.split("\t");
	        	map.put(split[0], str);	        	
	        }
	        in2.close();
	        return map;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}    	
    	return map;
    }

    public static void reomveHashMap(HashMap map, String chr, int location, int end_location, int readList, HashMap allList) {
	    Iterator itr = map.keySet().iterator();
	    int switch_location = location;
	    if (location > end_location) {
	    	location = end_location;
	    	end_location = switch_location;
	    }
	    while (itr.hasNext()) {
	    	String key = (String)itr.next();
	    	String str2 = (String)map.get(key);
        	
        	String[] split2 = str2.split("\t");
        	int start = new Integer(split2[6]);
        	int end = new Integer(split2[7]);
        	if (chr.equals(split2[5])) {
        		boolean foundHit = false;
        	    if (start <= location && location <= end) {
        	    	foundHit = true;
        	    }
        	    if (start <= end_location && end_location <= end) {
        	    	foundHit = true;
        	    }
        	    if (location <= start && start <= end_location) {
        	    	foundHit = true;
        	    }
        	    if (location <= end && end <= end_location) {
        	    	foundHit = true;
        	    }
        	    if (foundHit) {
        	    	if (!allList.containsKey(key)) {
        	    		allList.put(key, readList);
        	    	} else {
        	    		int readNumber = (Integer)allList.get(key);
        	    		readNumber = readNumber + readList;
        	    		allList.put(key, readNumber);
        	    	}
        	    }
        	}
	    }
    }
}

