package com.hzy.smarteq;

import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;

/**
 * ���ܱ�����
 * @author dzl
 *
 */
public class SearchInNearbyActivity extends PoiSearchBaseActivity {

	@Override
	public void poiSearchInit() {
		poiSearch.searchNearby(getSearchParams());
	}
	

	private PoiNearbySearchOption getSearchParams() {
		PoiNearbySearchOption params = new PoiNearbySearchOption();
		params.location(hmPos);	// ָ�����ĸ�λ������
		params.radius(5000);	// ָ��������Χ
		params.keyword("��������");	// ָ������������
		return params;
	}


	/** ��ȡ��Ȥ��������Ϣ */
	@Override
	public void onGetPoiDetailResult(PoiDetailResult result) {
		
	}

}
