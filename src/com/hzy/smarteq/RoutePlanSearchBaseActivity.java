package com.hzy.smarteq;

import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;

public abstract class RoutePlanSearchBaseActivity extends BaseActivity implements OnGetRoutePlanResultListener {

	protected RoutePlanSearch routePlanSearch;

	@Override
	public void init() {
		routePlanSearch = RoutePlanSearch.newInstance();
		routePlanSearch.setOnGetRoutePlanResultListener(this);
		routePlanSearchInit();
	}
	
	/** ·��������������д��������� ��*/
	public abstract void routePlanSearchInit();

}
