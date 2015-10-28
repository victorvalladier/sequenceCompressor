package compresseur;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Extractor {

	public static void main(String[] args) throws IOException {
		if(args.length != 1) {
			System.out.println("Usage: extractor <file to extract>");
		}

		Reader reader = new Reader(args[0]);
		Vector<Integer> vector = reader.read();
		if(vector.size() == 0) {
			throw new IOException("Unexploitable file");
		}


		try
		{
			// Creation of the result file
			StringBuffer buffer = new StringBuffer(args[0]);
			buffer.replace(buffer.lastIndexOf("."),buffer.length(),".seg");
			FileWriter fileWriter = new FileWriter (buffer.toString());

			int nbOfPixels;
			int sizeOfPixel;
			
			int index = 0;
			int character;;

			while (index != vector.size())
			{
				nbOfPixels = 0;
				sizeOfPixel = 0;
				// Acquiring the number of pixels in the segment
				try{
					nbOfPixels = Reader.readAByte(vector, index);
					index += 8;
				}
				catch(IOException exception){
					throw new IOException("Uncomplete header readen");
				}
				
				// Acquiring the size of each pixel of the segment
				for(int i = 0; i < 3; i++) {
					if(index == vector.size())
					{
						throw new IOException("Uncomplete header readen");
					}
					character = vector.get(index);
					sizeOfPixel = sizeOfPixel * 2 + Character.getNumericValue(character);
					index++;
				}

				// Reading the segment
				for(int i = 0; i < nbOfPixels; i++) {
					// Writing missing bits of the segment
					for(int j = 0; j < 8 - sizeOfPixel; j++) {
						fileWriter.write("0");
					}
					// Copying current bit
					for(int j=0; j < sizeOfPixel; j++) {
						if(index == vector.size())
						{
							throw new IOException("Uncomplete data readen");
						}
						character = vector.get(index);
						fileWriter.write(character);
						index++;
					}
				}
			}
			fileWriter.close();
		}
		catch (IOException exception)
		{
			System.out.println ("Error while reading file : " + exception.getMessage());
		}
	}
}

