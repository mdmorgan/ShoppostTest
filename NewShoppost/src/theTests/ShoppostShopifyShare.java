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
import shoppostPages.LandingPage;
import shoppostPages.ProductReporter;
import shoppostPages.ShareSetUp;
import shoppostPages.ProductCatalog;
import shoppostPages.ShareModal;
import shoppostPages.Shopify;
import shoppostPages.ShopifyLogin;
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
public class ShoppostShopifyShare {
	
	private String browser;
	private WebDriver driver;
	private static TestData _td;
	private static SauceLabData _sld;
	//private static AcctSetData _asd;
	private static LPData _ltd;
	private String userName, _username, _password, _freshUser, _usernameFB, _shareTitle, _usernameTwitter, _tweet, _initialTweet;
	private String accessCode, _passkey, _passkeyFB, _email, _currUrl, _currUrl_b, mwh_b, _mwh, _testCase, _emailAddress;
	private String _pinTitle, _googPlusPW, _googTitle;
	private DesiredCapabilities capabilities;
	private WebDriverWait wait, wait2;
	private java.awt.Dimension screenSize;
	private Dimension dim;
	private int counter, r, m, _productCount, preCt, postCt;
	private SignUpIn signup;
	private SignUpSignIn signupinPage;
	private UserAgreement userAgreementPage;
	private ProductCatalog catalog;
	private FBsignIn FBone, FBtwo, fbSignIn;
	private AnalyticsReporter analyticsReporter;
	private ProductReporter productReporter;
	private ShareModal shareModal;
	private ShareSetUp shareSetUp;
	private Home home;
	private ShopifyLogin shopifyLogin;
	private Shopify shopify;
	private LandingPage landingPage;
	private String _landingPageUrl, _embedCode;
	private String _errorMsg, _shopifyEmail, _shopifyStorename, _shopifyPassword, _shopifyLoginUrl, _shopifyProductName;
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
	
	public ShoppostShopifyShare (String browser){
		
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
	  public void test_ShopifyShare() throws Exception {
		wait = new WebDriverWait(driver, 10);
		Random rand = new Random();
		Actions move = new Actions(driver);
		signupinPage = new SignUpSignIn(driver);  //this waits for page to load AND initializes the pageFactory proxies
		analyticsReporter = new AnalyticsReporter(driver); 
		productReporter = new ProductReporter(driver);
		catalog = new ProductCatalog(driver);
		shareModal = new ShareModal(driver);
		shareSetUp = new ShareSetUp(driver);
		home = new Home(driver);
		shopifyLogin = new ShopifyLogin(driver);
		shopify = new Shopify(driver);
		landingPage = new LandingPage(driver);
		fbSignIn = new FBsignIn(driver);

		ReadTestJSON.read();
		_td = ReadTestJSON.get_td();
		
		String _browsName = this.browser;
		_browsName = _browsName.substring(1);
		ScreenShots ss = new ScreenShots(driver, _td, _sld, _ltd, _browsName, m);   //screenshot of platform signup
		
		signup = new SignUpIn(driver, _td);   //signup methods
		SignOut logout = new SignOut(driver);
		Window win = new Window(driver);
		
		_usernameFB = _td.getUsernameFB();
		_usernameTwitter = _td.getUsernameTwitter();
		_passkeyFB = _td.getPasswordFB();
		_username = _td.getUsername();
		_password = _td.getPassword();
		_shopifyEmail = _td.getShareData().getShopifyEmail();
		_shopifyStorename = _td.getShareData().getShopifyStorename();
		_shopifyPassword = _td.getShareData().getShopifyPassword();
		_shopifyLoginUrl = _td.getShareData().getShopifyLoginUrl();
		_freshUser = "";
		
		for (int k=0; k<_td.getShareData().getTests().size(); k++) {  //taking one testCase parameter at a time (this by-passes the need for TestRunner
		//for (int k=0; k<TestRunner.getTests().length; k++) {  //taking one testCase parameter at a time from cmd line
	    //for (int k=0; k<1; k++) {   //just a quick test
			//ss.takeTheShot(1, "platform");
  			//_testCase = TestRunner.getTests()[k];
			_testCase = _td.getShareData().getTests().get(k);
			//_testCase = "signupValid";
  			switch (_testCase) {
  			
					
					
				
  			
  				case "openShopifyModal": // opens share overlay FROM shopify store
  					String _prodName;
  					signup.helloPlatform(_td.getShareData().getShopifyLoginUrl());
  					shopifyLogin.login(_shopifyStorename, _shopifyEmail, _shopifyPassword);
  					shopify.toProductList();
  					shopify = new Shopify(driver);
  					_shopifyProductName = shopify.openProduct("2");  //open second product
  					shopify = new Shopify(driver);
  					Thread.sleep(1500);
  					shopify.clickEllipsis();
  					Thread.sleep(1500);
  					shopify.activateShoppostApp();
  					
  					try {
  						_prodName = shareModal.getProductName();
  					} catch (NoSuchElementException ex) {
  						System.out.println("ERROR! Shoppost App did NOT load. go to next test");
  						break;
  					}
  					
					if(_shopifyProductName.equals(shareModal.getProductName())) {
						System.out.println("PASS Shoppost App successfully opened: "+_shopifyProductName);
					} else {
						fail("FAIL - Shoppost App failed to open: "+_shopifyProductName);
					}
					
					Thread.sleep(1000);

  					
  					break;
  			
  				case "linkTab": // 
  					signup.helloPlatform(_td.getShopifyShareUrl());
  					shareModal.goLink();
  					Thread.sleep(2000);
  					if (shareModal.isPreviewVisible()) {
  						System.out.println("PASS Link tab successfully opened - LP URL: "+_landingPageUrl);
					} else {
						fail("FAIL - Link tab failed to open.");
					}
					Thread.sleep(1000);

  					break;
	
  				case "embedTab": // 
  					signup.helloPlatform(_td.getShopifyShareUrl());
  					shareModal.goEmbed();
  					if (shareModal.isEmbedVisible()) {
  						System.out.println("PASS Embed tab successfully opened - Code: "+_embedCode);
					} else {
						fail("FAIL - Embed tab failed to open.");
					}
					Thread.sleep(1000);

  					
  					break;

  				case "shareTab": // 
  					signup.helloPlatform(_td.getShopifyShareUrl());
  					shareModal.goLink();
  					Thread.sleep(1500);
  					shareModal.goShare();
  					
  					if (shareModal.isFBVisible()) {
						System.out.println("PASS Share tab successfully opened.");
					} else {
						fail("FAIL - Share tab failed to open.");
					}
					Thread.sleep(1000);


  					break;
  					
  				case "statsTab": // 
  					signup.helloPlatform(_td.getShopifyShareUrl());
  					shareModal.goStats();
  					
  					if (shareModal.isDonutVisible()) {
						System.out.println("PASS Stats tab successfully opened.");
					} else {
						fail("FAIL - Stats tab failed to open.");
					}
					Thread.sleep(1000);

  					
  					break;

  				case "shopifyFBShare": // FB shares
					signup.helloPlatform(_td.getShopifyShareUrl());
					 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //(FACEBOOK test) get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.shareFB();   //this opens a new tab for FB sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					shareSetUp.loginFacebook(_usernameFB, _passkeyFB);
					_shareTitle = shareSetUp.getShareTitleFB();
					shareSetUp.shareToTimeline(_td.getShareData().getShareMessage());
					
					
					driver.switchTo().window(_mwh);  //back to overlay
					driver.get(_currUrl);   //run with overlay
					Thread.sleep(1000);
					//catalog.getShare();
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
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_username, _password, 0);
					analyticsReporter.toCatalog();
					//Thread.sleep(1500);
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  // get current url which is the share modal
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
					//move.moveToElement(catalog.hoverProductAgain());
					//catalog.getShare();
					Thread.sleep(1000);
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
					
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_username, _password, 0);
					analyticsReporter.toCatalog();
					//Thread.sleep(1500);
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.sharePinterest();   //this opens a new tab for pinterst sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
					shareSetUp.havePinAcct();
					Thread.sleep(1000);
					shareSetUp.loginPinterest(_usernameFB, _passkeyFB);
					_pinTitle = shareSetUp.getPinTitle();  //
					shareSetUp.pinToPinterest();
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal
					//move.moveToElement(catalog.hoverProductAgain());
					//catalog.getShare();
					Thread.sleep(1000);
					//shareModal.goLink();
					System.out.println("pinTitle = "+_pinTitle);
					System.out.println("productName = "+shareModal.getProductName());
					if(_pinTitle.equals(shareModal.getProductName())) {
						System.out.println("PASS Correct data to twitter: "+_pinTitle);
					} else {
						fail("FAIL - invalid data to tiwtter: "+_pinTitle);
					}
					
					
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
				
				case "confirmGooglePlusShare": // see error message
					
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					
					analyticsReporter.toCatalog();
					//Thread.sleep(1500);
					
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //move to random product
					
					catalog.getShare(); //open share modal 
					//collect info about the windows
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.shareGooglePlus();   //this opens a new tab for googlePlus sharing now need to switch driver to that new window
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
					shareSetUp.signinGoogplus(_usernameFB, _td.getPasswordGoog());
					Thread.sleep(8000);
					_googTitle = shareSetUp.getGoogTitle();
					shareSetUp.shareToGoog(_td.getShareData().getGoogComment());  //
					//driver.close(); //closes current window - maybe	

					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal
					//move.moveToElement(catalog.hoverProductAgain());
					//catalog.getShare();
					Thread.sleep(1000);
					//shareModal.goLink();
					System.out.println("googTitle = "+_googTitle);
					System.out.println("productName = "+shareModal.getProductName());
					if(_googTitle.equals(shareModal.getProductName())) {
						System.out.println("PASS Correct data to twitter: "+_googTitle);
					} else {
						fail("FAIL - invalid data to tiwtter: "+_googTitle);
					}
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;

  				case "previewLandingPage": // exercise preview button, confirm landing page opens
  					
  					signup.helloPlatform(_td.getShopifyShareUrl());
  					shareModal.goLink();
  					Thread.sleep(1500);
					beforePopup = driver.getWindowHandles();   //collect windows and then send them to the change window class
					_currUrl = driver.getCurrentUrl();  //get current url which is the share modal
					_mwh = driver.getWindowHandle();   //get current window name
					//now open new window
					shareModal.openLandingPage();   //this opens a new tab for landing page, now need to switch driver to that new window
					
					driver.switchTo().window(win.changeWindowForShare(beforePopup));  //this clicks the link and switches windows because win.changeWindowForShare() returns a URL to switch to
					
  					if (landingPage.isMediaGalleryVisible()) {
  						System.out.println("PASS Landing page successfully opened: "+_landingPageUrl);
					} else {
						fail("FAIL - Landing page failed to open.");
					}
					Thread.sleep(1000);
					driver.close(); //close landing page window/tab
					
					driver.switchTo().window(_mwh);  //back to modal
					driver.get(_currUrl);   //run with modal

  					break;
  					
				
				case "viewRandomStats": //View random product Stats from product hover
					signup.helloPlatform(_td.getBaseUrl());
					Actions movea = new Actions(driver);
					catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					shareModal = PageFactory.initElements(driver, ShareModal.class);  //instantiate the pageObject
					_productCount = catalog.getProductCount();
					movea.moveToElement(catalog.hoverRandomProduct());  //hover random product from Catalog
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getRandomStats();  //click stats button
					Thread.sleep(2000);
					
					if (analyticsReporter.isDonutVisible()) {
						System.out.println("PASS Stats reporting is visible.");
					}else {
						fail("Fail - Stats reporting failed to open.");
					}
					
					shareModal.closeShareModal();
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "viewRandomStatsViaShare": //View random product Stats from share Modal
					signup.helloPlatform(_td.getShopifyDashUrl());
					analyticsReporter.toCatalog();
					Thread.sleep(1000);
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //hover random product from Catalog
					catalog.getShare();  //click share button
					Thread.sleep(1000);
					shareModal.goStats();
					
					if (analyticsReporter.isDonutVisible()) {
						System.out.println("PASS Single shoppost stats reporting is visible.");
					}else {
						fail("Fail - Single shoppost stats reporting failed to open.");
					}

					
					Thread.sleep(1000);
					shareModal.closeShareModal();
					//logout.logoutFromCat();
					
					break;

				case "viewSingleStatsViaDashMS": //View product Stats from Dashboard most shared
					signup.helloPlatform(_td.getShopifyDashUrl());
					analyticsReporter.openMostShared();
					
					if (productReporter.isDonutVisible()) {
						System.out.println("PASS Single shoppost stats reporting is visible.");
					}else {
						fail("Fail - Single shoppost stats reporting failed to open.");
					}

					
					Thread.sleep(2000);
					shareModal.closeShareModal();
					//logout.logoutFromCat();
					
					break;

				case "viewSingleStatsViaDashMR": //View product Stats from Dashboard most referred
					signup.helloPlatform(_td.getShopifyDashUrl());
					analyticsReporter.openMostReferred();
					
					if (productReporter.isDonutVisible()) {
						System.out.println("PASS Single shoppost stats reporting is visible.");
					}else {
						fail("Fail - Single shoppost stats reporting failed to open.");
					}

					
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
