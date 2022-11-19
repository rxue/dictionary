package rx.dictionary.ui.jsf.search;

import rx.dictionary.SearchKeyword;

public class SearchedItemValueDTO {
	private final SearchKeyword itemValue;

	public SearchedItemValueDTO(SearchKeyword itemValue) {
		this.itemValue = itemValue;
	}
	@Override
	public String toString() {
		return itemValue.getLanguage() 
				+ " : " +  itemValue.getValue();
	}
	

}
