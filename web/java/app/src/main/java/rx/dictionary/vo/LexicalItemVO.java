package rx.dictionary.vo;

import java.util.Locale;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class LexicalItemVO {
	private final String value;
	private final Locale language;
	
	public LexicalItemVO(String value, Locale language) {
		this.value = Objects.requireNonNull(value);
		this.language = Objects.requireNonNull(language, "Language cannot never be null");
	}

	public String getValue() {
		return value;
	}

	public Locale getLanguage() {
		return language;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LexicalItemVO) {
			LexicalItemVO that = (LexicalItemVO) obj;
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
