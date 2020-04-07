package com.example.im_gine.ui.MainActivity.fragments.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import java.util.ArrayList;
import adapter.ChatCategoryAdapter;
import model.ChatCategory;
import model.ChatSuggestion;

public class ChatFragment extends Fragment {

    private ChatViewModel chatViewModel;

    // UI variables
    private RecyclerView recyclerView;
    private ChatCategoryAdapter adapter;
    private ArrayList<ChatCategory> chatCategories;
    private ArrayList<ChatSuggestion> chatSuggestions;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = view.findViewById(R.id.chat_recyclerView);
        chatCategories = new ArrayList<>();
        chatSuggestions = new ArrayList<>();

        chatSuggestions.add(new ChatSuggestion("Giulia",R.drawable.girl_1));
        chatSuggestions.add(new ChatSuggestion("Erika",R.drawable.girl_2));
        chatSuggestions.add(new ChatSuggestion("Cristina",R.drawable.girl_3));
        chatSuggestions.add(new ChatSuggestion("Manuela",R.drawable.girl_4));
        chatCategories.add(new ChatCategory("Suggested For You", chatSuggestions));
        chatCategories.add(new ChatCategory("You might know", chatSuggestions));
        adapter = new ChatCategoryAdapter(chatCategories, getActivity());

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}