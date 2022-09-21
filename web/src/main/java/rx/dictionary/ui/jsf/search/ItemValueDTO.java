package rx.dictionary.ui.jsf.search;

import rx.dictionary.SearchKeyword;
import rx.dictionary.ui.jsf.CommonComponent;

public class ItemValueDTO {
	private final SearchKeyword itemValue;

	public ItemValueDTO(SearchKeyword itemValue) {
		this.itemValue = itemValue;
	}
	@Override
	public String toString() {
		return CommonComponent.getLocaleToLanguage().get(itemValue.getLanguage()) 
				+ " : " +  itemValue.getValue();
	}
	

}
