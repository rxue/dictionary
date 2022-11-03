package rx.dictionary.ui.jsf.search;

import rx.dictionary.SearchKeyword;

public class ItemValueDTO {
	private final SearchKeyword itemValue;

	public ItemValueDTO(SearchKeyword itemValue) {
		this.itemValue = itemValue;
	}
	@Override
	public String toString() {
		return itemValue.getLanguage() 
				+ " : " +  itemValue.getValue();
	}
	

}
