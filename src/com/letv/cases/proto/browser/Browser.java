package com.letv.cases.proto.browser;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class Browser extends LetvTestCase {

	private String url = "www.sina.cn";

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		launchAppByPackage(IntentConstants.proto_browser);
		callShell("am force-stop com.google.android.browser");
	}

	@CaseName("打开特定的web页面")
	public void testOpenPage() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		launchAppByPackage(IntentConstants.proto_browser);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		UiObject urlEdit = new UiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url"));
		// sina home page
		UiObject sina = new UiObject(new UiSelector().resourceId(
				"com.android.browser:id/url").textContains("sina"));
		for (int i = 0; i < getIntParams("Loop"); i++) {
			// clearCache();
			addStep("打开浏览器并正常显示");
			verify("Can't find url input EditText.", urlEdit.exists());
			addStep("输入特定网址（例如：www.sina.cn）并登陆");
			urlEdit.click();
			callShell("input text " + url);
			sleepInt(1);
			getUiDevice().pressEnter();
			sleepInt(10);
			addStep("确认登陆网页显示正常");
			verify("Can't open sina home page.", sina.exists());
			addStep("返回到浏览器首页");
			// press_back(1);
		}
		addStep("退出浏览器");
		press_back(5);
	}

	@CaseName("点击网页上的链接")
	public void testClickLink() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		launchAppByPackage(IntentConstants.proto_browser);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		addStep("进入特定网页");
		UiObject urlEdit = new UiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url"));
		verify(urlEdit.exists());
		urlEdit.click();
		callShell("input text " + url);
		sleepInt(1);
		getUiDevice().pressEnter();
		sleepInt(10);
		UiObject news = new UiObject(new UiSelector().resourceId(
				"com.android.browser:id/url").textContains("sports.sina.cn"));
		// sina home page
		UiObject sina = new UiObject(new UiSelector().resourceId(
				"com.android.browser:id/url").textContains("sina"));
		UiObject SinaSports = new UiObject(new UiSelector().className("android.view.View").packageName("com.android.browser").index(4).focusable(true));   
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("点击网页上固定链接");
			verify(SinaSports.exists());
			SinaSports.click();
			sleepInt(5);
			addStep("验证链接可进入");
			verify("Can't open sina sports page.", news.exists());
			addStep("返回特定网页页面");
			press_back(1);
			verify("Can't back to sina home page.", sina.waitForExists(5000));
			sleepInt(2);
		}
		addStep("返回浏览器主页面");
		press_back(1);
		// home page button
		// UiObject home = new UiObject(new UiSelector()
		// .resourceId("android:id/icon").index(0).instance(2));
		// verify("Can't find home page button.", home.exists());
		// home.click();
		// sleepInt(3);
		addStep("退出浏览器");
		press_back(5);
	}
}
