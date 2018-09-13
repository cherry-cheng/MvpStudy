package com.weizhan.superlook.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.weizhan.superlook.R;

/**
 * Created by Administrator on 2018/9/13.
 */

public class UpdateDialog extends Dialog {
    private Context context;
    private String title;
    private String content;
    private String confirmButtonText;
    private Boolean isForce = false;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {
        public void doConfirm();

        public void doCancel();
    }

    public UpdateDialog(Context context, String title, String confirmButtonText, String content, Boolean isForce) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.title = title;
        this.confirmButtonText = confirmButtonText;
        this.content = content;
        this.isForce = isForce;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_dialog, null);
        setContentView(view);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView content = (TextView) view.findViewById(R.id.content);
        Button update_bt = (Button) view.findViewById(R.id.update_bt);
        ImageView iv_close = (ImageView) view.findViewById(R.id.iv_close);

        if (isForce) {
            iv_close.setVisibility(View.GONE);
        } else {
            iv_close.setVisibility(View.VISIBLE);
        }

        title.setText(this.title);
        content.setText(this.content);
        update_bt.setText(this.confirmButtonText);

        update_bt.setOnClickListener(new clickListener());
        iv_close.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
    }

    public void setClickListener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.update_bt:
                    clickListenerInterface.doConfirm();
                    break;
                case R.id.iv_close:
                    clickListenerInterface.doCancel();
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
