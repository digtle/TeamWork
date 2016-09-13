package com.phone1000.groupproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.bean.HorzotalGroupInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${USER_NAME} on 2016/9/12.
 */
public class GroupGridAdapter extends BaseAdapter {
    private List<HorzotalGroupInfo.NewgroupBean>   newgroupBeenList ;
    private Context mContext;
//   private ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(dp2pix(120),dp2pix(160));
    public GroupGridAdapter(List<HorzotalGroupInfo.NewgroupBean> newgroupBeenList, Context mContext) {
        this.newgroupBeenList = newgroupBeenList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return newgroupBeenList == null? 0:newgroupBeenList.size();
    }

    @Override
    public Object getItem(int position) {
        return newgroupBeenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view  =convertView;
        ViewHolder  viewHolder = null;
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.horizotal_item_view,null,false);
            viewHolder = new ViewHolder(view);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        HorzotalGroupInfo.NewgroupBean newgroupBean = newgroupBeenList.get(position);
        String bannerUrl = newgroupBean.getBanner();
        String iconUrl = newgroupBean.getIcon();
//        view.setLayoutParams(layoutParams);
        Picasso.with(mContext).load(bannerUrl).resize(dp2pix(120),dp2pix(80)).centerCrop().into(viewHolder.bannerImage);
        Picasso.with(mContext).load(iconUrl).resize(dp2pix(40),dp2pix(40)).centerCrop().into(viewHolder.iconImage);
        viewHolder.groupNumberTv.setText(newgroupBean.getMembernum()+"成员");
        viewHolder.nameText.setText(newgroupBean.getName());
        viewHolder.threadNumberTv.setText(newgroupBean.getThreads()+"话题");
        return view;
    }
    private int dp2pix(int size){
        float scale = mContext.getResources().getDisplayMetrics().density;
        return  (int)(size*scale+0.5f);
    }
    class ViewHolder{
        @BindView(R.id.group_banner)
        ImageView bannerImage;
        @BindView(R.id.group_icon)
        ImageView iconImage;
        @BindView(R.id.group_name)
        TextView nameText;
        @BindView(R.id.group_number_tv)
        TextView groupNumberTv;
        @BindView(R.id.thread_number_tv)
        TextView threadNumberTv;
        public ViewHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
