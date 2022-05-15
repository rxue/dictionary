package rx.dictionary.jpaentity;

import java.util.Locale;

import javax.persistence.Embeddable;

@Embeddable
public class EntryValue {
	private String entry;
	private Locale language;
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public Locale getLanguage() {
		return language;
	}
	public void setLanguage(Locale language) {
		this.language = language;
	}
	
}
