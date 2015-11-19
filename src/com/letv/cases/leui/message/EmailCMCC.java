package com.letv.cases.leui.message;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class EmailCMCC extends LetvTestCaseMTBF {

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		setUpAccount();
		clearAllBox();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

	// get send count
	private int getSendCount() throws UiObjectNotFoundException,
			RemoteException {
		LeUiObject menu = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"cn.cj.pe:id/actionbar_expansion_view"));
		LeUiObject outbox = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("cn.cj.pe:id/folder_name").text("已发送"));
		verify("Can't find menu button.", menu.exists());
		menu.click();
		sleepInt(2);
		verify(outbox.exists());
		outbox.click();
		sleepInt(2);
		int count = 0;
		LeUiObject listView = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId("android:id/list"));
		verify("Cant't find list view.", listView.exists());
		count = listView.getChildCount() - 1;
		press_back(1);
		return count;
	}

	private void deleteEmail() throws UiObjectNotFoundException {
		 // int dx = firstItem.getBounds().centerX();
		// int dy = firstItem.getBounds().centerY();
		// phone.swipe(dx, dy, dx, dy, 100);
		sleepInt(2);
//		LeUiObject clicked = new LeUiObject(new UiSelector().className(
//				"android.widget.ImageView").resourceId("cn.cj.pe:id/clicked"));
//		sleepInt(2);
//		clicked.click();
		phone.click((int)(phone.getDisplayWidth()*0.037), (int)(phone.getDisplayHeight()*0.178));
//		phone.click(54, 455);
		sleepInt(2);
		// UiObject all = new LeUiObject(new UiSelector()
		// .className("android.widget.TextView")
		// .resourceId("com.android.email:id/select_all").text("全选"));
		// verify("Can't find all button.", all.exists());
		// all.click();
		phone.click((int)(phone.getDisplayWidth()*0.1), (int)(phone.getDisplayHeight()*0.73));
//		phone.click(144, 1868);
		sleepInt(1);
		phone.click((int)(phone.getDisplayWidth()*0.48), (int)(phone.getDisplayHeight()*0.73));
//		phone.click(696, 1868);
		// UiObject delete = new LeUiObject(new UiSelector()
		// .resourceId("com.android.email:id/conversation_bottom_bar")
		// .className("android.widget.LinearLayout").index(1)).getChild(new
		// UiSelector().className("android.widget.RelativeLayout").index(3));
		// verify("Can't find delete button.", delete.exists());
		// delete.click();
		sleepInt(5);
		LeUiObject empty = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("暂无邮件"));
		verify("Can't delete all mail.", empty.exists());
	}

	private void clearAllBox() throws UiObjectNotFoundException {
		LeUiObject emailCMCC = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("139邮箱").description("139邮箱"));
		verify("没有138邮箱程序图标",emailCMCC.exists());
		launchApp(AppName.EMAIL);
		sleepInt(2);
		LeUiObject firstItem = new LeUiObject(new UiSelector()
		 .className("android.widget.FrameLayout")
		 .resourceId("cn.cj.pe:layout/message_list_item").index(1));
//		LeUiObject empty = new LeUiObject(new UiSelector().className(
//				"android.widget.TextView").text("暂无邮件"));
		LeUiObject menu = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"cn.cj.pe:id/actionbar_expansion_view"));
		verify("Can't find menu button.", menu.exists());
		menu.click();
		sleepInt(2);
		LeUiObject inbox = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("cn.cj.pe:id/folder_name").text("收件箱"));
		verify("Can't find inbox button.", inbox.exists());
		inbox.click();
		sleepInt(2);
//		if (!(empty.exists())) {
//			sleepInt(2);
//			System.out.print("2323232");
//			deleteEmail();
//		}
		if (!firstItem.exists()) {
			sleepInt(2);
			System.out.print("11111");
			
		}else{
			sleepInt(2);
			deleteEmail();
		}
		LeUiObject outbox = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("cn.cj.pe:id/folder_name").text("已发送"));
		verify(menu.exists());
		menu.click();
		sleepInt(2);
		verify(outbox.exists());
		outbox.click();
		sleepInt(2);
		if (!firstItem.exists()) {
			sleepInt(2);
			System.out.print("22222");
			
		}else{
			sleepInt(2);
			deleteEmail();
		}
		LeUiObject draft = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("cn.cj.pe:id/folder_name").text("草稿箱"));
		verify(menu.exists());
		menu.click();
		sleepInt(2);
		verify("Can't find draft button.", draft.exists());
		draft.click();
		sleepInt(2);
		if (!firstItem.exists()) {
			sleepInt(2);
			System.out.print("33333");
			
		}else{
			sleepInt(2);
			deleteEmail();
		}
		phone.pressBack();
		phone.pressBack();
		sleepInt(2);
		phone.pressHome();
		sleepInt(2);
	}

	private void setUpAccount() throws UiObjectNotFoundException {
		LeUiObject emailCMCC = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("139邮箱").description("139邮箱"));
		verify("没有138邮箱程序图标",emailCMCC.exists());
		launchApp(AppName.EMAIL);
		sleepInt(5);
		LeUiObject accountSet = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("使用本机号码一键登录"));
		if (accountSet.exists()) {
			LeUiObject accountName = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"cn.cj.pe:id/login_name"));
			verify(accountName.exists());
			accountName.click();
			accountName.setText(getStrParams("deviceNum"));
			sleepInt(2);
			phone.pressEnter();
			LeUiObject accountpwd = new LeUiObject(new UiSelector().className(
					"android.widget.EditText").resourceId(
					"cn.cj.pe:id/login_password"));
			verify(accountpwd.exists());
			accountpwd.setText("letv123");
			sleepInt(2);
			LeUiObject login = new LeUiObject(new UiSelector().className(
					"android.widget.Button").resourceId("cn.cj.pe:id/login"));
			verify(login.exists());
			login.click();
			sleepInt(2);
			LeUiObject loginNote = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("cn.cj.pe:id/dialog_message")
					.text("登录中，请耐心等待..."));
			sleepInt(2);
			verify("没有正在登陆", loginNote.exists());
			sleepInt(5);
			for (int i = 0; i < 100; i++) {
				LeUiObject setting = new LeUiObject(new UiSelector()
						.className("android.widget.TextView")
						.resourceId("cn.cj.pe").text(" (发件人与签名将显示在所发邮件中)"));
				LeUiObject ok = new LeUiObject(new UiSelector()
						.className("android.widget.Button")
						.resourceId("cn.cj.pe:id/login").text("确定"));
				if (setting.exists()) {
					verify(ok.exists());
					ok.click();
				} else {
					sleepInt(3);
				}
			}
		}
		LeUiObject inbox = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("收件箱"));
		verify("未添加邮箱账号", inbox.exists());
		phone.pressBack();
		phone.pressBack();
		sleepInt(2);
		phone.pressHome();
		sleepInt(2);
	}

	@CaseName("发送邮件")
	public void testsendEmail() throws UiObjectNotFoundException,
			RemoteException {

		LeUiObject emailCMCC = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("139邮箱").description("139邮箱"));
		verify("没有138邮箱程序图标",emailCMCC.exists());
		launchApp(AppName.EMAIL);
		addStep("进入邮箱");
		sleepInt(5);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			LeUiObject newMail = new LeUiObject(new UiSelector().resourceId(
					"cn.cj.pe:id/actionbar_right_view").className("android.widget.LinearLayout"));
			verify("Can't find new mail button.", newMail.exists());
			newMail.click();
			addStep("新建邮件");
			sleepInt(2);
			LeUiObject recipient = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/to_layout"));
			verify("没有找到收件人", recipient.exists());
			recipient.click();
			recipient.setText(getStrParams("deviceNum") + "@139.com");
			sleepInt(2);
			phone.pressEnter();
			addStep("填收件人");
			LeUiObject subject = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/subject"));
			verify("没有找到主题", subject.exists());
			subject.click();
			subject.setText("TestEmail");
			sleepInt(2);
			phone.pressEnter();
			addStep("填主题");
			LeUiObject body = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/message_content"));
			verify("没有找到正文", body.exists());
			body.click();
			body.setText("TestEmail");
			sleepInt(2);
			phone.pressEnter();
			addStep("填正文");
			sleepInt(2);
			LeUiObject send = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/inbox_edit"));
			verify("没有找到发送", send.exists());
			send.click();
			addStep("发送邮件");
			sleepInt(8);
			int count = getSendCount();
			if (count < i + 1) {
				fail("Can't sent mail, loop===" + i);
			}
			addStep("查看邮件是否发送成功");
		}
		addStep("删除所有邮件");
		clearAllBox();
		addStep("退出邮箱");
		press_back(3);
		press_home(1);
	}

	@CaseName("发送带附件的邮件邮件")
	public void testsendEmailAttach() throws UiObjectNotFoundException,
			RemoteException {

		LeUiObject emailCMCC = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("139邮箱").description("139邮箱"));
		verify("没有138邮箱程序图标",emailCMCC.exists());
		launchApp(AppName.EMAIL);
		addStep("进入邮箱");
		sleepInt(2);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			LeUiObject newMail = new LeUiObject(new UiSelector().resourceId(
					"cn.cj.pe:id/actionbar_right_view").className("android.widget.LinearLayout"));
			verify("Can't find new mail button.", newMail.exists());
			newMail.click();
			addStep("新建邮件");
			sleepInt(2);
			LeUiObject recipient = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/to_layout"));
			verify("没有找到收件人", recipient.exists());
			recipient.click();
			recipient.setText(getStrParams("deviceNum") + "@139.com");
			sleepInt(2);
			phone.pressEnter();
			addStep("填收件人");
			LeUiObject subject = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/subject"));
			verify("没有找到主题", subject.exists());
			subject.click();
			subject.setText("TestEmail");
			sleepInt(2);
			phone.pressEnter();
			addStep("填主题");
			LeUiObject body = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/message_content"));
			verify("没有找到正文", body.exists());
			body.click();
			body.setText("TestEmail");
			sleepInt(2);
			phone.pressEnter();
			addStep("填正文");
			sleepInt(2);
			LeUiObject attach = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/add_attachment"));
			verify("没有找到附件按钮", attach.exists());
			attach.click();
			sleepInt(2);
			LeUiObject photos = new LeUiObject(new UiSelector().resourceId(
					"cn.cj.pe:id/attach_display_photo"));
			verify("没有找到图片", photos.exists());
			photos.click();
			sleepInt(2);
			LeUiObject Resource = new LeUiObject(new UiSelector().resourceId(
					"com.android.gallery3d:id/album_name").text("Resource"));
			verify("没有找到resource 相册", Resource.exists());
			Resource.click();
			sleepInt(2);
			phone.click((int)(phone.getDisplayWidth()*0.035), (int)(phone.getDisplayHeight()*0.098));
//			phone.click(50, 250);
			addStep("填附件");
			sleepInt(2);
			LeUiObject attachName = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/attachment_name"));
			verify("附件没有添加成功", attachName.exists());
			addStep("验证附件是否添加成功");
			LeUiObject send = new LeUiObject(
					new UiSelector().resourceId("cn.cj.pe:id/inbox_edit"));
			verify("没有找到发送", send.exists());
			send.click();
			addStep("发送邮件");
			sleepInt(10);
			int count = getSendCount();
			if (count < i + 1) {
				fail("Can't sent mail, loop===" + i);
			}
			addStep("查看邮件是否发送成功");
		}
		addStep("删除所有邮件");
		clearAllBox();
		addStep("退出邮箱");
		press_back(3);
		press_home(1);
	}

	@CaseName("查看邮件")
	public void testopenEmail() throws UiObjectNotFoundException,RemoteException {

		LeUiObject emailCMCC = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("139邮箱").description("139邮箱"));
		verify("没有138邮箱程序图标",emailCMCC.exists());
		launchApp(AppName.EMAIL);
		addStep("进入邮箱");
		sleepInt(2);
		LeUiObject newMail = new LeUiObject(new UiSelector().resourceId(
				"cn.cj.pe:id/actionbar_right_view").className("android.widget.LinearLayout"));
		verify("Can't find new mail button.", newMail.exists());
		newMail.click();
		addStep("新建邮件");
		sleepInt(2);
		LeUiObject recipient = new LeUiObject(
				new UiSelector().resourceId("cn.cj.pe:id/to_layout"));
		verify("没有找到收件人", recipient.exists());
		recipient.click();
		recipient.setText(getStrParams("deviceNum") + "@139.com");
		sleepInt(2);
		phone.pressEnter();
		addStep("填收件人");
		LeUiObject subject = new LeUiObject(
				new UiSelector().resourceId("cn.cj.pe:id/subject"));
		verify("没有找到主题", subject.exists());
		subject.click();
		subject.setText("TestEmail");
		sleepInt(2);
		phone.pressEnter();
		addStep("填主题");
		LeUiObject body = new LeUiObject(
				new UiSelector().resourceId("cn.cj.pe:id/message_content"));
		verify("没有找到正文", body.exists());
		body.click();
		body.setText("TestEmail");
		sleepInt(2);
		phone.pressEnter();
		addStep("填正文");
		sleepInt(2);
		LeUiObject attach = new LeUiObject(
				new UiSelector().resourceId("cn.cj.pe:id/add_attachment"));
		verify("没有找到附件按钮", attach.exists());
		attach.click();
		sleepInt(2);
		LeUiObject photos = new LeUiObject(new UiSelector().resourceId(
				"cn.cj.pe:id/attach_display_photo"));
		verify("没有找到图片", photos.exists());
		photos.click();
		sleepInt(2);
		LeUiObject Resource = new LeUiObject(new UiSelector().resourceId(
				"com.android.gallery3d:id/album_name").text("Resource"));
		verify("没有找到resource 相册", Resource.exists());
		Resource.click();
		sleepInt(2);
		phone.click((int)(phone.getDisplayWidth()*0.035), (int)(phone.getDisplayHeight()*0.098));
		addStep("填附件");
		sleepInt(2);
		LeUiObject attachName = new LeUiObject(
				new UiSelector().resourceId("cn.cj.pe:id/attachment_name"));
		verify("附件没有添加成功", attachName.exists());
		addStep("验证附件是否添加成功");
		press_back(1);
		sleepInt(1);
		LeUiObject saveDraft = new LeUiObject(new UiSelector().resourceId(
				"cn.cj.pe:id/mid").text("保存为草稿"));
		verify(saveDraft.exists());
		saveDraft.click();
		addStep("保存至草稿箱");
		for (int i = 0; i < getIntParams("Loop"); i++) {
			LeUiObject menu = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"cn.cj.pe:id/actionbar_expansion_view"));
			verify("Can't find menu button.", menu.exists());
			menu.click();
			LeUiObject draft = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("cn.cj.pe:id/folder_name").text("草稿箱"));
			verify("Can't find drafts button.", draft.exists());
			draft.click();
			sleepInt(2);
			LeUiObject firstItem = new LeUiObject(new UiSelector()
					.className("android.widget.FrameLayout")
					.resourceId("cn.cj.pe:layout/message_list_item").index(1));
			verify(firstItem.exists());
			firstItem.click();
			addStep("查看草稿箱中邮件");
			sleepInt(2);
			press_back(1);
			sleepInt(1);
//			saveDraft.click();
		}
		addStep("删除所有邮件");
		clearAllBox();
		addStep("退出邮箱");
		press_back(3);
		press_home(1);
	}
}
