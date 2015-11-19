package com.letv.cases.leui.pim;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Alarm extends LetvTestCaseMTBF {
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

	@CaseName("闹铃的添加和删除")
	public void testAlarm() throws UiObjectNotFoundException {
		addStep("打开闹钟");
		launchApp(AppName.CLOCK);
		sleepInt(1);
        /*mengfengxiao@letv.com*/
		/*LeUiObject alarmTag = new LeUiObject(new UiSelector().text("闹钟"));
		alarmTag.click();*/
		sleepInt(2);
		LeUiObject emptyTag = new LeUiObject(new UiSelector().text("无闹钟"));
		if (!emptyTag.exists()) {
			addStep("清空所有闹钟");
			clearAllAlarms();
		}
        sleepInt(1);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("新建闹钟");
			LeUiObject addAlarm = new LeUiObject(new UiSelector().resourceId("com.android.deskclock:id/alarm_menu_add"));
			addAlarm.click();
			sleepInt(1);
			LeUiObject saveAlarm = new LeUiObject(new UiSelector().resourceId("com.android.deskclock:id/alarm_setting_menu_ok"));
			saveAlarm.clickAndWaitForNewWindow();
			sleepInt(3);
            /*mengfengxiao@letv.com*/
            addStep("确认闹钟是否添加成功并删除闹钟");
			clearAllAlarms();
            /*mengfengxiao@letv.com*/
		}
		addStep("退出应用");
		press_back(1);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	private void clearAllAlarms() throws UiObjectNotFoundException {
		sleepInt(4);
       /* UiObject actionbar = new UiObject(new UiSelector().resourceId("android:id/action_bar")).getChild(new UiSelector().className("android.widget.LinearLayout"));
        UiObject editListTag = actionbar.getChild(new UiSelector().className("android.widget.TextView").instance(1));*/
        /*mengfengxiao@letv.com*/
        UiObject alarmList = new UiObject(new UiSelector().className("android.widget.ListView").resourceId("com.android.deskclock:id/alarm_list"));
        verify("无闹钟列表", alarmList.exists()&& alarmList.getChildCount()>0);
        UiObject alarmitem = alarmList.getChild(new UiSelector().resourceId("com.android.deskclock:id/alarm_item").index(0));
        int dx = alarmitem.getBounds().centerX();
        int dy = alarmitem.getBounds().centerY();
        int num = alarmList.getChildCount();
        if(1==num) {
            phone.swipe(dx, dy, dx, dy, 100);
            sleepInt(1);
            phone.click((int)(phone.getDisplayWidth()*0.5), (int)(phone.getDisplayHeight()*0.97));
            sleepInt(1);
            LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm").textContains("删除"));
            verify("无确认删除按钮", confirm.exists());
            confirm.click();
            sleepInt(1);
        } else if (num>1) {
            phone.swipe(dx, dy, dx, dy, 100);
            sleepInt(1);
            LeUiObject all = new LeUiObject(new UiSelector().resourceId(
                    "com.android.deskclock:id/alarm_menu_select").textContains("全选"));
            if (all.exists()) {
                addStep("点击全选清空闹钟列表");
                all.click();
                sleepInt(1);
                phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.97));
                sleepInt(1);
                LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm").textContains("删除"));
                verify("无确认删除按钮", confirm.exists());
                confirm.click();
                sleepInt(1);
            } else {
                while (alarmitem.exists()) {
                    addStep("逐条删除,清空闹钟列表");
                    phone.swipe(dx, dy, dx, dy, 100);
                    sleepInt(1);
                    phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.97));
                    sleepInt(1);
                    LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm").textContains("删除"));
                    verify("无确认删除按钮", confirm.exists());
                    confirm.click();
                    sleepInt(1);
                }
            }
        }
        /*mengfengxiao@letv.com*/
	}
}
