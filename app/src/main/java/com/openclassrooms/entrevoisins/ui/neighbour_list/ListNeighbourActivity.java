package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.OnClickNeighbourEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;
    private static final int REQUEST_CODE_UPDATE_ITEM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        EventBus.getDefault().register(this);

    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }

    /**
     * fired if the user clicks on a element of the recyclerView
     *
     * @param eventView
     */

    @Subscribe
    public void onItemClick(OnClickNeighbourEvent eventView) {
        Intent displayNeighbourActivityIntent = new Intent(this, DisplayNeighbourActivity.class);
        displayNeighbourActivityIntent.putExtra("neighbour", eventView.neighbour);
        startActivityForResult(displayNeighbourActivityIntent, REQUEST_CODE_UPDATE_ITEM);

    }
}
