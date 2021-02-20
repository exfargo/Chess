package org.api.user;


import org.data.dao.UserDAO;
import org.data.entities.User;
import org.utils.ResponseMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserApiManager {

    @Inject
    private UserDAO userDAO;

    public ResponseMessage createUser(User u) {
        try {
            if (userDAO.getUserByName(u.getUsername()).isEmpty()) {
                userDAO.save(new User(u.getUsername(), u.getPassword()));
            } else {
                return new ResponseMessage("User already exists");
            }
            return new ResponseMessage("User created successfully");
        } catch (Exception e) {
            return new ResponseMessage(e.toString());
        }
    }

    public ResponseMessage deleteUser(User u) {
        try {
            userDAO.delete(this.getNormalizedUser(u).getId());
            return new ResponseMessage("User deleted successfully");
        } catch (Exception e) {
            return new ResponseMessage("Something went wrong");
        }
    }

    public ResponseMessage changeUserPassword(User u) {
        try {
            userDAO.changePassword(u.getId(), u.getPassword());
            return new ResponseMessage("Password changed successfully");
        } catch (Exception e) {
            return new ResponseMessage("Something went wrong");
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

    public User getUserById(int id) {
        return userDAO.get(id);
    }

    public boolean isUserWithName(String username) {
        return !userDAO.getUserByName(username).isEmpty();
    }

    public ResponseMessage changeUserName(User u) {
        try {
            if (!isUserWithName(u.getUsername())) {
                userDAO.changeUserName(u.getId(), u.getUsername());
                return new ResponseMessage("Username changed successfully");
            }
            return new ResponseMessage("Username already in use");
        } catch (Exception e) {
            return new ResponseMessage("Something went wrong");
        }
    }

    public boolean idMatchesPassword(User u) {
        return getUserById(u.getId()).matchPassword(u.getPassword());
    }
}
