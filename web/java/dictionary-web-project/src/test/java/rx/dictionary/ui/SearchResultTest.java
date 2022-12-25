package rx.dictionary.ui;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Arrays;

import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.LexicalItem;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.search.ExplanationItemViewDTO;

public class SearchResultTest {
	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		LexicalItem lexicalItem = newLexicalItem(Locale.ENGLISH, PartOfSpeech.VT, "take");
		List<Explanation> explanations = new ArrayList<>();
		explanations.add(newExplanation(lexicalItem, Locale.ENGLISH, "contrive (a plan) by mutual agreement"));
		explanations.add(newExplanation(lexicalItem, Locale.ENGLISH, "settle by agreement"));
		SearchResult tested = new SearchResult(explanations);
		ExplanationItemViewDTO dtoToAdd = new ExplanationItemViewDTO(PartOfSpeech.N, Arrays.asList("a performance of music by players or singers not involving theatrical staging"));
		tested.add(dtoToAdd);
	}
	private static LexicalItem newLexicalItem(Locale language, PartOfSpeech partOfSpeech, String value) {
		LexicalItem lexicalItem = new LexicalItem();
		lexicalItem.setLanguage(language);
		lexicalItem.setPoS(partOfSpeech);
		lexicalItem.setValue(value);
		return lexicalItem;
	}
	private static Explanation newExplanation(LexicalItem item, Locale inLanguage, String value) {
		Explanation exp = new Explanation();
		exp.setLexicalItem(item);
		exp.setLanguage(inLanguage);
		exp.setExplanation(value);
		return exp;
	}
}
