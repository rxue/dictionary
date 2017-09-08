package ruixue.mydictionary.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

//@MappedSuperclass
@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"word_id","explanation_language_id"})
	)
@Entity
public class ExplanationMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="WORD_ID", insertable=false, updatable=false)
	private Word word;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="EXPLANATION_LANGUAGE_ID", insertable=false, updatable=false)
	private Language explanationLanguage;
	
	//@Transient
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="EXPLANATION_ID", insertable=false, updatable=false)
	private List<Explanation> explanations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public Language getExplanationLanguage() {
		return explanationLanguage;
	}

	public void setExplanationLanguage(Language explanationLanguage) {
		this.explanationLanguage = explanationLanguage;
	}

	public List<Explanation> getExplanations() {
		return explanations;
	}

	public void setExplanations(List<Explanation> explanations) {
		this.explanations = explanations;
	}
	
	
}
