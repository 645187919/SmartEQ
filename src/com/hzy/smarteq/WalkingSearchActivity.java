package com.hzy.smarteq;

import java.util.List;

import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption.TransitPolicy;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;

/**
 * 步行路线搜索
 * @author dzl
 *
 */
public class WalkingSearchActivity extends RoutePlanSearchBaseActivity {

	@Override
	public void routePlanSearchInit() {
		routePlanSearch.walkingSearch(getSearchParams());
	}
	
	private WalkingRoutePlanOption getSearchParams() {
		WalkingRoutePlanOption params = new WalkingRoutePlanOption();
		params.from(PlanNode.withLocation(hmPos));	// 设置起点
		params.to(PlanNode.withLocation(czPos));	// 设置终点
		return params;
	}


	/** 获取驾车搜索结果的回调方法 */
	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
	}

	/** 获取换乘（公交、地铁）搜索结果的回调方法 */
	@Override
	public void onGetTransitRouteResult(TransitRouteResult result) {
	}

	/** 获取步行搜索结果的回调方法 */
	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult result) {
		WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMap);
		baiduMap.setOnMarkerClickListener(overlay);
		List<WalkingRouteLine> routeLines = result.getRouteLines();	// 获取到所有的搜索路线，最优化的路线会在集合的前面
		overlay.setData(routeLines.get(0));	// 把搜索结果设置到覆盖物
		overlay.addToMap();					// 把搜索结果添加到地图
		overlay.zoomToSpan();				// 把搜索结果在一个屏幕内显示完
	}

}
