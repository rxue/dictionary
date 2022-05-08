package rx.dictionary.ui.jsf;

import java.util.Locale;

class MissingOptionLabelException extends RuntimeException {
	public MissingOptionLabelException(Locale locale) {
		super("Missing option label from locale " + locale.toLanguageTag());
	}
}
