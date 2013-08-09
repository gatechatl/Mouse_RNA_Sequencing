import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;


/**
 *  * January 20, 2012
 *   * The purpose of this class is to map the SOAP
 *    * @author Tim Shaw
 *     *
 *      */
public class BOWTIE2Map2GFF {
    public static void main(String[] args) {

        try {
                String fileName = args[0];
                String fileName2 = args[1];
                String outputFile = args[2];

                    FileInputStream fstream = new FileInputStream(fileName);
                        DataInputStream din = new DataInputStream(fstream);
                        BufferedReader in = new BufferedReader(new InputStreamReader(din));


                        FileInputStream fstream2 = new FileInputStream(fileName2);
                        DataInputStream din2 = new DataInputStream(fstream2);
                        BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));

                        FileWriter fwriter = new FileWriter(outputFile);
                        BufferedWriter out = new BufferedWriter(fwriter);

                        int count = 0;
                        HashMap forward_map = new HashMap();
                        HashMap reverse_map = new HashMap();
                        while (in2.ready()) {
                                String str = in2.readLine();
                                String[] split = str.split("\t");
                                String chromosome = "chr" + split[0];
                                String type = split[1];
                                int start = new Integer(split[3]);
                                int end = new Integer(split[4]);
                                String direction = split[6];
                                String geneDescription = split[8];
                                if (type.contains("RNA") && direction.equals("+")) {
                                        count++;
                                        GeneInfo2 GeneInfo2 = new GeneInfo2();
                                        GeneInfo2.CHROMOSOME = chromosome;
                                        GeneInfo2.TYPE = type;
                                        GeneInfo2.START = start;
                                        GeneInfo2.END = end;
                                        forward_map.put(geneDescription, GeneInfo2);
                                } else if (type.contains("RNA") && direction.equals("-")) {
                                        count++;
                                        GeneInfo2 GeneInfo2 = new GeneInfo2();
                                        GeneInfo2.CHROMOSOME = chromosome;
                                        GeneInfo2.TYPE = type;
                                        GeneInfo2.START = start;
                                        GeneInfo2.END = end;
                                        reverse_map.put(geneDescription, GeneInfo2);
                                } 
                        } 

                        in2.close();

                        count = 0;
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
	                                
	                                int length = new Integer(100);
	                                
	                                
	                                String orig_direction = direction;                                
	                                String chromosome = split[2];
	                                int location = new Integer(split[3]);	                                	                                	                                 
	
	                                if (direction.equals("+") && reverse) {
	                                    direction = "-";
	                                } else if (direction.equals("-") && reverse) {
	                                    direction = "+";
	                                }
	                                if (pairend) {
	                                int start = -1;
	                                int end = -1;
	                                if (direction.equals("+")) {
	                                        start = location;
	                                        end = location + 100;
	                                        Iterator itr = forward_map.keySet().iterator();
	                                        while (itr.hasNext()) {
	                                                String geneDesc = (String)itr.next();
	                                                GeneInfo2 GeneInfo2 = (GeneInfo2)forward_map.get(geneDesc);
	                                                if (chromosome.equals(GeneInfo2.CHROMOSOME)) {
	
	                                                        boolean result = checkOverLap(GeneInfo2.START, GeneInfo2.END, start, end);
	                                                        if (result) {
	                                                            out.write(name + "\t" + chromosome + "\t" + orig_direction + "\t" + start + "\t" + end + "\t" + GeneInfo2.TYPE + "\t" + GeneInfo2.START + "\t" + GeneInfo2.END + "\t" + "+" + "\t" + geneDesc + "\n");
	                                                            out.flush();
	                                                        }
	                                                }
	                                        }
	                                }
	                                if (direction.equals("-")) {
	                                        start = location;
	                                        end = location + 100;
	
	                                        Iterator itr = reverse_map.keySet().iterator();
	                                        while (itr.hasNext()) {
	                                            String geneDesc = (String)itr.next();
	                                            GeneInfo2 GeneInfo2 = (GeneInfo2)reverse_map.get(geneDesc);
	                                            if (chromosome.equals(GeneInfo2.CHROMOSOME)) {
	                                                    boolean result = checkOverLap(GeneInfo2.START, GeneInfo2.END, start, end);
	                                                    if (result) {
	                                                            out.write(name + "\t" + chromosome + "\t" + orig_direction + "\t" + start + "\t" + end + "\t" + GeneInfo2.TYPE + "\t" + GeneInfo2.START + "\t" + GeneInfo2.END + "\t" + "-" + "\t" + geneDesc + "\n");
	                                                            out.flush();
	                                                    }
	                                            }
	                                        }
	                                }
	                                }
	                                count++;
	                                if (count % 1000 == 0) {
	                                	System.out.println(count);
	                                }
	                                
                            }        
                    }
                        
	                    in.close();
	                    out.close();
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
	}
	public static boolean checkOverLap(int start, int end, int qstart, int qend) {
	    if ((start <= qstart && qstart <= end) || (start <= qend && qend <= end)) {
	            return true;
	    }
	    return false;
	}

}
class GeneInfo2 {
    public String CHROMOSOME = "";
    public int START = -1;
    public int END = -1;
    public String TYPE = "";
}


