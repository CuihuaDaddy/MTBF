package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class Wifi extends LetvTestCase {

	private String url = "www.sina.cn";

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		closeWifi();
		press_back(5);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		openWifi();
		press_back(5);
	}

	public void openWifi() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		UiObject wlan = new UiObject(new UiSelector().className(
				"android.widget.TextView").textContains("Wi").resourceId("com.android.settings:id/title"));
		verify("Can't find Wi-Fi button.", wlan.exists());
		wlan.click();
		sleepInt(2);
		UiObject switchWidget = new UiObject(new UiSelector().className(
				"android.widget.Switch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(15);
			UiObject connectInfo = new UiObject(new UiSelector().resourceId(
					"android:id/summary").text("Connected"));
			verify("Can't open wifi.",
					switchWidget.isChecked() || connectInfo.exists());
		}
	}

	public void closeWifi() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		UiObject wlan = new UiObject(new UiSelector().className(
				"android.widget.TextView").textContains("Wi").resourceId("com.android.settings:id/title"));
		verify("Can't find Wi-fi button.", wlan.exists());
		wlan.click();
		sleepInt(2);
		UiObject switchWidget = new UiObject(new UiSelector().className(
				"android.widget.Switch").resourceId(
				"com.android.settings:id/switch_widget"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			switchWidget.click();
			sleepInt(10);
			verify("Can't close wifi.", !(switchWidget.isChecked()));
		} else {
			return;
		}
	}

	private void browseWebPage() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.proto_browser);
		sleepInt(2);
		UiObject urlEdit = new UiObject(new UiSelector().className(
				"android.widget.EditText").resourceId(
				"com.android.browser:id/url"));
		verify("Can't find url input EditText.", urlEdit.exists());
		urlEdit.click();
		callShell("input text " + url);
		sleepInt(1);
		getUiDevice().pressEnter();
		sleepInt(10);
		// refresh web page
		for (int i = 0; i < 4; i++) {
			urlEdit.click();
			getUiDevice().pressEnter();
			sleepInt(5);
		}
	}

	@CaseName("wifi")
	public void testWifiConnection() throws UiObjectNotFoundException {
		addStep("Open wifi");
		openWifi();
		addStep("Connect wifi");
		sleepInt(10);
		UiObject connectInfo = new UiObject(new UiSelector().resourceId(
				"android:id/summary").text("Connected"));
		verify("Can't connect to wifi.", connectInfo.exists());
		press_back(5);
		for (int i = 0; i < TestCaseLoop; i++) {
			addStep("Open brower，进入特定网页，每隔5S刷新一次，持续20S");
			browseWebPage();
			addStep("Quit brower");
			press_back(5);
		}
		closeWifi();
	}
}
