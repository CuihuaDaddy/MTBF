package com.letv.cases.leui.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class AirplaneMode extends LetvTestCaseMTBF {
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		closeAPM();
		press_back(5);
		super.tearDown();

	}
	
	
	public void openAPM() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject APM = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("飞行模式").resourceId("android:id/title"));
		if(!APM.exists()){
			press_back(2);
			press_home(1);
			launchApp(AppName.SETTING);
		}
		verify("Can't find Airplan Mode Button.", APM.exists());
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switchWidget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(1);
			LeUiObject note = new LeUiObject(new UiSelector().text("启用飞行模式").resourceId("android:id/alertTitle"));
			LeUiObject ok = new LeUiObject(new UiSelector().text("确定").resourceId("android:id/button1"));		
			if(note.exists()){
				sleepInt(1);
				ok.click();
				sleepInt(1);
			}
			sleepInt(5);
			verify("Can't open Airplan mode.",switchWidget.isChecked());
		}
	}

	public void closeAPM() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject APM = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("飞行模式").resourceId("android:id/title"));
		verify("Can't find Airplan Mode Button.", APM.exists());
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switchWidget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (!switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(5);
			verify("Can't close Airplan mode.",!switchWidget.isChecked());
		}
	}

	@CaseName("APM")
	public void testAPMConnection() throws UiObjectNotFoundException {
		for (int i = 0;i< getIntParams("Loop");i++){
			addStep("Open APM");
			openAPM();
			sleepInt(5);
			press_back(2);
			addStep("Close APM");
			closeAPM();
			sleepInt(5);
			press_back(2);
		}
			press_back(5);
	}
}
