package compresseur;

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
	
	public abstract Vector<Integer> compress();
}
