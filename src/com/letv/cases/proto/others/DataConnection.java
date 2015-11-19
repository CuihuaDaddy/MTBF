package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class DataConnection extends LetvTestCase {	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		disconnectData();
		press_back(5);
	}
	
	public void connectData() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		UiObject dataUsage = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("Data usage").resourceId("com.android.settings:id/title"));
		verify("Can't find data usage button.", dataUsage.exists());
		dataUsage.click();
		sleepInt(2);
		UiObject datalimit = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("android:id/title").text("Set cellular data limit"));
		if(!datalimit.exists()){
			UiObject switchWidget = new UiObject(new UiSelector().className("android.widget.Switch").text("OFF"));
			verify("Can't find switchWidget button.", switchWidget.exists());
			switchWidget.click();
		}
		sleepInt(5);
		verify("Can't connect SIM1 data connection.",datalimit.exists());
	}

	public void disconnectData() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.setting);
		sleepInt(2);
		UiObject dataUsage = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("Data usage").resourceId("com.android.settings:id/title"));
		verify("Can't find data usage button.", dataUsage.exists());
		dataUsage.click();
		sleepInt(2);
		UiObject datalimit = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("android:id/title").text("Set cellular data limit"));
		if(datalimit.exists()){
			UiObject switchWidget = new UiObject(new UiSelector().className("android.widget.Switch").text("ON"));
			verify("Can't find switchWidget button.", switchWidget.exists());
			switchWidget.click();
			sleepInt(3);
			UiObject Note = new UiObject(new UiSelector().resourceId("android:id/message").className("android.widget.TextView").text("Turn off cellular data?"));
			if(Note.exists()){
				UiObject OK = new UiObject(new UiSelector().resourceId("android:id/button1").className("android.widget.Button").text("OK"));
				OK.click();
			}
		}
		sleepInt(5);
		verify("Can't disconnect SIM1 data connection.",!datalimit.exists());
	}

	@CaseName("DataConnection")
	public void testDataConnection() throws UiObjectNotFoundException {
		for (int i = 0;i< getIntParams("Loop");i++){
			addStep("connect data connection");
			connectData();
			sleepInt(5);
			addStep("disconncet data connection");
			disconnectData();
			sleepInt(5);
		}
			press_back(5);
	}
}
