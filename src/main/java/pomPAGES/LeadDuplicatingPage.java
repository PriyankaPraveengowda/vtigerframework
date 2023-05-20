package pomPAGES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadDuplicatingPage {

	@FindBy (xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy (xpath="//input[@name='lastname']")
	private WebElement lastNameTF;
	
	@FindBy (name="company")
	private WebElement companyTF;
	
	@FindBy (xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	public LeadDuplicatingPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void setLastnameTF(String lastname) {
		lastNameTF.clear();
		System.out.println("cleared");
		lastNameTF.sendKeys(lastname);
	}
	
	public void setCompanyTF(String company) {
		companyTF.clear();
		companyTF.sendKeys(company);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
}
