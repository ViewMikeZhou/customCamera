package com.zhou.customcamera.camera2;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.Range;
import android.util.Size;

/**
 * Created by zhou on 2018/10/19.
 */
public class CameraController {

    private final CameraManager mManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CameraController(Context context) {
        mManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
    }

    public void readPamarter(String camerId) throws CameraAccessException {
        if (Build.VERSION.SDK_INT > 21) {
            CameraCharacteristics cameraCharacteristics = mManager.getCameraCharacteristics(camerId);
            StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            Range<Integer>[] highSpeedVideoFpsRanges = streamConfigurationMap.getHighSpeedVideoFpsRanges();
            if (highSpeedVideoFpsRanges == null || highSpeedVideoFpsRanges.length == 0) {
                Log.e("test", "no support high speed");
                return;
            }
            getHighSpeedSize(streamConfigurationMap,highSpeedVideoFpsRanges);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getHighSpeedSize(StreamConfigurationMap streamConfigurationMap, Range<Integer>[] highSpeedVideoFpsRanges ) {
        for (Range<Integer> highSpeedVideoFpsRange : highSpeedVideoFpsRanges) {
            if (highSpeedVideoFpsRange.getLower() == highSpeedVideoFpsRange.getUpper()) {
                Size[] highSpeedVideoSizesFor = streamConfigurationMap.getHighSpeedVideoSizesFor(highSpeedVideoFpsRange);
                for (Size size : highSpeedVideoSizesFor) {
                    Log.e("test", "fps : " + highSpeedVideoFpsRange.getLower() + " size -> width : " + size.getWidth() + " height: " + size.getHeight());
                }
            }
        }
    }
}
