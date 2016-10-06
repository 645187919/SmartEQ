package com.hzy.smarteq;

import android.view.KeyEvent;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;

public class SearchInCityActivity extends PoiSearchBaseActivity {

	private int pageNum;

	@Override
	public void poiSearchInit() {
		poiSearch.searchInCity(getSearchParams());
		
	}
	
	@Override
	public boolean onPoiClick(int index) {
		// �����һ��������������Ȥ���ʱ����ȥ��������ϸ������
		PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(index);
		poiSearch.searchPoiDetail(getSearchDetailParams(poiInfo.uid));
		return true;
	}
	
	private PoiDetailSearchOption getSearchDetailParams(String poiUid) {
		PoiDetailSearchOption params = new PoiDetailSearchOption();
		params.poiUid(poiUid);
		return params;
	}

	private PoiCitySearchOption getSearchParams() {
		PoiCitySearchOption params = new PoiCitySearchOption();
		params.city("֣��");			// ָ�����ĸ������ڽ�������
		params.keyword("����վ");		// ָ����������ʲô����
		params.pageCapacity(10);	// ����һҳ��ȡ10������
		params.pageNum(pageNum);	// ָ����ȡ��һҳ
		return params;
	}

	/** ��ȡ��������Ϣ�Ļص����� */
	@Override
	public void onGetPoiDetailResult(PoiDetailResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			showToast("û��������������Ϣ");
			return ;
		}
		
		showToast(result.getShopHours() + ", " + result.getTelephone());
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_1) {
			// ��ȡ��һҳ����
			pageNum++;
			poiSearch.searchInCity(getSearchParams());
		}
		return super.onKeyDown(keyCode, event);
	}

}
