package genericImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;

public class CreateOrgWithIndustryAndTypeTest {
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
			System.out.println("HomePage is displayed");
		}
		else
		{
			System.out.println("HomePage Not found");
		}
		
		driver.findElement(By.xpath("//a[contains(@href,'Accounts&action')]")).click();
		WebElement organizationsPage = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (organizationsPage.isDisplayed())
		{
			System.out.println("Organizations page is displayed");
		}
		else
		{
			System.out.println("Organizations page not found");
		}
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		WebElement creatingNewOrgPage = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (creatingNewOrgPage.isDisplayed())
		{
			System.out.println("Creating New Organization page is displayed");
		}
		else
		{
			System.out.println("Creating new Organization page not found");
		}
		
		Map<String,String> map = excel.getDataFromExcel("OrganizationsTestData", "Create Organization With Industry And Type");
		String orgName =map.get("Organization Name")+jutil.generateRandomNumber(100);
//		Random random = new Random();
//		String orgName ="DCT"+ random.nextInt(100);
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement industry = driver.findElement(By.name("industry"));
		industry.click();
		web.dropDown(industry, "Consulting");
//		Select s= new Select(industry);
//		s.selectByValue("Consulting");
		
		WebElement type = driver.findElement(By.name("accounttype"));
		type.click();
		web.dropDown(type, "Investor");
//		Select e= new Select(type);
//		e.selectByValue("Investor");
		
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		WebElement OrganizationInfoPage = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (OrganizationInfoPage.isDisplayed())
		{
			System.out.println(" Organization Information page is displayed showing : "+OrganizationInfoPage.getText());
		}
		else
		{
			System.out.println("Organization Information page not found");
		}	

		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		web.mouseHover(adminIcon);
//		Actions a= new Actions(driver);
//		a.moveToElement(adminIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		Thread.sleep(2000);
//		driver.quit();
		web.closeAllWindows();
		excel.closeExcel();
	}

}
