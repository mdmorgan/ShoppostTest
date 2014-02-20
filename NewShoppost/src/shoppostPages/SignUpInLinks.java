package shoppostPages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class SignUpInLinks {

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

}