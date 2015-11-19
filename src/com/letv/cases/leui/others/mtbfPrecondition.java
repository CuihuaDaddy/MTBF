package com.letv.cases.leui.others;



import java.util.UUID;

import android.R.integer;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;
import com.letv.uf.Utf7ImeHelper;

public class mtbfPrecondition extends LetvTestCaseMTBF {
	protected void setUp() throws Exception {
		
		// TODO Auto-generated method stub
		super.setUp();
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void terms() throws UiObjectNotFoundException {
		LeUiObject termsWindows = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("申明和条款"));
		if (termsWindows.exists()) {
				new UiObject(new UiSelector().className(
						"android.widget.Button").text("同意并继续")).click();
				sleepInt(3);
				press_back(3);
				press_home(1);
				sleepInt(1);
		} 
	}
	
	public void letvCount() throws UiObjectNotFoundException {
		LeUiObject letvCountWindows = new LeUiObject(new UiSelector().textContains("暂不"));
		if (letvCountWindows.exists()) {
				letvCountWindows.click();
				sleepInt(5);
			}
		sleepInt(3);
		press_back(3);
		press_home(1);
		sleepInt(1);
	}
	
	@CaseName("MTBF测试初始化app")
	public void testOpenApps1() throws UiObjectNotFoundException {
		press_back(2);
		press_home(1);
		sleepInt(1);
		phone.swipe((int) (phone.getDisplayWidth() * 0.1),
				(int) (phone.getDisplayHeight() * 0.5),
				(int) (phone.getDisplayWidth() * 0.8),
				(int) (phone.getDisplayHeight() * 0.5), 30);
		sleepInt(2);
		LeUiObject startUse = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("开始使用"));
		verify("没有找到开始使用",startUse.exists());
		startUse.click();
		sleepInt(1);
		addStep("进入图库");
		launchApp(AppName.GALLERY);
		terms();
		addStep("进入乐视商城");
		launchApp(AppName.LESTORE);
		terms();
		addStep("进入WPS");
		launchApp(AppName.WPS);
		sleepInt(1);
		LeUiObject notips= new LeUiObject(new UiSelector().resourceId(
				"cn.wps.moffice_eng:id/checkBox_flow"));
		LeUiObject agree= new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("同意"));	
		LeUiObject autoLocation= new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("自动定位"));	
		LeUiObject beijing= new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("北京"));	
		if(notips.exists()){
			notips.click();
			sleepInt(2);
		}
		if(agree.exists()){
			agree.click();
			sleepInt(2);	
		}
		addStep("进入天气");
		launchApp(AppName.WEATHER);
		terms();
	if(autoLocation.exists()){
		beijing.click();
		sleepInt(2);
	}
		launchApp(AppName.MAP);
		terms();
		LeUiObject confirm= new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("确定"));
		LeUiObject noTips= new LeUiObject(new UiSelector().resourceId("com.baidu.BaiduMap:id/flow_hint_ask"));	
		if (confirm.exists()) {
			noTips.click();
			sleepInt(1);
			confirm.click();
			sleepInt(1);
		}
		LeUiObject prompts = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("Welcome to baidu map"));
		if(prompts.exists()){
			LeUiObject noAsk1 = new LeUiObject(new UiSelector().className(
					"android.widget.CheckBox").textContains("No ask"));
			LeUiObject noAsk2 = new LeUiObject(new UiSelector().className(
					"android.widget.CheckBox").textContains("不再提示"));
			verify("不再提示没有显示",noAsk1.exists()||noAsk2.exists());
			if(noAsk1.exists()){
				if(!(noAsk1.isChecked())){
					noAsk1.click();
					sleepInt(1);
				}
			}else {
				if(!(noAsk2.isChecked())){
					noAsk2.click();
					sleepInt(1);
				}
			}
			
			LeUiObject OK1 = new LeUiObject(new UiSelector().className(
					"android.widget.Button").textContains("Ok"));
			LeUiObject OK2 = new LeUiObject(new UiSelector().className(
					"android.widget.Button").textContains("确定"));
			verify("OK按钮不存在",OK1.exists()||OK2.exists());
			if(OK1.exists()){
				OK1.click();
			}else{
				OK2.click();
			}
			
			sleepInt(3);
		}
		LeUiObject allow= new LeUiObject(new UiSelector().className(
				"android.widget.Button").resourceId("android:id/le_bottomsheet_btn_confirm_5"));
		if (allow.exists()) {
			allow.click();
		}
		
		phone.drag(1000, 500, 40, 500, 8);
		sleepInt(1);
		phone.drag(1000, 500, 40, 500, 8);
		/*LeUiObject startMap = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId("com.baidu.BaiduMap:id/start_map"));
		
		startMap.click();*/
		sleepInt(2);
		LeUiObject cancel1= new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("取消"));
		LeUiObject cancel2= new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("取消"));
		if (cancel1.exists()) {
			cancel1.click();
			sleepInt(1);
		}
		if (cancel2.exists()) {
			cancel2.click();
			sleepInt(1);
		}
		if (cancel2.exists()) {
			cancel2.click();
			sleepInt(1);
		}
	}
	
	@CaseName("MTBF测试初始化app")
	public void testOpenApps2() throws UiObjectNotFoundException {
		addStep("进入日历");
		launchApp(AppName.CALENDAR);
		terms();
		addStep("进入音乐");
		launchApp(AppName.MUSIC);
		terms();
		LeUiObject sure = new LeUiObject(new UiSelector().textContains("确定"));
		LeUiObject install = new LeUiObject(new UiSelector().textContains("安装"));
		LeUiObject open = new LeUiObject(new UiSelector().textContains("打开"));
		if(sure.exists()){
			sure.click();
			sleepInt(1);
		}
		addStep("进入遥控器");
		launchApp(AppName.TVCONTROLLER);
		terms();
		launchApp(AppName.TVCONTROLLER);
		if(sure.exists()){
			sure.click();
			sleepInt(1);
			install.click();
			sleepInt(1);
			open.click();
			sleepInt(1);
			press_back(1);
		}
		addStep("进入乐视视频");
		launchApp(AppName.LETVVIDEO);
		terms();
		addStep("进入文件管理器");
		launchApp(AppName.FILEMANAGER);
		terms();
		addStep("进入壁纸");
		launchApp(AppName.WALLPAPER);
		terms();
		launchApp(AppName.PHONE);
		LeUiObject yellowPage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("黄页"));
		verify("没有找到黄页", yellowPage.exists());
		yellowPage.click();
		sleepInt(1);
		terms();
		launchApp(AppName.LESTORE);
		sleepInt(1);
		LeUiObject agree = new LeUiObject(new UiSelector().text("同意并继续"));
		if(agree.exists()){
			agree.click();
			sleepInt(1);
		}
		for (int i=0;i<5;i++){
			phone.swipe((int)(phone.getDisplayWidth()*0.8), (int)(phone.getDisplayHeight()*0.75),
					(int)(phone.getDisplayWidth()*0.2), (int)(phone.getDisplayHeight()*0.75), 30);
			sleepInt(1);
			LeUiObject enterStore = new LeUiObject(new UiSelector().text("进入商店"));
			if(enterStore.exists()){
				break;
			}
		}
		
	}
	
	@CaseName("设置允许未知来源安装")
	public void testAppInstallSet() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		UiScrollable panal=new UiScrollable(new UiSelector().className("android.widget.ListView").resourceId("android:id/list"));
		LeUiObject security=new LeUiObject(new UiSelector().className("android.widget.TextView").textContains("密码"));
		LeUiObject alwaysAsk=new LeUiObject(new UiSelector().className("android.widget.TextView").text("后台安装应用"));
		LeUiObject allow=new LeUiObject(new UiSelector().className("android.widget.TextView").text("允许"));
		LeUiObject titleMax=new LeUiObject(new UiSelector().className("android.widget.TextView").text("指纹和密码"));
		UiObject leSwitchMax=new UiObject(new UiSelector().resourceId("android:id/switchWidget").instance(2));
		UiObject leSwitchX=new UiObject(new UiSelector().resourceId("android:id/switchWidget").instance(1));
		sleepInt(1);
		if(!security.exists()){
			panal.scrollIntoView(new UiSelector().textContains("密码"));
			sleepInt(2);
		}
		LeUiObject ok=new LeUiObject(new UiSelector().className("android.widget.Button").text("确定"));
		verify("没有找到密码安全", security.exists());
		security.click();
		sleepInt(2);
		if(titleMax.exists()){
	/*		if(!leSwitch.exists()){
				panal.scrollTextIntoView("未知来源");
			}*/
			leSwitchMax.click();
			sleepInt(1);
			
			verify("没有找到确定按钮", ok.exists());
			ok.click();
			sleepInt(2);
		}else{
/*			 i=2;
			if(!leSwitch.exists()){
				panal.scrollTextIntoView("未知来源");
			}*/
			leSwitchX.click();
			sleepInt(1);
			verify("没有找到确定按钮", ok.exists());
			ok.click();
			sleepInt(2);
		}
		
		if(!alwaysAsk.exists()){
			panal.scrollIntoView(new UiSelector().textContains("后台安装应用"));
			sleepInt(2);
		}
			alwaysAsk.click();
			sleepInt(2);
			verify("没有找到允许提示", allow.exists());
			allow.click();
			sleepInt(2);
		press_back(1);
	}
	
	@CaseName("设置显示")
	public void testSettingDisplay() throws UiObjectNotFoundException {
		
		press_keyevent(10, 25);
		launchApp(AppName.SETTING);
		LeUiObject display = new LeUiObject(
				new UiSelector()
						.className("android.widget.TextView").text("显示"));
		phone.swipe((int) (phone.getDisplayWidth() * 0.5),
				(int) (phone.getDisplayHeight() * 0.1),
				(int) (phone.getDisplayWidth() * 0.5),
				(int) (phone.getDisplayHeight() * 0.9), 30);
		sleepInt(1);
		addStep("把显示亮度调到最低");
		verify("没有找到显示", display.exists());
		display.click();
		sleepInt(2);
		phone.click((int) (phone.getDisplayWidth() * 0.163),(int) (phone.getDisplayHeight() * 0.202));
		sleepInt(2);
		press_back(1);
		LeUiObject sound=new LeUiObject(new UiSelector().className("android.widget.TextView").text("声音和振动"));
		UiScrollable panal=new UiScrollable(new UiSelector().className("android.widget.ListView").resourceId("android:id/list"));
		UiObject switchTouch=new UiObject(new UiSelector().className("android.widget.LinearLayout").index(7))
		.getChild(new UiSelector().resourceId("android:id/widget_frame"));
		for(int i=0;i<10;i++){
			if(sound.exists()){
				break;
			}else{
				phone.swipe((int) (phone.getDisplayWidth() * 0.5),
						(int) (phone.getDisplayHeight() * 0.4),
						(int) (phone.getDisplayWidth() * 0.5),
						(int) (phone.getDisplayHeight() * 0.3), 30);
			}
			
		}
	/*	if(!sound.exists()){
			panal.scrollForward();
			panal.scrollTextIntoView("声音和振动");
			panal.scrollIntoView(new UiSelector().text("声音和振动"));
			panal.scrollForward(3);
			sleepInt(2);
		}*/
		verify("没有找到声音和振动", sound.exists());
		sound.click();
		addStep("关闭声音和振动");
		sleepInt(2);
		phone.click((int) (phone.getDisplayWidth() * 0.215),(int) (phone.getDisplayHeight() * 0.202));
		sleepInt(1);
		phone.click((int) (phone.getDisplayWidth() * 0.228),(int) (phone.getDisplayHeight() * 0.277));
		sleepInt(1);
		verify("没有找到显示", switchTouch.exists());
		switchTouch.click();
		press_back(2);
	}
	
	public void testWifiConnect() throws UiObjectNotFoundException {
		addStep("连接wifi");
		connectWifi();
		press_back(1);
		LeUiObject wificonnected = new LeUiObject(
				new UiSelector()
						.className("android.widget.TextView").text("已连接"));
		for(int i=0;i<13;i++){
		if(!wificonnected.exists()){
			sleepInt(3);
		}
		}
	}
	
	@CaseName("打开downloadMode enable alllog")
	public void testDumpModeAllLog() throws UiObjectNotFoundException {
		launchApp(AppName.PHONE);
		LeUiObject dialApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("拨号"));
		LeUiObject downLoad = new LeUiObject(new UiSelector().className(
				"android.widget.CheckBox").index(1));
		LeUiObject allLog = new LeUiObject(new UiSelector().className(
				"android.widget.CheckBox").index(2));
		LeUiObject star = new LeUiObject(new UiSelector().resourceId(
				"com.android.dialer:id/dialpad_key_star"));
		LeUiObject pound = new LeUiObject(new UiSelector().resourceId(
						"com.android.dialer:id/dialpad_key_pound"));
		dialApp.clickAndWaitForNewWindow();
		sleepInt(2);
		addStep("打开离线log");
		star.click();
		sleepInt(1);
		pound.click();
		sleepInt(1);
		star.click();
		sleepInt(1);
		pound.click();
		sleepInt(1);
		pressDialPad("76937");
		pound.click();
		sleepInt(1);
		star.click();
		sleepInt(1);
		pound.click();
		sleepInt(1);
		star.click();
		sleepInt(1);
		sleepInt(2);
		verify("没有找到打开downloaddump模式勾选框", downLoad.exists());
		downLoad.click();
		sleepInt(1);
		verify("没有找到打开enableAllLog模式勾选框", allLog.exists());
		allLog.click();
		sleepInt(1);
		press_back(2);
	}
	
	@CaseName("打开downloadMode enable alllog")
	public void testDumpMode() throws UiObjectNotFoundException {
		launchApp(AppName.PHONE);
		LeUiObject dialApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("拨号"));
		LeUiObject downLoad = new LeUiObject(new UiSelector().className(
				"android.widget.CheckBox").index(1));
		LeUiObject allLog = new LeUiObject(new UiSelector().className(
				"android.widget.CheckBox").index(2));
		LeUiObject star = new LeUiObject(new UiSelector().resourceId(
				"com.android.dialer:id/dialpad_key_star"));
		LeUiObject pound = new LeUiObject(new UiSelector().resourceId(
						"com.android.dialer:id/dialpad_key_pound"));
		dialApp.clickAndWaitForNewWindow();
		sleepInt(2);
		addStep("打开离线log");
		star.click();
		sleepInt(1);
		pound.click();
		sleepInt(1);
		star.click();
		sleepInt(1);
		pound.click();
		sleepInt(1);
		pressDialPad("76937");
		pound.click();
		sleepInt(1);
		star.click();
		sleepInt(1);
		pound.click();
		sleepInt(1);
		star.click();
		sleepInt(1);
		sleepInt(2);
		verify("没有找到打开downloaddump模式勾选框", downLoad.exists());
		downLoad.click();
		sleepInt(1);
		press_back(2);
	}

	@CaseName("打开离线log")
	public void testEnableKernalAppLogs() throws UiObjectNotFoundException {
		launchApp(AppName.PHONE);
		UiObject  kernalLog= new UiObject(new UiSelector().className(
				"android.widget.TextView").text("KERNEL LOG"))
		.getFromParent(new UiSelector().className("com.letv.leui.widget.LeSwitch"));
		UiObject  appLog= new UiObject(new UiSelector().className(
				"android.widget.TextView").text("APP LOG"))
		.getFromParent(new UiSelector().className("com.letv.leui.widget.LeSwitch"));
		LeUiObject confirm = new LeUiObject(new UiSelector().textContains("开始记录"));
		LeUiObject know = new LeUiObject(new UiSelector().className(
				"android.widget.Button").textContains("知道了"));
		LeUiObject star = new LeUiObject(new UiSelector().resourceId(
				"com.android.dialer:id/dialpad_key_star"));
		LeUiObject pound = new LeUiObject(new UiSelector().resourceId(
						"com.android.dialer:id/dialpad_key_pound"));
		star.click();
		sleepInt(1);
		pound.click();
		sleepInt(1);
		star.click();
		sleepInt(1);
		pound.click();
		sleepInt(1);
		pressDialPad("8888");
		pound.click();
		sleepInt(1);
		star.click();
		sleepInt(1);
		pound.click();
		sleepInt(1);
		star.click();
		sleepInt(1);
		sleepInt(2);
		verify("没有找到打开appLog模式勾选框", appLog.exists());
		if(!appLog.isChecked()){
			appLog.click();
			sleepInt(1);
		}
		verify("没有找到打开kernalLog模式勾选框", kernalLog.exists());
		if(!kernalLog.isChecked()){
			kernalLog.click();
			sleepInt(1);	
		}
		verify("没有找到开始记录按钮", confirm.exists());
		confirm.click();
		sleepInt(2);
		sleepInt(5);
	}
	@CaseName("MTBF测试前提条件")
	public void testcalandarcontact() throws UiObjectNotFoundException {
		addStep("日历添加一个日程");
		launchApp(AppName.CALENDAR);
		LeUiObject add = new LeUiObject(
				new UiSelector()
						.className("android.widget.TextView").text("添加"));
		verify("Can't find add button.", add.exists());
		add.click();
		sleepInt(2);
		LeUiObject eventName = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/title").text("活动名称"));
		verify("Can't find event name item.", eventName.exists());
		eventName.click();
		sleepInt(1);
		callShell("input text 010");
		sleepInt(2);
		LeUiObject complete = new LeUiObject(new UiSelector().resourceId("com.android.calendar:id/action_done"));
		verify("Can't find complete button.", complete.exists());
		complete.click();
		sleepInt(2);
		letvCount();
		sleepInt(3);
		press_back(3);
		press_home(1);
		addStep("添加一个联系人");
		launchApp(AppName.CONTACT);
		LeUiObject add1 = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("新建联系人"));
		add1.click();
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定").resourceId(
				"com.android.contacts:id/right_button"));
		if (confirm.exists()) {
			confirm.click();
			sleepInt(1);
		}
		// fill name field
		LeUiObject name = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("姓名"));
		verify("Can't find name field.", name.exists());
		name.setText("123");
		sleepInt(2);
		LeUiObject number = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("电话"));
		verify("Can't find number field.", number.exists());
		number.setText("10086");
		sleepInt(2);
		// click complete button
		LeUiObject complete1 = new LeUiObject(new UiSelector().resourceId("com.android.contacts:id/menudone"));
		verify("Can't find complete button.", complete.exists());
		complete1.click();
		sleepInt(3);
		letvCount();
		sleepInt(4);
		addStep("删除联系人");
		LeUiObject delete = new LeUiObject(new UiSelector().textContains("删除").resourceId(
				"android:id/title"));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(1);
		LeUiObject deletecontact = new LeUiObject(new UiSelector().textContains("删除"));
		verify("Can't find delete confirm button.", deletecontact.exists());
		deletecontact.click();
		sleepInt(4);
    	verify("删除失败",!deletecontact.exists());
		press_back(3);
		press_home(1);
	}
	LeUiObject addBookName = new LeUiObject(new UiSelector().text("书签名称"));
	LeUiObject addBookUrl = new LeUiObject(new UiSelector().text("书签地址"));
	@CaseName("MTBF测试前提条件")
	public void testaddBookmark() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(4);
		LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
		verify("Can't find home page button.", home.exists());
		home.click();
		sleepInt(2);
		addStep("查看是否有书签");
		LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
		verify("没有更多设置",more.exists());
		more.click();
		sleepInt(2);
		LeUiObject bookmark = new LeUiObject(new UiSelector().text("书签/历史").resourceId("com.android.browser:id/menu_item_text"));
		verify("没有书签选项",bookmark.exists());
		bookmark.click();
		sleepInt(2);
		LeUiObject bookmarklist = new LeUiObject(new UiSelector().className("android.widget.ListView").resourceId("com.android.browser:id/grid"));
		addStep("删除书签");
		if(bookmarklist.getChildCount()>1){
			LeUiObject firstbookmark = new LeUiObject(new UiSelector().className("android.widget.HorizontalScrollView").index(0));
			verify(firstbookmark.exists());
			int x = firstbookmark.getBounds().centerX();
			int y = firstbookmark.getBounds().centerY();
			phone.swipe(x, y, x, y, 50);
			sleepInt(2);
			LeUiObject allSelect = new LeUiObject(new UiSelector().text("全选"));
			if(allSelect.exists()){
				allSelect.click();
				sleepInt(2);
				LeUiObject delete = new LeUiObject(new UiSelector().text("删除"));
				verify(delete.exists());
				delete.click();
				LeUiObject confim = new LeUiObject(new UiSelector().textContains("删除"));
				verify(confim.exists());
				confim.click();
				sleepInt(1);
			}
			LeUiObject allNotSelect = new LeUiObject(new UiSelector().text("全不选"));
			if(allNotSelect.exists()){
				sleepInt(2);
				LeUiObject delete = new LeUiObject(new UiSelector().text("删除"));
				verify(delete.exists());
				delete.click();
				LeUiObject confim = new LeUiObject(new UiSelector().textContains("删除"));
				verify(confim.exists());
				confim.click();
				sleepInt(1);
			}
			LeUiObject noBookmark = new LeUiObject(new UiSelector().textContains("没有书签"));
			verify(noBookmark.exists());			
		}
		addStep("添加书签");
		LeUiObject addBookmark = new LeUiObject(new UiSelector().text("添加书签"));
		if(addBookmark.exists()){
			addBookmark.click();
			sleepInt(2);
			verify(addBookName.exists());
			verify(addBookUrl.exists());
			addBookName.setText(Utf7ImeHelper.e("text"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://10.57.9.203/txt.php"));
			sleepInt(2);
			LeUiObject save = new LeUiObject(new UiSelector().text("保存"));
			verify(save.exists());
			save.click();
			sleepInt(2);
			verify(addBookmark.exists());
			addBookmark.click();
			sleepInt(2);
			verify(addBookName.exists());
			verify(addBookUrl.exists());
			addBookName.setText(Utf7ImeHelper.e("mp3"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://10.57.9.203/music.php"));
			sleepInt(2);
			verify(save.exists());
			save.click();
			sleepInt(2);
			verify(addBookmark.exists());
			addBookmark.click();
			sleepInt(2);
			verify(addBookName.exists());
			verify(addBookUrl.exists());
			addBookName.setText(Utf7ImeHelper.e("picture"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://10.57.9.203/picture.php"));
			sleepInt(2);
			verify(save.exists());
			save.click();
			sleepInt(2);
			verify(addBookmark.exists());
			addBookmark.click();
			sleepInt(2);
			verify(addBookName.exists());
			verify(addBookUrl.exists());
			addBookName.setText(Utf7ImeHelper.e("video"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://10.57.9.203/video.php"));
			sleepInt(2);
			verify(save.exists());
			save.click();
			sleepInt(2);
			verify(addBookmark.exists());
			addBookmark.click();
			sleepInt(2);
			verify(addBookName.exists());
			verify(addBookUrl.exists());
			addBookName.setText(Utf7ImeHelper.e("SOGOU"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://www.sogou.com/"));
			sleepInt(2);
			verify(save.exists());
			save.click();
			sleepInt(2);
			press_back(5);
			press_home(1);
		}else{
			press_back(1);
			LeUiObject url= new LeUiObject(new UiSelector().resourceId("com.android.browser:id/url_input_view"));
			verify(url.exists());
			url.setText(Utf7ImeHelper.e("http://www.sogou.com/"));
			phone.pressEnter();
			sleepInt(5);
			LeUiObject share = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_share").index(1));
			verify(share.exists());
			share.click();
			sleepInt(2);
			LeUiObject addbookmark = new LeUiObject(new UiSelector().resourceId("android:id/share_title").text("加入书签"));
			verify(addbookmark.exists());
			addbookmark.click();
			sleepInt(2);
			LeUiObject clearBMUrl = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(2).childSelector(new UiSelector().resourceId("com.android.browser:id/bookmark_url_clear")));
			LeUiObject clearBMName = new LeUiObject(new UiSelector().className("android.widget.LinearLayout").index(1).childSelector(new UiSelector().resourceId("com.android.browser:id/bookmark_name_clear")));
			verify(clearBMUrl.exists());
			clearBMUrl.click();
			sleepInt(2);
			verify(clearBMName.exists());
			clearBMName.click();
			sleepInt(2);
			addBookName.setText(Utf7ImeHelper.e("text"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://10.57.9.203/txt.php"));
			sleepInt(2);
			LeUiObject save = new LeUiObject(new UiSelector().text("保存"));
			verify(save.exists());
			save.click();
			sleepInt(2);
			verify(share.exists());
			share.click();
			sleepInt(2);
			verify(addbookmark.exists());
			addbookmark.click();
			sleepInt(2);
			verify(clearBMUrl.exists());
			clearBMUrl.click();
			sleepInt(2);
			verify(clearBMName.exists());
			clearBMName.click();
			sleepInt(2);
			addBookName.setText(Utf7ImeHelper.e("mp3"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://10.57.9.203/music.php"));
			sleepInt(2);
			verify(save.exists());
			save.click();
			sleepInt(2);
			verify(share.exists());
			share.click();
			sleepInt(2);
			verify(addbookmark.exists());
			addbookmark.click();
			sleepInt(2);
			verify(clearBMUrl.exists());
			clearBMUrl.click();
			sleepInt(2);
			verify(clearBMName.exists());
			clearBMName.click();
			sleepInt(2);
			addBookName.setText(Utf7ImeHelper.e("picture"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://10.57.9.203/picture.php"));
			sleepInt(2);
			verify(save.exists());
			save.click();
			sleepInt(2);
			verify(share.exists());
			share.click();
			sleepInt(2);
			verify(addbookmark.exists());
			addbookmark.click();
			sleepInt(2);
			verify(clearBMUrl.exists());
			clearBMUrl.click();
			sleepInt(2);
			verify(clearBMName.exists());
			clearBMName.click();
			sleepInt(2);
			addBookName.setText(Utf7ImeHelper.e("video"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://10.57.9.203/video.php"));
			sleepInt(2);
			verify(save.exists());
			save.click();
			sleepInt(2);
			verify(share.exists());
			share.click();
			sleepInt(2);
			verify(addbookmark.exists());
			addbookmark.click();
			sleepInt(2);
			verify(clearBMUrl.exists());
			clearBMUrl.click();
			sleepInt(2);
			verify(clearBMName.exists());
			clearBMName.click();
			sleepInt(2);
			addBookName.setText(Utf7ImeHelper.e("SOGOU"));
			sleepInt(2);
			addBookUrl.setText(Utf7ImeHelper.e("http://www.sogou.com/"));
			sleepInt(2);
			verify(save.exists());
			save.click();
			sleepInt(2);
			press_back(4);
			press_home(1);
		}
		
	}
}
