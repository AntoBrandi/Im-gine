package com.example.im_gine.ui.MainActivity.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import java.util.ArrayList;
import adapter.PostProfileAdapter;
import model.Post;

public class PostFragment extends Fragment {

    private RecyclerView post_recyclerView;
    private PostProfileAdapter postAdapter;
    private ArrayList<Post> posts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_posts, container, false);
        post_recyclerView = view.findViewById(R.id.profile_post_recyclerView);
        post_recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        posts = new ArrayList<>();
        posts.add(new Post(R.drawable.tech, "My last tech toy","Miriam Leone", 1000, 23, 3));
        posts.add(new Post(R.drawable.food, "Food","Miriam Leone", 1000, 23, 3));
        posts.add(new Post(R.drawable.travel, "Travel","Miriam Leone", 1000, 23, 3));
        posts.add(new Post(R.drawable.love, "Love","Miriam Leone", 1000, 23, 3));
        postAdapter = new PostProfileAdapter(getActivity(), posts);
        post_recyclerView.setAdapter(postAdapter);


        return view;
    }
}
