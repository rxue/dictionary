package rx.dictionary;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import rx.dictionary.jpaentity.Explanation;

public class DictionaryService {
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

}
