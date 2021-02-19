package org.api.user;


import org.data.entities.User;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {

    @Inject
    UserApiManager apiManager;

    @Inject
    LoggedUser loggedUser;

    @GET
    public Response getUser(@QueryParam("id") int id) {
        try {
            User u = apiManager.getUserById(id);
            if (u == null) {
                return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
            }
            return Response.status(200).entity(u).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage("Something went wrong")).build();
        }
    }

    //region user/authentication
    @GET
    @Path("authentication")
    public Response getLoggedUser() {
        try {
            if (loggedUser.getLoggedUser() != null)
                return Response.status(200).entity(loggedUser.getLoggedUser()).build();
            else
                return Response.status(404).entity(new ResponseMessage("User is not logged in")).build();
        } catch (NullPointerException e) {
            return Response.status(400).entity(new ResponseMessage("Something went wrong")).build();
        }
    }

    @POST
    @Path("authentication/new")
    public Response createUser(User u) {
        try {
            if (apiManager.isUserWithName(u.getUsername())) {
                return Response.status(409).entity(new ResponseMessage("Name already in use")).build();
            }
            return Response.status(200).entity(apiManager.createUser(u)).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage("Something went wrong")).build();
        }
    }

    @POST
    @Path("authentication")
    public Response logUser(User user) {
        if (apiManager.isValidUser(user)) {
            loggedUser.setLoggedUser(apiManager.getNormalizedUser(user));
            return Response.status(200).build();
        }
        return Response.status(400).entity(new ResponseMessage("Something went wrong")).build();
    }
    //endregion

    //region user/settings
    @DELETE
    @Path("settings")
    public Response deleteUser(User u) {
        try {
            if (apiManager.isValidUser(u)) {
                return Response.status(200).entity(apiManager.deleteUser(u)).build();
            }
            return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
        } catch (Exception e) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }

    @PUT
    @Path("settings")
    public Response changeUserPassword(User u) {
        try {
            if (apiManager.isValidUser(u)) {
                return Response.status(200).entity(apiManager.changeUserPassword(u)).build();
            }
            return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
        } catch (Exception e) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }
    //endregion

}
