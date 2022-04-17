package rx.dictionary;

import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

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
