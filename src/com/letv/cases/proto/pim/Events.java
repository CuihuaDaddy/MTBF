package com.letv.cases.proto.pim;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class Events extends LetvTestCase {


	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	private void clearAllEvents() throws UiObjectNotFoundException {
		// launchAppByPackage(IntentConstants.calendar);
		// UiObject event = new UiObject(new UiSelector()
		// .className("android.widget.Button")
		// .resourceId("com.android.calendar:id/ib_agenda").text("事件"));
		// verify("Can't find event button.", event.exists());
		// event.click();
		UiObject event_item = new UiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/event_item_rl"));
		while (event_item.exists()) {
			event_item.click();
			sleepInt(2);
			UiObject delete = new UiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/delete_event"));
			verify("Can't find delete button.", delete.exists());
			delete.click();
			UiObject confirm = new UiObject(new UiSelector().resourceId(
					"android:id/button1").text("确定"));
			verify("Can't find confirm button.", confirm.exists());
			confirm.click();
			sleepInt(2);
		}
	}

	private void verifyAdd(int count) throws UiObjectNotFoundException {
		UiObject event = new UiObject(new UiSelector()
				.className("android.widget.Button")
				.resourceId("com.android.calendar:id/ib_agenda").text("事件"));
		verify("Can't find event button.", event.exists());
		event.click();
		UiObject event_item = new UiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/event_item_rl"));
		verify("Can't add event loop " + count, event_item.exists());
	}

	private void verifyDel(int count) throws UiObjectNotFoundException {
		UiObject event_item = new UiObject(
				new UiSelector()
						.resourceId("com.android.calendar:id/event_item_rl"));
		verify("Can't delete event loop " + count, !event_item.exists());
	}

	@CaseName("事件的添加和删除")
	public void testEventsAddDel() throws UiObjectNotFoundException {
		addStep("打开日历");
		launchAppByPackage(IntentConstants.calendar);
		addStep("清除所有事件");
		UiObject event = new UiObject(new UiSelector()
				.className("android.widget.Button")
				.resourceId("com.android.calendar:id/ib_agenda").text("事件"));
		verify("Can't find event button.", event.exists());
		event.click();
		clearAllEvents();
		press_back(1);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("编辑一事件");
			// UiObject title = new UiObject(
			// new UiSelector()
			// .resourceId("com.android.calendar:id/title"));
			// verify("Can't find title field.", title.exists());
			// title.setText("010");
			UiObject add = new UiObject(
					new UiSelector()
							.resourceId("com.android.calendar:id/action_create_event"));
			verify("Can't find add button.", add.exists());
			add.click();
			// callShell("input tap 800 200");
			sleepInt(2);
			callShell("input text 010");
			// sleepInt(5);
			callShell("input tap 980 145");
			// UiObject complete = new UiObject(new UiSelector().text("完成"));
			// verify("Can't find complete button.", complete.exists());
			// complete.click();
			addStep("确认事件添加成功");
			verifyAdd(i + 1);
			addStep("删除事件");
			clearAllEvents();
			press_back(1);
			addStep("确认事件删除成功");
			verifyDel(i + 1);
			sleepInt(2);
		}
		addStep("退出日历");
		press_back(5);
	}

}
