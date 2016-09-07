package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.groupproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class TeamFragment extends Fragment {
    private Context mComtext;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private MyPagerAdapter mMyPagerAdapter;
    private TabLayout mTabLayout;

    //重写抽象工厂方法
    public static TeamFragment newInstance(){
        TeamFragment teamFragment = new TeamFragment();
        return teamFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComtext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.team_fragment_tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.team_fragment_viewpager);
        initView();
        return view;
    }

    private void initView() {
        loadDatas();
        mMyPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mMyPagerAdapter);
        //TabLayout和ViewPager 进行关联
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void loadDatas() {
        fragmentList.add(TeamFragmentLeft.newInstance());
        fragmentList.add(TeamFragmentRight.newInstance());
        titles.add("最新");
        titles.add("精选");
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

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
