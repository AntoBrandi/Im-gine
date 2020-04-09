package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import model.Post;
import java.util.List;

public class PostHomeAdapter extends RecyclerView.Adapter<PostHomeAdapter.ViewHolder> {

    private List<Post> posts;
    private Context context;

    public PostHomeAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Post post = posts.get(position);
        holder.shared_imageView.setImageResource(post.getShared_image());
        holder.post_username.setText(post.getProfile_username());
        holder.content_textView.setText(post.getShared_text());
        holder.profile_imageView.setImageResource(post.getProfile_image());
        holder.profile_textView.setText(post.getProfile_username());
        holder.views_textView.setText(String.valueOf(post.getPost_views()));
        holder.messages_textView.setText(String.valueOf(post.getPost_messages()));
        holder.shares_textView.setText(String.valueOf(post.getPost_shares()));

        holder.post_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.post_more);
                popupMenu.inflate(R.menu.post_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.share:
                                // do something
                                return true;
                            case R.id.report:
                                // do something
                                return true;
                            case R.id.copy:
                                // do something
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        // UI elements
        private TextView post_username, content_textView, profile_textView, views_textView, messages_textView, shares_textView;
        private ImageView shared_imageView, profile_imageView;
        private ImageButton post_more;

        public  ViewHolder (View view){
            super(view);
            shared_imageView = view.findViewById(R.id.image);
            post_username = view.findViewById(R.id.post_username);
            content_textView = view.findViewById(R.id.post_text);
            profile_textView = view.findViewById(R.id.card_profileUsername);
            profile_imageView = view.findViewById(R.id.circle_profileImage);
            post_more =view.findViewById(R.id.post_more);
            views_textView = view.findViewById(R.id.post_viewsCount);
            messages_textView = view.findViewById(R.id.post_messagesCount);
            shares_textView = view.findViewById(R.id.post_shareCount);
        }
    }
}