package ruixue.mydictionary.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ruixue.mydictionary.dto.WordDTO;
import ruixue.mydictionary.entity.Word;
import ruixue.mydictionary.service.WordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class WordController extends CommonController<Word,WordDTO> {
	@Autowired
	private WordService wordService;
	
//	@RequestMapping(value={"/words"}, method = RequestMethod.GET)
//	public ResponseEntity<List<Word>> getAllWords() {
//		return new ResponseEntity<>(wordService.getAllWords(), HttpStatus.OK);
//	}
	
	@RequestMapping(value={"/{lang}/words/{word}"}, method = RequestMethod.DELETE)
	public ResponseEntity<Word> deleteWord(@PathVariable("lang") String languageCode,
	@PathVariable("word") String wordStr) {
		System.out.println("DEBUGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
		return getEntity(wordService.deleteWord(languageCode, wordStr));
	}
	
}
