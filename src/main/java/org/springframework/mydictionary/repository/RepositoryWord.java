package org.springframework.mydictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mydictionary.entity.Language;
import org.springframework.mydictionary.entity.Word;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryWord 
	extends JpaRepository<Word,Integer>{
	
	public Word findByLanguageAndWord(Language language, String word);

}
