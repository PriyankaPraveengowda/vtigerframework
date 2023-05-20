package testngImplementation;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.TabNames;

public class DeleteLeadTest extends BaseClass {
	@Test
	public void deleteLeadTest()
	{
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(TabNames.LEADS, web);
		soft.assertTrue(lead.getPageHeader().contains("Leads"));
		lead.clickCheckbox();
		lead.clickDeleteButton();
		web.handleAlert("OK");
		soft.assertAll();
	}

}
