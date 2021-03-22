package org.managers;


import org.data.dao.ChallengeDAO;
import org.data.dao.UserDAO;
import org.data.entities.Challenge;
import org.data.entities.User;
import org.utils.ResponseMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ApplicationScoped
public class UserManager {

    @Inject
    private UserDAO userDAO;

    //challengeDAO handles communication between application and database
    @Inject
    ChallengeDAO challengeDAO;

    //gameManager holds reference to all gameControllers and Game entities
    @Inject
    GameManager gameManager;

    private final String[] forbiddenNames = new String[] {"null", "admin", "root", "fabian", "petr"};

    //<editor-fold desc="User management">
    public ResponseMessage createUser(User u) {
        try {
            if (userDAO.getUserByName(u.getUsername()) == null) {
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
            return new ResponseMessage(e.toString());
        }
    }

    public ResponseMessage changeUserPassword(int loggedUser, User u) {
        try {
            userDAO.changePassword(loggedUser, u.getPassword());
            return new ResponseMessage("Password changed successfully");
        } catch (Exception e) {
            return new ResponseMessage(e.toString());
        }
    }

    public ResponseMessage changeUserName(User loggedUser, User u) {
        try {
            if (!isUserByName(u.getUsername())) {
                userDAO.changeUserName(loggedUser.getId(), u.getUsername());
                return new ResponseMessage("Username changed successfully");
            }
            return new ResponseMessage("Username already in use");
        } catch (Exception e) {
            return new ResponseMessage(e.toString());
        }
    }
    //</editor-fold>

    //<editor-fold desc="User getters and transformers">
    public boolean isValidUser(User user) {
        try {
            User usr = userDAO.getUserByName(user.getUsername());
            return usr.matchPassword(user.getPassword());
        } catch (Exception e) {
            return false;
        }
    }

    public User getNormalizedUser(User user) {
        return userDAO.getUserByName(user.getUsername());
    }

    public User getUserById(int id) {
        User u = userDAO.get(id);
        if (u != null)
            return u.retrievePasswordLess();
        else return null;
    }

    public User getUserWithPasswordById(int id) {
        return userDAO.get(id);
    }

    public boolean isUserByName(String username) {
        return userDAO.getUserByName(username) != null;
    }

    public boolean idMatchesPassword(int id, User u) {
        return getUserWithPasswordById(id).matchPassword(u.getPassword());
    }

    public boolean idMatchesUsername(int id, User u) {
        return getUserById(id).getUsername().equals(u.getUsername());
    }
    //</editor-fold>

    //<editor-fold desc="Challenge management">
    public List<Challenge> getChallengesFor(User loggedUser) {
        return challengeDAO.getChallengesForUser(loggedUser);
    }

    public List<Challenge> getChallengesBy(User loggedUser) {
        return challengeDAO.getChallengesByUser(loggedUser);
    }

    public boolean challengeExists(long id) {
        return challengeDAO.get(id) != null;
    }

    public boolean isChallengeAccepted(long id) {
        return challengeDAO.get(id).isAccepted();
    }

    public void createChallenge(User loggedUser, int idChallenged) {
        challengeDAO.save(new Challenge(loggedUser, userDAO.get(idChallenged)));
    }

    public void matchChallenge(long id) {
        gameManager.matchChallenge(id);
    }

    public boolean isForbidden(String username) {
        for (String name : forbiddenNames) {
            if (name.equals(username.toLowerCase())) return true;
        }
        return false;
    }
    //</editor-fold>
}
