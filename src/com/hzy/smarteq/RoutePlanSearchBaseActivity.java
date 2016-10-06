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
	
	/** 路径搜索初化代码写在这个方法 中*/
	public abstract void routePlanSearchInit();

}
