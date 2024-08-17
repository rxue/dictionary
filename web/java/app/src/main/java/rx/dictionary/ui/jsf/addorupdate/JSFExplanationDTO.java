package rx.dictionary.ui.jsf.addorupdate;

import io.github.rxue.dictionary.jpa.entity.PartOfSpeech;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@RequestScoped
@Named
public class JSFExplanationDTO {
	private PartOfSpeech partOfSpeech;
	private String meaning;
	public JSFExplanationDTO(PartOfSpeech partOfSpeech, String meaning) {
		this.partOfSpeech = partOfSpeech;
		this.meaning = meaning;
	}
	public static JSFExplanationDTO empty() {
		return new JSFExplanationDTO(null, null);
	}
	public PartOfSpeech getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	@Override
	public String toString() {
		return "Part of Speech: " + partOfSpeech + "Explanation: " + meaning;
	}
}
