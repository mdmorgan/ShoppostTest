package shoppostPages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class SignUpSignIn {

	@FindBy(id = "Facebook")
	private WebElement FBButton;
	
	@FindBy(how = How.ID, using = "email")
	private WebElement emailField;
	
	@FindBy(id = "password")
	private WebElement passwordField;
	
	@FindBy (id = "confirmPassword")
	private WebElement confirmField;
	
	@FindBy (id = "signUp")
	private WebElement signUpBtn;
	
	@FindBy (id = "signIn")
	private WebElement signInBtn;
	
	@FindBy(className = "checkbox")
	private WebElement remember;
	
	@FindBy (id = "navSignUp")
	private WebElement navSignUpBtn;
	
	@FindBy (id = "navSignIn")
	private WebElement navSignInBtn;
	
	@FindBy (id = "termsOfUse")
	private WebElement termsUse;
	
	@FindBy (id = "agreement")
	private WebElement userAgree;

	@FindBy (id = "emailValidation")
	private WebElement emailMsg;
	
	@FindBy (id = "validation")
	private WebElement redAdvisory;
	//@FindBy (xpath = "//li[contains(@class,'validation-summary-errors')]")
	@FindBy (xpath = "//ul[@id='validation']/li")
	private WebElement redAdvisoryMsg;
	
	@FindBy (id = "register")
	private WebElement registerBtn;

	
	
	public void signupFB(String email, String password) {   //sign up with passed-in credentials
		FBButton.click();
	}
	public void signupNow(String email, String password) {   //sign up with passed-in credentials
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		confirmField.sendKeys(password);
		signUpBtn.click();
	}
	public void signupNoMatch(String email, String password) {   //invalid sign up (misMatched password)
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		confirmField.sendKeys(password+"x");
		signUpBtn.click();
	}
	public String checkEmailError() {
		String errorEmailMsg = "";
		if (emailMsg.isDisplayed()) {
			errorEmailMsg = emailMsg.getText();
		}
		return errorEmailMsg;
	}
	public String getRedAdvisory() {
		String errorMsg = "";
		if (redAdvisoryMsg.isDisplayed()) {
			errorMsg = redAdvisoryMsg.getText();
		}
		return errorMsg;
	}
	
	public void signinNow(String email, String password) {  //no 'remember me'

		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		signInBtn.click();
	}
	
	public void signinRemember(String email, String password) {  //with 'remember me'

		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		remember.click();
		signInBtn.click();
	}
	public void rememberMe() {
		remember.click();
	}
	public String getBadUsernameOrPW() {
		String errorMsg = "";
		if (redAdvisory.isDisplayed()) {
			errorMsg = redAdvisory.getText();
		}
		return errorMsg;
	}
	
	public void register(String email)  {
		emailField.sendKeys(email);
		registerBtn.click();
	}

}
