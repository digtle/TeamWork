package com.phone1000.groupproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.phone1000.groupproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ${USER_NAME} on 2016/9/9.
 */
public class GridAdapter extends BaseAdapter {
    private List<String> imageList;
    private Context mContext;

    public GridAdapter(List<String> imageList, Context mContext) {
        this.imageList = imageList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imageList == null ? 0:imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if(convertView  == null){
            view= LayoutInflater.from(mContext).inflate(R.layout.grid_item,null,false);
            viewHolder = new ViewHolder(view);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.with(mContext).load(imageList.get(position)).resize(dp2pix(100),dp2pix(100)).centerCrop().placeholder(R.drawable.article_default).into(viewHolder.iamgeView);

        return view;
    }
    class ViewHolder{
        @BindView(R.id.grid_item)
        ImageView iamgeView;
        public ViewHolder(View view){
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
private int dp2pix(int size){
    float scale = mContext.getResources().getDisplayMetrics().density;
    return  (int)(size*scale+0.5f);
}

}
