package rx.dictionary.ui.jsf;

import java.util.Locale;
import java.util.Map;

public abstract class InputComponent {
	private String word;
	protected Locale searchLanguage;
	protected Locale explainLanguage;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}


//	public Locale getToLanguage() {
//		if (explainLanguage == null)
//			explainLanguage = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
//		return explainLanguage;
//	}
//
//	public void setToLanguage(Locale toLanguage) {
//		this.explainLanguage = toLanguage;
//	}
}
