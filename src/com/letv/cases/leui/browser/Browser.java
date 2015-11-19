package com.letv.cases.leui.browser;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Browser extends LetvTestCaseMTBF {
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
//		openWifi();
		press_back(5);
	}


	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		openWifi();
		super.tearDown();
	}
	
//	private void openwifi() throws UiObjectNotFoundException {
//		launchApp(AppName.SETTING);
//		sleepInt(2);
//		UiObject wlan = new UiObject(new UiSelector().className(
//				"android.widget.TextView").textContains("WLAN").resourceId("android:id/title"));
//		verify("Can't find wifi button.", wlan.exists());
//		wlan.click();
//		sleepInt(2);
//		UiObject switchWidget = new UiObject(new UiSelector().className(
//				"com.letv.leui.widget.LeSwitch").resourceId(
//				"com.android.settings:id/switch_widget"));
//		verify("Can't find switchWidget button.", switchWidget.exists());
//		if (switchWidget.isChecked()) {
//			return;
//		} else {
//			switchWidget.click();
//			sleepInt(10);
//			UiObject connectInfo = new UiObject(new UiSelector().resourceId(
//					"android:id/summary").text("已连接"));
//			verify("Can't open wifi.",
//					switchWidget.isChecked() || connectInfo.exists());
//		}
//	}
	private void clearCache() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(4);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
		verify("没有更多设置",more.exists());
		more.click();
		sleepInt(2);
		LeUiObject setting = new LeUiObject(new UiSelector().text("设置").resourceId("com.android.browser:id/menu_item_text"));
		verify("没有设置选项",setting.exists());
		setting.click();
		sleepInt(2);
		UiScrollable panal=new UiScrollable(new UiSelector().className("android.widget.ListView").resourceId("android:id/list"));
		LeUiObject clear = new LeUiObject(new UiSelector().textContains("数据清除"));
		if(!clear.exists()){
			panal.scrollIntoView(new UiSelector().textContains("数据清除"));
			sleepInt(2);
		}
		verify("没有浏览器数据清除选项",clear.exists());
		clear.click();
		sleepInt(2);
		UiObject cookie = new UiObject(new UiSelector().resourceId("android:id/list"))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(0))
				.getChild(new UiSelector().resourceId("android:id/widget_frame").index(1))
					.getChild(new UiSelector().resourceId("android:id/checkbox"));
		if(!cookie.isChecked()){
			cookie.click();
		}
		UiObject cache = new UiObject(new UiSelector().resourceId("android:id/list"))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(1))
				.getChild(new UiSelector().resourceId("android:id/widget_frame").index(1))
					.getChild(new UiSelector().resourceId("android:id/checkbox"));
		if(!cache.isChecked()){
			cache.click();
		}
		UiObject pwdName = new UiObject(new UiSelector().resourceId("android:id/list"))
			.getChild(new UiSelector().className("android.widget.LinearLayout").index(2))
				.getChild(new UiSelector().resourceId("android:id/widget_frame").index(1))
					.getChild(new UiSelector().resourceId("android:id/checkbox"));
		if(!pwdName.isChecked()){
			pwdName.click();
		}
		LeUiObject delete = new LeUiObject(new UiSelector().text("删除"));
		verify("没有删除按钮",delete.exists());
		delete.click();
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("清除数据"));
		verify("没有清除数据按钮",confirm.exists());
		confirm.click();
		sleepInt(2);
		addStep("退出浏览器");
		press_back(5);
		press_home(1);
	}
	
	/*1、打开浏览器。
	2、清除浏览器缓存、历史记录和cookie数据。
	3、退出浏览器
	4、打开主界面的web书签图标。
	5、确认网页显示正常。
	6、退出浏览器。*/
	@CaseName("打开特定的web页面")
	public void testOpenPage() throws UiObjectNotFoundException {
			for (int i = 0; i < getIntParams("Loop"); i++) {
				addStep("清除Cache");
				clearCache();
				addStep("打开浏览器");
				launchApp(AppName.BROWSER);
				sleepInt(4);
				UiObject home = new UiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
				verify("Can't find home page button.", home.exists());
				home.click();
				sleepInt(2);
				addStep("打开浏览器");
				launchApp(AppName.BROWSER);
				sleepInt(4);
				addStep("查看书签");
				LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
				verify("没有更多设置",more.exists());
				more.click();
				sleepInt(2);
				LeUiObject bookmark = new LeUiObject(new UiSelector().text("书签/历史").resourceId("com.android.browser:id/menu_item_text"));
				verify("没有书签选项",bookmark.exists());
				bookmark.click();
				sleepInt(2);
				LeUiObject sogou = new LeUiObject(new UiSelector().text("SOGOU").resourceId("com.android.browser:id/title"));
				verify("没有搜狗书签",sogou.exists());
				sogou.click();
				sleepInt(10);
				UiObject sogou1 = new UiObject(new UiSelector().textContains("搜狗").resourceId(
						"com.android.browser:id/url_input_view"));
				UiObject load = new UiObject(new UiSelector().textContains("正在加载").resourceId(
						"com.android.browser:id/url_input_view"));
				if(!(sogou1.exists()) && load.exists()){
					addStep("网页正在加载中,等20秒钟");
					sleepInt(20);
				}
				verify("网页没有加载成功", sogou1.exists());
				addStep("退出浏览器");
				press_back(5);
			}
		}
	/*1、打开浏览器。
	2、清除浏览器缓存、历史记录和cookie数据。
	3、退出浏览器
	4、打开主界面的web书签图标。
	5、确认网页显示正常。
	6、点击网页上的链接并确认进入成功。
	7、退出浏览器。*/
	@CaseName("点击网页上的链接")
	public void testClickLink() throws UiObjectNotFoundException {
			for (int i = 0; i < getIntParams("Loop"); i++) {
				addStep("清除Cache");
				clearCache();
				addStep("打开浏览器");
				launchApp(AppName.BROWSER);
				sleepInt(4);
				UiObject home = new UiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
				verify("Can't find home page button.", home.exists());
				home.click();
				sleepInt(2);
				addStep("打开浏览器");
				launchApp(AppName.BROWSER);
				sleepInt(4);
				addStep("查看书签");
				LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
				verify("没有更多设置",more.exists());
				more.click();
				sleepInt(2);
				LeUiObject bookmark = new LeUiObject(new UiSelector().text("书签/历史").resourceId("com.android.browser:id/menu_item_text"));
				verify("没有书签选项",bookmark.exists());
				bookmark.click();
				sleepInt(2);
				LeUiObject sogou = new LeUiObject(new UiSelector().text("SOGOU").resourceId("com.android.browser:id/title"));
				verify("没有搜狗书签",sogou.exists());
				sogou.click();
				sleepInt(10);
				UiObject sogou1 = new UiObject(new UiSelector().textContains("搜狗").resourceId(
						"com.android.browser:id/url_input_view"));
				UiObject load = new UiObject(new UiSelector().textContains("正在加载").resourceId(
						"com.android.browser:id/url_input_view"));
				if(!(sogou1.exists()) && load.exists()){
					addStep("网页正在加载中,等20秒钟");
					sleepInt(20);
				}
				verify("网页没有加载成功", sogou1.exists());
				addStep("点击网页上的链接并确认进入成功。");
				UiObject sogouNew = new UiObject(new UiSelector().description("新闻"));
				if(sogouNew.exists()){
					sogouNew.click();
				}else{
					phone.click((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.43));
				}
				sleepInt(5);
				UiObject sogouNew1 = new UiObject(new UiSelector().textContains("搜狗").resourceId(
						"com.android.browser:id/url_input_view"));
				if(!(sogouNew1.exists()) && load.exists()){
					addStep("网页正在加载中,等20秒钟");
					sleepInt(20);
				}
				verify("网页没有加载成功", sogouNew1.exists());
				addStep("退出浏览器");
				press_back(5);
			}
		}
	}
