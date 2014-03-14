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
import shoppostPages.ProductCatalog;
import shoppostPages.ProductReporter;
import shoppostPages.ShareModal;
import shoppostPages.ShareSetUp;
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

//import alltest.JavaBeans.AcctSetData;



@RunWith(Parameterized.class)   //this runs the tests serially one browser at a time
//@RunWith(Parallelized.class)    //this will run the tests in parallel and will open a new thread for each browser simultaneously
public class ShoppostCatalog {
	
	private String browser;
	private WebDriver driver;
	private static TestData _td;
	private static SauceLabData _sld;
	//private static AcctSetData _asd;
	private static LPData _ltd;
	private String userName, _username, _password, _freshUser, _usernameFB;
	private String accessCode, _passkey, _passkeyFB, _email, _currUrl, _testCase, _emailAddress;
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
	private Home home;
	private ProductReporter productReporter;
	private ShareSetUp shareSetUp;
	private String _errorMsg, _testPlatform, _folderTestCase;
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
	
	public ShoppostCatalog (String browser){
		
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
	  public void test_productCatalog() throws Exception {
		//wait = new WebDriverWait(driver, 10);
		signupinPage = new SignUpSignIn(driver);  //this waits for page to load AND initializes the pageFactory proxies
		analyticsReporter = new AnalyticsReporter(driver); 
		productReporter = new ProductReporter(driver);
		catalog = new ProductCatalog(driver);
		shareModal = new ShareModal(driver);
		shareSetUp = new ShareSetUp(driver);
		home = new Home(driver);

		Random rand = new Random();
		Actions move = new Actions(driver);
		ReadTestJSON.read();
		_td = ReadTestJSON.get_td();
		
		String _browsName = this.browser;
		_browsName = _browsName.substring(1);
		ScreenShots ss = new ScreenShots(driver, _td, _sld, _ltd, _browsName, m);   //screenshot of platform signup
		
		signup = new SignUpIn(driver, _td);   //signup methods
		SignOut logout = new SignOut(driver);
		
		_usernameFB = _td.getUsernameFB();
		_passkeyFB = _td.getPasswordFB();
		_username = _td.getUsername();
		_password = _td.getPassword();
		_freshUser = "";
		
		//System.out.println("testLength is: "+TestRunner.getTests().length);
		for (int k=0; k<_td.getCatalogTests().getTests().size(); k++) {  //taking one testCase parameter at a time (this by-passes the need for TestRunner
		//for (int k=0; k<TestRunner.getTests().length; k++) {  //taking one testCase parameter at a time from cmd line
	    //for (int k=0; k<1; k++) {   //just a quick test
			//ss.takeTheShot(1, "platform");
  			//_testCase = TestRunner.getTests()[k];
			_testCase = _td.getCatalogTests().getTests().get(k);
			//_testCase = "signupValid";
			_folderTestCase = _td.getUIData().getTcFolder()+_testCase;
  			switch (_testCase) {
  			
					
					
				case "addProductValidURL": // new product displayed in share modal
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					catalog.addProduct();
					//Thread.sleep(1000);
					catalog.scrape(_td.getCatalogTests().getProductURL());  //this should open the share modal
					Thread.sleep(500);
					shareModal.closeShareModal();
					catalog.closeAdd();
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "addProductInvalidURL": // from catalog
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					catalog.addProduct();
					catalog.scrape(_td.getCatalogTests().getProductURL()+"xx");  //bad URL
					Thread.sleep(1000);
					
					_errorMsg = catalog.checkError();
					if(_errorMsg.equals("The product URL is invalid.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("FAIL - requires invalid URL error advisory not: "+_errorMsg);
					}

					
					catalog.closeAdd();
					
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;
					
				case "addProductCancelShare": // see error message
					
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					preCt = catalog.getProductCount();
					catalog.addProduct();
					Thread.sleep(1000);
					catalog.scrapeCancel(_td.getCatalogTests().getProductURL());  //
					Thread.sleep(1000);
					postCt = catalog.getProductCount(); 
					Thread.sleep(1000);
					
					if(preCt == postCt) {
						System.out.println("PASS - no new products added.");
					} else {
						fail("FAIL - product count is off by: "+(preCt - postCt));
					}

					//logout.logoutFromCat();
					
					break;


				case "shareModalTest": //Opens share modal window for random product
					signup.helloPlatform(_td.getBaseUrl());
					analyticsReporter.toCatalog();
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();
					Thread.sleep(1000);
					shareModal.closeShareModal();
					Thread.sleep(1000);
					//logout.logoutFromCat();
					
					break;


					
				case "addProductViaDashboard": 	//
					
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					preCt = catalog.getProductCount();
					catalog.toDash();
					catalog.addProduct();
					Thread.sleep(1000);
					catalog.scrape(_td.getCatalogTests().getProductURL());  //this should open the share modal
					Thread.sleep(1000);
					shareModal.closeShareModal();
					catalog.closeAdd();
					analyticsReporter.toCatalog();  //go back to catalog to count products
					postCt = catalog.getProductCount(); 
					Thread.sleep(1000);
					
					if(preCt == postCt) {
						fail("FAIL - product count should increase: change "+(preCt - postCt));
					} else {
						System.out.println("PASS - new products added.");
					}

					
					Thread.sleep(1000);
					logout.logoutFromCat();
					
					break;
					
					
				case "noProductDisplay": 
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					Thread.sleep(1000);
					
					_errorMsg = signupinPage.checkEmailError();
					if(_errorMsg.equals("Must be a valid email address.")) {
						System.out.println("PASS Correct error advisory: "+_errorMsg);
					} else {
						fail("FAIL - requires invalid email error advisory not: "+_errorMsg);
					}
					
					
					break;
				
				case "deleteProductViaHover": 
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					Thread.sleep(1000);
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());
					catalog.getRandomDelete();
					Thread.sleep(500);
					postCt = catalog.getProductCount();
					//logout.logoutFromCat();
					
					if(_productCount == postCt) {
						fail("FAIL - product count should decrease: change "+(_productCount - postCt));
					} else {
						System.out.println("PASS - product deleted.");
					}

					
					break;

				
				case "deleteProductViaModalShare": 
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());
					catalog.getShare();
					Thread.sleep(1000);
					shareModal.deleteProdViaModal();
					postCt = catalog.getProductCount();
					Thread.sleep(500);

					if(_productCount == postCt) {
						fail("FAIL - product count should decrease: change "+(_productCount - postCt));
					} else {
						System.out.println("PASS - product deleted.");
					}

					
					
					break;

				
				case "deleteProductViaModalStats": 
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());
					catalog.getShare();
					shareModal.goStats();
					Thread.sleep(1000);
					shareModal.deleteProdViaModal();
					postCt = catalog.getProductCount();
					Thread.sleep(500);

					if(_productCount == postCt) {
						fail("FAIL - product count should decrease: change "+(_productCount - postCt));
					} else {
						System.out.println("PASS - product deleted.");
					}

					break;

				
				case "cancelDeleteProduct": 
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());
					catalog.getShare();
					Thread.sleep(1000);
					shareModal.deleteProdViaModalCancel();
					postCt = catalog.getProductCount();
					Thread.sleep(500);

					if(_productCount == postCt) {
						System.out.println("PASS - product deletion cancelled.");
					} else {
						fail("FAIL - product count should not change: change "+(_productCount - postCt));
					}

					break;

				
				case "viewRandomStats": //View random product Stats from product hover
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //hover random product from Catalog
					catalog.getRandomStats();  //click stats button
					Thread.sleep(2000);
					shareModal.closeShareModal();
					Thread.sleep(500);
					//logout.logoutFromCat();
					
					break;
					
				case "viewRandomStatsViaShare": //View random product Stats from share Modal
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.toCatalog();
					_productCount = catalog.getProductCount();
					move.moveToElement(catalog.hoverRandomProduct());  //hover random product from Catalog
					//catalog = PageFactory.initElements(driver, ProductCatalog.class);  //instantiate the pageOject 
					catalog.getShare();  //click share button
					Thread.sleep(1000);
					shareModal.goStats();
					Thread.sleep(2000);
					shareModal.closeShareModal();
					//logout.logoutFromCat();
					
					break;

				case "viewSingleStatsViaDashMS": //View product Stats from Dashboard most shared
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.openMostShared();
					Thread.sleep(2000);
					shareModal.closeShareModal();
					//logout.logoutFromCat();
					
					break;

				case "viewSingleStatsViaDashMR": //View product Stats from Dashboard most referred
					signup.helloPlatform(_td.getBaseUrl());
					signup.signInTest(_email, _password, 0);
					analyticsReporter.openMostReferred();
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
