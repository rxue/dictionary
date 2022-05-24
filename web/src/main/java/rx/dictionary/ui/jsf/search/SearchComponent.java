package rx.dictionary.ui.jsf.search;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.DefinitionRepository;
import rx.dictionary.jpaentity.Definition;
import rx.dictionary.jpaentity.EntryValue;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.CommonComponent;
import rx.dictionary.ui.jsf.InputComponent;

@RequestScoped
@Named
public class SearchComponent extends InputComponent {
	@Inject
	private DefinitionRepository definitionRepo;
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
		EntryValue entryVal = new EntryValue();
		entryVal.setLanguage(getFromLanguage());
		entryVal.setEntry(getWord());
		Map<PartOfSpeech,List<String>> rawResult = definitionRepo.find(entryVal, getToLanguage()).stream()
				.collect(groupingBy(d -> d.getEntry().getPoS(), mapping(Definition::getDefinition, toList())));
		searchResult = SearchResult.newWithAction();
		if (rawResult.size() > 0) 
			rawResult.entrySet().forEach(e -> searchResult.add(new DefinitionItemViewDTO(e.getKey(), e.getValue())));
	}
}
