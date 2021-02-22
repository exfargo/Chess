package org.data.entities;

import javax.persistence.*;

@Entity
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User challenger;
    @OneToOne
    private User challenged;
    private boolean isAccepted;

    public Challenge(User challenger, User challenged) {
        this.challenger = challenger;
        this.challenged = challenged;
        this.isAccepted = false;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @deprecated jpa use only
     */
    public Challenge(){}

    public boolean isAccepted() {
        return this.isAccepted;
    }
    public void setAccepted(boolean accepted) {
        this.isAccepted = accepted;
    }
    public User getChallenger() {
        return this.challenger;
    }
    public void setChallenger(User challenger) {
        this.challenger = challenger;
    }
    public User getChallenged() {
        return this.challenged;
    }
    public void setChallenged(User challenged) {
        this.challenged = challenged;
    }
}
