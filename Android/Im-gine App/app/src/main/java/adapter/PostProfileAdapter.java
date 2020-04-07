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
import model.Post;

public class PostProfileAdapter extends RecyclerView.Adapter<PostProfileAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Post> posts;

    public PostProfileAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_post, parent, false);
        return new PostProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostProfileAdapter.ViewHolder holder, int position) {
        final Post post = posts.get(position);
        holder.shared_imageView.setImageResource(posts.get(position).getShared_image());
        holder.post_username.setText(posts.get(position).getProfile_username());
        holder.content_textView.setText(posts.get(position).getShared_text());
        holder.views_textView.setText(String.valueOf(posts.get(position).getPost_views()));
        holder.messages_textView.setText(String.valueOf(posts.get(position).getPost_messages()));
        holder.shares_textView.setText(String.valueOf(posts.get(position).getPost_shares()));
    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // UI elements
        private TextView post_username, content_textView, views_textView, messages_textView, shares_textView;
        private ImageView shared_imageView;

        public ViewHolder(View view) {
            super(view);
            shared_imageView = view.findViewById(R.id.post_image);
            post_username = view.findViewById(R.id.post_username);
            content_textView = view.findViewById(R.id.post_text);
            views_textView = view.findViewById(R.id.post_viewsCount);
            messages_textView = view.findViewById(R.id.post_messagesCount);
            shares_textView = view.findViewById(R.id.post_shareCount);
        }
    }
}
