package rx.dictionary.jpaentity;

import java.util.Locale;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * At the beginning, assume it as word entry, i.e. not a phrase
 * @author rui
 *
 */
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "value", "language"}) })
@SequenceGenerator(sequenceName = "item_id_seq", initialValue= 1, name = "item_sequence", allocationSize=1)
@Entity
public class LexicalItem extends AbstractEntity {
	private String value;
	private Locale language;
	@Id
	@GeneratedValue(generator="item_sequence", strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	

	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public Locale getLanguage() {
		return language;
	}


	public void setLanguage(Locale language) {
		this.language = language;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LexicalItem) {
			LexicalItem that = (LexicalItem) obj;
			return new EqualsBuilder()
					.append(language, that.language)
					.append(value, that.value)
					.build();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17,37)
				.append(value)
				.hashCode();
	}
	
}
