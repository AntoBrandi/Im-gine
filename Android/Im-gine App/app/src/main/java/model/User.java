package model;

public class User {
    private String _username = "";
    private String _userId = "";

    public User(String userId, String username){
        this._userId = userId;
        this._username = username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_username() {
        return _username;
    }

    public String get_userId() {
        return _userId;
    }

    public void set_userId(String _userId) {
        this._userId = _userId;
    }
}
