package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.im_gine.ui.MainActivity.fragments.chat.ChatDiscoverFragment;
import com.example.im_gine.ui.MainActivity.fragments.chat.ChatHistoryFragment;

public class ChatAdapter extends FragmentPagerAdapter {
    private int tabCount;

    public ChatAdapter(FragmentManager fragmentManager, int tabCount){
        super(fragmentManager);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChatDiscoverFragment();
            case 1:
                return new ChatHistoryFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabCount;
    }
}
