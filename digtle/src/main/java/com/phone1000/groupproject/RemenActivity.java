package com.phone1000.groupproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.phone1000.groupproject.fragment.RemenFragmentLeft;
import com.phone1000.groupproject.fragment.RemenFragmentRight;

import java.util.ArrayList;
import java.util.List;

public class RemenActivity extends AppCompatActivity {

    private MyPagerAdapter mMyPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remen);
        initView();

    }

    private void initView() {
        loadTitles();
        mTabLayout = (TabLayout)findViewById(R.id.remen_fragment_tab_layout);
        mViewPager = (ViewPager)findViewById(R.id.remen_fragment_viewpager);
        mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMyPagerAdapter);
        //TabLayout和ViewPager 进行关联
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void loadTitles() {
        fragmentList.add(RemenFragmentLeft.newInstance());
        fragmentList.add(RemenFragmentRight.newInstance());
        titles.add("文章");
        titles.add("咨询");
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
