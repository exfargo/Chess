package org.data.entities;



import org.utils.Pair;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/*
JPA persistable wrapper object
stores two Users (players), all moves made by those players and the winning player
 */

@Entity
public class Game {
    //<editor-fold desc="Users">
    //user1 is reference to host
    @OneToOne
    private User user1;
    //user2 is reference to co-player
    @OneToOne
    private User user2;
    //<editor-fold desc="Winner">
    //user1 or user2, whichever wins
    @OneToOne
    private User winner;
    //<editor-fold desc="ID">
    //id for database referencing
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //</editor-fold>
    //<editor-fold desc="Moves">
    //stores all moves made by players
    @ElementCollection
    private List<Move> moves;

    /**
     * @param users Pair of users, user1 is host, user2 is co-player
     */
    public Game(Pair<User, User> users) {
        moves = new LinkedList<>();
        this.user1 = users.first();
        this.user2 = users.second();
    }

    /**
     * @deprecated for jpa only, use other constructor
     */
    public Game() {
    }
    //</editor-fold>

    /**
     * @return game host
     */
    public User getUser1() {
        return user1;
    }

    /**
     * @return game co-player
     */
    public User getUser2() {
        return user2;
    }

    /**
     * @return winning user, null if not decided yet
     */
    public User getWinner() {
        return winner;
    }
    //</editor-fold>

    /**
     * @param plr winner(1 = white, 2 black)
     */
    public void setWinner(byte plr) {
        if (plr == 1) {
            this.winner = this.user1;
        } else if (plr == 2) {
            this.winner = this.user2;
        }
    }

    /**
     * @return Game id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id id to be used
     * @deprecated use Auto generation instead
     */
    public void setId(Long id) {
        this.id = id;
    }
    //</editor-fold>

    /**
     * @return list of all moves made to-date
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * @param move
     */
    public void writeMove(Move move) {
        moves.add(move);
    }

}
