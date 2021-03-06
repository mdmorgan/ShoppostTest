package theTests;

import static org.junit.Assert.*;

import java.io.File;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
//import java.util.Set;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.internal.selenesedriver.TakeScreenshot;
//import org.openqa.selenium.remote.Augmenter;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shoppostBeans.LPData;
import shoppostBeans.TestData;
import shoppostBeans.SauceLabData;

import shoppostPages.AnalyticsReporter;
import shoppostPages.ProductCatalog;
import shoppostPages.SignUpSignIn;
import shoppostPages.UserAgreement;

import shoppostTestSupport.GetDrivers;
import shoppostTestSupport.Global;
import shoppostTestSupport.SignOut;
import shoppostTestSupport.ReadTestJSON;
import shoppostTestSupport.ScreenShots;
import shoppostTestSupport.SignUpIn;
import shoppostTestSupport.Window;

//import alltest.JavaBeans.AcctSetData;


@RunWith(Parameterized.class)   //this runs the tests serially one browser at a time
//@RunWith(Parallelized.class)    //this will run the tests in parallel and will open a new thread for each browser simultaneously
public class ShoppostLinks {
	
	private String browser;
	private WebDriver driver;
	private static TestData _td;
	private static SauceLabData _sld;
	//private static AcctSetData _asd;
	private static LPData _ltd;
	private String userName, _username, _password, _freshUser;
	private String accessCode, _passkey, _email, _currUrl, _testCase, _emailAddress;
	private DesiredCapabilities capabilities;
	private int m;
	private WebDriverWait wait, wait2;
	private java.awt.Dimension screenSize;
	private Dimension dim;
	private int counter, r;
	private SignUpIn signup;
	private SignUpSignIn signupinPage;
	private ProductCatalog catalog;
	private AnalyticsReporter analyticsReporter;
	private UserAgreement userAgreementPage;
	private String _errorMsg,  _testPlatform, _folderTestCase;
	//private ScreenShot ss;
	
	
	
	
	//public String getBrowser() { return browser; }
	
	@Parameters
	public static Collection<Object[]> browsersStrings(){
		Object[][] browsersStrings = new Object[][]{
				//{"*firefox"}
				{"*chrome"}
				//{"*internetexplorer"}
				//{"*safari"}
				//{"*sauceLabsRemote1"}
				//{"*sauceLabsRemote2"},
				//{"*sauceLabsRemote3"},
				//{"*sauceLabsRemote4"}
				};
		return Arrays.asList(browsersStrings);
	} 
	
	public ShoppostLinks (String browser){
		
		this.browser = browser;
		
	}
	
	@Before
	public void setUp() throws Exception {  //sets up the driver
		counter = Global.sauceCounter;
		System.out.println("counter is: "+counter);
		
		GetDrivers getDriver = new GetDrivers(this.browser);   //instantiate GetDriver
		driver = getDriver.set();
		_testPlatform = getDriver.getPlatform();  //gets platform (must match qmetry platform in test cases)
		
		return;
		
	}
	
	@Test
	  public void test_Links() throws Exception {
		wait = new WebDriverWait(driver, 10);
		Random rand = new Random();
		
		ReadTestJSON.read();
		_td = ReadTestJSON.get_td();
		
		String _browsName = this.browser;
		_browsName = _browsName.substring(1);
		ScreenShots ss = new ScreenShots(driver, _td, _sld, _ltd, _browsName, m);   //screenshot of platform signup
		
		signup = new SignUpIn(driver, _td);   //signup methods
		SignOut logout = new SignOut(driver);
		
		catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
		analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
		signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject
		
		_username = _td.getUsername();
		_passkey = _td.getSignupinTests().getHalfPassword();
		_freshUser = "";
		
		//System.out.println("testLength is: "+TestRunner.getTests().length);
		
		for (int k=0; k<_td.getSignupinTests().getTests().size(); k++) {  //taking one testCase parameter at a time
		//for (int k=0; k<TestRunner.getTests().length; k++) {  //taking one testCase parameter at a time
	    //for (int k=0; k<1; k++) {
			//ss.takeTheShot(1, "platform");
  			//_testCase = TestRunner.getTests()[k];
			_testCase = _td.getSignupinTests().getTests().get(k);
			_folderTestCase = _td.getSignupinTests().getTcFolder()+_testCase;
			System.out.println(_testCase);
  			switch (_testCase) {
  			
				case "signupValid": //loads home page then redirects to signup page
					signup.helloPlatform(_td.getBaseUrl());
					
					r = rand.nextInt(1000);
					_freshUser = _username+r+"@sharklasers.com";  //make fake email with random number (a brand new user)
					_email = _freshUser;  //initialize '_freshUser'
					
					_password = _passkey+_passkey;
					System.out.println(_email);
					signup.signUpTest(_email, _password, 0);
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					Thread.sleep(1500);
					_emailAddress = catalog.getEmailAddress();
					if(_emailAddress.equals(_email)) {
						System.out.println("PASS Correct signup email: "+_email);
					} else {
						fail("Fail - bad email address: "+_emailAddress);
					}
					
					Thread.sleep(500);
					catalog.openAcctMenu();
					catalog.signOut();
					break;
					
					
				case "userAgreement": 
					Window win = new Window(driver);
					
					signup.helloPlatform(_td.getBaseUrl());
					r = rand.nextInt(10000);
					_email = _username+r+"@sharklasers.com";  //make fake email with random number (a brand new user)_email = _username+r+".com";  //make fake email with random number
					_password = _passkey+_passkey;
					signup.gotoSignup();
					//signupPage = PageFactory.initElements(driver, SignUp.class);  //instantiate the pageOject
					
					//signupPage.toAgreement();  
					
					userAgreementPage = PageFactory.initElements(driver, UserAgreement.class);
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='agreementCheckbox']")));
					_currUrl = driver.getCurrentUrl();  //
					
					String mwh = driver.getWindowHandle();   //get current window name
					driver.switchTo().window(win.changeWindow(By.xpath("//label[@for='agreementCheckbox']/p/a")));
					
					try {
						
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='container']")));  //wait for user agreement to show
						assertEquals("Shoppost - End User License Agreement",driver.getTitle());
						System.out.println("User Agreement confirmed.");
					}
					catch (TimeoutException e) {
						System.out.println("NO Agreement Page!");
					}
					catch (ComparisonFailure ex) {
						
						System.out.println("Different Error Message Found");
					}
					finally { driver.close(); }
					
					//Thread.sleep(1000);
					//driver.close(); //close agreement window
					
					driver.switchTo().window(mwh);  //go back to signup page
					driver.get(_currUrl);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signUpBtn")));  //waiting for sign up page
				
					break;
				
				case "alreadyMemberBtn": 
					signup.helloPlatform(_td.getBaseUrl()+"sign-up");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='form']/footer/p[1]")));
					driver.findElement(By.xpath("//section[@class='form']/footer/p[1]/a")).click();
					Thread.sleep(500);
					
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBtn")));  //waiting for sign up page
						driver.findElement(By.id("email_input"));
						System.out.println("Member redirect confirmed.");
					}
					catch (TimeoutException e) {
						System.out.println("ERROR! No Sign In Page!");
					}
					catch (ElementNotVisibleException ex) {
						
						System.out.println("ERROR! Email input not found");
					}
					finally {}
					driver.get(_td.getBaseUrl()+"/sign-up"); 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signUpBtn")));  //waiting for sign up page
					//driver.close();
					break;
				
				
				case "forgotPassword": 
					signup.helloPlatform(_td.getBaseUrl()+"sign-up");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='form']/footer/p[1]")));
					driver.findElement(By.xpath("//section[@class='form']/footer/p[1]/a")).click();
					Thread.sleep(500);
					
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBtn")));  //waiting for sign up page
						driver.findElement(By.id("email_input"));
						System.out.println("Member redirect confirmed.");
					}
					catch (TimeoutException e) {
						System.out.println("ERROR! No Sign In Page!");
					}
					catch (ElementNotVisibleException ex) {
						
						System.out.println("ERROR! Email input not found");
					}
					finally {}
					driver.get(_td.getBaseUrl()+"/sign-up"); 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signUpBtn")));  //waiting for sign up page
					//driver.close();
					break;
				
				case "signUp": 
					signup.helloPlatform(_td.getBaseUrl()+"sign-up");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='form']/footer/p[1]")));
					driver.findElement(By.xpath("//section[@class='form']/footer/p[1]/a")).click();
					Thread.sleep(500);
					
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBtn")));  //waiting for sign up page
						driver.findElement(By.id("email_input"));
						System.out.println("Member redirect confirmed.");
					}
					catch (TimeoutException e) {
						System.out.println("ERROR! No Sign In Page!");
					}
					catch (ElementNotVisibleException ex) {
						
						System.out.println("ERROR! Email input not found");
					}
					finally {}
					driver.get(_td.getBaseUrl()+"/sign-up"); 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signUpBtn")));  //waiting for sign up page
					//driver.close();
					break;
				
				case "getHelp": 
					signup.helloPlatform(_td.getBaseUrl()+"sign-up");
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='form']/footer/p[1]")));
					driver.findElement(By.xpath("//section[@class='form']/footer/p[1]/a")).click();
					Thread.sleep(500);
					
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBtn")));  //waiting for sign up page
						driver.findElement(By.id("email_input"));
						System.out.println("Member redirect confirmed.");
					}
					catch (TimeoutException e) {
						System.out.println("ERROR! No Sign In Page!");
					}
					catch (ElementNotVisibleException ex) {
						
						System.out.println("ERROR! Email input not found");
					}
					finally {}
					driver.get(_td.getBaseUrl()+"/sign-up"); 
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signUpBtn")));  //waiting for sign up page
					//driver.close();
					break;

					
					
  				}
		}
		
		Thread.sleep(1000);
		//ss.takeTheShot(i, "platform", _platform, _folderTestCase); //screenshot of outro
		Thread.sleep(1000);  
		
	}
	

	/**
	public void checkSignupError(String em) throws Exception {
		String _errorMessage = em;
		wait2 = new WebDriverWait(driver, 5);
		
		if (driver.findElement(By.id("errorMsg")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
			
		} else if (driver.findElement(By.xpath("//div[@id='emailField']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='emailField']/span")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
		} else if (driver.findElement(By.xpath("//div[@id='passwordField']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='passwordField']/span")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
		} else if (driver.findElement(By.xpath("//div[@id='repeatPasswordField']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='repeatPasswordField']/span")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
		} else if (driver.findElement(By.xpath("//div[@id='agreementField']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='agreementField']/span")).getText().equals(_errorMessage)) {
			System.out.println(_errorMessage+" error displayed.");
		} else {
			System.out.println("No message was found.");
		}
		
		
	}
	
	public void checkSigninError (String em) throws Exception {
		String _error = em;
		wait2 = new WebDriverWait(driver, 5);
		
		try {
			driver.findElement(By.xpath("//div[@id='main-login']/div[contains(@class,'errorMessage')]"));
			if (driver.findElement(By.xpath("//div[@id='main-login']/div[contains(@class,'errorMessage')]")).getText().equals(_error)) {
				System.out.println("'"+_error+"' error displayed.");
			}
		} catch (NoSuchElementException e) {
			if (driver.findElement(By.xpath("//div[@id='emailWrapper']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='emailWrapper']/span")).getText().equals(_error)) {
				System.out.println("'"+_error+"' error displayed.");
			} else if (driver.findElement(By.xpath("//div[@id='passwordWrapper']/span")).isDisplayed()||driver.findElement(By.xpath("//div[@id='passwordWrapper']/span")).getText().equals(_error)) {
				System.out.println("'"+_error+"' error displayed.");
			} else {
				System.out.println("No message was found.");
			}

		}
	
		
	}**/
	
	
	@After
	public void tearDown() throws Exception {
		Global.increment();           //increment the global variable 'counter' by 1
        driver.quit();
    }  

}
