package com.hzy.smarteq;

import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public abstract class PoiSearchBaseActivity extends BaseActivity implements OnGetPoiSearchResultListener{

	protected PoiSearch poiSearch;
	protected PoiOverlay poiOverlay;

	@Override
	public final void init() {
		// ��Ϊ��������Ҳ��Ҫ���ʵ�������Է��ڸ����ʼ�������Ļ�����Ͳ���Ҫ��ʵ����
		poiSearch = PoiSearch.newInstance();
		poiSearch.setOnGetPoiSearchResultListener(this);
		
		poiOverlay = new PoiOverlay(baiduMap) {
			@Override
			public boolean onPoiClick(int index) {
				return PoiSearchBaseActivity.this.onPoiClick(index);
			}
		};
		baiduMap.setOnMarkerClickListener(poiOverlay);
		
		poiSearchInit();
	}
	
	/**
	 * ���������������Ϊ����������Ը���
	 * @param index
	 * @return
	 */
	public boolean onPoiClick(int index) {
		PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(index);
		showToast(poiInfo.name + ", " + poiInfo.address);
		return true;
	}
	
	/** poi�����ĳ�ʼ������д��������� */
	public abstract void poiSearchInit();

	// ��Ϊ������������Ĵ�������ͬ�ģ����Է��ڸ���
	/** ��ȡ��Ȥ����Ϣ */
	@Override
	public void onGetPoiResult(PoiResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			showToast("û�����������");
			return;
		}
		
		poiOverlay.setData(result);	// ���������ø�������
		poiOverlay.addToMap();		// �����е����ݵı�ɸ�����ӵ�BaiduMap
		poiOverlay.zoomToSpan();	// �����е����������һ����Ļ����ʾ����
	}

}
