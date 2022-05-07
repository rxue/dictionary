package rx.dictionary.application;

import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

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
