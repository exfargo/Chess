package org.api.game;

import org.api.user.LoggedUser;
import org.data.entities.Game;
import org.data.entities.Move;
import org.managers.GameManager;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("game")
public class GameResources {

    @Inject
    GameManager gameManager;

    @Inject
    LoggedUser loggedUser;

    @Path("/all")
    public Response getGames() {
        try {
            if (loggedUser.isLogged()) {
                return Response.status(200).entity(gameManager.getForUser(loggedUser.getLoggedUser())).build();
            } else {
                return Response.status(404).entity(new ResponseMessage("User not logged")).build();
            }
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage("Something went wrong")).build();
        }
    }

    @Path("{id}/play")
    @PUT
    public Response makeMove(@PathParam("id") long id, Move move) {
        try {
            if (gameManager.getGame(id) != null) {
                gameManager.makeMove(id, move);
                return Response.status(200).entity(new ResponseMessage("Move successful")).build();
            }
            return Response.status(404).entity(new ResponseMessage("No game with id : " + id)).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage("Something went wrong")).build();
        }
    }

    @GET
    @Path("{id}/r")
    public Response getGameResults(@PathParam("id") int id) {
        try {
            Game g = gameManager.getGame(id);
            if (g != null) {
                return Response.status(200).entity(g).build();
            }
            return Response.status(404).entity(new ResponseMessage("No game with id : " + id)).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage("Something went wrong")).build();
        }
    }


}
