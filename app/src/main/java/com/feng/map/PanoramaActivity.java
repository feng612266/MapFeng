package com.feng.map;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.panoramaview.PanoramaView;
import com.baidu.mapapi.map.BaiduMap;
//百度全景图
public class PanoramaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private MapFengApplication app;
    private Context mContext;
    private PanoramaView mPanoramaView = null;
    private BaiduMap mBaiduMap;
    private BMapManager mBMapManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panorama);
        mContext = this;


        //获取地图控件引用
        mPanoramaView = (PanoramaView) findViewById(R.id.panorama);
        /*if (app.mBMapManager == null) {
            app.mBMapManager = new BMapManager(app);
            //app.mBMapManager.init(new MapFengApplication.MyGeneralListener());
        }*/
        mPanoramaView.setPanorama("0100220000130817164838355J5");

    }


    @Override
    protected void onResume() {
        super.onResume();
        //在 activity 执行 onDestroy 时执行 mPanoramaView.onResume()，实现地图生命周期管理
        mPanoramaView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在 activity 执行 onPause 时执行 mPanoramaView. onPause ()，实现地图生命周期管理
        mPanoramaView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在 activity 执行 onPause 时执行 mPanoramaView. onPause ()，实现地图生命周期管理
        //mPanoramaView.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_normal:
                Log.e(TAG,"普通普通普通普通");
                Toast.makeText(mContext, "切换成普通地图", Toast.LENGTH_SHORT).show();
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.bt_site:
                Log.e(TAG,"卫星卫星卫星卫星");
                Toast.makeText(mContext, "切换成卫星地图", Toast.LENGTH_SHORT).show();
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            default:
                break;
        }
    }
}
