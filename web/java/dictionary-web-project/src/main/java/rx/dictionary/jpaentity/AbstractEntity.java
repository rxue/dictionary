package rx.dictionary.jpaentity;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;


@MappedSuperclass
public abstract class AbstractEntity {
	protected int id;
	private LocalDate createdDate;
	/**
	 * Based on the Law of Demeter, try not to override it
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
