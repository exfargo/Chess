package org.api.user;


import org.data.entities.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user/settings")
@Produces(MediaType.APPLICATION_JSON)
public class UserSettingsResources {

    @Inject
    UserApiManager apiManager;

    @POST
    public Response createUser(User u) {
        return Response.ok(apiManager.createUser(u)).build();
    }

    @DELETE
    public Response deleteUser(User u) {
        return Response.ok(apiManager.deleteUser(u)).build();
    }

    @PUT
    public Response changeUserProperties(User u) {
        return Response.ok(apiManager.changeUserPassword(u)).build();
    }

}
