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
public class ShoppostSharing {
	
	private String browser;
	private WebDriver driver;
	private static TestData _td;
	private static SauceLabData _sld;
	//private static AcctSetData _asd;
	private static LPData _ltd;
	private String userName, _username, _password, _freshUser, _usernameFB, _shareTitle, _usernameTwitter, _tweet, _initialTweet;
	private String accessCode, _passkey, _passkeyFB, _email, _currUrl, _currUrl_b, mwh_b, _mwh, _testCase, _emailAddress;
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
	private String _errorMsg;
	private Window win;
	private Set beforePopup;
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
	
	public ShoppostSharing (String browser){
		
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
	  public void test_productSharing() throws Exception {
		wait = new WebDriverWait(driver, 10);
		Random rand = new Random();
		Actions move = new Actions(driver);
		signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject 
		shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
		shareSetUp = PageFactory.initElements(driver, ShareSetUp.class);
		analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
		catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 

		ReadTestJSON.read();
		_td = ReadTestJSON.get_td();
		
		String _browsName = this.browser;
		_browsName = _browsName.substring(1);
		ScreenShots ss = new ScreenShots(driver, _td, _sld, _ltd, _browsName, m);   //screenshot of platform signup
		
		signup = new SignUpIn(driver, _td);   //signup methods
		SignOut logout = new SignOut(driver);
		Window win = new Window(driver);
		
		_usernameFB = _td.getShareData().getUsernameFB();
		System.out.println(_usernameFB);
		System.out.println("A");
		_usernameTwitter = _td.getShareData().getUsernameTwitter();
		_passkeyFB = _td.getShareData().getPasswordFB();
		_username = _td.getShareData().getUsername();
		_password = _td.getShareData().getPassword();
		_freshUser = "";
		
		//System.out.println("testLength is: "+TestRunner.getTests().length);
		for (int k=0; k<_td.getShareData().getTests().size(); k++) {  //taking one testCase parameter at a time (this by-passes the need for TestRunner
		//for (int k=0; k<TestRunner.getTests().length; k++) {  //taking one testCase parameter at a time from cmd line
	    //for (int k=0; k<1; k++) {   //just a quick test
			//ss.takeTheShot(1, "platform");
  			//_testCase = TestRunner.getTests()[k];
			_testCase = _td.getShareData().getTests().get(k);
			//_testCase = "signupValid";
  			switch (_testCase) {
  			
					
					
				case "confirmFBShare": // FB shares
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					//signup.signInTest(_email, _password, 0);
					//
					//analyticsReporter.toCatalog();
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.shareFB();   //this opens a new tab for FB sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					shareSetUp.loginFacebook(_usernameFB, _passkeyFB);
					//shareSetUp.shareToTimeline(_td.getShareData().getShareMessage());
					_shareTitle = shareSetUp.getShareTitleFB();
					
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal
					move.moveToElement(catalog.hoverProductAgain());
					catalog.getShare();
					System.out.println("name = "+shareModal.getProductName());
					if(_shareTitle.equals(shareModal.getProductName())) {
						System.out.println("PASS Correct data to FB: "+_shareTitle);
					} else {
						fail("FAIL - invalid data to FB: "+_shareTitle);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "confirmTwitterShare": // twitter shares
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					//signup.signInTest(_email, _password, 0);
					//
					//analyticsReporter.toCatalog();
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.shareTwitter();   //this opens a new tab for twitter sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
					_initialTweet = shareSetUp.getTweet();
					shareSetUp.addMoreTweet(_td.getShareData().getMoreTweet());
					shareSetUp.loginTweetTwitter(_usernameTwitter, _passkeyFB);
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal
					move.moveToElement(catalog.hoverProductAgain());
					catalog.getShare();
					shareModal.goLink();
					System.out.println("tweet = "+_initialTweet);
					if(_initialTweet.equals(shareModal.getProductName()+" "+shareModal.getLPUrl())) {
						System.out.println("PASS Correct data to twitter: "+_initialTweet);
					} else {
						fail("FAIL - invalid data to tiwtter: "+_initialTweet);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "confirmPinterestShare": // see error message
					
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					//signup.signInTest(_email, _password, 0);
					//
					//analyticsReporter.toCatalog();
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.sharePinterest();   //this opens a new tab for pinterst sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
					_initialTweet = shareSetUp.getTweet();
					shareSetUp.addMoreTweet(_td.getShareData().getMoreTweet());
					shareSetUp.loginTweetTwitter(_usernameTwitter, _passkeyFB);
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal
					move.moveToElement(catalog.hoverProductAgain());
					catalog.getShare();
					shareModal.goLink();
					System.out.println("tweet = "+_initialTweet);
					if(_initialTweet.equals(shareModal.getProductName()+" "+shareModal.getLPUrl())) {
						System.out.println("PASS Correct data to twitter: "+_initialTweet);
					} else {
						fail("FAIL - invalid data to tiwtter: "+_initialTweet);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;


				case "shareModalTest": //Opens share modal window for random product
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					Actions move2 = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					move2.moveToElement(catalog.hoverRandomProduct());
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();
					Thread.sleep(2000);
					shareModal.closeShareModal();
					Thread.sleep(2000);
					//logout.logoutFromCat();
					
					break;


					
				case "addProductViaDashboard": 	
					
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject 
					signup.signInTest(_email, _password, 0);
					analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
					analyticsReporter.addProduct();
					Thread.sleep(1000);
					catalog.scrape(_td.getShareData().getProductURL());  //this should open the share modal
					Thread.sleep(1000);
					catalog.closeAdd();
					
					Thread.sleep(1000);
					logout.logoutFromCat();
					
					break;
					
				case "productDisplay": 	
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject 
					signup.signInTest(_email, _password, 0);
					analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
					analyticsReporter.toCatalog();
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.addProduct();
					Thread.sleep(1000);
					catalog.scrape(_td.getShareData().getProductURL());  //bad URL
					Thread.sleep(1000);
					catalog.closeAdd();
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					_errorMsg = signupinPage.checkPWError();
					if(_errorMsg.equals("The Password field is required.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("FAIL - requires blank password error advisory not: "+_errorMsg);
					}
					
					
					break;
					
					
				case "noProductDisplay": 
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					signupinPage = PageFactory.initElements(driver, SignUpSignIn.class);  //instantiate the pageOject 
					//signup.signInTest(_email, _password, 0);
					//analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
					//analyticsReporter.toCatalog();
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					Thread.sleep(2000);
					catalog.closeAdd();
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					_errorMsg = signupinPage.checkEmailError();
					if(_errorMsg.equals("Must be a valid email address.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("FAIL - requires invalid email error advisory not: "+_errorMsg);
					}
					
					
					break;
				
				case "deleteProductViaHover": 
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					Actions move3 = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					move3.moveToElement(catalog.hoverRandomProduct());
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getRandomDelete();
					Thread.sleep(2000);
					//logout.logoutFromCat();
					
					break;

				
				case "deleteProductViaModalShare": 
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					Actions move4 = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					move4.moveToElement(catalog.hoverRandomProduct());
					//catalog =Share();
					Thread.sleep(2000);
					shareModal.deleteProdViaModal();
					
					Thread.sleep(2000);
					//logout.logoutFromCat();
					
					break;

				
				case "deleteProductViaModalStats": 
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					Actions move5 = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					move5.moveToElement(catalog.hoverRandomProduct());
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();
					shareModal.goStats();
					Thread.sleep(1000);
					shareModal.deleteProdViaModal();
					
					Thread.sleep(2000);
					//logout.logoutFromCat();
					
					break;

				
				case "cancelDeleteProduct": 
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					Actions move6 = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					move6.moveToElement(catalog.hoverRandomProduct());
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();
					Thread.sleep(2000);
					shareModal.deleteProdViaModalCancel();
					Thread.sleep(1500);
					shareModal.closeShareModal();
					
					Thread.sleep(2000);
					//logout.logoutFromCat();
					
					break;

				
				case "viewRandomStats": //View random product Stats from product hover
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					Actions movea = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					movea.moveToElement(catalog.hoverRandomProduct());  //hover random product from Catalog
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getRandomStats();  //click stats button
					Thread.sleep(2000);
					shareModal.closeShareModal();
					Thread.sleep(2000);
					//logout.logoutFromCat();
					
					break;
					
				case "viewRandomStatsViaShare": //View random product Stats from share Modal
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					Actions move7 = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					move7.moveToElement(catalog.hoverRandomProduct());  //hover random product from Catalog
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();  //click share button
					Thread.sleep(1000);
					shareModal.goStats();
					Thread.sleep(2000);
					shareModal.closeShareModal();
					//logout.logoutFromCat();
					
					break;

				case "viewSingleStatsViaDashMS": //View product Stats from Dashboard most shared
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();  //click share button
					Thread.sleep(1000);
					shareModal.goStats();
					Thread.sleep(2000);
					shareModal.closeShareModal();
					//logout.logoutFromCat();
					
					break;

				case "viewSingleStatsViaDashMR": //View product Stats from Dashboard most referred
					signup.helloPlatform(_td.getShareData().getBaseUrl());
					analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();  //click share button
					Thread.sleep(1000);
					shareModal.goStats();
					Thread.sleep(2000);
					shareModal.closeShareModal();
					//logout.logoutFromCat();
					
					break;



					
					
  				}
		}
		
		Thread.sleep(1000);
		//ss.takeTheShot(2, "platform", _testCase);  //screenshot of outro
		Thread.sleep(1000);  
		
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
