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
public class SOAPMap2GFF {
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
                                        GeneInfo geneInfo = new GeneInfo();
                                        geneInfo.CHROMOSOME = chromosome;
                                        geneInfo.TYPE = type;
                                        geneInfo.START = start;
                                        geneInfo.END = end;
                                        forward_map.put(geneDescription, geneInfo);
                                } else if (type.contains("RNA") && direction.equals("-")) {
                                        count++;
                                        GeneInfo geneInfo = new GeneInfo();
                                        geneInfo.CHROMOSOME = chromosome;
                                        geneInfo.TYPE = type;
                                        geneInfo.START = start;
                                        geneInfo.END = end;
                                        reverse_map.put(geneDescription, geneInfo);
                                }
                        }

                        in2.close();

                        count = 0;
                        while (in.ready()) {
                                String str = in.readLine();
                                String[] split = str.split("\t");
                                String name = split[0]; 

                                boolean reverse = false;
                                if (name.contains("/2")) {
                                    
                                    reverse = true;
                                }
                                int length = new Integer(split[5]);
                                String direction = split[6];
                                String orig_direction = split[6];
                                if (direction.equals("+") && reverse) {
                                    direction = "-";
                                } else if (direction.equals("-") && reverse) {
                                    direction = "+";
                                }
                                String chromosome = split[7];
                                int location = new Integer(split[8]);
                                int start = -1;
                                int end = -1;
                                if (direction.equals("+")) {
                                        start = location;
                                        end = location + 100;
                                        Iterator itr = forward_map.keySet().iterator();
                                        while (itr.hasNext()) {
                                                String geneDesc = (String)itr.next();
                                                GeneInfo geneInfo = (GeneInfo)forward_map.get(geneDesc);
                                                if (chromosome.equals(geneInfo.CHROMOSOME)) {

                                                        boolean result = checkOverLap(geneInfo.START, geneInfo.END, start, end);
                                                        if (result) {
                                                            out.write(name + "\t" + chromosome + "\t" + orig_direction + "\t" + start + "\t" + end + "\t" + geneInfo.TYPE + "\t" + geneInfo.START + "\t" + geneInfo.END + "\t" + "+" + "\t" + geneDesc + "\n");
                                                           
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
                                            GeneInfo geneInfo = (GeneInfo)reverse_map.get(geneDesc);
                                            if (chromosome.equals(geneInfo.CHROMOSOME)) {
                                                    boolean result = checkOverLap(geneInfo.START, geneInfo.END, start, end);
                                                    if (result) {
                                                            out.write(name + "\t" + chromosome + "\t" + orig_direction + "\t" + start + "\t" + end + "\t" + geneInfo.TYPE + "\t" + geneInfo.START + "\t" + geneInfo.END + "\t" + "-" + "\t" + geneDesc + "\n");
                                                            
                                                    }
                                            }
                                    }
                            }
                            count++;
                            if (count % 1000 == 0) {
                            	out.flush();
                                System.out.println(count);
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


class GeneInfo {
    public String CHROMOSOME = "";
    public int START = -1;
    public int END = -1;
    public String TYPE = "";
}

