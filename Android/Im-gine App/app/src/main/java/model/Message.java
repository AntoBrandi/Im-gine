package model;

public class Message {

    private String _sender;
    private String _topic;
    private String _messageText;

    public Message(){}

    public Message(String sender, String topic, String messageText){
        this._sender = sender;
        this._topic = topic;
        this._messageText = messageText;
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
