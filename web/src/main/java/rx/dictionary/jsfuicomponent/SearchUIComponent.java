package rx.dictionary.jsfuicomponent;

import static java.util.stream.Collectors.toMap;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class SearchUIComponent {
	private static Map<String,String> supportedLanguageMap;
	private static Map<Locale,String> supportedLocaleToLanguage;
	public Map<String,String> getLanguageMap() {
		Locale browserLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		Map<String,String> orderedSupportedLanguageMap = new LinkedHashMap<>();
		String labelToRemove = getSupportedLocaleToLanguageString().get(browserLocale);
		orderedSupportedLanguageMap.put(labelToRemove, getSupportedLanguageMap().get(labelToRemove));
		for (String key : supportedLanguageMap.keySet()) {
			if (!orderedSupportedLanguageMap.containsKey(key)) {
				orderedSupportedLanguageMap.put(key, supportedLanguageMap.get(key));
			}
		}
		return orderedSupportedLanguageMap;
	}
	
	private static Map<String,String> getSupportedLanguageMap() {
		if (supportedLanguageMap == null) {
			Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
			supportedLanguageMap = StreamSupport.stream(Spliterators.spliteratorUnknownSize(supportedLocales, Spliterator.ORDERED), false)
				.map(Language::new)
				.collect(toMap(Language::label, Language::value));
		}
		return supportedLanguageMap;
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

}
