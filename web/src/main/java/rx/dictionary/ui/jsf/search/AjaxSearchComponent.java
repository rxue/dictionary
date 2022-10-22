package rx.dictionary.ui.jsf.search;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
	@SuppressWarnings("unchecked")
	private Map<String,SearchResult> resultCandidates = Collections.EMPTY_MAP;
	@Inject
	private SearchService searchService;
	private SearchResult searchResult = null;
	public void searchCandidates() {
		//NOTE! atm fromLanguage and toLanguage is hard-coded
		SearchKeyword keyword = new SearchKeyword(getKeyword(), Locale.US);
		resultCandidates = searchService.searchCandidates(keyword, Locale.SIMPLIFIED_CHINESE);
	}
	public void navigate(AjaxBehaviorEvent evt) throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		String viewId = fc.getViewRoot().getViewId();
		ExternalContext ec = fc.getExternalContext();
		ec.redirect(ec.getApplicationContextPath() + viewId + "?keyword=" + getKeyword());
	}
	/**
	 * Action
	 */
	public void search() {
		String keywordValue = getKeyword();
		if (keywordValue != null) {
			searchResult = resultCandidates.get(keywordValue);
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
	public boolean showResult() {
		return getKeyword() != null;
	}
}
