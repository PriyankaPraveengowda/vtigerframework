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

public class CreateNewEvent {
	public static void main(String[] args) throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Thread.sleep(2000);
		
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
		Select s= new Select(quickCreateDD);
		s.selectByValue("Events");
		WebElement createEventPage = driver.findElement(By.xpath("//td[@class='mailSubHeader']"));
		if(createEventPage.isDisplayed()) 
		{
			System.out.println(" create new event page is displayed showing : "+createEventPage.getText());
		}
		else
		{
			System.out.println("Create new Event page is not found");
		}
		
		Random random = new Random();
		String subject = "meeting"+random.nextInt(100);
		driver.findElement(By.name("subject")).sendKeys(subject);
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
		
		Actions a = new Actions(driver);
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
