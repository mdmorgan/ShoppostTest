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
public class ShoppostSignUpEmail {
	
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
	private String _errorMsg;
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
	
	public ShoppostSignUpEmail (String browser){
		
		this.browser = browser;
		
	}
	
	@Before
	public void setUp() throws Exception {  //sets up the driver
		counter = Global.sauceCounter;
		System.out.println("counter is: "+counter);
		
		GetDrivers getDriver = new GetDrivers(this.browser);   //instantiate GetDriver
		driver = getDriver.set();
		
		return;
		
	}
	
	@Test
	  public void test_signUpIn() throws Exception {
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
		
		_username = _td.getSignupinTests().getUsername();
		_passkey = _td.getSignupinTests().getHalfPassword();
		_freshUser = "";
		
		//System.out.println("testLength is: "+TestRunner.getTests().length);
		
		for (int k=0; k<_td.getSignupinTests().getTests().size(); k++) {  //taking one testCase parameter at a time
		//for (int k=0; k<TestRunner.getTests().length; k++) {  //taking one testCase parameter at a time
	    //for (int k=0; k<1; k++) {
			//ss.takeTheShot(1, "platform");
  			//_testCase = TestRunner.getTests()[k];
			_testCase = _td.getSignupinTests().getTests().get(k);
			//_testCase = "signupValid";
  			System.out.println(_testCase);
  			switch (_testCase) {
  			
				case "signupValid": //loads home page then redirects to signup page
					signup.helloPlatform(_td.getSignupinTests().getBaseUrl());
					
					r = rand.nextInt(1000);
					_freshUser = _username+r+"@sharklasers.com";  //make fake email with random number (a brand new user)
					_email = _freshUser;  //initialize '_freshUser'
					
					_password = _passkey+_passkey;
					System.out.println(_email);
					signup.signUpTest(_email, _password, 0);
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					Thread.sleep(2000);
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
					
				case "alreadyExist": 
					signup.helloPlatform(_td.getSignupinTests().getBaseUrl());
					_email = _td.getSignupinTests().getExistingUser(); 
					if (_freshUser != "") { _email = _freshUser; }  //if there is a 'freshUser' then log in with same
					_password = _passkey+_passkey;
					System.out.println(_email+", "+_password);
					signup.signUpTest(_email, _password, 0);
					
					//signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject
					//System.out.println("bb");
					Thread.sleep(500);
					_errorMsg = signupinPage.getRedAdvisory();
					if(_errorMsg.equals("Name "+_email+" is already taken.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("Fail - requires email taken advisory not: "+_errorMsg);
					}
					
					
					break;
					
				case "signupBlankEmail": 	//this will fail - issue in backlog 3/5/14
					signup.helloPlatform(_td.getSignupinTests().getBaseUrl());
					_password = _passkey+_passkey;
					signup.signUpTest("", _password, 0);
					Thread.sleep(1500);
					//signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject
					//System.out.println("bb");
					
					_errorMsg = signupinPage.checkEmailError();
					if(_errorMsg.equals("Email address field is required.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("Fail - requires blank email error advisory not: "+_errorMsg);
					}
					
					
					break;
					
				case "signupInvalidEmail": 	
					signup.helloPlatform(_td.getSignupinTests().getBaseUrl());
					_password = _passkey+_passkey;
					signup.signUpTest(_username, _password, 0);
					//signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject for errors
					//System.out.println("bb");
					Thread.sleep(1500);
					_errorMsg = signupinPage.checkEmailError();
					if(_errorMsg.equals("Please enter a valid email address.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("Fail - requires invalid email error advisory not: "+_errorMsg);
					}
					break;
				case "signupNoPW": //this will fail - issue in backlog 3/5/14
					signup.helloPlatform(_td.getSignupinTests().getBaseUrl());
					
					r = rand.nextInt(10000);
					_email = _username+r+"@sharklasers.com";  //make fake email with random number (a brand new user)
					if (_freshUser != "") { _email = _freshUser; }  //if there is a 'freshUser' then log in with same
					signup.signUpTest(_email, "", 0);
					
					signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject
					//System.out.println("bb");
					
					_errorMsg = signupinPage.getRedAdvisory();
					if(_errorMsg.equals("The Password field is required.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("Fail - requires blank password error advisory not: "+_errorMsg);
					}
					
					
					break;
					
				case "signupShortPW": 	
					signup.helloPlatform(_td.getSignupinTests().getBaseUrl());
					r = rand.nextInt(10000);
					_email = _username+r+"@sharklasers.com";  //make fake email with random number (a brand new user)_email = _username+r+".com";  //make fake email with random number
					_password = _passkey;
					signup.signUpTest(_email, _password, 0);
					
					//signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject
					//System.out.println("bb");
					Thread.sleep(1500);
					
					_errorMsg = signupinPage.getRedAdvisory();
					if(_errorMsg.equals("The Password must be at least 6 characters long.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("Fail - requires password too short error advisory not: "+_errorMsg);
					}
					
					break;
					
				case "signupMismatchPW": 
					signup.helloPlatform(_td.getSignupinTests().getBaseUrl());
					r = rand.nextInt(10000);
					_email = _username+r+"@sharklasers.com";  //make fake email with random number (a brand new user)_email = _username+r+".com";  //make fake email with random number
					_password = _passkey+_passkey;
					signup.signUpTest(_email, _password, 1);
					
					signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject
					//System.out.println("bb");
					Thread.sleep(1500);
					_errorMsg = signupinPage.getRedAdvisory();
					if(_errorMsg.equals("The password and confirmation password do not match.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("Fail - requires password mismatch error advisory not: "+_errorMsg);
					}
					break;
					
				
					
					
  				}
		}
		
		Thread.sleep(1000);
		ss.takeTheShot(2, "platform", _testCase);  //screenshot of outro
		Thread.sleep(1000);  
		
	}
	

	
	
	@After
	public void tearDown() throws Exception {
		Global.increment();           //increment the global variable 'counter' by 1
        driver.quit();
    }  

}
