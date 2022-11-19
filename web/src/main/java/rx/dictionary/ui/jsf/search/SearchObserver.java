package rx.dictionary.ui.jsf.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import rx.dictionary.SearchKeyword;
@SessionScoped
public class SearchObserver implements Serializable {
    private List<SearchedItemValueDTO> searchedLexicalItemValues;
    @PostConstruct
    public void initSearchedItems() {
    	searchedLexicalItemValues = new ArrayList<>();	
    }
    
    public void onSearch(@Observes(notifyObserver = Reception.IF_EXISTS) final SearchKeyword searchedLexicalItem) {
    	searchedLexicalItemValues.add(new SearchedItemValueDTO(searchedLexicalItem));
    }
    
    @Produces
    @Named
    public List<SearchedItemValueDTO> getSearchedLexicalItems() {
		return searchedLexicalItemValues;
	}

}
