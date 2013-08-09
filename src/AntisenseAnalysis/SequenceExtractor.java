package AntisenseAnalysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * Extract sequence from a file
 * @author gatech
 *
 */
public class SequenceExtractor {

	public static void main(String[] args) {
		
		String file = args[0];
		double location = new Double(args[1]);
		double size = new Double(args[2]);
		String tag = args[3];
		String outputFile = args[4];
		sequence_extraction(file, location, size, tag, outputFile);
		
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

}
