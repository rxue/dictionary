package rx.dictionary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rx.dictionary.LexicalItemRepository;
import rx.dictionary.jpaentity.LexicalItem;

@Path("lexicalitems")
public class LexicalItemResource {
    @Inject
    private LexicalItemRepository repo;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LexicalItem create(LexicalItem lexicalItem) {
        repo.create(lexicalItem);
        return lexicalItem;
    }
}
