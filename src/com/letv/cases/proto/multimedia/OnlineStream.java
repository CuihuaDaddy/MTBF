package com.letv.cases.proto.multimedia;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCaseMTBF;

public class OnlineStream extends LetvTestCaseMTBF {

	private String link = "http://10.57.9.203/httpvod/video/flv-h264.html";
	@CaseName("在线流媒体")
	public void testOnlineVideo() throws UiObjectNotFoundException {
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("打开浏览器并正常显示首页");
			launchAppByPackage(IntentConstants.proto_browser);
			sleepInt(2);
			addStep("清除浏览器缓存，历史记录和cookie数据");
			press_back(5);
			launchAppByPackage(IntentConstants.proto_browser);
			sleepInt(2);
			addStep("在地址栏中输入流媒体地址");
			UiObject urlEdit = new UiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"com.android.browser:id/url"));
			verify("Can't find url input EditText.", urlEdit.exists());
			urlEdit.setText(link);
			// UiObject goButton = new UiObject(new UiSelector().text("前往"));
			getUiDevice().pressEnter();
			sleepInt(5);
//			UiObject Play = new UiObject(new UiSelector().className(
//					"android.widget.Button").description("play"));
//			verify("Can't find play button.", Play.exists());
//			Play.click();
			phone.click(672, 642);
			sleepInt(5);
			addStep("播放节目5秒钟");
//			callShell("input tap 25 730");			
			sleepInt(2);			
//			callShell("input tap 25 730");
//			sleepInt(1);
//			callShell("input tap 25 730");
/*			UiObject play = new UiObject(new UiSelector().className(
					"android.widget.SeekBar").fromParent(
					new UiSelector().className("android.widget.Button")));
			verify("Can't find play button.", play.exists());
			*/
			addStep("关闭播放");
			press_back(2);
//			callShell("input tap 700 1850");
			sleepInt(1);
//			callShell("input swipe 300 600 300 100");
			sleepInt(2);
		}
		addStep("退出浏览器");
		press_back(5);
	}
}
