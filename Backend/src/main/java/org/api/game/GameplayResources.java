package org.api.game;

import org.api.user.LoggedUser;
import org.data.entities.Move;
import org.game.Figures.IFigure;
import org.game.Figures.Teams;
import org.managers.GameManager;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("game")
@Produces(MediaType.APPLICATION_JSON)
public class GameplayResources {

    @Inject
    GameManager gameManager;

    @Inject
    LoggedUser loggedUser;

    @GET
    @Path("{id}/sync")
    public Response getGameBoard(@PathParam("id") long id) {
        if (gameManager.getGame(id) == null) {
            return Response.status(404).entity(new ResponseMessage("No game with id : " + id)).build();
        }
        if (!loggedUser.isLogged()) {
            return Response.status(400).entity(new ResponseMessage("How the fuck did you get here ?!")).build();
        }
        if (!gameManager.playerInGameById(loggedUser.getLoggedUser(), id)) {
            return Response.status(400).entity(new ResponseMessage("You Dementor, what u tryin', heh?")).build();
        }
        return Response.status(200).entity(gameManager.getCurrentBoardById(id)).build();
    }

    @PUT
    @Path("{id}/play")
    public Response makeMove(@PathParam("id") long id, Move move) {
        System.out.println(move.getxSource());
        System.out.println(move.getySource());
        System.out.println(move.getxTarget());
        System.out.println(move.getyTarget());
        try {
            if (gameManager.getGame(id) == null) {
                return Response.status(404).entity(new ResponseMessage("No game with id : " + id)).build();
            }
            if (move == null) {
                return Response.status(400).entity(new ResponseMessage("Missing move entity!")).build();
            }
            if (!loggedUser.isLogged()) {
                return Response.status(400).entity(new ResponseMessage("How the fuck did you get here ?!")).build();
            }
            if ((gameManager.getGame(id).getUser1().getId() == loggedUser.getLoggedUser().getId() && gameManager.getTurn(id) != Teams.White)
                    || gameManager.getGame(id).getUser2().getId() == loggedUser.getLoggedUser().getId() && gameManager.getTurn(id) != Teams.Black) {
                return Response.status(200).entity(new ResponseMessage("Not your turn")).build();
            }
            System.out.println("got here");
            System.out.println(gameManager.makeMove(id, move));
//            if (gameManager.makeMove(id, move)) {
//                return Response.status(200).entity(new ResponseMessage("Moved successfully")).build();
//            }
            System.out.println("got here two");
            return Response.status(200).entity(new ResponseMessage("Invalid move!")).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }
}
