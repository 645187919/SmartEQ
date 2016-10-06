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
 * ����·������
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
		params.from(PlanNode.withLocation(hmPos));	// �������
		params.to(PlanNode.withLocation(czPos));	// �����յ�
		return params;
	}


	/** ��ȡ�ݳ���������Ļص����� */
	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
	}

	/** ��ȡ���ˣ���������������������Ļص����� */
	@Override
	public void onGetTransitRouteResult(TransitRouteResult result) {
	}

	/** ��ȡ������������Ļص����� */
	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult result) {
		WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMap);
		baiduMap.setOnMarkerClickListener(overlay);
		List<WalkingRouteLine> routeLines = result.getRouteLines();	// ��ȡ�����е�����·�ߣ����Ż���·�߻��ڼ��ϵ�ǰ��
		overlay.setData(routeLines.get(0));	// ������������õ�������
		overlay.addToMap();					// �����������ӵ���ͼ
		overlay.zoomToSpan();				// �����������һ����Ļ����ʾ��
	}

}
