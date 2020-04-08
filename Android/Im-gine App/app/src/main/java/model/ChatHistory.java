package model;

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
