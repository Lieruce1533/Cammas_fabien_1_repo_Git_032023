package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {
    private final int NUM_TABS = 2;

    public ListNeighbourPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * fc mon implémentation de changement de page, ajout de position. fc
     */
    @Override
    public Fragment getItem(int position) {
        return NeighbourFragment.newInstance(position);
    }

    /**
     * @return the number of pages
     */
    @Override
    public int getCount() {
        return NUM_TABS;
    }
}