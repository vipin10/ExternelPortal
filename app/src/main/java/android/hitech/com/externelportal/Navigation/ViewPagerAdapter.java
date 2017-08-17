package android.hitech.com.externelportal.Navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.hitech.com.externelportal.NavigationFragments.CurrentPositions;
import android.hitech.com.externelportal.NavigationFragments.Profiles;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment fragment;
    String title;
    Context context;
    SharedPreferences sf;

    ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        sf = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public Fragment getItem(int position) {
        int i = sf.getInt("page", 0);
        Log.i("ThePageIs:", String.valueOf(i));
        if (position == 1) {
            fragment = new Profiles();
        } else {
            fragment = new CurrentPositions();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        if (position == 0) {
            title = "Running Jobs";
        } else {
            title = "Relevant Jobs";
        }
        return title;
    }
}
