package model;

/**
 * ChatHistory class
 *
 * This class models an item in the <layout name="fragment_chat_history"></layout> which is an element in the <layout name="fragment_chat"></layout>.
 * This represent a single conversation that the user had with another user or in a group of users. So, in the <layout name="fragment_chat_history"></layout>
 * is displayed a list of ChatHistory elements with their view, concerning all the conversations had by the user.
 * Two constructors have been implemented in order to set a default value of 0 to the <attribute name="unreadMessages"></attribute> which counts
 * the number of messages sent by a specific user that haven't been read yet.
 * If not provided, the number of unread messages is 0.
 */
public class ChatHistory {
    private int profileImage;
    private String profileUsername;
    private String lastMessage;
    private String lastMessageTime;
    private int unreadMessages;

    public ChatHistory(int profileImage, String profileUsername, String lastMessage, String lastMessageTime, int unreadMessages){
        this.profileImage = profileImage;
        this.profileUsername = profileUsername;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.unreadMessages = unreadMessages;
    }
    public ChatHistory(int profileImage, String profileUsername, String lastMessage, String lastMessageTime){
        this.profileImage = profileImage;
        this.profileUsername = profileUsername;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
        this.unreadMessages = 0;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileUsername() {
        return profileUsername;
    }

    public void setProfileUsername(String profileUsername) {
        this.profileUsername = profileUsername;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public int getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(int unreadMessages) {
        this.unreadMessages = unreadMessages;
    }
}
