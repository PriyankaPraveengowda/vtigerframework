package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import genericLibraries.TabNames;

public class CreateContactWithExistingOrgInfoTest extends BaseClass {
	@Test
	public void createContactWithOrgTest() {
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(TabNames.CONTACTS, web);
		contact.clickPlusButton();

		Map<String, String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact With Organization");
		String lastname = map.get("Last Name") + jutil.generateRandomNumber(100);
		createContact.setLastName(lastname);
		createContact.selectExistingOrganization(web, "polm1");
		createContact.clickSave();

		soft.assertTrue(newContactInfo.getPageHeader().contains(lastname));
		if (newContactInfo.getPageHeader().contains(lastname)) {
			excel.writeDataToExcel("ContactsTestData", "Create Contact With Organization", "pass",
					IConstantPath.EXCEL_PATH);
		} 
		else {
			excel.writeDataToExcel("ContactsTestData", "Create Contact With Organization", "pass",
					IConstantPath.EXCEL_PATH);
		}
		
		soft.assertAll();
	}
}
