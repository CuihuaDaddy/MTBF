package com.letv.cases.proto.message;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.LetvTestCaseMTBF;

public class SMSMMS extends LetvTestCaseMTBF {

	private final String SUBJECT = "010";
	private final String CONTENT = "123";
	private final String picture = "Image 100K.JPG";
	private final String audio = "Sound 100k.amr";
	private final String video = "Video 100K.3gp";

	// insert the attachments
	private void attach(String path, String att)
			throws UiObjectNotFoundException {
		UiObject attBtn = new UiObject(new UiSelector().className(
				"android.widget.TextView").description("Attach"));
		verify("Can't find attach button.", attBtn.exists());
		attBtn.click();
		sleepInt(1);
		UiObject attPic = new UiObject(new UiSelector().resourceId(
				"com.android.mms:id/text1").text(path));
		verify("Can't find picture button.", attPic.exists());
		attPic.click();
		sleepInt(2);
		UiObject filemanager = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("File Manager"));
		if (filemanager.exists()) {
			filemanager.click();
			sleepInt(2);
		}
		UiObject always = new UiObject(new UiSelector().resourceId(
				"android:id/button_always").text("Always"));
		if (always.exists()) {
			always.click();
			sleepInt(2);
		}
		UiScrollable list = new UiScrollable(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.rhmsoft.fm:id/entryList"));
		verify("Can't find list view.", list.exists());
		list.setAsVerticalList();
		UiObject resource = list
				.getChildByText(new UiSelector()
						.className(android.widget.TextView.class.getName()),
						"Resource");
		verify("Can't find Resource folder.", resource.exists());
		resource.click();
		sleepInt(1);
		UiObject mms = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("MMS"));
		verify("Can't find MMS folder.", mms.exists());
		mms.click();
		sleepInt(1);
		UiObject image = new UiObject(new UiSelector().className(
				"android.widget.TextView").text(att));
		verify("Can't find attachment.", image.exists());
		image.click();
		sleepInt(2);
		UiObject image_content = new UiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.mms:id/image_content"));
		if (!image_content.exists()) {
			image_content = new UiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.mms:id/video_thumbnail"));
		}
		if (!image_content.exists()) {
			image_content = new UiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/audio_name"));
		}
		verify("Can't attach accessory.", image_content.exists());
	}

	@CaseName("发送短信")
	public void testSendSMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		UiObject msgApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("Messaging"));
		verify("找不到信息图标", msgApp.waitForExists(5000));
		msgApp.clickAndWaitForNewWindow();
//		verify("未能打开信息程序", phone.getCurrentPackageName().equals(PACKAGE_MSG));

		UiObject emptyPrompt = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations."));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			UiObject newMsgBtn = new UiObject(new UiSelector().resourceId(
					"com.android.mms:id/action_compose_new").description(
					"New message"));
			newMsgBtn.click();
			sleepInt(2);
			UiObject PhoneSMSReceiverEdit = new UiObject(
					new UiSelector()
							.className("android.widget.MultiAutoCompleteTextView"));
			verify("未找到收信人输入框", PhoneSMSReceiverEdit.waitForExists(5000));
			PhoneSMSReceiverEdit.setText(getStrParams("dialNum"));
			sleepInt(2);
			UiObject contentEdit = new UiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			contentEdit.setText(CONTENT);
			sleepInt(2);
			addStep("发送新信息");
			UiObject sendBtn = new UiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.mms:id/send_button_sms"));
			sendBtn.click();
			sleepInt(2);
			UiObject sendingMark = new UiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("com.android.mms:id/date_view")
					.textContains("SENDING"));
			verify("未能发送信息", sendingMark.waitUntilGone(20000));
			UiObject date_view = new UiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/date_view"));
			verify("未能找到发送信息时间", date_view.exists());
			addStep("删除发送的信息");
			press_back(1);
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("打开短信")
	public void testOpenSMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		UiObject msgApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("Messaging"));
		verify("找不到信息图标", msgApp.waitForExists(5000));
		msgApp.clickAndWaitForNewWindow();
//		verify("未能打开信息程序", phone.getCurrentPackageName().equals(PACKAGE_MSG));

		UiObject emptyPrompt = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations."));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新信息");
		UiObject newMsgBtn = new UiObject(new UiSelector().resourceId(
				"com.android.mms:id/action_compose_new").description(
				"New message"));
		newMsgBtn.click();
		sleepInt(1);
		UiObject PhoneSMSReceiverEdit = new UiObject(
				new UiSelector()
						.className("android.widget.MultiAutoCompleteTextView"));
		verify("找不到收信人编辑框", PhoneSMSReceiverEdit.waitForExists(5000));
		PhoneSMSReceiverEdit.click();
		sleepInt(1);
		PhoneSMSReceiverEdit.setText(getStrParams("dialNum"));
		sleepInt(1);
		UiObject contentEdit = new UiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		verify("找不到信息内容编辑框", contentEdit.waitForExists(5000));
		contentEdit.click();
		sleepInt(1);
		contentEdit.setText(CONTENT);
		sleepInt(1);
		verify("输入信息有误",
				PhoneSMSReceiverEdit.getText().equals(
						getStrParams("dialNum") + ", ")
						&& contentEdit.getText().equals(CONTENT));

		addStep("保存信息");
		// UiObject backBtn = new UiObject(new UiSelector().className(
		// "android.widget.ImageView").resourceId("com.android.mms:id/up"));
		// backBtn.click();
		press_back(1);
		sleepInt(1);
		UiObject draft = new UiObject(new UiSelector().className(
				"android.widget.TextView")
				.textContains(getStrParams("dialNum")));
		verify("信息未保存", draft.waitForExists(10000));
		UiObject smsReceiver = new UiObject(new UiSelector().resourceIdMatches(
				"android:id/action_bar_.*").className("android.widget.TextView"));
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			verify("收信人或信息内容未找到.", smsReceiver.exists() && contentEdit.exists());
			verify("保存的信息有误",
					smsReceiver.getText()
							.equals(getStrParams("dialNum"))
							&& contentEdit.getText().equals(CONTENT));
			// backBtn.click();
			press_back(1);
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("发送图片彩信")
	public void testSendPicMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		UiObject msgApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("Messaging"));
		msgApp.clickAndWaitForNewWindow();
//		verify("未能打开信息程序", phone.getCurrentPackageName().equals(PACKAGE_MSG));

		UiObject emptyPrompt = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations."));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			UiObject newMsgBtn = new UiObject(new UiSelector().resourceId(
					"com.android.mms:id/action_compose_new").description(
					"New message"));
			newMsgBtn.click();
			sleepInt(1);
			UiObject PhoneSMSReceiverEdit = new UiObject(
					new UiSelector()
							.className("android.widget.MultiAutoCompleteTextView"));
			PhoneSMSReceiverEdit.setText(getStrParams("dialNum"));
			sleepInt(1);
			UiObject more = new UiObject(new UiSelector().className(
					"android.widget.ImageButton").description("More options"));
			verify("Can't find more button.", more.exists());
			more.click();
			sleepInt(1);
			UiObject add_subject = new UiObject(new UiSelector().resourceId(
					"android:id/title").text("Add subject"));
			verify("Can't find add subject button.", add_subject.exists());
			add_subject.click();
			sleepInt(1);
			UiObject subjectEdit = new UiObject(
					new UiSelector().resourceId("com.android.mms:id/subject"));
			verify("Can't find subject edit.", subjectEdit.exists());
			subjectEdit.click();
			sleepInt(1);
			callShell("input text " + SUBJECT);
			sleepInt(1);
			UiObject contentEdit = new UiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			contentEdit.setText(CONTENT);
			sleepInt(1);
			addStep("插入图片附件");
			attach("Pictures", picture);
			addStep("发送新信息");
			UiObject sendBtn = new UiObject(new UiSelector().resourceId(
					"com.android.mms:id/send_button_mms").description("发送彩信"));
			sendBtn.click();

			addStep("删除发送的信息");
			press_back(1);
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("发送音频彩信")
	public void testSendAudioMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		UiObject msgApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
//		verify("未能打开信息程序", phone.getCurrentPackageName().equals(PACKAGE_MSG));

		UiObject emptyPrompt = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			UiObject newMsgBtn = new UiObject(new UiSelector().resourceId(
					"com.android.mms:id/action_compose_new").description("新信息"));
			newMsgBtn.click();
			sleepInt(1);
			UiObject PhoneSMSReceiverEdit = new UiObject(
					new UiSelector()
							.className("android.widget.MultiAutoCompleteTextView"));
			PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
			sleepInt(1);
			UiObject more = new UiObject(new UiSelector().className(
					"android.widget.ImageButton").description("更多选项"));
			verify("Can't find more button.", more.exists());
			more.click();
			sleepInt(1);
			UiObject add_subject = new UiObject(new UiSelector().resourceId(
					"android:id/title").text("添加主题"));
			verify("Can't find add subject button.", add_subject.exists());
			add_subject.click();
			sleepInt(1);
			UiObject subjectEdit = new UiObject(
					new UiSelector().resourceId("com.android.mms:id/subject"));
			verify("Can't find subject edit.", subjectEdit.exists());
			subjectEdit.click();
			sleepInt(1);
			callShell("input text " + SUBJECT);
			sleepInt(1);
			UiObject contentEdit = new UiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			contentEdit.setText(CONTENT);
			sleepInt(1);
			addStep("插入音频附件");
			attach("音频", audio);
			addStep("发送新信息");
			UiObject sendBtn = new UiObject(new UiSelector().resourceId(
					"com.android.mms:id/send_button_mms").description("发送彩信"));
			sendBtn.click();

			addStep("删除发送的信息");
			press_back(1);
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("发送视频彩信")
	public void testSendVideoMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		UiObject msgApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
//		verify("未能打开信息程序", phone.getCurrentPackageName().equals(PACKAGE_MSG));

		UiObject emptyPrompt = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			UiObject newMsgBtn = new UiObject(new UiSelector().resourceId(
					"com.android.mms:id/action_compose_new").description("新信息"));
			newMsgBtn.click();
			sleepInt(1);
			UiObject PhoneSMSReceiverEdit = new UiObject(
					new UiSelector()
							.className("android.widget.MultiAutoCompleteTextView"));
			PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
			sleepInt(1);
			UiObject more = new UiObject(new UiSelector().className(
					"android.widget.ImageButton").description("更多选项"));
			verify("Can't find more button.", more.exists());
			more.click();
			sleepInt(1);
			UiObject add_subject = new UiObject(new UiSelector().resourceId(
					"android:id/title").text("添加主题"));
			verify("Can't find add subject button.", add_subject.exists());
			add_subject.click();
			sleepInt(1);
			UiObject subjectEdit = new UiObject(
					new UiSelector().resourceId("com.android.mms:id/subject"));
			verify("Can't find subject edit.", subjectEdit.exists());
			subjectEdit.click();
			sleepInt(1);
			callShell("input text " + SUBJECT);
			sleepInt(1);
			UiObject contentEdit = new UiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			contentEdit.setText(CONTENT);
			sleepInt(1);
			addStep("插入视频附件");
			attach("视频", video);
			addStep("发送新信息");
			UiObject sendBtn = new UiObject(new UiSelector().resourceId(
					"com.android.mms:id/send_button_mms").description("发送彩信"));
			sendBtn.click();

			addStep("删除发送的信息");
			press_back(1);
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("打开图片彩信")
	public void testOpenPicMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		UiObject msgApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
//		verify("未能打开信息程序", phone.getCurrentPackageName().equals(PACKAGE_MSG));

		UiObject emptyPrompt = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新彩信");
		UiObject newMsgBtn = new UiObject(new UiSelector().resourceId(
				"com.android.mms:id/action_compose_new").description("新信息"));
		newMsgBtn.click();
		sleepInt(1);
		UiObject PhoneSMSReceiverEdit = new UiObject(
				new UiSelector()
						.className("android.widget.MultiAutoCompleteTextView"));
		PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
		sleepInt(1);
		UiObject more = new UiObject(new UiSelector().className(
				"android.widget.ImageButton").description("更多选项"));
		verify("Can't find more button.", more.exists());
		more.click();
		sleepInt(1);
		UiObject add_subject = new UiObject(new UiSelector().resourceId(
				"android:id/title").text("添加主题"));
		verify("Can't find add subject button.", add_subject.exists());
		add_subject.click();
		sleepInt(1);
		UiObject subjectEdit = new UiObject(
				new UiSelector().resourceId("com.android.mms:id/subject"));
		verify("Can't find subject edit.", subjectEdit.exists());
		subjectEdit.click();
		sleepInt(1);
		callShell("input text " + SUBJECT);
		sleepInt(1);
		UiObject contentEdit = new UiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(CONTENT);
		sleepInt(1);
		addStep("插入图片附件");
		attach("照片", picture);
		verify("输入信息有误",
				PhoneSMSReceiverEdit.getText().equals(PhoneSMSReceiver + ", ")
						&& contentEdit.getText().equals(CONTENT));

		addStep("保存信息");
		// UiObject backBtn = new UiObject(new UiSelector().className(
		// "android.widget.ImageView").resourceId("com.android.mms:id/up"));
		// backBtn.click();
		press_back(1);
		sleepInt(1);
		UiObject draft = new UiObject(new UiSelector().className(
				"android.widget.TextView").textContains(PhoneSMSReceiver));
		verify("信息未保存", draft.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			addStep("验证彩信内容及附件");
			verify("Can't find draft message.", PhoneSMSReceiverEdit.exists()
					&& contentEdit.exists());
			verify("保存的信息有误",
					PhoneSMSReceiverEdit.getText().equals(
							PhoneSMSReceiver + ", ")
							&& contentEdit.getText().equals(CONTENT));
			// backBtn.click();
			UiObject image_content = new UiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.mms:id/image_content"));
			verify("未发现图片附件", image_content.exists());
			press_back(1);
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("打开音频彩信")
	public void testOpenAudioMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		UiObject msgApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
//		verify("未能打开信息程序", phone.getCurrentPackageName().equals(PACKAGE_MSG));

		UiObject emptyPrompt = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新彩信");
		UiObject newMsgBtn = new UiObject(new UiSelector().resourceId(
				"com.android.mms:id/action_compose_new").description("新信息"));
		newMsgBtn.click();
		sleepInt(1);
		UiObject PhoneSMSReceiverEdit = new UiObject(
				new UiSelector()
						.className("android.widget.MultiAutoCompleteTextView"));
		PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
		sleepInt(1);
		UiObject more = new UiObject(new UiSelector().className(
				"android.widget.ImageButton").description("更多选项"));
		verify("Can't find more button.", more.exists());
		more.click();
		sleepInt(1);
		UiObject add_subject = new UiObject(new UiSelector().resourceId(
				"android:id/title").text("添加主题"));
		verify("Can't find add subject button.", add_subject.exists());
		add_subject.click();
		sleepInt(1);
		UiObject subjectEdit = new UiObject(
				new UiSelector().resourceId("com.android.mms:id/subject"));
		verify("Can't find subject edit.", subjectEdit.exists());
		subjectEdit.click();
		sleepInt(1);
		callShell("input text " + SUBJECT);
		sleepInt(1);
		UiObject contentEdit = new UiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(CONTENT);
		sleepInt(1);
		addStep("插入音频附件");
		attach("音频", audio);
		verify("输入信息有误",
				PhoneSMSReceiverEdit.getText().equals(PhoneSMSReceiver + ", ")
						&& contentEdit.getText().equals(CONTENT));

		addStep("保存信息");
		// UiObject backBtn = new UiObject(new UiSelector().className(
		// "android.widget.ImageView").resourceId("com.android.mms:id/up"));
		// backBtn.click();
		press_back(1);
		sleepInt(1);
		UiObject draft = new UiObject(new UiSelector().className(
				"android.widget.TextView").textContains(PhoneSMSReceiver));
		verify("信息未保存", draft.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			addStep("验证彩信内容及附件");
			verify("Can't find draft message.", PhoneSMSReceiverEdit.exists()
					&& contentEdit.exists());
			verify("保存的信息有误",
					PhoneSMSReceiverEdit.getText().equals(
							PhoneSMSReceiver + ", ")
							&& contentEdit.getText().equals(CONTENT));
			// backBtn.click();
			UiObject audio_content = new UiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/audio_name"));
			verify("未发现音频附件", audio_content.exists());
			press_back(1);
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(5);
	}

	@CaseName("打开视频彩信")
	public void testOpenVideoMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		UiObject msgApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("短信"));
		msgApp.clickAndWaitForNewWindow();
//		verify("未能打开信息程序", phone.getCurrentPackageName().equals(PACKAGE_MSG));

		UiObject emptyPrompt = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("无会话。"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新彩信");
		UiObject newMsgBtn = new UiObject(new UiSelector().resourceId(
				"com.android.mms:id/action_compose_new").description("新信息"));
		newMsgBtn.click();
		sleepInt(1);
		UiObject PhoneSMSReceiverEdit = new UiObject(
				new UiSelector()
						.className("android.widget.MultiAutoCompleteTextView"));
		PhoneSMSReceiverEdit.setText(PhoneSMSReceiver);
		sleepInt(1);
		UiObject more = new UiObject(new UiSelector().className(
				"android.widget.ImageButton").description("更多选项"));
		verify("Can't find more button.", more.exists());
		more.click();
		sleepInt(1);
		UiObject add_subject = new UiObject(new UiSelector().resourceId(
				"android:id/title").text("添加主题"));
		verify("Can't find add subject button.", add_subject.exists());
		add_subject.click();
		sleepInt(1);
		UiObject subjectEdit = new UiObject(
				new UiSelector().resourceId("com.android.mms:id/subject"));
		verify("Can't find subject edit.", subjectEdit.exists());
		subjectEdit.click();
		sleepInt(1);
		callShell("input text " + SUBJECT);
		sleepInt(1);
		UiObject contentEdit = new UiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		contentEdit.setText(CONTENT);
		sleepInt(1);
		addStep("插入视频附件");
		attach("视频", video);
		verify("输入信息有误",
				PhoneSMSReceiverEdit.getText().equals(PhoneSMSReceiver + ", ")
						&& contentEdit.getText().equals(CONTENT));

		addStep("保存信息");
		// UiObject backBtn = new UiObject(new UiSelector().className(
		// "android.widget.ImageView").resourceId("com.android.mms:id/up"));
		// backBtn.click();
		press_back(1);
		sleepInt(1);
		UiObject draft = new UiObject(new UiSelector().className(
				"android.widget.TextView").textContains(PhoneSMSReceiver));
		verify("信息未保存", draft.exists());
		for (int i = 1; i <= TestCaseLoop; i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			addStep("验证彩信内容及附件");
			verify("Can't find draft message.", PhoneSMSReceiverEdit.exists()
					&& contentEdit.exists());
			verify("保存的信息有误",
					PhoneSMSReceiverEdit.getText().equals(
							PhoneSMSReceiver + ", ")
							&& contentEdit.getText().equals(CONTENT));
			// backBtn.click();
			UiObject video_content = new UiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.mms:id/video_thumbnail"));
			verify("未发现视频附件", video_content.exists());
			press_back(1);
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(5);
	}

	private void deleteExistingMsg() throws UiObjectNotFoundException {
		UiObject msgList = new UiObject(new UiSelector()
				.className("android.widget.ListView")
				.packageName("com.android.mms").resourceId("android:id/list"));
		msgList.waitForExists(5000);
		if (!msgList.exists())
			return;
		UiObject editLabel = new UiObject(new UiSelector().className(
				"android.widget.ImageButton").description("More options"));
		verify("找不到更多按钮", editLabel.waitForExists(5000));
		editLabel.click();
		sleepInt(1);
		// UiObject selAll = new UiObject(new UiSelector().className(
		// "android.widget.TextView").text("全选"));
		// selAll.click();
		// sleepInt(1);
		UiObject delAll = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("Delete all threads"));
		verify("找不到全部删除按钮", delAll.waitForExists(5000));
		delAll.click();
		sleepInt(1);
		// workaround for the invisiable popup prompt
		// phone.click(770, 1690);
		UiObject confirm = new UiObject(new UiSelector().resourceId(
				"android:id/button1").text("Delete"));
		verify("Can't find confirm button.", confirm.waitForExists(10000));
		confirm.click();
	}
}
