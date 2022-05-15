package rx.dictionary.application;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.DictionaryService;
import rx.dictionary.ui.jsf.SearchComponent;

@RequestScoped
@Named
public class Search {
	private DictionaryService searchService;
	@Inject
	private SearchComponent searchComponent;
	@Inject
	private SearchResult result;
	@Inject
	public Search(DictionaryService searchService) {
		this.searchService = searchService;
	}
	public void action() {
		FacesContext context = FacesContext.getCurrentInstance();
		System.out.println("current phase: " + context.getCurrentPhaseId().getName());
		result.setAsPresent();
		SearchCriteria searchCriteria = new SearchCriteria.Builder()
				.setFromLanguage(searchComponent.getFromLanguage())
				.setKeyword(searchComponent.getKeyword())
				.setToLanguage(searchComponent.getToLanguage())
				.build();
		result.addAll(searchService.search(searchCriteria.getEntryValue(), searchCriteria.getToLanguage()));
	}
	
}
