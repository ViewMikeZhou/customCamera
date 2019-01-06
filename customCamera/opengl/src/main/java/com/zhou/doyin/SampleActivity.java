package com.zhou.doyin;

import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.opengl.EGLContext;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhou.doyin.record.OnFrameAvailableListener;
import com.zhou.doyin.record.OnSurfaceCreatedCallback;
import com.zhou.doyin.record.RecordSurfaceView;
import com.zhou.doyin.video.OffScreenWrapper;
import com.zhou.doyin.video.VideoFrameData;

/**
 * Created by zhou on 2019/1/6.
 */
public class SampleActivity extends AppCompatActivity implements OnSurfaceCreatedCallback, OnFrameAvailableListener {
    private EGLContext eglContext;
    private OffScreenWrapper mWrapper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecordSurfaceView surfaceView = null;
        surfaceView.setOnSurfaceCreatedCallback(this);
        surfaceView.setFrameListener(this);
    }

    @Override
    public void onSurfaceCreated(SurfaceTexture texture, EGLContext context) {
        this.eglContext = context;
        // 此处有texture可以开启camera的预览
    }

    boolean isStartRecorder = false;
    public void  startRecord(){
        //需要先创建Mediacodec的surface
        MediaCodec codec = null;
        mWrapper = new OffScreenWrapper(eglContext,codec.createInputSurface());
    }

    @Override
    public void onFrameAvailable(VideoFrameData frameData) {
        //此处只要有可用帧就会回掉
        if (isStartRecorder){
          //  mWrapper.draw();
        }

    }
}
