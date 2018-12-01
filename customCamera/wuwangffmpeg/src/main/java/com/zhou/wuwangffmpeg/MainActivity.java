package com.zhou.wuwangffmpeg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhou.wuwangffmpeg.demo.AACDecoderDemo;
import com.zhou.wuwangffmpeg.demo.H264DecoderDemo;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FFMpeg.init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mBtnDecodeH264:
                openActivity(H264DecoderDemo.class);
                break;
            case R.id.mBtnDecodeAAC:
                openActivity(AACDecoderDemo.class);
                break;
            case R.id.mBtnDecodeMP4:
                break;
        }
    }

    public void openActivity(Class clazz){
        startActivity(new Intent(this,clazz));
    }
}
