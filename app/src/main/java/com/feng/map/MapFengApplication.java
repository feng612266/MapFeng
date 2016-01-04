package com.feng.map;

import android.app.Application;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.MKGeneralListener;
import com.baidu.mapapi.SDKInitializer;

/**
 * Created by fenggj on 2016/1/3.
 */
public class MapFengApplication extends Application implements MKGeneralListener{


    public BMapManager mBMapManager;
    public MKGeneralListener MyGeneralListener;
    @Override
    public void onCreate() {
        super.onCreate();

        //在使用 SDK 各组件之前初始化 context 信息，传入 ApplicationContext
        // 注意该方法要再 setContentView 方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        mBMapManager = new BMapManager(this);

    }

    @Override
    public void onGetPermissionState(int i) {

    }
}
