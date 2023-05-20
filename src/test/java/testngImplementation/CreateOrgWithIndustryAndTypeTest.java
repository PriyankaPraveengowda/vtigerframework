package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;
import genericLibraries.TabNames;

public class CreateOrgWithIndustryAndTypeTest extends BaseClass {
	@Test
	public void createOrgWithIndustryAndTypeTest()  {
		
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(TabNames.ORGANIZATIONS, web);
		org.clickPlusButton();
		
		Map<String, String> map = excel.getDataFromExcel("OrganizationsTestData", "Create Organization With Industry And Type");
		String orgName = map.get("Organization Name") + jutil.generateRandomNumber(100);
		String industry = map.get("Industry");
		String type = map.get("Type");
		createOrg.setOrgName(orgName);
		createOrg.selectIndustry(web, industry);
		createOrg.selectType(web, type);
		createOrg.clickSaveButton();

		soft.assertTrue(newOrgInfo.getPageHeader().contains(orgName));
		if (newOrgInfo.getPageHeader().contains(orgName)) {
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "pass", IConstantPath.EXCEL_PATH);
		} else {
			excel.writeDataToExcel("OrganizationsTestData", "Create Organization With Industry And Type", "fail", IConstantPath.EXCEL_PATH);
		}
		soft.assertAll();
	}

}
