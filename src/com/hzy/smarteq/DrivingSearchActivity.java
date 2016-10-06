package com.hzy.smarteq;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

public class DrivingSearchActivity extends RoutePlanSearchBaseActivity {

	@Override
	public void routePlanSearchInit() {
		routePlanSearch.drivingSearch(getSearchParams());
	}
	
	private DrivingRoutePlanOption getSearchParams() {
		DrivingRoutePlanOption params = new DrivingRoutePlanOption();
		List<PlanNode> nodes = new ArrayList<PlanNode>();
		nodes.add(PlanNode.withCityNameAndPlaceName("洛阳市", "中国国花园"));
		params.from(PlanNode.withLocation(hmPos));	// 设置起点
		params.passBy(nodes);						// 设置途经点
		params.to(PlanNode.withLocation(czPos));	// 设置终点
		return params;
	}

	/** 获取驾车搜索结果的回调方法 */
	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
		DrivingRouteOverlay overlay = new DrivingRouteOverlay(baiduMap);
		baiduMap.setOnMarkerClickListener(overlay);
		List<DrivingRouteLine> routeLines = result.getRouteLines();	// 获取到所有的搜索路线，最优化的路线会在集合的前面
		overlay.setData(routeLines.get(0));	// 把搜索结果设置到覆盖物
		overlay.addToMap();					// 把搜索结果添加到地图
		overlay.zoomToSpan();				// 把搜索结果在一个屏幕内显示完
	}

	/** 获取换乘（公交、地铁）搜索结果的回调方法 */
	@Override
	public void onGetTransitRouteResult(TransitRouteResult result) {
	}

	/** 获取步行搜索结果的回调方法 */
	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult result) {
	}

}
