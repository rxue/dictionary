package rx.dictionary.vo;

import java.util.Locale;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record LexicalItemVO(String value, Locale language) {}
