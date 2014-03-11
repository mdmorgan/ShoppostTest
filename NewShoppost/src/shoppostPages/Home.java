package shoppostPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.WebElement;

public class Home {
	private int DRIVER_WAIT = 15;

	@FindBy(id = "navSignUp")
	private WebElement signupBtn;
	
	@FindBy (id = "navSignIn")
	private WebElement signInBtn;
	
	@FindBy (id = "navLogo")
	private WebElement logoBtn;
	
	public Home (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}

	
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
