package com.letv.cases.leui.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Wifi extends LetvTestCaseMTBF {

	private String url = "www.sina.cn";

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		closeAPM();
		closeWifi();
		press_back(5);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		openWifi();
		press_back(5);
		super.tearDown();

	}

	private void browseWebPage() throws UiObjectNotFoundException {
		launchApp(AppName.BROWSER);
		sleepInt(3);
		LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
		verify("Can't find home page button.", home.exists());
		home.click();
		// url input edit text
		LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url_input_view"));
		verify("Can't find url input EditText.", urlEdit.exists());
		urlEdit.click();
		callShell("input text " + url);
		sleepInt(1);
		getUiDevice().pressEnter();
		sleepInt(10);
		LeUiObject sina1 = new LeUiObject(new UiSelector().textContains("新浪").resourceId(
				"com.android.browser:id/url_input_view"));
		LeUiObject load = new LeUiObject(new UiSelector().textContains("正在加载").resourceId(
				"com.android.browser:id/url_input_view"));
		if(!(sina1.exists()) && load.exists()){
			addStep("网页正在加载中,等10秒钟");
			sleepInt(10);
		}
		// refresh web page
		for (int i = 0; i < 4; i++) {
			addStep("刷新页面");
			LeUiObject refresh = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/address_bar_inner_right_function_view"));
			verify("Can't find url input EditText.", refresh.exists());
			refresh.click();
			sleepInt(5);
		}
	}

	@CaseName("wifi")
	public void testWifiConnection() throws UiObjectNotFoundException {
		addStep("打开wifi");
		openWifi();
		addStep("建立wifi连接");
		sleepInt(5);
		LeUiObject connectInfo = new LeUiObject(new UiSelector().resourceId(
				"android:id/summary").text("已连接"));
		verify("Can't connect to wifi.", connectInfo.exists());
		press_back(5);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("打开浏览器，进入特定网页，每隔5S刷新一次，持续20S");
			browseWebPage();
			addStep("退出浏览器");
			press_back(5);
		}
		closeWifi();
		press_back(5);
	}
}
