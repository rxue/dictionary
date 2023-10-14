package rx.dictionary.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rx.dictionary.ExplanationService;
import rx.dictionary.rest.dto.ExplanationDTO;
import rx.dictionary.jpa.entity.Explanation;

import java.net.URI;


@Path("explanations")
public class ExplanationsResource {
    @Inject
    private ExplanationService explanationService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ExplanationDTO explanationDTO) {
        Explanation createdExplanation = explanationService.add(explanationDTO);
        return Response.created(formURI(createdExplanation))
                .entity(createdExplanation)
                .build();
    }

    static URI formURI(Explanation explanation) {
        return URI.create("/explanations/" + explanation.getId());
    }
}
