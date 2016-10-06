package com.hzy.smarteq;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.hzy.smarteq.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {
	
	private static final String TAG = "BaseActivity";
	
	/** �������꣨�����к�������������·45�ţ�*/
	protected LatLng hmPos = new LatLng(34.822584,113.543431);
	/** �������� */
	protected LatLng czPos = new LatLng(34.753194,113.665386);
	/** �찲������ */
	protected LatLng tamPos = new LatLng(34.749051,113.645819);


	protected MapView mapView;
	protected BaiduMap baiduMap;

	// �����final��Ϊ�˲������า�ǣ�ԭ����Ϊ��Ԥ�������һЩ�໹û��ʼ����ʱ��ͱ��������
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡ��ͼ�ؼ�����  
        mapView = (MapView) findViewById(R.id.bmapView); 
        
        baiduMap = mapView.getMap();	// ��ȡ��ͼ������
        
        // 1.	�������Ű�ť��������
        // mapView.showScaleControl(false);	// ���ر�����ť��Ĭ������ʾ��
        // mapView.showZoomControls(false);	// �������Ű�ť��Ĭ������ʾ��
        
        // 2.	��ȡ��ȡ��С��3����������ż���20��
        float maxZoomLevel = baiduMap.getMaxZoomLevel(); // ��ȡ��ͼ������ż���
        float minZoomLevel = baiduMap.getMinZoomLevel(); // ��ȡ��ͼ��С���ż���
        Log.i(TAG, "minZoomLevel = " + minZoomLevel + ", maxZoomLevel" + maxZoomLevel);
        
		// 3.	���õ�ͼ���ĵ�Ϊ����
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(hmPos);
        baiduMap.setMapStatus(mapStatusUpdate);
        
        // 4.	���õ�ͼ����Ϊ15
        mapStatusUpdate = MapStatusUpdateFactory.zoomTo(15);
        baiduMap.setMapStatus(mapStatusUpdate);
        
        // 6.	��ȡ��ͼUi������������ָ����
        // UiSettings uiSettings = baiduMap.getUiSettings();
        // uiSettings.setCompassEnabled(false);	//  ����ʾָ����
        
        init();
	}
	
	/** �������������ʵ�� */
	public abstract void init();
	
	/**
	 * ����Ļ������ʾһ��Toast
	 * @param text
	 */
	public void showToast(CharSequence text) {
		Utils.showToast(this, text);
	}
	
	@Override  
	protected void onDestroy() {  
		super.onDestroy();  
		//��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
		mapView.onDestroy();  
	} 
	
	@Override  
	protected void onResume() {  
		super.onResume();  
		//��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
		mapView.onResume();  
	}  
	
	@Override  
	protected void onPause() {  
		super.onPause();  
		//��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
		mapView.onPause();  
	}  
}
