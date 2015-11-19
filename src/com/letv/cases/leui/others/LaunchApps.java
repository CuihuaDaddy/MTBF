package com.letv.cases.leui.others;


import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class LaunchApps extends LetvTestCaseMTBF {
	private String MapPackage = "com.baidu.BaiduMap";
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

	private String[] apps = { AppName.PHONE,
			AppName.BROWSER, AppName.DOWNLOAD,
			AppName.FILEMANAGER, AppName.CALENDAR,
			AppName.CAMERA,AppName.GALLERY
			,AppName.WEATHER,AppName.MAP,AppName.CLOCK,AppName.SETTING,AppName.MESSAGE,AppName.CALCULATOR,AppName.RECORDER,AppName.MUSIC};
	
	@CaseName("浏览本地应用")
	public void testOpenApps() throws UiObjectNotFoundException {
		addStep("进入应用导航页面");
		phone.pressHome();
		for (int i = 0; i < apps.length; i++) {
			phone.pressHome();
			addStep("逐一点击本地应用");
			launchApp(apps[i]);
			sleepInt(5);
			addStep("逐一关闭本地应用");
			press_back(5);
			
		}
		press_back(5);
		addStep("返回主页面");
		phone.pressHome();
	}
	
	@CaseName("浏览本地应用")
	public void testOpenAppsSea() throws UiObjectNotFoundException {
		addStep("进入应用导航页面");
		
		phone.pressHome();
		for (int i = 0; i < apps.length; i++) {
			System.out.println(apps[i]);
			addStep("逐一点击本地应用");
			if(i==1||i==8||i==7||i==13||i==10){
				continue;
			}
			launchApp(apps[i]);
			sleepInt(5);
			addStep("逐一关闭本地应用");
			press_back(5);
		}
		launchApp(AppName.MAP);
		LeUiObject sure = new LeUiObject(new UiSelector().className("android.widget.Button").text("确定"));
		press_back(1);
		sleepInt(1);
		sure.click();
		sleepInt(1);
		addStep("返回主页面");
		phone.pressHome();
	}

}
