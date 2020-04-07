package model;

public class Post {
    private String profile_username;
    private int shared_image;
    private String shared_text;
    private int post_views;
    private int post_messages;
    private int post_shares;

    public Post(int shared_image, String shared_text, String profile_username, int post_views, int post_messages, int post_shares) {
        this.shared_image = shared_image;
        this.shared_text = shared_text;
        this.profile_username = profile_username;
        this.post_views = post_views;
        this.post_messages = post_messages;
        this.post_shares = post_shares;
    }

    public String getShared_text() {
        return shared_text;
    }

    public void setShared_text(String shared_text) {
        this.shared_text = shared_text;
    }

    public int getPost_views() {
        return post_views;
    }

    public void setPost_views(int post_views) {
        this.post_views = post_views;
    }

    public int getPost_messages() {
        return post_messages;
    }

    public void setPost_messages(int post_messages) {
        this.post_messages = post_messages;
    }

    public int getPost_shares() {
        return post_shares;
    }

    public void setPost_shares(int post_shares) {
        this.post_shares = post_shares;
    }

    public int getShared_image() {
        return shared_image;
    }

    public void setShared_image(int shared_image) {
        this.shared_image = shared_image;
    }

    public String getProfile_username() {
        return profile_username;
    }

    public void setProfile_username(String profile_username) {
        this.profile_username = profile_username;
    }
}
