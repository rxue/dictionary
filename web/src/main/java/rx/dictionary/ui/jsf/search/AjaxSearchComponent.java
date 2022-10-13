package rx.dictionary.ui.jsf.search;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class AjaxSearchComponent {
	private String keyword;
	private List<String> matchedResults;
	public void fuzzySearch() {
		System.out.println("::::::::::::::::!!!!!!!!!!!!!!!!!!!!!!!!!!! keyword is " + keyword);
		matchedResults = Arrays.asList("x","y","z","K","a");
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
