package org.managers;

import org.data.dao.UserDAO;
import org.data.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class LeaderBoardApiManager {

    @Inject
    UserDAO userDAO;

    public List<User> getLeaderboardTop50() {
        return userDAO.getTop(50);
    }
}
