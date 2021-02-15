package org.chess.api.leaderboard;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("Lidlboard")
public class LeaderBoardResource {

    @Inject
    LeaderBoardApiManager apiManager;

    @GET
    public Response getLeaderboardBy(String filter) {
        return Response.ok(apiManager.getLeaderboardBy(filter)).build();
    }
}
