package rx.dictionary.ui.jsf.search;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	private SearchResult searchResult = null;
	public AjaxSearchComponent() {
		Path forwardPath = Paths.get("" + FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.forward.servlet_path"));
		super.searchLanguage = Locale.forLanguageTag(forwardPath.getName(0).toString());
		super.explainLanguage = Locale.forLanguageTag(forwardPath.getName(1).toString());
	}
	public void searchCandidates() {
		SearchKeyword keyword = new SearchKeyword(getWord(), super.searchLanguage);
		resultCandidates = searchService.searchCandidates(keyword, super.explainLanguage);
	}
	
	public void redirectToSearch() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		System.out.println("JSF phase is " + facesContext.getCurrentPhaseId());
		String redirectPath = new StringBuilder(facesContext.getExternalContext().getApplicationContextPath())
					.append("/" + super.searchLanguage.toLanguageTag())
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
		String keywordValue = getWord();
		if (keywordValue != null) {
			SearchKeyword keyword = new SearchKeyword(keywordValue, searchLanguage);
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
}
