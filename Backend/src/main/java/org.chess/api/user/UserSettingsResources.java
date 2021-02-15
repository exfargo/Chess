package org.chess.api.user;


import org.chess.data.entities.User;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("user/settings")
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
