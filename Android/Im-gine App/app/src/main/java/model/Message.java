package model;

import com.google.firebase.firestore.FieldValue;

public class Message {

    private String _sender;
    private String _topic;
    private String _messageText;
    private String _timestamp;

    public Message(){}

    public Message(String sender, String topic, String messageText, String timestamp){
        this._sender = sender;
        this._topic = topic;
        this._messageText = messageText;
        this._timestamp = timestamp;
    }

    public String get_timestamp() {
        return _timestamp;
    }

    public void set_timestamp(String _timestamp) {
        this._timestamp = _timestamp;
    }

    public String get_sender() {
        return _sender;
    }

    public void set_sender(String _sender) {
        this._sender = _sender;
    }

    public String get_topic() {
        return _topic;
    }

    public void set_topic(String _topic) {
        this._topic = _topic;
    }

    public String get_messageText() {
        return _messageText;
    }

    public void set_messageText(String _messageText) {
        this._messageText = _messageText;
    }
}
