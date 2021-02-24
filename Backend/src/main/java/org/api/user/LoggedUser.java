package org.api.user;


import org.data.dao.UserDAO;
import org.data.entities.User;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
public class LoggedUser implements Serializable {

    @Inject
    UserDAO userDAO;

    private Integer loggedUserId;

    public User getLoggedUser() {
        return userDAO.get(loggedUserId);
    }

    public void setLoggedUser(Integer loggedUserId) {
        this.loggedUserId = loggedUserId;
    }
}
