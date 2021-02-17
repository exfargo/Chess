package org.api.user;


import org.data.dao.UserDAO;
import org.data.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserApiManager {

    @Inject
    private UserDAO userDAO;

    public Object createUser(User u) {
        try {
            if (userDAO.getUserByName(u.getUsername()).isEmpty()) {
                userDAO.save(new User(u.getUsername(), u.getPassword()));
            } else {
                return "Username already exists";
            }
            return true;
        } catch (Exception e) {
            return e.toString();
        }
    }

    public boolean deleteUser(User u) {
        try {
            userDAO.delete(u.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changeUserPassword(User u) {
        try {
            userDAO.changePassword(u.getId(), u.getPassword());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidUser(User user) {
        try {
            User usr = userDAO.getUserByName(user.getUsername()).get(0);
            return usr.matchPassword(user.getPassword());
        } catch (Exception e) {
            return false;
        }
    }

    public User getNormalizedUser(User user) {
        return userDAO.getUserByName(user.getUsername()).get(0);
    }

    public User getUser(int id) {
        return userDAO.get(id);
    }
}
