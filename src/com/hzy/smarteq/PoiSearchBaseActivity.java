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
		// 因为其它搜索也需要这个实例，所以放在父类初始，这样的话子类就不需要再实例化
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
	 * 生成这个方法，是为了让子类可以覆盖
	 * @param index
	 * @return
	 */
	public boolean onPoiClick(int index) {
		PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(index);
		showToast(poiInfo.name + ", " + poiInfo.address);
		return true;
	}
	
	/** poi搜索的初始化代码写在这个方法 */
	public abstract void poiSearchInit();

	// 因为其它搜索结果的处理都是相同的，所以放在父类
	/** 获取兴趣点信息 */
	@Override
	public void onGetPoiResult(PoiResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			showToast("没有搜索到结果");
			return;
		}
		
		poiOverlay.setData(result);	// 把数据设置给覆盖物
		poiOverlay.addToMap();		// 把所有的数据的变成覆盖添加到BaiduMap
		poiOverlay.zoomToSpan();	// 把所有的搜索结果在一个屏幕内显示出来
	}

}
