package ruixue.mydictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ruixue.mydictionary.entity.Language;
import ruixue.mydictionary.entity.Word;

@Repository
public interface RepositoryWord 
	extends JpaRepository<Word,Integer>{
	
	public Word findByLanguageAndWord(Language language, String word);

}
