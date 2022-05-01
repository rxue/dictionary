package rx.dictionary.jsfuicomponent;

import static java.util.stream.Collectors.toMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@RequestScoped
@Named
public class SearchUIComponent {
	public Map<String,String> getLanguageMap() {
		Locale browserLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		System.out.println("browser locale: " + browserLocale.getISO3Country());
		return getSupportedLanguageMap();
	}
	
	public Map<String,String> getSupportedLanguageMap() {
		Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(supportedLocales, Spliterator.ORDERED), false)
				.collect(toMap(Locale::getDisplayLanguage, locale ->String.join("_", locale.getLanguage(), locale.getCountry()), 
						(v1,v2)-> {throw new UnsupportedOperationException();}, LinkedHashMap::new));
	}

}
