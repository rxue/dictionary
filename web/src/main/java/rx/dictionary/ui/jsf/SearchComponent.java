package rx.dictionary.ui.jsf;

import java.util.Locale;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class SearchComponent {
	private Locale fromLanguage;
	private String keyword;
	private Locale toLanguage;
	public Map<String,String> getLanguageMap() {
		return CommonComponent.FRONTEND_LANGUAGE_OPTIONS;
	}
	public Locale getFromLanguage() {
		if (fromLanguage == null)
			fromLanguage = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		return fromLanguage;
	}

	public void setFromLanguage(Locale fromLanguage) {
		this.fromLanguage = fromLanguage;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Locale getToLanguage() {
		if (toLanguage == null)
			toLanguage = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		return toLanguage;
	}

	public void setToLanguage(Locale toLanguage) {
		this.toLanguage = toLanguage;
	}
}
