package rx.dictionary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.rest.dto.ExplanationsByLanguageDTO;

import java.net.URI;
import java.util.List;
import java.util.Set;

@Path("lexicalitems")
public class LexicalItemResource {
	@Inject
	private LexicalItemService lexicalItemService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(LexicalItem lexicalItem) {
		System.out.println("Going to add Lexical Item with language: " + lexicalItem.getLanguage());
		lexicalItemService.create(lexicalItem);
		return Response.created(URI.create("lexicalitems/" + lexicalItem.getId()))
				.entity(lexicalItem)
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public List<ExplanationsByLanguageDTO> get(@PathParam("id") long id) {
		LexicalItem lexicalItem = lexicalItemService.findById(id);
		Set<Explanation> explanations = lexicalItem.getExplanations();
		ExplanationsByLanguageDTO.Builder resultBuilder = new ExplanationsByLanguageDTO.Builder(explanations.stream().findAny().get().getLanguage());
		explanations.forEach(resultBuilder::addExplanation);
		return List.of(resultBuilder.build());
	}
}