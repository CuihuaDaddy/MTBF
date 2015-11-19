package com.letv.cases.proto.multimedia;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCaseMTBF;

public class Recoder extends LetvTestCaseMTBF {

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

	private void clearDcim() throws UiObjectNotFoundException {
		launchAppByPackage(IntentConstants.proto_filemanager);
		sleepInt(2);
		UiObject dcim = new UiObject(new UiSelector().resourceId(
				"com.rhmsoft.fm:id/name").text("DCIM"));
		UiObject camera = new UiObject(new UiSelector().resourceId(
				"com.rhmsoft.fm:id/name").text("Camera"));
		UiObject listView = new UiObject(new UiSelector().className(
				"android.widget.ListView").resourceId(
				"com.rhmsoft.fm:id/entryList"));
		if(dcim.waitForExists(5000)){
			dcim.click();
			sleepInt(1);
			if(camera.waitForExists(5000)){
				camera.click();
				sleepInt(4);
				if (listView.exists()) {
					UiObject edit = new UiObject(new UiSelector().className(
							"android.widget.Button").text("Multi"));
					verify("Can't find edit button.", edit.waitForExists(5000));
					edit.click();
					UiObject all = new UiObject(new UiSelector().className(
							"android.widget.Button").text("Select All"));
					verify("Can't find select all button.", all.waitForExists(5000));
					all.click();
					UiObject delete = new UiObject(new UiSelector().className(
							"android.widget.Button").text("Delete"));
					verify("Can't find delete button.", delete.waitForExists(5000));
					delete.click();
					UiObject confirm = new UiObject(new UiSelector().resourceId(
							"com.rhmsoft.fm:id/button1").text("OK"));
					verify("Can't find confirm button.", confirm.waitForExists(5000));
					confirm.click();
					press_back(5);
				} else {
					press_back(5);
					return;
				}
			}else{
				System.out.println("Can't find camera folder.");
			}
		}else{
			System.out.println("Can't find dcim folder.");
		}
	}

	@CaseName("录制视频")
	public void testRecordVideo() throws UiObjectNotFoundException {
		clearDcim();
		addStep("打开相机，切换到录像模式");
		launchAppByPackage(IntentConstants.proto_camera);
		sleepInt(2);
		UiObject RePhoto = new UiObject(new UiSelector().resourceId(
				"android:id/alertTitle").className("android.widget.TextView").textContains("Remember"));
		UiObject NoThanks = new UiObject(new UiSelector().resourceId(
				"android:id/button2").textContains("No"));		
		if(RePhoto.exists()){
			sleep(2000);
			NoThanks.click();
			sleep(2000);
		}
		callShell("input swipe 100 900 600 900");
		UiObject mode_video = new UiObject(new UiSelector().resourceId(
				"com.android.camera2:id/mode_video").text("Video"));
		verify("Can't switch to video mode.", mode_video.waitForExists(5000));
		// int dx = mode_video.getBounds().centerX();
		// int dy = mode_video.getBounds().centerY();
		// callShell("input tap " + dx + " " + dy);
		sleepInt(2);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("点击开始录像60S");
			UiObject record = new UiObject(new UiSelector().resourceId(
					"com.android.camera2:id/shutter_button")
					.description("Shutter"));
			verify("Can't find record button.", record.waitForExists(10000));
			record.click();
			sleepInt(60);
			record.click();
			sleepInt(2);
			addStep("播放录像10S");
			// callShell("input swipe 600 900 100 900");
			UiObject thumb = new UiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/preview_thumb_frame"));
			verify("Can't find thumb button.", thumb.exists());
			thumb.click();
			// UiObject play = new UiObject(new UiSelector().resourceId(
			// "com.android.camera2:id/play_button").description("播放视频"));
			// verify("Can't find play button.", play.exists());
			// play.click();
			sleepInt(2);
			phone.click(733, 1260);
			sleepInt(10);
			press_back(1);
			sleepInt(2);
			press_back(1);
			sleepInt(1);
			if(!record.exists()){
				press_back(1);
			}
			sleepInt(3);
//			callShell("input swipe 100 900 600 900");
			// addStep("删除录像");
			// UiObject delete = new UiObject(new UiSelector().resourceId(
			// "com.android.camera2:id/filmstrip_bottom_control_delete")
			// .description("删除"));
			// verify("Can't find delete button.", delete.exists());
			// delete.click();
			// sleepInt(1);
			// UiObject done = new UiObject(new UiSelector().className(
			// "android.widget.TextView").text("已删除"));
			// if (done.exists()) {
			// done.click();
			// sleepInt(1);
			// if (delete.exists()) {
			// delete.click();
			// sleepInt(1);
			// }
			// }
		}
		addStep("删除录像");
		clearDcim();
		addStep("退出相机");
		press_back(1);
	}

	@CaseName("拍照")
	public void testTakePicture() throws UiObjectNotFoundException {
		clearDcim();
		addStep("打开相机，切换到照相模式");
		launchAppByPackage(IntentConstants.proto_camera);
		sleep(3000);
		// callShell("input swipe 100 900 600 900");
		UiObject RePhoto = new UiObject(new UiSelector().resourceId(
				"android:id/alertTitle").className("android.widget.TextView").textContains("Remember"));
		UiObject NoThanks = new UiObject(new UiSelector().resourceId(
				"android:id/button2").textContains("No"));		
		if(RePhoto.exists()){
			sleep(2000);
			NoThanks.click();
			sleep(2000);
		}
		UiObject mode_photo = new UiObject(new UiSelector().resourceId(
				"com.android.camera2:id/mode_photo").text("Camera"));
		verify("Can't switch to photo mode.", mode_photo.waitForExists(5000));
		// int dx = mode_photo.getBounds().centerX();
		// int dy = mode_photo.getBounds().centerY();
		// callShell("input tap " + dx + " " + dy);
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
			UiObject thumb = new UiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/preview_thumb_frame"));
			verify("Can't find thumb button.", thumb.exists());
			thumb.click();
			sleepInt(3);
			UiObject image = new UiObject(
					new UiSelector()
							.resourceId("com.android.gallery3d:id/gl_root_view"));
			UiObject Gallery = new UiObject(
					new UiSelector()
							.resourceId("android:id/text1").className("android.widget.TextView").text("Gallery"));
			UiObject OpenWithGallery = new UiObject(
					new UiSelector()
							.resourceId("android:id/title").className("android.widget.TextView").text("Open with Gallery"));
			if(Gallery.exists()||OpenWithGallery.exists()){
				if(Gallery.exists()){
					Gallery.click();
				}
				sleep(2000);
				UiObject ALWAYS = new UiObject(
						new UiSelector()
								.text("Always"));
				ALWAYS.click();
				sleep(1000);
			}
			verify("Can't find photo.", image.exists());
			image.click();
			addStep("删除录像");
			UiObject moreBtn = new UiObject(new UiSelector().className(
					"android.widget.ImageButton").description("More options"));
			if(!moreBtn.exists()){
				image.click();
			}
			verify("未找到更多按钮", moreBtn.waitForExists(5000));
			moreBtn.click();
			sleep(1000);
			UiObject delete = new UiObject(new UiSelector().resourceId(
					"android:id/title")
					.text("Delete"));
			verify("Can't find delete button.", delete.waitForExists(5000));
			delete.click();
			sleepInt(1);			
			UiObject done = new UiObject(new UiSelector().className(
					"android.widget.Button").text("OK"));
			verify("未找到确认按钮", done.waitForExists(5000));
			done.click();
			sleep(4000);
//			if (done.exists()) {
//				press_back(1);
//				sleepInt(1);
//			}
		}
		addStep("删除照片");
		clearDcim();
		addStep("退出相机");
		press_back(1);
	}
}
