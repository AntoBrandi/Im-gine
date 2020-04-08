package com.example.im_gine.ui.MainActivity.fragments.addContent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.im_gine.R;

import java.util.ArrayList;

import adapter.PostCategoryAdapter;
import model.PostCategory;
import model.PostItem;

public class AddContentFragment extends Fragment {

    private AddContentViewModel addContentViewModel;

    // UI variables
    private EditText add_text;
    private RecyclerView recyclerView;
    private ArrayList<PostCategory> postCategories;
    private ArrayList<PostItem> postContents;
    private ArrayList<PostItem> postTags;
    private PostCategoryAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addContentViewModel = ViewModelProviders.of(this).get(AddContentViewModel.class);
        View view = inflater.inflate(R.layout.fragment_addcontent, container, false);
        add_text = view.findViewById(R.id.addcontent_text);
        recyclerView = view.findViewById(R.id.addcontent_recyclerView);
        postCategories = new ArrayList<>();
        postContents = new ArrayList<>();
        postTags = new ArrayList<>();

        postContents.add(new PostItem("Photo",R.drawable.picture));
        postContents.add(new PostItem("Audio",R.drawable.voicemail));
        postContents.add(new PostItem("Video",R.drawable.video));
        postContents.add(new PostItem("File",R.drawable.folder));
        postTags.add(new PostItem("#covid19",R.drawable.empty,1));
        postTags.add(new PostItem("#celafaremo",R.drawable.empty,1));
        postTags.add(new PostItem("#andratuttobene",R.drawable.empty,1));
        postTags.add(new PostItem("#tiktok",R.drawable.empty,1));
        postCategories.add(new PostCategory("Add a content to your post", postContents));
        postCategories.add(new PostCategory("Select the best tag", postTags));

        adapter = new PostCategoryAdapter(postCategories, getActivity());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        return view;
    }
}