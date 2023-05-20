package pomImplementaion;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.TabNames;
import genericLibraries.WebDriverUtility;
import pomPAGES.CreateOrganizationPage;
import pomPAGES.HomePage;
import pomPAGES.LoginPage;
import pomPAGES.OrganizationInfoPage;
import pomPAGES.OrganizationsPage;

public class CreateNewOrganizationTest {
	public static void main(String[] args) throws InterruptedException 
	{
		PropertiesUtility property = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility webUtil = new WebDriverUtility();
		
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		String browser = property.fetchDataFromProperties("browser");
		String url = property.fetchDataFromProperties("url");
		long time = Long.parseLong(property.fetchDataFromProperties("timeouts"));
		
		WebDriver driver = webUtil.openApplication(browser,  url, time);
		HomePage home = new HomePage(driver);
		LoginPage login = new LoginPage(driver);
		OrganizationsPage org = new OrganizationsPage(driver);
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		CreateOrganizationPage createOrg = new CreateOrganizationPage(driver);
		
		if (driver.getTitle().contains("vtiger")) {
			System.out.println("LoginPage is displayed");
		}
		else {
			System.out.println("LoginPage Not found");
		}
		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username, password);
		
		if (driver.getTitle().contains("vtiger")) {
			System.out.println("HomePage is displayed");
		}
		else {
			System.out.println("HomePage Not found");
		}
		
		home.clickRequiredTab(TabNames.ORGANIZATIONS, webUtil);
		if (org.getPageHeader().contains("Organizations")) {
			System.out.println("Organizations page is displayed");
		}
		else {
			System.out.println("Organizations page not found");
		}
		
		org.clickPlusButton();
		if (createOrg.getPageHeader().contains("Creating New Organization")) {
			System.out.println("Creating New Organization page is displayed");
		}
		else {
			System.out.println("Creating new Organization page not found");
		}
		
		Map<String,String> map= excel.getDataFromExcel("OrganizationsTestData", "Create Organization");
		String orgName = map.get("Organization Name")+ jutil.generateRandomNumber(100);
		createOrg.setOrgName(orgName);
		createOrg.clickSaveButton();
		
		if (orgInfo.getPageHeader().contains(orgName)) {
			System.out.println(" Organization Information page is displayed ");
		}
		else {
			System.out.println("Organization Information page not found");
		}	

		home.signOutOfApp(webUtil);
		webUtil.closeAllWindows();
		excel.closeExcel();
	}
}
