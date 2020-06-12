package cn.edu.sc.train;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> arrayList;
    public ViewPagerFragmentAdapter(@NonNull FragmentManager fm, ArrayList list) {
        super(fm);
        this.arrayList=list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}
