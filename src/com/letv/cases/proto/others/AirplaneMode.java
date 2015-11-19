package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class AirplaneMode extends LetvTestCase {
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		closeAPM();
		press_back(5);
	}
	
	public void openAPM() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		UiObject more = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("More").resourceId("com.android.settings:id/title"));
		verify("Can't find more Button.", more.exists());
		more.click();
		sleepInt(2);
		UiObject APM = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("Airplane mode").resourceId("android:id/title"));
		verify("Can't find Airplan Mode Button.", APM.exists());
		sleepInt(2);
		UiObject switchWidget = new UiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)).
				getChild(new UiSelector().resourceId("android:id/widget_frame").className("android.widget.LinearLayout").index(1)).
				getChild(new UiSelector().resourceId("android:id/switchWidget").className("android.widget.Switch"));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(5);
			verify("Can't open Airplan mode.",switchWidget.isChecked());
		}
	}

	public void closeAPM() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		UiObject more = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("More").resourceId("com.android.settings:id/title"));
		verify("Can't find more Button.", more.exists());
		more.click();
		sleepInt(2);
		UiObject APM = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("Airplane mode").resourceId("android:id/title"));
		verify("Can't find Airplan Mode Button.", APM.exists());
		sleepInt(2);
		UiObject switchWidget = new UiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0)).
				getChild(new UiSelector().resourceId("android:id/widget_frame").className("android.widget.LinearLayout").index(1)).
				getChild(new UiSelector().resourceId("android:id/switchWidget").className("android.widget.Switch"));
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
