package com.feng.map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Context mContext;
    private MapView mMapView = null;
    private Button bt_normal,bt_site,bt_panorama;
    private BaiduMap mBaiduMap;
    private Spinner sp;
    private List<String> data=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        //获取地图控件引用
         mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        bt_panorama=(Button) findViewById(R.id.bt_panorama);
        bt_normal=(Button) findViewById(R.id.bt_normal);
        bt_site=(Button) findViewById(R.id.bt_site);
        bt_panorama.setOnClickListener(this);
        bt_normal.setOnClickListener(this);
        bt_site.setOnClickListener(this);
        sp = (Spinner)findViewById(R.id.sp);
        data.add("功能");
        data.add("开启交通流量图");
        data.add("关闭交通流量图");
        data.add("开启热力图");
        data.add("关闭热力图");
        data.add("添加标注");
        data.add("文字标注");
        data.add("移除标注");
        // 给 spinner 设置适配器
        sp.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        //功能;
                        break;
                    case 1:
                    // 开启交通流量图
                      mBaiduMap.setTrafficEnabled(true);
                    break;
                    case 2:
                        // 关闭交通流量图
                        mBaiduMap.setTrafficEnabled(false);
                        break;
                    case 3:
                        // 开启热力图
                        mBaiduMap.setBaiduHeatMapEnabled(true);
                        break;
                    case 4:
                        // 关闭热力图
                        mBaiduMap.setBaiduHeatMapEnabled(false);
                        break;
                    case 5:
                        // 添加标注
                        // addMarker();
                        // 添加多个标注，标注的图片每 500ms 切换一次
                        addMarkers();
                        break;
                    case 6:
                        // 添加文字标注
                        addTextMarker();
                        break;
                    case 7:
                        // 移除标注
                        //mBaiduMap.removeL(null);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    protected void addMarkers() {
      //经纬度坐标，第一个参数为纬度，第二个参数为经度
        LatLng latLng = new LatLng(39.963175, 116.400244);
        ArrayList<BitmapDescriptor> bitmapDescriptors = new ArrayList<BitmapDescriptor>();
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_openmap_focuse_mark);
        BitmapDescriptor bitmapDescriptor2 = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_openmap_mark);
        bitmapDescriptors.add(bitmapDescriptor2);
        bitmapDescriptors.add(bitmapDescriptor);
           //给 option 添加一个标注集合，实现每 500ms 切换一次
           // icons 中只可以传入 ArrayList 的对象
              OverlayOptions option = new MarkerOptions().position(latLng).icons(
               bitmapDescriptors);
           // 添加图层
              mBaiduMap.addOverlay(option);
             }


    protected void addTextMarker() {
            //经纬度坐标
        LatLng latLng = new LatLng(39.963175, 116.400244);
                 //设置参数，位置，背景颜色，字体颜色，字体大小，偏转角度，文字
        OverlayOptions option = new TextOptions().position(latLng).
                 bgColor(Color.YELLOW).fontColor(Color.GREEN).fontSize(36).rotate(-45).text("FengFengFengFeng");
       //添加图层
        mBaiduMap.addOverlay(option);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在 activity 执行 onDestroy 时执行 mMapView.onResume()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在 activity 执行 onPause 时执行 mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在 activity 执行 onPause 时执行 mMapView. onPause ()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_panorama:
                Log.e(TAG, "全景全景全景全景");
                Toast.makeText(mContext, "切换成全景地图", Toast.LENGTH_SHORT).show();
                Intent intentPanorama = new Intent(mContext,PanoramaActivity.class);
                startActivity(intentPanorama);
                break;
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
