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
	private DictionaryService service;
	@Inject
	private SearchComponent searchComponent;
	@Inject
	private SearchResult result;
	@Inject
	public Search(DictionaryService searchService) {
		this.service = searchService;
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
		result.addAll(service.search(searchCriteria.getEntryValue(), searchCriteria.getToLanguage()));
	}
	public void search(SearchCriteria searchCriteria) {
		result.addAll(service.search(searchCriteria.getEntryValue(), searchCriteria.getToLanguage()));
	}
	
}
