package pomPAGES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibraries.WebDriverUtility;

public class CreateOrganizationPage {

	@FindBy(xpath= "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy (name="accountname")
	private WebElement orgNameTF;
	
	@FindBy (xpath = "//select[@name='industry']")
	private WebElement industryDD;
	
	@FindBy (xpath = "//select[@name='accounttype']")
	private WebElement typeDD;
	
	@FindBy (xpath = "//input[contains(@value,'Save')]")
	private WebElement saveButton;
	
	public CreateOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void setOrgName(String orgName) {
		orgNameTF.sendKeys(orgName);
	}
	
	public void selectIndustry(WebDriverUtility web, String value) {
		web.dropDown(industryDD, value);
	}
	
	public void selectType(WebDriverUtility web, String value) {
		web.dropDown(typeDD, value);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
}
