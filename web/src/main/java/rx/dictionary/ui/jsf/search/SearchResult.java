package rx.dictionary.ui.jsf.search;

import java.util.ArrayList;

import rx.dictionary.ui.ExplanationItemViewDTO;

@SuppressWarnings("serial")
public class SearchResult extends ArrayList<ExplanationItemViewDTO> {
	private final boolean isActioned;
	private SearchResult(boolean isActioned) {
		this.isActioned = isActioned;
	}
	public boolean isActionedAndNotEmpty() {
		return isActioned && size() > 0;
	}
	public boolean isActionedAndEmpty() {
		return isActioned && size() == 0;
	}
	public static SearchResult newWithAction() {
		return new SearchResult(true);
	}
	public static SearchResult newWithoutAction() {
		return new SearchResult(false);
	}
}
