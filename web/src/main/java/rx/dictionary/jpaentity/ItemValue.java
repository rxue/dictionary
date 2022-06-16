package rx.dictionary.jpaentity;

import java.util.Locale;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ItemValue) {
			ItemValue that = (ItemValue) obj;
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
