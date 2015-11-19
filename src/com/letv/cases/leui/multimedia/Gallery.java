package com.letv.cases.leui.multimedia;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Gallery extends LetvTestCaseMTBF {
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
			
	}
	
	@CaseName("从图库启动相机 5次")
	public void testOpenGallery() throws UiObjectNotFoundException {
		addStep("进入图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(4);
		LeUiObject albums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums"));
		verify("没有成功进入图库",albums.exists());
		sleepInt(2);

		/*mengfengxiao@letv.com*/
        int loop = getIntParams("Loop");
        /*mengfengxiao@letv.com*/
		addStep("启动相机"+loop+"次");
		for(int i = 1; i<=loop;i++){
			LeUiObject camera = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/menu_camera"));
			camera.clickAndWaitForNewWindow(5000);
			LeUiObject shutter = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
							"com.android.camera2:id/shutter_button"));
			verify("拍照的按钮不存在",shutter.exists());
			press_back(1);
			
		}
		
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	@CaseName("单拍50张相片")
	public void testTakePhoto() throws UiObjectNotFoundException {
		/*mengfengxiao@letv.com*/
		int thousond=getIntParams("Loop");
		System.out.println("Loop is "+thousond);
		
		addStep("进入图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(4);
		LeUiObject albums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums"));
		verify("没有成功进入图库",albums.exists());
		sleepInt(2);
				
        addStep("启动相机，拍照后返回，重复"+thousond+"次");
		for(int i=1;i<=thousond;i++){
			System.out.println("------第 "+i+" 次--------");
			LeUiObject camera = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/menu_camera"));
			camera.clickAndWaitForNewWindow(5000);
			LeUiObject shutter = new LeUiObject(new UiSelector().className(
					"android.widget.ImageView").resourceId(
							"com.android.camera2:id/shutter_button"));
			verify("拍照的按钮不存在",shutter.exists());
			shutter.click();
			sleepInt(5);
			press_back(1);
			
		}
		
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
		
	@CaseName("本地图库有50张图片，快速上下滑动5次")
	public void testVertiSlidePhoto() throws UiObjectNotFoundException {
		/*mengfengxiao@letv.com*/
		/*int thousond=getIntParams("Thousond");
		System.out.println("Thousond is "+thousond);*/
		
		addStep("进入图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(4);
		LeUiObject albums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums"));
		verify("没有成功进入图库",albums.exists());
		sleepInt(2);
		LeUiObject myCamera = new LeUiObject(new UiSelector().text("我的相机"));
		myCamera.click();
		sleepInt(3);
				
		addStep("快速上下滑动"+getIntParams("Loop")+"次");
		for(int i=0;i<getIntParams("Loop");i++){
			System.out.println("------第 "+i+" 次--------");
//			phone.swipe((int)(phone.getDisplayWidth()*0.275), (int)(phone.getDisplayWidth()*0.44),(int)(phone.getDisplayWidth()*0.642),(int)(phone.getDisplayWidth()*0.44),30);
			phone.swipe(553, 553, 553, 1560, 30);
			
			sleepInt(5);
			press_back(1);
			
		}
		
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	
	@CaseName("本地图库有50张图片，横屏快速左右滑动5次")
	public void testHertiSlidePhoto() throws UiObjectNotFoundException {
		
		/*int thousond=getIntParams("Thousond");
		System.out.println("Thousond is "+thousond);*/
		
		addStep("进入图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(4);
		LeUiObject albums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums"));
		verify("没有成功进入图库",albums.exists());
		sleepInt(2);
		LeUiObject myCamera = new LeUiObject(new UiSelector().text("我的相机"));
		myCamera.click();
		sleepInt(3);
		
		addStep("快速左右滑动"+getIntParams("Loop")+"次");
		for(int i=0;i<getIntParams("Loop");i++){
			System.out.println("------第 "+i+" 次--------");
			phone.swipe(136, 1000, 912, 1000, 30);
			
			sleepInt(5);
			press_back(1);
			
		}
		
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("从图库播放30秒视频 5次")
	public void testPlay30sVideo() throws UiObjectNotFoundException {
		
		/*int thousond=getIntParams("Thousond");
		System.out.println("Thousond is "+thousond);*/
		
		addStep("进入图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(4);
		LeUiObject albums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums"));
		verify("没有成功进入图库",albums.exists());
		sleepInt(2);
		LeUiObject myCamera = new LeUiObject(new UiSelector().text("我的相机"));
		myCamera.click();
		sleepInt(3);
		phone.click(143, 345);//进入视频
		
		addStep("播放30秒视频"+getIntParams("Loop")+"次");
		for(int i=0;i<getIntParams("Loop");i++){
			System.out.println("------第 "+i+" 次--------");
			phone.click(543, 945);//点击播放按钮
			sleepInt(30);			
		}
		
		addStep("退出应用");
		press_back(5);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	
	@CaseName("从图库幻灯片播放图片 1分钟")
	public void testPlayPhoto30m() throws UiObjectNotFoundException {
		
		int minute=getIntParams("Minute");
		System.out.println("Minute is "+minute);
		addStep("进入图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(4);
		/*LeUiObject albums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums"));
		verify("没有成功进入图库",albums.exists());*/
        /*mengfengxiao@letv.com*/
		LeUiObject albums = new LeUiObject(new UiSelector().resourceId("android:id/title").className("android.widget.TextView").text("本地"));
        verify("没有成功进入图库",albums.exists());
        /*mengfengxiao@letv.com*/
		LeUiObject myCamera = new LeUiObject(new UiSelector().text("我的相机"));
		myCamera.click();
		
		sleepInt(2);
		phone.click(550, 1850);	
		
		addStep("从图库幻灯片播放图片"+minute+"分钟");			
		LeUiObject ppt = new LeUiObject(new UiSelector().text("幻灯片播放"));
		ppt.click();
		sleepInt(minute*60);
	
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
	
	@CaseName("删除图片 50次")
	public void testDeletePhoto() throws UiObjectNotFoundException {
		
		/*int thousond=getIntParams("Thousond");
		System.out.println("Thousond is "+thousond);*/
		
		addStep("进入图库应用");
		launchApp(AppName.GALLERY);
		sleepInt(4);
		/*LeUiObject albums = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/lv_albums"));
		verify("没有成功进入图库",albums.exists());*/
        /*mengfengxiao@letv.com*/
        LeUiObject albums = new LeUiObject(new UiSelector().resourceId("android:id/title").className("android.widget.TextView").text("本地"));
        verify("没有成功进入图库",albums.exists());
        /*mengfengxiao@letv.com*/
		sleepInt(2);
		
		LeUiObject objAlbumCount = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/album_item_count"));
		int albumCount = Integer.parseInt(objAlbumCount.getText()); 
		verify("图库中没有待删除的"+getIntParams("Loop")+"张照片",albumCount >= getIntParams("Loop"));
		
		LeUiObject myCamera = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/album_name").text("我的相机"));
        verify("没有我的相机相册", myCamera.exists());
		myCamera.click();
        sleepInt(2);
		/*mengfengxiao@letv.com*/
        LeUiObject myCamera1 = new LeUiObject(new UiSelector().className("android.widget.TextView").text("我的相机"));
        verify("没有进入我的相机相册", myCamera1.exists());
        LeUiObject glRootView = new LeUiObject(new UiSelector().resourceId("com.android.gallery3d:id/gl_root_view"));
        int dx = glRootView.getBounds().width()/8;//point in first image
        int dy = glRootView.getBounds().height()/12+glRootView.getBounds().top;
        /*phone.click(390,322);*/
        phone.click(dx, dy);
        sleepInt(2);
        int center_x = phone.getDisplayWidth()/2;
        int center_y = phone.getDisplayHeight()/2;
        UiObject delete = new UiObject(new UiSelector().resourceId("com.android.gallery3d:id/bottom_widget"))
                .getChild(new UiSelector().className("android.widget.RelativeLayout").index(3));
        /*mengfengxiao@letv.com*/
		addStep("删除"+getIntParams("Loop")+"张图片");
		for(int i=1;i<=getIntParams("Loop");i++){
			System.out.println("------第 "+i+" 次--------");
			/*phone.click(500,1000);//图片中间，出菜单*/
            System.out.println("点击屏幕中点");
            phone.click(center_x, center_y);
            sleepInt(2);
            verify("删除按钮不存在", delete.exists());
			/*phone.click(730,1850);//删除按钮*/
            delete.click();
			sleepInt(2);
			LeUiObject delConfirm = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm"));
			verify("确认删除按钮不存在",delConfirm.exists());
            delConfirm.click();
			sleepInt(2);
			/*phone.click(600,1280);//出菜单
			LeUiObject backGallery = new LeUiObject(new UiSelector().description("向上导航"));
			backGallery.click();
			sleepInt(2);		*/
		}
		
		addStep("退出应用");
		press_back(2);
		
		int albumCountAftDel = Integer.parseInt(objAlbumCount.getText()); 
		verify("删除掉的照片为"+albumCount+"-"+albumCountAftDel+"张",albumCount-albumCountAftDel==getIntParams("Loop"));
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}
}
