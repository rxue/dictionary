package rx.dictionary;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.vo.LexicalItemVO;

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
	public List<Explanation> find(LexicalItemVO lexicalItemVO, Locale toLang) {
		return explanationRepository.find(lexicalItemVO, toLang);
	}
}
