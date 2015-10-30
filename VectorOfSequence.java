package compresseur;

import java.util.Vector;

@SuppressWarnings("serial")
public class VectorOfSequence extends Vector<Sequence> {
	int weight;
	
	public VectorOfSequence() {
		super();
		this.weight = 0;
	}
	
	public VectorOfSequence(VectorOfSequence v) {
		super(v);
		this.weight = v.getSize();
	}
	
	public void addAByteToTheLastSequence(int minimumSize) throws TooLongSequenceException {
		int lastElementSize = this.lastElement().getSize();
		this.lastElement().addAByte(minimumSize);
		this.weight = this.size() - lastElementSize + this.lastElement().getSize();
	}

	public int getSize() {
		return this.weight;
	}
	
	public boolean add(Sequence sequence) {
		System.out.println(sequence.getSize());
		this.weight += sequence.getSize();
		return this.add(sequence);
	}
}
