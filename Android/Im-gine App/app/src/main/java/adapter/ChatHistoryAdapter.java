package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import java.util.ArrayList;
import model.ChatHistory;

public class ChatHistoryAdapter extends RecyclerView.Adapter<ChatHistoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ChatHistory> chatHistories;

    public ChatHistoryAdapter(Context context, ArrayList<ChatHistory> chatHistories){
        this.context = context;
        this.chatHistories = chatHistories;
    }

    @NonNull
    @Override
    public ChatHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_history, parent, false);
        return new ChatHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHistoryAdapter.ViewHolder holder, int position) {
        final ChatHistory chatHistory = chatHistories.get(position);
        holder.profileImage.setImageResource(chatHistory.getProfileImage());
        holder.profileUsername.setText(chatHistory.getProfileUsername());
        holder.lastMessage.setText(chatHistory.getLastMessage());
        holder.lastMessageTime.setText(chatHistory.getLastMessageTime());
        if(chatHistory.getUnreadMessages()<=0){
            holder.unreadMessages.setVisibility(View.GONE);
        }
        else{
            holder.unreadMessages.setText(String.valueOf(chatHistory.getUnreadMessages()));
        }
    }

    @Override
    public int getItemCount() {
        return this.chatHistories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImage;
        TextView profileUsername;
        TextView lastMessage;
        TextView lastMessageTime;
        TextView unreadMessages;

        public ViewHolder(View view){
            super(view);
            profileImage = view.findViewById(R.id.chat_history_profileImage);
            profileUsername = view.findViewById(R.id.chat_history_profileUsername);
            lastMessage = view.findViewById(R.id.chat_history_lastMessage);
            lastMessageTime = view.findViewById(R.id.chat_history_lastMessageTime);
            unreadMessages = view.findViewById(R.id.chat_history_unreadMessages);
        }
    }
}
