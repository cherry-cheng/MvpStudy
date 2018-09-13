package com.weizhan.superlook.ui.mine;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.base.BaseActivity;
import com.weizhan.superlook.R;

/**
 * Created by Administrator on 2018/9/13.
 */

public class DeclareActivity extends BaseActivity {
    private ImageView back;
    private TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declare);
        back = findViewById(R.id.back_last);
        tx = (TextView) findViewById(R.id.tv_content);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
