package rx.dictionary.ui.jsf;

import java.util.Locale;


final class Language {
	private final Locale locale;

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
		String label = CommonComponent.getLocaleToLanguage().get(locale);
		if (label == null)
			throw new MissingOptionLabelException(locale);
		return label;
	}
	final Locale locale() {
		return locale;
	}
}
