package rx.dictionary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.LexicalItem;
import rx.dictionary.jpaentity.PartOfSpeech;

@RunWith(Arquillian.class)
public class DictionaryServiceUpdateIT {
	@Deployment
	public static JavaArchive createDeployment() {
		return ExplanationRepositoryIT.createDeployment().addClass(DictionaryService.class);
	}
	@Inject
	DictionaryService tested;
	
    @Test
    public void update() {
    	SearchKeyword searchKeyword = new SearchKeyword("take", Locale.ENGLISH);
    	List<Explanation> explanations = tested.find(searchKeyword, Locale.SIMPLIFIED_CHINESE);
    	//When: update word of speech on the 2nd Explanation
    	Explanation first = explanations.get(1);
    	LexicalItem firstLexicalItem = first.getLexicalItem();
    	firstLexicalItem.setPoS(PartOfSpeech.PRON);
    	first.setLexicalItem(firstLexicalItem);
    	tested.update(explanations);
    	//Then: search again the same word and there should be one explanation with the updated word of speech
    	List<Explanation> updatedExplanations = tested.find(searchKeyword, Locale.SIMPLIFIED_CHINESE);
    	int countItemsWithUpdatedWordOfSpeech = 0;
    	for (Explanation e : updatedExplanations) {
    		if (e.getLexicalItem().getPoS() == PartOfSpeech.PRON)
    			countItemsWithUpdatedWordOfSpeech++;
    	}
    	assertEquals(countItemsWithUpdatedWordOfSpeech, 1);
    }
}