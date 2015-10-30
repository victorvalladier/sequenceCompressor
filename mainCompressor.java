package compresseur;

import java.io.IOException;
import java.util.Vector;

public class mainCompressor {

	public static void main(String[] args) {
		Reader reader = new Reader(args[0]);
		Vector<Integer> vector = reader.read();
		System.out.println(vector.toString());
		RecursiveCompressor compressor = new RecursiveCompressor(vector);
		try {
			Vector<Integer> result = compressor.compress();
		} catch (TooLongSequenceException | IOException e) {
			e.printStackTrace();
		}
	}

}
