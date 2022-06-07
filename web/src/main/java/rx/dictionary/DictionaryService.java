package rx.dictionary;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.transaction.Transactional;

import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.ItemValue;

public class DictionaryService implements Serializable {
	@Inject
	private ExplanationRepository explanationRepository;
	/**
	 * Handle transaction
	 * @param explanations
	 */
	@Transactional
	public void add(List<Explanation> explanations) {
		explanationRepository.add(explanations);
	}
	@Transactional
	public void update(List<Explanation> explanations) {
		explanationRepository.update(explanations);
	}
	@Transactional
	public List<Explanation> find(ItemValue itemValue, Locale toLang) {
		return explanationRepository.find(itemValue, toLang);
	}
}
