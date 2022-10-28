package rx.dictionary.ui.jsf.search;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.faces.context.ExternalContext;
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
	private String fromLanguageStr;
	public void searchCandidates() {
		//NOTE! atm fromLanguage and toLanguage is hard-coded
		SearchKeyword keyword = new SearchKeyword(getKeyword(), Locale.US);
		resultCandidates = searchService.searchCandidates(keyword, Locale.SIMPLIFIED_CHINESE);
	}
	
	public void redirectToSearch() throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		String viewId = fc.getViewRoot().getViewId();
		ExternalContext ec = fc.getExternalContext();
		Path redirectPath = Paths.get(ec.getApplicationContextPath(), fromLanguageStr, "cn", viewId);
		ec.redirect(redirectPath + "?keyword=" + getKeyword());
	}
	/**
	 * Pre-render view Action
	 */
	public void search() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		String keywordValue = getKeyword();
		if (keywordValue != null) {
			if (fromLanguageStr == null)
				fromLanguageStr = getFromLanguage(ec).orElse("");
			SearchKeyword keyword = new SearchKeyword(keywordValue, Locale.US);
			searchResult = searchService.search(keyword, Locale.SIMPLIFIED_CHINESE);
		}
	}
	private static Optional<String> getFromLanguage(ExternalContext ec) {
		Object forwardServletPathStr = ec.getRequestMap().get("javax.servlet.forward.servlet_path");
		if (forwardServletPathStr != null) {
			return Optional.of(Paths.get(""+forwardServletPathStr).getName(0).toString());
		}
		return Optional.empty();
	}
	
	public boolean hasResultCandidates() {
		return !resultCandidates.isEmpty();
	}
	public Set<String> getResultCandidateWords() {
		return resultCandidates.keySet();
	}
	
	public String getFromLanguageStr() {
		return fromLanguageStr;
	}

	public void setFromLanguageStr(String fromLanguageStr) {
		this.fromLanguageStr = fromLanguageStr;
	}

	public SearchResult getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}
}
