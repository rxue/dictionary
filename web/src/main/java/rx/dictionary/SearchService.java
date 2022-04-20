package rx.dictionary;

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;


import javax.inject.Inject;

public class SearchService {
	@Inject
	private DefinitionRepository definitionRepo;
	public Map<PartOfSpeech,List<String>> search(EntryValue entryValue, Language toLanguage) {
		return definitionRepo.find(entryValue, toLanguage).stream()
				.collect(groupingBy(d -> d.getEntry().getPoS(), mapping(Definition::getDefinition, toList())));
	}
}