package com.viewwang.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.viewwang.materialdesign.view.demo1View;

/**
 * Title:DemoOneActivity
 * Package:com.viewwang.materialdesign.fragment
 * Description:TODO
 * Author: wjx@tomcat360.com
 * Date: 2017/3/6 0006 11:10
 * Version: V1.0.0
 * 版本号修改日期修改人修改内容
 */
public class DemoOneActivity extends AppCompatActivity {
    private demo1View demo1View;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1_layout);
        initView();
    }

    private void initView() {
        demo1View = (com.viewwang.materialdesign.view.demo1View) findViewById(R.id.btn_demo1);
        demo1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demo1View.addHeard();
            }
        });
    }
}
