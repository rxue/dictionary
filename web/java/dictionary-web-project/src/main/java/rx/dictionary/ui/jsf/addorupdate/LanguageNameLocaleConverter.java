package rx.dictionary.ui.jsf.addorupdate;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@FacesConverter("languageNameLocaleConverter")
public class LanguageNameLocaleConverter implements Converter {
	private static final Map<Locale,String> LOCALE_TO_LANGUAGE_NAME;
	static {
		LOCALE_TO_LANGUAGE_NAME = new HashMap<>();
		LOCALE_TO_LANGUAGE_NAME.put(Locale.ENGLISH, "English");
		LOCALE_TO_LANGUAGE_NAME.put(Locale.SIMPLIFIED_CHINESE, "中文");
	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Locale languageLocale = (Locale) value;
		return LOCALE_TO_LANGUAGE_NAME.get(languageLocale);
	}

}
