package model;

import java.util.ArrayList;


/**
 * ChatCategory Class
 *
 * This class is the model for a category item shown in the <layout name="item_chat_discover"></layout> so it represents
 * a category of suggested chats like for example the category 'People that are close to you'. This category item is included
 * in the recycler view in the <layout name="fragment_chat_discover"></layout> which is an element of the <layout name="fragment_chat"></layout>.
 * Also, this model includes the list of atomic item that falls in the specific category. This means, that this model includes a list of all
 * the items (or ChatSuggestion) that belongs to the specific category. So in the <layout name="item_chat_discover"></layout> there is a recycler view
 * whose elements are the list of all the ChatSuggestion in the ChatCategory. This ChatSuggestions are then presented in the <layout name="item_chat_discover_person"></layout>
 * or in the <layout name="item_chat_discover_group"></layout> based on the content of the suggestion.
 */
public class ChatCategory {

    private String title;
    private ArrayList<ChatSuggestion> chatSuggestions;

    public ChatCategory(String title, ArrayList<ChatSuggestion> chatSuggestions){
        this.title = title;
        this.chatSuggestions = chatSuggestions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ChatSuggestion> getChatSuggestions() {
        return chatSuggestions;
    }

    public void setChatSuggestions(ArrayList<ChatSuggestion> chatSuggestions) {
        this.chatSuggestions = chatSuggestions;
    }
}
