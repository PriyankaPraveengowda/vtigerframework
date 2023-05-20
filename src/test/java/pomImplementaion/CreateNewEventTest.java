package pomImplementaion;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;
import pomPAGES.CreateToDoPage;
import pomPAGES.EventInfoPage;
import pomPAGES.HomePage;
import pomPAGES.LoginPage;

public class CreateNewEventTest {
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
		CreateToDoPage createEvent = new CreateToDoPage(driver);
		EventInfoPage eventInfo = new EventInfoPage(driver);
		
		if (driver.getTitle().contains("vtiger"))
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
		
		if (driver.getTitle().contains("vtiger"))
		{
			System.out.println("HomePage is displayed");
		}
		else
		{
			System.out.println("HomePage Not found");
		}
		
		home.selectFromQuickCreate(web, "Events");
		if(createEvent.getPageHeader().contains("Create To Do")) 
		{
			System.out.println(" create new event page is displayed ");
		}
		else
		{
			System.out.println("Create new Event page is not found");
		}
		
		Map<String,String> map = excel.getDataFromExcel("EventsTestData", "Create New Event");
		String subject = map.get("Subject")+jutil.generateRandomNumber(100);
		createEvent.setSubjectTF(subject);
		
		createEvent.clickStartDatePicker();
		String startDate = map.get("Start Date");
		createEvent.datePicker(startDate, web);
		
		createEvent.clickDueDatePicker();
		String dueDate = map.get("Due Date");
		createEvent.datePicker(dueDate, web);
		createEvent.clickSaveButton();

		if (eventInfo.getPageHeader().contains(subject))
		{
			System.out.println("Event Information page is displayed ");
		}
		else {
			System.out.println("Event Information page is not found");
		}

		home.signOutOfApp(web);
		Thread.sleep(2000);
		web.closeAllWindows();
		excel.closeExcel();
	}

}
