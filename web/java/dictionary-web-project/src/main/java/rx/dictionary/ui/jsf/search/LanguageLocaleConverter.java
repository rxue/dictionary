package rx.dictionary.ui.jsf.search;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Named;

import java.util.Locale;

public class LanguageLocaleConverter implements Converter<Locale> {
    @Override
    public Locale getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.out.println(":::::::::::::::::::::::::convert language string to locale");
        return Locale.forLanguageTag(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Locale locale) {
        return null;
    }
}
