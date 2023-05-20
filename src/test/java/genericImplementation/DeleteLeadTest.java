package genericImplementation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import genericLibraries.ExcelUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertiesUtility;
import genericLibraries.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteLeadTest {
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
		
		driver.findElement(By.xpath("//a[text()='Leads' and contains(@href,'Leads&action')]")).click();
		Thread.sleep(2000);
		
		WebElement checkbox = driver.findElement(By.xpath("//tr[3]/td[1]/input[@type='checkbox']"));
		checkbox.click();
		
		if (checkbox.isSelected())
		{
			System.out.println("checkbox is selected");
		}
		else
		{
			System.out.println("checkbox not selected");
		}
		
		driver.findElement(By.xpath("//input[@class='crmbutton small delete']")).click();
		
		web.handleAlert("OK");
//		Alert al = driver.switchTo().alert();
//		System.out.println(al.getText());
//		al.accept();
		
		WebElement adminIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		web.mouseHover(adminIcon);
//		Actions a = new Actions(driver);
//		a.moveToElement(adminIcon).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		web.closeAllWindows();
//		driver.quit();
		excel.closeExcel();
	}

}
