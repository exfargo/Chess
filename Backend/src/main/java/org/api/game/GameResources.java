package org.api.game;

import org.api.user.LoggedUser;
import org.data.entities.Game;
import org.managers.GameManager;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("game")
@Produces(MediaType.APPLICATION_JSON)
public class GameResources {

    @Inject
    GameManager gameManager;

    @Inject
    LoggedUser loggedUser;

    @GET
    @Path("all")
    public Response getGames() {
        try {
            if (loggedUser.isLogged()) {
                return Response.status(200).entity(gameManager.getForUser(loggedUser.getLoggedUserId())).build();
            } else {
                return Response.status(404).entity(new ResponseMessage("User not logged")).build();
            }
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }

    @GET
    @Path("user/{id}")
    public Response getGamesFor(@PathParam("id") int id) {
        try {
            return Response.status(200).entity(gameManager.getForUser(id)).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
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
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }
}
