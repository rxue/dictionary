package ruixue.mydictionary.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import ruixue.mydictionary.dto.DictionaryEntryDTO;
import ruixue.mydictionary.entity.ExplanationMapping;

@RequestMapping("/rest")
public abstract class AbstractDictionaryEntryController {
	
	protected ResponseEntity<DictionaryEntryDTO> getDictionaryEntryDTO(ExplanationMapping explanationMapping) {
		return Optional.ofNullable(explanationMapping)
			.map(e -> new ResponseEntity<>(new DictionaryEntryDTO(e), HttpStatus.OK))
			.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	public ResponseEntity<List<DictionaryEntryDTO>> getDictionaryEntryDTOList(List<ExplanationMapping> explanationMappings) {
		return Optional.ofNullable(explanationMappings.stream()
				.map(DictionaryEntryDTO::new).collect(Collectors.toList()))
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
}
