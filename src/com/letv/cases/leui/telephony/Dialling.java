package com.letv.cases.leui.telephony;

import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Dialling extends LetvTestCaseMTBF {
	private String PhonePackage = "com.android.dialer";
	boolean call = false;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		Log.i(MTCalltag, "StopCall");
		callShell("am force-stop " + PhonePackage);
		super.tearDown();
	}

	@CaseName("从拨号盘发起呼叫")
	public void testDialling() throws UiObjectNotFoundException {
		launchApp(AppName.PHONE);
		addStep("打开拨号程序");
		LeUiObject dialApp = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("拨号"));
		dialApp.clickAndWaitForNewWindow();
		sleepInt(2);

		/*addStep("拨打号码" + getStrParams("dialNum1"));*/
		/*pressDialPad(getStrParams("dialNum1"));
*/
		/*addStep("拨出电话");*/
		LeUiObject dialBtn1 = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/show_call_1").className("android.widget.FrameLayout"));
		LeUiObject dialBtn2 = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/show_call_2").className("android.widget.FrameLayout"));
		/*dialBtn1.click();
		for(int i=0;i<=getIntParams("Loop");i++){
			sleepInt(1);
			if(callShell("dumpsys telecom").contains("DIALING")){
				addStep("正在拨号");
			}else if(callShell("dumpsys telecom").contains("ALERTING")){
				addStep("成功拨出号码");
			}else if(callShell("dumpsys telecom").contains("ACTIVE")){
				addStep("成功拨通号码");
				sleepInt(1);
				LeUiObject endBtn = new LeUiObject(new UiSelector().className(
						"android.widget.ImageButton").resourceId(
						"com.android.dialer:id/end_call_button"));
				verify("未能成功接通",endBtn.exists());
				sleepInt(5);
				addStep("挂断电话");
				endBtn.click();
				break;
			}else if(i>getIntParams("Loop")){
				addStep("等待100秒未拨通电话");
				break;
			}
		}
		sleepInt(4);*/


		addStep("循环拨打" + String.valueOf(getIntParams("Loop")) + "次电话");
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("正在拨打第" + String.valueOf(i) + "遍");
			if(i%2==0){
				pressDialPad(getStrParams("dialNum1"));
				sleepInt(1);
				dialBtn1.click();
			}else{
				pressDialPad(getStrParams("dialNum2"));
				sleepInt(1);
				dialBtn2.click();
			}
			
			for(int a=0;a<=100;a++){
				sleepInt(1);
				if(callShell("dumpsys telecom").contains("DIALING")){
					addStep("正在拨号");
				}else if(callShell("dumpsys telecom").contains("ALERTING")){
					addStep("成功拨出号码");
				}else if(callShell("dumpsys telecom").contains("ACTIVE")){
					addStep("成功拨通号码");
					sleepInt(1);
					LeUiObject endBtn = new LeUiObject(new UiSelector().className(
							"android.widget.ImageButton").resourceId(
							"com.android.dialer:id/end_call_button"));
					verify("未能成功接通",endBtn.exists());
					sleepInt(5);
					addStep("挂断电话");
					endBtn.click();
					break;
				}else if(a>100){
					addStep("等待100秒未拨通电话");
					break;
				}
			}
			sleepInt(4);
		}

		addStep("返回桌面");

		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	
	@CaseName("拒绝接听来自配对机的电话")
	public void testRejectMTCall () throws UiObjectNotFoundException {
		addStep("配对机向测试机打电话");
		for (int i = 1; i <= getIntParams("Loop"); i++){
			Log.i(MTCalltag, "StartCall");
			sleepInt(3);
			int a = 0;
			while(a<=60){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					addStep("拒绝配对机打来的电话");
					sleepInt(2);
					call = true;
//					phone.drag((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5),(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.9),30);
//					phone.drag((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5),(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.9),30);
//					phone.drag((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5),(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.9),30);
//					phone.drag((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5),(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.9),30);
////
//					phone.drag(500, 500, 500, 1800, 20);
//					phone.drag(500, 500, 500, 1800, 20);
//					phone.drag(500, 500, 500, 1800, 20);
					phone.click((int)(phone.getDisplayWidth()*0.238), (int)(phone.getDisplayHeight()*0.145));
					press_home(1);
					break;
				}
				a++;
			}
			sleepInt(3);
			verify("未能挂断电话",call&&(!callShell("dumpsys telecom").contains("RINGING")));
			addStep("成功挂断第" +i + "次电话");
			call = false;
			Log.i(MTCalltag, "StopCall");
			sleepInt(3);
			press_back(1);
			press_home(1);
		}
		press_back(1);
		press_home(1);	
		
	}
	@CaseName("接听来自配对手机的电话")
	public void testMTCall() throws UiObjectNotFoundException {
		addStep("配对机向测试机打电话");
		for (int i = 1; i <= getIntParams("Loop"); i++){
			Log.i(MTCalltag, "StartCall");
			sleepInt(3);
			int a = 0;
			while(a<=getIntParams("Loop")){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					addStep("接通配对机打来的电话");
					sleepInt(2);
//					phone.drag((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.9),(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.1),30);
//					phone.drag((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.9),(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.1),30);
//					phone.drag((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.9),(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.1),30);
//
////					phone.drag(500, 1800, 500, 500, 20);
//					phone.drag(500, 1800, 500, 500, 20);
					phone.click((int)(phone.getDisplayWidth()*0.592), (int)(phone.getDisplayHeight()*0.145));
					break;
				}
				a++;
			}
			sleepInt(3);
			LeUiObject endBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.dialer:id/end_call_button"));
			verify("未能成功接通", endBtn.exists());
			addStep("成功接听第" +i + "次电话");
			sleepInt(8);
			endBtn.click();
			sleepInt(2);
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			press_back(1);
			press_home(1);
		}
		press_back(1);
		press_home(1);	
	}
	
//	private void preDialPad(String number) throws UiObjectNotFoundException {
//		for (int i = 0; i < number.length(); i++) {
//			LeUiObject numBtn = new LeUiObject(new UiSelector()
//					.className("android.widget.TextView")
//					.resourceId("com.android.dialer:id/dialpad_key_number")
//					.text(String.valueOf(number.charAt(i))));
//			verify(numBtn.exists());
//			numBtn.click();
//			sleepInt(1);
//		}
//	}
    /*---mengfengxiao@letv.com---2015.11.17---*/
    @CaseName("切换全部通话/未接来电")
    public void testCallList() throws UiObjectNotFoundException {
        launchApp(AppName.PHONE);
        addStep("打开拨号程序");
        LeUiObject dialApp = new LeUiObject(new UiSelector().className(
                "android.widget.TextView").text("拨号"));
        dialApp.clickAndWaitForNewWindow();
        sleepInt(2);
        LeUiObject callFilter = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/calllog_filter_title_contain"));
        verify("通话过滤器不存在", callFilter.exists());
        UiObject all = new UiObject(new UiSelector().resourceId("com.android.dialer:id/slide_listview"))
                .getChild(new UiSelector().resourceId("com.android.dialer:id/tv_handle").index(0));
        UiObject miss = new UiObject(new UiSelector().resourceId("com.android.dialer:id/slide_listview"))
                .getChild(new UiSelector().resourceId("com.android.dialer:id/tv_handle").index(1));
        LeUiObject tileAll = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/filte_type_title")
                .className("android.widget.TextView").text("全部通话"));
        LeUiObject titleMiss = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/filte_type_title")
                .className("android.widget.TextView").text("未接来电"));
        for (int i=0; i<=getIntParams("Loop"); i++) {
            addStep("点击通话过滤器");
            callFilter.click();
            sleepInt(1);
            addStep("点击全部通话");
            verify("无全部通话", all.exists());
            all.click();
            sleepInt(1);
            verify("进入全部通话失败",tileAll.exists());
            addStep("点击通话过滤器");
            callFilter.click();
            sleepInt(1);
            addStep("点击未接来电");
            verify("无未接来电", miss.click());
            miss.click();
            sleepInt(1);
            verify("进入未接来电失败", titleMiss.exists());
        }
        addStep("退出电话");
        press_back(1);
        press_home(1);
    }
}
