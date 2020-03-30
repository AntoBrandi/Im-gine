package com.example.im_gine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import model.Message;

public class ChatActivity extends AppCompatActivity {

    private Intent i;
    private String topic;
    private int colorId;
    private ImageButton btnSend;
    private Toolbar toolbar;
    private TextView chatTitle;
    private RelativeLayout messageSection;
    private EditText message;
    private String textMessage;
    private Message actualMessage;

    // Firebase Variables
    private FirebaseFirestore db;
    private final String DATABASE_COLLECTION = "messages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbar);
        chatTitle = findViewById(R.id.chat_title);
        btnSend = findViewById(R.id.btnSend);
        messageSection = findViewById(R.id.message_section);
        message = findViewById(R.id.text_message);

        // firebase setup
        Firebase.setAndroidContext(this);
        db = FirebaseFirestore.getInstance();

        Integer[] backgroundColors = {
                R.color.color1,
                R.color.color2,
                R.color.color3,
                R.color.color4
        };

        i = getIntent();
        colorId = i.getIntExtra("colorId",0);
        topic = i.getStringExtra("param");

        toolbar.setBackgroundColor(getResources().getColor(backgroundColors[colorId]));
        chatTitle.setText(topic);
        messageSection.setBackground(getDrawable(backgroundColors[colorId]));

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textMessage = message.getText().toString();
                // TODO: take the user from the preferences
                final String actualUser = "admin";
                if(!textMessage.equals("")){
                    actualMessage = new Message(actualUser, topic, textMessage);
                    sendMessage();
                }
                else{
                    Toast.makeText(ChatActivity.this, "You can't send an empty message", Toast.LENGTH_SHORT).show();
                }
                message.setText("");
            }
        });
    }

    private void sendMessage(){
        db.collection(DATABASE_COLLECTION)
                .add(actualMessage)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}
