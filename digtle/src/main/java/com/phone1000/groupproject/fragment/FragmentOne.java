package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.RemenActivity;

/**
 * Created by Administrator on 2016/9/18.
 */
public class FragmentOne extends Fragment {
    private Context mContext;
    private Button buttonOne;

    public static FragmentOne newInstance(){
        FragmentOne fragmentOne = new FragmentOne();
        return fragmentOne;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_fragment_headertwo_itemone, container, false);
        buttonOne = (Button) view.findViewById(R.id.button_one);
        Button buttonTwo = (Button) view.findViewById(R.id.button_two);
        Button buttonThree = (Button) view.findViewById(R.id.button_three);
        Button buttonFour = (Button) view.findViewById(R.id.button_four);
        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"热门",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, RemenActivity.class);
                startActivity(intent);
            }
        });
        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"活动",Toast.LENGTH_LONG).show();
            }
        });
        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"资讯",Toast.LENGTH_LONG).show();
            }
        });
        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"评测",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
