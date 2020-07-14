package kor.co.mu.jin.theword;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentPager extends FragmentPagerAdapter {

    Fragment[] fragments = new Fragment[6];

    public FragmentPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        fragments[0] = new FragmentMain();
        fragments[1] = new FragmentFamous();
        fragments[2] = new FragmentPopular();
        fragments[3] = new FragmentGood();
        fragments[4] = new FragmentFunny();
        fragments[5] = new FragmentMyStroy();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
