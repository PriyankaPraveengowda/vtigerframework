package pomPAGES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class LeadsPage {

	@FindBy(xpath="//a[@class='hdrLink']")
	private WebElement pageHeader;
	
	@FindBy(xpath="//img[@title='Create Lead...']")
	private WebElement plusButton;
	
	@FindBy (xpath="//table[@class='lvt small']/descendant::tr[last()]/descendant::input[@type='checkbox']")
	private WebElement checkbox;
	
	@FindBy (xpath="//input[@class='crmbutton small delete']")
	private WebElement deleteButton;
	
	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void clickPlusButton() {
		plusButton.click();
	}
	
	public void clickCheckbox() {
		checkbox.click();
	}
	
	public void clickDeleteButton() {
		deleteButton.click();
	}
}
