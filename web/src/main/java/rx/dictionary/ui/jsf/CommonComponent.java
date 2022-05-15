package rx.dictionary.ui.jsf;

import static java.util.stream.Collectors.toMap;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import javax.faces.context.FacesContext;

public class CommonComponent {
	private CommonComponent() {}
	public static final Map<String,String> FRONTEND_LANGUAGE_OPTIONS = getFrontendLanguageOptions();
	private static Map<String,String> getFrontendLanguageOptions() {
		Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(supportedLocales, Spliterator.ORDERED), false)
				.map(Language::new)
				.collect(toMap(Language::label, Language::value));
	}
}
