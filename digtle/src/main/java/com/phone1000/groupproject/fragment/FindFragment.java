package com.phone1000.groupproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phone1000.groupproject.R;

/**
 * Created by Administrator on 2016/9/6.
 */
public class FindFragment extends Fragment {
    //重写抽象工厂方法
    public static FindFragment newInstance(){
        FindFragment findFragment = new FindFragment();
        return findFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment, container, false);
        return view;
    }
}
