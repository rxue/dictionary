package rx.dictionary.application;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.DictionaryService;
import rx.dictionary.jpaentity.Definition;

@ApplicationScoped
@Named
public class Add {
	@Inject
	private DictionaryService searchService;
	public void add(List<Definition> newExplanations) {
		searchService.add(newExplanations);
	}
	
}
