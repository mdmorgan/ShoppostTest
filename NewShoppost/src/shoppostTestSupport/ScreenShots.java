package shoppostTestSupport;

import static org.junit.Assert.fail;

import java.io.File;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shoppostBeans.LPData;
import shoppostBeans.TestData;
import shoppostBeans.SauceLabData;

//import alltest.JavaBeans.AcctSetData;

public class ScreenShots {
  
  //private String _browser;
  private WebDriverWait wait;
  private WebDriver _driver, augmentedDriver;
  private static TestData _td;
  private static SauceLabData _sld;
  private static LPData _lpd;
  private String _browserName, _test, _setup, _platform;
  private int j,m,n;
  
  
  public ScreenShots(WebDriver driver, TestData td, SauceLabData sld, LPData lpd, String browserName, int _m) {    //NOTE: already on product detail edit page
		_driver = driver;
		_td = td;
		_sld = sld;
		_lpd = lpd;
		m = _m;
		_browserName = browserName;
		wait = new WebDriverWait(driver, 30);
	} 

 
  public void takeTheShot(int c, String test, String tcPlatform, String testCase) throws Exception {
	  int _c = c+1;
	  _test = test;
	  _setup = testCase;
	  _platform = tcPlatform;
	  
	  	
		if (("sauceLabsRemote1").equals(_browserName)||("sauceLabsRemote2").equals(_browserName)||("sauceLabsRemote3").equals(_browserName)||("sauceLabsRemote4").equals(_browserName)) {//if using sauceLabs, start augmented driver
			WebDriver augmentedDriver = new Augmenter().augment(_driver);
			if (("landingPage").equals(_test)) {  //if test is a landing page, get screenshot and save with this name
				File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File("c:\\Users\\DIY\\LPScreenshots\\lp_"+_sld.getlpName()+_sld.getSauceBrowser().get(m).getBOS()+_setup+_c+".png"));
			} else {  //if not landing page, get screenshot and save this way
				File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File("c:\\Users\\DIY\\LPScreenshots\\"+_sld.getSauceBrowser().get(m).getBOS()+_setup+_c+".png"));
			}
			System.out.println("screen shot taken from sauce");
			
		}else {  //if not saucelabs
			if (("landingPage").equals(_test)) {
			File screenshot = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("c:\\Users\\DIY\\LPScreenshots\\lp_"+_lpd.getlpName()+_platform+_setup+_c+".png"));
			
			}else if (("platform").equals(_test)) {
				File screenshot = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File("c:\\Users\\DIY\\LPScreenshots\\"+_platform+"_"+_setup+"_"+_c+".png"));
			}
		}
	  return ;
  }
  
  
	 

  //public String getSkuQtyPrice() { return _skuQtyPrice; }
}


