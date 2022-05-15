package rx.dictionary.ui.jsf;

import static java.util.stream.Collectors.toMap;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import rx.dictionary.jpaentity.Definition;
import rx.dictionary.jpaentity.EntryValue;

@RequestScoped
@Named
public class SearchComponent {
	private static Map<Locale,String> supportedLocaleToLanguage;
	private Locale fromLanguage;
	private String keyword;
	private Locale toLanguage;
	public Map<String,String> getLanguageMap() {
		Locale browserLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		Map<String,String> orderedSupportedLanguageMap = new LinkedHashMap<>();
		String labelToRemove = getSupportedLocaleToLanguageString().get(browserLocale);
		orderedSupportedLanguageMap.put(labelToRemove, CommonComponent.FRONTEND_LANGUAGE_OPTIONS.get(labelToRemove));
		for (String key : CommonComponent.FRONTEND_LANGUAGE_OPTIONS.keySet()) {
			if (!orderedSupportedLanguageMap.containsKey(key)) {
				orderedSupportedLanguageMap.put(key, CommonComponent.FRONTEND_LANGUAGE_OPTIONS.get(key));
			}
		}
		return orderedSupportedLanguageMap;
	}
	
	private static Map<Locale,String> getSupportedLocaleToLanguageString() {
		if (supportedLocaleToLanguage == null) {
			Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
			supportedLocaleToLanguage = StreamSupport.stream(Spliterators.spliteratorUnknownSize(supportedLocales, Spliterator.ORDERED), false)
				.map(Language::new)
				.collect(toMap(Language::locale, Language::label));
		}
		return supportedLocaleToLanguage;
	}

	public Locale getFromLanguage() {
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
		return toLanguage;
	}

	public void setToLanguage(Locale toLanguage) {
		this.toLanguage = toLanguage;
	}

}
