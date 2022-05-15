package rx.dictionary;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import rx.dictionary.jpaentity.Definition;
import rx.dictionary.jpaentity.EntryValue;

public class DefinitionRepository {
	@Inject
	private EntityManager em;
	/**
	 * Given a word and language, find the list of entries, which match EXACTLY
	 * 
	 * 
	 * @param word 
	 * @param lang
	 * @return list of entries matching exactly. NOTE! Return type is list due to the fact that a word could have several parts of speech
	 */
	public List<Definition> find(EntryValue entryValue, Locale toLang) {
		return em.createQuery("SELECT d FROM Definition d WHERE d.entry.value = :entryValue and d.language = :language", Definition.class)
				.setParameter("entryValue", entryValue)
				.setParameter("language", toLang)
				.getResultList();
	} 

	public void add(List<Definition> definitions) {
		System.out.println(definitions.size() + " definitions:" + definitions);
		for (Definition definition : definitions) {
			System.out.println("ADD definition: " + definition);
			em.merge(definition);
		}
	}
}
