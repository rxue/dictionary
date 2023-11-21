package rx.dictionary;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.vo.LexicalItemVO;

public class ExplanationRepository implements Serializable {
	@Inject
	private EntityManager em;
	/**
	 * Given a word and language, find the list of entries, which match EXACTLY
	 * 
	 *
	 * @return list of entries matching exactly. NOTE! Return type is list due to the fact that a word could have several parts of speech
	 */
	public List<Explanation> findLike(LexicalItemVO lexicalItemVO, Locale toLanguage) {
		return em.createQuery("SELECT e FROM Explanation e WHERE e.lexicalItem.language = :fromLanguage AND e.language = :toLanguage AND UPPER(e.lexicalItem.value) like CONCAT(UPPER(:value),'%')",
				Explanation.class)
				.setParameter("fromLanguage", lexicalItemVO.language())
				.setParameter("toLanguage", toLanguage)
				.setParameter("value", lexicalItemVO.value())
				.getResultList();
	}
	public List<Explanation> find(LexicalItemVO lexicalItemVO, Locale toLanguage) {
		return em.createQuery("SELECT e FROM Explanation e WHERE e.lexicalItem.language = :fromLanguage AND e.language = :toLanguage AND e.lexicalItem.value = :value",
				Explanation.class)
				.setParameter("fromLanguage", lexicalItemVO.language())
				.setParameter("toLanguage", toLanguage)
				.setParameter("value", lexicalItemVO.value())
				.getResultList();
	}
	public void add(List<Explanation> definitions) {
		for (Explanation definition : definitions) {
			add(definition);
		}
	}
	public void add(Explanation explanation) {
		explanation.setNextId(80000);
		em.persist(explanation);
		System.out.println("added explanation ID is " + explanation.getId());
	}
	public void update(List<Explanation> updatedDefinitions) {
		for (Explanation updatedDef : updatedDefinitions) {
			em.merge(updatedDef);
		}
	}
}
