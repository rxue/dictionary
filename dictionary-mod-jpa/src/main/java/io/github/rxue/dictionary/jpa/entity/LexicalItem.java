package io.github.rxue.dictionary.jpa.entity;

import jakarta.persistence.*;

import java.util.Locale;

@Entity
@Table(name = "lexical_item")
public class LexicalItem extends AbstractEntity {
    @Column(nullable=false)
    private Locale language;
    @Column(nullable=false)
    private String itemValue;
    //Merely for testing purpose
    public LexicalItem(Long id) {
        this.id = id;
    }
    public LexicalItem() {
    }


    //@Override
    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    //@Override
    public String getItemValue() {
        return itemValue;
    }


    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
