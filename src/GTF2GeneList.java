import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;


public class GTF2GeneList {
    public static void main(String[] args) {
    	
    	String fileName = args[0];
    	String outputFile = args[1];
    	try {
    		
			FileWriter fwriter1 = new FileWriter("chr1" + outputFile);
            BufferedWriter out1 = new BufferedWriter(fwriter1);
			FileWriter fwriter2 = new FileWriter("chr2" + outputFile);
            BufferedWriter out2 = new BufferedWriter(fwriter2);
			FileWriter fwriter3 = new FileWriter("chr3" + outputFile);
            BufferedWriter out3 = new BufferedWriter(fwriter3);
			FileWriter fwriter4 = new FileWriter("chr4" + outputFile);
            BufferedWriter out4 = new BufferedWriter(fwriter4);
			FileWriter fwriter5 = new FileWriter("chr5" + outputFile);
            BufferedWriter out5 = new BufferedWriter(fwriter5);
			FileWriter fwriter6 = new FileWriter("chr6" + outputFile);
            BufferedWriter out6 = new BufferedWriter(fwriter6);
			FileWriter fwriter7 = new FileWriter("chr7" + outputFile);
            BufferedWriter out7 = new BufferedWriter(fwriter7);
			FileWriter fwriter8 = new FileWriter("chr8" + outputFile);
            BufferedWriter out8 = new BufferedWriter(fwriter8);
			FileWriter fwriter9 = new FileWriter("chr9" + outputFile);
            BufferedWriter out9 = new BufferedWriter(fwriter9);
			FileWriter fwriter10 = new FileWriter("chr10" + outputFile);
            BufferedWriter out10 = new BufferedWriter(fwriter10);
			FileWriter fwriter11 = new FileWriter("chr11" + outputFile);
            BufferedWriter out11 = new BufferedWriter(fwriter11);
			FileWriter fwriter12 = new FileWriter("chr12" + outputFile);
            BufferedWriter out12 = new BufferedWriter(fwriter12);
			FileWriter fwriter13 = new FileWriter("chr13" + outputFile);
            BufferedWriter out13 = new BufferedWriter(fwriter13);
			FileWriter fwriter14 = new FileWriter("chr14" + outputFile);
            BufferedWriter out14 = new BufferedWriter(fwriter14);
			FileWriter fwriter15 = new FileWriter("chr15" + outputFile);
            BufferedWriter out15 = new BufferedWriter(fwriter15);
			FileWriter fwriter16 = new FileWriter("chr16" + outputFile);
            BufferedWriter out16 = new BufferedWriter(fwriter16);
			FileWriter fwriter17 = new FileWriter("chr17" + outputFile);
            BufferedWriter out17 = new BufferedWriter(fwriter17);
			FileWriter fwriter18 = new FileWriter("chr18" + outputFile);
            BufferedWriter out18 = new BufferedWriter(fwriter18);
			FileWriter fwriter19 = new FileWriter("chr19" + outputFile);
            BufferedWriter out19 = new BufferedWriter(fwriter19);
			FileWriter fwriterX = new FileWriter("chrX" + outputFile);
            BufferedWriter outX = new BufferedWriter(fwriterX);
			FileWriter fwriterY = new FileWriter("chrY" + outputFile);
            BufferedWriter outY = new BufferedWriter(fwriterY);
            
	        FileInputStream fstream2 = new FileInputStream(fileName);
	        DataInputStream din2 = new DataInputStream(fstream2);
	        BufferedReader in2 = new BufferedReader(new InputStreamReader(din2));
	        HashMap map = new HashMap();
	        while (in2.ready()) {
	        	String str = in2.readLine();
	        	String[] split = str.split("\t");
	        	if (split[1].equals("protein_coding")) {
	        		String chromosome = split[0];
	        		int start = new Integer(split[3]);
	        		int end = new Integer(split[4]);
	        		String geneInfo = split[8];
	        		String[] split2 = geneInfo.split("protein_id");
	        		if (split2.length > 1) {
	        			String protein_id = split2[1];
	        			protein_id = protein_id.trim();
	        			protein_id = protein_id.replaceAll("\"", "");
	        			
	        			if (map.containsKey(protein_id)) {
		        			GeneInfo3 gene = (GeneInfo3)map.get(protein_id);
		        			if (gene.START > start) {
		        				gene.START = start;
		        			}
		        			if (gene.END < end) {
		        				gene.END = end;
		        			}		        			
	        				map.put(protein_id, gene);
	        			} else {
		        			GeneInfo3 gene = new GeneInfo3();
		        			gene.NAME = protein_id;
		        			gene.START = new Integer(start);
		        			gene.END = new Integer(end);
		        			gene.CHROMOSOME = chromosome;
		        			map.put(protein_id, gene);
	        			}
	        		}
	        	}
	        }
	        
	        Iterator itr = map.keySet().iterator();
	        while (itr.hasNext()) {
	        	String key = (String)itr.next();
	        	GeneInfo3 gene = (GeneInfo3)map.get(key);
	        	System.out.println(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END);
	        	String chr = "chr" + gene.CHROMOSOME;
	        	if (chr.equals("chr1")) {
	        		out1.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr2")) {
	        		out2.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr3")) {
	        		out3.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr4")) {
	        		out4.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr5")) {
	        		out5.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr6")) {
	        		out6.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr7")) {
	        		out7.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr8")) {
	        		out8.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr9")) {
	        		out9.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr10")) {
	        		out10.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr11")) {
	        		out11.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr12")) {
	        		out12.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr13")) {
	        		out13.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr14")) {
	        		out14.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr15")) {
	        		out15.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr16")) {
	        		out16.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr17")) {
	        		out17.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr18")) {
	        		out18.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chr19")) {
	        		out19.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chrX")) {
	        		outX.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	} else if (chr.equals("chrY")) {
	        		outY.write(gene.NAME + "\t" + "chr" + gene.CHROMOSOME + "\t" + gene.START + "\t" + gene.END + "\n");
	        	}
	        }
	        out1.close();
	        out2.close();
	        out3.close();
	        out4.close();
	        out5.close();
	        out6.close();
	        out7.close();
	        out8.close();
	        out9.close();
	        out10.close();
	        out11.close();
	        out12.close();
	        out13.close();
	        out14.close();
	        out15.close();
	        out16.close();
	        out17.close();
	        out18.close();
	        out19.close();
	        outX.close();
	        outY.close();
	        
	        System.out.println(map.size());
	        in2.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}

class GeneInfo3 {
	public String NAME = "";
    public String CHROMOSOME = "";
    public int START = -1;
    public int END = -1;
    public String TYPE = "";
}