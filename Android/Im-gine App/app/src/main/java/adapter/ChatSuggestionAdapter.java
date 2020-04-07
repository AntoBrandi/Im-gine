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
import model.ChatSuggestion;

public class ChatSuggestionAdapter extends RecyclerView.Adapter<ChatSuggestionAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ChatSuggestion> chatSuggestions;
    private LayoutInflater layoutInflater;

    public ChatSuggestionAdapter(Context context, ArrayList<ChatSuggestion> chatSuggestions){
        this.context = context;
        this.chatSuggestions = chatSuggestions;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ChatSuggestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_chat_card, parent, false);
        return new ChatSuggestionAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ChatSuggestionAdapter.ViewHolder holder, int position) {
        ChatSuggestion chatSuggestion = chatSuggestions.get(position);
        holder.name.setText(chatSuggestion.getName());
        holder.image.setImageResource(chatSuggestion.getImage());
    }

    @Override
    public int getItemCount() {
        return chatSuggestions.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.item_chat_card_name);
            image = view.findViewById(R.id.item_chat_card_image);
        }
    }
}
