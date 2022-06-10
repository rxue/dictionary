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
public class SearchObserver {
    private List<ItemValue> searchedLexicalItemValues;
    
    public void onSearch(@Observes(notifyObserver = Reception.IF_EXISTS) final ItemValue searchedLexicalItem) {
    	if (searchedLexicalItemValues == null)
    		searchedLexicalItemValues = new ArrayList<>();
    	searchedLexicalItemValues.add(searchedLexicalItem);
    }
    
    @Produces
    @Named
    public List<ItemValue> getSearchedLexicalItems() {
    	if (searchedLexicalItemValues == null)
    		searchedLexicalItemValues = new ArrayList<>();
		return searchedLexicalItemValues;
	}

}
