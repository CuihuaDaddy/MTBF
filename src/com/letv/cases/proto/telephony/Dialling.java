package com.letv.cases.proto.telephony;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class Dialling extends LetvTestCase {

	@CaseName("从拨号盘发起呼叫")
	public void testDialling() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.proto_telephone);
		sleepInt(2);

		addStep("拨打号码" + getStrParams("dialNum"));
		pressDialPad(getStrParams("dialNum"));

		addStep("拨出电话");
		UiObject dialBtn = new UiObject(new UiSelector().resourceId(
				"com.android.dialer:id/dialpad_floating_action_button")
				.description("dial"));
		verify("未找到拨打电话按钮", dialBtn.waitForExists(5000));
		dialBtn.click();
		// UiObject diallingPrompt = new UiObject(new UiSelector().className(
		// "android.widget.TextView").text("正在拨号"));
		// verify("未能成功拨出号码", diallingPrompt.waitForExists(5000));

		addStep("验证成功拨号");
		sleepInt(3);
		UiObject phoneApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("Phone").description("Phone"));
//		launchApp(phoneApp);			
		UiObject ReturnCall = new UiObject(new UiSelector().resourceId(
				"com.android.dialer:id/text").text("Return to call in progress"));
		ReturnCall.click();
		sleepInt(1);
		UiObject endBtn = new UiObject(new UiSelector().resourceId(
				"com.android.dialer:id/floating_end_call_action_button")
				.description("End"));
		verify("未能成功接通", endBtn.waitForExists(20000));
		sleepInt(5);
		addStep("挂断电话");
		endBtn.click();
		sleepInt(1);
		addStep("循环拨打" + String.valueOf(getIntParams("Loop")) + "次电话");
		UiObject dialPad = new UiObject(new UiSelector().resourceId(
				"com.android.dialer:id/floating_action_button").description(
				"dial pad"));
		for (int i = 1; i <= getIntParams("Loop"); i++) {
//			launchApp(phoneApp);
			if (dialPad.exists()) {
				dialPad.clickAndWaitForNewWindow();
				sleepInt(1);
			}
			addStep("正在拨打第" + String.valueOf(i) + "遍");
			dialBtn.click();
			sleep(1000);
			dialBtn.click();
			sleepInt(3);
//			launchApp(phoneApp);
			ReturnCall.click();
			sleepInt(1);
			verify("未能成功接通", endBtn.waitForExists(20000));
			sleepInt(5);
			verify("电话已中断", endBtn.exists());
			endBtn.click();
			sleepInt(1);
		}

		addStep("返回桌面");

		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	@CaseName("从拨号盘发起呼叫")
	public void testDialling_1() throws UiObjectNotFoundException {
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
		addStep("打开拨号程序");
		UiObject dialApp = new UiObject(new UiSelector()
				.packageName("com.android.launcher")
				.className("android.widget.TextView").text("Phone"));
		dialApp.clickAndWaitForNewWindow();
		sleepInt(1);
		addStep("拨打号码" + getStrParams("dialNum"));
		pressDialPad(getStrParams("dialNum"));

		addStep("拨出电话");
		UiObject dialBtn = new UiObject(new UiSelector().resourceId(
				"com.android.dialer:id/dialpad_floating_action_button")
				.description("dial"));
		verify("未找到拨打电话按钮", dialBtn.waitForExists(5000));
		dialBtn.click();
		// UiObject diallingPrompt = new UiObject(new UiSelector().className(
		// "android.widget.TextView").text("正在拨号"));
		// verify("未能成功拨出号码", diallingPrompt.waitForExists(5000));

		addStep("验证成功拨号");
		verify("没有跳回桌面", dialApp.waitForExists(10000));
		sleepInt(2);
		dialApp.clickAndWaitForNewWindow();
		UiObject endBtn = new UiObject(new UiSelector().resourceId(
				"com.android.dialer:id/text")
				.text("Return to call in progress"));
		verify("未能成功接通", endBtn.waitForExists(10000));
		sleepInt(5);
		addStep("挂断电话");
		callShell("input keyevent 6");
		sleepInt(2);
		press_back(1);

		addStep("循环拨打" + String.valueOf(getIntParams("Loop")) + "次电话");
		UiObject dialPad = new UiObject(new UiSelector().resourceId(
				"com.android.dialer:id/floating_action_button").description(
				"dial pad"));
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			verify("Can't find dialer Pad.", dialPad.waitForExists(10000));
			dialPad.click();
			sleepInt(2);
			addStep("正在拨打第" + String.valueOf(i) + "遍");
			dialBtn.click();
			sleep(1000);
			dialBtn.click();
			verify("没有跳回桌面", dialApp.waitForExists(10000));
			sleepInt(2);
			dialApp.clickAndWaitForNewWindow();
			verify("未能成功接通", endBtn.waitForExists(10000));
			sleepInt(5);
			verify("电话已中断", endBtn.exists());
			addStep("挂断电话");
			callShell("input keyevent 6");
			sleepInt(2);
			press_back(1);
		}

		addStep("返回桌面");

		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

//	private void pressDialPad(String number) throws UiObjectNotFoundException {
//		UiObject dialApp = new UiObject(new UiSelector().resourceId(
//				"com.android.dialer:id/floating_action_button").description(
//				"dial pad"));
//		if (dialApp.exists()) {
//			dialApp.clickAndWaitForNewWindow();
//			sleepInt(1);
//		}
//		for (int i = 0; i < number.length(); i++) {
//			UiObject numBtn = new UiObject(new UiSelector()
//					.className("android.widget.TextView")
//					.resourceId("com.android.dialer:id/dialpad_key_number")
//					.text(String.valueOf(number.charAt(i))));
//			numBtn.click();
//			sleepInt(1);
//		}
//	}

}
