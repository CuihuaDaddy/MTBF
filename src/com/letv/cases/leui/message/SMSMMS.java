package com.letv.cases.leui.message;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class SMSMMS extends LetvTestCaseMTBF {
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

	@CaseName("发送短信")
	public void testSendSMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		launchApp(AppName.MESSAGE);

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("无会话"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			LeUiObject newMsgBtn = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").description("新信息"));
			verify(newMsgBtn.exists());
			newMsgBtn.click();
			sleepInt(1);
			LeUiObject receiverEdit = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/recipients_editor"));
			verify(receiverEdit.exists());
			
			if (i % 2 == 0) {
				receiverEdit.setText(getStrParams("smsReceiver1"));
			} else {
				receiverEdit.setText(getStrParams("smsReceiver2"));
			}
			sleepInt(1);
			LeUiObject contentEdit = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			verify(contentEdit.exists());
			contentEdit.setText(getStrParams("smsContent"));
			sleepInt(1);
			addStep("选择sim卡");
			/*LeUiObject sim1 = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/sim1Name"));
			LeUiObject sim2 = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/sim2Name"));*/
            /*mengfengxiao@letv.com*/
            LeUiObject sim1 = new LeUiObject(new UiSelector().resourceId(
                    "com.android.mms:id/sub_sim_selected_layout").instance(0));
            LeUiObject sim2 = new LeUiObject(new UiSelector().resourceId(
                    "com.android.mms:id/sub_sim_selected_layout").instance(1));
            /*mengfengxiao@letv.com*/
			sleepInt(1);
			if (i % 2 == 0) {
				verify(sim1.exists());
				sim1.click();
				sleepInt(1);
			} else {
				verify(sim2.exists());
				sim2.click();
				sleepInt(1);
			}
			addStep("发送新信息");
			LeUiObject sendBtn = new LeUiObject(new UiSelector().resourceId(
                    "com.android.mms:id/send_button_sms"));
			verify(sendBtn.exists());
			sendBtn.click();
            sleepInt(2);
			/*LeUiObject backBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").description("向上导航"));
			verify(backBtn.exists());*/
            press_back(1);//mengfengxiao@letv.com
			sleepInt(1);
	/*		LeUiObject errorMark = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("com.android.mms:id/subject")
					.textContains("发送失败"));*/
            /*mengfengxiao@letv.com*/
            LeUiObject errorMark = new LeUiObject(new UiSelector().resourceId("android:id/alertTitle").text("无法发送短信"));
            /*mengfengxiao@letv.com*/
			verify("发送失败", !errorMark.exists());
			addStep("删除发送的信息");
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	@CaseName("打开短信")
	public void testOpenSMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		launchApp(AppName.MESSAGE);

		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("无会话"));
		if (!emptyPrompt.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPrompt.exists());

		addStep("编写新信息");
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").description("新信息"));
		verify(newMsgBtn.exists());
		newMsgBtn.click();
		sleepInt(1);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/recipients_editor"));
		verify(receiverEdit.exists());
		receiverEdit.setText(getStrParams("smsReceiver1"));
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		verify(contentEdit.exists());
		contentEdit.setText(getStrParams("smsContent"));
		addStep("保存信息");
        /*mengfengxiao@letv.com*/
        LeUiObject backBtn = new LeUiObject(new UiSelector().resourceId("com.android.mms:id/compose_back"));
		/*LeUiObject backBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").description("向上导航"));*/
		verify(backBtn.exists());
		backBtn.click();
		sleepInt(2);
/*		LeUiObject draft = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.mms:id/from"));
		verify("信息未保存", draft.exists());*/
        LeUiObject draft = new LeUiObject(new UiSelector().className("android.widget.TextView").resourceId("com.android.mms:id/subject").textContains("草稿"));
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("打开已保存的信息，第" + String.valueOf(i) + "遍");
			draft.click();
			sleepInt(1);
			verify("保存的信息有误",
//					receiverEdit
//							.getChild(
//									new UiSelector()
//											.className("android.widget.TextView"))
//							.getText().equals(getStrParams("smsReceiver"))
//							&& 
					contentEdit.getText().equals(
									getStrParams("smsContent")));
			verify(backBtn.exists());
			backBtn.click();
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	@CaseName("发送彩信(附件包括图片视频音频)")
	public void testSendMMS() throws UiObjectNotFoundException {
		addStep("打开信息程序");
		launchApp(AppName.MESSAGE);
		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations"));
		LeUiObject emptyPromptCN = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("无会话"));
		if (!emptyPrompt.exists() && !emptyPromptCN.exists()) {
			deleteExistingMsg();
		}
		sleepInt(3);
		verify("信息列表不为空", emptyPromptCN.exists() || emptyPrompt.exists());
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("编写新信息，第" + String.valueOf(i) + "遍");
			LeUiObject newMsgBtn = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/action_compose_new"));
			newMsgBtn.click();
			sleepInt(1);
			LeUiObject receiverEdit = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/recipients_editor"));
			if (i % 2 == 0) {
				receiverEdit.setText(getStrParams("deviceNum1"));
			} else {
				receiverEdit.setText(getStrParams("deviceNum2"));
			}
			sleepInt(1);
			LeUiObject contentEdit = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/embedded_text_editor"));
			contentEdit.setText(getStrParams("smsContent"));
			sleepInt(1);
			addStep("选择添加附件");
			LeUiObject attachment = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/share_button"));
			sleepInt(1);
			verify("没有添加附件按钮", attachment.exists());
			attachment.click();
			addStep("选择添加图片");
			LeUiObject shareMedia = new LeUiObject(new UiSelector().resourceId(
					"com.android.mms:id/tv_share_name").text("多媒体"));
			sleepInt(1);
			verify("没有添加多媒体附件按钮", shareMedia.exists());
			shareMedia.click();
			sleepInt(1);
			
			LeUiObject agreenbtn = new LeUiObject(new UiSelector().resourceId(
					"android.widget.Button").text("同意并继续"));
			if(agreenbtn.exists()){
				agreenbtn.click();
				sleepInt(2);
			}
			LeUiObject picture = new LeUiObject(
					new UiSelector()
						.resourceId("com.letv.android.filemanager:id/image_image"));
			sleepInt(1);
			verify("没有图片",picture.exists());
			picture.click();
//			LeUiObject filemanager = new LeUiObject(
//					new UiSelector()
//							.resourceId("com.letv.android.filemanager:id/mobile_storage"));
//			sleepInt(1);
//			verify("没有手机存储", filemanager.exists());
//			filemanager.click();
//			LeUiObject searchBtn = new LeUiObject(
//					new UiSelector()
//							.resourceId("com.letv.android.filemanager:id/category_search_menu"));
//			sleepInt(1);
//			verify("没有搜索按钮", searchBtn.exists());
//			searchBtn.click();
//			LeUiObject searchEdit = new LeUiObject(
//					new UiSelector()
//							.resourceId("com.letv.android.filemanager:id/search_edit"));
//			sleepInt(1);
//			verify("没有输入栏", searchEdit.exists());
//			searchEdit.setText("resource");
//			LeUiObject resourceFolder = new LeUiObject(
//					new UiSelector()
//							.resourceId(
//									"com.letv.android.filemanager:id/navigation_view_item_name")
//							.text("Resource"));
//			sleepInt(1);
//			verify("没有Resource文件夹", resourceFolder.exists());
//			resourceFolder.click();
			sleepInt(2);
			LeUiObject MMSFolder = new LeUiObject(
					new UiSelector()
							.resourceId(
									"com.letv.android.filemanager:id/navigation_view_item_name")
							.text("MMS"));
			sleepInt(1);
			verify("没有MMS文件夹", MMSFolder.exists());
			MMSFolder.click();
//			LeUiObject ImageFile = new LeUiObject(
//					new UiSelector()
//							.resourceId(
//									"com.letv.android.filemanager:id/navigation_view_item_name")
//							.textContains("Image"));
//			sleepInt(1);
//			verify("没有图片文件", ImageFile.exists());
//			ImageFile.click();
			LeUiObject ImageFile = new LeUiObject(
					new UiSelector()
							.className(
									"android.widget.ImageView"));
			sleepInt(1);
			verify("没有图片文件", ImageFile.exists());
			ImageFile.click();
			sleepInt(3);
			addStep("选择添加音频");
			verify("没有添加附件按钮", attachment.exists());
			attachment.click();
			sleepInt(1);
			verify("没有添加多媒体附件按钮", shareMedia.exists());
			shareMedia.click();
			sleepInt(1);
			LeUiObject audio = new LeUiObject(
					new UiSelector()
						.resourceId("com.letv.android.filemanager:id/image_audio"));
			sleepInt(1);
			verify("没有音频",audio.exists());
			audio.click();
			sleepInt(2);
//			sleepInt(1);
//			verify("没有手机存储", filemanager.exists());
//			filemanager.click();
//			sleepInt(1);
//			verify("没有搜索按钮", searchBtn.exists());
//			searchBtn.click();
//			sleepInt(1);
//			verify("没有输入栏", searchEdit.exists());
//			searchEdit.setText("resource");
//			sleepInt(1);
//			verify("没有Resource文件夹", resourceFolder.exists());
//			resourceFolder.click();
//			sleepInt(1);
//			verify("没有MMS文件夹", MMSFolder.exists());
//			MMSFolder.click();
			LeUiObject SoundFile = new LeUiObject(
					new UiSelector()
							.resourceId(
									"com.letv.android.filemanager:id/navigation_view_item_name")
							.textContains("Sound"));
			sleepInt(1);
			verify("没有音频文件", SoundFile.exists());
			SoundFile.click();
			sleepInt(3);
			addStep("选择添加视频");
			verify("没有添加附件按钮", attachment.exists());
			attachment.click();
			sleepInt(1);
			verify("没有添加多媒体附件按钮", shareMedia.exists());
			shareMedia.click();
			sleepInt(1);
			sleepInt(1);
			LeUiObject video = new LeUiObject(
					new UiSelector()
						.resourceId("com.letv.android.filemanager:id/image_video"));
			sleepInt(1);
			verify("没有视频",video.exists());
			video.click();
			sleepInt(2);
//			verify("没有手机存储", filemanager.exists());
//			filemanager.click();
//			sleepInt(1);
//			verify("没有搜索按钮", searchBtn.exists());
//			searchBtn.click();
//			sleepInt(1);
//			verify("没有输入栏", searchEdit.exists());
//			searchEdit.setText("resource");
//			sleepInt(1);
//			verify("没有Resource文件夹", resourceFolder.exists());
//			resourceFolder.click();
//			sleepInt(1);
//			verify("没有MMS文件夹", MMSFolder.exists());
//			MMSFolder.click();
			LeUiObject VideoFile = new LeUiObject(
					new UiSelector()
							.resourceId(
									"com.letv.android.filemanager:id/navigation_view_item_name")
							.textContains("Video"));
			sleepInt(1);
			verify("没有视频文件", VideoFile.exists());
			VideoFile.click();
			sleepInt(4);
			addStep("查看附件是否插入成功");
			LeUiObject Attach1 = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/video_thumbnail"));
			LeUiObject Attach2 = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.mms:id/play_slideshow_button"));
			sleepInt(1);
			verify("附件插入失败", (Attach2.exists())||(Attach1.exists()));

			addStep("选择sim卡");
            /*mengfengxiao@letv.com*/
            LeUiObject sim1 = new LeUiObject(new UiSelector().resourceId(
                    "com.android.mms:id/sub_sim_selected_layout").instance(0));
            LeUiObject sim2 = new LeUiObject(new UiSelector().resourceId(
                    "com.android.mms:id/sub_sim_selected_layout").instance(1));
            /*mengfengxiao@letv.com*/
			/*LeUiObject sim1 = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/sim1Name"));
			LeUiObject sim2 = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/sim2Name"));*/
			sleepInt(1);
			if (i % 2 == 0) {
				verify(sim1.exists());
				sim1.click();
				sleepInt(1);
			} else {
				verify(sim2.exists());
				sim2.click();
				sleepInt(1);
			}
			addStep("发送新信息");
			LeUiObject sendMMSBtn = new LeUiObject(new UiSelector().className(
					"android.widget.TextView").resourceId(
					"com.android.mms:id/send_button_mms"));
			verify(sendMMSBtn.exists());
			sendMMSBtn.click();
			sleepInt(8);
			/*LeUiObject backBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").description("向上导航"));
			verify(backBtn.exists());
			backBtn.click();*/
            press_back(1);
			sleepInt(1);
			/*LeUiObject errorMark = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("com.android.mms:id/subject")
					.textContains("发送失败"));*/
            LeUiObject errorMark = new LeUiObject(new UiSelector().resourceId("android:id/alertTitle").text("无法发送短信"));
			verify("发送失败", !errorMark.exists());
			addStep("删除发送的信息");
			deleteExistingMsg();
			sleepInt(1);
		}

		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	@CaseName("查看彩信(附件包括图片视频音频)")
	public void testOpenMMS() throws UiObjectNotFoundException {
		addStep("發送彩信");
		addStep("打开信息程序");
		launchApp(AppName.MESSAGE);
		LeUiObject emptyPrompt = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("No conversations"));
		LeUiObject emptyPromptCN = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("无会话"));
		if (!emptyPrompt.exists() && !emptyPromptCN.exists()) {
			deleteExistingMsg();
		}
		sleepInt(1);
		verify("信息列表不为空", emptyPromptCN.exists());
		LeUiObject newMsgBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.mms:id/action_compose_new"));
		verify(newMsgBtn.exists());
		newMsgBtn.click();
		sleepInt(1);
		LeUiObject receiverEdit = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/recipients_editor"));
		verify(receiverEdit.exists());
		receiverEdit.setText(getStrParams("deviceNum"));
		sleepInt(1);
		LeUiObject contentEdit = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/embedded_text_editor"));
		verify(contentEdit.exists());
		contentEdit.setText(getStrParams("smsContent"));
		sleepInt(1);
		addStep("选择添加附件");
		LeUiObject attachment = new LeUiObject(
				new UiSelector().resourceId("com.android.mms:id/share_button"));
		sleepInt(1);
		verify("没有添加附件按钮", attachment.exists());
		attachment.click();
		addStep("选择添加图片");
		LeUiObject shareMedia = new LeUiObject(new UiSelector().resourceId(
				"com.android.mms:id/tv_share_name").text("多媒体"));
		sleepInt(1);
		verify("没有添加多媒体附件按钮", shareMedia.exists());
		shareMedia.click();
		sleepInt(1);
		

		LeUiObject agreenbtn = new LeUiObject(new UiSelector().resourceId(
				"android.widget.Button").text("同意并继续"));
		if(agreenbtn.exists()){
			agreenbtn.click();
			sleepInt(2);
		}
		LeUiObject picture = new LeUiObject(
				new UiSelector()
					.resourceId("com.letv.android.filemanager:id/image_image"));
		sleepInt(1);
		verify("没有图片",picture.exists());
		picture.click();
		
		sleepInt(2);
		LeUiObject MMSFolder = new LeUiObject(
				new UiSelector()
						.resourceId(
								"com.letv.android.filemanager:id/navigation_view_item_name")
						.text("MMS"));
		sleepInt(1);
		verify("没有MMS文件夹", MMSFolder.exists());
		MMSFolder.click();
		
		LeUiObject ImageFile = new LeUiObject(
				new UiSelector()
						.className(
								"android.widget.ImageView"));
		sleepInt(1);
		verify("没有图片文件", ImageFile.exists());
		ImageFile.click();
		sleepInt(3);
		addStep("选择添加音频");
		verify("没有添加附件按钮", attachment.exists());
		attachment.click();
		sleepInt(1);
		verify("没有添加多媒体附件按钮", shareMedia.exists());
		shareMedia.click();
		sleepInt(1);
		LeUiObject audio = new LeUiObject(
				new UiSelector()
					.resourceId("com.letv.android.filemanager:id/image_audio"));
		sleepInt(1);
		verify("没有音频",audio.exists());
		audio.click();
		sleepInt(2);
		LeUiObject SoundFile = new LeUiObject(
				new UiSelector()
						.resourceId(
								"com.letv.android.filemanager:id/navigation_view_item_name")
						.textContains("Sound"));
		sleepInt(1);
		verify("没有音频文件", SoundFile.exists());
		SoundFile.click();
		sleepInt(3);
		addStep("选择添加视频");
		verify("没有添加附件按钮", attachment.exists());
		attachment.click();
		sleepInt(1);
		verify("没有添加多媒体附件按钮", shareMedia.exists());
		shareMedia.click();
		sleepInt(1);
		sleepInt(1);
		LeUiObject video = new LeUiObject(
				new UiSelector()
					.resourceId("com.letv.android.filemanager:id/image_video"));
		sleepInt(1);
		verify("没有视频",video.exists());
		video.click();
		sleepInt(2);
		
		UiScrollable scrollPanal = new UiScrollable(
				new UiSelector()
						.className(
								"android.widget.ListView").index(0));
		LeUiObject VideoFile = new LeUiObject(
				new UiSelector()
						.resourceId(
								"com.letv.android.filemanager:id/navigation_view_item_name")
						.textContains("Video 100K.3gp"));
		sleepInt(1);
		if(!VideoFile.exists()){
			scrollPanal.scrollForward();
			sleepInt(1);
		}
		verify("没有视频文件", VideoFile.exists());
		VideoFile.click();
		sleepInt(2);
//		LeUiObject filemanager = new LeUiObject(
//				new UiSelector()
//						.resourceId("com.letv.android.filemanager:id/mobile_storage"));
//		sleepInt(1);
//		verify("没有手机存储", filemanager.exists());
//		filemanager.click();
//		LeUiObject searchBtn = new LeUiObject(
//				new UiSelector()
//						.resourceId("com.letv.android.filemanager:id/category_search_menu"));
//		sleepInt(1);
//		verify("没有搜索按钮", searchBtn.exists());
//		searchBtn.click();
//		LeUiObject searchEdit = new LeUiObject(
//				new UiSelector()
//						.resourceId("com.letv.android.filemanager:id/search_edit"));
//		sleepInt(1);
//		verify("没有输入栏", searchEdit.exists());
//		searchEdit.setText("Resource");
//		LeUiObject resourceFolder = new LeUiObject(new UiSelector().resourceId(
//				"com.letv.android.filemanager:id/navigation_view_item_name")
//				.text("Resource"));
//		sleepInt(1);
//		verify("没有Resource文件夹", resourceFolder.exists());
//		resourceFolder.click();
//		LeUiObject MMSFolder = new LeUiObject(new UiSelector().resourceId(
//				"com.letv.android.filemanager:id/navigation_view_item_name")
//				.text("MMS"));
//		sleepInt(1);
//		verify("没有MMS文件夹", MMSFolder.exists());
//		MMSFolder.click();
//		LeUiObject ImageFile = new LeUiObject(new UiSelector().resourceId(
//				"com.letv.android.filemanager:id/navigation_view_item_name")
//				.textContains("Image"));
//		sleepInt(1);
//		verify("没有图片文件", ImageFile.exists());
//		ImageFile.click();
//		sleepInt(3);
//		addStep("选择添加音频");
//		sleepInt(1);
//		verify("没有添加附件按钮", attachment.exists());
//		attachment.click();
//		sleepInt(1);
//		verify("没有添加多媒体附件按钮", shareMedia.exists());
//		shareMedia.click();
//		sleepInt(1);
//		sleepInt(1);
//		verify("没有手机存储", filemanager.exists());
//		filemanager.click();
//		sleepInt(1);
//		verify("没有搜索按钮", searchBtn.exists());
//		searchBtn.click();
//		sleepInt(1);
//		verify("没有输入栏", searchEdit.exists());
//		searchEdit.setText("resource");
//		sleepInt(1);
//		verify("没有Resource文件夹", resourceFolder.exists());
//		resourceFolder.click();
//		sleepInt(1);
//		verify("没有MMS文件夹", MMSFolder.exists());
//		MMSFolder.click();
//		LeUiObject SoundFile = new LeUiObject(new UiSelector().resourceId(
//				"com.letv.android.filemanager:id/navigation_view_item_name")
//				.textContains("Sound"));
//		sleepInt(1);
//		verify("没有音频文件", SoundFile.exists());
//		SoundFile.click();
//		sleepInt(3);
//		addStep("选择添加视频");
//		sleepInt(1);
//		verify("没有添加附件按钮", attachment.exists());
//		attachment.click();
//		sleepInt(1);
//		verify("没有添加多媒体附件按钮", shareMedia.exists());
//		shareMedia.click();
//		sleepInt(1);
//		sleepInt(1);
//		verify("没有手机存储", filemanager.exists());
//		filemanager.click();
//		sleepInt(1);
//		verify("没有搜索按钮", searchBtn.exists());
//		searchBtn.click();
//		sleepInt(1);
//		verify("没有输入栏", searchEdit.exists());
//		searchEdit.setText("resource");
//		sleepInt(1);
//		verify("没有Resource文件夹", resourceFolder.exists());
//		resourceFolder.click();
//		sleepInt(1);
//		verify("没有MMS文件夹", MMSFolder.exists());
//		MMSFolder.click();
//		LeUiObject VideoFile = new LeUiObject(new UiSelector().resourceId(
//				"com.letv.android.filemanager:id/navigation_view_item_name")
//				.textContains("Video"));
//		sleepInt(1);
//		verify("没有视频文件", VideoFile.exists());
//		VideoFile.click();
//		sleepInt(3);

		addStep("查看附件是否插入成功");
		LeUiObject Attach1 = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/video_thumbnail"));
		LeUiObject Attach2 = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.mms:id/play_slideshow_button"));
		sleepInt(1);
		verify("附件插入失败", Attach2.exists()||Attach1.exists());

		addStep("选择sim卡");
        /*mengfengxiao@letv.com*/
        LeUiObject sim1 = new LeUiObject(new UiSelector().resourceId(
                "com.android.mms:id/sub_sim_selected_layout").instance(0));
        /*mengfengxiao@letv.com*/
		/*LeUiObject sim1 = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.mms:id/sim1Name"));*/
		verify(sim1.exists());
		sim1.click();
		sleepInt(1);
		addStep("发送新信息");
		LeUiObject sendMMSBtn = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").resourceId(
				"com.android.mms:id/send_button_mms"));
		verify(sendMMSBtn.exists());
		sendMMSBtn.click();
		sleepInt(8);
		LeUiObject backBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").description("向上导航"));
		verify(backBtn.exists());
		backBtn.click();
		sleepInt(1);
         /*mengfengxiao@letv.com*/
        LeUiObject errorMark = new LeUiObject(new UiSelector().resourceId("android:id/alertTitle").text("无法发送短信"));
        /*mengfengxiao@letv.com*/
		/*LeUiObject errorMark = new LeUiObject(new UiSelector()
				.className("android.widget.TextView")
				.resourceId("com.android.mms:id/subject").textContains("发送失败"));*/
		verify("发送失败", !errorMark.exists());
		sleepInt(20);
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("打开已发送成功的彩信，第" + String.valueOf(i) + "遍");
			LeUiObject msgList = new LeUiObject(new UiSelector()
					.className("android.widget.ListView")
					.packageName("com.android.mms")
					.resourceId("android:id/list"));
			int dx = msgList
					.getChild(
							new UiSelector().className(
									"android.widget.FrameLayout").index(0))
					.getBounds().centerX();
			int dy = msgList
					.getChild(
							new UiSelector().className(
									"android.widget.FrameLayout").index(0))
					.getBounds().centerY();
			
			phone.click(dx, dy);
			sleepInt(2);
			addStep("查看彩信内容");
			sleepInt(2);
			verify(backBtn.exists());
			backBtn.click();
			sleepInt(1);
		}
		addStep("清空信息");
		deleteExistingMsg();
		sleepInt(1);

		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));

	}

	private void deleteExistingMsg() throws UiObjectNotFoundException {
		LeUiObject msgList = new LeUiObject(new UiSelector()
				.className("android.widget.ListView")
				.packageName("com.android.mms").resourceId("android:id/list"));
		sleepInt(5);
		
		if (!msgList.exists())
			return;
		int dx = msgList
				.getChild(
						new UiSelector().className(
								"android.widget.FrameLayout").index(0))
				.getBounds().centerX();
		int dy = msgList         
				.getChild(
						new UiSelector().className(
								"android.widget.FrameLayout").index(0))
				.getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 200);
		sleepInt(2);
		LeUiObject selAll = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("全选"));
		if (selAll.exists()) {
			verify(selAll.exists());
			selAll.click();
		}
		sleepInt(1);
		LeUiObject delAll = new LeUiObject(new UiSelector().textContains("删除"));
		verify(delAll.exists());
		delAll.click();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().textContains("删除").resourceId("android:id/le_bottomsheet_default_confirm"));
		verify(confirm.exists());
		confirm.click();
		sleepInt(1);
	}
}
