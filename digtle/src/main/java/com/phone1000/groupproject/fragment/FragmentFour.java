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
 * Created by Administrator on 2016/9/18.
 */
public class FragmentFour extends Fragment {
    private Context mContext;

    public static FragmentFour newInstance(){
        FragmentFour fragmentFour = new FragmentFour();
        return fragmentFour;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment_headertwo_itemfour, container, false);
        return view;
    }
}
