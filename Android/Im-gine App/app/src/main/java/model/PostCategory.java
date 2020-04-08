package model;

import java.util.ArrayList;

public class PostCategory {
    private String title;
    private ArrayList<PostItem> postItems;

    public PostCategory(String title, ArrayList<PostItem> postItems){
        this.title = title;
        this.postItems = postItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<PostItem> getPostItems() {
        return postItems;
    }

    public void setPostItems(ArrayList<PostItem> postItems) {
        this.postItems = postItems;
    }
}
