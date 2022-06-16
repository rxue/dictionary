package rx.dictionary.ui.jsf.search;

import rx.dictionary.jpaentity.ItemValue;
import rx.dictionary.ui.jsf.CommonComponent;

public class ItemValueDTO {
	private final ItemValue itemValue;

	public ItemValueDTO(ItemValue itemValue) {
		this.itemValue = itemValue;
	}
	@Override
	public String toString() {
		return CommonComponent.getLocaleToLanguage().get(itemValue.getLanguage()) 
				+ " : " +  itemValue.getValue();
	}
	

}
