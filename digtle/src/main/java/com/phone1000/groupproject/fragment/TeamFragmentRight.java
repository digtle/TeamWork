package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.groupproject.R;

/**
 * Created by Administrator on 2016/9/7.
 */
public class TeamFragmentRight extends Fragment {
    private Context mContext;

    //重写抽象工厂方法
    public static TeamFragmentRight newInstance(){
        TeamFragmentRight teamFragmentRight = new TeamFragmentRight();
        return teamFragmentRight;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_ftagment_right, container, false);
        return view;
    }
}
