package com.hzy.smarteq;

import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;

/**
 * 在周边搜索
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
		params.location(hmPos);	// 指定在哪个位置搜索
		params.radius(5000);	// 指定搜索范围
		params.keyword("建设银行");	// 指定搜索的内容
		return params;
	}


	/** 获取兴趣点详情信息 */
	@Override
	public void onGetPoiDetailResult(PoiDetailResult result) {
		
	}

}
