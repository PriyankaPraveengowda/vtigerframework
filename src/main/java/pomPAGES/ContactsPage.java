package pomPAGES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	@FindBy(xpath="//a[@class='hdrLink']")
	private WebElement pageHeader;
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement plusButton;
	
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	public void clickPlusButton() {
		plusButton.click();
	}
}
