package rx.dictionary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rx.dictionary.LexicalItemService;
import rx.dictionary.jpa.entity.LexicalItem;

@Path("lexicalitems")
public class LexicalItemResource {
    @Inject
    private LexicalItemService service;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LexicalItem create(LexicalItem lexicalItem) {
        service.create(lexicalItem);
        return lexicalItem;
    }
}
