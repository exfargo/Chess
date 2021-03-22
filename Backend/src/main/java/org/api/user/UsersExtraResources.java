package org.api.user;

import org.data.dao.UserDAO;
import org.utils.ResponseMessage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersExtraResources {

    @Inject
    UserDAO userDAO;

    @Path("all")
    @GET
    public Response getAllUsers() {
        try {
            return Response.status(200).entity(userDAO.getAll()).build();
        } catch (Exception e) {
            return Response.status(400).entity(new ResponseMessage(e.toString())).build();
        }
    }
}
