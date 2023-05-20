package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import genericLibraries.TabNames;

public class CreateContactsTest extends BaseClass{

	@Test
	public void createContactTest() {
		SoftAssert soft = new SoftAssert();
		
		home.clickRequiredTab(TabNames.CONTACTS, web);
		contact.clickPlusButton();
		
		soft.assertTrue(createContact.getPageHeader().contains("Creating"));

		Map<String, String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact");
		String contactname = map.get("Last Name")+jutil.generateRandomNumber(100);
		createContact.setLastName(contactname);
		createContact.clickSave();
		
		soft.assertTrue(newContactInfo.getPageHeader().contains(contactname));
		if (newContactInfo.getPageHeader().contains(contactname)) {
			excel.writeDataToExcel("ContactsTestData", "Create Contact", "pass", IConstantPath.EXCEL_PATH);
		}
		else {
			excel.writeDataToExcel("ContactsTestData", "Create Contact", "fail", IConstantPath.EXCEL_PATH);
		}

		soft.assertAll();
	}
}
