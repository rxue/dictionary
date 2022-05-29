package rx.dictionary;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.ItemValue;

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
	public List<Explanation> find(ItemValue itemValue, Locale toLang) {
		return em.createQuery("SELECT e FROM Explanation e WHERE e.lexicalItem.itemValue = :itemValue and e.language = :language", Explanation.class)
				.setParameter("itemValue", itemValue)
				.setParameter("language", toLang)
				.getResultList();
	} 
	@Transactional
	public void add(List<Explanation> definitions) {
		System.out.println(definitions.size() + " definitions:" + definitions);
		for (Explanation definition : definitions) {
			System.out.println("ADD definition: " + definition);
			em.merge(definition);
		}
	}
	@Transactional
	public void update(List<Explanation> updatedDefinitions) {
		for (Explanation updatedDef : updatedDefinitions) {
			em.merge(updatedDef);
		}
	}
}
