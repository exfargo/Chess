package org.api.user;

import org.managers.UserManager;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserChallengeResource {

    //loggedUser keeps session logged user
    @Inject
    LoggedUser loggedUser;
    //apiManager takes care of more complex actions, that may require communication with databases
    @Inject
    UserManager userManager;

    /**
     * returns list of challenges for logged user
     *
     * @return List of challenges
     */
    @GET
    @Path("challenge")
    public Response getChallenges() {
        if (!this.loggedUser.isLogged())
            return Response.status(404).entity(new ResponseMessage("You must be logged in order to view challenges")).build();
        try {
            return Response.status(200).entity(userManager.getChallengesFor(this.loggedUser.getLoggedUser())).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    /**
     * creates challenge for logged player and specified player
     *
     * @param idChallenged id of challenged player
     * @return outcome message
     */
    @POST
    @Path("{id}/challenge")
    public Response createChallenge(@PathParam("id") int idChallenged) {
        if (!this.loggedUser.isLogged())
            return Response.status(404).entity(new ResponseMessage("You must be logged in order to challenge someone")).build();
        if (this.userManager.getUserWithPasswordById(idChallenged) == null)
            return Response.status(404).entity(new ResponseMessage("No such player")).build();
        if (idChallenged == this.loggedUser.getLoggedUser().getId()) {
            return Response.status(400).entity(new ResponseMessage("Cannot challenge yourself")).build();
        }
        try {
            userManager.createChallenge(this.loggedUser.getLoggedUser(), idChallenged);
            return Response.status(200).entity(new ResponseMessage("Player challenged successfully")).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    /**
     * accept challenge, creates a game
     *
     * @param id of challenge
     * @return outcome message
     */
    @PUT
    @Path("challenge/{id}")
    public Response acceptChallenge(@PathParam("id") long id) {
        if (!this.loggedUser.isLogged())
            return Response.status(404).entity(new ResponseMessage("You must be logged in order to accept challenges")).build();
        if (!userManager.challengeExists(id))
            return Response.status(404).entity(new ResponseMessage("No such challenge")).build();
        if (userManager.isChallengeAccepted(id)) {
            return Response.status(200).entity(new ResponseMessage("Challenge already accepted")).build();
        }
        try {
            userManager.matchChallenge(id);
            return Response.status(200).entity(new ResponseMessage("Challenge accepted successfully")).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    /**
     * decline / cancel a challenge by id
     *
     * @param id of challenge
     * @return outcome message
     */
    @DELETE
    @Path("challenge/{id}/del")
    public Response revokeChallenge(@PathParam("id") int id) {
        //TODO revoke/decline challenge
        return Response.status(400).entity(new ResponseMessage("NOT IMPLEMENTED YET!")).build();
    }
}
