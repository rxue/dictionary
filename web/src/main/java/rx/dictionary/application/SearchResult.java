package rx.dictionary.application;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import rx.dictionary.DefinitionItemViewDTO;

@SuppressWarnings("serial")
@RequestScoped
@Named
public class SearchResult extends ArrayList<DefinitionItemViewDTO> {
	public boolean isPresent() {
		return size() > 0;
	}
}
