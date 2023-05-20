package hardCodedTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewContact {
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
		
		Random random = new Random();
		String name = "ghj"+random.nextInt(100);
		driver.findElement(By.name("lastname")).sendKeys(name);
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
