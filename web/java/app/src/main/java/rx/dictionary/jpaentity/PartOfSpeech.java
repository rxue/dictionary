package rx.dictionary.jpaentity;

public enum PartOfSpeech {
	ADJ, N, VI, VT, ADV, PRON;
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
