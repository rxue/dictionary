package rx.dictionary.rest.dto;

import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;

public class LexicalItemDTO {
    private Long id;
    @NotNull
    private String languageTag;
    @NotNull
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguageTag() {
        return languageTag;
    }

    public void setLanguageTag(String languageTag) {
        this.languageTag = languageTag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public LexicalItem toLexicalItem() {
        LexicalItem result = new LexicalItem(id);
        result.setLanguage(Locale.forLanguageTag(languageTag));
        result.setValue(value);
        return result;
    }
    static LexicalItemDTO create(LexicalItem lexicalItem) {
        assert lexicalItem.getId() != null : "lexicalItem should not be null";
        System.out.println("lexical item id::::::::::::" + lexicalItem.getId());
        LexicalItemDTO lexicalItemDTO = new LexicalItemDTO();
        lexicalItemDTO.setId(lexicalItem.getId());
        lexicalItemDTO.setLanguageTag(lexicalItem.getLanguage().toLanguageTag());
        lexicalItemDTO.setValue(lexicalItem.getValue());
        return lexicalItemDTO;
    }
}
