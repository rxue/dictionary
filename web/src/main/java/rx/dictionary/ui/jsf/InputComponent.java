package rx.dictionary.ui.jsf;

import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;

public abstract class InputComponent {
	private String keyword;
	protected Locale searchLanguage;
	protected Locale explainLanguage;
	public Map<String,String> getLanguageMap() {
		return CommonComponent.FRONTEND_LANGUAGE_OPTIONS;
	}

	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String word) {
		this.keyword = word;
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
