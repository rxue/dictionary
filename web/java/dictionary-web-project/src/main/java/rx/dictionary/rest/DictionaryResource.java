package rx.dictionary.rest;

import java.util.List;
import java.util.Locale;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import rx.dictionary.DictionaryService;
import rx.dictionary.SearchKeyword;
import rx.dictionary.jpaentity.Explanation;
@Path("vocabulary")
public class DictionaryResource {
	@Inject
	private DictionaryService dictionaryService;
	
	@GET
	@Path("{language}/{word}")
	public List<Explanation> getExplanations(@PathParam("language")String language, @PathParam("word")String word) {
		SearchKeyword searchKeyword = new SearchKeyword("take", Locale.ENGLISH);
		return dictionaryService.find(searchKeyword, Locale.SIMPLIFIED_CHINESE);
	}
}
