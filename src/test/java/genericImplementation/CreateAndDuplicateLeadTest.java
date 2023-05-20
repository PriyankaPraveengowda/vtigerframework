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
		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:8888/");
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//		Thread.sleep(2000);
//		
		WebElement loginHeader = driver.findElement(By.xpath("//a[.='vtiger']"));
		if (loginHeader.getText().equals("vtiger"))
		{
			System.out.println("LoginPage is displayed");
		}
		else
		{
			System.out.println("LoginPage Not found");
		}
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).submit();
		
		WebElement homeHeader = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (homeHeader.getText().contains("Home"))
		{
			System.out.println("HomePage is displayed");
		}
		else
		{
			System.out.println("HomePage Not found");
		}
		
		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'Leads&action')]")).click();
		WebElement leadHeader = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (leadHeader.getText().contains("Leads"))
		{
			System.out.println("LeadPage is displayed");
		}
		else
		{
			System.out.println("LeadPage Not found");
		}
		
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
		WebElement createLeadPage = driver.findElement(By.xpath("//span[text()='Creating New Lead']"));
		if (createLeadPage.getText().contains("Creating New Lead"))
		{
			System.out.println("Create LeadPage is displayed");
		}
		else
		{
			System.out.println("Create LeadPage Not found");
		}
		
		Map<String, String> map = excel.getDataFromExcel("LeadsTestData", "Create and Duplicate Lead");
//		Random random = new Random();
//		String lastName = "pqr" + Random.nextInt(100);
		
		String lastName = map.get("Last Name")+jutil.generateRandomNumber(100);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		String company = map.get("Company")+jutil.generateRandomNumber(100);
		driver.findElement(By.name("company")).sendKeys(company);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		WebElement newLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (newLeadInfoHeader.isDisplayed())
		{
			System.out.println("New lead created Successfully");
		}
		else
		{
			System.out.println("New lead not created");
		}
		
		driver.findElement(By.name("Duplicate")).click();
		WebElement duplicatingHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (duplicatingHeader.getText().contains("Duplicating"))
		{
			System.out.println("Duplicating page is displayed");
		}
		else
		{
			System.out.println("Duplicating page is not found");
		}
		
		String newLastName =map.get("New Last Name")+jutil.generateRandomNumber(100);
		WebElement lastNameTF = driver.findElement(By.name("lastname"));
		lastNameTF.clear();
		lastNameTF.sendKeys(newLastName);
		driver.findElement(By.xpath("//input[contains(@value,'  Save  ')]")).click();
		WebElement duplicatedLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (duplicatedLeadInfoHeader.getText().contains(newLastName))
		{
			System.out.println("Duplicated Lead Information page is opened");
		}
		else
		{
			System.out.println("Duplicated Lead Information page is not found");
		}
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions a = new Actions(driver);
//		a.moveToElement(adminIcon).perform();
		webUtil.mouseHover(adminIcon);
//		WebElement signOutIcon = driver.findElement(By.xpath("//a[text()='Sign Out']"));
//		webUtil.mouseHover(signOutIcon);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		excel.closeExcel();
	}
	
}
