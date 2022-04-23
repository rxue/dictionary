package rx.dictionary.application;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import rx.dictionary.EntryValue;
import rx.dictionary.Language;
@RequestScoped
@Named
public class SearchCriteria {
	private String keyword;
	private Language fromLanguage;
	private Language toLanguage;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Language getFromLanguage() {
		return fromLanguage;
	}
	public void setFromLanguage(Language fromLanguage) {
		this.fromLanguage = fromLanguage;
	}
	public Language getToLanguage() {
		return toLanguage;
	}
	public void setToLanguage(Language toLanguage) {
		this.toLanguage = toLanguage;
	}
	EntryValue getEntryValue() {
		EntryValue ev = new EntryValue();
		ev.setEntry(keyword);
		ev.setLanguage(fromLanguage);
		return ev;
	}
}
