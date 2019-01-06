package com.zhou.doyin.record;

import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;


/**
 *
 */
public interface OnSurfaceCreatedCallback {
    void onSurfaceCreated(SurfaceTexture texture, EGLContext context);
}
