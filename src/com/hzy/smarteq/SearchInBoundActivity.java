package com.hzy.smarteq;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;

public class SearchInBoundActivity extends PoiSearchBaseActivity {

	@Override
	public void poiSearchInit() {
		poiSearch.searchInBound(getSearchParams());
	}
	
	private PoiBoundSearchOption getSearchParams() {
		PoiBoundSearchOption params = new PoiBoundSearchOption();
		
		// ָ��������Χ����һ�����ϵ��һ����������ɵķ�Χ
		LatLngBounds bounds = new LatLngBounds.Builder().include(new LatLng(34.822584,113.543431))
														.include(new LatLng(34.764898,113.595639)).build();
		params.bound(bounds);	// ָ��������Χ
		params.keyword("����վ");	// ָ������ʲô����
		return params;
	}

	/** ��ȡ��Ȥ��������Ϣ */
	@Override
	public void onGetPoiDetailResult(PoiDetailResult result) {
		
	}

}
