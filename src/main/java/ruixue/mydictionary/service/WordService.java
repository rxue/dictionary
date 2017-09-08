package ruixue.mydictionary.service;

import java.util.List;

import ruixue.mydictionary.entity.Word;

public interface WordService {
	public List<? extends Word> getAllWords();
}
