package genericImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;

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
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:8888/");
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		Thread.sleep(2000);
		
		WebElement loginPage = driver.findElement(By.xpath("//a[.='vtiger']"));
		if (driver.getTitle().contains("vtiger"))
		{
			System.out.println("LoginPage is displayed");
		}
		else
		{
			System.out.println("LoginPage Not found");
		}
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(2000);
		
		WebElement homePage = driver.findElement(By.xpath("//td[@class=\"moduleName\"]"));
		if (homePage.isDisplayed())
		{
			System.out.println("HomePage is displayed");
		}
		else
		{
			System.out.println("HomePage Not found");
		}
		
		WebElement quickCreateDD = driver.findElement(By.id("qccombo"));
		quickCreateDD.click();
		web.dropDown(quickCreateDD, "Events");
//		Select s= new Select(quickCreateDD);
//		s.selectByValue("Events");
		WebElement createEventPage = driver.findElement(By.xpath("//td[@class='mailSubHeader']"));
		if(createEventPage.isDisplayed()) 
		{
			System.out.println(" create new event page is displayed showing : "+createEventPage.getText());
		}
		else
		{
			System.out.println("Create new Event page is not found");
		}
		
		Map<String,String> map = excel.getDataFromExcel("EventsTestData", "Create New Event");
		String subject = map.get("Subject")+jutil.generateRandomNumber(100);
//		Random random = new Random();
//		String subject = "meeting"+random.nextInt(100);
		driver.findElement(By.name("subject")).sendKeys(subject);
		driver.findElement(By.name("date_start")).sendKeys("2025-10-18");
		driver.findElement(By.name("due_date")).sendKeys("2025-11-26");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		WebElement EventInfoPage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (EventInfoPage.isDisplayed())
		{
			System.out.println("Event Information page is displayed showing : "+EventInfoPage.getText());
		}
		else
		{
			System.out.println("Event Information page is not found");
		}

		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions a = new Actions(driver);
//		a.moveToElement(adminIcon).perform();
		web.mouseHover(adminIcon);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		Thread.sleep(2000);
//		driver.quit();
		web.closeAllWindows();
		excel.closeExcel();
	}

}
