package shoppostPages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class FBsignIn {

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