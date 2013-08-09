package PostProcessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class ComputeExpressionLevelRPKM {

    public static HashMap getGTFInformation(String fileName) {
        HashMap map = new HashMap();
        
        try {



                FileInputStream fstream = new FileInputStream(fileName);
                DataInputStream din = new DataInputStream(fstream);
                BufferedReader in = new BufferedReader(new InputStreamReader(din));
                while (in.ready()) {
                        String str = in.readLine();
                        String[] split = str.split("\t");
                        String data = split[8];
                        String type = data.split("gene_biotype")[1].split(";")[0].replaceAll("\"", "").trim();
                        String ensembl = data.split("gene_id")[1].split(";")[0].replaceAll("\"", "").trim();
                        String geneName = data.split("gene_name")[1].split(";")[0].replaceAll("\"", "").trim();

                        map.put(ensembl, geneName + "\t" + type);
                }
                in.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
        return map;
}


	public static void main(String[] args) {
		String filePath = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Tophat Result\\";
		String outputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Tophat Result\\TopHat_NonCoding_Expression_Level.output";;
		HashMap map = new HashMap();
		HashMap metadataMap = getGTFInformation("C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\Tophat Result\\Mus_musculus.NCBIM37.67.CHR.gtf");
		try {
			
			String[] librarys = {"AYAD21", "AYAD22", "AYAD23", "AYAD24", "AYAD25", "AYAD26", "AYAD28", "AYAD36"};
			double[] ratio = {1.058, 1.022, 0.926, 0.937, 1.229, 0.937, 0.778, 1.266};
			int count = 0;
			for (String library: librarys) {
				String fileName = filePath + library + "_CoverageBED_NonCoding_Unique.txt";
			    FileInputStream fstream = new FileInputStream(fileName);
				DataInputStream din = new DataInputStream(fstream); 
				BufferedReader in = new BufferedReader(new InputStreamReader(din));		
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split("\t");
					String key = split[0] + "\t" + split[1] + "\t" + split[2] + "\t" + split[3];
					String geneName = split[3];
					
                    String metadata = "no available ensembl" + "\t" + "Unannotated Cufflink";
                    if (metadataMap.containsKey(geneName)) {
                            metadata = (String)metadataMap.get(geneName);
                    }
                    key += "\t" + metadata;
					double reads = new Double(split[4]);
					double kilobase = new Double(split[5]);
					double expression = reads / (kilobase * ratio[count]);
					if (map.containsKey(key)) {
						String expressions = (String)map.get(key);
						expressions += "\t" + expression;
						map.put(key, expressions + "");
					} else {
						map.put(key, expression + "");
					}
				}
				in.close();
				count++;
			}
			FileWriter fwriter = new FileWriter(outputFile);
			BufferedWriter out = new BufferedWriter(fwriter);		
			Iterator itr = map.keySet().iterator();
			while (itr.hasNext()) {
				String key = (String)itr.next();
				String value = (String)map.get(key);
				out.write(key + "\t" + value + "\n");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
