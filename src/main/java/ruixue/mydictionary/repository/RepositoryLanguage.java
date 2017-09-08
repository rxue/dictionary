package ruixue.mydictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ruixue.mydictionary.entity.Language;
@Repository
public interface RepositoryLanguage 
	extends JpaRepository<Language,Integer>{
	public Language findByLanguage(String language);
	public Language findByLanguageCodeIgnoreCase(String languageCode);
}
