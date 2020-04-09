package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import java.util.List;
import model.ChatCategory;

public class ChatCategoryAdapter extends RecyclerView.Adapter<ChatCategoryAdapter.ViewHolder> {

    private List<ChatCategory> chatCategories;
    private Context context;

    public ChatCategoryAdapter(List<ChatCategory> chatCategories, Context context){
        this.context = context;
        this.chatCategories = chatCategories;
    }

    @NonNull
    @Override
    public ChatCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_discover, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatCategoryAdapter.ViewHolder holder, int position) {
        final ChatCategory category = chatCategories.get(position);
        holder.title.setText(category.getTitle());
        holder.recyclerView.setAdapter(new ChatSuggestionAdapter(context, category.getChatSuggestions()));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setHasFixedSize(true);
    }

    @Override
    public int getItemCount() {
        return chatCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // UI elements
        private TextView title;
        private RecyclerView recyclerView;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.item_chat_title);
            recyclerView = view.findViewById(R.id.item_chat_recyclerView);
        }
    }
}
