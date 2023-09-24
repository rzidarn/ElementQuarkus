package org.element;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/index")
public class ElementResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String element() {
        return "ELEMENT";
    }
}
