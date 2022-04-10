package rx.dictionary;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "entry_id", "language", "definition"}) })
@SequenceGenerator(sequenceName = "definition_id_seq", name = "sequence_definition")
@Entity
public class Definition extends AbstractEntity {
	private Entry entry;
	private Language language;
	private String definition;
	@Id @GeneratedValue(generator="sequence_definition", strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	@ManyToOne
	@JoinColumn(name="entry_id")
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	@Enumerated(EnumType.STRING)
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
}
