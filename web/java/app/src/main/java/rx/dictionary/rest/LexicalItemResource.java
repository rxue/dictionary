package rx.dictionary.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import rx.dictionary.LexicalItemRepository;
import rx.dictionary.jpaentity.LexicalItem;

@Path("lexicalitems")
public class LexicalItemResource {
    private LexicalItemRepository repo;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public LexicalItem create(LexicalItem lexicalItem) {
        System.out.println("received LexicalItem::::::::::: " + lexicalItem);
        return lexicalItem;
    }
}
