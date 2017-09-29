package ruixue.mydictionary.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ruixue.mydictionary.dto.DictionaryEntryDTO;
import ruixue.mydictionary.entity.Explanation;
import ruixue.mydictionary.entity.Word;
import ruixue.mydictionary.service.ExplanationMappingService;
import ruixue.mydictionary.entity.ExplanationMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/words")
public class DictionaryEntryController extends CommonController<ExplanationMapping,DictionaryEntryDTO> {
	@Autowired
	private ExplanationMappingService service;
	
	{
		super.dtoConverter = explanationMapping -> {
			Word w = explanationMapping.getWord();
			String language = w.getLanguage().getLanguage();
			String wordString = w.getWord();
			String explanationLanguage = explanationMapping
					.getExplanationLanguage().getLanguage();
			Map<String, List<String>> explanations = explanationMapping.getExplanations().stream().collect(
					Collectors.groupingBy(
							e -> e.getPartOfSpeech().getPartOfSpeech(), 
							Collectors.mapping(Explanation::getExplanation, Collectors.toList())
					));
			return new DictionaryEntryDTO(language, wordString, explanationLanguage, explanations);
		};
	}
	
	@RequestMapping(value={"/{lan}/{explainedLan}/{word}"})
	public ResponseEntity<DictionaryEntryDTO> getAllExplanation(
			@PathVariable("lan") String languageCode,
			@PathVariable("explainedLan") String explainedLanguageCode,
			@PathVariable("word") String word) {
		return super.getDTO(service.getExplanationMappingByWordAndLanguageCode(languageCode, word, explainedLanguageCode));
	}
}
