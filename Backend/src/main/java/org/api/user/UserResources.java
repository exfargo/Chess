package org.api.user;


import org.data.entities.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {

    @Inject
    UserApiManager apiManager;

    @GET
    public Response getUserByID(@QueryParam("id") int id) {
        try {
            return Response.ok(apiManager.getUser(id)).build();
        } catch (Exception e) {
            return Response.ok(e.toString()).build();
        }
    }
}
