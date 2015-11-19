package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class LaunchApps extends LetvTestCase {

	private String[] apps = { IntentConstants.proto_telephone,
			IntentConstants.proto_browser, IntentConstants.downloadlist,
			IntentConstants.proto_filemanager, IntentConstants.calendar,
			IntentConstants.proto_camera, IntentConstants.proto_gallery,
			IntentConstants.proto_clock, IntentConstants.setting,
			IntentConstants.message, IntentConstants.music,
			IntentConstants.calculator };

	@CaseName("浏览本地应用")
	public void testOpenApps() throws UiObjectNotFoundException {
		addStep("进入应用导航页面");
		phone.pressHome();
		for (int i = 0; i < apps.length; i++) {
			addStep("逐一点击本地应用");
			launchAppByPackage(apps[i]);
			sleepInt(5);
			addStep("逐一关闭本地应用");
			press_back(5);
		}
		addStep("返回主页面");
		phone.pressHome();
	}

}
