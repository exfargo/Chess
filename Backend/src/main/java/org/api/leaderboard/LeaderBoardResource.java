package org.api.leaderboard;

import org.managers.LeaderBoardApiManager;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("Lidlboard")
public class LeaderBoardResource {

    @Inject
    LeaderBoardApiManager apiManager;

    @GET
    @Path("top-50")
    public Response getLeaderboardTop50() {
        try {
            return Response.status(200).entity(apiManager.getLeaderboardTop50()).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage("Something went wrong")).build();
        }
    }
}
