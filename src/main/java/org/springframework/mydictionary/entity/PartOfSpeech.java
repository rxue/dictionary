package org.springframework.mydictionary.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;
@Entity
public class PartOfSpeech {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Type(type = "org.hibernate.type.ByteType")
	private byte id;
	private String partOfSpeech;
	public int getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getPartOfSpeech() {
		return partOfSpeech;
	}
	
	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
}
