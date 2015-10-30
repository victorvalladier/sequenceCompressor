package compresseur;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class RecursiveCompressor extends Compressor {

	public RecursiveCompressor(Vector<Integer> vector) {
		super(vector);
	}

	@Override
	public Vector<Integer> compress() throws TooLongSequenceException, IOException {
		VectorOfSequence bestCompression = new VectorOfSequence();
		bestCompression = findTheLightestSequence(0, bestCompression);

		readSequencesToBinaryVector(bestCompression);
		
		return compression;
	}


	public VectorOfSequence findTheLightestSequence(int index, VectorOfSequence inputSequence) throws TooLongSequenceException, IOException {
		if(index == this.source.size())
			return inputSequence;

		int currentByteSize = Reader.getMinimumSizeOfAByte(this.source, index);
		VectorOfSequence firstSequence = null;

		try {
			if(inputSequence.lastElement().numberOfElements < 256) {
				firstSequence = new VectorOfSequence(inputSequence);
				firstSequence.addAByteToTheLastSequence(currentByteSize);
				firstSequence = findTheLightestSequence(index + 8, firstSequence);
			}
		}
		catch (NoSuchElementException e) {}

		VectorOfSequence secondSequence = new VectorOfSequence(inputSequence);
		System.out.println("1");
		secondSequence.add(new Sequence(currentByteSize));
		System.out.println("2");
		if(sequencesMemoisation.get(index).getNumberOfBitsPerByte() == -1) {
			secondSequence = findTheLightestSequence(index + 8, secondSequence);
			sequencesMemoisation.add(index, secondSequence.lastElement());
		}
		else {
			System.out.println("3");
			secondSequence.add(sequencesMemoisation.get(index));
			System.out.println("4");
		}

		if(firstSequence == null) {
			return secondSequence;
		}
		else {
			if(firstSequence.getSize() <= secondSequence.getSize()) {
				return firstSequence;
			}
			else {
				return secondSequence;
			}
		}

	}
}
