package com.letv.cases.leui.others;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.AppName;
import com.letv.uf.LeUiObject;
import com.letv.uf.LetvTestCaseMTBF;

public class FileManager extends LetvTestCaseMTBF {
	private final String FOLDER_NAME = "010";
	
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

	@CaseName("文件管理器中建立文件夹与删除")
	public void testFolder() throws UiObjectNotFoundException {
		addStep("打开文件管理");
		launchApp(AppName.FILEMANAGER);
		sleepInt(2);
		LeUiObject PhoneMemory = new LeUiObject(new UiSelector().resourceId(
				"com.letv.android.filemanager:id/mobile_storage_name")
				.text("手机存储"));
		verify("Can't find Phone Memory.", PhoneMemory.exists());
		PhoneMemory.click();
		sleepInt(2);
		removeFolder();

		for (int i = 0; i < getIntParams("Loop"); i++) {
			addStep("新建文件夹010");
			LeUiObject newFolderTag = new LeUiObject(new UiSelector().resourceId("com.letv.android.filemanager:id/category_mkdir_menu").className("android.widget.TextView"));
			newFolderTag.click();
			sleepInt(1);

			LeUiObject folderNameEdit = new LeUiObject(
					new UiSelector().className("android.widget.EditText"));
			folderNameEdit.setText(FOLDER_NAME);
			sleepInt(1);

			LeUiObject confirmBtn = new LeUiObject(new UiSelector().className("android.widget.Button").resourceId("android:id/button1").text("新建"));
			confirmBtn.click();
			sleepInt(1);

			addStep("确认文件夹建立成功");
			LeUiObject OIOFolder = new LeUiObject(
					new UiSelector()
							.className("android.widget.TextView")
							.resourceId(
									"com.letv.android.filemanager:id/navigation_view_item_name")
							.text(FOLDER_NAME));
			verify("未能成功建立文件夹", OIOFolder.exists());

			addStep("删除新建的文件夹");
			removeFolder();
		}

		addStep("退出文件管理器");
		press_back(3);
		press_home(1);
		verify("未能返回桌面", phone.getCurrentPackageName().equals(PACKAGE_HOME));
	}

	private void removeFolder() throws UiObjectNotFoundException {
		LeUiObject OIOFolder = new LeUiObject(
				new UiSelector()
						.className("android.widget.TextView")
						.resourceId(
								"com.letv.android.filemanager:id/navigation_view_item_name")
						.text(FOLDER_NAME));
		if (OIOFolder.exists()) {
			int dx = OIOFolder.getBounds().centerX();
			int dy = OIOFolder.getBounds().centerY();
			phone.swipe(dx, dy, dx, dy, 100);
			sleepInt(2);
			LeUiObject delete = new LeUiObject(new UiSelector().className("android.widget.RelativeLayout").index(3));
			verify("Can't find delete button.", delete.exists());
			delete.click();
			sleepInt(1);
			LeUiObject confirm = new LeUiObject(new UiSelector().resourceId("android:id/le_bottomsheet_default_confirm").index(2));
			verify("Can't find confirm button.", confirm.exists());
			confirm.click();
		}
		verify("删除文件夹失败", !OIOFolder.exists());
	}
}
