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
		
		// 指定搜索范围，由一个西南点和一个东北点组成的范围
		LatLngBounds bounds = new LatLngBounds.Builder().include(new LatLng(34.822584,113.543431))
														.include(new LatLng(34.764898,113.595639)).build();
		params.bound(bounds);	// 指定搜索范围
		params.keyword("加油站");	// 指定搜索什么内容
		return params;
	}

	/** 获取兴趣点详情信息 */
	@Override
	public void onGetPoiDetailResult(PoiDetailResult result) {
		
	}

}
