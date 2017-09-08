package ruixue.mydictionary.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruixue.mydictionary.entity.Word;
//@Service
public class FactoryWordService {
//	@Autowired
//	private WordServiceENImpl wordServiceEN;
//	@Autowired
//	private WordServiceFIImpl wordServiceFI;
//	private WordService getService(Optional<String> language) {
//		switch(language.isPresent() ? 
//				language.get():Locale.getDefault().getLanguage()) {
//			case "en":
//				return wordServiceEN;
//			case "fi":
//				return wordServiceFI;
//			default:
//				return wordServiceEN;
//		}	 
//	}
//	public List<? extends Word> getAllWords(Optional<String> language) {
//		return getService(language).getAllWords();
//	}

}
