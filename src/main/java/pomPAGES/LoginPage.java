package pomPAGES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains all the element of login page of Vtiger
 * @author 91897
 *
 */

public class LoginPage {

	@FindBy (xpath="//a[.='vtiger']")
	private WebElement pageHeader;
	
	@FindBy(xpath = "//input[@name='user_name']")
	private WebElement usernameTF;
	
	@FindBy(name="user_password")
	private WebElement passwordTF;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method is used to login to the application
	 * @param username
	 * @param password
	 */
	public void loginToApp(String username, String password) {
		usernameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		loginButton.submit();
	}

	public String getPageHeader() {
		return pageHeader.getText();
	}
}
