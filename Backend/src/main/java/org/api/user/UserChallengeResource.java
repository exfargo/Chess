package org.api.user;

import org.data.dao.ChallengeDAO;
import org.data.entities.Challenge;
import org.managers.GameManager;
import org.managers.UserApiManager;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserChallengeResource {

    //TODO haha smaz tyhle injeckty a dej to do manageru

    //loggedUser keeps session logged user
    @Inject
    LoggedUser loggedUser;

    //challengeDAO handles communication between application and database
    @Inject
    ChallengeDAO challengeDAO;

    //gameManager holds reference to all gameControllers and Game entities
    @Inject
    GameManager gameManager;

    //apiManager takes care of more complex actions, that may require communication with databases
    @Inject
    UserApiManager apiManager;


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
            gameManager.matchChallenge(id);
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
}
