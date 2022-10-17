package rx.dictionary.ui.jsf.search;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.ExplanationRepository;
import rx.dictionary.SearchKeyword;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.LexicalItem;
import rx.dictionary.ui.jsf.InputComponent;

@ViewScoped
@Named
public class AjaxSearchComponent extends InputComponent implements Serializable {
	private String keyword;
	private List<String> matchedResults;
	private List<String> resultCandidates;
	private String chosenCandidate;
	@Inject
	private ExplanationRepository explanationRepo;
	public void fuzzySearch() {
		System.out.println("::::::::::::::::!!!!!!!!!!!!!!!!!!!!!!!!!!! keyword is " + keyword);
		//NOTE! atm fromLanguage and toLanguage is hard-coded
		SearchKeyword keyword = new SearchKeyword(getKeyword(), Locale.US);
		List<Explanation> explanationCandidates = explanationRepo.find(keyword, Locale.SIMPLIFIED_CHINESE);
		System.out.println("result explanation candidate " + explanationCandidates.size());
		resultCandidates = explanationCandidates.stream()
				.map(Explanation::getLexicalItem)
				.map(LexicalItem::getValue)
				.distinct()
				.collect(Collectors.toList());
	}
	public void exactSearch() {
		System.out.println("select matached search result::::::::::::::" + chosenCandidate);
	}
	public boolean hasResultCandidates() {
		return resultCandidates != null && !resultCandidates.isEmpty();
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getChosenCandidate() {
		return chosenCandidate;
	}
	public void setChosenCandidate(String chosenCandidate) {
		this.chosenCandidate = chosenCandidate;
	}
	public List<String> getResultCandidates() {
		return resultCandidates;
	}
	public void setResultCandidates(List<String> resultCandidates) {
		this.resultCandidates = resultCandidates;
	}
	public List<String> getMatchedResults() {
		return matchedResults;
	}
	public void setMatchedResults(List<String> matchedResults) {
		this.matchedResults = matchedResults;
	}
	
	public int candidateSize() {
		return 3;
	}
}
