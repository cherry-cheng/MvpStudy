package com.weizhan.superlook.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.weizhan.superlook.R;

/**
 * Created by Administrator on 2018/9/13.
 */

public class SharePopWindow extends PopupWindow {
    private View mView;

    public SharePopWindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        initView(context, itemsOnClick);
    }

    private void initView(final Activity context, View.OnClickListener itemsOnClick) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.popwindow_share, null);
        LinearLayout weiXFriend = (LinearLayout) mView.findViewById(R.id.weixinf);
        LinearLayout wechatCircle = (LinearLayout) mView.findViewById(R.id.wechatc);
        LinearLayout qqFriend = (LinearLayout) mView.findViewById(R.id.qqFriend);
        LinearLayout sina = (LinearLayout) mView.findViewById(R.id.sina);
        TextView cancelTv = (TextView) mView.findViewById(R.id.share_cancle);

        cancelTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dismiss();
                backgroundAlpha(context, 1f);
            }
        });

        //设置按钮监听
        weiXFriend.setOnClickListener(itemsOnClick);
        wechatCircle.setOnClickListener(itemsOnClick);
        qqFriend.setOnClickListener(itemsOnClick);
        sina.setOnClickListener(itemsOnClick);

        this.setContentView(mView);
        this.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setTouchable(true);
        //设置非PopupWindow区域是否可触摸
//    this.setOutsideTouchable(false);
        //设置SelectPicPopupWindow弹出窗体动画效果
//    this.setAnimationStyle(R.style.select_anim);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);
        backgroundAlpha(context, 0.5f);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(context, 1f);
            }
        });
    }

    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
