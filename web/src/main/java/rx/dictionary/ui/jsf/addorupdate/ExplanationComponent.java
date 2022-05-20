package rx.dictionary.ui.jsf.addorupdate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import rx.dictionary.jpaentity.PartOfSpeech;
@RequestScoped
@Named
public class ExplanationComponent {
	private final boolean required;
	private PartOfSpeech partOfSpeech;
	private String explanation;
	public ExplanationComponent() {
		this(false);
	}
	public ExplanationComponent(boolean required) {
		this.required = required;
	}
	public boolean getRequired() {
		return required;
	}
	
	public PartOfSpeech getPartOfSpeech() {
		return partOfSpeech;
	}
	public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getExplanation() {
		return explanation;
	}
	public String toString() {
		return "Part of Speech: " + partOfSpeech + "Explanation: " + explanation;
	}
}
