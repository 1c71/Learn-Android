package com.example.agoodob.criminalintent;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 *
 */
public class CrimeCameraFragment extends Fragment {

    private static final String TAG = "CrimeCameraFragment";

    public static final String EXTRA_PHOTO_FILENAME =
            "com.example.asdaohfaoeu.photo_filename";

    private android.hardware.Camera mCamera;
    private SurfaceView mSurfaceView;

    private View mProgressContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crime_camera, container, false);

        mProgressContainer = v.findViewById(R.id.crime_camera_progressContainer);
        mProgressContainer.setVisibility(View.INVISIBLE);

        Button takePictureButton = (Button) v.findViewById(R.id.crime_camera_takePictureButton);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCamera != null){
                    mCamera.takePicture(mShutterCallback, null, mJpegCallback);
                }
            }
        });

        mSurfaceView = (SurfaceView) v.findViewById(R.id.crime_camera_surfaceView);
        SurfaceHolder holder = mSurfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(new SurfaceHolder.Callback(){

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                try {
                    if(mCamera != null) {
                        mCamera.setPreviewDisplay(holder);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error setting up preview display", e);
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if( mCamera == null) return;

                android.hardware.Camera.Parameters para = mCamera.getParameters();

                android.hardware.Camera.Size s = getBestSupportSize(
                        para.getSupportedPreviewSizes(),
                        width, height);

                para.setPreviewSize(s.width, s.height);

                s = getBestSupportSize(para.getSupportedPictureSizes(), width, height);
                // 参数1 ..PictureSizes() 返回可用的图片尺寸的列表
                para.setPictureSize(s.width, s.height);

                mCamera.setParameters(para);

                try{
                    mCamera.startPreview();
                } catch (Exception e) {
                    Log.e(TAG, "无法开始预览", e);
                    mCamera.release();
                    mCamera = null;
                }

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(mCamera!=null){
                    mCamera.stopPreview();
                }
            }
        });

        return v;
    }


    /*
        ShutterCallback
        相机回调方法
        相机捕获图片，但尚未完成时调用
     */
    private android.hardware.Camera.ShutterCallback mShutterCallback
            = new android.hardware.Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            mProgressContainer.setVisibility(View.VISIBLE);
        }
    };

    /*
        PictureCallback
     */
    private android.hardware.Camera.PictureCallback mJpegCallback
            = new android.hardware.Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, android.hardware.Camera camera) {

            String filename = UUID.randomUUID().toString() + ".jpg";

            FileOutputStream os = null;
            boolean success = true;

            try {
                os = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
                os.write(data);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                success = false;
            } catch (IOException e) {
                e.printStackTrace();
                success = false;
            }

            // 如果成功就 setResult
            if(success) {
                Intent i = new Intent();
                i.putExtra(EXTRA_PHOTO_FILENAME, filename);
                getActivity().setResult(Activity.RESULT_OK, i);
            } else {
                getActivity().setResult(Activity.RESULT_CANCELED);
            }
            getActivity().finish(); // 结束
        }
    };


    @TargetApi(9)
    @Override
    public void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            mCamera = android.hardware.Camera.open(0);
        } else {
            mCamera = android.hardware.Camera.open();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if(mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    /*
        找出设备支持的最佳尺寸
     */
    private android.hardware.Camera.Size getBestSupportSize(
            List<android.hardware.Camera.Size> sizes,
            int width, int height) {
        android.hardware.Camera.Size bestSize = sizes.get(0);
        int largestArea = bestSize.width * bestSize.height;

        for (android.hardware.Camera.Size s : sizes) {
            int area = s.width * s.height;
            if (area > largestArea) {
                bestSize = s;
                largestArea = area;
            }
        }
        return bestSize;
    }




















}
