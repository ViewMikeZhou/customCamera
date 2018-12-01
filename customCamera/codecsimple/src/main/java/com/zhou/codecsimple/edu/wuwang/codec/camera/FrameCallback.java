package com.zhou.codecsimple.edu.wuwang.codec.camera;

/**
 * Description:
 */
public interface FrameCallback {

    void onFrame(byte[] bytes, long time);

}
