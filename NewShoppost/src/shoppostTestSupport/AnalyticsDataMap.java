package shoppostTestSupport;

import static org.junit.Assert.fail;

import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import shoppostPages.AnalyticsReporter;
import shoppostPages.ProductReporter;


public class AnalyticsDataMap {
  private WebDriver driver;
  private String _browser;
  private WebDriverWait wait;
  
  private HashMap _hm;
   
  private AnalyticsReporter analyticsReporter;
  private ProductReporter productReporter;
  
  public AnalyticsDataMap(WebDriver _driver) {
		driver = _driver;
		
	} 

 
  public HashMap buildFullMap(String range) throws Exception {  //collect analytics data from dashboard
	  	analyticsReporter = new AnalyticsReporter(driver);  //instantiate the pageOject 
	  	_hm = new HashMap();
	  				                                         //open product overview page
	  	Thread.sleep(1000);
	  	
	  	analyticsReporter.selectDateRange(range);  //select 7, 14, or 30 date range
	  	
	  	_hm.put("dateRange", analyticsReporter.getDateRange());  //String
	  	
	  	_hm.put("socialReactions", analyticsReporter.getSocialReactions());  //String[10]
	  	
	  	_hm.put("geoLocations", analyticsReporter.getGeoLocation()); //just top 2, String[4]
	  	
	  	_hm.put("redirectTotal", analyticsReporter.getRedirectTotal());  //String
	  	_hm.put("facebookRedirects", analyticsReporter.getFBRedirects());  //String[2]
	  	_hm.put("twitterRedirects", analyticsReporter.getTwitRedirects());  //String[2]
	  	_hm.put("pinterestRedirects", analyticsReporter.getPinRedirects());  //String[2]
	  	_hm.put("googlePlusRedirects", analyticsReporter.getGoogRedirects());  //String[2]
	  	_hm.put("otherRedirects", analyticsReporter.getOtherRedirects());  //String[2]
	  	
	  	_hm.put("productMostAllSharesTop3", analyticsReporter.getMostSharesTop3("allNetworks"));  //String[6]
	  	_hm.put("productMostFBSharesTop3", analyticsReporter.getMostSharesTop3("facebook"));  //String[6]
	  	_hm.put("productMostTwitSharesTop3", analyticsReporter.getMostSharesTop3("twitter"));  //String[6]
	  	_hm.put("productMostPinSharesTop3", analyticsReporter.getMostSharesTop3("pinterest"));  //String[6]
	  	_hm.put("productMostGoogSharesTop3", analyticsReporter.getMostSharesTop3("googlePlus"));  //String[6]
	  	
	  	_hm.put("productMostAllReferralsTop3", analyticsReporter.getMostReferralsTop3("allChannels"));  //String[6]
	  	_hm.put("productMostFBReferralsTop3", analyticsReporter.getMostReferralsTop3("facebook"));  //String[6]
	  	_hm.put("productMostTwitReferralsTop3", analyticsReporter.getMostReferralsTop3("twitter"));  //String[6]
	  	_hm.put("productMostPinReferralsTop3", analyticsReporter.getMostReferralsTop3("pinterest"));  //String[6]
	  	_hm.put("productMostGoogReferralsTop3", analyticsReporter.getMostReferralsTop3("googlePlus"));  //String[6]
	  	_hm.put("productMostOtherReferralsTop3", analyticsReporter.getMostReferralsTop3("other"));  //String[6]
	  	
		
		return _hm;
	
	}
  
  public HashMap buildProdMap(String range) throws Exception {  //collect analytics data from dashboard
	  	productReporter = new ProductReporter(driver);  //instantiate the pageOject 
	  	_hm = new HashMap();
	  				                                         //open product overview page
	  	Thread.sleep(1000);
	  	
	  	//analyticsReporter.selectDateRange(range);  //select 7, 14, or 30 date range
	  	
	  	//_hm.put("dateRange", analyticsReporter.getDateRange());  //String
	  	
	  	_hm.put("socialReactions", productReporter.getSocialReactions());  //String[10]
	  	
	  	_hm.put("geoLocations", productReporter.getGeoLocation()); //just top 2, String[4]
	  	
	  	_hm.put("redirectTotal", productReporter.getRedirectTotal());  //String
	  	_hm.put("facebookRedirects", productReporter.getFBRedirects());  //String[2]
	  	_hm.put("twitterRedirects", productReporter.getTwitRedirects());  //String[2]
	  	_hm.put("pinterestRedirects", productReporter.getPinRedirects());  //String[2]
	  	_hm.put("googlePlusRedirects", productReporter.getGoogRedirects());  //String[2]
	  	_hm.put("otherRedirects", productReporter.getOtherRedirects());  //String[2]
	  	
		
		return _hm;
	
	}

}