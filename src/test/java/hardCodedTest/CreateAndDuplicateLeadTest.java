package hardCodedTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAndDuplicateLeadTest {
	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		WebElement loginHeader = driver.findElement(By.xpath("//a[.='vtiger']"));
		if (loginHeader.getText().equals("vtiger"))
			System.out.println("LoginPage is displayed");
		else
			System.out.println("LoginPage Not found");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(2000);
		
		WebElement homeHeader = driver.findElement(By.xpath("//td[@class=\"moduleName\"]"));
		if (homeHeader.getText().contains("Home"))
			System.out.println("HomePage is displayed");
		else
			System.out.println("HomePage Not found");
		
		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'Leads&action')]")).click();
		Thread.sleep(2000);
		
		WebElement leadHeader = driver.findElement(By.xpath("//a[@class='hdrLink']"));
		if (leadHeader.getText().contains("Leads"))
			System.out.println("LeadPage is displayed");
		else
			System.out.println("LeadPage Not found");
		
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		WebElement createLeadHeader = driver.findElement(By.xpath("//span[text()='Creating New Lead']"));
		if (createLeadHeader.getText().contains("Creating New Lead"))
			System.out.println("Create LeadPage is displayed");
		else
			System.out.println("Create LeadPage Not found");
		
		Random random = new Random();
		String lastName = "pqr" + random.nextInt(100);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		String company = "DCT" + random.nextInt(100);
		driver.findElement(By.name("company")).sendKeys(company);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		WebElement newLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (newLeadInfoHeader.getText().contains(lastName))
			System.out.println("New lead created Successfully");
		else
			System.out.println("New lead not created");
		
		driver.findElement(By.name("Duplicate")).click();
		
		WebElement duplicatingHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (duplicatingHeader.getText().contains("Duplicating"))
			System.out.println("Duplicating page is displayed");
		else
			System.out.println("Duplicating page is not found");
		
		String newLastName ="Mno"+random.nextInt(100);
		WebElement lastNameTF = driver.findElement(By.name("lastname"));
		lastNameTF.clear();
		lastNameTF.sendKeys(newLastName);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		WebElement duplicateLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (duplicateLeadInfoHeader.getText().contains(newLastName))
			System.out.println("Duplicated Lead Information page is opened ");
		else
			System.out.println("Duplicated Lead Information page is not found");
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(adminIcon).perform();
		WebElement signOutIcon = driver.findElement(By.xpath("//a[text()='Sign Out']"));
		a.moveToElement(signOutIcon).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		Thread.sleep(2000);
		driver.quit();
	}

}
