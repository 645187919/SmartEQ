package com.hzy.smarteq;

import com.baidu.mapapi.SDKInitializer;
import com.hzy.smarteq.Utils;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DemoListActivity extends ListActivity {

	private BroadcastReceiver receiver;
	private ClassAndName[] datas = {
			new ClassAndName(HelloBaiduMapActivity.class, "HelloBaiduMap"),
//			new ClassAndName(MapLayerActivity.class, "��ͼͼ��"),
//			new ClassAndName(CircelOverlayActivity.class, "Բ�θ�����"),
//			new ClassAndName(TextOverlayActivity.class, "���ָ�����"),
//			new ClassAndName(MarkerOverlayActivity.class, "��־������"),
			new ClassAndName(SearchInBoundActivity.class, "�ڷ�Χ������"),
			new ClassAndName(SearchInCityActivity.class, "�ڳ���������"),
			new ClassAndName(SearchInNearbyActivity.class, "���ܱ�������"),
			new ClassAndName(DrivingSearchActivity.class, "�ݳ�·������"),
			new ClassAndName(TransitSearchActivity.class, "����·������"),
			new ClassAndName(WalkingSearchActivity.class, "����·������"),
			new ClassAndName(LocationActivity.class, "��λ"),
			new ClassAndName(com.hzy.smarteq.jbq.SimplePedometerActivity.class,"�Ʋ���"),
			new ClassAndName(com.hzy.smarteq.xinlv.MainActivity.class,"���ʼ�"),
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerSDKCheckReceiver();
		ArrayAdapter<ClassAndName> adapter = 
				new ArrayAdapter<DemoListActivity.ClassAndName>(this, android.R.layout.simple_list_item_1, datas);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// ȡ������λ�õ�ClassAndName
		ClassAndName classAndName = (ClassAndName) l.getItemAtPosition(position);
		startActivity(new Intent(this, classAndName.clazz));
	}
	
	
	class ClassAndName {
		/** ���ڱ���Activity���ֽ��� */
		public Class<?> clazz;
		/** ���ڱ���Activity��Ӧ������ */
		public String name;
		public ClassAndName(Class<?> cls, String name) {
			this.clazz = cls;
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}
	
	private void registerSDKCheckReceiver() {
		receiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction();
				if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR.equals(action)) {
					Utils.showToast(DemoListActivity.this, "�������");
				} else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR.equals(action)) {
					Utils.showToast(DemoListActivity.this, "key��֤ʧ��");
				}
			}
		};
		IntentFilter filter = new IntentFilter();
		// �����������
		filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		// �����ٶȵ�ͼsdk ��key�Ƿ���ȷ
		filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		registerReceiver(receiver, filter);
	}
	
	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}
}
