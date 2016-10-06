package com.hzy.smarteq;

import android.util.Log;
import android.view.KeyEvent;

import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;

public class HelloBaiduMapActivity extends BaseActivity {
	
	private static final String TAG = "HelloBaiduMapActivity";
	
	@Override
	public void init() {
		
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 5.	���µ�ͼ״̬
		MapStatusUpdate mapStatusUpdate = null;
		switch (keyCode) {
		case KeyEvent.KEYCODE_1:
			// 		1)	��С
			mapStatusUpdate = MapStatusUpdateFactory.zoomOut();
			break;
		case KeyEvent.KEYCODE_2:
			// 		2)	�Ŵ�
			mapStatusUpdate = MapStatusUpdateFactory.zoomIn();
			break;
		case KeyEvent.KEYCODE_3:
			// 		3)	��ת��0 ~ 360����ÿ����ԭ���Ļ���������ת30��
			MapStatus currentMapStatus = baiduMap.getMapStatus();	// ��ȡ��ͼ��ǰ��״̬
			float rotate = currentMapStatus.rotate + 30;
			Log.i(TAG, "rotate = " + rotate);
			MapStatus mapStatus = new MapStatus.Builder().rotate(rotate).build();
			mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
			
			break;
		case KeyEvent.KEYCODE_4:
			// 		4)	������0 ~ -45����ÿ����ԭ���Ļ������ٸ���-5��
			currentMapStatus = baiduMap.getMapStatus();	// ��ȡ��ͼ��ǰ��״̬
			float overlook = currentMapStatus.overlook - 5;
			Log.i(TAG, "overlook = " + overlook);
			mapStatus = new MapStatus.Builder().overlook(overlook).build();
			mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
			
			break;
		case KeyEvent.KEYCODE_5:
			// 		5)	�ƶ�
			mapStatusUpdate = MapStatusUpdateFactory.newLatLng(czPos);
			baiduMap.animateMapStatus(mapStatusUpdate, 2000);
			return super.onKeyDown(keyCode, event);
		}
		baiduMap.setMapStatus(mapStatusUpdate);
		return super.onKeyDown(keyCode, event);
	}

}
