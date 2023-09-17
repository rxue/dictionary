package rx.dictionary.ui.jsf.addorupdate;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import rx.dictionary.jpaentity.PartOfSpeech;
@RequestScoped
@Named
public class ExplanationDTO {
	private PartOfSpeech partOfSpeech;
	private String meaning;
	public ExplanationDTO(PartOfSpeech partOfSpeech, String meaning) {
		this.partOfSpeech = partOfSpeech;
		this.meaning = meaning;
	}
	public static ExplanationDTO empty() {
		return new ExplanationDTO(null, null);
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
