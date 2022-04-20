package rx.dictionary;

public enum Language {
	EN("English"), FI("Suomi"), CN("中文");
	private final String value;
	private Language(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return value;
	}
}
