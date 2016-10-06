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
		nodes.add(PlanNode.withCityNameAndPlaceName("������", "�й�����԰"));
		params.from(PlanNode.withLocation(hmPos));	// �������
		params.passBy(nodes);						// ����;����
		params.to(PlanNode.withLocation(czPos));	// �����յ�
		return params;
	}

	/** ��ȡ�ݳ���������Ļص����� */
	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
		DrivingRouteOverlay overlay = new DrivingRouteOverlay(baiduMap);
		baiduMap.setOnMarkerClickListener(overlay);
		List<DrivingRouteLine> routeLines = result.getRouteLines();	// ��ȡ�����е�����·�ߣ����Ż���·�߻��ڼ��ϵ�ǰ��
		overlay.setData(routeLines.get(0));	// ������������õ�������
		overlay.addToMap();					// �����������ӵ���ͼ
		overlay.zoomToSpan();				// �����������һ����Ļ����ʾ��
	}

	/** ��ȡ���ˣ���������������������Ļص����� */
	@Override
	public void onGetTransitRouteResult(TransitRouteResult result) {
	}

	/** ��ȡ������������Ļص����� */
	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult result) {
	}

}
