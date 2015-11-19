package com.letv.cases.leui.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Bluetooth extends LetvTestCaseMTBF {
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		closeAPM();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		closeBT();
		press_back(5);
		super.tearDown();

	}
	
	public void openBT() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject BT = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("蓝牙").resourceId("android:id/title"));
		verify("Can't find Bluetooth button.", BT.exists());
		BT.click();
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
			verify("Can't open Bluetooth.",
					switchWidget.isChecked() || connectInfo.exists());
		}
	}

	public void closeBT() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject BT = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("蓝牙").resourceId("android:id/title"));
		verify("Can't find Bluetooth button.", BT.exists());
		BT.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			switchWidget.click();
			sleepInt(5);
			verify("Can't close Bluetooth.", !(switchWidget.isChecked()));
		} else {
			return;
		}
	}

	@CaseName("Bluetooth")
	public void testBluetoothConnection() throws UiObjectNotFoundException {
		for (int i = 0;i< getIntParams("Loop");i++){
			addStep("Open Bluetooth");
			openBT();
			sleepInt(5);
			press_back(2);
			addStep("Close Bluetooth");
			closeBT();
			sleepInt(5);
			press_back(2);
		}
			press_back(5);
	}
}
