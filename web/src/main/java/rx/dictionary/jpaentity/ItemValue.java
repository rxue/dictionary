package rx.dictionary.jpaentity;

import java.util.Locale;

import javax.persistence.Embeddable;

@Embeddable
public class ItemValue {
	private String value;
	private Locale language;
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
	
}
