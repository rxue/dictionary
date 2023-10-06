package rx.dictionary.ui.jsf.update;


import org.junit.jupiter.api.Test;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.PartOfSpeech;
import rx.dictionary.ui.jsf.addorupdate.JSFExplanationDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateComponentTest {
	@Test
	public void updateExplanation() {
		//GIVEN
		Explanation explanationToUpdate = new Explanation();
		explanationToUpdate.setPartOfSpeech(PartOfSpeech.VT);
		explanationToUpdate.setExplanation("original");
		final String newExplanation = "updated";
		JSFExplanationDTO JSFExplanationDTO = new JSFExplanationDTO(PartOfSpeech.N, newExplanation);
		//WHEN
		UpdateComponent.updateExplanation(explanationToUpdate, JSFExplanationDTO);
		//THEN
		assertEquals(PartOfSpeech.N, explanationToUpdate.getPartOfSpeech());
		assertEquals(newExplanation, explanationToUpdate.getExplanation());
	}
}
