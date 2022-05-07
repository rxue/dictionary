package rx.dictionary.application;

import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import rx.dictionary.EntryValue;
@RequestScoped
@Named
public class SearchCriteria {
	private String keyword;
	private Locale fromLanguage;
	private Locale toLanguage;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Locale getFromLanguage() {
		return fromLanguage;
	}
	public void setFromLanguage(Locale fromLanguage) {
		this.fromLanguage = fromLanguage;
	}
	public Locale getToLanguage() {
		return toLanguage;
	}
	public void setToLanguage(Locale toLanguage) {
		this.toLanguage = toLanguage;
	}
	EntryValue getEntryValue() {
		EntryValue ev = new EntryValue();
		ev.setEntry(keyword);
		ev.setLanguage(fromLanguage);
		return ev;
	}

}
