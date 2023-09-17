package rx.dictionary.ui.jsf;

import java.util.Locale;

public abstract class InputComponent {
	private String word;
	protected Locale language;
	protected Locale explainLanguage;
	public final String getWord() {
		return word;
	}
	public final void setWord(String word) {
		this.word = word;
	}
}
