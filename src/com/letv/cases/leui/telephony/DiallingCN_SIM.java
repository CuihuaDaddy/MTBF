package com.letv.cases.leui.telephony;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class DiallingCN_SIM extends LetvTestCaseMTBF {
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	@CaseName("从拨号盘发起呼叫")
	public void testDialling() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.telephone);
		addStep("打开拨号程序");
		LeUiObject dialApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("拨号"));
		dialApp.clickAndWaitForNewWindow();
		sleepInt(2);

		addStep("拨打号码" + getStrParams("dialNum"));
		pressDialPad(getStrParams("dialNum"));

		addStep("拨出电话");
/*		LeUiObject dialBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TabHost").index(1))
				.getChild(
						new UiSelector().className("android.widget.TabWidget")
								.index(1)).getChild(
						new UiSelector().className(
								"android.widget.RelativeLayout").index(1));
*/
//		LeUiObject dialBtn1 = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/show_call_1").className("android.widget.FrameLayout"));
//		LeUiObject dialBtn2 = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/show_call_2").className("android.widget.FrameLayout"));
		LeUiObject dialBtn = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/show_call_1").className("android.widget.FrameLayout"));
		dialBtn.click();
		LeUiObject diallingPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("正在拨号"));
		verify("未能成功拨出号码", diallingPrompt.waitForExists(5000));

		addStep("验证成功拨号");
		LeUiObject endBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.dialer:id/end_call_button"));
		verify("未能成功接通", diallingPrompt.waitUntilGone(10000) && endBtn.exists());
		sleepInt(5);
		addStep("挂断电话");
		endBtn.click();
		sleepInt(2);

		addStep("循环拨打" + String.valueOf(getIntParams("Loop")) + "次电话");
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("正在拨打第" + String.valueOf(i) + "遍");
			if(i%2==0){
				dialBtn.click();
				sleepInt(1);
				dialBtn.click();
			}else{
				dialBtn.click();
				sleepInt(1);
				dialBtn.click();
			}
			
			verify("未能成功拨出号码", diallingPrompt.waitForExists(5000));
			verify("未能成功接通",
					diallingPrompt.waitUntilGone(10000) && endBtn.exists());
			sleepInt(5);
			verify("电话已中断", endBtn.exists());
			endBtn.click();
			sleepInt(2);
		}

		addStep("返回桌面");

		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

//	private void pressDialPad(String number) throws UiObjectNotFoundException {
//		for (int i = 0; i < number.length(); i++) {
//			LeUiObject numBtn = new LeUiObject(new UiSelector()
//					.className("android.widget.TextView")
//					.resourceId("com.android.dialer:id/dialpad_key_number")
//					.text(String.valueOf(number.charAt(i))));
//			numBtn.click();
//			sleepInt(1);
//		}
//	}

}
