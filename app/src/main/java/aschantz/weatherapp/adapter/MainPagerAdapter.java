package aschantz.weatherapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import aschantz.weatherapp.fragments.FragmentOne;
import aschantz.weatherapp.fragments.FragmentTwo;

/**
 * Created by aschantz on 11/27/16.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentOne();
                break;
            case 1:
                fragment = new FragmentTwo();
                break;
            default:
                fragment = new FragmentOne();
                break;
        }
        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Main";
            case 1:
                return "Details";
        }
        return "Main";
    }

    //return the number of pages you have, otherwise it will not display any pages
    @Override
    public int getCount() {
        return 2;
    }
}
