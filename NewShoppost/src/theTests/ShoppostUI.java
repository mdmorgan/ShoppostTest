package theTests;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.Set;

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
import org.openqa.selenium.interactions.Actions;
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


import shoppostPages.Home;
import shoppostPages.ShareSetUp;
import shoppostPages.ProductCatalog;
import shoppostPages.ShareModal;
import shoppostPages.SignUpSignIn;
import shoppostPages.UserAgreement;
import shoppostPages.FBsignIn;
import shoppostPages.AnalyticsReporter;

import shoppostTestSupport.GetDrivers;
import shoppostTestSupport.Global;
import shoppostTestSupport.SignOut;
import shoppostTestSupport.ReadTestJSON;
import shoppostTestSupport.ScreenShots;
import shoppostTestSupport.SignUpIn;
import shoppostTestSupport.Window;






@RunWith(Parameterized.class)   //this runs the tests serially one browser at a time
//@RunWith(Parallelized.class)    //this will run the tests in parallel and will open a new thread for each browser simultaneously
public class ShoppostUI {
	
	private String browser;
	private WebDriver driver;
	private static TestData _td;
	private static SauceLabData _sld;
	//private static AcctSetData _asd;
	private static LPData _ltd;
	private String userName, _username, _password, _freshUser, _usernameFB, _shareTitle, _usernameTwitter, _tweet, _initialTweet;
	private String accessCode, _passkey, _passkeyFB, _email, _currUrl, _currUrl_b, mwh_b, _mwh, _testCase, _emailAddress;
	private String _pinTitle, _googPlusPW, _googTitle, _browsName;
	private DesiredCapabilities capabilities;
	private WebDriverWait wait, wait2;
	private java.awt.Dimension screenSize;
	private Dimension dim;
	private int counter, r, m, _productCount, preCt, postCt;
	private SignUpIn signup;
	private SignUpSignIn signupinPage;
	private UserAgreement userAgreementPage;
	private ProductCatalog catalog;
	private FBsignIn FBone, FBtwo;
	private AnalyticsReporter analyticsReporter;
	private ShareModal shareModal;
	private ShareSetUp shareSetUp;
	private Home home;
	private String _errorMsg, _testPlatform, _folderTestCase;
	private Window win;
	private Set beforePopup;
	private ScreenShots ss;
	
	
	
	
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
	
	public ShoppostUI (String browser){
		
		this.browser = browser;
		_browsName = this.browser.substring(1);  //takes off "*" from beginning
	}
	
	@Before
	public void setUp() throws Exception {  //sets up the driver
		counter = Global.sauceCounter;
		System.out.println("counter is: "+counter);
		
		GetDrivers getDriver = new GetDrivers(this.browser);   //instantiate GetDriver
		driver = getDriver.set();   // gets needed browser driver
		_testPlatform = getDriver.getPlatform();  //gets platform (must match qmetry platform in test cases)
		
		return;
		
	}
	
	@Test
	  public void test_platformUI() throws Exception {
		//wait = new WebDriverWait(driver, 10);
		signupinPage = new SignUpSignIn(driver);  //this waits for page to load AND initializes the pageFactory proxies
		analyticsReporter = new AnalyticsReporter(driver); 
		catalog = new ProductCatalog(driver);
		shareModal = new ShareModal(driver);
		shareSetUp = new ShareSetUp(driver);
		home = new Home(driver);
		Random rand = new Random();
		Actions move = new Actions(driver);
		//signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject 
		//shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
		//shareSetUp = PageFactory.initElements(driver, ShareSetUp.class);
		//analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
		//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
		//home = PageFactory.initElements(driver, Home.class);  //instantiate the pageOject 

		ReadTestJSON.read();
		_td = ReadTestJSON.get_td();
		
		String _browsName = this.browser;
		_browsName = _browsName.substring(1);
		ScreenShots ss = new ScreenShots(driver, _td, _sld, _ltd, _browsName, m);   //screenshot of platform signup
		
		signup = new SignUpIn(driver, _td);   //signup methods
		SignOut logout = new SignOut(driver);
		Window win = new Window(driver);
		
		_username = _td.getUIData().getUsername();
		_password = _td.getUIData().getUserPW();
		_freshUser = "";
		
		
		//System.out.println("testLength is: "+TestRunner.getTests().length);
		//for (int k=0; k<_td.getUIData().getTests().size(); k++) {  //taking one testCase parameter at a time (this by-passes the need for TestRunner
		for (int k=0; k<TestRunner.getTests().length; k++) {  //taking one testCase parameter at a time from cmd line
	    //for (int k=0; k<1; k++) {   //just a quick test
			
			_testCase = TestRunner.getTests()[k];
			//_testCase = _td.getUIData().getTests().get(k);
			_folderTestCase = _td.getUIData().getTcFolder()+_testCase;
			switch (_testCase) {
  			
					
					
				case "home": // FB shares
					signup.helloPlatform(_td.getUIData().getBaseUrl());
					browsWidths(_testPlatform, _folderTestCase); // takes screenshots with varying browser widths
					Thread.sleep(1000);
					
					//logout.logoutFromCat();
					
					break;
					
				case "signUp": //
					signup.helloPlatform(_td.getUIData().getBaseUrl());
					home.toSignup();  //open sign up page
					browsWidths(_testPlatform, _folderTestCase); // takes screenshots with varying browser widths
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "signIn": //
					signup.helloPlatform(_td.getUIData().getBaseUrl());
					home.toSignin();  // open sign in page
					browsWidths(_testPlatform, _folderTestCase); // takes screenshots with varying browser widths
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;

					
				case "dashboard": // 
					
					signup.helloPlatform(_td.getUIData().getBaseUrl());
					signup.signInTest(_username, _password, 0);
					browsWidths(_testPlatform, _folderTestCase);
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
				
				case "catalog": // see error message
					
					signup.helloPlatform(_td.getUIData().getBaseUrl());
					signup.signInTest(_username, _password, 0);
					analyticsReporter.toCatalog();
					browsWidths(_testPlatform, _folderTestCase);
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "modalWindow": // see error message
					
					signup.helloPlatform(_td.getUIData().getBaseUrl());
					signup.signInTest(_username, _password, 0);
					analyticsReporter.toCatalog();
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());
					catalog.getShare();
					browsWidths(_testPlatform, _folderTestCase);
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;



				case "forgotPassword": //Opens share modal window for random product
					signup.helloPlatform(_td.getUIData().getBaseUrl());
					home.toSignin();  // open sign in page
					signupinPage.toForgetful();
					browsWidths(_testPlatform, _folderTestCase); // takes screenshots with varying browser widths
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;

					
				case "resetPassword": 	
					
					signup.helloPlatform(_td.getUIData().getBaseUrl());
					signup.signInTest(_username, _password, 0);
					catalog.openAcctMenu();
					catalog.toAcctSet();
					
					browsWidths(_testPlatform, _folderTestCase);
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;



					
				case "acctSetting": 	
					signup.helloPlatform(_td.getUIData().getBaseUrl());
					signup.signInTest(_username, _password, 0);
					catalog.openAcctMenu();
					catalog.toAcctSet();
					
					browsWidths(_testPlatform, _folderTestCase);
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;

					
					
  				}
		}
		
		//Thread.sleep(1000);
		//ss.takeTheShot(2, "platform", _testCase);  //screenshot of outro 
		Thread.sleep(1000);  
		
	}
	public void browsWidths(String platform, String foldertestCase) throws Exception {
		ScreenShots ss = new ScreenShots(driver, _td, _sld, _ltd, _browsName, m);
		int i;
		String _foldercase = foldertestCase;
		String _platform = platform;
		
		for (i=0; i<_td.getUIData().getWidths().size(); i++) {
			driver.manage().window().setPosition(new Point(0,0));
			screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			dim = new Dimension((int) _td.getUIData().getWidths().get(i), (int) screenSize.getHeight());
			driver.manage().window().setSize(dim);
			Thread.sleep(1000);
			
			ss.takeTheShot(i, "platform", _platform, _foldercase);  // ss.takeTheShot(2, "platform", _testCase); 
			if (_browsName.equals("sauceLabsRemote1")) { break; }
		}
		
		driver.manage().window().maximize();
		Thread.sleep(1000);
		ss.takeTheShot(i, "platform", _platform, _foldercase);
		
		
	}

	
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
	
		
	}
	
	
	@After
	public void tearDown() throws Exception {
		Global.increment();           //increment the global variable 'counter' by 1
        driver.quit();
    }  

}
