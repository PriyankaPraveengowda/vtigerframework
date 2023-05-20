package pomPAGES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateLeadPage {
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(name="lastname")
	private WebElement lastnameTF;
	
	@FindBy(name="company")
	private WebElement companyTF;
	
	@FindBy(xpath="//input[@class='crmButton small save']")
	private WebElement saveButton;
	
	public CreateLeadPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void setLastNameTF(String lastname) {
		lastnameTF.sendKeys(lastname);
	}
	
	public void  setCompanyTF(String companyName) {
		companyTF.sendKeys(companyName);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
}
