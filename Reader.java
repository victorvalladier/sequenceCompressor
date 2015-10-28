package compresseur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Reader {
	private String fileName;

	public Reader(String fileName) {
		this.fileName = fileName;
	}

	public Vector<Integer> read() {
		Vector<Integer> vector = new Vector<Integer>();

		try{
			File file = new File (this.fileName);
			FileReader fileReader = new FileReader (file);
			BufferedReader bufferedReader = new BufferedReader (fileReader);

			int character = fileReader.read();
			int index = 0;
			while (character != 10 && character != 13) {
				if(character != 48 && character != 49) {
					throw new IOException("Bad file content");
				}
				vector.add(character);
				character = fileReader.read();
			}
		}
		catch (IOException exception)
		{
			System.out.println ("Error while reading file : " + exception.getMessage());
		}

		return vector;
	}
	
	public static int readAByte(Vector<Integer> source, int index) throws IOException {
		int result = 0;
		for(int i = 0; i < 8; i++) {
			if((index + i) >= source.size()) {
				throw new IOException("File not big enough to read a byte at this index");
			}
			result = result * 2 + Character.getNumericValue(source.get(index + i));
		}
		return result;
	}
	
	public static int getMinimumSizeOfAByte(Vector<Integer> source, int index) throws IOException {
		int result = 1;
		for(int i = 0; i < 8; i++) {
			if((index + i) >= source.size()) {
				throw new IOException("File not big enough to read a byte at this index");
			}
			if(Character.getNumericValue(source.get(index + i)) == 1) {
				result = i + 1;
			}
		}
		return result;
	}

}
