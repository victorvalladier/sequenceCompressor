package compresseur;

import java.util.Vector;

@SuppressWarnings("serial")
public class VectorOfSequence extends Vector<Sequence> {
	int size;
	
	public VectorOfSequence() {
		super();
		size = 0;
	}
	
	public void addAByteToTheLastSequence(int minimumSize) throws TooLongSequenceException {
		int lastElementSize = this.lastElement().getSize();
		this.lastElement().addAByte(minimumSize);
		this.size = this.size() - lastElementSize + this.lastElement().getSize();
	}

	public int getSize() {
		return this.size;
	}
	
	public boolean add(Sequence sequence) {
		this.size += sequence.getSize();
		return this.add(sequence);
	}
}
