package genericImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;

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
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:8888/");
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		Thread.sleep(2000);
		
		WebElement loginPage = driver.findElement(By.xpath("//a[.='vtiger']"));
		if (loginPage.isDisplayed())
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
			System.out.println("HomePage is displayed showing : "+homePage.getText());
		}
		else
		{
			System.out.println("HomePage Not found");
		}
		
		driver.findElement(By.xpath("//a[contains(@href,'Contacts&action')]")).click();
		WebElement contactUsPage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (contactUsPage.isDisplayed())
		{
			System.out.println("Contacts page is displayed showing : "+contactUsPage.getText());
		}
		else
		{
			System.out.println("Contacts page not found");
		}
		
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		WebElement createNewContactPage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (createNewContactPage.isDisplayed())
		{
			System.out.println("Creating new Contact page is displayed showing : "+createNewContactPage.getText());
		}
		else
		{
			System.out.println("CReateing new Contact page not found");
		}
		
//		Random random = new Random();
//		String name = "ghj"+random.nextInt(100);
		Map<String, String> map = excel.getDataFromExcel("ContactsTestData", "Create Contact");
		String lastname = map.get("Last Name")+jutil.generateRandomNumber(100);
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		WebElement contactInfoPage = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (contactInfoPage.isDisplayed())
		{
			System.out.println("Contact Information page is displayed showing : "+contactInfoPage.getText());
		}
		else
		{
			System.out.println("Contact Information page not found");
		}
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions a= new Actions(driver);
//		a.moveToElement(adminIcon).perform();
		web.mouseHover(adminIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
//		driver.quit();
		web.closeAllWindows();
		excel.closeExcel();
	}

}
