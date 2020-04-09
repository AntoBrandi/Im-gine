package model;

/**
 * User class
 *
 * This class models a generic user in the application. This user model is also useful for the communication
 * with the firestone cloud database to which this application establish a connection and exchange data.
 */
public class User {
    private String username;
    private String userId;

    public User(String userId, String username){
        this.userId = userId;
        this.username = username;
    }

    public void set_username(String username) {
        this.username = username;
    }

    public String get_username() {
        return username;
    }

    public String get_userId() {
        return userId;
    }

    public void set_userId(String userId) {
        this.userId = userId;
    }
}
