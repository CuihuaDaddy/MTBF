package com.letv.cases.leui.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class DataConnection extends LetvTestCaseMTBF {	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		closeAPM();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		connectData();
		press_back(5);
		super.tearDown();

	}
	
	public void connectData() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject dataUsage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("双卡和移动网络").resourceId("android:id/title"));
		verify("Can't find data usage button.", dataUsage.exists());
		dataUsage.click();
		sleepInt(2);
		sleepInt(2);
		LeUiObject switchWidget =new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0).childSelector(
						new UiSelector().className("android.widget.LinearLayout").index(1).childSelector(
								new UiSelector().className("com.letv.leui.widget.LeSwitch"))));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(4);
			verify("Can't enabled data.",switchWidget.isChecked());
		}
	}

	public void disconnectData() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject dataUsage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("双卡和移动网络").resourceId("android:id/title"));
		verify("Can't find data usage button.", dataUsage.exists());
		dataUsage.click();
		sleepInt(2);
		sleepInt(2);
		LeUiObject switchWidget =new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0).childSelector(
						new UiSelector().className("android.widget.LinearLayout").index(1).childSelector(
								new UiSelector().className("com.letv.leui.widget.LeSwitch"))));
		verify("Can't find switchWidget button.", switchWidget.exists());
		if (!switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(4);
			verify("Can't disabled data.",!switchWidget.isChecked());
		}
	}

	@CaseName("DataConnection")
	public void testDataConnection() throws UiObjectNotFoundException {
		for (int i = 0;i< getIntParams("Loop");i++){
			addStep("connect data connection");
			connectData();
			sleepInt(2);
			press_back(2);
			addStep("disconncet data connection");
			disconnectData();
			sleepInt(2);
			press_back(2);
		}
			press_back(5);
	}
}
