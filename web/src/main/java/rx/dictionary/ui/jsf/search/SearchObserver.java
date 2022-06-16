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

import rx.dictionary.jpaentity.ItemValue;
@SessionScoped
public class SearchObserver implements Serializable {
    private List<ItemValueDTO> searchedLexicalItemValues;
    @PostConstruct
    public void initSearchedItems() {
    	searchedLexicalItemValues = new ArrayList<>();	
    }
    
    public void onSearch(@Observes(notifyObserver = Reception.IF_EXISTS) final ItemValue searchedLexicalItem) {
    	searchedLexicalItemValues.add(new ItemValueDTO(searchedLexicalItem));
    }
    
    @Produces
    @Named
    public List<ItemValueDTO> getSearchedLexicalItems() {
		return searchedLexicalItemValues;
	}

}
