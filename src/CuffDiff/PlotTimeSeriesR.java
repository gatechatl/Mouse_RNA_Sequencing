package CuffDiff;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class PlotTimeSeriesR {

	public static void main(String[] args) {
		String inputFile = "C:\\School Notes\\HIV_Diversity\\SideProject\\Cancer RNAseq\\TopHat Differential Expression Result\\Input.txt";
		try {
			LinkedList list = new LinkedList();
			FileInputStream fstream = new FileInputStream(inputFile);
			DataInputStream din = new DataInputStream(fstream); 
			BufferedReader in = new BufferedReader(new InputStreamReader(din));
			double min = Double.POSITIVE_INFINITY;
			double max = Double.NEGATIVE_INFINITY;
			while (in.ready()) {				
				String str = in.readLine();
				String[] split = str.split("\t");
				for (int i = 1; i < split.length; i++) {
					double num = new Double(split[i]);
					if (min > num) {
						min = num;
					}
					if (max < num) {
						max = num;
					}
				}
				String geneName = split[0];
				list.add(geneName);
			}
			in.close();
			System.out.println(plotgroup(list, min, max));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String plotgroup(LinkedList list, double min, double max) {
		String R = "";
		R += "filename = 'C:/School Notes/HIV_Diversity/SideProject/Cancer RNAseq/TopHat Differential Expression Result/Hedge_Hog_Small.txt';\n";
		R += "data=read.csv(filename, sep = '\\t', header=FALSE);\n";
		R += "data = as.matrix(data);\n";
		R += "plot(0,0,col=0,xlab='time points', ylab='FPKM',xlim=c(1, 8), ylim=c(" + min + "," + 50 + "));\n";
		String names = "c('" + (String)list.get(0) + "'";
		String col = "c(1";
		for (int i = 1; i <= 8; i++) {
			R += "points(data[" + i + ",2:9], type = 'l', col = " + i + ", lwd=3);\n";
			
		}
		for (int i = 1; i < 8; i++) {
			names += ",'" + (String)list.get(i) + "'";
			col += "," + (i + 1);
		}
		names += ")";
		col += ")";
		R += "legend('topleft', " + names + ", col = " + col + ", lwd=3);\n";
		return R;
	}
}
