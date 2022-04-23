package rx.dictionary;

public enum PartOfSpeech {
	ADJ, N, VI, VT, ADV;
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
