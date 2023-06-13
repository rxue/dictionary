package rx.dictionary.ui.jsf;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.util.Locale;


@FacesConverter("languageLocaleConverter")
public class LanguageLocaleConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String[] splitted = value.split("-");
		return splitted.length == 2 ? new Locale(splitted[0], splitted[1]) : new Locale(splitted[0]);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return (String) value;
	}

}
