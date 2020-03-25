package model;

public class User {
    private String _username = "";
    private String _password = "";
    private String _chatWith = "";

    public User(String username, String password){
        this._username = username;
        this._password = password;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void set_chatWith(String _chatWith) {
        this._chatWith = _chatWith;
    }

    public String get_username() {
        return _username;
    }

    public String get_password() {
        return _password;
    }

    public String get_chatWith() {
        return _chatWith;
    }
}
