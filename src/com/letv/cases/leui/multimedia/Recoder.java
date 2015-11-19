package com.letv.cases.leui.multimedia;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Recoder extends LetvTestCaseMTBF {

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
	
	@CaseName("音频文件的录制、打开和删除")
	public void testRecodeAudio() throws UiObjectNotFoundException {
		launchApp(AppName.RECORDER);
		sleepInt(2);
		LeUiObject stopBtn = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/stopBtn").className(
				"android.widget.ImageView"));
		if(stopBtn.exists()){
			addStep("先停止录音");
			stopBtn.click();
			sleepInt(2);
		}
		LeUiObject emptyTag = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").textContains("无"));
		LeUiObject audioList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.recorder:id/record_list"));
		sleepInt(3);
		if (!emptyTag.exists()) {
			addStep("清空所有录音");
			clearAudioFile();
		}
		verify("录音记录不为空", emptyTag.exists());

		addStep("录制5秒音频");
		LeUiObject recordBtn = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/recordBtn").className(
				"android.widget.ImageView"));
		recordBtn.click();
		sleepInt(5);
		verify("未能开始录音", stopBtn.exists());

		addStep("保存音频");
		stopBtn.click();
		sleepInt(1);
		verify("未能保存录音", audioList.exists() && audioList.getChildCount() == 1);
		
		addStep("播放录音");
		phone.click((int)(phone.getDisplayWidth()*0.49), (int)(phone.getDisplayHeight()*0.124));
//		phone.click(702,318);
		sleepInt(1);
		LeUiObject playSeekBar = new LeUiObject(new UiSelector().className(
				"android.view.View").resourceId(
				"com.letv.android.recorder:id/play_seekbar"));
		verify("未能播放录音", playSeekBar.exists());
		press_back(1);

		addStep("清空录音文件");
		
		int dx = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerX();
		int dy = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		sleepInt(2);
		LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().textContains("删除"));
		verify("Can't find confirm button.", confirm.exists());
		confirm.click();
		sleepInt(1);
		verify("删除录音失败", emptyTag.exists());
		
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	private void clearAudioFile() throws UiObjectNotFoundException {
		LeUiObject audioList = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.recorder:id/record_list"));
		int dx = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerX();
		int dy = audioList.getChild(new UiSelector().className("android.widget.LinearLayout").index(0)).getBounds().centerY();
		phone.swipe(dx, dy, dx, dy, 100);
		for (int i = 0; i < audioList.getChildCount(); i++) {
			sleepInt(2);			
			UiObject itemCheckbox = audioList.getChild(
					new UiSelector().className("android.widget.LinearLayout")
							.index(i)).getChild(
					new UiSelector()
							.className("com.letv.leui.widget.LeCheckBox"));
			if (!itemCheckbox.isChecked()) {
				itemCheckbox.click();
				sleepInt(1);
			}
		}
		LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(1));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(1);
		LeUiObject confirm = new LeUiObject(new UiSelector().textContains("删除"));
		verify("Can't find confirm button.", confirm.exists());
		confirm.click();
		sleepInt(1);
	}

	private void clearDcim() throws UiObjectNotFoundException {
		launchApp(AppName.FILEMANAGER);
		sleepInt(2);
		LeUiObject PhoneMemory = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/mobile_storage_name")
				.text("手机存储"));
		verify("Can't find Phone Memory.", PhoneMemory.exists());
		PhoneMemory.click();
		sleepInt(2);
		LeUiObject dcim = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/navigation_view_item_name")
				.text("DCIM"));
		verify("Can't find dcim folder.", dcim.exists());
		dcim.click();
		sleepInt(1);
		LeUiObject camera = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/navigation_view_item_name")
				.text("Camera"));
		if(camera.exists()){
			int dx = camera.getBounds().centerX();
			int dy = camera.getBounds().centerY();
			phone.swipe(dx, dy, dx, dy, 100);
			sleepInt(2);
			LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(3));
			verify("Can't find delete button.", delete.exists());
			delete.click();
			sleepInt(1);
			LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm").index(2));
			verify("Can't find confirm button.", confirm.exists());
			confirm.click();
			sleepInt(1);
		}else {
			press_back(5);
			return;
		}

	}

	@CaseName("录制视频")
	public void testRecordVideo() throws UiObjectNotFoundException {
		addStep("打开相机，切换到录像模式");
		launchApp(AppName.CAMERA);
		sleepInt(2);
		phone.swipe((int)(phone.getDisplayWidth()*0.2), (int)(phone.getDisplayHeight()*0.5),
				(int)(phone.getDisplayWidth()*0.8), (int)(phone.getDisplayHeight()*0.5), 30);
		sleepInt(2);
		LeUiObject mode_video = new LeUiObject(new UiSelector()
				.resourceId("com.android.camera2:id/mode_video").text("视频")
				.selected(true));
		verify("Can't switch to video mode.", mode_video.exists());
		sleepInt(2);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("点击开始录像30S");
			LeUiObject record = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/shutter_button"));
			verify("Can't find record button.", record.exists());
			record.click();
			sleepInt(30);
			record.click();
			sleepInt(5);
			addStep("播放录像10S");
			LeUiObject thumb = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/preview_thumb_frame"));
			verify("Can't find thumb button.", thumb.exists());
			thumb.click();
			sleepInt(2);
			LeUiObject agreegoon = new LeUiObject(new UiSelector().className(
					"android.widget.Button").resourceId(
					"android:id/btn_agree"));
			if(agreegoon.exists()){
				agreegoon.click();			
			}
			phone.click((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.5));
//			phone.click(720, 1280);
			sleepInt(10);
			press_back(1);
			sleepInt(2);
			press_back(1);
			sleepInt(2);
		}
		addStep("删除录像");
		clearDcim();
		addStep("退出相机");
		press_back(1);
	}

	@CaseName("拍照")
	public void testTakePicture() throws UiObjectNotFoundException {
		addStep("打开相机，切换到照相模式");
		launchApp(AppName.CAMERA);
		sleepInt(2);
		LeUiObject mode_photo = new LeUiObject(new UiSelector()
				.resourceId("com.android.camera2:id/mode_photo").text("拍照")
				.selected(true));
		verify("Can't switch to photo mode.", mode_photo.exists());
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("点击拍照");
			LeUiObject shutter = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/shutter_button"));
			verify("Can't find shutter button.", shutter.exists());
			shutter.click();
			sleepInt(4);
			addStep("查看照片");
			LeUiObject thumb = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/preview_thumb_frame"));
			verify("Can't find thumb button.", thumb.exists());
			thumb.click();
			sleepInt(2);
			press_back(1);
			sleepInt(2);
		}
		addStep("删除照片");
		clearDcim();
		addStep("退出相机");
		press_back(1);
	}
}
