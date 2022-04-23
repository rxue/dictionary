package rx.dictionary.application;

import java.util.Arrays;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.Language;
import rx.dictionary.SearchService;

@RequestScoped
@Named
public class Search {
	private SearchService searchService;
	@Inject
	private SearchCriteria searchCriteria;
	@Inject
	private SearchResult result;
	@Inject
	public Search(SearchService searchService) {
		this.searchService = searchService;
	}
	public void search() {
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("current phase: " + context.getCurrentPhaseId().getName());
		result.addAll(searchService.search(searchCriteria.getEntryValue(), searchCriteria.getToLanguage()));
	}

	public Map<String,String> getLanguageMap() {
		return Arrays.stream(Language.values())
				.collect(toMap(Language::toString, Language::name, (v1,v2)-> {throw new UnsupportedOperationException();}));
	}
	
}
