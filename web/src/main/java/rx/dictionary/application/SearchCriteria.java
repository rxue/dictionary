package rx.dictionary.application;

import java.util.Locale;

import rx.dictionary.jpaentity.EntryValue;
public class SearchCriteria {
	private String keyword;
	private Locale fromLanguage;
	private Locale toLanguage;
	private SearchCriteria(Builder builder) {
		this.keyword = builder.keyword;
		this.fromLanguage = builder.fromLanguage;
		this.toLanguage = builder.toLanguage;
	}
	Locale getToLanguage() {
		return toLanguage;
	}

	EntryValue getEntryValue() {
		EntryValue ev = new EntryValue();
		ev.setEntry(keyword);
		ev.setLanguage(fromLanguage);
		return ev;
	}
	public static class Builder {
		private String keyword;
		private Locale fromLanguage;
		private Locale toLanguage;
		public Builder setKeyword(String keyword) {
			this.keyword = keyword;
			return this;
		}
		public Builder setFromLanguage(Locale fromLanguage) {
			this.fromLanguage = fromLanguage;
			return this;
		}
		public Builder setToLanguage(Locale toLanguage) {
			this.toLanguage = toLanguage;
			return this;
		}
		public SearchCriteria build() {
			return new SearchCriteria(this);
		}
	
	}

}
