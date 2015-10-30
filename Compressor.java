package compresseur;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public abstract class Compressor {
	Vector<Integer> source;
	Vector<Integer> compression;
	Vector<Sequence> sequencesMemoisation;

	public Compressor(Vector<Integer> vector) {
		this.source = vector;
		this.sequencesMemoisation = new Vector<Sequence>();
		for (int i = 0; i < vector.size(); i++) {
			sequencesMemoisation.add(new Sequence(-1));
		}


	}

	public abstract Vector<Integer> compress() throws TooLongSequenceException, IOException;

	public void readSequencesToBinaryVector (VectorOfSequence bestCompression) {
		int index = 0;
		int numberOfElements, numberOfBits;
		
		for(Iterator<Sequence> i = bestCompression.iterator(); i.hasNext(); ) {
			Sequence item = i.next();

			// Writing header
			numberOfElements = item.getNumberOfElements();
			numberOfBits = item.getNumberOfBitsPerByte();
			for(float l = 128; l >= 1; l /= 2) {
				if(numberOfElements / l >= 1) {
					compression.add(1);
					numberOfElements -= l;
				}
				else {
					compression.add(0);
				}
			}
			for(float l = 4; l >= 1; l/=2) {
				if(numberOfBits / l >= 1) {
					compression.add(1);
					numberOfBits -= l;
				}
				else {
					compression.add(0);
				}
			}

			// Writing data
			for(int j = 0; j < item.numberOfElements; j++) {
				for(int k = 0; k < (8 - item.numberOfBitsPerByte); k++) {
					// Skipping useless zeros
					index++;
				}
				for(int k = 0; k < item.numberOfBitsPerByte; k++) {
					compression.add(source.get(index));
					System.out.println(source.get(index));
					index++;
				}
			}
		}
	}
}
