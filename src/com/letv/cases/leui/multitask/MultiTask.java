package com.letv.cases.leui.multitask;

import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class MultiTask extends LetvTestCaseMTBF {

	private String music1 = "California Dreaming";
	private String musicPackage = "com.android.music";
	private String PhonePackage = "com.android.dialer";
	boolean call = false;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub

		callShell("am force-stop " + PhonePackage);
		super.tearDown();
	}
	
	private void callNum(String number) throws UiObjectNotFoundException {
		launchApp(AppName.PHONE);
		for (int i = 0; i < number.length(); i++) {
			LeUiObject numBtn = new LeUiObject(new UiSelector()
					.className("android.widget.TextView")
					.resourceId("com.android.dialer:id/dialpad_key_number")
					.text(String.valueOf(number.charAt(i))));
			numBtn.click();
			sleepInt(1);
		}
		LeUiObject dialBtn1 = new LeUiObject(new UiSelector().resourceId("com.android.dialer:id/show_call_1").className("android.widget.FrameLayout"));
		dialBtn1.click();
		LeUiObject diallingPrompt = new LeUiObject(new UiSelector().resourceId(
				"com.android.dialer:id/callStateLabel").text("正在拨号"));
		verify("未能成功拨出号码", diallingPrompt.waitForExists(5000));
		addStep("验证成功拨号");
		LeUiObject endBtn = new LeUiObject(new UiSelector().className(
				"android.widget.ImageButton").resourceId(
				"com.android.dialer:id/end_call_button"));
		verify("未能成功接通", diallingPrompt.waitUntilGone(10000) && endBtn.exists());
	}


	private void browseWebPage() throws UiObjectNotFoundException {		
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(4);
		LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
		verify("Can't find home page button.", home.exists());
		home.click();
		sleepInt(2);
		addStep("查看是否有文档下载书签");
		LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
		verify("没有更多设置",more.exists());
		more.click();
		sleepInt(2);
		LeUiObject bookmark = new LeUiObject(new UiSelector().text("书签/历史").resourceId("com.android.browser:id/menu_item_text"));
		verify("没有书签选项",bookmark.exists());
		bookmark.click();
		sleepInt(2);
		sleepInt(2);
		LeUiObject sogou = new LeUiObject(new UiSelector().text("SOGOU").resourceId("com.android.browser:id/title"));
		verify("没有搜狗书签",sogou.exists());
		sogou.click();
		sleepInt(10);
		UiObject sogou1 = new UiObject(new UiSelector().textContains("搜狗").resourceId(
				"com.android.browser:id/url_input_view"));
		UiObject load = new UiObject(new UiSelector().textContains("正在加载").resourceId(
				"com.android.browser:id/url_input_view"));
		if(!(sogou1.exists()) && load.exists()){
			addStep("网页正在加载中,等20秒钟");
			sleepInt(20);
		}
		verify("网页没有加载成功", sogou1.exists());
	}
/*
 1、点击网页链接图标。
2、返回主界面，打开电话程序并拨通电话。
3、返回网页浏览界面并刷新1次。
4、返回电话界面
5、挂断电话后退出。
6、退出浏览器。
 */
	@CaseName("电话和浏览器切换")
	public void testPhoneBrowser() throws UiObjectNotFoundException {
		for (int i = 0; i < getIntParams("Loop"); i++) {
			browseWebPage();
			addStep("回到主界面");
			press_home(1);
			sleepInt(2);
			addStep("拨通电话");
			callNum(getStrParams("dialNum1"));
			addStep("返回网页浏览界面并刷新1次。");
			press_home(2);
			sleepInt(2);
			launchApp(AppName.BROWSER);
			sleepInt(2);
			LeUiObject refresh = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/address_bar_inner_right_function_view"));
			refresh.click();
			sleepInt(3);
			addStep("返回电话界面");
			phone.click((int)(phone.getDisplayWidth()*0.376), (int)(phone.getDisplayHeight()*0.0156));
//			phone.click(542, 40);
			sleepInt(1);
			addStep("挂断电话后退出。");
			LeUiObject endBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.dialer:id/end_call_button"));
			endBtn.click();
			addStep("退出浏览器。");
			press_back(5);
			press_home(1);
		
		}
	}
	@CaseName("播放音乐时拒绝来电")
	public void testPhoneMusic01() throws UiObjectNotFoundException {
		addStep("打开音乐播放器");
		launchApp(AppName.MUSIC);
		sleepInt(2);
		sleepInt(1);
		addStep("进入我的音乐");
		LeUiObject Mine = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("我的"));
		Mine.click();
		sleepInt(2);
		LeUiObject localMusic = new LeUiObject(new UiSelector().resourceId(
		"com.android.music:id/local_music_count"));
		verify("Can't find local music.", localMusic.exists());
		localMusic.click();
		sleepInt(2);
		LeUiObject SongTag = new LeUiObject(new UiSelector().text("歌曲").className("android.widget.TextView"));
		verify("Can't find Song Tag.", SongTag.exists());
		SongTag.click();
		sleepInt(1);
		addStep("播放歌曲");
		LeUiObject m1 = new LeUiObject(new UiSelector().resourceId(
				"com.android.music:id/track_name").text(music1));
		verify("Can't find music " + music1, m1.exists());
		m1.click();	
		for (int i = 0; i < getIntParams("Loop"); i++) {
			sleepInt(1);
			press_keyevent(5, 25);
			sleepInt(10);
			Log.i(MTCalltag, "StartCall");
			sleepInt(3);
			int a = 0;
			while(a<=60){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					call = true;
					addStep("拒绝配对机打来的电话");
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.238), (int)(phone.getDisplayHeight()*0.145));
//										phone.click((int)(phone.getDisplayWidth()*0.238), (int)(phone.getDisplayHeight()*0.0949));
					break;
				}
				a++;
			}
			sleepInt(3);
			verify("未能挂断电话",call&&(!callShell("dumpsys telecom").contains("RINGING")));
			addStep("成功挂断第" +i + "次电话");
			call = false;
			Log.i(MTCalltag, "StopCall");
			sleepInt(3);
			press_back(1);
			sleepInt(1);
			verify("Can't find music " + music1, m1.exists());
			m1.click();		
		}
		addStep("关闭音乐播放器");
		callShell("am force-stop " + musicPackage);
		press_home(1);
		
	}
	
	@CaseName("播放音乐时来电")
	public void testPhoneMusic02() throws UiObjectNotFoundException {
		addStep("打开音乐播放器");
		launchApp(AppName.MUSIC);
		sleepInt(2);
		sleepInt(1);
		addStep("进入我的音乐");
		LeUiObject Mine = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("我的"));
		Mine.click();
		sleepInt(2);
		LeUiObject localMusic = new LeUiObject(new UiSelector().resourceId(
		"com.android.music:id/local_music_count"));
		verify("Can't find local music.", localMusic.exists());
		localMusic.click();
		sleepInt(2);
		LeUiObject SongTag = new LeUiObject(new UiSelector().text("歌曲").className("android.widget.TextView"));
		verify("Can't find Song Tag.", SongTag.exists());
		SongTag.click();
		sleepInt(1);
		addStep("播放歌曲");
		LeUiObject m1 = new LeUiObject(new UiSelector().resourceId(
				"com.android.music:id/track_name").text(music1));
		verify("Can't find music " + music1, m1.exists());
		m1.click();	
		for (int i = 0; i < getIntParams("Loop"); i++) {
			sleepInt(1);
			press_keyevent(5, 25);
			sleepInt(10);
			Log.i(MTCalltag, "StartCall");
			sleepInt(3);
			int a = 0;
			while(a<=100){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					addStep("接通配对机打来的电话");
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.592), (int)(phone.getDisplayHeight()*0.145));
//										phone.click((int)(phone.getDisplayWidth()*0.592), (int)(phone.getDisplayHeight()*0.0988));
					break;
				}
				a++;
			}
			sleepInt(1);
			LeUiObject endBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.dialer:id/end_call_button"));
			verify("未能成功接通", endBtn.exists());
			addStep("成功接听第" +i + "次电话");
			sleepInt(10);
			endBtn.click();
			sleepInt(2);
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			press_back(1);
			sleepInt(1);
			verify("Can't find music " + music1, m1.exists());
			m1.click();		
		}
		addStep("关闭音乐播放器");
		callShell("am force-stop " + musicPackage);
		press_home(1);	
	}
	
	@CaseName("观看乐视网视频时拒绝来电")
	public void testPhoneLetv01() throws UiObjectNotFoundException {
		addStep("打开乐视网");
		launchApp(AppName.LETVVIDEO);
		sleepInt(2);
		LeUiObject UpdateNote = new LeUiObject(new UiSelector().resourceId("com.letv.android.client:id/update_later_btn")
				.textContains("升级"));
		if(UpdateNote.exists()){
			addStep("暂不升级");
			UpdateNote.click();
			sleepInt(1);
		}
		addStep("进入乐看搜索");
		LeUiObject LetvSearch = new LeUiObject(new UiSelector().resourceId("com.letv.android.client:id/searchContent"));
		verify("没有乐看搜素",LetvSearch.exists());
		LetvSearch.click();
		sleepInt(2);
//		addStep("点击搜索栏");
//		LeUiObject searchbox = new LeUiObject(new UiSelector().resourceId("com.letv.lesophoneclient:id/searchbox_layout"));
//		verify("没有搜索栏",searchbox.exists());
//		searchbox.click();
//		sleepInt(2);
		addStep("播放一个视频");
		LeUiObject video = new LeUiObject(new UiSelector().resourceId("com.letv.lesophoneclient:id/poster_img1").index(1));
		verify("没有视频",video.exists());
		video.click();
//		addStep("输入letv");
//		callShell("input text " + "letv");
//		sleepInt(1);
//		phone.pressEnter();
//		addStep("点击搜索结果“letv手机”");
//		LeUiObject letvPhone = new LeUiObject(new UiSelector().resourceId("com.letv.lesophoneclient:id/leso_suggest_key_name")
//				.text("letv手机"));
//		verify("没有搜到letv手机",letvPhone.exists());
//		letvPhone.click();
//		sleepInt(2);
//		addStep("观看letv手机相关的第一个视频");
//		phone.click((int)(phone.getDisplayWidth()*0.182), (int)(phone.getDisplayHeight()*0.296));

//		phone.click(262, 759);
		sleepInt(2);
		LeUiObject playvideo = new LeUiObject(new UiSelector().resourceId("com.letv.android.client:id/control_panel_root"));
		verify("没有播放视频",playvideo.exists());
		for (int i = 0; i < getIntParams("Loop"); i++) {
			sleepInt(20);
			Log.i(MTCalltag, "StartCall");
			sleepInt(3);
			int a = 0;
			while(a<=60){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					call = true;
					addStep("拒绝配对机打来的电话");
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.238), (int)(phone.getDisplayHeight()*0.145));

//										phone.click((int)(phone.getDisplayWidth()*0.238), (int)(phone.getDisplayHeight()*0.0949));
					break;
				}
				a++;
			}
			sleepInt(3);
			verify("未能挂断电话",call&&(!callShell("dumpsys telecom").contains("RINGING")));
			addStep("成功挂断第" +i + "次电话");
			call = false;
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			addStep("继续播放视频");
			phone.click((int)(phone.getDisplayWidth()*0.225), (int)(phone.getDisplayHeight()*0.141));

//			phone.click((int)(phone.getDisplayWidth()*0.225), (int)(phone.getDisplayHeight()*0.141));
			sleepInt(1);
			phone.click((int)(phone.getDisplayWidth()*0.0486), (int)(phone.getDisplayHeight()*0.248));

//			phone.click((int)(phone.getDisplayWidth()*0.0486), (int)(phone.getDisplayHeight()*0.248));
		}
		addStep("退出乐视网");
		press_back(5);
		press_home(1);
	}
	
	@CaseName("观看乐视网视频时接听一个电话")
	public void testPhoneLetv02() throws UiObjectNotFoundException {
		addStep("打开乐视网");
		launchApp(AppName.LETVVIDEO);
		sleepInt(2);
		LeUiObject UpdateNote = new LeUiObject(new UiSelector().resourceId("com.letv.android.client:id/update_later_btn")
				.textContains("升级"));
		if(UpdateNote.exists()){
			addStep("暂不升级");
			UpdateNote.click();
			sleepInt(1);
		}
		addStep("进入乐看搜索");
		LeUiObject LetvSearch = new LeUiObject(new UiSelector().resourceId("com.letv.android.client:id/searchContent"));
		verify("没有乐看搜素",LetvSearch.exists());
		LetvSearch.click();
		sleepInt(2);
//		addStep("点击搜索栏");
//		LeUiObject searchbox = new LeUiObject(new UiSelector().resourceId("com.letv.lesophoneclient:id/searchbox_layout"));
//		verify("没有搜索栏",searchbox.exists());
//		searchbox.click();
//		sleepInt(2);
		addStep("播放一个视频");
		LeUiObject video = new LeUiObject(new UiSelector().resourceId("com.letv.lesophoneclient:id/poster_img1").index(1));
		verify("没有视频",video.exists());
		video.click();
//		addStep("输入letv");
//		callShell("input text " + "letv");
//		sleepInt(1);
//		addStep("点击搜索结果“letv手机”");
//		LeUiObject letvPhone = new LeUiObject(new UiSelector().resourceId("com.letv.lesophoneclient:id/leso_suggest_key_name")
//				.text("letv手机"));
//		verify("没有搜到letv手机",letvPhone.exists());
//		letvPhone.click();
//		sleepInt(2);
//		addStep("观看letv手机相关的第一个视频");
//		phone.click((int)(phone.getDisplayWidth()*0.182), (int)(phone.getDisplayHeight()*0.269));
////		phone.click(262, 759);
		sleepInt(2);
		LeUiObject playvideo = new LeUiObject(new UiSelector().resourceId("com.letv.android.client:id/control_panel_root"));
		verify("没有播放视频",playvideo.exists());
		for (int i = 0; i < getIntParams("Loop"); i++) {
			sleepInt(20);
			Log.i(MTCalltag, "StartCall");
			sleepInt(3);
			int a = 0;
			while(a<=100){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					addStep("接通配对机打来的电话");
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.592), (int)(phone.getDisplayHeight()*0.145));

//										phone.click((int)(phone.getDisplayWidth()*0.592), (int)(phone.getDisplayHeight()*0.0988));
					break;
				}
				a++;
			}
			sleepInt(1);
			LeUiObject endBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.dialer:id/end_call_button"));
			verify("未能成功接通", endBtn.exists());
			addStep("成功接听第" +i + "次电话");
			sleepInt(10);
			endBtn.click();
			sleepInt(2);
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			addStep("继续播放视频");
			phone.click((int)(phone.getDisplayWidth()*0.225), (int)(phone.getDisplayHeight()*0.141));
			sleepInt(1);
			phone.click((int)(phone.getDisplayWidth()*0.0486), (int)(phone.getDisplayHeight()*0.248));
		}
		addStep("退出乐视网");
		press_back(5);
		press_home(1);
	}
	
	@CaseName("拍照时拒绝来电")
	public void testPhoneCamera01() throws UiObjectNotFoundException {
		addStep("打开相机，切换到照相模式");
		launchApp(AppName.CAMERA);
		sleepInt(2);
		LeUiObject mode_photo = new LeUiObject(new UiSelector()
				.resourceId("com.android.camera2:id/mode_photo").text("拍照")
				.selected(true));
		verify("Can't switch to photo mode.", mode_photo.exists());
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			for (int j = 1; j <= 5; j++) {
				addStep("点击拍照");
				LeUiObject shutter = new LeUiObject(new UiSelector().className(
						"android.widget.ImageView").resourceId(
								"com.android.camera2:id/shutter_button"));
				verify("Can't find shutter button.", shutter.exists());
				shutter.click();
				sleepInt(2);
			}
			Log.i(MTCalltag, "StartCall");
			int a = 0;
			while(a<=100){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				addStep("点击拍照");
				phone.click((int)(phone.getDisplayWidth()*0.37), (int)(phone.getDisplayHeight()*0.707));
				if (callShell("dumpsys telecom").contains("RINGING")){
					sleepInt(5);
					addStep("拒绝配对机打来的电话");
					call = true;
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.238), (int)(phone.getDisplayHeight()*0.145));
					break;
				}
				a++;
			}
			sleepInt(3);
			verify("未能挂断电话",call&&(!callShell("dumpsys telecom").contains("RINGING")));
			addStep("成功挂断第" +i + "次电话");
			call = false;
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			addStep("继续拍照");
		}
		addStep("退出相机");
		press_back(5);
		press_home(1);
	}
	
	@CaseName("拍照时接听来电")
	public void testPhoneCamera02() throws UiObjectNotFoundException {
		addStep("打开相机，切换到照相模式");
		launchApp(AppName.CAMERA);
		sleepInt(2);
		LeUiObject mode_photo = new LeUiObject(new UiSelector()
				.resourceId("com.android.camera2:id/mode_photo").text("拍照")
				.selected(true));
		verify("Can't switch to photo mode.", mode_photo.exists());
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			for (int j = 1; j <= 5; j++) {
				addStep("点击拍照");
				LeUiObject shutter = new LeUiObject(new UiSelector().className(
						"android.widget.ImageView").resourceId(
								"com.android.camera2:id/shutter_button"));
				verify("Can't find shutter button.", shutter.exists());
				shutter.click();
				sleepInt(2);
			}
			Log.i(MTCalltag, "StartCall");
			int a = 0;
			while(a<=100){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				addStep("点击拍照");
				phone.click((int)(phone.getDisplayWidth()*0.37), (int)(phone.getDisplayHeight()*0.707));

//				phone.click(539, 1810);
				if (callShell("dumpsys telecom").contains("RINGING")){
					sleepInt(5);
					addStep("接通配对机打来的电话");
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.592), (int)(phone.getDisplayHeight()*0.145));
					break;
				}
				a++;
			}
			sleepInt(1);
			LeUiObject endBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.dialer:id/end_call_button"));
			verify("未能成功接通", endBtn.exists());
			addStep("成功接听第" +i + "次电话");
			sleepInt(10);
			endBtn.click();
			sleepInt(2);
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			addStep("继续拍照");
		}
		addStep("退出相机");
		press_back(5);
		press_home(1);
	}
	
	@CaseName("录像时拒绝来电")
	public void testPhoneRecordVideo01() throws UiObjectNotFoundException {
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
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("点击开始录像");
			LeUiObject record = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/shutter_button"));
			verify("Can't find record button.", record.exists());
			record.click();
			sleepInt(20);
			Log.i(MTCalltag, "StartCall");
			int a = 0;
			while(a<=100){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					call = true;
					sleepInt(5);
					addStep("拒绝配对机打来的电话");
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.238), (int)(phone.getDisplayHeight()*0.145));
					break;
				}
				a++;
			}
			sleepInt(3);
			verify("未能挂断电话",call&&(!callShell("dumpsys telecom").contains("RINGING")));
			addStep("成功挂断第" +i + "次电话");
			call = false;
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			addStep("继续录像");
		}
		addStep("退出相机");
		press_back(5);
		press_home(1);
	}
	
	@CaseName("录像时接听来电")
	public void testPhoneRecordVideo02() throws UiObjectNotFoundException {
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
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("点击开始录像");
			LeUiObject record = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
					"com.android.camera2:id/shutter_button"));
			verify("Can't find record button.", record.exists());
			record.click();
			sleepInt(20);
			Log.i(MTCalltag, "StartCall");
			int a = 0;
			while(a<=100){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					sleepInt(5);
					addStep("接通配对机打来的电话");
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.592), (int)(phone.getDisplayHeight()*0.145));
					break;
				}
				a++;
			}
			sleepInt(1);
			LeUiObject endBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.dialer:id/end_call_button"));
			verify("未能成功接通", endBtn.exists());
			addStep("成功接听第" +i + "次电话");
			sleepInt(10);
			endBtn.click();
			sleepInt(2);
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			addStep("继续录像");
		}
		addStep("退出相机");
		press_back(5);
		press_home(1);
		
	}
	
	@CaseName("录音时拒绝来电")
	public void testPhoneRecordVoice01() throws UiObjectNotFoundException {
		addStep("进入录音机");
		launchApp(AppName.RECORDER);
		sleepInt(2);
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("开始录音");
			LeUiObject recordBtn = new LeUiObject(new UiSelector().resourceId(
					"com.letv.android.recorder:id/recordBtn").className(
					"android.widget.ImageView"));
			recordBtn.click();
			sleepInt(20);
			Log.i(MTCalltag, "StartCall");
			int a = 0;
			while(a<=100){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					call = true;
					sleepInt(5);
					addStep("拒绝配对机打来的电话");
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.238), (int)(phone.getDisplayHeight()*0.145));
					break;
				}
				a++;
			}
			sleepInt(3);
			verify("未能挂断电话",call&&(!callShell("dumpsys telecom").contains("RINGING")));
			addStep("成功挂断第" +i + "次电话");
			call = false;
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			addStep("继续录音");
		}
		LeUiObject stopBtn = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/stopBtn").className(
				"android.widget.ImageView"));
		verify("未能开始录音", stopBtn.exists());
		addStep("停止录音");
		stopBtn.click();
		addStep("退出录音机");
		press_back(5);
		press_home(1);
	}
	@CaseName("录音时接听来电")
	public void testPhoneRecordVoice02() throws UiObjectNotFoundException {
		addStep("进入录音机");
		launchApp(AppName.RECORDER);
		sleepInt(2);
		for (int i = 1; i <= getIntParams("Loop"); i++) {
			addStep("开始录音");
			LeUiObject recordBtn = new LeUiObject(new UiSelector().resourceId(
					"com.letv.android.recorder:id/recordBtn").className(
					"android.widget.ImageView"));
			recordBtn.click();
			sleepInt(20);
			Log.i(MTCalltag, "StartCall");
			int a = 0;
			while(a<=100){
				sleepInt(1);
				System.out.println("等待来电: "+a+"second");
				if (callShell("dumpsys telecom").contains("RINGING")){
					sleepInt(5);
					addStep("接通配对机打来的电话");
					sleepInt(2);
					phone.click((int)(phone.getDisplayWidth()*0.592), (int)(phone.getDisplayHeight()*0.145));
					break;
				}
				a++;
			}
			sleepInt(1);
			LeUiObject endBtn = new LeUiObject(new UiSelector().className(
					"android.widget.ImageButton").resourceId(
					"com.android.dialer:id/end_call_button"));
			verify("未能成功接通", endBtn.exists());
			addStep("成功接听第" +i + "次电话");
			sleepInt(10);
			endBtn.click();
			sleepInt(2);
			Log.i(MTCalltag, "StopCall");
			sleepInt(2);
			addStep("继续录音");
		}
		LeUiObject stopBtn = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.recorder:id/stopBtn").className(
				"android.widget.ImageView"));
		verify("未能开始录音", stopBtn.exists());
		addStep("停止录音");
		stopBtn.click();
		addStep("退出录音机");
		press_back(5);
		press_home(1);
	}
	
}
