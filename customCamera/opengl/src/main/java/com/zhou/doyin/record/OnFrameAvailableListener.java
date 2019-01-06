package com.zhou.doyin.record;


import com.zhou.doyin.video.VideoFrameData;

/**
 * Created by 薛贤俊 on 2018/3/9.
 */

public interface OnFrameAvailableListener {
    void onFrameAvailable(VideoFrameData frameData);
}
