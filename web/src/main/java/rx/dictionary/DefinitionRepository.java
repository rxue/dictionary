package rx.dictionary;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import rx.dictionary.jpaentity.Definition;
import rx.dictionary.jpaentity.EntryValue;

public class DefinitionRepository implements Serializable {
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
	@Transactional
	public void add(List<Definition> definitions) {
		System.out.println(definitions.size() + " definitions:" + definitions);
		for (Definition definition : definitions) {
			System.out.println("ADD definition: " + definition);
			em.merge(definition);
		}
	}
	@Transactional
	public void update(List<Definition> updatedDefinitions) {
		for (Definition updatedDef : updatedDefinitions) {
			em.merge(updatedDef);
		}
	}
}
