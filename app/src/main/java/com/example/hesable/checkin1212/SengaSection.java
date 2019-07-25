package com.example.hesable.checkin1212;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class SengaSection extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senga_section);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //add fragements

        adapter.AddFragment(new FragamentChat(),"Chat");
        adapter.AddFragment(new FragamentVideos(),"Video");
        adapter.AddFragment(new FragamentFav(),"Upload");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_file_uploaddp);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_video);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_chat_black_24dp);

        // remove the shadows

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

    }
}
