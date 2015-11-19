package com.letv.cases.leui.others;

import android.graphics.Rect;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;
import com.letv.uf.Utf7ImeHelper;

public class mtbfAddCase extends LetvTestCaseMTBF {
	private String MapPackage = "com.baidu.BaiduMap";
	private String wpsContent = "aaaaa";
	LeUiObject live1 = new LeUiObject(new UiSelector().resourceId("com.android.launcher3:id/live_layout"));
	LeUiObject live2 = new LeUiObject(new UiSelector().description("应用"));

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		callShell("am force-stop " + MapPackage);
		super.tearDown();
	}
	
	@CaseName("乐见桌面与手机主界面来回切换25次")
	public void testDesktopSwitch() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		for (int i = 1; i<=getIntParams("Loop");i++) {
			
			addStep("从左向右滑动切换到乐见桌面"+i+"遍");
			phone.swipe((int)(phone.getDisplayWidth()*0.025), (int)(phone.getDisplayHeight()*0.508),
					(int)(phone.getDisplayWidth()*0.72), (int)(phone.getDisplayHeight()*0.508), 30);
//			phone.swipe(36, 1302, 1044, 1302, 20);
//			LeUiObject lejian = new LeUiObject(new UiSelector().resourceId("com.android.launcher3:id/sarrs_main_title"));
//			verify("没有进入到乐见",lejian.exists());
			addStep("点击home键回到主界面"+i+"遍");
			press_home(1);
//			verify("没有下载程序图标,没有回到主界面",live1.exists()||live2.exists());
		}
	}
	
	@CaseName("在乐见桌面上下来回滑动25次")
	public void testSlideLeJian() throws UiObjectNotFoundException {
		press_home(1);
		addStep("从左向右滑动切换到乐见桌面");
		phone.swipe((int)(phone.getDisplayWidth()*0.025), (int)(phone.getDisplayHeight()*0.508),
				(int)(phone.getDisplayWidth()*0.72), (int)(phone.getDisplayHeight()*0.508), 30);
//		LeUiObject lejian = new LeUiObject(new UiSelector().resourceId("com.android.launcher3:id/sarrs_main_title"));
//		LeUiObject lejian1 = new LeUiObject(new UiSelector().textContains("乐见"));

//		verify("没有进入到乐见",lejian.exists()||lejian1.exists());
		for (int i = 1; i<=getIntParams("Loop");i++) {
			addStep("从下往上滑动乐见桌面第"+i+"遍");
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
					(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
					(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
					(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
					(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
//						phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
//						phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
//						phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			addStep("从上往下滑动乐见桌面"+i+"遍");
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
					(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
					(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
					(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
					(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
		}
		addStep("点击home键回到主界面");
		press_home(1);
	}
	
	@CaseName("live与手机主界面来回切换25次")
	public void testLiveSwitch() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		for (int i = 1; i<=getIntParams("Loop");i++) {
			addStep("进入live第"+i+"遍");
			verify("没有live",live1.exists()||live2.exists());
			if(live1.exists()){
				live1.click();
			}
			if(live2.exists()){
				live2.click();
			}
			addStep("点击home键回到主界面"+i+"遍");
			press_home(1);
		}
	}
	
	@CaseName("反复进出地图APP25次")
	public void testOpenMap() throws UiObjectNotFoundException {
		addStep("进入手机界面");
		press_home(1);
		addStep("进入设置");
		launchApp(AppName.SETTING);
		addStep("开启GPS");
		sleepInt(2);
		UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().className("android.widget.LinearLayout").packageName("com.android.settings")
						.index(6)));		
		LeUiObject location = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("位置信息").resourceId("android:id/title"));
		verify("Can't into settings",settingPanel.exists());
		settingPanel.setAsVerticalList();
		for (int i = 0; i < 20; i++) {
			if (!location.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}
		verify("Can't find Location button.", location.exists());
		location.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			sleepInt(2);
		} else {
			switchWidget.click();
			sleepInt(5);
			LeUiObject connectInfo = new LeUiObject(new UiSelector().resourceId(
					"com.android.settings:id/switch_text").text("开启"));
			verify("Can't open Location.",
					switchWidget.isChecked() || connectInfo.exists());
		}
		press_back(4);
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入地图");
		launchApp(AppName.MAP);
		sleepInt(1);
		LeUiObject tap1 = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/alertTitle").text("系统提示"));
		LeUiObject tap2 = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/flow_hint_ask").text("不再提示"));
		LeUiObject OK = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/positiveButton").text("确定"));
		if(tap1.exists()){
			if(!(tap2.isChecked())){
				tap2.click();
			}
			sleepInt(1);
			OK.click();
			sleepInt(1);
			phone.swipe((int)(phone.getDisplayWidth()*0.725), (int)(phone.getDisplayHeight()*0.508),
					(int)(phone.getDisplayWidth()*0.0125), (int)(phone.getDisplayHeight()*0.508), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.725), (int)(phone.getDisplayHeight()*0.508),
					(int)(phone.getDisplayWidth()*0.0125), (int)(phone.getDisplayHeight()*0.508), 30);
//			phone.swipe((int)(phone.getDisplayWidth()*0.725), (int)(phone.getDisplayHeight()*0.508),(int)(phone.getDisplayWidth()*0.0125), (int)(phone.getDisplayHeight()*0.508), 30);
//			phone.swipe((int)(phone.getDisplayWidth()*0.725), (int)(phone.getDisplayHeight()*0.508),(int)(phone.getDisplayWidth()*0.0125), (int)(phone.getDisplayHeight()*0.508), 30);
			LeUiObject start = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/guide_button_ok"));
			if(start.exists()){
				start.click();
			}
		}
		for (int i = 1; i<=getIntParams("Loop");i++) {
			LeUiObject search = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/tv_searchbox_home_text"));
			if(!search.exists()){
				addStep("未进入地图等待10秒");
				sleepInt(10);
			}
			verify("未进入地图",search.exists());
			addStep("退出地图第"+i+"次");
			press_back(1);
			sleepInt(1);
            /*mengfengxiao@letv.com*/
			UiObject quit = new UiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/positiveButton").text("确定"));
			if(!quit.exists()){
				addStep("未弹出退出Button等待1秒");
				sleepInt(3);

			}
			verify("未弹出退出Button", quit.exists());
			quit.click();
            /*mengfengxiao@letv.com*/
			sleepInt(2);
			addStep("进入地图第"+i+"次");
			launchApp(AppName.MAP);
			sleepInt(4);
		}
		addStep("退出地图");
		sleepInt(5);
		press_back(1);
		sleepInt(1);
//		UiObject quit = new UiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/positiveButton").text("确定"));
//		if(!quit.exists()){
//			addStep("未弹出退出Button等待1秒");
//			sleepInt(1);
//			
//		}
//		verify("未弹出退出Button",quit.exists());
//		quit.click();
		press_home(1);
	}
	
	@CaseName("地图导航测试25次")
	public void testMapNavigation() throws UiObjectNotFoundException {
		addStep("进入手机界面");
		press_home(1);
		addStep("进入设置");
		launchApp(AppName.SETTING);
		addStep("开启GPS");
		sleepInt(2);
        UiScrollable settingPanel = new UiScrollable(new UiSelector().scrollable(true));
		/*UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().className("android.widget.LinearLayout").packageName("com.android.settings")
						.index(6)));	*/
		LeUiObject location = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("位置信息").resourceId("android:id/title"));
		verify("Can't into settings",settingPanel.exists());
       settingPanel.scrollIntoView(location);
		/*settingPanel.setAsVerticalList();*/
		/*for (int i = 0; i < 20; i++) {
			if (!location.exists()) {
				settingPanel.scrollForward();
			} else {
				break;
			}
		}*/
		verify("Can't find Location button.", location.exists());
		location.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			sleepInt(2);
		} else {
			switchWidget.click();
			sleepInt(5);
			LeUiObject connectInfo = new LeUiObject(new UiSelector().resourceId(
					"com.android.settings:id/switch_text").text("开启"));
			verify("Can't open Location.",
					switchWidget.isChecked() || connectInfo.exists());
		}
		press_back(4);
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入地图");
		launchApp(AppName.MAP);
		sleepInt(1);
		LeUiObject tap1 = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/alertTitle").text("系统提示"));
		LeUiObject tap2 = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/flow_hint_ask").text("不再提示"));
		LeUiObject OK = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/positiveButton").text("确定"));
		if(tap1.exists()){
			if(!(tap2.isChecked())){
				tap2.click();
			}
			sleepInt(1);
			OK.click();
			sleepInt(1);
			phone.swipe((int)(phone.getDisplayWidth()*0.725), (int)(phone.getDisplayHeight()*0.508),
					(int)(phone.getDisplayWidth()*0.0125), (int)(phone.getDisplayHeight()*0.508), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.725), (int)(phone.getDisplayHeight()*0.508),
					(int)(phone.getDisplayWidth()*0.0125), (int)(phone.getDisplayHeight()*0.508), 30);
			
			LeUiObject start = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/guide_button_ok"));
			if(start.exists()){
				start.click();
			}
		}
		for (int i = 1; i<=getIntParams("Loop");i++) {
			LeUiObject search = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/tv_searchbox_home_text"));
			if(!search.exists()){
				addStep("未进入地图等待10秒");
				sleepInt(10);
			}
			verify("未进入地图",search.exists());
			addStep("点击搜索");
			search.click();
			sleepInt(2);
			addStep("点击景点");
			LeUiObject food = new LeUiObject(new UiSelector().text("景点"));
			verify("没有景点按钮",food.exists());
			food.click();
			sleepInt(4);
			addStep("点击第一个的 到这去");
			LeUiObject gohere = new LeUiObject(new UiSelector().text("到这去"));
			gohere.click();
			sleepInt(4);
			addStep("选择开车");
			LeUiObject car = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/iv_topbar_middle_car"));
			verify("没有开车",car.exists());
			car.click();
			sleepInt(2);
//			addStep("点击搜索");
//			LeUiObject search1 = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/iv_topbar_right_search"));
//			verify("没有搜索",search1.exists());
//			search1.click();
//			sleepInt(2);
			addStep("点击导航");
			LeUiObject navigation = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/to_navigation"));
			verify("没有导航",navigation.exists());
			navigation.click();
			sleepInt(2);
            /*mengfengxiao@letv.com*/
			/*LeUiObject accept = new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/btn_accept"));
			if(accept.exists()){
				accept.click();
				sleepInt(2);
			}*/
            /*mengfengxiao@letv.com*/
			addStep("退出导航");
			press_back(1);
			sleepInt(1);
			UiObject ok = new UiObject(new UiSelector().text("确定"));
			ok.click();
			sleepInt(2);
			addStep("退出搜索");
            /*mengfengxiao@letv.com*/
            press_back(1);
            sleepInt(2);
		}
		addStep("退出地图");
		press_back(1);
		sleepInt(1);
//		UiObject quit = new UiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/positiveButton").text("确定"));
//		if(!quit.exists()){
//			addStep("未弹出退出Button等待3秒");
//			sleepInt(3);
//		}
//		verify("未弹出退出Button",quit.exists());
//		verify(quit.exists());
//		quit.click();
		press_home(1);
	}
	@CaseName("在天气界面滑动25次")
	public void testSlideWeather() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入天气");
		launchApp(AppName.WEATHER);
		sleepInt(5);
        /*mengfengxiao@letv.com*/
        //LeUiObject CurrentTemp = new LeUiObject(new UiSelector().resourceId("com.letv.android.weather:id/weather_view_current_temp"));
        LeUiObject CurrentTemp = new LeUiObject(new UiSelector().resourceId("sina.mobile.tianqitongletv:id/weather"));
        /*mengfengxiao@letv.com*/
		verify("没有成功进入到天气中",CurrentTemp.exists());
		for (int i = 1; i<=getIntParams("Loop");i++) {
			addStep("从下往上滑动第"+i+"遍");
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			addStep("从上往下滑动第"+i+"遍");
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
		}
		addStep("退出天气");
		press_back(1);
		press_home(1);
	}
	
	@CaseName("在系统设置界面反复上下滑动25次")
	public void testSlideSystemSetting() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入设置");
		launchApp(AppName.SETTING);
		sleepInt(1);
		for (int i = 1; i<=getIntParams("Loop");i++) {
			addStep("从下往上滑动第"+i+"遍");
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);				
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			addStep("从上往下滑动第"+i+"遍");
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
		}
		addStep("退出设置");
		press_back(1);
		press_home(1);
	}
	
	@CaseName("在应用设置界面反复上下滑动25次")
	public void testSlideAppSetting() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入设置");
		launchApp(AppName.SETTING);
		addStep("进入应用设置");
		LeUiObject setting1 = new LeUiObject(new UiSelector().resourceId("com.android.settings:id/title").text("应用设置"));
		LeUiObject setting11 = new LeUiObject(new UiSelector().resourceId("android:id/title").text("应用设置"));
		verify("没有设置程序图标",setting1.exists()||setting11.exists());
		if(setting1.exists()){
			setting1.click();
		}
		if(setting11.exists()){
			setting11.click();
		}
		sleepInt(1);
		for (int i = 1; i<=getIntParams("Loop");i++) {
			addStep("从下往上滑动第"+i+"遍");
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
			addStep("从上往下滑动第"+i+"遍");
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
			phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
		}
		addStep("退出设置");
		press_back(2);
		press_home(1);
	}
	
	@CaseName("系统设置与应用设置反复切换25次")
	public void testSwitchSetting() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入设置");
		launchApp(AppName.SETTING);
		LeUiObject setting2 = new LeUiObject(new UiSelector().resourceId("com.android.settings:id/title").text("系统设置"));
		LeUiObject setting22 = new LeUiObject(new UiSelector().resourceId("android:id/title").text("系统设置"));
		LeUiObject setting11 = new LeUiObject(new UiSelector().resourceId("com.android.settings:id/title").text("应用设置"));
		LeUiObject setting1 = new LeUiObject(new UiSelector().resourceId("android:id/title").text("应用设置"));
		for (int i = 1; i<=getIntParams("Loop");i++) {
			addStep("进入应用设置");
			if(setting1.exists()){
				setting1.click();
			}
			if(setting11.exists()){
				setting11.click();
			}
			addStep("进入系统设置");
			if(setting2.exists()){
				setting2.click();
			}
			if(setting22.exists()){
				setting22.click();
			}
		}
		addStep("退出设置");
		press_back(2);
		press_home(1);
	}
	
	@CaseName("反复开启关闭便携式WLAN热点25次")
	public void testHotspotWifi () throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入设置");
		launchApp(AppName.SETTING);
		sleep(1);
		addStep("进入个人热点");	
		LeUiObject hotspot = new LeUiObject(new UiSelector().resourceId("android:id/title").text("个人热点"));
		verify("没有个人热点图标",hotspot.exists());
		hotspot.click();
		LeUiObject switchbutton = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(0)
			.childSelector(new UiSelector().resourceId("android:id/widget_frame").index(1)
				.childSelector(new UiSelector().resourceId("android:id/switchWidget"))));
		sleepInt(1);
		for (int i = 1; i<=getIntParams("Loop");i++) {
			if(switchbutton.isChecked()){
				addStep("关闭便携式wlan热点第"+i+"次");
				switchbutton.click();
				tip();
				sleepInt(3);
			}else{
				addStep("开启便携式wlan热点第"+i+"次");
				switchbutton.click();
				tip();
				sleepInt(3);
			}
		}
		addStep("退出");
		press_back(4);
		press_home(1);
	}
	
	public void tip() throws UiObjectNotFoundException{
		LeUiObject nevertip = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_checkbox").text("不再提示"));
		if(nevertip.exists()){
			if(!nevertip.isChecked()){
				nevertip.click();
			}
			sleepInt(1);
			LeUiObject ok = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm").text("确定"));
			verify(ok.exists());
			ok.click();
			sleepInt(1);
		}
	}
	
	@CaseName("反复在黄页，拨号，联系人之间切换25次")
	public void testDialContactYellowpage() throws UiObjectNotFoundException {
		
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入电话");
		launchApp(AppName.PHONE);
		LeUiObject contact = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("联系人"));
		LeUiObject dial = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("拨号"));
		LeUiObject yellowpage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("黄页"));
		for (int i = 1; i<=getIntParams("Loop");i++) {
			addStep("进入联系人第"+i+"次");
			contact.click();
			sleepInt(1);
			if(yellowpage.exists()){
				addStep("进入黄页第"+i+"次");
				yellowpage.click();
				sleepInt(1);
			}
			addStep("进入拨号第"+i+"次");
			dial.click();
			sleepInt(1);
		}
		addStep("退出");
		press_back(2);
		press_home(1);
	}
	
	
	@CaseName("反复进入在线壁纸25次")
	public void testOnlineWallpaper() throws UiObjectNotFoundException {
		
		addStep("进入手机主界面");
		press_home(1);
		for (int i = 1; i<=getIntParams("Loop");i++) {
			addStep("进入在线壁纸第"+i+"次");
			launchApp(AppName.WALLPAPER);
			sleepInt(2);
			LeUiObject NewWallpaper = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text("最新"));
			verify("没有成功进入壁纸",NewWallpaper.waitForExists(30000));
			addStep("退出");
			press_back(2);
		}
		addStep("退出");
		press_back(2);
		press_home(1);
	}
	
	@CaseName("在线壁纸设置壁纸25次")
	public void testSetWallpaper() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入在线壁纸第");
		launchApp(AppName.WALLPAPER);
		sleepInt(2);
		LeUiObject Rank = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("排行"));
		verify("没有成功进入壁纸",Rank.waitForExists(30000));
		Rank.click();
		sleepInt(4);
		addStep("点击排行中第一张壁纸");
		LeUiObject firstWallpaper = new LeUiObject(new UiSelector().resourceId("com.letv.android.wallpaperonline:id/thumbView0").index(0));
		verify("没有壁纸",firstWallpaper.exists());
		firstWallpaper.click();
		sleepInt(2);
		addStep("点击壁纸调出设置壁纸");
		phone.click((int)(phone.getDisplayWidth()*0.69), (int)(phone.getDisplayHeight()*0.39));
//		phone.click(1000, 1000);
		for(int i = 1; i<=getIntParams("Loop");i++) {
			addStep("点击设置壁纸");
			LeUiObject setWallpaper = new LeUiObject(new UiSelector().text("设为壁纸"));
			verify("没有设置壁纸按钮",setWallpaper.exists());
			setWallpaper.click();
			sleepInt(1);
			addStep("点击同时设置第"+i+"次");
			LeUiObject doubleset = new LeUiObject(new UiSelector().text("同时设置"));
			verify("没有同时设置按钮",doubleset.exists());
			doubleset.click();
			sleepInt(4);
			addStep("从右向左滑动到下一张壁纸");
			phone.swipe((int)(phone.getDisplayWidth()*0.725), (int)(phone.getDisplayHeight()*0.508),(int)(phone.getDisplayWidth()*0.0125), (int)(phone.getDisplayHeight()*0.508), 30);
			sleepInt(3);
		}
		
		addStep("退出");
		press_back(2);
		press_home(1);
	}
	
	@CaseName("反复进入多任务界面25次")
	public void testOpenMultitasking() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		for(int i = 1; i<=getIntParams("Loop");i++){
			addStep("进入多任务界面第"+i+"次");
			press_menu(1);
			sleepInt(1);
			addStep("返回手机主界面");
			press_home(1);
		}
	}
	@CaseName("访问退出应用商店50次，通过back退出25次，通过home退出25次")
	public void testAppStore() throws UiObjectNotFoundException {
		openWifi();
		addStep("进入手机主界面");
		press_home(1);
		for(int i = 1; i<=getIntParams("Loop");i++){
			addStep("打开应用商店");
			launchApp(AppName.APPSTORE);
			sleepInt(4);
			addStep("确认是否成功进入应用商店");
			LeUiObject store = new LeUiObject(new UiSelector().packageName(
					"com.letv.app.appstore"));
			verify("没有成功进入应用商店",store.exists());
			addStep("成功进入应用商店");
			if(i%2==0){
				addStep("通过Back退出应用商店");
				press_back(1);
				sleepInt(2);
			}else{
				addStep("通过Home退出应用商店");
				press_home(1);
				sleepInt(2);
			}
		}
	}
	
	@CaseName("访问退出乐视视频50次，通过back退出25次，通过home退出25次")
	public void testLetvVideo() throws UiObjectNotFoundException {
		openWifi();
		addStep("进入手机主界面");
		press_home(1);
		for(int i = 1; i<=getIntParams("Loop");i++){
			addStep("打开乐视网");
			launchApp(AppName.LETVVIDEO);
			sleepInt(4);
			LeUiObject UpdateNote = new LeUiObject(new UiSelector().resourceId("com.letv.android.client:id/update_later_btn")
					.textContains("升级"));
			if(UpdateNote.exists()){
				addStep("暂不升级");
				UpdateNote.click();
				sleepInt(1);
			}
			addStep("确认是否成功进入乐视视频");
			LeUiObject firstPage = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text("首页"));
			verify("没有成功进入乐视视频",firstPage.exists());
			addStep("成功进入乐视视频");
			if(i%2==0){
				addStep("通过Back退出乐视视频");
				press_back(1);
				sleepInt(2);
			}else{
				addStep("通过Home退出乐视视频");
				press_home(1);
				sleepInt(2);
			}
		}
	}
	
	@CaseName("访问退出乐视商城50次，通过back退出25次，通过home退出25次")
	public void testLetvshop() throws UiObjectNotFoundException {
		openWifi();
		addStep("进入手机主界面");
		press_home(1);
		for(int i = 1; i<=getIntParams("Loop");i++){
			press_home(1);
			sleepInt(1);
			addStep("打开乐视商城");
			launchApp(AppName.LESTORE);
			LeUiObject UpdateNote = new LeUiObject(new UiSelector().resourceId("com.letv.android.client:id/update_later_btn")
					.textContains("升级"));
			if(UpdateNote.exists()){
				addStep("暂不升级");
				UpdateNote.click();
				sleepInt(1);
			}
			sleepInt(5);
			addStep("确认是否成功进入乐视商城");
			LeUiObject firstPage = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text("首页"));
			verify("没有成功进入乐视商城",firstPage.exists());
			addStep("成功进入乐视商城");
			if(i%2==0){
				addStep("通过Back退出乐视商城");
				press_back(1);
				press_back(1);
			}else{
				addStep("通过Home退出乐视商城");
				press_home(1);
				sleepInt(2);
			}
		}
	}
	
	@CaseName("访问退出遥控50次，通过back退出25次，通过home退出25次")
	public void testLetvRemoteControl() throws UiObjectNotFoundException {
		openWifi();
		addStep("进入手机主界面");
		press_home(1);
		for(int i = 1; i<=getIntParams("Loop");i++){
			press_home(1);
			sleepInt(1);
			addStep("打开遥控");
			launchApp(AppName.TVCONTROLLER);
			LeUiObject UpdateNote = new LeUiObject(new UiSelector().resourceId("com.letv.android.client:id/update_later_btn")
					.textContains("升级"));
			if(UpdateNote.exists()){
				addStep("暂不升级");
				UpdateNote.click();
				sleepInt(1);
			}
			sleepInt(8);
			addStep("确认是否成功进入遥控");
            /*mengfengxiao@letv.com*/
            LeUiObject update = new LeUiObject(new UiSelector().resourceId("com.letv.android.remotecontrol:id/le_bottomsheet_default_title").text("版本更新"));
            if(update.exists()) {
                LeUiObject cancle = new LeUiObject(new UiSelector().resourceId("com.letv.android.remotecontrol:id/le_bottomsheet_default_cancel"));
                verify("can't find cancle button in update dialog", cancle.exists());
                cancle.click();
                sleepInt(5);
            }
            /*mengfengxiao@letv.com*/
			LeUiObject mycontrol = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").text("我的遥控"));
			verify("没有成功进入遥控",mycontrol.exists());
			addStep("成功进入遥控");
			if(i%2==0){
				addStep("通过Back退出遥控");
				press_back(1);
				sleepInt(1);
			}else{
				addStep("通过Home退出遥控");
				press_home(1);
				sleepInt(1);
			}
		}
		
	}
	
	@CaseName("日历视图切换25次")
	public void testChangeView() throws UiObjectNotFoundException {
		for(int i = 1; i<=getIntParams("Loop");i++){
			addStep("打开日历");
			launchApp(AppName.CALENDAR);
			sleepInt(2);
			LeUiObject agree = new LeUiObject(new UiSelector().textContains("同意"));
			if(agree.exists()){
				agree.click();
				sleepInt(2);
			}
			LeUiObject event = new LeUiObject(new UiSelector().resourceId("android:id/title")
					.packageName("com.android.calendar"));
			verify("没有进入到日历中.", event.exists());
			LeUiObject date = new LeUiObject(new UiSelector().resourceId("com.android.calendar:id/title")
					.className("android.widget.TextView").index(0));

			
			verify("年月存在",date.exists());
			String changeDate=null;
			for (int a= 0;a < 13; a++) {
				phone.swipe((int)(phone.getDisplayWidth()*0.52), (int)(phone.getDisplayHeight()*0.27),
						(int)(phone.getDisplayWidth()*0.52), (int)(phone.getDisplayHeight()*0.47), 30);
//				phone.swipe(750, 580, 750, 1200, 20);
				sleepInt(1);
				changeDate = date.getText();
				if(changeDate.contains("2014")){
					break;
				}
			}
			verify("切换年月不成功",changeDate.contains("2014"));
			
			addStep("进入周视图");
			LeUiObject week = new LeUiObject(new UiSelector()
					.className("android.widget.RadioButton").text("周"));
			verify("周视图选项不存在",week.exists());
			week.click();
			sleepInt(3);
			LeUiObject weekVerify = new LeUiObject(new UiSelector()
				.className("android.widget.ViewSwitcher"));
			verify("周视图切换不成功",weekVerify.exists());
			for (int a = 0; a< 13; a++) {
				phone.swipe((int)(phone.getDisplayWidth()*0.172), (int)(phone.getDisplayHeight()*0.39),
						(int)(phone.getDisplayWidth()*0.55), (int)(phone.getDisplayHeight()*0.39), 30);
//				phone.swipe(250, 1000, 800, 1000, 10);
				sleepInt(1);
			}
			LeUiObject year = new LeUiObject(new UiSelector()
				.className("android.widget.RadioButton").text("年"));
			year.click();
			sleepInt(3);
			LeUiObject yearVerify = new LeUiObject(new UiSelector()
			.resourceId("com.android.calendar:id/year")
			.childSelector(new UiSelector().className("android.widget.ListView")));
			verify("年视图不存在",yearVerify.exists());
			for (int a = 0; a < 13; a++) {
				phone.swipe((int)(phone.getDisplayWidth()*0.52), (int)(phone.getDisplayHeight()*0.226),
						(int)(phone.getDisplayWidth()*0.52), (int)(phone.getDisplayHeight()*0.47), 30);
//				phone.swipe(750, 580, 750, 1200, 20);
				sleepInt(1);
			}
			press_back(5);
		}
	}
	private void addWPS() throws UiObjectNotFoundException {
		LeUiObject addBtn1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("New"));
		LeUiObject addBtn2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("新建"));
		verify("没有添加按钮",addBtn1.exists()||addBtn2.exists());
		sleepInt(2);
		if(addBtn1.exists()){
			addBtn1.click();
		}
		if(addBtn2.exists()){
			addBtn2.click();
		}
		sleepInt(3);
		LeUiObject doc1 = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/new_document_item_textview").text("Document"));
		LeUiObject doc2 = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/new_document_item_textview").text("文档"));
		verify("没有Document选项",doc1.exists()||doc2.exists());
		if(doc1.exists()){
			doc1.click();
		}
		if(doc2.exists()){
			doc2.click();
		}
		sleepInt(10);
		LeUiObject Baidu = new LeUiObject(new UiSelector().className("android.widget.TextView").text("Baidu IME for Letv"));
		if(Baidu.exists()){
			LeUiObject doNotR = new LeUiObject(new UiSelector().className("android.widget.CheckBox").textContains("Don't remind"));
			if(!(doNotR.isChecked())){
				doNotR.click();
				sleepInt(1);
			}
			LeUiObject ok= new LeUiObject(new UiSelector().className("android.widget.Button").text("continue"));
			ok.click();
			sleepInt(1);
		}
		callShell("input text " + wpsContent);
		sleepInt(2);
		LeUiObject Done1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("Done"));
		LeUiObject Done2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("完成"));
		if(Done1.exists()){
			Done1.click();
		}
		if(Done2.exists()){
			Done2.click();
		}
		sleepInt(2);
		LeUiObject save = new LeUiObject(new UiSelector().className("android.widget.ImageView").resourceId("cn.wps.moffice_eng:id/image_save"));
		save.click();
		sleepInt(2);
		LeUiObject saveToMyDoc1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("My Documents"));
		LeUiObject saveToMyDoc2= new LeUiObject(new UiSelector().className("android.widget.TextView").text("我的文档"));
		if(saveToMyDoc1.exists()){
			saveToMyDoc1.click();
		}
		if(saveToMyDoc2.exists()){
			saveToMyDoc2.click();
		}
		sleepInt(2);
		LeUiObject editTitle1 = new LeUiObject(new UiSelector().className("android.widget.EditText").resourceId("cn.wps.moffice_eng:id/save_new_name"));
		editTitle1.click();
		editTitle1.clearTextField();
		editTitle1.click();
		editTitle1.clearTextField();
		editTitle1.click();
		editTitle1.clearTextField();
		callShell("input text " + wpsContent);
		LeUiObject save2 = new LeUiObject(new UiSelector().className("android.widget.Button").resourceId("cn.wps.moffice_eng:id/btn_save"));
		save2.click();
		sleepInt(5);	
		press_back(1);
		sleepInt(2);
		LeUiObject Recent1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("Recent"));
		LeUiObject Recent2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("最近"));
		if(Recent1.exists()){
			Recent1.click();
		}
		if(Recent2.exists()){
			Recent2.click();
		}
		sleepInt(2);
		LeUiObject item = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/history_record_item_name"));
		sleepInt(2);
		verify("文档创建失败",(wpsContent+".doc").equals(item.getText()));
	}
	
	private void checkWPS() throws UiObjectNotFoundException {
		LeUiObject item = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/history_record_item_name").textContains(wpsContent));
		item.click();
		sleepInt(2);
		LeUiObject close = new LeUiObject(new UiSelector().className("android.widget.ImageView").resourceId("cn.wps.moffice_eng:id/image_close"));
		close.click();
		sleepInt(2);
		
	}
	
	private void delWPS() throws UiObjectNotFoundException {
		LeUiObject open1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("open"));
		LeUiObject open2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("打开"));
		if(open1.exists()){
			open1.click();
		}
		if(open2.exists()){
			open2.click();
		}
		sleepInt(2);

		LeUiObject doc1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("DOC"));
		LeUiObject doc2 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("DOC"));
		verify("没有Doc选项",doc1.exists()||doc2.exists());
		if(doc1.exists()){
			doc1.click();
		}
		if(doc2.exists()){
			doc2.click();
		}
		sleepInt(2);
		phone.swipe((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.48),
				(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.45), 30);
//		phone.swipe(710, 1237, 710, 1150, 50);
		sleepInt(2);
		LeUiObject item = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("cn.wps.moffice_eng:id/fb_filename_text").textContains(wpsContent));
		verify(item.exists());
		sleepInt(2);
		LeUiObject property = new LeUiObject(new UiSelector().className("android.widget.ImageView").resourceId("cn.wps.moffice_eng:id/fb_item_property_btn"));
		verify(property.exists());
		property.click();
		LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.TextView").text("删除文档"));
		delete.click();
		sleepInt(2);
		verify("文档删除失败",!(item.exists()));
	}
	
	
	@CaseName("WPS中查看、编辑、删除、新建文件　25次")
	public void testAddDelWPS() throws UiObjectNotFoundException {
		for(int i = 1; i<=getIntParams("Loop");i++){
			addStep("step1:启动WPS");
			phone.swipe((int)(phone.getDisplayWidth()*0.725), (int)(phone.getDisplayHeight()*0.508),(int)(phone.getDisplayWidth()*0.0125), (int)(phone.getDisplayHeight()*0.508), 30);
			sleepInt(1);
			LeUiObject WPS = new LeUiObject(new UiSelector().className("android.widget.TextView").textContains("WPS"));
			LeUiObject Notice2 = new LeUiObject(new UiSelector().className("android.widget.Button").text("同意"));
			verify("没有WPS程序图标",WPS.exists());
			WPS.click();
			sleepInt(6);
			if(Notice2.exists()){
				LeUiObject doNotR2 = new LeUiObject(new UiSelector().className("android.widget.CheckBox").textContains("不再提醒"));
				if(!(doNotR2.isChecked())){
					doNotR2.click();
					sleepInt(1);
				}
				Notice2.click();
				sleepInt(1);
				
			}
			LeUiObject myOffice1 = new LeUiObject(new UiSelector().packageName("cn.wps.moffice_eng"));
			verify("没有成功进入WPS", myOffice1.exists());
			addWPS();
			checkWPS();
			delWPS();
			addStep("退出应用");
			press_back(2);
			press_home(1);
			verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		}
	}
	@CaseName("启动计算器应用,计算器操作正常,退出计算器：25遍")
	public void testCalculator() throws UiObjectNotFoundException {
		for(int i = 1; i<=getIntParams("Loop");i++){
			addStep("Step1:启动计算器应用");
			launchApp(AppName.CALCULATOR);
			sleepInt(4);
			//加减乘除号
			LeUiObject plus = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/plus"));
			verify("Can't find plus icon.", plus.exists());
			LeUiObject minus = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/minus"));
			verify("Can't find minus icon.", minus.exists());
			LeUiObject multiply  = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/mul"));
			verify("Can't find multiply  icon.", multiply .exists());
			LeUiObject divide = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/div"));
			verify("Can't find divide icon.", divide.exists());
			//9 和1
			LeUiObject digit1 = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/digit1").text("1"));
			verify("Can't find one digit1.", digit1.exists());
			LeUiObject digit9 = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/digit9").text("9"));
			verify("Can't find digit9 icon.", digit9.exists());
			//点号
			LeUiObject dot = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/dot"));
			verify("Can't find dot icon.", dot.exists());
			//等于号
			LeUiObject equal = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/equal"));
			verify("Can't find equal icon.", equal.exists());
			
			//结果
			LeUiObject result = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/formula").className("android.widget.EditText"));
			verify("Can't find result.", result.exists());
			
			//清除
			LeUiObject clear = new LeUiObject(new UiSelector().resourceId("com.android.calculator2:id/clear"));
			verify("Can't find clear.", clear.exists());
			
			addStep("Step2:计算器操作正常");
			//1.11111+9.99999=11.1111
			clear.click();
			sleepInt(1);
			digit1.click();
			sleepInt(1);
			dot.click();
			sleepInt(1);
			for (int a = 0; a < 6; a++) {
				digit1.click();
				sleepInt(1);
			}
			plus.click();
			sleepInt(1);
			digit9.click();
			sleepInt(1);
			dot.click();
			sleepInt(1);
			for (int a = 0; a < 6; a++) {
				digit9.click();
				sleepInt(1);
			}
			
			equal.click();
			sleepInt(2);
			verify("加法结果计算错误！","11.11111".equals(result.getText()));
			
			//9.99999-1.11111=8.888888
			clear.click();
			sleepInt(1);
			digit9.click();
			sleepInt(1);
			dot.click();
			sleepInt(1);
			for (int a = 0; a < 5; a++) {
				digit9.click();
				sleepInt(1);
			}
			minus.click();
			sleepInt(1);
			digit1.click();
			sleepInt(1);
			dot.click();
			sleepInt(1);
			for (int a = 0; a < 5; a++) {
				digit1.click();
				sleepInt(1);
			}
			equal.click();
			sleepInt(2);
			verify("减法结果计算错误！","8.88888".equals(result.getText()));
			
			//1.11111X9.99999=11.1110888889
			clear.click();
			sleepInt(1);
			digit1.click();
			sleepInt(1);
			dot.click();
			sleepInt(1);
			for (int a = 0; a< 5; a++) {
				digit1.click();
				sleepInt(1);
			}
			multiply.click();
			sleepInt(1);
			digit9.click();
			sleepInt(1);
			dot.click();
			sleepInt(1);
			for (int a= 0; a < 5; a++) {
				digit9.click();
				sleepInt(1);
			}
			equal.click();
			sleepInt(2);
			verify("乘法结果计算错误！","11.111088889".equals(result.getText()));
			
			//9.99999/1.11111=9
			clear.click();
			sleepInt(1);
			digit9.click();
			sleepInt(1);
			dot.click();
			sleepInt(1);
			for (int a = 0; a < 5; a++) {
				digit9.click();
				sleepInt(1);
			}
			divide.click();
			sleepInt(1);
			digit1.click();
			sleepInt(1);
			dot.click();
			sleepInt(1);
			for (int a = 0; a < 5; a++) {
				digit1.click();
				sleepInt(1);
			}
			equal.click();
			sleepInt(2);
			verify("除法结果计算错误！","9".equals(result.getText()));
			
			addStep("Step3:计算器操作正常");
			clear.click();
			sleepInt(1);
			press_back(2);
			press_home(1);
			verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		}
	}
	
	@CaseName("在应用商店各个窗口上下滑动")
	public void testAppStoreSwipe() throws UiObjectNotFoundException {
		launchApp(AppName.APPSTORE);
		sleepInt(5);
		LeUiObject searchBtn = new LeUiObject(new UiSelector().resourceId("com.letv.app.appstore:id/imageView1"));
		LeUiObject stock = new LeUiObject(new UiSelector().className("android.widget.TextView").text("股票"));
		verify("没有找到搜索框", searchBtn.exists());
		searchBtn.click();
		sleepInt(2);
		verify("热搜关键词中没有找到股票关键字", stock.exists());
		stock.click();
		sleepInt(4);
		addStep("在搜索到的结果中滑动查看相关的软件");
		for(int i=0;i<4;i++){
		phone.swipe((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8),
				(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.3), 25);
		sleepInt(2);
		}
		press_back(1);
		swipeDownUp("精品");
			swipeDownUp("最热");
			sleepInt(5);
			swipeDownUp("飙升");
			sleepInt(5);
			swipeDownUp("经典");
			sleepInt(5);
			swipeDownUp("个性");
			sleepInt(5);
			swipeDownUp("推荐");
			sleepInt(5);
			swipeDownUp("分类");
			sleepInt(5);
			swipeDownUp("新品");
			sleepInt(5);
			swipeDownUp("游戏");	
			sleepInt(5);
	}
	
	public void swipeDownUp(String text) throws UiObjectNotFoundException {
		LeUiObject str = new LeUiObject(new UiSelector().className("android.widget.TextView").text(text));
		LeUiObject top = new LeUiObject(new UiSelector().className("android.widget.TextView").text("排行"));
		LeUiObject softWare = new LeUiObject(new UiSelector().className("android.widget.TextView").text("软件"));
		if(text.equals("推荐")||text.equals("分类")||text.equals("新品")){
			softWare.click();
			sleepInt(2);
		}
		if(text.equals("最热")||text.equals("飙升")||text.equals("经典")||text.equals("个性")){
			top.click();
			sleepInt(2);
		}
		verify("没有找到搜索框", str.exists());
		str.click();
		sleepInt(2);
		addStep("在应用商店的"+text+"窗口上下滑动5次");
		for(int i=0;i<5;i++){
			phone.swipe((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8),
					(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.3), 25);
			sleepInt(2);
			phone.swipe((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8),
					(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.3), 25);
			sleepInt(2);
			phone.swipe((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.3),
					(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8), 25);
			sleepInt(2);
			phone.swipe((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.3),
					(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.8), 25);
			sleepInt(2);
			}
	}
	
	@CaseName("访问退出便签50次，通过back退出25次，通过home退出25次")
	public void testNote() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		for(int i = 1; i<=getIntParams("Loop");i++){
			press_home(1);
			sleepInt(1);
			addStep("进入便签");
			launchApp(AppName.NOTE);
			sleepInt(2);
			addStep("确认是否成功进入便签");
			LeUiObject allNote = new LeUiObject(new UiSelector().text("全部便签"));
			verify("没有成功进入便签",allNote.exists());
			addStep("成功进入便签");
			if(i%2==0){
				addStep("通过Back退出便签");
				press_back(1);
			}else{
				addStep("通过Home退出便签");
				press_home(1);
				sleepInt(2);
			}
		}
	}

    /*---mengfengxiao@letv.com---2015.11.17---*/
    private void addNote(String note) throws UiObjectNotFoundException {
        addStep("点击add");
        phone.click((int) (phone.getDisplayWidth() * 0.5),(int) (phone.getDisplayHeight() * 0.97));
        sleepInt(1);
        addStep("添加文字");
        LeUiObject noteTime = new LeUiObject(new UiSelector().resourceId("com.letv.android.note:id/detail_note_editor"));
        verify("没有noteTime",noteTime.exists());
        //noteTime.setText(Utf7ImeHelper.e("testNote"+i));
        noteTime.setText(Utf7ImeHelper.e(note));
        sleepInt(2);
            /*---mengfengxiao@letv.com---2015.11.17---*/
        addStep("添加图片");
        LeUiObject addImage = new LeUiObject(new UiSelector().resourceId("com.letv.android.note:id/note_edit_add_image"));
        verify("添加图片按钮不存在",addImage.exists());
        addImage.click();
        sleepInt(2);
        LeUiObject camera = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_text").text("拍照"));
        verify("拍照按钮不存在", camera.exists());
        camera.click();
        sleepInt(2);
        LeUiObject shutter = new LeUiObject(new UiSelector().resourceId("com.android.camera2:id/shutter_button"));
        verify("没有成功进入相机", shutter.exists());
        shutter.click();
        sleepInt(4);
        LeUiObject done = new LeUiObject(new UiSelector().resourceId("com.android.camera2:id/btn_done"));
        verify("保存图片按钮不存在", done.exists());
        done.click();
        sleepInt(4);
        LeUiObject imageNote = new LeUiObject(new UiSelector().resourceId("com.letv.android.note:id/detail_note_image"));
        verify("添加图片失败",imageNote.exists());
        addStep("添加图片成功");
        LeUiObject actionBar = new LeUiObject(new UiSelector().resourceId("android:id/action_bar"));
        phone.click((int) (phone.getDisplayWidth() * 0.1),actionBar.getBounds().centerY());
            /*mengfengxiao@letv.com*/
        sleepInt(2);
    }

    /*mengfengxiao@letv.com-----20151027*/
    private void clearAllNote() throws UiObjectNotFoundException {
        LeUiObject note = new LeUiObject(new UiSelector().resourceId("com.letv.android.note:id/note_content"));
        verify("没有note",note.exists());
        int x=note.getBounds().centerX();
        int y=note.getBounds().centerY();
        phone.swipe(x, y, x, y, 100);
        sleepInt(2);
        LeUiObject allSelect = new LeUiObject(new UiSelector().text("全选"));
        verify("没有全选",allSelect.exists());
        allSelect.click();
        sleepInt(1);
        LeUiObject delete = new LeUiObject(new UiSelector().resourceId("android:id/icon"));
        verify("没有delete",delete.exists());
        delete.click();
        sleepInt(1);
        LeUiObject confirm = new LeUiObject(new UiSelector().textContains("删除"));
        verify("没有confirm",confirm.exists());
        confirm.click();
        sleepInt(2);
        LeUiObject none = new LeUiObject(new UiSelector().textContains("无便签"));
        verify("删除失败",none.exists());
    }

	@CaseName("连续添加多个便签,删除全部便签")
	public void testAddNote() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入便签");
		launchApp(AppName.NOTE);
        sleepInt(2);
        /*mengfengxiao@letv.com*/
        UiObject noteTitle = new UiObject(new UiSelector().resourceId("android:id/action_bar")).getChild(new UiSelector().className("android.widget.TextView").text("全部便签"));
        verify("没有进入便签",noteTitle.exists());
        addStep("删除全部便签");
        clearAllNote();
        sleepInt(2);
        UiObject firstNote = new UiObject(new UiSelector().resourceId("com.letv.android.note:id/note_list_fragment"))
                .getChild(new UiSelector().resourceId("com.letv.android.note:id/front").index(0));
        UiObject firstNoteTitle = firstNote.getChild(new UiSelector().resourceId("com.letv.android.note:id/note_content"));
        /*mengfengxiao@letv.com*/
		for(int i = 1; i<=getIntParams("Loop");i++){
            addStep("添加第"+i+"条便签");
            addNote("testNote"+i);
            verify("添加便签失败",firstNote.exists()&&firstNoteTitle.getText().equals("testNote"+i));
		}
		addStep("删除全部便签");
        sleep(2);
        clearAllNote();
		addStep("退出");
		press_back(2);
		press_home(1);
	}

    @CaseName("便签：反复贴到/取消贴到桌面")
    /*---mengfengxiao@letv.com---2015.11.17---*/
    public void testStickNote() throws UiObjectNotFoundException {
        addStep("进入手机主界面");
        press_home(1);
        addStep("进入便签");
        launchApp(AppName.NOTE);
        sleepInt(2);
        UiObject noteTitle = new UiObject(new UiSelector().resourceId("android:id/action_bar")).getChild(new UiSelector().className("android.widget.TextView").text("全部便签"));
        verify("没有进入便签",noteTitle.exists());
        UiObject firstNote = new UiObject(new UiSelector().resourceId("com.letv.android.note:id/note_list_fragment"))
                .getChild(new UiSelector().resourceId("com.letv.android.note:id/front").index(0));
        UiObject firstNoteTitle = firstNote.getChild(new UiSelector().resourceId("com.letv.android.note:id/note_content"));
        UiObject share = new UiObject(new UiSelector().resourceId("com.letv.android.note:id/bottom_widget"))
                .getChild(new UiSelector().resourceId("android:id/icon").className("android.widget.ImageView").index(1));
        LeUiObject stick = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_text").text("贴到桌面"));
        LeUiObject remove = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_text").text("取消贴到桌面"));
        UiObject tip = new UiObject(new UiSelector().resourceId("com.letv.android.note:id/layout_widget"))
                .getChild(new UiSelector().resourceId("com.letv.android.note:id/txtContent").text("testNote"));
        if (!firstNote.exists()) {
            addStep("无便签，添加便签");
            addNote("testNote");
            verify("添加便签失败",firstNote.exists()&&firstNoteTitle.getText().equals("testNote"));
            addStep("添加便签成功");
        }
        for (int i=0; i<=getIntParams("Loop"); i++) {
            addStep("进入便签");
            launchApp(AppName.NOTE);
            sleepInt(2);
            addStep("点击便签");
            firstNote.click();
            sleepInt(2);
            addStep("点击分享");
            verify("分享按钮不存在", share.exists());
            share.click();
            sleepInt(2);
            addStep("点击贴到桌面");
            verify("贴到桌面按钮不存在", stick.exists());
            stick.click();
            sleepInt(2);
            addStep("返回桌面,确认便签成功贴到桌面");
            press_home(1);
            sleepInt(2);
            findAppInDesktop(tip);
            verify("便签贴到桌面失败", tip.exists());
            addStep("便签成功贴到桌面");
            addStep("点击便签");
            tip.click();
            sleepInt(2);
            addStep("点击分享");
            verify("分享按钮不存在", share.exists());
            share.click();
            sleepInt(2);
            addStep("点击取消贴到桌面");
            verify("取消贴到桌面按钮不存在", remove.exists());
            remove.click();
            sleepInt(2);
            addStep("返回桌面,确认便签删除成功");
            press_back(3);
            press_home(1);
            findAppInDesktop(tip);
            if(!tip.exists())
                addStep("桌面便签删除成功");
        }
        addStep("退出便签");
        press_back(2);
        press_home(1);
    }

	public void openGPS() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		LeUiObject location=new LeUiObject(new UiSelector().className("android.widget.TextView").text("位置信息"));
		LeUiObject GPS=new LeUiObject(new UiSelector().resourceId("com.android.settings:id/switch_widget"));
		for(int i=0;i<10;i++){
			if(location.exists()){
				break;
			}else{
				phone.swipe((int) (phone.getDisplayWidth() * 0.5),
						(int) (phone.getDisplayHeight() * 0.5),
						(int) (phone.getDisplayWidth() * 0.5),
						(int) (phone.getDisplayHeight() * 0.3), 30);
			}
		}
		verify("没有找到位置信息", location.exists());
		location.click();
		sleepInt(1);
		if(!GPS.isChecked()){
			GPS.click();
			sleepInt(2);
		}
	}
	
	@CaseName("进入播放器播放视频10次(4K 1080P 720P 480P )")
	public void testVideoPlayer() throws UiObjectNotFoundException {
		addStep("进入播放器");
		launchApp(AppName.VIDEOPLAYER);
		addStep("进入VideoPlayerTest");
		LeUiObject Resource=new LeUiObject(new UiSelector().resourceId("com.android.videoplayer:id/folder_list_item_title_text_view").text("Resource"));
		verify(Resource.exists());
		Resource.click();
		sleepInt(2);
		LeUiObject video4k=new LeUiObject(new UiSelector().resourceId("com.android.videoplayer:id/video_list_item_title_text_view").text("4K.mp4"));
		LeUiObject video720P=new LeUiObject(new UiSelector().resourceId("com.android.videoplayer:id/video_list_item_title_text_view").text("720P.mp4"));
		LeUiObject video480P=new LeUiObject(new UiSelector().resourceId("com.android.videoplayer:id/video_list_item_title_text_view").text("480P.mp4"));
		LeUiObject video1080P=new LeUiObject(new UiSelector().resourceId("com.android.videoplayer:id/video_list_item_title_text_view").text("1080P.mp4"));
        /*mengfengxiao@letv.com*/
        LeUiObject timeBar = new LeUiObject(new UiSelector().resourceId("com.android.videoplayer:id/player_seekbar_time_container"));
        LeUiObject pause = new LeUiObject(new UiSelector().resourceId("com.android.videoplayer:id/player_btn_play_pause"));
        int center_x = phone.getDisplayWidth()/2;
        int center_y = phone.getDisplayHeight()/2;
        UiObject resourceList = new UiObject(new UiSelector().resourceId("android:id/action_bar"))
                .getChild(new UiSelector().className("android.widget.TextView").text("Resource"));
        /*mengfengxiao@letv.com*/
		for (int i = 1; i <= getIntParams("Loop"); i++) {
            addStep("播放4K.mp4 5S");
            verify("没有4k.mp4",video4k.exists());
            video4k.click();
            sleepInt(5);
            addStep("退出播放");
            press_back(1);
            sleepInt(2);
            addStep("播放1080P.mp4 5S");
            verify("没有1080p.mp4",video1080P.exists());
            video1080P.click();
            sleepInt(5);
            addStep("退出播放");
            press_back(1);
            sleepInt(2);
            addStep("播放720P.mp4 5S");
            if(!resourceList.exists()&&Resource.exists()){
                Resource.click();
                sleepInt(2);
            }
            verify("没有720P.mp4",video720P.exists());
            video720P.click();
            sleepInt(5);
            addStep("退出播放");
            press_back(1);
            sleepInt(2);
            addStep("播放480P.mp4 5S");
            verify("没有480P.mp4",video480P.exists());
            video480P.click();
            sleepInt(5);
            addStep("退出播放");
            press_back(1);
            sleepInt(2);
			/*addStep("播放1080P.mp4 15S");
			verify("没有1080p.mp4",video1080P.exists());
			video1080P.click();
			sleepInt(5);
             mengfengxiao@letv.com
            phone.click(center_x, center_y);//点击视频
            sleepInt(2);
            verify("can't find pause button", pause.exists());
            pause.click();
            sleepInt(2);
            phone.swipe(timeBarRect.right, timeBarRect.centerY(), timeBarRect.left, timeBarRect.centerY(), 50);
            sleep(2);
            mengfengxiao@letv.com
			addStep("退出播放");
			press_back(1);
			sleepInt(1);
			addStep("播放720P.mp4 15S");
			verify("没有720P.mp4",video720P.exists());
			video720P.click();
			sleepInt(5);
             mengfengxiao@letv.com
            phone.click(center_x, center_y);//点击视频
            sleepInt(2);
            verify("can't find pause button", pause.exists());
            pause.click();
            sleepInt(2);
            phone.swipe(timeBarRect.right, timeBarRect.centerY(), timeBarRect.left, timeBarRect.centerY(), 50);
            sleep(2);
            mengfengxiao@letv.com
			addStep("退出播放");
			press_back(1);
			sleepInt(1);
			addStep("播放480P.mp4 15S");
			verify("没有480P.mp4",video480P.exists());
			video480P.click();
			sleepInt(5);
             mengfengxiao@letv.com
            phone.click(center_x, center_y);//点击视频
            sleepInt(2);
            verify("can't find pause button", pause.exists());
            pause.click();
            sleepInt(2);
            phone.swipe(timeBarRect.right, timeBarRect.centerY(), timeBarRect.left, timeBarRect.centerY(), 50);
            sleep(2);
            mengfengxiao@letv.com
			addStep("退出播放");
			press_back(1);
			sleepInt(1);*/
		}
		addStep("退出播放器");
	}

    /*@CaseName("连续进入管家,点击优化,退出")
    public void testSuperManager() throws UiObjectNotFoundException {
        for (int i=0; i<getIntParams("Loop"); i++) {
            addStep("第"+i+"次进入管家");
            launchApp(AppName.CONCIERGE);
            sleepInt(5);
            LeUiObject manager = new LeUiObject(new UiSelector().resourceId("android:id/decor_content_parent")
                    .packageName("com.letv.android.supermanager"));
            verify("进入管家失败", manager.exists());
            LeUiObject optimize = new LeUiObject(new UiSelector().resourceId("com.letv.android.supermanager:id/circleBgView2"));
            verify("优化按钮不存在",optimize.exists());
            addStep("点击优化按钮");
            optimize.click();
            sleepInt(5);
            addStep("点击完成优化");
            LeUiObject finish = new LeUiObject(new UiSelector().resourceId("com.letv.android.supermanager:id/bottomButton"));
            verify("优化按钮不存在",finish.exists());
            if(!finish.isEnabled()){
                sleepInt(5);
            }
            addStep("退出管家");
            finish.click();
            press_back(2);
        }
    }*/
}
