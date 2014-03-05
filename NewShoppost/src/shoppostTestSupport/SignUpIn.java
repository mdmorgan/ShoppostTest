package shoppostTestSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shoppostBeans.TestData;
//import shoppostPages.SignUp;
import shoppostPages.Home;
//import shoppostPages.SignIn;
import shoppostPages.SignUpSignIn;




public class SignUpIn {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private String _baseURL, _email, _username, _password;
	private TestData _td;
	private Home homePage;
	//private SignUp signupPage;
	//private SignIn signinPage;
	private SignUpSignIn signupinPage;
	private int j,m,r;
	
	
	
	public SignUpIn (WebDriver _driver, TestData td){
		
		driver = _driver;
		_td = td;
		
		homePage = PageFactory.initElements(driver, Home.class);
		signupinPage = PageFactory.initElements(driver,  SignUpSignIn.class);
		
		//System.out.println(baseURL);
		
	}
	
	
	  public void helloPlatform(String baseURL) throws Exception {  //goes to url and don't wait
		_baseURL = baseURL;
		wait = new WebDriverWait(driver, 7);
		Thread.sleep(1000);
		driver.get(_baseURL);
		Thread.sleep(1000);
		//homePage = PageFactory.initElements(driver, Home.class); //create new instance of the home pageObject class and initialize any WebElement fields in it.
		//System.out.println("to home");
	  }
	  
	  public void gotoSignup() throws Exception {
		  homePage.toSignup(); // navigate to signup page
	  }
	  
	  public void gotoSignin() throws Exception {
		  homePage.toSignin();
	  }
		
	  public void signUpTest(String username, String password, int aa) throws Exception {	
		_email = username;
		_password = password;
		Actions move = new Actions(driver);
		int _aa = aa;
		
		gotoSignup(); //homePage.toSignup();  //navigates to sign up page
		Thread.sleep(1500);
		
	    if (_aa == 1) { 
	    	signupinPage.signupNoMatch(_email, _password);   //sends mismatch password
		
	    }else{
		    signupinPage.signupNow(_email, _password);
	    }
	  }
	  
	  public void signInTest(String username, String password, int aa) throws Exception {	
			_email = username;
			_password = password;
			int _aa = aa;
			
			gotoSignin();
			Thread.sleep(1000);
			signupinPage.signinNow(_email, _password);
			Thread.sleep(500);
	  }
	  
	 
	  
	  
	  public String getEmail() { return _email; }
}

