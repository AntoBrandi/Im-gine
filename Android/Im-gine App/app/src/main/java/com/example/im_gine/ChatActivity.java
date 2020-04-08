package com.example.im_gine;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import adapter.MessageAdapter;
import model.Message;


public class ChatActivity extends AppCompatActivity {

    private TextView userView;
    private TextView lastAccessView;
    private RecyclerView recyclerView;
    private EditText messageEdit;
    private ImageButton emoticon;
    private ImageButton attach;
    private ImageButton gallery;
    private ImageButton vocalMessage;

    // Variables for displaying messages
    private MessageAdapter messageAdapter;
    private List<Message> messages;

    // Firebase Variables
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        userView = findViewById(R.id.chat_user);
        lastAccessView = findViewById(R.id.chat_user_lastAccess);
        recyclerView = findViewById(R.id.chat_recyclerView);
        messageEdit = findViewById(R.id.chat_message);
        emoticon = findViewById(R.id.chat_emoticon);
        attach = findViewById(R.id.chat_attach);
        gallery = findViewById(R.id.chat_gallery);
        vocalMessage = findViewById(R.id.chat_vocal_message);
        messages = new ArrayList<>();

        // Setup for firebase
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // Create fake messages for debug purpose
        messages.add(new Message("Miriam", firebaseUser.getUid(),"Hi how are you?","12:34"));
        messages.add(new Message("Miriam", firebaseUser.getUid(),"Are you fine?","12:37"));
        messages.add(new Message(firebaseUser.getUid(),"Miriam","Yes, all good","12:43"));
        messages.add(new Message("Miriam", firebaseUser.getUid(),"Ok good","12:55"));
        messages.add(new Message(firebaseUser.getUid(),"Miriam","What about you?","12:34"));

        // setup the recycler view in which are displayed the messages
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        messageAdapter = new MessageAdapter(ChatActivity.this, messages);
        recyclerView.setAdapter(messageAdapter);
    }
}
