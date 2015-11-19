package com.letv.cases.proto.others;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.letv.cases.CaseName;
import com.letv.uf.IntentConstants;
import com.letv.uf.LetvTestCase;

public class FileManager extends LetvTestCase {
	
	private final String FOLDER_NAME = "010";

	@CaseName("文件管理器中建立文件夹与删除")
	public void testFolder() throws UiObjectNotFoundException {
		addStep("打开文件管理");
		launchAppByPackage(IntentConstants.proto_filemanager);
		removeFolder();

		for (int i = 0; i < TestCaseLoop; i++) {
			addStep("新建文件夹010");
			phone.pressKeyCode(KEY_MENU);
			sleepInt(2);
			UiObject create = new UiObject(new UiSelector().className(
					"android.widget.TextView").text("创建"));
			verify("Can't find create text view.", create.exists());
			create.click();
			UiObject newFolderTag = new UiObject(new UiSelector().className(
					"android.widget.TextView").text("文件夹"));
			verify("Can't find folder text view.", newFolderTag.exists());
			newFolderTag.click();
			sleepInt(1);

			UiObject folderNameEdit = new UiObject(
					new UiSelector().className("android.widget.EditText"));
			verify("Can't find edit text.", folderNameEdit.exists());
			folderNameEdit.setText(FOLDER_NAME);
			sleepInt(1);

			UiObject confirmBtn = new UiObject(new UiSelector().className(
					"android.widget.Button").text("确定"));
			confirmBtn.click();
			sleepInt(1);

			addStep("确认文件夹建立成功");
			UiObject OIOFolder = new UiObject(new UiSelector().resourceId(
					"com.rhmsoft.fm:id/name").text(FOLDER_NAME));
			verify("未能成功建立文件夹", OIOFolder.exists());

			addStep("删除新建的文件夹");
			removeFolder();
		}

		addStep("退出文件管理器");
		press_back(5);
	}

	private void removeFolder() throws UiObjectNotFoundException {
		UiObject OIOFolder = new UiObject(new UiSelector().resourceId(
				"com.rhmsoft.fm:id/name").text(FOLDER_NAME));
		if (OIOFolder.exists()) {
			UiObject editTag = new UiObject(new UiSelector().className(
					"android.widget.Button").text("多选"));
			editTag.click();
			sleepInt(1);
			OIOFolder.click();
			sleepInt(1);
			UiObject delTag = new UiObject(new UiSelector().className(
					"android.widget.Button").text("删除"));
			delTag.click();
			sleepInt(1);
			UiObject delBtn = new UiObject(new UiSelector().resourceId(
					"com.rhmsoft.fm:id/button1").text("确定"));
			delBtn.click();
			sleepInt(1);
		}
		verify("删除文件夹失败", !OIOFolder.exists());
	}
}
