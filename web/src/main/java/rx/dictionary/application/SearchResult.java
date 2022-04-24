package rx.dictionary.application;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import rx.dictionary.DefinitionItemViewDTO;

@SuppressWarnings("serial")
@RequestScoped
@Named
public class SearchResult extends ArrayList<DefinitionItemViewDTO> {
	private boolean isPresent;
	public boolean isNotEmpty() {
		return size() > 0;
	}
	void setAsPresent() {
		isPresent = true;
	}
	public boolean isSetButEmpty() {
		return isPresent && size() == 0;
	}
}
