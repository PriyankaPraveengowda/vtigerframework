package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import genericLibraries.TabNames;

public class CreateAndDuplicateLeadTest extends BaseClass{
	@Test
	public void createAndDuplicateLeadTest() {
		
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(TabNames.LEADS, web);
		
		soft.assertTrue(lead.getPageHeader().contains("Leads"));
		lead.clickPlusButton();
		Map<String, String> map = excel.getDataFromExcel("LeadsTestData", "Create and Duplicate Lead");
		
		soft.assertTrue(createLead.getPageHeader().contains("Creating"));
		String lastName = map.get("Last Name")+jutil.generateRandomNumber(100);
		createLead.setLastNameTF(lastName);
		String company = map.get("Company")+jutil.generateRandomNumber(100);
		createLead.setCompanyTF(company);
		createLead.clickSaveButton();
		
		soft.assertTrue(leadInfo.getPageHeader().contains(lastName));
		leadInfo.clickDuplicateButton();
		
		String newLastName =map.get("New Last Name")+jutil.generateRandomNumber(100);
		leadDuplicate.setLastnameTF(newLastName);
		leadDuplicate.clickSaveButton();
		
		soft.assertTrue(leadInfo.getPageHeader().contains("ygdsg"));
		if(leadInfo.getPageHeader().contains(newLastName)) {
			excel.writeDataToExcel("LeadsTestData", "Create and Duplicate Lead", "pass", IConstantPath.EXCEL_PATH);
		}
		else {
			excel.writeDataToExcel("LeadsTestData", "Create and Duplicate Lead", "fail", IConstantPath.EXCEL_PATH);
		}
		soft.assertAll();
	}
	
}
