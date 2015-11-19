package com.letv.cases.leui.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Location extends LetvTestCaseMTBF {
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		closeAPM();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		openGPS();
		press_back(5);
		super.tearDown();

	}
	
	public void openGPS() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		/*UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().className("android.widget.LinearLayout").packageName("com.android.settings")
						.index(6)));		*/
        /*mengfengxiao@letv.com*/
        UiScrollable settingPanel = new UiScrollable(new UiSelector().resourceId("android:id/list").className("android.widget.ListView").scrollable(true));
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
			return;
		} else {
			switchWidget.click();
			sleepInt(5);
			LeUiObject connectInfo = new LeUiObject(new UiSelector().resourceId(
					"com.android.settings:id/switch_text").text("开启"));
			verify("Can't open Location.",
					switchWidget.isChecked() || connectInfo.exists());
		}
	}

	public void closeGPS() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		
		/*UiScrollable settingPanel = new UiScrollable(new UiSelector()
		.className("android.widget.FrameLayout")
		.packageName("com.android.settings")
		.childSelector(
				new UiSelector().className("android.widget.LinearLayout").packageName("com.android.settings")
						.index(6)));		*/
        UiScrollable settingPanel = new UiScrollable(new UiSelector().resourceId("android:id/list").className("android.widget.ListView").scrollable(true));
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
			switchWidget.click();
			sleepInt(5);
			verify("Can't close GPS.", !(switchWidget.isChecked()));
		} else {
			return;
		}
	}

	@CaseName("Location 10次")
	public void testGPSConnection() throws UiObjectNotFoundException {
		for (int i = 0;i< getIntParams("Loop");i++){
			addStep("Open GPS");
			openGPS();
			sleepInt(2);
			press_back(2);
			addStep("Close GPS");
			closeGPS();
			sleepInt(2);
			press_back(2);
		}
			press_back(5);
	}
}
