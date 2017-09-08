package ruixue.mydictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ruixue.mydictionary.entity.ExplanationMapping;
import ruixue.mydictionary.entity.Language;
import ruixue.mydictionary.entity.Word;
@Repository
public interface RepositoryExplanationMapping 
	extends JpaRepository<ExplanationMapping,Integer>{
	public ExplanationMapping findByWordAndExplanationLanguage(Word word, Language explanationLanguage);
}
