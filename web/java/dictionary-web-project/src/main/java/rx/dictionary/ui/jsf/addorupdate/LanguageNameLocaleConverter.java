package rx.dictionary.ui.jsf.addorupdate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

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
