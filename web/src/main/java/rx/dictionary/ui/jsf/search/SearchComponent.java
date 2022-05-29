package rx.dictionary.ui.jsf.search;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.ExplanationRepository;
import rx.dictionary.jpaentity.ItemValue;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.CommonComponent;
import rx.dictionary.ui.jsf.InputComponent;

@RequestScoped
@Named
public class SearchComponent extends InputComponent {
	@Inject
	private ExplanationRepository definitionRepo;
	private SearchResult searchResult = SearchResult.newWithoutAction();
	public Map<String,String> getLanguageMap() {
		return CommonComponent.FRONTEND_LANGUAGE_OPTIONS;
	}
	
	
	public SearchResult getResult() {
		return searchResult;
	}


	public void setResult(SearchResult searchResult) {
		this.searchResult = searchResult;
	}


	public void action() {
		ItemValue itemVal = new ItemValue();
		itemVal.setLanguage(getFromLanguage());
		itemVal.setValue(getWord());
		Map<PartOfSpeech,List<String>> rawResult = definitionRepo.find(itemVal, getToLanguage()).stream()
				.collect(groupingBy(d -> d.getLexicalItem().getPoS(), mapping(Explanation::getExplanation, toList())));
		searchResult = SearchResult.newWithAction();
		if (rawResult.size() > 0) 
			rawResult.entrySet().forEach(e -> searchResult.add(new DefinitionItemViewDTO(e.getKey(), e.getValue())));
	}
}
