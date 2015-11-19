package com.letv.cases.leui.browser;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Download extends LetvTestCaseMTBF {
    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        openWifi();
    }

    @Override
    protected void tearDown() throws Exception {
        // TODO Auto-generated method stub
        super.tearDown();
    }
    private int getDownloadCount() throws UiObjectNotFoundException, RemoteException {
        if (!phone.isScreenOn()) {
            phone.wakeUp();
            sleepInt(1);
            unLockDevice();
        }
        press_back(5);
        phone.pressHome();
        int count = 0;


        addStep("打开文件管理器");
        launchApp(AppName.FILEMANAGER);
        sleepInt(3);
        addStep("进入手机存储");
        LeUiObject PhoneMemory = new LeUiObject(new UiSelector().resourceId(
                "com.letv.android.filemanager:id/mobile_storage_name")
                .text("手机存储"));
        verify("Can't find Phone Memory.", PhoneMemory.exists());
        PhoneMemory.click();
        sleepInt(2);
        LeUiObject download = new LeUiObject(new UiSelector().className(
                "android.widget.TextView")
                .text("Download"));
        LeUiObject download1 = new LeUiObject(new UiSelector().className(
                "android.widget.TextView")
                .text("download"));
        LeUiObject browser = new LeUiObject(new UiSelector().className(
                "android.widget.TextView")
                .text("Browser"));
        for(int d=0;d<=10;d++){
            if(download.exists()){
                sleepInt(1);
                download.click();
               /* mengfengxiao@letv.com*/
                verify(browser.exists());
                browser.click();
                sleepInt(1);
               /* mengfengxiao@letv.com*/
                break;
            }else if(browser.exists()){
                sleepInt(1);
                browser.click();
                sleepInt(1);
                verify(download1.exists());
                download1.click();
                break;
            }
            //phone.swipe((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.7),(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.3), 30);
            sleepInt(2);
        }
        LeUiObject downloadlist = new LeUiObject(new UiSelector().resourceId(
                "com.letv.android.filemanager:id/navigation_view_layout")
                .className("android.widget.ListView"));
        verify("Cant't find download list view.", downloadlist.exists());
        count = downloadlist.getChildCount();
       /* LeUiObject object = new LeUiObject(new UiSelector().className("android.widget.TextView").text("music.mp3"));
        count = 1;*/
        return count;
    }
    /*1、点击主界面下载文件书签图标，并开始下载。
    2、确认下载文件成功，
    3、文件管理器中删除下载文件。
    4、退出浏览器。*/
    @CaseName("下载一个正常文件")
    public void testDownloadTxt() throws UiObjectNotFoundException, RemoteException {
        testDeleteAll2();
        for (int i = 0; i < getIntParams("Loop"); i++) {
            addStep("打开浏览器");
            launchApp(AppName.BROWSER);
            sleepInt(4);
            LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
            verify("Can't find home page button.", home.exists());
            home.click();
            sleepInt(2);
            addStep("查看书签");
            LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
            verify("没有更多设置",more.exists());
            more.click();
            sleepInt(2);
            LeUiObject bookmark = new LeUiObject(new UiSelector().text("书签/历史").resourceId("com.android.browser:id/menu_item_text"));
            verify("没有书签选项",bookmark.exists());
            bookmark.click();
            sleepInt(2);
            LeUiObject txt = new LeUiObject(new UiSelector().text("text").resourceId("com.android.browser:id/title"));
            verify("没有下载文本书签",txt.exists());
            txt.click();
            sleepInt(10);
            LeUiObject confirm = new LeUiObject(new UiSelector().text(
                    "下载").resourceId(
                    "android:id/le_bottomsheet_default_confirm"));
            if(confirm.exists()){
                confirm.click();
            }
            sleepInt(3);
            addStep("确认下载文件成功");
            int count = getDownloadCount();
            if (count == 1) {
                addStep("下载文件成功");
            }else{
                fail("Can't download txt file, loop===" + i);
            }

            addStep("去文件管理器中删除下载文件。");
			/*mengfengxiao@letv.com*/
            press_back(1);//进入Download目录(仅有browser子目录)
            testDeleteAll();
        }
        addStep("退出浏览器");
        press_back(5);
        press_home(1);
    }

    @CaseName("下载音频文件并打开")
    public void testDownloadAudio() throws UiObjectNotFoundException, RemoteException {
        testDeleteAll2();
        for (int i = 0; i < getIntParams("Loop"); i++) {
            addStep("打开浏览器");
            launchApp(AppName.BROWSER);
            sleepInt(4);
            LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
            verify("Can't find home page button.", home.exists());
            home.click();
            sleepInt(2);
            addStep("查看书签");
            LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
            verify("没有更多设置",more.exists());
            more.click();
            sleepInt(2);
            LeUiObject bookmark = new LeUiObject(new UiSelector().text("书签/历史").resourceId("com.android.browser:id/menu_item_text"));
            verify("没有书签选项",bookmark.exists());
            bookmark.click();
            sleepInt(2);
            LeUiObject audio = new LeUiObject(new UiSelector().text("mp3").resourceId("com.android.browser:id/title"));
            verify("没有下载音频书签",audio.exists());
            audio.clickAndWaitForNewWindow();
            sleepInt(3);
            /*mengfengxiao@letv.com*/
            LeUiObject down = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/download_open").className("android.widget.Button"));
            verify(down.exists());
            down.click();
            sleepInt(10);
            /*mengfengxiao@letv.com*/
            addStep("确认下载音频是否成功");
            int count = getDownloadCount();
            if (count == 1) {
                addStep("下载音频成功");
            }else{
                fail("Can't download audio file, loop===" + i);
            }
            addStep("从文件管理器播放此音频文件，正常播放后停止播放。");
            LeUiObject music = new LeUiObject(new UiSelector().text(
                    "music.mp3").resourceId(
                    "com.letv.android.filemanager:id/navigation_view_item_name"));
            verify("下载失败",music.exists());
            music.click();
            sleepInt(5);
            addStep("停止播放");
            press_back(1);
            sleepInt(1);
            addStep("去文件管理器中删除下载文件。");
            /*mengfengxiao@letv.com*/
            press_back(1);//进入Download目录(仅有browser子目录)
            testDeleteAll();
        }
        addStep("退出浏览器");
        press_back(5);
        press_home(1);

    }
    /*1、点击主界面下载图片书签图标，并开始下载。
    2、确认下载图片文件成功，
    3、文件管理器中删除下载的图片文件。
    4、退出浏览器。*/
    @CaseName("下载图片")
    public void testDownloadPicture() throws UiObjectNotFoundException, RemoteException {
        testDeleteAll2();
        for (int i = 0; i < getIntParams("Loop"); i++) {
            addStep("打开浏览器");
            launchApp(AppName.BROWSER);
            sleepInt(4);
            LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
            verify("Can't find home page button.", home.exists());
            home.click();
            sleepInt(2);
            addStep("查看书签");
            LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
            verify("没有更多设置",more.exists());
            more.click();
            sleepInt(2);
            LeUiObject bookmark = new LeUiObject(new UiSelector().text("书签/历史").resourceId("com.android.browser:id/menu_item_text"));
            verify("没有书签选项",bookmark.exists());
            bookmark.click();
            sleepInt(2);
            LeUiObject picture = new LeUiObject(new UiSelector().text("picture").resourceId("com.android.browser:id/title"));
            verify("没有下载图片书签",picture.exists());
            picture.clickAndWaitForNewWindow();
            sleepInt(2);
            /*mengfengxiao@letv.com*/
            LeUiObject down = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/download_open").className("android.widget.Button"));
            verify(down.exists());
            down.click();
            sleepInt(10);
            addStep("确认下载图片成功");
            int count = getDownloadCount();
            if (count == 1) {
                addStep("下载图片成功");
            }else{
                fail("Can't download picture file, loop===" + i);
            }
            addStep("去文件管理器中删除下载文件。");
			/*mengfengxiao@letv.com*/
            press_back(1);//进入Download目录
            testDeleteAll();
        }
        addStep("退出浏览器");
        press_back(5);
        press_home(1);
    }
    /*
    1、点击主界面下载视频书签图标，并开始下载。
    2、确认下载文件成功。
    3、从文件管理器播放此视频文件，正常播放后停止播放。
    4、删除下载文件。
    5、退出浏览器
     */
    @CaseName("下载视频并能正确打开")
    public void testDownloadVideo() throws UiObjectNotFoundException, RemoteException {
        testDeleteAll2();
        for (int i = 0; i < getIntParams("Loop"); i++) {
            addStep("打开浏览器");
            launchApp(AppName.BROWSER);
            sleepInt(4);
            LeUiObject home = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_home").index(2));
            verify("Can't find home page button.", home.exists());
            home.click();
            sleepInt(2);
            addStep("查看书签");
            LeUiObject more = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/menu_bar_more"));
            verify("没有更多设置",more.exists());
            more.click();
            sleepInt(2);
            LeUiObject bookmark = new LeUiObject(new UiSelector().text("书签/历史").resourceId("com.android.browser:id/menu_item_text"));
            verify("没有书签选项",bookmark.exists());
            bookmark.click();
            sleepInt(2);
            LeUiObject video = new LeUiObject(new UiSelector().text("video").resourceId("com.android.browser:id/title"));
            verify("没有下载视频书签",video.exists());
            video.clickAndWaitForNewWindow();
            sleepInt(2);
            /*mengfengxiao@letv.com*/
            LeUiObject down = new LeUiObject(new UiSelector().resourceId("com.android.browser:id/download_open").className("android.widget.Button"));
            verify(down.exists());
            down.click();
            sleepInt(20);
            addStep("确认下载视频是否成功");
            int count = getDownloadCount();
            if (count == 1) {
                addStep("下载视频成功");
            }else{
                fail("Can't download video file, loop===" + i);
            }
            addStep("从文件管理器播放此视频文件，正常播放后停止播放。");
            LeUiObject video1 = new LeUiObject(new UiSelector().textContains(
                    "video.mp4").resourceId(
                    "com.letv.android.filemanager:id/navigation_view_item_name"));
            verify("下载失败",video1.exists());
            video1.clickAndWaitForNewWindow();
            sleepInt(2);
            LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_text").textContains("音频"));
			/*LeUiObject note = new LeUiObject(new UiSelector().text(
					"开启视频显示增强"));
			if(note.exists()){
				press_back(1);
				sleepInt(1);
			}*/
            if(confirm.exists()) {
                confirm.click();
                sleepInt(3);
                video1.click();
            }
            sleepInt(10);
            addStep("停止播放");
            press_back(1);
            sleepInt(1);
            addStep("去文件管理器中删除下载文件。");
			/*mengfengxiao@letv.com*/
            press_back(1);//进入Download目录
            testDeleteAll();
        }
        addStep("退出浏览器");
        press_back(5);
        press_home(1);
    }

    @CaseName("文件管理器中删除下载文件。")
    private void testDeleteAll() throws UiObjectNotFoundException {
        sleepInt(2);
        /*mengfengxiao@letv.com*/
        LeUiObject browser = new LeUiObject(new UiSelector().className(
                "android.widget.TextView").text("Browser"));
        if(browser.exists()){
            int dx1 = browser.getBounds().centerX();
            int dy1 = browser.getBounds().centerY();
            phone.swipe(dx1, dy1, dx1, dy1, 100);
        }
        sleepInt(2);
         /*mengfengxiao@letv.com*/
        LeUiObject bottom_bar = new LeUiObject(new UiSelector().resourceId("com.letv.android.filemanager:id/navigation_bottom_widget"));
        verify("can't find bottom bar", bottom_bar.exists());
        UiObject delete = bottom_bar.getChild(new UiSelector().className("android.widget.RelativeLayout").index(3));
		/*LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(3));*/
        /*mengfengxiao@letv.com*/
        verify("Can't find delete button.", delete.exists());
        delete.click();
        sleepInt(2);
        LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm").index(2));
        verify("Can't find confirm button.", confirm.exists());
        confirm.click();
        sleepInt(1);
        addStep("退出文件管理器");
        press_back(5);
    }

    private void testDeleteAll2() throws UiObjectNotFoundException {
        press_back(5);
        phone.pressHome();
        addStep("打开文件管理器");
        launchApp(AppName.FILEMANAGER);
        sleepInt(3);
        addStep("进入手机存储");
        LeUiObject PhoneMemory = new LeUiObject(new UiSelector().resourceId(
                "com.letv.android.filemanager:id/mobile_storage_name")
                .text("手机存储"));
        verify("Can't find Phone Memory.", PhoneMemory.exists());
        PhoneMemory.click();
        sleepInt(2);
        sleepInt(2);
        LeUiObject download = new LeUiObject(new UiSelector().className(
                "android.widget.TextView")
                .text("Download"));
        LeUiObject browser = new LeUiObject(new UiSelector().className(
                "android.widget.TextView")
                .text("Browser"));
        for(int d=0;d<=10;d++){
            if(download.exists()){
                int dx = download.getBounds().centerX();
                int dy = download.getBounds().centerY();
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
                break;
            }else if(browser.exists()){
                int dx = browser.getBounds().centerX();
                int dy = browser.getBounds().centerY();
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
                break;
            }
            phone.swipe((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.7),(int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.3), 30);
            sleepInt(2);
        }
        addStep("退出文件管理器");
        press_back(5);
    }
}
