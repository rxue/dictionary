package rx.dictionary.ui.jsf.addorupdate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

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
	public String toString() {
		return "Part of Speech: " + partOfSpeech + "Explanation: " + meaning;
	}
}
