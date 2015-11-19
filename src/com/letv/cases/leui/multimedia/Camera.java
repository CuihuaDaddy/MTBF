package com.letv.cases.leui.multimedia;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Camera extends LetvTestCaseMTBF {
//	private String S1 = "";
	LeUiObject modeSlow = new LeUiObject(
			new UiSelector()
					.resourceId("com.android.camera2:id/mode_slow_motion"));
	LeUiObject modeVideo = new LeUiObject(
			new UiSelector()
					.resourceId("com.android.camera2:id/mode_video"));
	LeUiObject modePhoto = new LeUiObject(
			new UiSelector()
					.resourceId("com.android.camera2:id/mode_photo"));
	LeUiObject modePano = new LeUiObject(
			new UiSelector().resourceId("com.android.camera2:id/mode_pano"));

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		addStep("退出相机");
		press_back(4);
		callShell("rm -rf /sdcard/DCIM/*");
		sleepInt(2);
		if(callShell("getprop ro.product.model").contains("X6")){
			sleepInt(1500);
		}
		if(callShell("getprop ro.product.model").contains("Le 1")){
			sleepInt(1500);
		}
		super.tearDown();
	}

	@CaseName("启动相机5次")
	public void testOpenCamera() throws UiObjectNotFoundException {

		for (int i = 1; i<=getIntParams("Loop");i++) {
			addStep("打开相机第"+i+"次");
			launchApp(AppName.CAMERA);
			sleepInt(8);
			press_back(1);
		}
	}

	@CaseName("单拍50张相片")
	public void testTakePhotos() throws UiObjectNotFoundException {

		addStep("打开相机");
		launchApp(AppName.CAMERA);
		sleepInt(8);

		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.camera2:id/shutter_button"));
		addStep("连续拍照50次");
		for (int i = 1; i<=getIntParams("Loop");i++) {
			System.out.println("第 " + i + " 次拍照");
			verify("拍照按钮不存在", shutter.exists());
			shutter.click();
			sleepInt(3);
		}

		press_back(1);
	}

	@CaseName("前后相机切换5次")
	public void testChCameras() throws UiObjectNotFoundException {

		addStep("打开相机");
		launchApp(AppName.CAMERA);
		sleepInt(8);

		LeUiObject chCamera = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.camera2:id/camera_switch_btn"));

		addStep("前后相机切换100次");
		for (int i = 1; i<=getIntParams("Loop");i++) {
			System.out.println("第 " + i + " 次");
			verify("拍照按钮不存在", chCamera.exists());
			chCamera.click();
			sleepInt(3);
		}

		press_back(1);
	}

	@CaseName("切换拍照，视频，慢动作，全景模式 5次")
	public void testChModes() throws UiObjectNotFoundException {

		addStep("打开相机");
		launchApp(AppName.CAMERA);
		sleepInt(8);

		verify("不是四种模式都存在", modeSlow.exists() && modeVideo.exists()
				&& modePhoto.exists() && modePano.exists());

		addStep("拍照，视频，慢动作，全景模式切换100次");
		for (int i = 1; i<=getIntParams("Loop");i++) {

			addStep("第 " + i + " 次");
			addStep("拍照模式");
			modePhoto.click();
			sleepInt(3);
			verify("拍照模式未被选中", modePhoto.isSelected());
			addStep("视频模式");
			modeVideo.click();
			sleepInt(3);
			verify("视频模式未被选中", modeVideo.isSelected());
			addStep("慢动作模式");
			modeSlow.click();
			sleepInt(3);
			verify("慢动作模式未被选中", modeSlow.isSelected());
			addStep("视频模式");
			modeVideo.click();
			sleepInt(3);
			verify("视频模式未被选中", modeVideo.isSelected());
			addStep("拍照模式");
			modePhoto.click();
			sleepInt(3);
			verify("拍照模式未被选中", modePhoto.isSelected());
			addStep("全景模式");
			modePano.click();
			sleepInt(3);
			verify("全景模式未被选中", modePano.isSelected());
		}
		press_back(1);
	}

	@CaseName("开启闪光灯，拍照5次")
	public void testTakePhotoWithLight() throws UiObjectNotFoundException {

		addStep("打开相机");
		launchApp(AppName.CAMERA);
		sleepInt(8);

		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.camera2:id/shutter_button"));
		LeUiObject flash = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.camera2:id/camera_flash_btn"));

		verify("快门和闪光灯不都存在", shutter.exists() && flash.exists());

		addStep("连续拍照100次");
		for( int i = 1; i<=getIntParams("Loop");i++) {

			System.out.println("第 " + i + " 次拍照");
			flash.click();
			sleepInt(3);
			verify("拍照按钮不存在", shutter.exists());
			shutter.click();
			sleepInt(3);
			flash.click();
		}

		press_back(1);
	}

	@CaseName("开启闪光灯，录像5次，每次持续5秒钟")
	public void testVideoWithLight() throws UiObjectNotFoundException {

		addStep("打开相机");
		launchApp(AppName.CAMERA);
		sleepInt(8);

		LeUiObject modeVideo = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.camera2:id/mode_video"));

		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.camera2:id/shutter_button"));
		LeUiObject flash = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.camera2:id/camera_flash_btn"));

		verify("快门和闪光灯不都存在", shutter.exists() && flash.exists());
		phone.swipe((int)(phone.getDisplayWidth()*0.275), (int)(phone.getDisplayHeight()*0.44),(int)(phone.getDisplayWidth()*0.642),(int)(phone.getDisplayHeight()*0.44),30);
//		phone.swipe(396, 1127, 925, 1127, 30);
		sleepInt(3);
		verify("视频模式未被选中", modeVideo.isSelected());
		addStep("连续录像100次");
		for (int i = 1; i<=getIntParams("Loop");i++) {

			System.out.println("第 " + i + " 次");
			flash.click();
			sleepInt(3);
			verify("快门按钮不存在", shutter.exists());
			shutter.click();
			sleepInt(5);
			shutter.click();
			flash.click();
		}
		press_back(1);
	}

	@CaseName("切换拍照照片大小 5次")
	public void testChPhotoSize() throws UiObjectNotFoundException {

		addStep("打开相机");
		launchApp(AppName.CAMERA);
		sleepInt(8);
		LeUiObject sets = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.camera2:id/camera_setting_btn"));

		LeUiObject photoSize = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("照片尺寸"));
		LeUiObject photoSize_13M = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("13M"));
		LeUiObject photoSize_10M = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("10M"));
		LeUiObject photoSize_8M = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("8M"));

		for (int i = 0; i<=getIntParams("Loop"); i++) {
			verify("设置按钮不存在", sets.exists());
			sets.click();
			sleepInt(2);
			verify("照片尺寸不存在", photoSize.exists());
			photoSize.clickAndWaitForNewWindow();
			verify("照片尺寸未打开", photoSize_13M.exists() && photoSize_10M.exists()
					&& photoSize_8M.exists());

			switch (i % 3) {
			case 0:
				photoSize_13M.click();
				sleepInt(3);
				break;
			case 1:
				photoSize_10M.click();
				sleepInt(3);
				break;
			case 2:
				photoSize_8M.click();
				sleepInt(3);
				break;
			}
		}

		press_back(1);
	}

	@CaseName("切换切换视频大小  5次")
	public void testChVideoSize() throws UiObjectNotFoundException {

		addStep("打开视频");
		launchApp(AppName.CAMERA);
		sleepInt(8);
		LeUiObject sets = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.camera2:id/camera_setting_btn"));

		LeUiObject modeVideo = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.camera2:id/mode_video"));
		
		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.camera2:id/shutter_button"));

		verify("快门不存在", shutter.exists());
		phone.swipe((int)(phone.getDisplayWidth()*0.275), (int)(phone.getDisplayHeight()*0.44),(int)(phone.getDisplayWidth()*0.642),(int)(phone.getDisplayHeight()*0.44),30);
//		phone.swipe(396, 1127, 925, 1127, 30);
		sleepInt(3);
		verify("视频画质未被选中", modeVideo.isSelected());

		LeUiObject videoSize = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("视频画质"));
		LeUiObject videoSize_4k = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("4K (16:9)"));
		LeUiObject videoSize_1080p = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("1080P (16:9)"));
		LeUiObject videoSize_720p = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("720P (16:9)"));
		LeUiObject videoSize_480p = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("480P (3:2)"));		

		for (int i = 0; i<=getIntParams("Loop"); i++) {
			verify("设置按钮不存在", sets.exists());
			sets.click();
			sleepInt(2);
			verify("视频大小不存在", videoSize.exists());
			videoSize.clickAndWaitForNewWindow();
			verify("视频大小未打开", videoSize_4k.exists() && videoSize_1080p.exists()
					&& videoSize_720p.exists() && videoSize_480p.exists());
			switch (i % 4) {
			case 0:
				videoSize_4k.click();
				sleepInt(3);
				break;
			case 1:
				videoSize_1080p.click();
				sleepInt(3);
				break;
			case 2:
				videoSize_720p.click();
				sleepInt(3);
				break;
			case 3:
				videoSize_480p.click();
				sleepInt(3);
				break;
			}
		}

		press_back(1);
	}

	@CaseName("录视频2分钟")
	public void testTake30Video() throws UiObjectNotFoundException {
		
		addStep("打开相机");
		launchApp(AppName.CAMERA);
		sleepInt(8);

		LeUiObject modeVideo = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.camera2:id/mode_video"));

		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.camera2:id/shutter_button"));

		verify("快门不存在", shutter.exists());
		phone.swipe((int)(phone.getDisplayWidth()*0.275), (int)(phone.getDisplayWidth()*0.44),(int)(phone.getDisplayWidth()*0.642),(int)(phone.getDisplayWidth()*0.44),30);

//		phone.swipe(396, 1127, 925, 1127, 30);
		sleepInt(3);
		verify("视频模式未被选中", modeVideo.isSelected());

		shutter.click();
		sleepInt(2*60);
		shutter.click();
		press_back(1);
	}

	@CaseName("慢动作录视频2分钟")
	public void testTakeSlow30Video() throws UiObjectNotFoundException {
		
		addStep("打开相机");
		launchApp(AppName.CAMERA);
		sleepInt(8);
		LeUiObject modeVideo = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.camera2:id/mode_video"));
		LeUiObject modeSlow = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.camera2:id/mode_slow_motion"));

		LeUiObject shutter = new LeUiObject(new UiSelector().className(
				"android.widget.ImageView").resourceId(
				"com.android.camera2:id/shutter_button"));

		verify("快门不存在", shutter.exists());
		addStep("先切到视频模式");
		modeVideo.click();
		sleepInt(3);
		verify("视频模式未被选中", modeVideo.isSelected());
		addStep("再切到慢动作模式");
		modeSlow.click();
		sleepInt(3);
		verify("慢动作模式未被选中", modeSlow.isSelected());
		sleepInt(3);
		addStep("开始录制");
		verify("快门按钮不存在", shutter.exists());
		shutter.click();
		sleepInt(2*60);
		addStep("停止录制");
		shutter.click();
		sleepInt(2);
		addStep("退出相机");
		press_back(1);
	}
}