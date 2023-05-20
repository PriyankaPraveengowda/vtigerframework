package hardCodedTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewOrgWithMandatoryFields {
	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(2000);
		
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
		
		Random random = new Random();
		String orgName ="DCT"+ random.nextInt(100);
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement industry = driver.findElement(By.name("industry"));
		Select s= new Select(industry);
		industry.click();
		s.selectByValue("Consulting");
		
		WebElement type = driver.findElement(By.name("accounttype"));
		Select e= new Select(type);
		type.click();
		e.selectByValue("Investor");
		
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

		Actions a= new Actions(driver);
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a.moveToElement(adminIcon).perform();
		WebElement signOutIcon = driver.findElement(By.xpath("//a[text()='Sign Out']"));
		a.moveToElement(signOutIcon).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		Thread.sleep(2000);
		driver.quit();
	}

}