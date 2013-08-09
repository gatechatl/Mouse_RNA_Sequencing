package StatisticsSummary;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadLogFile {

	public static void main(String[] args) {
		String fileName = args[0];
		
		try {
			String[] files = {"bowtie.left_kept_reads.fixmap.log", "bowtie.left_kept_reads.m2g_um.fixmap.log", "bowtie.left_kept_reads.m2g_um_seg1.fixmap.log",
					"bowtie.left_kept_reads.m2g_um_seg2.fixmap.log", "bowtie.left_kept_reads.m2g_um_seg3.fixmap.log", "bowtie.left_kept_reads.m2g_um_seg4.fixmap.log",
					"bowtie.right_kept_reads.fixmap.log", "bowtie.right_kept_reads.m2g_um.fixmap.log", "bowtie.right_kept_reads.m2g_um_seg1.fixmap.log",
					"bowtie.right_kept_reads.m2g_um_seg2.fixmap.log", "bowtie.right_kept_reads.m2g_um_seg3.fixmap.log", "bowtie.right_kept_reads.m2g_um_seg4.fixmap.log"};
			
			int totalReads = 0;
			int alignedReads = 0;
			int failedReads = 0;
			int suppressed_reads = 0;
		
			for (String file: files) {
				
				FileInputStream fstream = new FileInputStream(fileName + "/" + file);
				DataInputStream din = new DataInputStream(fstream); 			
				BufferedReader in = new BufferedReader(new InputStreamReader(din));
				while (in.ready()) {
					String str = in.readLine();
					String[] split = str.split(" ");
					if (str.contains("# reads processed: ")) {
						if (file.equals("bowtie.left_kept_reads.fixmap.log") || file.equals("bowtie.right_kept_reads.fixmap.log")) {
							totalReads += new Integer(split[split.length - 1]);
						}
					} else if (str.contains("# reads with at least one reported alignment: ")) {
						alignedReads += new Integer(split[split.length - 2]);
					} else if (str.contains("# reads that failed to align: ")) {
						
					} else if (str.contains("# reads with alignments suppressed due to -m: ")) {
						
					}
				}
				in.close();
				
			}
			double ratio = new Integer(alignedReads).doubleValue() / totalReads;
			System.out.println(totalReads + "\t" + alignedReads + "\t" + ratio);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
