package com.example.im_gine;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.MessageAdapter;
import model.Message;

public class ChatActivity extends AppCompatActivity {

    // application variables
    private Intent i;
    private String topic;
    private int colorId;
    private String textMessage;
    private Message actualMessage;

    // Firebase Variables
    private FirebaseFirestore db;
    private FirebaseUser firebaseUser;
    private final String DATABASE_COLLECTION = "messages";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");

    // adapter
    MessageAdapter messageAdapter;
    List<Message> messages;

    // view related variables
    private RecyclerView recyclerView;
    private ImageButton btnSend;
    private Toolbar toolbar;
    private TextView chatTitle;
    private RelativeLayout messageSection;
    private EditText message;
    private GradientDrawable shape_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbar);
        chatTitle = findViewById(R.id.chat_title);
        btnSend = findViewById(R.id.btnSend);
        messageSection = findViewById(R.id.message_section);
        message = findViewById(R.id.text_message);
        recyclerView = findViewById(R.id.recycler_view);
        shape_right = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.message_right);

        // firebase setup
        Firebase.setAndroidContext(this);
        db = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Integer[] backgroundColors = {
                R.color.color1,
                R.color.color2,
                R.color.color3,
                R.color.color4
        };

        i = getIntent();
        colorId = i.getIntExtra("colorId",0);
        topic = i.getStringExtra("param");

        // style setup
        toolbar.setBackgroundColor(getResources().getColor(backgroundColors[colorId]));
        chatTitle.setText(topic);
        messageSection.setBackground(getDrawable(backgroundColors[colorId]));
        shape_right.setColor(getResources().getColor(backgroundColors[colorId]));

        // recycler view setup
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        messages = new ArrayList<>();


        db.collection(DATABASE_COLLECTION)
                .whereEqualTo("_topic", topic)
                .orderBy("_timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e!=null){
                            Toast.makeText(ChatActivity.this, getString(R.string.error_generic),Toast.LENGTH_SHORT).show();
                        }
                        List<Message> actualMessages = new ArrayList<>();
                        if(queryDocumentSnapshots!=null){

                            for (DocumentSnapshot doc : queryDocumentSnapshots){
                                actualMessages.add(doc.toObject(Message.class));
                            }
                        }

                        messages = actualMessages;
                        messageAdapter = new MessageAdapter(ChatActivity.this, messages);
                        recyclerView.setAdapter(messageAdapter);
                    }
                });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textMessage = message.getText().toString();
                if(!textMessage.equals("")){
                    Timestamp time = com.google.firebase.Timestamp.now();
                    long millis = time.getSeconds()*1000 + time.getNanoseconds()/1000000;
                    actualMessage = new Message(firebaseUser.getUid(), topic, textMessage, dateFormat.format(new Date(millis)).toString());
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
