package rx.dictionary.ui.jsf;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

final class Language {
	private final Locale locale;
	private static final Map<Locale,String> LOCALE_TO_LANGUAGE;
	static {
		LOCALE_TO_LANGUAGE = new HashMap<>();
		LOCALE_TO_LANGUAGE.put(Locale.TAIWAN, "繁體中文");
		LOCALE_TO_LANGUAGE.put(Locale.CHINA, "简体中文");
		LOCALE_TO_LANGUAGE.put(Locale.US, "English");
		LOCALE_TO_LANGUAGE.put(new Locale("fi"), "Suomi");
		LOCALE_TO_LANGUAGE.put(Locale.FRENCH, "français");
	}
	Language(Locale locale) {
		this.locale = locale;
	}
	final String value() {
		return locale.toLanguageTag();
	}
	/**
	 * TODO: give corresponding error message in case there is no language with the giben locale (NPE)
	 * @return
	 */
	final String label() {
		String label = LOCALE_TO_LANGUAGE.get(locale);
		if (label == null)
			throw new MissingOptionLabelException(locale);
		return label;
	}
	final Locale locale() {
		return locale;
	}
}
