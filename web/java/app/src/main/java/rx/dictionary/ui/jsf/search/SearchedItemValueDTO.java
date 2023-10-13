package rx.dictionary.ui.jsf.search;

import rx.dictionary.vo.LexicalItemVO;

public class SearchedItemValueDTO {
	private final LexicalItemVO itemValue;

	public SearchedItemValueDTO(LexicalItemVO itemValue) {
		this.itemValue = itemValue;
	}
	@Override
	public String toString() {
		return itemValue.language()
				+ " : " +  itemValue.value();
	}
	

}
