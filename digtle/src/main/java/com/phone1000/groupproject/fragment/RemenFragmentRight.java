package com.phone1000.groupproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.adapter.TeamNewListAdapter;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.bean.MostnewListInfo;
import com.phone1000.groupproject.http.JsonHttpUtils;
import com.phone1000.groupproject.view.IjsonView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class RemenFragmentRight extends Fragment{
    private Context mContext;
    private View view;
    private List<String> datas = new ArrayList<>();
    //重写抽象工厂方法
    public static RemenFragmentRight newInstance(){
        RemenFragmentRight remenFragmentRight = new RemenFragmentRight();
        return remenFragmentRight;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.remen_ftagment_right, container, false);
        return view;
    }
}
