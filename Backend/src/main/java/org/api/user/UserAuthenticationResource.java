package org.api.user;

import org.data.entities.User;
import org.utils.ErrorResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user/authentication")
@Produces(MediaType.APPLICATION_JSON)
public class UserAuthenticationResource {

    @Inject
    LoggedUser loggedUser;
    @Inject
    UserApiManager apiManager;

    @GET
    public Response getLoggedUser() {
        try {
            return Response.ok(loggedUser.getLoggedUser()).build();
        } catch (NullPointerException e) {
            //TODO
            //return Response.ok(new ErrorResponse(Response.Status.NOT_FOUND ,"User isn't logged in"));
        }
    }

    @POST
    public Response createUser(User u) {
        try {
            apiManager.createUser(u);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.ok().build();
        }
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
