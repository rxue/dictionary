package rx.dictionary;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import static java.util.stream.Collectors.*;


import javax.inject.Inject;

public class SearchService {
	@Inject
	private DefinitionRepository definitionRepo;
	public List<DefinitionItemViewDTO> search(EntryValue entryValue, Locale toLanguage) {
		Map<PartOfSpeech,List<String>> rawResult = definitionRepo.find(entryValue, toLanguage).stream()
				.collect(groupingBy(d -> d.getEntry().getPoS(), mapping(Definition::getDefinition, toList())));
		return rawResult.entrySet().stream()
				.map(e -> new DefinitionItemViewDTO(e.getKey(), e.getValue()))
				.collect(toList());
	}
}