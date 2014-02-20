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
  private String _browserName, _test, _setup;
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

 
  public void takeTheShot(int c, String test, String setup) throws Exception {
	  int _c = c;
	  _test = test;
	  _setup = setup;
	  	
		if (("sauceLabsRemote1").equals(_browserName)||("sauceLabsRemote2").equals(_browserName)||("sauceLabsRemote3").equals(_browserName)||("sauceLabsRemote4").equals(_browserName)) {
			WebDriver augmentedDriver = new Augmenter().augment(_driver);
			if (("landingPage").equals(_test)) {
				File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File("c:\\Users\\DIY\\LPScreenshots\\lp_"+_sld.getlpName()+_sld.getSauceBrowser().get(m).getBOS()+_setup+_c+".png"));
			} else {
				File screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File("c:\\Users\\DIY\\LPScreenshots\\platform_"+_sld.getSauceBrowser().get(m).getBOS()+_setup+_c+".png"));
			}
			System.out.println("screen shot taken from sauce");
			
		}else {
			if (("landingPage").equals(_test)) {
			File screenshot = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("c:\\Users\\DIY\\LPScreenshots\\lp_"+_lpd.getlpName()+_browserName+_setup+_c+".png"));
			
			}else if (("platform").equals(_test)) {
				File screenshot = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshot, new File("c:\\Users\\DIY\\LPScreenshots\\platform_"+_browserName+_setup+_c+".png"));
			}
		}
	  return ;
  }
  
  
	 

  //public String getSkuQtyPrice() { return _skuQtyPrice; }
}


