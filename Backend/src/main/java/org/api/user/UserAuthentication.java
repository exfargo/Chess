package org.api.user;

import org.data.entities.User;
import org.managers.UserManager;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserAuthentication {

    //apiManager takes care of more complex actions, that may require communication with databases
    @Inject
    UserManager userManager;

    //loggedUser keeps session logged user
    @Inject
    LoggedUser loggedUser;

    /**
     * retrieves and returns a user with specified id from database
     * @param id user's identification
     * @return User by id
     */
    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") int id) {
        try {
            User u = userManager.getUserById(id);
            if (u == null) {
                return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
            }
            return Response.status(200).entity(u).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    //region user/authentication
    /**
     * retrieves currently logged user, returns null if not logged
     * @return User? instance
     */
    @GET
    @Path("authentication")
    public Response getLoggedUser() {
        try {
            if (loggedUser.isLogged()) {
                return Response.status(200).entity(loggedUser.getLoggedUser()).build();
            } else
                return Response.status(404).entity(new ResponseMessage("User is not logged in")).build();
        } catch (NullPointerException e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    /**
     * creates new user
     * @param newUser information (username, password)
     * @return outcome message
     */
    @POST
    @Path("authentication/new")
    public Response createUser(User newUser) {
        try {
            if (userManager.isUserByName(newUser.getUsername())) {
                return Response.status(409).entity(new ResponseMessage("Name already in use")).build();
            }
            return Response.status(200).entity(userManager.createUser(newUser)).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    /**
     * sets passed user as currently logged
     * @param user information (username, password)
     * @return outcome message
     */
    @POST
    @Path("authentication")
    public Response logUser(User user) {
        user.print();
        try {
            if (userManager.isValidUser(user)) {
                loggedUser.setLoggedUser(userManager.getNormalizedUser(user).getId());
                return Response.status(200).entity(new ResponseMessage("Logged in")).build();
            }
            return Response.status(420).entity("huh").build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    /**
     * signs out session logged user,
     * @return outcome message
     */
    @DELETE
    @Path("authentication")
    public Response signOut() {
        try {
            if (this.loggedUser.isLogged()) {
                loggedUser.setLoggedUser(null);
                return Response.status(200).entity(new ResponseMessage("user signed out")).build();
            }
            return Response.status(404).entity(new ResponseMessage("No user is logged in")).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }
}
