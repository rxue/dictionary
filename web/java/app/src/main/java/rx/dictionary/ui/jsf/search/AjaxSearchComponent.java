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
import rx.dictionary.vo.LexicalItemVO;
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
	private Event<LexicalItemVO> searchEvent;
	private SearchResult searchResult = null;
	public AjaxSearchComponent() {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath = externalContext.getRequestServletPath();
		System.out.println("::::::::::::::::::::::request path is " + requestPath);
	}

	public void searchCandidates() {
		LexicalItemVO keyword = new LexicalItemVO(getWord(), super.language);
		resultCandidates = searchService.searchCandidates(keyword, super.explainLanguage);
	}
	
	public void redirectToSearch() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		System.out.println("JSF phase is " + facesContext.getCurrentPhaseId());
		String redirectPath = new StringBuilder(facesContext.getExternalContext().getApplicationContextPath())
					.append(facesContext.getViewRoot().getViewId())
					.append("?")
					.append("lang=" + getLanguage().toLanguageTag())
					.append("&")
					.append("explain_in_lang=" + getExplainLanguage().toLanguageTag())
					.append("&")
					.append("word=" + getWord())
					.toString();
		facesContext.getExternalContext().redirect(redirectPath);
	}
	/**
	 * Pre-render view Action
	 */
	public void search() {
		String keywordValue = getWord();
		if (keywordValue != null) {
			LexicalItemVO keyword = new LexicalItemVO(keywordValue, language);
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
		super.language = language;
	}
	public Locale getLanguage() {
		return super.language;
	}
	public Locale getExplainLanguage() {
		return super.explainLanguage;
	}

	public void setExplainLanguage(Locale explainLanguage) {
		super.explainLanguage = explainLanguage;
	}

}
