package org.api.user;

import org.data.entities.User;
import org.managers.UserManager;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("User")
public class UserSettingsResources {

    //apiManager takes care of more complex actions, that may require communication with databases
    @Inject
    UserManager userManager;

    //loggedUser keeps session logged user
    @Inject
    LoggedUser loggedUser;

    //endregion
    /**
     * deletes a user account
     * @param delUser information (username, password)
     * @return outcome message
     */
    @DELETE
    @Path("delete")
    public Response deleteUser(User delUser) {
        if (!this.loggedUser.isLogged()) {
            return Response.status(400).entity(new ResponseMessage("You must be logged in order to delete user")).build();
        }
        try {
            if (userManager.isValidUser(delUser)) {
                return Response.status(200).entity(userManager.deleteUser(delUser)).build();
            }
            return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
        } catch (Exception e) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }

    /**
     * changes password for logged user
     * @param changedUser information (username, newPassword)
     * @return outcome message
     */
    @PUT
    @Path("password")
    public Response changeUserPassword(User changedUser) {
        if (!this.loggedUser.isLogged()) {
            return Response.status(400).entity(new ResponseMessage("You must be logged in order to change password")).build();
        }
        try {
            if (userManager.idMatchesUsername(loggedUser.getLoggedUser().getId(), changedUser)) {
                return Response.status(200).entity(userManager.changeUserPassword(loggedUser.getLoggedUser().getId(), changedUser)).build();
            }
            return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
        } catch (Exception e) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }

    /**
     * changes username for logged user
     * @param changedUser information (newUsername, password)
     * @return outcome message
     */
    @PUT
    @Path("username")
    public Response changeUserName(User changedUser) {
        if (!this.loggedUser.isLogged()) {
            return Response.status(400).entity(new ResponseMessage("You must be logged in order to change username")).build();
        }
        try {
            if (userManager.idMatchesPassword(this.loggedUser.getLoggedUser().getId(), changedUser)) {
                return Response.status(200).entity(this.userManager.changeUserName(this.loggedUser.getLoggedUser(), changedUser)).build();
            }
            return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
        } catch (Exception e) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }
}
