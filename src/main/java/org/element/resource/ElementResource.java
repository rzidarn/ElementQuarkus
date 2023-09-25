package org.element.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.element.model.Element;
import org.element.service.ElementService;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/api/v1/elements")
public class ElementResource {

    private final ElementService elementService;

    @Inject
    public ElementResource(ElementService elementService) {
        this.elementService = elementService;
    }

    @GET
    @Path("/blocking")
    @Produces(MediaType.TEXT_PLAIN)
    public String blocking() {
        return "BLOCKING";
    }

    @GET
    @Path("/non-blocking")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> nonBlocking() {  // event-driven
        return Uni.createFrom().item("NON").onItem().transform(s -> s + " BLOCKING");
    }

    @GET
    public Uni<List<Element>> get() {
        return elementService.listForUser();  // curl localhost:8082/api/v1/elements
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(201)
    public Uni<Element> create(Element project) {
        return elementService.create(project);
    }
}
