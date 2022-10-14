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
	private List<String> matchedResultsTest = Arrays.asList("first","fiiit");
	public void fuzzySearch() {
		System.out.println("::::::::::::::::!!!!!!!!!!!!!!!!!!!!!!!!!!! keyword is " + keyword);
		matchedResults = Arrays.asList("xxx","yyy","zzz","KKK","aaa");
	}
	public void search() {
		System.out.println("real search::::::::::::::");
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
	
	public List<String> getMatchedResultsTest() {
		return matchedResultsTest;
	}
	public void setMatchedResultsTest(List<String> matchedResultsTest) {
		this.matchedResultsTest = matchedResultsTest;
	}
	public int candidateSize() {
		return 3;
	}
}
