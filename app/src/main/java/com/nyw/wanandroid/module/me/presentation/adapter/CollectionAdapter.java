package com.nyw.wanandroid.module.me.presentation.adapter;

import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nyw.domain.domain.bean.response.home.CollectionBean;
import com.nyw.libwidgets.utils.img.CustomRoundedCornersTransformation;
import com.nyw.wanandroid.module.home.presentation.widget.CollectView;
import com.nyw.wanandroid.R;



public class CollectionAdapter extends BaseQuickAdapter<CollectionBean, BaseViewHolder> {

    private OnCollectViewClickListener mOnCollectViewClickListener = null;

    public CollectionAdapter() {
        super(R.layout.adapter_home);
    }

    public void setOnCollectViewClickListener(OnCollectViewClickListener onCollectViewClickListener) {
        mOnCollectViewClickListener = onCollectViewClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionBean item) {
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_desc = helper.getView(R.id.tv_desc);
        if (TextUtils.isEmpty(item.getDesc())) {
            tv_title.setSingleLine(false);
            tv_desc.setVisibility(View.GONE);
            tv_desc.setText("");
        } else {
            tv_title.setSingleLine(true);
            tv_desc.setVisibility(View.VISIBLE);
            tv_desc.setText(Html.fromHtml(item.getDesc()));
        }
        helper.setText(R.id.tv_title, Html.fromHtml(item.getTitle()));
        helper.setText(R.id.tv_author, item.getAuthor());
        helper.setText(R.id.tv_time, item.getNiceDate());
        TextView tv_super_chapter_name = helper.getView(R.id.tv_super_chapter_name);
        tv_super_chapter_name.setVisibility(View.GONE);
        FrameLayout fl_dot = helper.getView(R.id.fl_dot);
        fl_dot.setVisibility(View.GONE);
        helper.setText(R.id.tv_chapter_name, item.getChapterName());
        LinearLayout ll_new = helper.getView(R.id.ll_new);
        ll_new.setVisibility(View.GONE);
        ImageView iv_img = helper.getView(R.id.iv_img);
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            Glide.with(mContext).load(item.getEnvelopePic())
                    .apply(new RequestOptions().transform(new CustomRoundedCornersTransformation
                            (ConvertUtils.dp2px(2), 0)))
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into((ImageView) iv_img);
            iv_img.setVisibility(View.VISIBLE);
        } else {
            iv_img.setVisibility(View.GONE);
        }
        CollectView cv_collect = helper.getView(R.id.cv_collect);
        cv_collect.setChecked(true);
        TextView tv_tag = helper.getView(R.id.tv_tag);
        tv_tag.setVisibility(View.GONE);
        cv_collect.setOnClickListener(new CollectView.OnClickListener() {
            @Override
            public void onClick(CollectView v) {
                if (mOnCollectViewClickListener != null) {
                    mOnCollectViewClickListener.onClick(helper, v, helper.getAdapterPosition() - getHeaderLayoutCount());
                }
            }
        });
    }

    public interface OnCollectViewClickListener {
        void onClick(BaseViewHolder helper, CollectView v, int position);
    }
}
