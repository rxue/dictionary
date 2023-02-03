package rx.dictionary.rest;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import rx.dictionary.DictionaryService;
import rx.dictionary.SearchKeyword;
import rx.dictionary.jpaentity.Explanation;
@Path("vocabulary")
public class DictionaryResource {
	@Inject
	private DictionaryService dictionaryService;
	
	@GET
	@Path("{language}/{word}")
	@Produces("application/json")
	public List<Explanation> getExplanations(@PathParam("language")String language, @PathParam("word")String word) {
		SearchKeyword searchKeyword = new SearchKeyword("take", Locale.ENGLISH);
		return dictionaryService.find(searchKeyword, Locale.SIMPLIFIED_CHINESE);
	}
}
