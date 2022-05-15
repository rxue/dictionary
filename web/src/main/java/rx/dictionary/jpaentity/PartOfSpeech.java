package rx.dictionary.jpaentity;

public enum PartOfSpeech {
	ADJ, N, VI, VT, ADV;
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
