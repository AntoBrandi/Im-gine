package model;

/**
 * ChatSuggestion class
 *
 * This class models an element in the <layout name="item_chat_discover_person"></layout>. This is the atomic element if the view
 * <layout name="fragment_chat_discover"></layout> in which are shown the list of all the suggested categories of persons or groups
 * to chat with. This class models the single item in a ChatCategory. So that, each ChatCategory has a list of ChatSuggestion
 * corresponding to a single person or group chat. So , in the <layout name="item_chat_discover"></layout> that corresponds to a
 * category of persons or groups, there is recyclerView with a list of actual people or groups displayed in the <layout name="item_chat_discover_person"></layout>
 */
public class ChatSuggestion {
    private String name;
    private int image;
    private int type;

    public ChatSuggestion(String name, int image){
        this.name = name;
        this.image = image;
        this.type = 0;
    }

    public ChatSuggestion(String name, int image, int type){
        this.name = name;
        this.image = image;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
