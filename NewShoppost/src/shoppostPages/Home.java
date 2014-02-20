package shoppostPages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class Home {

	@FindBy(id = "navSignUp")
	private WebElement signupBtn;
	
	@FindBy (id = "navSignIn")
	private WebElement signInBtn;
	
	@FindBy (id = "navLogo")
	private WebElement logoBtn;
	
	public void toSignup() {

		signupBtn.click();
	}
	public void toSignin() {
		signInBtn.click();
	}
	public void viaLogo() {
		logoBtn.click();
	}

}
