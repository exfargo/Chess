package org.api.user;


import org.data.dao.ChallengeDAO;
import org.data.entities.Challenge;
import org.data.entities.User;
import org.managers.GameManager;
import org.managers.UserApiManager;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {

    //apiManager takes care of more complex actions, that may require communication with databases
    @Inject
    UserApiManager apiManager;
    //challengeDAO handles communication between application and database
    @Inject
    ChallengeDAO challengeDAO;
    //gameManager holds reference to all gameControllers and Game entities
    @Inject
    GameManager gameManager;
    //loggedUser keeps session logged user
    @Inject
    LoggedUser loggedUser;

    /**
     * retrieves and returns a user with specified id from database
     * @param id user's identification
     * @return User by id
     * TODO returns password
     */
    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") int id) {
        try {
            User u = apiManager.getUserById(id);
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
            if (loggedUser.getLoggedUser() != null)
                return Response.status(200).entity(loggedUser.getLoggedUser()).build();
            else
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
            if (apiManager.isUserWithName(newUser.getUsername())) {
                return Response.status(409).entity(new ResponseMessage("Name already in use")).build();
            }
            return Response.status(200).entity(apiManager.createUser(newUser)).build();
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
        try {
            if (apiManager.isValidUser(user)) {
                loggedUser.setLoggedUser(apiManager.getNormalizedUser(user).getId());
                return Response.status(200).entity(new ResponseMessage("Logged in")).build();
            }
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
        return Response.status(420).entity(new ResponseMessage("You WATAFAK")).build();
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

    //endregion

    //region user/settings

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
    //endregion

    //region challenge

    /**
     * decline / cancel a challenge by id
     * @param id of challenge
     * @return outcome message
     */
    @DELETE
    @Path("challenge/{id}/del")
    public Response revokeChallenge(@PathParam("id") int id) {
        //TODO revoke/decline challenge
        return Response.status(400).entity(new ResponseMessage("NOT IMPLEMENTED YET!")).build();
    }

    /**
     * returns list of challenges for logged user
     * @return
     */
    @GET
    @Path("challenge")
    public Response getChallenges(@QueryParam("yours") boolean flip) {
        if (!this.loggedUser.isLogged())
            return Response.status(404).entity(new ResponseMessage("You must be logged in order to view challenges")).build();
        try {
            if (flip) {
                return Response.status(200).entity(challengeDAO.getChallengesForUser(this.loggedUser.getLoggedUser())).build();
            }
            return Response.status(200).entity(challengeDAO.getChallengesByUser(this.loggedUser.getLoggedUser())).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    /**
     * accept challenge, creates a game
     * @param id of challenge
     * @return outcome message
     */
    @PUT
    @Path("challenge/{id}")
    public Response acceptChallenge(@PathParam("id") long id) {
        if (!this.loggedUser.isLogged())
            return Response.status(404).entity(new ResponseMessage("You must be logged in order to accept challenges")).build();
        if (challengeDAO.get(id) == null)
            return Response.status(404).entity(new ResponseMessage("No such challenge")).build();
        if (this.challengeDAO.get(id).isAccepted()) {
            return Response.status(200).entity(new ResponseMessage("Challenge already accepted")).build();
        }
        try {
            challengeDAO.acceptChallenge(id);
            gameManager.matchChallenges();
            return Response.status(200).entity(new ResponseMessage("Challenge accepted successfully")).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    /**
     * creates challenge for logged player and specified player
     * @param idChallenged id of challenged player
     * @return outcome message
     */
    @POST
    @Path("{id}/challenge")
    public Response createChallenge(@PathParam("id") int idChallenged) {
        if (!this.loggedUser.isLogged())
            return Response.status(404).entity(new ResponseMessage("You must be logged in order to challenge someone")).build();
        if (this.apiManager.getUserById(idChallenged) == null)
            return Response.status(404).entity(new ResponseMessage("No such player")).build();
        if (idChallenged == this.loggedUser.getLoggedUser().getId()) {
            return Response.status(400).entity(new ResponseMessage("Cannot challenge yourself")).build();
        }
        try {
            challengeDAO.save(new Challenge(this.loggedUser.getLoggedUser(), apiManager.getUserById(idChallenged)));
            return Response.status(200).entity(new ResponseMessage("Player challenged successfully")).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }
    //endregion
}
