package pomImplementaion;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.TabNames;
import genericLibraries.WebDriverUtility;
import pomPAGES.CreateLeadPage;
import pomPAGES.HomePage;
import pomPAGES.LeadDuplicatingPage;
import pomPAGES.LeadInfoPage;
import pomPAGES.LeadsPage;
import pomPAGES.LoginPage;

public class CreateAndDuplicateLeadTest {
	
	public static void main(String [] args) {
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
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadsPage lead = new LeadsPage(driver);
		CreateLeadPage createLead = new CreateLeadPage(driver);
		LeadInfoPage leadInfo = new LeadInfoPage(driver);
		LeadDuplicatingPage duplicateLead = new LeadDuplicatingPage(driver);
		
		login.getPageHeader();
		if (login.getPageHeader().equals("vtiger"))
		{
			System.out.println("LoginPage is displayed");
		}
		else
		{
			System.out.println("LoginPage Not found");
		}
		
		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username,password);
		
		if (home.getPageHeader().contains("Home"))
		{
			System.out.println("HomePage is displayed");
		}
		else
		{
			System.out.println("HomePage Not found");
		}
		
		home.clickRequiredTab(TabNames.LEADS, webUtil);
		if (lead.getPageHeader().contains("Leads")){
			System.out.println("LeadPage is displayed");
		}
		else {
			System.out.println("LeadPage Not found");
		}
		
		lead.clickPlusButton();
		if (createLead.getPageHeader().contains("Creating New Lead"))
		{
			System.out.println("Create LeadPage is displayed");
		}
		else
		{
			System.out.println("Create LeadPage Not found");
		}
		
		Map<String, String> map = excel.getDataFromExcel("LeadsTestData", "Create and Duplicate Lead");
		
		String lastName = map.get("Last Name")+jutil.generateRandomNumber(100);
		createLead.setLastNameTF(lastName);
		
		String company = map.get("Company")+jutil.generateRandomNumber(100);
		createLead.setCompanyTF(company);
		createLead.clickSaveButton();
		
		if (leadInfo.getPageHeader().contains(lastName))
		{
			System.out.println("New lead created Successfully");
		}
		else
		{
			System.out.println("New lead not created");
		}
		
		leadInfo.clickDuplicateButton();
		if (duplicateLead.getPageHeader().contains("Duplicating"))
		{
			System.out.println("Duplicating page is displayed");
		}
		else
		{
			System.out.println("Duplicating page is not found");
		}
		
		String newLastName =map.get("New Last Name")+jutil.generateRandomNumber(100);
		duplicateLead.setLastnameTF(newLastName);
		duplicateLead.clickSaveButton();
		if (leadInfo.getPageHeader().contains(newLastName))
		{
			System.out.println("Duplicated Lead Information page is opened");
		}
		else
		{
			System.out.println("Duplicated Lead Information page is not found");
		}
		
		home.signOutOfApp(webUtil);
		webUtil.closeAllWindows();
		excel.closeExcel();
	}
	
}
