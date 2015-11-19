package com.letv.cases.leui.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class oversea extends LetvTestCaseMTBF {
	private String PACKAGE_GOOGLEMap = "com.google.android.apps.maps";
	private String PACKAGE_YOUTUBE = "com.google.android.youtube";
	private String PACKAGE_CHROME = "com.android.chrome";
	private String PACKAGE_GMAIL = "com.google.android.gm";
	private String PACKAGE_GOOGLEPICTURE = "com.google.android.apps.photos";
	private String PACKAGE_GOOGLESETTING = "com.google.android.gm";
	private String PACKAGE_GOOGLEMUSIC= "com.google.android.music";
	private String PACKAGE_GOOGLEMOVIE= "com.google.android.videos";
	private String PACKAGE_VOICESEARCH= "com.google.android.googlequicksearchbox";
	private String PACKAGE_GOOGLESTORE= "com.google.android.gm";
	private String PACKAGE_HOME = "com.android.launcher3";
	LeUiObject AcceptContinue= new LeUiObject(new UiSelector().text("接受并继续"));
	LeUiObject pass= new LeUiObject(new UiSelector().text("跳过"));
	LeUiObject map= new LeUiObject(new UiSelector().text("地图"));
	LeUiObject YouTube= new LeUiObject(new UiSelector().text("YouTube"));
	LeUiObject Chrome= new LeUiObject(new UiSelector().text("Chrome"));
	LeUiObject Gmail= new LeUiObject(new UiSelector().text("Gmail"));
	LeUiObject GooglePicture= new LeUiObject(new UiSelector().text("Google 相册"));
	LeUiObject GoogleSetting= new LeUiObject(new UiSelector().text("Google设置"));
	LeUiObject PlayMusic= new LeUiObject(new UiSelector().text("Play 音乐"));
	LeUiObject PlayMovie= new LeUiObject(new UiSelector().text("Play影视"));
	LeUiObject VoiceSearch= new LeUiObject(new UiSelector().text("语音搜索"));
	LeUiObject GoogleStore= new LeUiObject(new UiSelector().text("Play 商店"));
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
//		callShell("am force-stop " + MapPackage);
		super.tearDown();
	}
	
	@CaseName("访问退出谷歌地图50遍,back退出25遍，home退出25遍")
	public void testGoogleMap() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入地图第1次");
		launchApp(AppName.MAP);
		verify("未能打开googleMap", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMap));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开googleMap", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMap));
			addStep("通过back退出地图第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出googleMap", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入地图第"+i+"次");
			verify("没有地图",map.exists());
			map.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开googleMap", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMap));
			addStep("通过Home退出地图第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出googleMap", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入地图第"+i+"次");
			verify("没有地图",map.exists());
			map.click();
		}
		press_back(1);
		press_home(1);
	}
	@CaseName("地图导航测试50次")
	public void testGoogleMapNavigation() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入地图");
		launchApp(AppName.MAP);
		sleepInt(2);
		if(AcceptContinue.exists()){
			AcceptContinue.click();
			sleepInt(8);
			if(pass.exists()){
				pass.click();
				sleepInt(2);
				}
		}
		sleepInt(8);
		verify("未能打开googleMap", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMap));
		for (int i = 1; i <= 50; i++) {
			LeUiObject directions = new LeUiObject(new UiSelector().resourceId("com.google.android.apps.gmm:id/on_map_directions_button").description("路线"));
			verify("没有directions",directions.exists());
			addStep("点击directions");
			directions.click();
			sleepInt(2);
			addStep("点击选择目的地");
			LeUiObject destination = new LeUiObject(new UiSelector().text("选择目的地"));
			verify("没有选择目的地",destination.exists());
			destination.click();
			sleepInt(2);
			addStep("点击在地图上选择");
			LeUiObject choiseFromMap = new LeUiObject(new UiSelector().text("在地图上选择"));
			verify("没有在地图上选择",choiseFromMap.exists());
			choiseFromMap.click();
			sleepInt(2);
			addStep("滑动屏幕");
			phone.swipe((int)(phone.getDisplayWidth()*0.725), (int)(phone.getDisplayHeight()*0.508),
					(int)(phone.getDisplayWidth()*0.0125), (int)(phone.getDisplayHeight()*0.508), 30);
			sleepInt(2);
			addStep("点击确定");
			LeUiObject ok = new LeUiObject(new UiSelector().text("确定"));
			verify("没有确定",ok.exists());
			ok.click();
			sleepInt(10);
			addStep("点击开始导航");
			LeUiObject start = new LeUiObject(new UiSelector().description("开始导航"));
			if(!start.exists()){
				addStep("在等10S");
				sleepInt(10);
			}
			verify("没有开始导航",start.exists());
			start.click();
			sleepInt(8);
			addStep("退出导航");
			press_back(1);
			sleepInt(1);
			press_back(1);
			sleepInt(1);
			press_back(1);
			sleepInt(2);
		}
		press_home(2);
	}
	
	@CaseName("访问退出YouTube50遍,back退出25遍，home退出25遍")
	public void testYouTube() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入YouTube第1次");
		launchApp(AppName.YOUTUBE);
		verify("未能打开YOUTUBE", phone.getCurrentPackageName().equals(PACKAGE_YOUTUBE));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开YOUTUBE", phone.getCurrentPackageName().equals(PACKAGE_YOUTUBE));
			addStep("通过back退出YOUTUBE第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出YOUTUBE", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入YOUTUBE第"+i+"次");
			verify("没有YOUTUBE",YouTube.exists());
			YouTube.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开YOUTUBE", phone.getCurrentPackageName().equals(PACKAGE_YOUTUBE));
			addStep("通过Home退出地图第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出YOUTUBE", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入YOUTUBE第"+i+"次");
			verify("没有YOUTUBE",YouTube.exists());
			YouTube.click();
		}
		press_back(1);
		press_home(1);
	}
	
	@CaseName("访问退出Chrome50遍,back退出25遍，home退出25遍")
	public void testChrome() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入Chrome第1次");
		launchApp(AppName.CHROME);
		verify("未能打开Chrome", phone.getCurrentPackageName().equals(PACKAGE_CHROME));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开Chrome", phone.getCurrentPackageName().equals(PACKAGE_CHROME));
			addStep("通过back退出Chrome第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出Chrome", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入Chrome第"+i+"次");
			verify("没有Chrome",Chrome.exists());
			Chrome.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开Chrome", phone.getCurrentPackageName().equals(PACKAGE_CHROME));
			addStep("通过Home退出Chrome第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出Chrome", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入Chrome第"+i+"次");
			verify("没有Chrome",Chrome.exists());
			Chrome.click();
		}
		press_back(1);
		press_home(1);
	}
	
	@CaseName("访问退出Gmail50遍,back退出25遍，home退出25遍")
	public void testGmail() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入Chrome第1次");
		launchApp(AppName.GMAIL);
		verify("未能打开Gmail", phone.getCurrentPackageName().equals(PACKAGE_GMAIL));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开Gmail", phone.getCurrentPackageName().equals(PACKAGE_GMAIL));
			addStep("通过back退出Gmail第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出Gmail", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入Gmail第"+i+"次");
			verify("没有Gmail",Gmail.exists());
			Gmail.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开Gmail", phone.getCurrentPackageName().equals(PACKAGE_GMAIL));
			addStep("通过Home退出Gmail第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出Gmail", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入Gmail第"+i+"次");
			verify("没有Gmail",Gmail.exists());
			Gmail.click();
		}
		press_back(1);
		press_home(1);
	}
	@CaseName("访问退出PlayMusic50遍,back退出25遍，home退出25遍")
	public void testPlayMusic() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入GoogleMusic第1次");
		launchApp(AppName.PLAYMUSIC);
		verify("未能打开PlayMusic", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMUSIC));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开PlayMusic", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMUSIC));
			addStep("通过back退出PlayMusic第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出PlayMusic", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入PlayMusic第"+i+"次");
			verify("没有PlayMusic",PlayMusic.exists());
			PlayMusic.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开PlayMusic", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMUSIC));
			addStep("通过Home退出PlayMusic第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出PlayMusic", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入PlayMusic第"+i+"次");
			verify("没有PlayMusic",PlayMusic.exists());
			PlayMusic.click();
		}
		press_back(1);
		press_home(1);
	}
	
	@CaseName("访问退出Play影视50遍,back退出25遍，home退出25遍")
	public void testPlayMovie() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入PlayMovie第1次");
		launchApp(AppName.PLAYMOVIE);
		verify("未能打开PlayMovie", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMOVIE));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开PlayMovie", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMOVIE));
			addStep("通过back退出PlayMovie第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出PlayMovie", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入PlayMovie第"+i+"次");
			verify("没有PlayMovie",PlayMovie.exists());
			PlayMovie.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开PlayMovie", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEMOVIE));
			addStep("通过Home退出PlayMovie第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出PlayMovie", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入PlayMovie第"+i+"次");
			verify("没有PlayMovie",PlayMovie.exists());
			PlayMovie.click();
		}
		press_back(1);
		press_home(1);
	}
	
	@CaseName("访问退出Google 相册50遍,back退出25遍，home退出25遍")
	public void testGooglePicture() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入GooglePicture第1次");
		launchApp(AppName.GOOGLEPICTURE);
		verify("未能打开GooglePicture", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEPICTURE));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开GooglePicture", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEPICTURE));
			addStep("通过back退出GooglePicture第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出GooglePicture", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入GooglePicture第"+i+"次");
			verify("没有GooglePicture",GooglePicture.exists());
			GooglePicture.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开GooglePicture", phone.getCurrentPackageName().equals(PACKAGE_GOOGLEPICTURE));
			addStep("通过Home退出GooglePicture第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出GooglePicture", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入GooglePicture第"+i+"次");
			verify("没有GooglePicture",GooglePicture.exists());
			GooglePicture.click();
		}
		press_back(1);
		press_home(1);
	}
	
	@CaseName("访问退出Google设置 50遍,back退出25遍，home退出25遍")
	public void testGoogleSetting() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入GoogleSetting第1次");
		launchApp(AppName.GOOGLESETTING);
		verify("未能打开GoogleSetting", phone.getCurrentPackageName().equals(PACKAGE_GOOGLESETTING));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开GoogleSetting", phone.getCurrentPackageName().equals(PACKAGE_GOOGLESETTING));
			addStep("通过back退出GoogleSetting第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出GoogleSetting", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入GoogleSetting第"+i+"次");
			verify("没有GoogleSetting",GoogleSetting.exists());
			GoogleSetting.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开GoogleSetting", phone.getCurrentPackageName().equals(PACKAGE_GOOGLESETTING));
			addStep("通过Home退出GoogleSetting第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出GoogleSetting", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入GoogleSetting第"+i+"次");
			verify("没有GoogleSetting",GoogleSetting.exists());
			GoogleSetting.click();
		}
		press_back(1);
		press_home(1);
	}
	
	@CaseName("访问退出VoiceSearch50遍,back退出25遍，home退出25遍")
	public void testVoiceSearch() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入VoiceSearch第1次");
		launchApp(AppName.VOICESEARCH);
		verify("未能打开VoiceSearch", phone.getCurrentPackageName().equals(PACKAGE_VOICESEARCH));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开VoiceSearch", phone.getCurrentPackageName().equals(PACKAGE_VOICESEARCH));
			addStep("通过back退出VoiceSearch第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出VoiceSearch", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入VoiceSearch第"+i+"次");
			verify("没有VoiceSearch",VoiceSearch.exists());
			VoiceSearch.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开VoiceSearch", phone.getCurrentPackageName().equals(PACKAGE_VOICESEARCH));
			addStep("通过Home退出VoiceSearch第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出VoiceSearch", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入VoiceSearch第"+i+"次");
			verify("没有VoiceSearch",VoiceSearch.exists());
			VoiceSearch.click();
		}
		press_back(1);
		press_home(1);
	}
	
	@CaseName("访问退出GoogleStore50遍,back退出25遍，home退出25遍")
	public void testGoogleStore() throws UiObjectNotFoundException {
		addStep("进入手机主界面");
		press_home(1);
		addStep("进入GoogleStore第1次");
		launchApp(AppName.GOOGLESTORE);
		verify("未能打开GoogleStore", phone.getCurrentPackageName().equals(PACKAGE_GOOGLESTORE));
		for (int i = 2; i <= 25; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开GoogleStore", phone.getCurrentPackageName().equals(PACKAGE_GOOGLESTORE));
			addStep("通过back退出GoogleStore第"+(i-1)+"次");
			press_back(1);
			sleepInt(1);
			verify("未能退出GoogleStore", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入GoogleStore第"+i+"次");
			verify("没有GoogleStore",GoogleStore.exists());
			GoogleStore.click();

		}
		for (int i = 26; i <= 50; i++) {
			sleepInt(2);
			if(AcceptContinue.exists()){
				AcceptContinue.click();
				sleepInt(8);
				if(pass.exists()){
					pass.click();
					sleepInt(2);
					}
			}
			sleepInt(8);
			verify("未能打开GoogleStore", phone.getCurrentPackageName().equals(PACKAGE_GOOGLESTORE));
			addStep("通过Home退出GoogleStore第"+(i-1)+"次");
			press_home(1);
			sleepInt(1);
			verify("未能退出GoogleStore", phone.getCurrentPackageName().equals(PACKAGE_HOME));
			addStep("进入GoogleStore第"+i+"次");
			verify("没有GoogleStore",GoogleStore.exists());
			GoogleStore.click();
		}
		press_back(1);
		press_home(1);
	}
}
