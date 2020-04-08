package com.example.im_gine.ui.MainActivity.fragments.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import java.util.ArrayList;
import adapter.ChatHistoryAdapter;
import model.ChatHistory;

public class ChatHistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ChatHistoryAdapter adapter;
    private ArrayList<ChatHistory> chatHistoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_history, container, false);
        recyclerView = view.findViewById(R.id.chat_history_recyclerView);

        chatHistoryList = new ArrayList<>();
        chatHistoryList.add(new ChatHistory(R.drawable.miriam_profile,"Miriam Leone","Hey how are you?","13:43",7));
        chatHistoryList.add(new ChatHistory(R.drawable.miriam_profile,"Miriam Leone","Hey how are you?","13:43"));
        chatHistoryList.add(new ChatHistory(R.drawable.miriam_profile,"Miriam Leone","Hey how are you?","13:43",124));
        chatHistoryList.add(new ChatHistory(R.drawable.miriam_profile,"Miriam Leone","Hey how are you?","13:43",1547));
        chatHistoryList.add(new ChatHistory(R.drawable.miriam_profile,"Miriam Leone","Hey how are you?","13:43"));

        adapter = new ChatHistoryAdapter(getActivity(), chatHistoryList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
