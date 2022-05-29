package rx.dictionary.jpaentity;

import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "item_id", "language", "definition"}) })
@SequenceGenerator(sequenceName = "definition_id_seq", initialValue=1, name = "sequence_definition")
@Entity
public class Explanation extends AbstractEntity {
	private LexicalItem lexicalItem;
	private Locale language;
	private String explanation;
	@Id @GeneratedValue(generator="sequence_definition", strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="item_id")
	public LexicalItem getLexicalItem() {
		return lexicalItem;
	}
	public void setLexicalItem(LexicalItem lexicalItem) {
		this.lexicalItem = lexicalItem;
	}
	
	public Locale getLanguage() {
		return language;
	}
	public void setLanguage(Locale language) {
		this.language = language;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
}
