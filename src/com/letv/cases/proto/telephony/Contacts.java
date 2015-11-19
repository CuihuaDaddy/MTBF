package com.letv.cases.proto.telephony;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class Contacts extends LetvTestCase {	

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		launchAppByPackage(IntentConstants.proto_contacts);
		sleepInt(3);
		super.tearDown();
		clearContacts();
		press_back(5);
	}

	// add contact
	private void addContact() throws UiObjectNotFoundException {
		// find add contact button
		UiObject add = new UiObject(new UiSelector().resourceId(
				"com.android.contacts:id/floating_action_button").description(
				"add new contact"));
		verify("Can't find add contact button.", add.exists());
		add.click();
		sleepInt(1);
		// UiObject confirm = new UiObject(new
		// UiSelector().text("确定").resourceId(
		// "com.android.contacts:id/right_button"));
		// if (confirm.exists()) {
		// confirm.click();
		// sleepInt(1);
		// }
		// fill name field
		UiObject name = new UiObject(new UiSelector().className(
				"android.widget.EditText").text("Name"));
		verify("Can't find name field.", name.waitForExists(10000));
		name.setText(getStrParams("contactName"));
		// fill number field
		UiObject number = new UiObject(new UiSelector().className(
				"android.widget.EditText").text("Phone"));
		verify("Can't find number field.", number.waitForExists(10000));
		number.setText(getStrParams("dialNum"));
		// click complete button
		UiObject complete = new UiObject(
				new UiSelector()
						.resourceId("com.android.contacts:id/save_menu_item"));
		verify("Can't find complete button.", complete.waitForExists(10000));
		complete.click();
		sleepInt(2);
		// confirm add contact success
		addStep("确认联系人新建成功");
		UiObject detail = new UiObject(
				new UiSelector()
						.resourceId("com.android.contacts:id/photo_touch_intercept_overlay"));
		verify("Add contact failed.", detail.waitForExists(10000));
		// phone.pressBack();
	}

	// delete contact
	private void deleteContact() throws UiObjectNotFoundException {
		UiObject edit = new UiObject(new UiSelector().resourceId(
				"com.android.contacts:id/menu_edit").description("Edit"));
		verify("Can't find contact edit button.", edit.exists());
		edit.click();
		sleepInt(1);
		UiObject more = new UiObject(new UiSelector().className(
				"android.widget.ImageButton").description("More options"));
		verify("Can't find more button.", more.waitForExists(10000));
		more.click();
		sleepInt(1);
		UiObject delete = new UiObject(new UiSelector().text("Delete")
				.resourceId("android:id/title"));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(1);
		UiObject confirm = new UiObject(new UiSelector().text("OK").resourceId(
				"android:id/button1"));
		verify("Can't find delete confirm button.", confirm.exists());
		confirm.click();
		sleepInt(2);
		// confirm delete success
		addStep("确认联系人删除成功");
		UiObject empty = new UiObject(new UiSelector().resourceId(
				"com.android.contacts:id/message").text("No contacts."));
		verify("delete contact failed.", empty.waitForExists(5000));
	}

	// clear all the contacts
	private void clearContacts() throws UiObjectNotFoundException {
		UiObject list = new UiObject(new UiSelector().className(
				"android.widget.ListView").resourceId("android:id/list"));
		if (list.waitForExists(5000)) {
			UiObject view = list.getChild(new UiSelector()
					.className("android.view.View"));
			UiObject edit = new UiObject(new UiSelector().resourceId(
					"com.android.contacts:id/menu_edit").description("Edit"));
			UiObject more = new UiObject(new UiSelector().className(
					"android.widget.ImageButton").description("More options"));
			UiObject delete = new UiObject(new UiSelector().text("Delete")
					.resourceId("android:id/title"));
			UiObject confirm = new UiObject(new UiSelector().text("OK")
					.resourceId("android:id/button1"));
			for (int i = 0; i < 20; i++) {
				if (view.waitForExists(5000)) {
					view.click();
					sleepInt(1);
					if (edit.waitForExists(5000)) {
						edit.click();
						sleepInt(1);
						if (more.waitForExists(5000)) {
							more.click();
							sleepInt(1);
							if (delete.waitForExists(5000)) {
								delete.click();
								if (confirm.waitForExists(5000)) {
									confirm.click();
									sleepInt(1);
								}
							} else {
								System.out.println("Can't find delete button.");
								continue;
							}
						} else {
							System.out.println("Can't find more button.");
							continue;
						}
					}
				} else {
					break;
				}
			}
		}
	}

	@CaseName("添加删除联系人")
	public void testAddDelContacts() throws UiObjectNotFoundException {
		addStep("打开通讯录");
		launchAppByPackage(IntentConstants.proto_contacts);
		sleepInt(3);
		// find contact image button
		// UiObject contact = new UiObject(new UiSelector()
		// .packageName("com.android.dialer")
		// .className("android.widget.TabHost")
		// .childSelector(
		// new UiSelector().className("android.widget.ImageView")
		// .resourceId("android:id/icon").index(0)));
		// verify("Can't find contact image button.", contact.exists());
		// contact.click();
		// sleepInt(1);
		addStep("清空联系人");
		clearContacts();
		press_back(5);
		sleepInt(2);
		launchAppByPackage(IntentConstants.proto_contacts);
		sleepInt(3);
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("新建联系人");
			addContact();
			addStep("删除联系人");
			deleteContact();
		}
		addStep("退出通讯录");
		press_back(5);
	}
}
