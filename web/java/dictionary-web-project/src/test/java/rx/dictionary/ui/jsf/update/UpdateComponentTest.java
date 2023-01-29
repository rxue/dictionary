package rx.dictionary.ui.jsf.update;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.addorupdate.ExplanationDTO;

public class UpdateComponentTest {
	@Test
	public void updateExplanation() {
		//GIVEN
		Explanation explanationToUpdate = new Explanation();
		explanationToUpdate.setPartOfSpeech(PartOfSpeech.VT);
		explanationToUpdate.setExplanation("original");
		final String newExplanation = "updated";
		ExplanationDTO explanationDTO = new ExplanationDTO(PartOfSpeech.N, newExplanation);
		//WHEN
		UpdateComponent.updateExplanation(explanationToUpdate, explanationDTO);
		//THEN
		assertEquals(PartOfSpeech.N, explanationToUpdate.getPartOfSpeech());
		assertEquals(newExplanation, explanationToUpdate.getExplanation());
	}
}
