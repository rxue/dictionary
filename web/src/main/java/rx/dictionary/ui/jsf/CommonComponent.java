package rx.dictionary.ui.jsf;

import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import javax.faces.context.FacesContext;

public class CommonComponent {
	public static final Map<String,String> FRONTEND_LANGUAGE_OPTIONS = getFrontendLanguageOptions();
	private static Map<String,String> getFrontendLanguageOptions() {
		Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(supportedLocales, Spliterator.ORDERED), false)
				.map(Language::new)
				.collect(toMap(Language::label, Language::value));
	}
	private static Map<Locale,String> LOCALE_TO_LANGUAGE;
	public static Map<Locale,String> getLocaleToLanguage() {
		if (LOCALE_TO_LANGUAGE == null) {
		Map<Locale,String> result = new HashMap<>();
		result.put(Locale.TAIWAN, "繁體中文");
		result.put(Locale.CHINA, "简体中文");
		result.put(Locale.US, "English");
		result.put(new Locale("fi"), "Suomi");
		result.put(Locale.FRENCH, "français");
		return (LOCALE_TO_LANGUAGE = result);
		}
		return LOCALE_TO_LANGUAGE;
	}
}
