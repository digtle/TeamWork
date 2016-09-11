package com.phone1000.groupproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.phone1000.groupproject.R;
import com.phone1000.groupproject.bean.DigtleUrl;
import com.phone1000.groupproject.bean.MostnewListInfo;
import com.phone1000.groupproject.customview.CustomGridview;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ${USER_NAME} on 2016/9/8.
 */
public class TeamNewListAdapter  extends BaseAdapter{
    private Context mContext;
    private List<MostnewListInfo> newlistBeanList;
    private List<String> imageUrlList = new ArrayList<>();
    public TeamNewListAdapter(Context mContext, List<MostnewListInfo> newlistBeanList) {
        this.mContext = mContext;
        this.newlistBeanList = newlistBeanList;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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
    public long getItemId(int  position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
      ViewHolder viewHolder = null;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.team_left_fragment_item1,null,false);
            viewHolder = new ViewHolder(view);
        }
       else  {
            viewHolder = (ViewHolder) view.getTag();
        }
        MostnewListInfo  newlistBean = newlistBeanList.get(position);
        viewHolder.titleTv.setText(newlistBean.getSubject());
        viewHolder.shortText.setText(newlistBean.getSummary());
//        viewHolder.allTextTv.setText(newlistBean.getSummary());
        final ViewHolder finalViewHolder = viewHolder;

        viewHolder.typeTv.setText(newlistBean.getForum_name());
        String forn_icon = newlistBean.getForum_icon();
        String aid = newlistBean.getAuthorid();
        //获取用户头像的url
        String userLogo = DigtleUrl.getUserLogoUrl(aid);
        Picasso.with(mContext).load(forn_icon).placeholder(R.drawable.category_camera_small).into(viewHolder.typeIv);
        Picasso.with(mContext).load(userLogo).placeholder(R.drawable.avatar_middle).into(viewHolder.userLogo);
        viewHolder.userNameTv.setText(newlistBean.getAuthor());
        viewHolder.shortText.getLineHeight();
        viewHolder.commentTv.setText(newlistBean.getReplies());
        viewHolder.likeTv.setText(newlistBean.getRecommend_add());
        //判断第一个textview的行数是否超过了8，没有就设置按钮的影藏
        int   attachCount = newlistBean.getAttachcount();
        switch (attachCount){
            case 0:
                //一张图片的话设置imageview的隐藏

                viewHolder.singleImageIv.setVisibility(View.GONE);
                viewHolder.imageGrid.setVisibility(View.GONE);
                break;
            case 1:
                viewHolder.singleImageIv.setVisibility(View.VISIBLE);
                viewHolder.dividLineTv.setVisibility(View.GONE);
                viewHolder.imageGrid.setVisibility(View.GONE);
                String imageUrl = newlistBean.getAttachList().get(0);
                Picasso.with(mContext).load(imageUrl).placeholder(R.drawable.article_default).into(viewHolder.singleImageIv);

                break;
            default:
                viewHolder.singleImageIv.setVisibility(View.GONE);
                viewHolder.imageGrid.setVisibility(View.VISIBLE);
                GridAdapter gridAdapter = new GridAdapter(newlistBean.getAttachList(),mContext);
                viewHolder.imageGrid.setAdapter(gridAdapter);
                break;
        }

          return view;
    }
    class ViewHolder{
        @BindView(R.id.leftfregment_name_tv)
        TextView typeTv;
        @BindView(R.id.leftfregment_avatar_image)
        CircleImageView userLogo;
        @BindView(R.id.leftfregment_icon_iv)
        ImageView typeIv;
        @BindView(R.id.user_name_tv)
        TextView userNameTv;
        @BindView(R.id.left_dividline)
        TextView dividLineTv;
        @BindView(R.id.leftfragment_timeline)
        TextView  timeTv;
        @BindView(R.id.leftfragment_title)
        TextView titleTv;
        @BindView(R.id.leftfragment_text_short)
        TextView shortText;
        @BindView(R.id.show_all)
        TextView showAllTv;
        @BindView(R.id.text_all)
        TextView allTextTv;
        @BindView(R.id.show_short)
        TextView showShortTv;
        @BindView(R.id.pic_image)
        ImageView singleImageIv;
        @BindView(R.id.team_fragment_item_grid)
        CustomGridview  imageGrid;
        @BindView(R.id.left_frgmet_comment)
        TextView commentTv;
        @BindView(R.id.left_frgmet_like)
        TextView likeTv;
   public ViewHolder(View view){
       view.setTag(this);
       ButterKnife.bind(this,view);
   }


    }
}
