package com.letv.cases.leui.others;

import java.io.File;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;
import com.letv.uf.Utf7ImeHelper;

public class HFCX extends LetvTestCaseMTBF {
	private String[] simcardNum = {"13810904087","13811835847","13810768463","13811528657","13811705065","13811707983","13811976972","13810609172","13810918013","13811017483","13811527120","13811632950","13811537572","13811276505","13810215417","13810632672","13811057710","13811676830","13811496554","13810842050","13810290691","13811305597","13810519357","13811971013","13811819261","13811203861","13810842993","13810943596","13810779451","13810556487","13810827477","13810981467","13811486156","13811976883","13811291715","13810696335","13811725390","13811574295","13811623393","13811650560"};
	@CaseName("查询移动卡话费")
	public void testCMCC() throws UiObjectNotFoundException {
		LeUiObject CMCC = new LeUiObject(new UiSelector().text("手机营业厅").className("android.widget.TextView"));
		verify("NoCMCC.", CMCC.exists());
		CMCC.click();
		sleepInt(5);
		LeUiObject ignore = new LeUiObject(new UiSelector().text("忽略"));
		if(ignore.exists()){
			ignore.click();
			sleepInt(3);
		}
		LeUiObject menu = new LeUiObject(new UiSelector().resourceId("com.greenpoint.android.mc10086.activity:id/title_leftbtn_img"));
		LeUiObject login = new LeUiObject(new UiSelector().text("点击登录"));
		LeUiObject num = new LeUiObject(new UiSelector().text("请输入11位移动手机号码"));
		LeUiObject pwd = new LeUiObject(new UiSelector().resourceId("com.greenpoint.android.mc10086.activity:id/user_password_edt"));
		LeUiObject login2 = new LeUiObject(new UiSelector().text("登 录"));
		LeUiObject list = new LeUiObject(new UiSelector().text("我的账单"));
		LeUiObject logout = new LeUiObject(new UiSelector().text("注销"));
		LeUiObject ok = new LeUiObject(new UiSelector().text("确认"));
		LeUiObject close = new LeUiObject(new UiSelector().resourceId("com.greenpoint.android.mc10086.activity:id/login_close"));
		for (int i = 0; i < simcardNum.length; i++) {
			verify(menu.exists());
			menu.click();
			sleepInt(2);
			verify(login.exists());
			login.click();
			sleepInt(2);
			verify(num.exists());
			verify(pwd.exists());
			num.setText(Utf7ImeHelper.e(simcardNum[i]));
			sleepInt(2);
			pwd.setText(Utf7ImeHelper.e("102938"));
			sleepInt(2);
			login2.click();
			sleepInt(3);
			list.click();
			sleepInt(6);
			String filename =  simcardNum[i]+ ".png";
			String command = "screencap -p /sdcard/"+filename;
			callShell(command);
			sleepInt(4);
			press_back(1);
			menu.click();
			sleepInt(2);
			logout.click();
			sleepInt(2);
			ok.click();
			sleepInt(2);
			close.click();
			press_back(1);
			sleepInt(2);
		}
	}
}
