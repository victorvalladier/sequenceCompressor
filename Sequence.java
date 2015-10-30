package compresseur;

public class Sequence {
	int numberOfElements;
	int numberOfBitsPerByte;
	int size;
	
	public Sequence() {
		this.numberOfBitsPerByte = 0;
		this.numberOfElements = 0;
		this.size = 0;
	}
	
	public Sequence(int numberOfBits) {
		this.numberOfBitsPerByte = numberOfBits;
		this.numberOfElements = 1;
		this.size = numberOfBits;
	}
	
	public int getSize() {
		return size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void addAByte(int minimumSize) throws TooLongSequenceException {
		if(numberOfElements == 256) {
			throw new TooLongSequenceException();
		}
		if(minimumSize > numberOfBitsPerByte) {
			numberOfBitsPerByte = minimumSize;
		}
		numberOfElements++;
		size = this.numberOfBitsPerByte * this.numberOfElements;
	}

	public int getNumberOfBitsPerByte() {
		return numberOfBitsPerByte;
	}
}
