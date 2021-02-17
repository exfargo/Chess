package org.api.user;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResources {

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
