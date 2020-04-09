package model;

/**
 * Message class
 *
 * This class models a single message element that a person sends to another person.
 * The message always have a sender and receiver and a content. Finally, when the message is sent to the server,
 * a timestamp is added to it. The timestamp is the one of the server and not the one on the local machine in order to keep
 * ordered all the messages based on a unique time reference.
 * This model is related to the views <layout name="item_chat_right"></layout> or <layout name="item_chat_left"></layout>.
 * The distinction between the two views is done in the <class name="MessageAdapter"></class> where if the message sender is the
 * current user, then it is displayed in the <layout name="item_chat_right"></layout>, otherwise, if the receiver is the current user,
 * then it is displayed in the <layout name="item_chat_left"></layout>
 */
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
