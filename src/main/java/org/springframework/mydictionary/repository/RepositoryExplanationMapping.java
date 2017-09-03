package org.springframework.mydictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mydictionary.entity.ExplanationMapping;
import org.springframework.mydictionary.entity.Language;
import org.springframework.mydictionary.entity.Word;
import org.springframework.stereotype.Repository;
@Repository
public interface RepositoryExplanationMapping 
	extends JpaRepository<ExplanationMapping,Integer>{
	public ExplanationMapping findByWordAndExplanationLanguage(Word word, Language explanationLanguage);
}
