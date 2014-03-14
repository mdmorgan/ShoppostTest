package shoppostPages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FBsignIn {
	private int DRIVER_WAIT = 15;

	@FindBy(id = "email")
	private WebElement emailField;
	
	@FindBy(id = "pass")
	private WebElement passwordField;
	
	@FindBy (id = "persist_box")
	private WebElement rememberCheck;
	
	@FindBy (name = "login")
	private WebElement loginBtn;
	
	@FindBy (name = "__CONFIRM__")
	private WebElement okayBtn;
	
	@FindBy (name = "__SKIP__")
	private WebElement skipBtn;

	public FBsignIn (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}
	
	public void signupNow(String email, String password) {   //sign up with passed-in credentials
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginBtn.click();
	}
	
	public void signupRemember(String email, String password) {   //sign up with passed-in credentials
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		rememberCheck.click();
		loginBtn.click();
	}
	
	public void confirmInfo() {
		okayBtn.click();
	}

	
}