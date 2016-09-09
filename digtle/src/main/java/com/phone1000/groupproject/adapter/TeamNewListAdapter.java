package com.phone1000.groupproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.phone1000.groupproject.bean.MostnewListInfo;

import java.util.List;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public class TeamNewListAdapter  extends BaseAdapter{
    private Context mContext;
    private List<MostnewListInfo.NewlistBean> newlistBeanList;

    public TeamNewListAdapter(Context mContext, List<MostnewListInfo.NewlistBean> newlistBeanList) {
        this.mContext = mContext;
        this.newlistBeanList = newlistBeanList;
    }

    @Override
    public int getCount() {
        return newlistBeanList == null ? 0:newlistBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return newlistBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    class ViewHolder{

    }
}
