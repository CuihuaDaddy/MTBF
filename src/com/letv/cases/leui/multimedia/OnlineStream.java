package com.letv.cases.leui.multimedia;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class OnlineStream extends LetvTestCaseMTBF {

	private String link = "http://10.57.9.203/httpvod/video/flv-h264.html";
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
	
	private void clearCache() throws UiObjectNotFoundException {
		addStep("打开浏览器");
		launchApp(AppName.BROWSER);
		sleepInt(4);
		addStep("清除浏览器缓存，历史记录和cookie数据");
		LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
		verify("没有更多设置",more.exists());
		more.click();
		sleepInt(2);
		LeUiObject setting = new LeUiObject(new UiSelector().text("设置").resourceId("com.android.browser:id/menu_item_text"));
		verify("没有设置选项",setting.exists());
		setting.click();
		sleepInt(2);
		UiScrollable panal=new UiScrollable(new UiSelector().className("android.widget.ListView").resourceId("android:id/list"));
		LeUiObject clear = new LeUiObject(new UiSelector().textContains("数据清除"));
		if(!clear.exists()){
			panal.scrollIntoView(new UiSelector().textContains("数据清除"));
			sleepInt(2);
		}
		verify("没有浏览器数据清除选项",clear.exists());
		clear.click();
		sleepInt(2);
		LeUiObject cookie = new LeUiObject(new UiSelector().resourceId("android:id/list")
		.childSelector(new UiSelector().className("android.widget.LinearLayout").index(0)
				.childSelector(new UiSelector().resourceId("android:id/widget_frame").index(1)
					.childSelector(new UiSelector().resourceId("android:id/checkbox")))));
		if(!cookie.isChecked()){
			cookie.click();
		}
		LeUiObject cache = new LeUiObject(new UiSelector().resourceId("android:id/list")
			.childSelector(new UiSelector().className("android.widget.LinearLayout").index(1)
				.childSelector(new UiSelector().resourceId("android:id/widget_frame").index(1)
					.childSelector(new UiSelector().resourceId("android:id/checkbox")))));
		if(!cache.isChecked()){
			cache.click();
		}
		LeUiObject pwdName = new LeUiObject(new UiSelector().resourceId("android:id/list")
				.childSelector(new UiSelector().className("android.widget.LinearLayout").index(2)
						.childSelector(new UiSelector().resourceId("android:id/widget_frame").index(1)
								.childSelector(new UiSelector().resourceId("android:id/checkbox")))));
		if(!pwdName.isChecked()){
			pwdName.click();
		}
		LeUiObject delete = new LeUiObject(new UiSelector().text("删除"));
		verify("没有删除按钮",delete.exists());
		delete.click();
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("清除数据"));
		verify("没有清除数据按钮",confirm.exists());
		confirm.click();
		sleepInt(2);
		addStep("退出浏览器");
		press_back(5);
		press_home(1);
	}
	/*
1、打开浏览器。
2、清除浏览器缓存、历史记录和cookie数据。
3、退出浏览器
4、打开主界面的流媒体链接图标。
5、确认流媒体播放5s。
6、退出浏览器。
	 */
	@CaseName("在线流媒体")
	public void testOnlineVideo() throws UiObjectNotFoundException {
		for (int i = 0; i < getIntParams("Loop"); i++) {
			clearCache();
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
			LeUiObject video = new LeUiObject(new UiSelector().textContains("flv-h264.html"));
			if(!video.exists()){
				addStep("添加流媒体下载书签");
				press_back(1);
				LeUiObject urlEdit = new LeUiObject(new UiSelector().className(
						"android.widget.EditText").resourceId(
						"com.android.browser:id/url_input_view"));
				verify("没有输入栏",urlEdit.exists());
				urlEdit.click();
				sleepInt(1);
				callShell("input text " + link);
				sleepInt(1);
				addStep("在线播放流媒体");
				LeUiObject go = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/address_bar_right_outside_function_view"));
				verify("没有 前往 按钮",go.exists());
				go.click();
				sleepInt(10);
				addStep("添加到书签");
				LeUiObject share = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_share").index(1));
				verify("没有分享按钮",share.exists());
				share.click();
				sleepInt(2);
				LeUiObject addbookmark = new LeUiObject(new UiSelector().text("加入书签").resourceId("android:id/share_title"));
				verify("没有加入书签按钮",addbookmark.exists());
				addbookmark.click();
				sleepInt(2);
				LeUiObject sync = new LeUiObject(new UiSelector().text("暂不"));
				if(sync.exists()){
					sync.click();
					sleepInt(1);
				}
				LeUiObject save = new LeUiObject(new UiSelector().text("保存"));
				verify("没有保存按钮",save.exists());
				save.click();
				sleepInt(2);
			}
			press_back(5);
			press_home(1);
			addStep("打开浏览器");
			launchApp(AppName.BROWSER);
			sleepInt(4);
			verify("Can't find home page button.", home.exists());
			home.click();
			sleepInt(2);
			addStep("查看书签");
			verify("没有更多设置",more.exists());
			more.click();
			sleepInt(2);
			verify("没有书签选项",bookmark.exists());
			bookmark.click();
			sleepInt(2);
			verify("没有流媒体书签",video.exists());
			video.click();
			sleepInt(10);
			addStep("播放节目5秒钟");
			phone.click((int)(phone.getDisplayWidth()*0.43), (int)(phone.getDisplayHeight()*0.234));
			sleepInt(1);
			press_keyevent(5, 25);
//			callShell("input tap 623 599");
			sleepInt(8);
			LeUiObject webkit = new LeUiObject(new UiSelector().className(
				"android.webkit.WebView"));
			verify("没在浏览器页面.", webkit.exists());
			addStep("关闭播放");
			press_back(1);
			sleepInt(1);
			addStep("退出浏览器");
			press_back(5);
		}
	}
}
