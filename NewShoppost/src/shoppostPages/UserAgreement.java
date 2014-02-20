package shoppostPages;


import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class UserAgreement {

	@FindBy(how = How.ID, using = "UserName")
	private WebElement emailField;
	
	@FindBy(id = "Password")
	private WebElement passwordField;
	
	
	
	
	public void signinNow(String email, String password) {

		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		//signInBtn.click();
	}
	
	public void rememberMe() {
		//remember.click();
	}

}
