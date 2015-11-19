package com.letv.cases.leui.pim;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Events extends LetvTestCaseMTBF {

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

	private void clearAllEvents() throws UiObjectNotFoundException {
		// launchAppByPackage(IntentConstants.calendar);
		// UiObject event = new LeUiObject(new UiSelector()
		// .className("android.widget.Button")
		// .resourceId("com.android.calendar:id/ib_agenda").text("事件"));
		// verify("Can't find event button.", event.exists());
		// event.click();
		LeUiObject event_item = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/event_item_rl"));
		while (event_item.exists()) {
			event_item.click();
			sleepInt(2);
			LeUiObject delete = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/delete_event"));
			verify("Can't find delete button.", delete.exists());
			delete.click();
			sleepInt(1);
			LeUiObject confirm = new LeUiObject(new UiSelector().textContains("删除"));
			verify("Can't find confirm button.", confirm.exists());
			confirm.click();
			sleepInt(3);
		}
	}

	private void verifyAdd(int count) throws UiObjectNotFoundException {
		LeUiObject event = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("列表"));
		verify("Can't find event button.", event.exists());
		event.click();
		sleepInt(5);
		/*LeUiObject event_list = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/filterName").text("全部日程"));*/
		LeUiObject spinner = new LeUiObject(
				new UiSelector()
					.resourceId("com.android.calendar:id/custom_arrow_icon"));
		verify("can't find Spinner button",spinner.exists());
	/*	if(event_list.exists()){
			return;
		}else{*/
			spinner.click();
			sleepInt(5);
			LeUiObject ALL_event_list = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/header_footer_tv"));
			verify("can't find all event list button",ALL_event_list.exists());
			ALL_event_list.click();
			sleepInt(1);
		/*}*/
		LeUiObject event_item = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/event_item_rl"));
		verify("Can't add event loop " + count, event_item.exists());
	}

	private void verifyDel(int count) throws UiObjectNotFoundException {
		LeUiObject event = new LeUiObject(new UiSelector()
		.className("android.widget.TextView").text("列表"));
		verify("Can't find event button.", event.exists());
		event.click();
		LeUiObject event_list = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/filterName").text("全部日程"));
		LeUiObject spinner = new LeUiObject(
				new UiSelector()
					.resourceId("com.android.calendar:id/custom_arrow_icon"));
		verify("can't find Spinner button",spinner.exists());
		if(event_list.exists()){
			return;
		}else{
			spinner.click();
			sleepInt(1);
			LeUiObject ALL_event_list = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/header_footer_tv"));
			verify("can't find all event list button",ALL_event_list.exists());
			ALL_event_list.click();
			sleepInt(1);
		}
		LeUiObject event_item = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/event_item_rl"));
		verify("Can't delete event loop " + count, !event_item.exists());
	}

	@CaseName("事件的添加和删除")
	public void testEventsAddDel() throws UiObjectNotFoundException {
		addStep("打开日历");
		launchApp(AppName.CALENDAR);
		addStep("清除所有事件");
		sleepInt(2);
		LeUiObject event = new LeUiObject(new UiSelector()
				.className("android.widget.TextView").text("列表"));
		verify("Can't find event button.", event.exists());
		event.click();
		LeUiObject event_list = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/filterName").text("全部日程"));
		LeUiObject spinner = new LeUiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/custom_arrow_icon"));
		verify("can't find Spinner button",spinner.exists());
		if(!event_list.exists()){
			spinner.click();
			sleepInt(3);
			LeUiObject ALL_event_list = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/header_footer_tv"));
			verify("can't find all event list button",ALL_event_list.exists());
			ALL_event_list.click();
			sleepInt(1);
		}
		clearAllEvents();
		press_back(1);
		for (int i = 1; i < getIntParams("Loop"); i++) {
            /*mengfengxiao@letv*/
            System.out.println("----------第 " + i + " 次编辑事件----------");
			LeUiObject add = new LeUiObject(
					new UiSelector()
							.className("android.widget.TextView").text("添加"));
			verify("Can't find add button.", add.exists());
			add.click();
			sleepInt(2);
			LeUiObject eventName = new LeUiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/title").text("活动名称"));
			verify("Can't find event name item.", eventName.exists());
			eventName.click();
			sleepInt(1);
			callShell("input text 010");
			sleepInt(2);
			LeUiObject complete = new LeUiObject(new UiSelector().resourceId("com.android.calendar:id/action_done"));
			verify("Can't find complete button.", complete.exists());
			complete.click();
			sleepInt(2);
			UiObject cancel = new UiObject(new UiSelector().className("android.widget.Button").text("暂不"));
			sleepInt(2);

			if(cancel.exists()){
			cancel.click();
			sleepInt(2);
			}
			addStep("确认事件添加成功");
			verifyAdd(i + 1);
			addStep("删除事件");
			clearAllEvents();
			press_back(1);
			addStep("确认事件删除成功");
			verifyDel(i + 1);
			press_back(1);
			sleepInt(2);
		}
		addStep("退出日历");
		press_back(5);
	}

   /* ---mengfengxiao@letv.com---2015.11.17---*/
    @CaseName("日历关注广场搜索")

    public void testSubscribe() throws UiObjectNotFoundException {
        
    }
}
