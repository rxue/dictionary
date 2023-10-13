package rx.dictionary.ui.jsf.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Reception;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import rx.dictionary.vo.LexicalItemVO;
@SessionScoped
public class SearchObserver implements Serializable {
    private List<SearchedItemValueDTO> searchedLexicalItemValues;
    @PostConstruct
    public void initSearchedItems() {
    	searchedLexicalItemValues = new ArrayList<>();	
    }
    
    public void onSearch(@Observes(notifyObserver = Reception.IF_EXISTS) final LexicalItemVO searchedLexicalItem) {
        System.out.println("FIRST OBSERVER: observed keyword: " + searchedLexicalItem.value());
    	searchedLexicalItemValues.add(new SearchedItemValueDTO(searchedLexicalItem));
    }
    
    @Produces
    @Named
    public List<SearchedItemValueDTO> getSearchedLexicalItems() {
		return searchedLexicalItemValues;
	}

}
