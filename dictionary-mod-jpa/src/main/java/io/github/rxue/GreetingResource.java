package io.github.rxue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @PersistenceContext
    private EntityManager em;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("Entity manager::::" + em);
        return "Hello from Quarkus REST";
    }
}
