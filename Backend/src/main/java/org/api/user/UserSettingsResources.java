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

    @DELETE
    public Response deleteUser(User u) {
        try {
            apiManager.deleteUser(u);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

    @PUT
    public Response changeUserPassword(User u) {
        try {
            apiManager.changeUserPassword(u);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(404).build();
        }
    }
}
