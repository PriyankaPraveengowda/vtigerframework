package pomImplementaion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.TabNames;
import genericLibraries.WebDriverUtility;
import pomPAGES.HomePage;
import pomPAGES.LeadsPage;
import pomPAGES.LoginPage;

public class DeleteLeadTest {
	public static void main(String[] args) throws InterruptedException
	{
		WebDriverUtility web =  new WebDriverUtility();
		JavaUtility jutil  = new JavaUtility();
		PropertiesUtility property = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		excel.excelInit(IConstantPath.EXCEL_PATH);
		
		String browser = property.fetchDataFromProperties("browser");
		String url = property.fetchDataFromProperties("url");
		long time = Long.parseLong(property.fetchDataFromProperties("timeouts"));
		
		WebDriver driver = web.openApplication(browser, url, time);
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadsPage lead = new LeadsPage(driver);
		
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
		
		home.clickRequiredTab(TabNames.LEADS, web);
		if (lead.getPageHeader().contains("Leads")){
			System.out.println("LeadPage is displayed");
		}
		else {
			System.out.println("LeadPage Not found");
		}
		lead.clickCheckbox();
		lead.clickDeleteButton();
		web.handleAlert("OK");
		
		home.signOutOfApp(web);
		web.closeAllWindows();
		excel.closeExcel();
	}

}
