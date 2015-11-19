package com.letv.cases.leui.message;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class EmailCN extends LetvTestCaseMTBF {
	
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
		private int getSendCount() throws UiObjectNotFoundException, RemoteException {
			LeUiObject menu = new LeUiObject(new UiSelector()
				.className("android.widget.ImageButton").description("打开抽屉式导航栏"));
			LeUiObject outbox = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.email:id/name").text("已发送邮件"));
			verify("Can't find menu button.", menu.exists());
			menu.click();
			sleepInt(2);
			verify(outbox.exists());
			outbox.click();
			sleepInt(2);
			int count = 0;
			LeUiObject listView = new LeUiObject(new UiSelector().className(
					"android.widget.ListView").resourceId(
					"android:id/list"));
			verify("Cant't find list view.", listView.exists());
			count = listView.getChildCount();
			press_back(1);
			return count;
		}



	private void deleteEmail() throws UiObjectNotFoundException {
		LeUiObject firstItem = new LeUiObject(new UiSelector()
		.className("android.widget.RelativeLayout")
		.resourceId("com.android.email:id/back").index(0));
		int dx = firstItem.getBounds().centerX();
		int dy = firstItem.getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		sleepInt(2);
		LeUiObject all = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.email:id/select_all").text("全选"));
		LeUiObject allnot = new LeUiObject(new UiSelector()
		.className("android.widget.TextView")
		.resourceId("com.android.email:id/select_none").text("全不选"));
		if(all.exists()){
			all.click();
			sleepInt(3);
		}
		verify("Can't find allnot button.", allnot.exists());		
		sleepInt(1);
		LeUiObject delete = new LeUiObject(new UiSelector().text("删除"));
				
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(2);
		LeUiObject empty = new LeUiObject(new UiSelector().resourceId(
				"com.android.email:id/empty_view"));
		verify("Can't delete all mail.", empty.exists());
	}

	private void clearAllBox() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.email);
		sleepInt(2);
		LeUiObject empty = new LeUiObject(new UiSelector().resourceId(
				"com.android.email:id/empty_view"));
		LeUiObject menu = new LeUiObject(new UiSelector()
				.className("android.widget.ImageButton").description("打开抽屉式导航栏"));
		verify("Can't find menu button.", menu.exists());
		menu.click();
		sleepInt(2);
		LeUiObject load = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/conversation_bottom_bar")
			.className("android.widget.LinearLayout").index(1).childSelector(new UiSelector().className("android.widget.RelativeLayout").index(0)));
		LeUiObject inbox = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.email:id/name").text("收件箱"));
		verify("Can't find inbox button.", inbox.exists());
		inbox.click();
		sleepInt(2);
		verify("Can't find load mail button.", load.exists());
//		load.click();
		sleepInt(15);
		
		if(!empty.exists()){
			sleepInt(2);
			deleteEmail();
		}
		LeUiObject outbox = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("com.android.email:id/name").text("已发送邮件"));
		verify(menu.exists());
		menu.click();
		sleepInt(2);
		verify(outbox.exists());
		outbox.click();
		sleepInt(2);
		if(!empty.exists()){
			sleepInt(2);
			deleteEmail();
		}
		LeUiObject draft = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("com.android.email:id/name").text("草稿"));
		verify(menu.exists());
		menu.click();
		sleepInt(2);
		verify("Can't find draft button.", draft.exists());
		draft.click();
		sleepInt(2);
		if(!empty.exists()){
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
		launchAppByPackage(IntentConstants.email);
		LeUiObject accountSet = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("帐户设置"));
		if(accountSet.exists()){
			LeUiObject accountName = new LeUiObject(new UiSelector()
			.className("android.widget.EditText")
			.resourceId("com.android.email:id/account_email"));
			verify(accountName.exists());
			accountName.click();
			accountName.setText(getStrParams("email"));
			sleepInt(2);
			phone.pressEnter();
			LeUiObject accountpwd = new LeUiObject(new UiSelector()
			.className("android.widget.EditText")
			.resourceId("com.android.email:id/account_password"));
			verify(accountpwd.exists());
			accountpwd.setText(getStrParams("emailpwd"));
			sleepInt(2);
			LeUiObject type = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("android:id/text1"));
			type.click();
			sleepInt(2);
			LeUiObject pop3 = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("android:id/text1").text("POP3"));
			verify(pop3.exists());
			pop3.click();
			LeUiObject next = new LeUiObject(new UiSelector()
			.className("android.widget.TextView")
			.resourceId("com.android.email:id/next").text("下一步"));
			sleepInt(2);
			verify(next.exists());
			next.click();
			sleepInt(2);
			verify(next.exists());
			next.click();
			for(int i=0;i<100;i++){
				if(!next.exists()){
					sleepInt(2);
				}else{
					verify(next.exists());
					next.click();
					break;
				}
			}
			sleepInt(2);
			for(int i=0;i<100;i++){
				if(!next.exists()){
					sleepInt(2);
				}else{
					verify(next.exists());
					next.click();
					break;
				}
			}
			sleepInt(2);
			for(int i=0;i<100;i++){
				if(!next.exists()){
					sleepInt(2);
				}else{
					verify(next.exists());
					next.click();
					break;
				}
			}
			
		}
		LeUiObject inbox = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").textContains("收件箱"));
		verify("未添加邮箱账号",inbox.exists());
		phone.pressBack();
		phone.pressBack();
		sleepInt(2);
		phone.pressHome();
		sleepInt(2);
	}
	
	@CaseName("发送邮件")
	public void testsendEmail() throws UiObjectNotFoundException, RemoteException {
	
		launchAppByPackage(IntentConstants.email);
		addStep("进入邮箱");
		sleepInt(2);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			LeUiObject newMail = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/conversation_bottom_bar")
				.className("android.widget.LinearLayout").index(1).childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)));
			verify("Can't find new mail button.", newMail.exists());
			newMail.click();
			addStep("新建邮件");
			sleepInt(2);
			LeUiObject recipient = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/to_content"));
			verify("没有找到收件人", recipient.exists());
			recipient.click();
			recipient.setText(getStrParams("email"));
			sleepInt(2);
			phone.pressEnter();
			addStep("填收件人");
			LeUiObject subject = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/subject_content"));
			verify("没有找到主题", subject.exists());
			subject.click();
			subject.setText("TestEmail");
			sleepInt(2);
			phone.pressEnter();
			addStep("填主题");
			LeUiObject body = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/body_wrapper"));
			verify("没有找到正文", body.exists());
			body.click();
			body.setText("TestEmail");
			sleepInt(2);
			phone.pressEnter();
			addStep("填正文");
			sleepInt(2);
			LeUiObject send = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/send"));
			verify("没有找到发送", body.exists());
			send.click();
			addStep("发送邮件");
			sleepInt(5);
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
	public void testsendEmailAttach() throws UiObjectNotFoundException, RemoteException {
	
		launchAppByPackage(IntentConstants.email);
		addStep("进入邮箱");
		sleepInt(2);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			LeUiObject newMail = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/conversation_bottom_bar")
				.className("android.widget.LinearLayout").index(1).childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)));
			verify("Can't find new mail button.", newMail.exists());
			newMail.click();
			addStep("新建邮件");
			sleepInt(2);
			LeUiObject recipient = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/to_content"));
			verify("没有找到收件人", recipient.exists());
			recipient.click();
			recipient.setText(getStrParams("email"));
			sleepInt(2);
			phone.pressEnter();
			addStep("填收件人");
			LeUiObject subject = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/subject_content"));
			verify("没有找到主题", subject.exists());
			subject.click();
			subject.setText("TestEmail");
			sleepInt(2);
			phone.pressEnter();
			addStep("填主题");
			LeUiObject body = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/body_wrapper"));
			verify("没有找到正文", body.exists());
			body.click();
			body.setText("TestEmail");
			sleepInt(2);
			phone.pressEnter();
			addStep("填正文");
			sleepInt(2);
			LeUiObject attach = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/add_attachments_control"));
			verify("没有找到附件按钮", attach.exists());
			attach.click();
			sleepInt(2);
			LeUiObject photo = new LeUiObject(new UiSelector()
				.resourceId("android:id/title").text("相册"));
			verify("没有找到相册按钮", photo.exists());
			photo.click();
			sleepInt(3);
			LeUiObject Resource = new LeUiObject(new UiSelector()
				.resourceId("com.android.gallery3d:id/album_name").text("Resource")); //com.android.gallery3d:id/album_name
			verify("没有找到resource 相册", Resource.exists());
			Resource.click();
			sleepInt(4);
			phone.click(200, 400);
			addStep("填附件");
			sleepInt(4);
			LeUiObject attachNote = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/attachments_header").textContains("共有1个附件"));
			verify("附件没有添加成功", attachNote.exists());
			addStep("验证附件是否添加成功");
			LeUiObject send = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/send"));
			verify("没有找到发送", body.exists());
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
	public void testopenEmail() throws UiObjectNotFoundException, RemoteException {
	
		launchAppByPackage(IntentConstants.email);
		addStep("进入邮箱");
		sleepInt(2);
		LeUiObject newMail = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/conversation_bottom_bar")
			.className("android.widget.LinearLayout").index(1).childSelector(new UiSelector().className("android.widget.RelativeLayout").index(3)));
		verify("Can't find new mail button.", newMail.exists());
		newMail.click();
		addStep("新建邮件");
		sleepInt(2);
		LeUiObject recipient = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/to_content"));
		verify("没有找到收件人", recipient.exists());
		recipient.click();
		recipient.setText(getStrParams("email"));
		sleepInt(2);
		phone.pressEnter();
		addStep("填收件人");
		LeUiObject subject = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/subject_content"));
		verify("没有找到主题", subject.exists());
		subject.click();
		subject.setText("TestEmail");
		sleepInt(2);
		phone.pressEnter();
		addStep("填主题");
		LeUiObject body = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/body_wrapper"));
		verify("没有找到正文", body.exists());
		body.click();
		body.setText("TestEmail");
		sleepInt(2);
		phone.pressEnter();
		addStep("填正文");
		sleepInt(2);
		LeUiObject attach = new LeUiObject(new UiSelector()
				.resourceId("com.android.email:id/add_attachments_control"));
		verify("没有找到附件按钮", attach.exists());
		attach.click();
		sleepInt(2);
		LeUiObject photo = new LeUiObject(new UiSelector()
			.resourceId("android:id/title").text("相册"));
		verify("没有找到相册按钮", photo.exists());
		photo.click();
		sleepInt(2);
		LeUiObject Resource = new LeUiObject(new UiSelector()
			.resourceId("com.android.gallery3d:id/album_name").text("Resource"));
		verify("没有找到resource 相册", Resource.exists());
		Resource.click();
		sleepInt(4);
		phone.click(200, 450);
		addStep("填附件");
		sleepInt(4);
		LeUiObject attachNote = new LeUiObject(new UiSelector()
			.resourceId("com.android.email:id/attachments_header").textContains("共有1个附件"));
		verify("附件没有添加成功", attachNote.exists());
		addStep("验证附件是否添加成功");
		press_back(1);
		sleepInt(1);
		LeUiObject saveDraft = new LeUiObject(new UiSelector().text("保存草稿"));
		saveDraft.click();
		addStep("保存至草稿箱");
		sleepInt(3);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			LeUiObject menu = new LeUiObject(new UiSelector()
				.className("android.widget.ImageButton").description("打开抽屉式导航栏"));
			verify("Can't find menu button.", menu.exists());
			menu.click();
			sleepInt(3);	
			LeUiObject draft = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").resourceId("com.android.email:id/name").text("草稿"));
			verify("Can't find inbox button.", draft.exists());
			draft.click();
			sleepInt(4);
			LeUiObject firstItem = new LeUiObject(new UiSelector()
			.className("android.widget.TextView").textContains("letvmtbf"));
			verify("Can't find first mail.", firstItem.exists());
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
