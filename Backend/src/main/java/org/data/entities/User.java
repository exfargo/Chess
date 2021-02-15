package org.data.entities;



import org.utils.PasswordService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private Long points;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password) {
        PasswordService p = new PasswordService();
        this.username = username;
        this.password = p.hashPassword(password);
        this.points = 800L;
    }

    public void changePoints(Long points) {
        this.points += points;
    }

    public Long getPoints() {
        return this.points;
    }

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void changePassword(String password) {
        this.password = new PasswordService().hashPassword(password);
    }

    /**
     * @deprecated test use only
     */

    public void print() {
        System.out.println(id);
        System.out.println(username);
        System.out.println(password);
        System.out.println(points);
    }

    public boolean matchPassword(String password) {
        return new PasswordService().comparePassword(password, this.password);
    }
}
