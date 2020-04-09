package model;


/**
 * Post Class
 *
 * This class represent a single post element in the home layout. This class represents the model
 * for the layout of a single post in the application. Two constructors have been implemented because there
 * will be different ways to show a post in the application, each of them showing different elements.
 * The first constructor is linked to the layout <layout name="item_home"></layout> which is a single item
 * of the recycler view in the <layout name="fragment_home"></layout>. This includes all the elements in the view.
 * The second constructor is linked to the layout <layout name="item_profile_post"></layout> which is a single item
 * of the recycler view in the <layout name="fragment_profile_posts"></layout> belonging to the <layout name="fragment_profile"></layout>.
 * This is a quick representation of all the posts so not all the data are shown.
 */
public class Post {
    private int profile_image;
    private String profile_username;
    private int shared_image;
    private String shared_text;
    private int post_views;
    private int post_messages;
    private int post_shares;

    // Constructor for the post shown in the home page
    public Post(int shared_image, String shared_text, int profile_image, String profile_username, int post_views, int post_messages, int post_shares) {
        this.shared_image = shared_image;
        this.shared_text = shared_text;
        this.profile_image = profile_image;
        this.profile_username = profile_username;
        this.post_views = post_views;
        this.post_messages = post_messages;
        this.post_shares = post_shares;
    }

    // Constructor for the post shown in the profile page
    public Post(int shared_image, String shared_text, String profile_username, int post_views, int post_messages, int post_shares){
        this.shared_image = shared_image;
        this.shared_text = shared_text;
        this.profile_username = profile_username;
        this.post_views = post_views;
        this.post_messages = post_messages;
        this.post_shares = post_shares;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
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
