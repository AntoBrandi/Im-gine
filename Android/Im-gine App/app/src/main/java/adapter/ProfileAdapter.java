package adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.im_gine.ui.MainActivity.fragments.profile.ChartFragment;
import com.example.im_gine.ui.MainActivity.fragments.profile.PostFragment;

public class ProfileAdapter extends FragmentPagerAdapter {

    private int tabCount;
    public ProfileAdapter(FragmentManager fragmentManager, int tabCount){
        super(fragmentManager);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                Log.d("BOSCH", "Chart item");
                return new ChartFragment();
            case 1:
                Log.d("BOSCH", "Post item");
                return new PostFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabCount;
    }
}
