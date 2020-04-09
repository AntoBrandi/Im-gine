package model;

import java.util.ArrayList;

/**
 * PostCategory Class
 *
 * This class is the model for a post category item shown in the <layout name="item_addcontent"></layout> so it represents
 * a category of suggested components that might be added to a post like for example the post category 'Add a tag to your post'. This post category item is included
 * in the recycler view in the <layout name="fragment_addcontent"></layout>. Also, this model includes the list of atomic item that falls in the specific category.
 * This means, that this model includes a list of all the items (or PostItem) that belongs to the specific post category.
 * So in the <layout name="item_addcontent"></layout> there is a recycler view whose elements are the list of all the
 * PostItem in the PostCategory. This PostItem are then presented in the <layout name="item_addcontent_tag"></layout> or
 * <layout name="item_addcontent_component"></layout> based on the type of content
 */
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
