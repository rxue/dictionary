package rx.dictionary.ui.jsf.search;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import jakarta.enterprise.event.Event;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import rx.dictionary.SearchKeyword;
import rx.dictionary.ui.SearchResult;
import rx.dictionary.ui.SearchService;
import rx.dictionary.ui.jsf.InputComponent;

@ViewScoped
@Named
public class AjaxSearchComponent extends InputComponent implements Serializable {
	@Inject
	private SearchService searchService;
	@SuppressWarnings("unchecked")
	private Map<String,SearchResult> resultCandidates = Collections.EMPTY_MAP;
	@Inject
	private Event<SearchKeyword> searchEvent;
	private SearchResult searchResult = null;
	public AjaxSearchComponent() {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath = externalContext.getRequestServletPath();
		System.out.println("::::::::::::::::::::::request path is " + requestPath);
		//super.language = Locale.forLanguageTag("en");
		super.explainLanguage = Locale.SIMPLIFIED_CHINESE;
	}

	public void searchCandidates() {
		SearchKeyword keyword = new SearchKeyword(getWord(), super.language);
		resultCandidates = searchService.searchCandidates(keyword, super.explainLanguage);
	}
	
	public void redirectToSearch() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		System.out.println("JSF phase is " + facesContext.getCurrentPhaseId());
		String redirectPath = new StringBuilder(facesContext.getExternalContext().getApplicationContextPath())
					.append("/" + super.language.toLanguageTag())
					.append("/" + super.explainLanguage.toLanguageTag())
					.append(facesContext.getViewRoot().getViewId())
					.append("?word=" + getWord())
					.toString();
		facesContext.getExternalContext().redirect(redirectPath);
	}
	/**
	 * Pre-render view Action
	 */
	public void search() {
		System.out.println("::::::::::::::::::::::::::::::::::::::language is " + language);
		String keywordValue = getWord();
		if (keywordValue != null) {
			SearchKeyword keyword = new SearchKeyword(keywordValue, language);
			searchEvent.fire(keyword);
			searchResult = searchService.search(keyword, explainLanguage);
		}
	}
	
	public boolean hasResultCandidates() {
		return !resultCandidates.isEmpty();
	}
	public Set<String> getResultCandidateWords() {
		return resultCandidates.keySet();
	}

	public SearchResult getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}
	public void setLanguage(Locale language) {
		System.out.println("===============set language locale to " + language);
		super.language = language;
	}
	public Locale getLanguage() {
		return super.language;
	}
}
