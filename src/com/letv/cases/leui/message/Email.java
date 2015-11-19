package com.letv.cases.leui.message;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;
import com.letv.uf.Utf7ImeHelper;

public class Email extends LetvTestCaseMTBF {
	private String X908 = "X908";
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		
//		closeWifi();
//		connectData();
		super.setUp();
		checkAccount();
//		if(callShell("getprop ro.product.model").contains(X908)){
//			closeWifi();
//		}

	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
//		openWifi();
//		if(callShell("getprop ro.product.model").contains(X908)){
//			openWifi();
//		}
		super.tearDown();
	}
	
	UiObject inbox = new LeUiObject(new UiSelector().textContains("收件箱"));
	UiObject menu = new LeUiObject(new UiSelector().className("android.widget.ImageButton").description("打开抽屉式导航栏"));
	UiObject outbox = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("com.android.email:id/name").text("已发送邮件"));
	UiObject Replay = new LeUiObject(new UiSelector().text("转发"));
	UiObject recipient = new LeUiObject(new UiSelector().resourceId("com.android.email:id/to").description("收件人"));
	UiObject body = new LeUiObject(new UiSelector().resourceId("com.android.email:id/body_wrapper"));
	UiObject send = new LeUiObject(new UiSelector().resourceId("com.android.email:id/send"));
	UiObject FWD010 = new LeUiObject(new UiSelector().resourceId("com.android.email:id/subject").text("Fwd: 010"));//mengfengxiao@letv.com
	UiObject FWD010attach = new LeUiObject(new UiSelector().resourceId("com.android.email:id/subject").text("Fwd: 010-attachment"));////mengfengxiao@letv.com
	UiObject empty = new LeUiObject(new UiSelector().resourceId("android:id/message").textContains("无邮件"));//mengfengxiao@letv.com
	LeUiObject accountSetting = new LeUiObject(new UiSelector().text("帐户设置"));
	LeUiObject account = new LeUiObject(new UiSelector().text("电子邮件地址").resourceId("com.android.email:id/account_email"));
	LeUiObject pwd = new LeUiObject(new UiSelector().resourceId("com.android.email:id/account_password"));
	LeUiObject emailType = new LeUiObject(new UiSelector().resourceId("com.android.email:id/account_type"));
	LeUiObject pop3 = new LeUiObject(new UiSelector().text("POP3"));
	LeUiObject next = new LeUiObject(new UiSelector().text("下一步"));
	LeUiObject incomming = new LeUiObject(new UiSelector().text("接收服务器设置"));
	LeUiObject outgoing = new LeUiObject(new UiSelector().text("SMTP服务器"));
	LeUiObject sycnFrequency = new LeUiObject(new UiSelector().text("同步频率"));
	LeUiObject accountNotify = new LeUiObject(new UiSelector().resourceId("com.android.email:id/account_notify"));
	LeUiObject accountSycn = new LeUiObject(new UiSelector().resourceId("com.android.email:id/account_sync_email"));
	LeUiObject done = new LeUiObject(new UiSelector().textContains("设置完毕"));
	LeUiObject replayNote = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm"));

	
	public void checkAccount() throws UiObjectNotFoundException {
		launchApp(AppName.EMAIL);
		sleepInt(5);
		if(accountSetting.exists()){
			addAccount();
		}
		press_back(2);
		press_home(1);
	}
	public void addAccount() throws UiObjectNotFoundException {
		verify("没有账号地址栏填写处",account.exists());
		verify("没有密码填写处",pwd.exists());
		verify("没有账户类型",emailType.exists());
		account.click();
		account.setText(Utf7ImeHelper.e(getStrParams("deviceNum1")+"@139.com"));
		sleepInt(2);
		pwd.click();
		pwd.setText(Utf7ImeHelper.e("letv123"));
		sleepInt(2);
		emailType.click();
		sleepInt(2);
		verify("没有pop3账户类型",pop3.exists());
		pop3.click();
		sleepInt(2);
		verify("没有下一步",next.exists());
		next.click();
		sleepInt(5);
		verify("没有进入接收服务器设置页面",incomming.exists());
		verify("没有下一步",next.exists());
		next.click();
		sleepInt(20);
		verify("没有进入发送服务器设置页面",outgoing.exists());
		verify("没有下一步",next.exists());
		next.click();
		sleepInt(20);
		verify("没有进入同步设置界面",sycnFrequency.exists());
		if(accountNotify.exists() && accountNotify.isChecked()){
			accountNotify.click();
			sleepInt(2);
		}
		if(accountSycn.exists() && accountSycn.isChecked()){
			accountSycn.click();
			sleepInt(2);
		}
		verify("没有下一步",next.exists());
		next.click();
		sleepInt(5);
		verify("没有进入邮箱设置最后一步",done.exists());
		verify("没有下一步",next.exists());
		next.click();
		sleepInt(5);		
		verify("邮箱设置失败",inbox.exists());
		sleepInt(2);
		sleepInt(15);
	}
	public void connectData() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(2);
		LeUiObject dataUsage = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("移动网络").resourceId("android:id/title"));
		verify("Can't find data usage button.", dataUsage.exists());
		dataUsage.click();
		sleepInt(2);
		sleepInt(2);
		LeUiObject switchWidget =new LeUiObject(new UiSelector().className(
				"android.widget.LinearLayout").index(0).childSelector(
						new UiSelector().className("android.widget.LinearLayout").index(1).childSelector(
								new UiSelector().className("com.letv.leui.widget.LeSwitch"))));
		verify("Can't find switchWidget button.",switchWidget.exists());
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(4);
			verify("Can't enabled data.",switchWidget.isChecked());
		}
		sleepInt(2);
		press_back(4);
		press_home(1);
	}
	public void closeWifi() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(4);
		LeUiObject wlan =new LeUiObject(new UiSelector().className("android.widget.TextView").text("WLAN"));
		verify("Can't find Wi-fi button.", wlan.exists());
		wlan.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		if (switchWidget.isChecked()) {
			switchWidget.click();
			sleepInt(5);
			verify("Can't close wifi.", !(switchWidget.isChecked()));
		} 
		
		sleepInt(4);
		press_back(4);
		press_home(1);
	}
	public void openWifi() throws UiObjectNotFoundException {
		launchApp(AppName.SETTING);
		sleepInt(4);
		LeUiObject wlan =new LeUiObject(new UiSelector().className("android.widget.TextView").text("WLAN"));
		verify("Can't find Wi-Fi button.", wlan.exists());
		wlan.click();
		sleepInt(2);
		LeUiObject switchWidget = new LeUiObject(new UiSelector().className(
				"com.letv.leui.widget.LeSwitch").resourceId(
				"com.android.settings:id/switch_widget"));
		if (switchWidget.isChecked()) {
			return;
		} else {
			switchWidget.click();
			sleepInt(10);
			verify("Can't open wifi.",
					switchWidget.isChecked());
		}
	}
	public void WifiConnection() throws UiObjectNotFoundException {
		addStep("Open wifi");
		openWifi();
		addStep("Connect wifi");
		sleepInt(10);
		LeUiObject connectInfo2 = new LeUiObject(new UiSelector().resourceId(
				"android:id/summary").text("已连接"));
		verify("Can't connect to wifi.",connectInfo2.exists() );
		press_back(5);
		press_home(1);
		sleepInt(1);
	}
	
	private void deleteEmail() throws UiObjectNotFoundException {
        /*mengfengxiao@letv.com*/
		/*LeUiObject firstItem = new LeUiObject(new UiSelector()
		.className("android.widget.RelativeLayout")
		.resourceId("com.android.email:id/back").index(0));
		int dx = firstItem.getBounds().centerX();
		int dy = firstItem.getBounds().centerY();*/
        LeUiObject FWD = new LeUiObject(new UiSelector().resourceId("com.android.email:id/sender_name").textContains("我"));
        int dx = FWD.getBounds().centerX();
        int dy = FWD.getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		sleepInt(2);
		LeUiObject delete = new LeUiObject(new UiSelector().text("删除"));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(3);
	}

    /*mengfengxiao@letv.com*/
    private void stopEmail() throws UiObjectNotFoundException, RemoteException {
        addStep("进入设置");
        launchApp(AppName.SETTING);
        sleepInt(2);
        addStep("进入应用管理");
        UiScrollable settingPanel = new UiScrollable(new UiSelector()
                .className("android.widget.ListView")
                .packageName("com.android.settings")
                .resourceId("android:id/list"));
        LeUiObject appManager = new LeUiObject(new UiSelector().className(
                "android.widget.TextView").text("应用管理").resourceId("android:id/title"));
        verify("Can't into settings",settingPanel.exists());
        settingPanel.setAsVerticalList();
        for (int i = 0; i < 20; i++) {
            if (!appManager.exists()) {
                settingPanel.scrollForward();
            } else {
                break;
            }
        }
        appManager.clickAndWaitForNewWindow();
        sleepInt(2);
        addStep("强制停止电子邮件");
        UiObject tab = new UiObject(new UiSelector().resourceId("com.android.settings:id/tabwidget"));
        UiObject all = tab.getChild(new UiSelector().className("android.widget.TextView").text("全部"));
        verify(all.exists());
        all.click();
        sleepInt(1);
        UiScrollable apppPanel = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/pager").className("android.support.v4.view.ViewPager"));
        verify(apppPanel.exists());
        UiObject email = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("com.android.settings:id/app_name").text("电子邮件"));
        settingPanel.setAsVerticalList();
        for (int i = 0; i < 20; i++) {
            if (!email.exists()) {
                apppPanel.scrollForward();
            } else {
                break;
            }
        }
        email.clickAndWaitForNewWindow();
        sleepInt(1);

        LeUiObject detail = new LeUiObject(new UiSelector().resourceId("android:id/action_bar")
                .childSelector(new UiSelector().className("android.widget.TextView").text("应用详情")));
        verify("can't into app detail", detail.exists());
        LeUiObject stop = new LeUiObject(new UiSelector().resourceId("com.android.settings:id/left_button"));
        if(stop.exists()&&stop.isEnabled()) {
            stop.click();
            sleepInt(2);
        }
        UiObject confirm = new UiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm"));
        verify(confirm.exists());
        confirm.click();
        sleepInt(1);
        press_back(5);
        press_home(1);
        sleepInt(2);
    }

	@CaseName("发送邮件")
	public void testsendEmail() throws UiObjectNotFoundException, RemoteException {
        stopEmail();
        addStep("进入邮箱");
        launchApp(AppName.EMAIL);
        sleepInt(4);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("进入收件箱");
			if(!inbox.exists()){
				verify("没有menu",menu.exists());
				menu.click();
				sleepInt(2);
				verify("没有收件箱",inbox.exists());
				inbox.click();
				sleepInt(1);
			}
			addStep("转发010邮件");
			LeUiObject email = new LeUiObject(new UiSelector().text("010").resourceId("com.android.email:id/subject"));
			verify("没有010邮件",email.exists());
			email.click();
			sleepInt(2);
			verify("没有转发",Replay.exists());
			Replay.click();
			sleepInt(2);
			verify("没有收件人",recipient.exists());
			recipient.click();
			recipient.setText(Utf7ImeHelper.e("letvmtbf1@163.com"));
			sleepInt(2);
			verify("没有body",body.exists());
			body.click();
			body.setText(Utf7ImeHelper.e("123"));
			sleepInt(2);
			verify("没有send",send.exists());
			send.click();
			sleepInt(15);//mengfengxiao@letv.com 45->15
			press_back(1);
			addStep("查看邮件发送成功否");
            /*mengfengxiao@letv.com*/
            for(int j=0; j<3; j++) {
                //避免有时进入已发送邮件,无邮件的情况(已发送成功)
                verify("没有menu",menu.exists());
                menu.click();
                sleepInt(1);
                verify("没有outbox",outbox.exists());
                outbox.click();
                sleepInt(1);
                if(FWD010.exists())
                    break;
            }
            /* mengfengxiao@letv.com*/
			verify("没有发送成功",FWD010.exists());
			addStep("删除刚刚发送的邮箱");
            /*mengfengxiao@letv.com*/
            FWD010.click();
            sleepInt(1);
			deleteEmail();
            press_back(1);
            verify("没有menu",menu.exists());
            menu.click();
            sleep(1);
            verify("没有收件箱",inbox.exists());
            inbox.click();
            sleep(1);
            verify("没有menu",menu.exists());
            menu.click();
            sleep(1);
            verify("没有outbox",outbox.exists());//直接进入outbox,无"暂无邮件"icon
            outbox.click();
            sleepInt(2);
            /* mengfengxiao@letv.com*/
			verify("删除失败",empty.exists());
			addStep("退出邮箱");
			press_back(5);
			press_home(1);
			sleepInt(4);
		}
	
	}
	
	@CaseName("发送带附件的邮件邮件")
	public void testsendEmailAttach() throws UiObjectNotFoundException, RemoteException {
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("进入邮箱");
			launchApp(AppName.EMAIL);
			sleepInt(4);
			addStep("进入收件箱");
			if(!inbox.exists()){
				verify("没有menu",menu.exists());
				menu.click();
				sleepInt(2);
				verify("没有收件箱",inbox.exists());
				inbox.click();
				sleepInt(1);
			}
			addStep("转发010-attachment邮件");
			LeUiObject emailattachment = new LeUiObject(new UiSelector().text("010-attachment").resourceId("com.android.email:id/subject"));
			verify("没有010-attachment邮件",emailattachment.exists());
			emailattachment.click();
			sleepInt(2);
			verify("没有转发",Replay.exists());
			Replay.click();
			sleepInt(2);
			if(replayNote.exists()){
				replayNote.click();
				sleepInt(2);
			}
			verify("没有收件人",recipient.exists());
			recipient.setText(Utf7ImeHelper.e("letvmtbf1@163.com"));
			sleepInt(2);
			verify("没有body",body.exists());
			body.setText(Utf7ImeHelper.e("123"));
			sleepInt(2);
			verify("没有send",send.exists());
			send.click();
			sleepInt(15);//mengfengxiao@letv.com 45->15
			press_back(1);
			addStep("查看邮件发送成功否");
            /*mengfengxiao@letv.com*/
            for(int j=0; j<3; j++) {
                //避免有时进入已发送邮件,无邮件的情况(已发送成功)
                verify("没有menu",menu.exists());
                menu.click();
                sleepInt(1);
                verify("没有outbox",outbox.exists());
                outbox.click();
                sleepInt(1);
                if(FWD010attach.exists())
                    break;
            }
            /* mengfengxiao@letv.com*/
			verify("没有发送成功",FWD010attach.exists());
			addStep("删除刚刚发送的邮箱");
             /*mengfengxiao@letv.com*/
            FWD010attach.click();
            sleepInt(1);
			deleteEmail();
            /* mengfengxiao@letv.com*/
            press_back(1);
            verify("没有menu",menu.exists());
            menu.click();
            sleep(1);
            verify("没有收件箱",inbox.exists());
            inbox.click();
            sleep(1);
            verify("没有menu",menu.exists());
            menu.click();
            sleep(1);
            verify("没有outbox",outbox.exists());//直接进入outbox,无"暂无邮件"icon
            outbox.click();
            sleepInt(2);
            /* mengfengxiao@letv.com*/
			verify("删除失败",empty.exists());
			addStep("退出邮箱");
			press_back(3);
			press_home(1);
			sleepInt(4);
		}
		
	}
	
	@CaseName("查看邮件")
	public void testopenEmail() throws UiObjectNotFoundException, RemoteException {
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("进入邮箱");
			launchApp(AppName.EMAIL);
			sleepInt(4);
			addStep("进入收件箱");
			if(!inbox.exists()){
				verify("没有menu",menu.exists());
				menu.click();
				sleepInt(2);
				verify("没有收件箱",inbox.exists());
				inbox.click();
				sleepInt(1);
			}
			addStep("查看010-attachment邮件");
			LeUiObject emailattachment = new LeUiObject(new UiSelector().text("010-attachment").resourceId("com.android.email:id/subject"));
			verify("没有010-attachment邮件",emailattachment.exists());
			emailattachment.click();
			sleepInt(2);
			LeUiObject emailtitle = new LeUiObject(new UiSelector().text("010-attachment").resourceId("com.android.email:id/subject"));
			LeUiObject emailattach = new LeUiObject(new UiSelector().text("附件：共有1个附件").resourceId("com.android.email:id/attachments_header"));
			verify("打开失败",emailtitle.exists()&&emailattach.exists());
			addStep("返回邮件主界面");
			press_back(1);
			addStep("退出邮箱");
			press_back(2);
			press_home(1);
			sleepInt(4);
		}
		
	}
}
