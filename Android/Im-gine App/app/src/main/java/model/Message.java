package model;


public class Message {

    private String sender;
    private String receiver;
    private String messageText;
    private String timestamp;

    public Message(){}

    public Message(String sender, String receiver, String messageText, String timestamp){
        this.sender = sender;
        this.receiver = receiver;
        this.messageText = messageText;
        this.timestamp = timestamp;
    }

    public String get_timestamp() {
        return timestamp;
    }

    public void set_timestamp(String _timestamp) {
        this.timestamp = _timestamp;
    }

    public String get_sender() {
        return sender;
    }

    public void set_sender(String _sender) {
        this.sender = _sender;
    }

    public String get_receiver() {
        return receiver;
    }

    public void set_receiver(String receiver) {
        this.receiver = receiver;
    }

    public String get_messageText() {
        return messageText;
    }

    public void set_messageText(String _messageText) {
        this.messageText = _messageText;
    }
}
