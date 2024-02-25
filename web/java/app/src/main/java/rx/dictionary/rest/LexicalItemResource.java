package rx.dictionary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.rest.dto.LexicalItemWithExplanationsDTO;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Path("lexicalitems")
public class LexicalItemResource {
	@Inject
	private LexicalItemService lexicalItemService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(LexicalItem lexicalItem) {
		lexicalItemService.create(lexicalItem);
		System.out.println("post:)))))))))))))))))");
		return Response.created(URI.create("lexicalitems/" + lexicalItem.getId()))
				.entity(lexicalItem)
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public List<LexicalItem> get(@PathParam("id") Long id) {
		return List.of(lexicalItemService.findById(id));
	}
}