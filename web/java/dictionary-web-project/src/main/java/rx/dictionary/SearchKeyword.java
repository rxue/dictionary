package rx.dictionary;

import java.util.Locale;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class SearchKeyword {
	private final String value;
	private final Locale language;
	
	public SearchKeyword(String value, Locale language) {
		this.value = value;
		this.language = language;
	}

	public String getValue() {
		return value;
	}

	public Locale getLanguage() {
		return language;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SearchKeyword) {
			SearchKeyword that = (SearchKeyword) obj;
			return new EqualsBuilder()
					.append(value, that.value)
					.append(language, that.language)
					.build();
		}
		return false;
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17,37)
				.append(value)
				.append(language)
				.hashCode();
	}
}
