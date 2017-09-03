package org.springframework.mydictionary.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mydictionary.dto.DictionaryEntryDTO;
import org.springframework.mydictionary.service.ExplanationMappingService;

@RestController
public class DictionaryEntryController extends AbstractDictionaryEntryController {
	@Autowired
	private ExplanationMappingService service;
	
	@RequestMapping(value={"/{lan}/{explainedLan}/{word}"})
	public ResponseEntity<DictionaryEntryDTO> getAllExplanation(
			@PathVariable("lan") String languageCode,
			@PathVariable("explainedLan") String explainedLanguageCode,
			@PathVariable("word") String word) {
		return super.getDictionaryEntryDTO(service.getExplanationMappingByWordAndLanguageCode(languageCode, word, explainedLanguageCode));
	}
}
