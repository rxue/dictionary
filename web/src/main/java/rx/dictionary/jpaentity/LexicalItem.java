package rx.dictionary.jpaentity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * At the beginning, assume it as word entry, i.e. not a phrase
 * @author rui
 *
 */
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "value", "language", "pos"}) })
@SequenceGenerator(sequenceName = "item_id_seq", initialValue= 1, name = "item_sequence", allocationSize=1)
@Entity
public class LexicalItem extends AbstractEntity {
	/*@AttributeOverrides({
	    @AttributeOverride(name = "entry", column = @Column(name = "entry")),
	    @AttributeOverride(name = "language", column = @Column(name = "language"))
	})*/
	@Embedded
	private ItemValue value;
	private PartOfSpeech poS;
	@Id @GeneratedValue(generator="item_sequence", strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public ItemValue getItemValue() {
		return value;
	}

	public void setItemValue(ItemValue value) {
		this.value = value;
	}

	@Enumerated(EnumType.STRING)
	public PartOfSpeech getPoS() {
		return poS;
	}

	public void setPoS(PartOfSpeech poS) {
		this.poS = poS;
	}

}
