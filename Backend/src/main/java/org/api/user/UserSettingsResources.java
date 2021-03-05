package org.api.user;

import org.data.entities.User;
import org.managers.UserApiManager;
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
    UserApiManager apiManager;

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
            if (apiManager.isValidUser(delUser)) {
                return Response.status(200).entity(apiManager.deleteUser(delUser)).build();
            }
            return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
        } catch (Exception e) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }

    /**
     * changes password for logged user
     * @param chanUser information (username, newPassword)
     * @return outcome message
     */
    @PUT
    @Path("password")
    public Response changeUserPassword(User chanUser) {
        if (!this.loggedUser.isLogged()) {
            return Response.status(400).entity(new ResponseMessage("You must be logged in order to change password")).build();
        }
        try {
            if (apiManager.idMatchesUsername(loggedUser.getLoggedUser().getId(), chanUser)) {
                return Response.status(200).entity(apiManager.changeUserPassword(loggedUser.getLoggedUser(), chanUser)).build();
            }
            return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
        } catch (Exception e) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }

    /**
     * changes username for logged user
     * @param chanUser information (newUsername, password)
     * @return outcome message
     */
    @PUT
    @Path("username")
    public Response changeUserName(User chanUser) {
        if (!this.loggedUser.isLogged()) {
            return Response.status(400).entity(new ResponseMessage("You must be logged in order to change username")).build();
        }
        try {
            if (apiManager.idMatchesPassword(this.loggedUser.getLoggedUser().getId(), chanUser)) {
                return Response.status(200).entity(this.apiManager.changeUserName(this.loggedUser.getLoggedUser(), chanUser)).build();
            }
            return Response.status(404).entity(new ResponseMessage("User doesn't exist")).build();
        } catch (Exception e) {
            return Response.status(400).entity("Something went wrong").build();
        }
    }
}
