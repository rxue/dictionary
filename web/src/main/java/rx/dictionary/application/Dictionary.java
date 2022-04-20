package rx.dictionary.application;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import rx.dictionary.EntryValue;
import rx.dictionary.Language;
import rx.dictionary.PartOfSpeech;
import rx.dictionary.SearchService;

@RequestScoped
@Named
public class Dictionary {
	private String keyword;
	private Language fromLanguage;
	private Language toLanguage;
	private SearchService searchService;
	private String result;
	
	@Inject
	public Dictionary(SearchService searchService) {
		this.searchService = searchService;
	}
	public void search() {
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("current phase: " + context.getCurrentPhaseId().getName());
		Map<PartOfSpeech,List<String>> definitions = searchService.search(getEntryValue(), toLanguage);
		System.out.println("Definition: " + definitions.size());
		StringBuilder resultBuilder = new StringBuilder();
		for (PartOfSpeech poS : definitions.keySet()) {
			resultBuilder.append(poS.toString())
				.append(String.join(",", definitions.get(poS)))
				.append(". ");
		}
		result = resultBuilder.toString();
	}
	private EntryValue getEntryValue() {
		EntryValue ev = new EntryValue();
		ev.setEntry(keyword);
		ev.setLanguage(fromLanguage);
		return ev;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}
	public Language getFromLanguage() {
		return fromLanguage;
	}
	public void setFromLanguage(Language toLanguage) {
		this.fromLanguage = toLanguage;
	}
	
	public Language getToLanguage() {
		return toLanguage;
	}
	public void setToLanguage(Language toLanguage) {
		this.toLanguage = toLanguage;
	}
	public boolean getHasImage() {
		return keyword != null;
	}
	public boolean getHasResult() {
		return result != null;
	}
	public String getResult() {
		return result;
	} 

	public Map<String,String> getLanguageMap() {
		return Arrays.stream(Language.values())
				.collect(toMap(Language::toString, Language::name, (v1,v2)-> {throw new UnsupportedOperationException();}));
	}
	
}
