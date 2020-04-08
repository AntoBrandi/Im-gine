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
import model.PostItem;

public class PostItemAdapter extends RecyclerView.Adapter<PostItemAdapter.ViewHolder>{
    private Context context;
    private ArrayList<PostItem> postItems;

    public PostItemAdapter(ArrayList<PostItem> postItems, Context context){
        this.context = context;
        this.postItems = postItems;
    }

    @NonNull
    @Override
    public PostItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_tag_card, parent, false);
                break;
            default:
                view = LayoutInflater.from(context).inflate(R.layout.item_postcomponent_card, parent, false);
                break;
        }
        return new PostItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostItemAdapter.ViewHolder holder, int position) {
        PostItem postItem = postItems.get(position);
        holder.name.setText(postItem.getName());
        holder.image.setImageResource(postItem.getImage());
    }

    @Override
    public int getItemCount() {
        return postItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        PostItem postItem = postItems.get(position);
        return postItem.getType();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;

        public ViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.item_addcontent_card_name);
            image = view.findViewById(R.id.item_addcontent_card_image);
        }
    }
}
