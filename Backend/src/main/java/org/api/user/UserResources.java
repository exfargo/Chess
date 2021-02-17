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
    LoggedUser loggedUser;
    @Inject
    UserApiManager apiManager;

    @GET
    public Response getUserByID() {
        return Response.ok(loggedUser.getLoggedUser()).build();
    }

    @POST
    public Response logUser(User user) {
        if (apiManager.isValidUser(user)) {
            loggedUser.setLoggedUser(apiManager.getNormalizedUser(user));
            return Response.ok(true).build();
        }
        return Response.ok(false).build();
    }



}
