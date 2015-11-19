package com.letv.cases.proto.multimedia;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class RecoderMTP extends LetvTestCase {

	@CaseName("音频文件的录制、打开和删除")
	public void testRecodeAudio() throws UiObjectNotFoundException {
		UiObject recorderApp = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("录音机"));
//		launchApp(recorderApp);

		UiObject emptyTag = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("暂无录音记录"));
		UiObject audioList = new UiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.recorder:id/record_list"));
		if (!emptyTag.exists()) {
			addStep("清空所有录音");
			clearAudioFile();
		}
		verify("录音记录不为空", emptyTag.exists());

		addStep("录制5秒音频");
		UiObject recordBtn = new UiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/recordBtn").className(
				"android.widget.ImageView"));
		recordBtn.click();
		sleepInt(5);
		UiObject stopBtn = new UiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/stopBtn").className(
				"android.widget.ImageView"));
		verify("未能开始录音", stopBtn.exists());

		addStep("保存音频");
		stopBtn.click();
		sleepInt(1);
		verify("未能保存录音", audioList.exists() && audioList.getChildCount() == 1);

		addStep("播放录音");
		audioList.getChild(
				new UiSelector().className("android.widget.LinearLayout")
						.index(0)).click();
		UiObject playSeekBar = new UiObject(new UiSelector().className(
				"android.widget.SeekBar").resourceId(
				"com.letv.android.recorder:id/play_seekbar"));
		verify("未能播放录音", playSeekBar.exists());
		press_back(1);

		addStep("清空录音文件");
		clearAudioFile();

		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	private void clearAudioFile() throws UiObjectNotFoundException {
		UiObject audioList = new UiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.letv.android.recorder:id/record_list"));
		UiObject editListTag = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("编辑"));
		editListTag.click();
		sleepInt(1);
		for (int i = 0; i < audioList.getChildCount(); i++) {
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
		UiObject delTag = new UiObject(new UiSelector().className(
				"android.widget.TextView").text("删除"));
		delTag.click();
		sleepInt(1);
		UiObject delBtn = new UiObject(new UiSelector().className(
				"android.widget.Button").text("删除"));
		delBtn.click();
		sleepInt(1);
	}

	@CaseName("录制视频")
	public void testRecordVideo() throws UiObjectNotFoundException {
		addStep("打开相机，切换到录像模式");
		launchAppByPackage(IntentConstants.proto_camera);
		sleepInt(2);
		callShell("input swipe 100 900 600 900");
		sleepInt(2);
		UiObject mode_video = new UiObject(new UiSelector().resourceId(
				"com.android.camera2:id/selector_text").text("Video"));
		verify("Can't switch to video mode.", mode_video.waitForExists(5000));
		mode_video.click();
		sleepInt(4);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("点击开始录像60S");
			UiObject record = new UiObject(new UiSelector().resourceId(
					"com.android.camera2:id/shutter_button")
					.description("Shutter"));
			verify("Can't find record button.", record.exists());
			record.click();
			sleepInt(3);
			UiObject recordTime = new UiObject(new UiSelector().resourceId(
					"com.android.camera2:id/recording_time"));
			verify("Can't start recording.", recordTime.exists());
			sleepInt(50);
			record.click();
			sleepInt(5);
			
			addStep("播放录像10S");
			callShell("input swipe 600 900 100 900");
			UiObject video = new UiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/video_view"));
			verify("Can't find video.", video.exists());
			UiObject play = new UiObject(new UiSelector().resourceId(
			"com.android.camera2:id/play_button").description("Play Video"));
		    verify("Can't find play button.", play.exists());
			play.click();
			sleepInt(10);
			press_back(1);
			sleepInt(2);
			
			addStep("删除录像");
			UiObject delete = new UiObject(new UiSelector().resourceId(
			"com.android.camera2:id/filmstrip_bottom_control_delete")
			.description("Delete"));
			verify("Can't find delete button.", delete.exists());
		    delete.click();
            sleepInt(3);
            verify("Can't delete video.", record.exists());
		}
		addStep("退出相机");
		press_back(3);
	}

	@CaseName("拍照")
	public void testTakePicture() throws UiObjectNotFoundException {
		addStep("打开相机，切换到照相模式");
		launchAppByPackage(IntentConstants.proto_camera);
		sleep(3000);
		callShell("input swipe 100 900 600 900");
		UiObject mode_photo = new UiObject(new UiSelector().resourceId(
				"com.android.camera2:id/selector_text").text("Camera"));
		verify("Can't switch to photo mode.", mode_photo.waitForExists(2000));
		mode_photo.click();
		sleepInt(3);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			sleep(3000);
			addStep("点击拍照");
			UiObject shutter = new UiObject(new UiSelector().resourceId(
					"com.android.camera2:id/shutter_button").description(
					"Shutter"));
			verify("Can't find shutter button.", shutter.exists());
			shutter.click();
			sleepInt(4);
			
			addStep("查看照片");
			callShell("input swipe 700 900 100 900");
			sleepInt(2);
			UiObject thumb = new UiObject(new UiSelector().className(
					"android.widget.ImageView").descriptionContains("Photo taken on"));
			verify("Can't find thumb button.", thumb.exists());
			
			addStep("删除照片");
			sleep(1000);
			UiObject delete = new UiObject(new UiSelector().resourceId(
					"com.android.camera2:id/filmstrip_bottom_control_delete")
					.description("Delete"));
			verify("Can't find delete button.", delete.waitForExists(5000));
			delete.click();
			sleepInt(2);
			verify("Can't delete photo.", shutter.exists());
		}
		addStep("退出相机");
		press_back(3);
	}
}
