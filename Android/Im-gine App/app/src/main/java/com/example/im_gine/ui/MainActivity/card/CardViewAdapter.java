package com.example.im_gine.ui.MainActivity.card;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import com.example.im_gine.ChatActivity;
import com.example.im_gine.MainActivity;
import com.example.im_gine.R;
import java.util.List;

public class CardViewAdapter  extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    private List<CardView> models;
    private Context context;

    public CardViewAdapter(List<CardView> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CardView card = models.get(position);
        holder.shared_imageView.setImageResource(models.get(position).getShared_image());
        holder.post_username.setText(models.get(position).getProfile_username());
        holder.content_textView.setText(models.get(position).getShared_text());
        holder.profile_imageView.setImageResource(models.get(position).getProfile_image());
        holder.profile_textView.setText(models.get(position).getProfile_username());
        holder.views_textView.setText(String.valueOf(models.get(position).getPost_views()));
        holder.messages_textView.setText(String.valueOf(models.get(position).getPost_messages()));
        holder.shares_textView.setText(String.valueOf(models.get(position).getPost_shares()));

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
        return models.size();
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