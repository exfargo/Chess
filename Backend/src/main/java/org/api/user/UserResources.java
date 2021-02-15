package org.api.user;


import org.data.entities.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResources {

    @Inject
    LoggedUser loggedUser;
    @Inject
    UserApiManager apiManager;

    @GET
    public Response getUserByID(@QueryParam("id") int id) {
        if (id == -1) {
            return Response.ok(loggedUser.getLoggedUser()).build();
        }
        return Response.ok(apiManager.getUser(id)).build();
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
