package rx.dictionary.application;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import rx.dictionary.Language;
@RequestScoped
@Named
public class SearchUIComponent {
	public Map<String,String> getLanguageMap() {
		return Arrays.stream(Language.values())
				.collect(toMap(Language::toString, Language::name, (v1,v2)-> {throw new UnsupportedOperationException();}));
	}

}
