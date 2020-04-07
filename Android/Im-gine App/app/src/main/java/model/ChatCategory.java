package model;

import java.util.ArrayList;

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
