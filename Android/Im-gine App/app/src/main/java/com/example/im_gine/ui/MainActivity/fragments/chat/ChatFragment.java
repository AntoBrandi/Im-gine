package com.example.im_gine.ui.MainActivity.fragments.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.example.im_gine.R;
import com.google.android.material.tabs.TabLayout;
import adapter.ChatAdapter;

public class ChatFragment extends Fragment {

    private ChatViewModel chatViewModel;

    // UI variables
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChatAdapter chatAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.chat_viewPager);
        chatAdapter = new ChatAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(chatAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.chat_dscover);
        tabLayout.getTabAt(1).setIcon(R.drawable.chat_hisory);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return view;
    }
}