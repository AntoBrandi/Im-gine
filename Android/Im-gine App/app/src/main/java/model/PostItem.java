package model;


/**
 * PostItem class
 *
 * This class models an element in the <layout name="item_addcontent_tag"></layout> or <layout name="item_addcontent_component"></layout>. This is the atomic element if the view
 * <layout name="fragment_addcontent"></layout> in which are shown the list of all the suggested categories of post items or tags
 * to add to your post. This class models the single item in a PostCategory. So that, each PostCategory has a list of PostItem
 * corresponding to a single tag or content to add to a post. So , in the <layout name="item_addcontent"></layout> that corresponds to a
 * category of post contents, there is recyclerView with a list of actual tags or components displayed in the <layout name="item_addcontent_tag"></layout>
 * or <layout name="item_addcontent_component"></layout>
 */
public class PostItem {
    private String name;
    private int image;
    private int type;

    public PostItem(String name, int image, int type){
        this.name = name;
        this.image = image;
        this.type = type;
    }

    public PostItem(String name, int image){
        this.name = name;
        this.image = image;
        this.type = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
