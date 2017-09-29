package ruixue.mydictionary.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CommonWord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String word;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
