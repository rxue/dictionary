package rx.dictionary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import rx.dictionary.ExplanationService;
import rx.dictionary.dto.ExplanationDTO;
import rx.dictionary.jpa.entity.Explanation;

@Path("explanations")
public class ExplanationsResource {
    @Inject
    private ExplanationService explanationService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Explanation create(ExplanationDTO explanationDTO) {
        return explanationService.add(explanationDTO);
    }
}
