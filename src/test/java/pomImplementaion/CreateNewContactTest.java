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
import pomPAGES.ContactInfoPage;
import pomPAGES.ContactsPage;
import pomPAGES.CreateContactPage;
import pomPAGES.HomePage;
import pomPAGES.LoginPage;

public class CreateNewContactTest {
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
		ContactsPage contact = new ContactsPage(driver);
		CreateContactPage createContact = new CreateContactPage(driver);
		ContactInfoPage contactInfo = new ContactInfoPage(driver);
		
		
		if (driver.getTitle().contains("Vtiger"))
		{
			System.out.println("LoginPage is displayed");
		}
		else
		{
			System.out.println("LoginPage Not found");
		}
		
		String username = property.fetchDataFromProperties("username");
		String password = property.fetchDataFromProperties("password");
		login.loginToApp(username, password);
		
		if (driver.getTitle().contains("Home"))
		{
			System.out.println("HomePage is displayed ");
		}
		else
		{
			System.out.println("HomePage Not found");
		}
		
		home.clickRequiredTab(TabNames.CONTACTS, web);
		
		if (contact.getPageHeader().contains("Contacts"))
		{
			System.out.println("Contacts page is displayed");
		}
		else
		{
			System.out.println("Contacts page not found");
		}
		
		contact.clickPlusButton();
		if (createContact.getPageHeader().contains("Creating New Contact"))
		{
			System.out.println("Creating new Contact page is displayed");
		}
		else
		{
			System.out.println("CReateing new Contact page not found");
		}
		
		Map<String, String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact");
		String lastname = map.get("Last Name")+jutil.generateRandomNumber(100);
		createContact.setLastName(lastname);
		createContact.clickSave();
		
		if (contactInfo.getPageHeader().contains(lastname))
		{
			System.out.println("Contact Information page is displayed");
		}
		else
		{
			System.out.println("Contact Information page not found");
		}
		
		home.signOutOfApp(web);
		web.closeAllWindows();
		excel.closeExcel();
	}

}
