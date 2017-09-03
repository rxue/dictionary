package org.springframework.mydictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mydictionary.entity.Language;
import org.springframework.stereotype.Repository;
@Repository
public interface RepositoryLanguage 
	extends JpaRepository<Language,Integer>{
	public Language findByLanguage(String language);
	public Language findByLanguageCodeIgnoreCase(String languageCode);
}
