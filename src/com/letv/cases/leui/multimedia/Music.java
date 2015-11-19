package com.letv.cases.leui.multimedia;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;
import com.letv.uf.Utf7ImeHelper;

public class Music extends LetvTestCaseMTBF {

	private String music1 = "California Dreaming";
	private String music2 = "Can you feel it";
	private String musicPackage = "com.android.music";
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		callShell("am force-stop " + musicPackage);
		super.tearDown();
	}

	@CaseName("打开音乐播放器，播放音乐，关闭音乐播放器")
	public void testPlayMusic() throws UiObjectNotFoundException {
		addStep("打开音乐播放器");
		launchApp(AppName.MUSIC);
		sleepInt(2);
		LeUiObject myMusic = new LeUiObject(new UiSelector().className(
				"android.widget.RelativeLayout").index(0).clickable(true));
//		verify("Can't find My music.", myMusic.exists());
		if(myMusic.exists()){
			myMusic.click();	
		}
		
		sleepInt(1);
		addStep("选择歌曲播放5秒");
		LeUiObject Mine = new LeUiObject(new UiSelector().className(
				"android.widget.TextView").text("我的"));
		if(Mine.exists()){
			Mine.click();
			sleepInt(2);
		}
		LeUiObject localMusic = new LeUiObject(new UiSelector().resourceId(
		"com.android.music:id/entry_local_music"));
		verify("Can't find local music.", localMusic.exists());
		localMusic.click();
		sleepInt(2);
		LeUiObject SongTag = new LeUiObject(new UiSelector().text("歌曲").className("android.widget.TextView"));
		verify("Can't find Song Tag.", SongTag.exists());
		SongTag.click();
		sleepInt(1);
		addStep("至少2首音乐被交替循环播放10遍");
		for (int i = 0; i < getIntParams("Loop"); i++) {
			LeUiObject m1 = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/track_name").text(music1));
			verify("Can't find music " + music1, m1.exists());
			m1.click();
			sleepInt(1);
			press_keyevent(10, 25);
			sleepInt(4);
			press_back(1);
			sleepInt(1);
			LeUiObject m2 = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/track_name").text(music2));
			verify("Can't find music " + music2, m2.exists());
			m2.click();
			sleepInt(5);
			press_back(1);
			sleepInt(1);
		}
		addStep("关闭音乐播放器");
		callShell("am force-stop " + musicPackage);
		press_back(5);
	}
	@CaseName("公开版:音乐模块测试:Loop 5")
	public void testMusic() throws UiObjectNotFoundException {
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("1进入音乐");
			launchApp(AppName.MUSIC);
			addStep("2音乐下方所有tag都进入并退出，循环5遍");
			LeUiObject seeMusic = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("看音乐"));
			LeUiObject my = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("我的"));
			LeUiObject listemMusic = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("听音乐"));
			LeUiObject WatchPlay = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("看演出"));
			LeUiObject search = new LeUiObject(new UiSelector().resourceId(
					"android:id/title").text("搜索"));
			verify("没有看音乐",seeMusic.exists());
			verify("没有我的",my.exists());
			verify("没有听音乐",listemMusic.exists());
			verify("没有看演出",WatchPlay.exists());
			verify("没有搜索",search.exists());
			for(int a=0;a<5;a++){
				seeMusic.click();
				sleepInt(1);
				listemMusic.click();
				sleepInt(1);
				search.click();
				sleepInt(1);
				WatchPlay.click();
				sleepInt(1);
				my.click();
				sleepInt(1);
			}
			addStep("3进入听音乐");
			listemMusic.click();
			sleepInt(3);
			addStep("4进入精选集推荐更多");
			UiObject choiceness = new UiObject(new UiSelector().resourceId(
					"com.android.music:id/recom_tab1_recommended_title")).getChild(new UiSelector().text("更多"));
			verify("没有精选集推荐更多按钮",choiceness.exists());
			choiceness.click();
			sleepInt(3);
			addStep("5在精选集推荐内上下滑动5次");
			for(int b=0;b<5;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
				sleepInt(1);
			}
			for(int b=0;b<5;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
				sleepInt(1);
			}
			addStep("6进入任意精选集");
			LeUiObject bestTrack = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/cornersImageView_best_collectons_item_cover"));
			verify("没有精选集",bestTrack.exists());
			bestTrack.click();
			sleepInt(3);
			addStep("7播放任意一首歌20秒");
			LeUiObject artistName = new LeUiObject(new UiSelector().resourceId(
					"com.android.music:id/artist_name"));
			verify("没有歌曲",artistName.exists());
			artistName.click();
			sleepInt(20);
//			LeUiObject playAnimation = new LeUiObject(new UiSelector().resourceId(
//					"com.android.music:id/playing_animation"));
//			verify("没有进入播放界面快捷按钮",playAnimation.exists());
//			playAnimation.click();
//			sleepInt(2);
			addStep("8退回到听音乐界面");
			press_back(2);
			addStep("9进入排行榜更多");
			UiObject Ranklist = new UiObject(new UiSelector().resourceId(
					"com.android.music:id/rank_title")).getChild(new UiSelector().text("更多"));
			verify("没有排行榜更多按钮",Ranklist.exists());
			Ranklist.click();
			sleepInt(3);
			addStep("10在排行榜内上下滑动5次");
			for(int b=0;b<5;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
				sleepInt(1);
			}
			for(int b=0;b<5;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
				sleepInt(1);
			}
			
			LeUiObject hotMusic = new LeUiObject(new UiSelector().text("热歌榜"));
			LeUiObject newMusic = new LeUiObject(new UiSelector().text("新歌榜"));
			LeUiObject originalMusic = new LeUiObject(new UiSelector().text("原创榜"));
			LeUiObject demoMusic = new LeUiObject(new UiSelector().text("音乐人Demo榜"));
			verify("没有热歌榜",hotMusic.exists());
			verify("没有新歌榜",newMusic.exists());
			verify("没有原创榜",originalMusic.exists());
			verify("没有音乐人Demo榜",demoMusic.exists());
			
			addStep("11进入热歌榜，上下滑动2次，随机选一首歌播放8秒");
			hotMusic.click();
			sleepInt(2);
			for(int b=0;b<2;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
				sleepInt(1);
			}
			for(int b=0;b<2;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
				sleepInt(1);
			}
			verify("没有歌曲",artistName.exists());
			artistName.click();
			sleepInt(8);
			press_back(1);
			
			addStep("12进入新歌榜，上下滑动2次，随机选一首歌播放8秒");
			newMusic.click();
			sleepInt(2);
			for(int b=0;b<2;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
				sleepInt(1);
			}
			for(int b=0;b<2;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
				sleepInt(1);
			}
			verify("没有歌曲",artistName.exists());
			artistName.click();
			sleepInt(8);
			press_back(1);
			
			addStep("13进入原创榜，上下滑动2次，随机选一首歌播放8秒");
			originalMusic.click();
			sleepInt(2);
			for(int b=0;b<2;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
				sleepInt(1);
			}
			for(int b=0;b<2;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
				sleepInt(1);
			}
			verify("没有歌曲",artistName.exists());
			artistName.click();
			sleepInt(8);
			press_back(1);
			
			addStep("14进入音乐人Demo榜，上下滑动2次，随机选一首歌播放8秒");
			demoMusic.click();
			sleepInt(2);
			for(int b=0;b<2;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134), 30);
				sleepInt(1);
			}
			for(int b=0;b<2;b++){
				phone.swipe((int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.134),
						(int)(phone.getDisplayWidth()*0.347), (int)(phone.getDisplayHeight()*0.742), 30);
				sleepInt(1);
			}
			verify("没有歌曲",artistName.exists());
			artistName.click();
			sleepInt(8);
			press_back(1);
			
			addStep("15进入搜索，搜索hero");
			search.click();
			sleepInt(2);
			LeUiObject searchText= new LeUiObject(new UiSelector().resourceId(
					"android:id/lc_search_src_text"));
			searchText.setText(Utf7ImeHelper.e("hero"));
			phone.pressEnter();
			sleepInt(5);
			
			addStep("16点击歌手");
			LeUiObject singer= new LeUiObject(new UiSelector().text("歌手"));
			verify("没有歌手",singer.exists());
			sleepInt(1);
			singer.click();
			sleepInt(2);
			addStep("17点击专辑");
			LeUiObject album= new LeUiObject(new UiSelector().text("专辑"));
			verify("没有专辑",album.exists());
			sleepInt(1);
			album.click();
			sleepInt(2);
			addStep("18点击MV");
			LeUiObject MV= new LeUiObject(new UiSelector().text("MV"));
			verify("没有MV",MV.exists());
			sleepInt(1);
			MV.click();
			sleepInt(2);
			addStep("19点击本地");
			LeUiObject local = new LeUiObject(new UiSelector().text("本地"));
			verify("没有本地",local.exists());
			sleepInt(1);
			local.click();
			sleepInt(2);
			addStep("20点击单曲");
			LeUiObject song= new LeUiObject(new UiSelector().text("单曲"));
			verify("没有单曲",song.exists());
			sleepInt(1);
			song.click();
			sleepInt(2);
			addStep("21播放歌曲20秒");
			verify("没有歌曲",artistName.exists());
			artistName.click();
			sleepInt(20);
			addStep("退出音乐");
			press_back(5);
			press_home(1);
		}
	}

}
