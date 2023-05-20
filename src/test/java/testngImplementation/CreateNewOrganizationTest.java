package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import genericLibraries.TabNames;

public class CreateNewOrganizationTest extends BaseClass {
	@Test
	public void createOrganizationTest() {
		SoftAssert soft = new SoftAssert();
		
		home.clickRequiredTab(TabNames.ORGANIZATIONS, web);
		org.clickPlusButton();
		Map<String,String> map= excel.getDataFromExcel("OrganizationsTestData", "Create Organization");
		String orgName = map.get("Organization Name")+ jutil.generateRandomNumber(100);
		createOrg.setOrgName(orgName);
		createOrg.clickSaveButton();
		
		soft.assertTrue(newOrgInfo.getPageHeader().contains(orgName));
		if (newOrgInfo.getPageHeader().contains(orgName)) {
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization", "pass", IConstantPath.EXCEL_PATH);
		}
		else {
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization", "fail", IConstantPath.EXCEL_PATH);
		}
		
		soft.assertAll();
	}
}
