package rx.dictionary;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
	protected int id;
	public final void setId(int id) {
		this.id = id;
	}

}
