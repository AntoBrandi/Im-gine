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
import model.PostCategory;

public class PostCategoryAdapter extends RecyclerView.Adapter<PostCategoryAdapter.ViewHolder>{
    private Context context;
    private List<PostCategory> postCategories;

    public PostCategoryAdapter(List<PostCategory> postCategories, Context context){
        this.context = context;
        this.postCategories = postCategories;
    }

    @NonNull
    @Override
    public PostCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addcontent, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostCategoryAdapter.ViewHolder holder, int position) {
        final PostCategory category = postCategories.get(position);
        holder.title.setText(category.getTitle());
        holder.recyclerView.setAdapter(new PostItemAdapter(category.getPostItems(),context));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setHasFixedSize(true);
    }

    @Override
    public int getItemCount() {
        return postCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // UI elements
        private TextView title;
        private RecyclerView recyclerView;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.item_addcontent_title);
            recyclerView = view.findViewById(R.id.item_addcontent_recyclerView);
        }
    }
}
