package rx.dictionary;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import rx.dictionary.jpaentity.Explanation;

public class ExplanationRepository implements Serializable {
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
	public List<Explanation> find(SearchKeyword searchKeyword, Locale toLanguage) {
		return em.createQuery("SELECT e FROM Explanation e WHERE e.lexicalItem.language = :fromLanguage and UPPER(e.lexicalItem.value) = UPPER(:value) and e.language = :toLanguage", 
				Explanation.class)
				.setParameter("fromLanguage", searchKeyword.getLanguage())
				.setParameter("value", searchKeyword.getValue())
				.setParameter("toLanguage", toLanguage)
				.getResultList();
	} 
	public void add(List<Explanation> definitions) {
		for (Explanation definition : definitions) {
			em.persist(definition);
		}
	}
	public void update(List<Explanation> updatedDefinitions) {
		for (Explanation updatedDef : updatedDefinitions) {
			em.merge(updatedDef);
		}
	}
}
