package com.letv.cases.leui.telephony;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class Contacts extends LetvTestCaseMTBF {

	private final String contactName = "123";
	private final String contactNumber = "13800138000";
	
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


	// add contact
	private void addContact() throws UiObjectNotFoundException {
		LeUiObject add1 = new LeUiObject(new UiSelector().className(
				"android.widget.Button").text("新建联系人"));
		add1.click();
		sleepInt(2);
		LeUiObject confirm = new LeUiObject(new UiSelector().text("确定").resourceId(
				"com.android.contacts:id/right_button"));
		if (confirm.exists()) {
			confirm.click();
			sleepInt(1);
		}
		// fill name field
		LeUiObject name = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("姓名"));
		verify("Can't find name field.", name.exists());
		name.setText(contactName);
		sleepInt(2);
//		// fill number field
		LeUiObject number = new LeUiObject(new UiSelector().className(
				"android.widget.EditText").text("电话"));
		verify("Can't find number field.", number.exists());
		number.setText(contactNumber);
		sleepInt(2);
		// click complete button
		LeUiObject complete = new LeUiObject(new UiSelector().resourceId("com.android.contacts:id/menudone"));
		verify("Can't find complete button.", complete.exists());
		complete.click();
		sleepInt(3);
		LeUiObject Notnow = new LeUiObject(
				new UiSelector().className("android.widget.Button").text("暂不"));	
		// confirm add contact success
		if(Notnow.exists()){
			Notnow.click();
			sleepInt(4);
		}
		// confirm add contact success
		addStep("确认联系人新建成功");
		LeUiObject detail = new LeUiObject(new UiSelector().resourceId("com.android.contacts:id/card_container"));
		verify("Add contact failed.", detail.exists());
		// phone.pressBack();
	}

	// delete contact
	private void deleteContact() throws UiObjectNotFoundException {
		LeUiObject delete = new LeUiObject(new UiSelector().textContains("删除").resourceId(
				"android:id/title"));
		verify("Can't find delete button.", delete.exists());
		delete.click();
		sleepInt(1);
		
		LeUiObject deletecontact = new LeUiObject(new UiSelector().textContains("删除"));
		verify("Can't find delete confirm button.", deletecontact.exists());
		deletecontact.click();
		sleepInt(4);
    	verify("删除失败",!deletecontact.exists());
		// confirm delete success
	/*	addStep("确认联系人删除成功");
		// UiObject empty = new LeUiObject(
		//		new UiSelector().resourceId("android:id/empty"));
		LeUiObject list = new LeUiObject(new UiSelector().className(
				"android.widget.ListView").resourceId("android:id/list"));
		LeUiObject view = list.getChild(new UiSelector()
		.className("android.view.View").index(2));
		verify("delete contact failed.", !view.exists());*/
	}

	// clear all the contacts
	private void clearContacts() throws UiObjectNotFoundException {
		/*LeUiObject list = new LeUiObject(new UiSelector().className(
				"android.view.View").index(2));
		if (list.exists()) {
			UiObject view = list.getChild(new UiSelector()
					.className("android.view.View").index(2));
			LeUiObject delete = new LeUiObject(new UiSelector().textContains("删除")
					.resourceId("android:id/title"));
			LeUiObject deletecontact = new LeUiObject(new UiSelector().text("删除联系人"));
//			LeUiObject confirm = new LeUiObject(new UiSelector().text("确定"));			
			
			for (int i = 0; i < 20; i++) {
				if (view.exists()) {
					view.click();
					sleepInt(1);
					if (delete.exists()) {
						delete.click();
						if (deletecontact.exists()) {
							deletecontact.click();
							sleepInt(1);
							if(delete.exists())
								delete.click();
							sleepInt(1);
						}
					} else {
						continue;
					}
				} else {
					break;
				}
			}
		}*/
        /*mengfengxiao@letv.com*/
        LeUiObject list = new LeUiObject(new UiSelector().className("android.widget.ListView").resourceId("android:id/list"));
        verify("联系人列表不存在", list.exists());
        UiObject view = list.getChild(new UiSelector().className("com.letv.leui.widget.LeQuickContactBadge").instance(1));
        verify("联系人item不存在", view.exists());
        while (view.exists()) {
            view.click();
            sleepInt(1);
            deleteContact();
        }
         /*mengfengxiao@letv.com*/
	}

	@CaseName("添加删除联系人")
	public void testAddDelContacts() throws UiObjectNotFoundException {
		addStep("打开通讯录");
		launchApp(AppName.PHONE);
		// find contact image button
		sleepInt(2);
		/*LeUiObject contact = new LeUiObject(new UiSelector()
				.packageName("com.android.dialer")
				.className("android.widget.TextView")
				.text("联系人"));
		verify("Can't find contact image button.", contact.exists());
		contact.click();
		sleepInt(1);
		addStep("清空联系人");
		LeUiObject list = new LeUiObject(new UiSelector().className(
				"android.view.View").index(2));
		if(list.exists()){
		clearContacts();
		}
		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("新建联系人");
			addContact();
			addStep("删除联系人");
			deleteContact();
		}
		addStep("退出通讯录");
		press_back(5);*/
        /*mengfengxiao@letv.com*/
        LeUiObject contact = new LeUiObject(new UiSelector()
                .packageName("com.android.dialer")
                .className("android.widget.TextView")
                .text("联系人"));
        verify("Can't find contact image button.", contact.exists());
        contact.click();
        sleepInt(1);
        LeUiObject list = new LeUiObject(new UiSelector().className("android.widget.ListView").resourceId("android:id/list"));
        LeUiObject non = new LeUiObject(new UiSelector().resourceId("android:id/message").textContains("没有"));
        UiObject view = list.getChild(new UiSelector().className("com.letv.leui.widget.LeQuickContactBadge").instance(1));
        if(view.exists()) {
            addStep("清空联系人");
            clearContacts();
            verify("clear all contact failed", non.exists());
        }
       if (!view.exists()) {
           for (int i = 0; i < getIntParams("Loop"); i++) {
               addStep("新建联系人");
               addContact();
               addStep("删除联系人");
               deleteContact();
           }
       }
        addStep("退出通讯录");
        press_back(5);
         /*mengfengxiao@letv.com*/
	}
    /*---mengfengxiao@letv.com---2015.11.17---*/
    @CaseName("查看联系人详情")
    public void testContactDetail() throws UiObjectNotFoundException {
        addStep("打开通讯录");
        launchApp(AppName.PHONE);
        sleepInt(2);
        LeUiObject contact = new LeUiObject(new UiSelector()
                .packageName("com.android.dialer")
                .className("android.widget.TextView")
                .text("联系人"));
        verify("联系人按钮不存在", contact.exists());
        addStep("点击联系人");
        contact.click();
        sleepInt(1);
        UiObject contactTitle = new UiObject(new UiSelector().resourceId("android:id/action_bar"))
                .getChild(new UiSelector().className("android.widget.TextView").text("联系人"));
        verify("进入联系人列表失败",contactTitle.exists());
        for (int i=0; i<=getIntParams("Loop"); i++) {
            addStep("点击进入联系人详情");
            int dx = (int)(phone.getDisplayWidth()*0.5);
            int dy = (int)(phone.getDisplayHeight()*0.25);
            phone.swipe(dx, dy, dx, dy, 100);
            LeUiObject name = new LeUiObject(new UiSelector().resourceId("com.android.contacts:id/large_title"));
            verify("进入联系人详情失败", name.exists());
            addStep("返回联系人列表");
            press_back(1);
            sleepInt(1);
            verify("返回联系人列表失败",contactTitle.exists());
        }
       addStep("退出联系人");
        press_back(1);
        press_home(1);
    }
}
