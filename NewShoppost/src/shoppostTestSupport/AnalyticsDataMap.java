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


public class AnalyticsDataMap {
  private WebDriver driver;
  private String _browser;
  private WebDriverWait wait;
  
   private HashMap _hm;
   
  private AnalyticsReporter analyticsReporter;
  
  public AnalyticsDataMap(WebDriver _driver) {
		driver = _driver;
		
	} 

 
  public HashMap buildMap(String range) throws Exception {  //collect analytics data from dashboard
	  	analyticsReporter = PageFactory.initElements(driver, AnalyticsReporter.class);  //instantiate the pageOject 
	  	_hm = new HashMap();
	  				                                         //open product overview page
	  	Thread.sleep(1000);
	  	
	  	analyticsReporter.selectDateRange(range);  //select 7, 14, or 30 date range
	  	
	  	_hm.put("dateRange", analyticsReporter.getDateRange());  //String
	  	
	  	_hm.put("socialReactions", analyticsReporter.getSocialReactions());  //String[10]
	  	
	  	_hm.put("referralTotal", analyticsReporter.getReferralTotal());  //String
	  	_hm.put("facebookReferrals", analyticsReporter.getFBReferrals());  //String[2]
	  	_hm.put("twitterReferrals", analyticsReporter.getTwitReferrals());  //String[2]
	  	_hm.put("pinterestReferrals", analyticsReporter.getPinReferrals());  //String[2]
	  	_hm.put("googlePlusReferrals", analyticsReporter.getGoogReferrals());  //String[2]
	  	_hm.put("otherReferrals", analyticsReporter.getOtherReferrals());  //String[2]
	  	
	  	_hm.put("productMostAllSharesTop3", analyticsReporter.getMostSharesTop3("allNetworks"));  //String[6]
	  	_hm.put("productMostFBSharesTop3", analyticsReporter.getMostSharesTop3("facebook"));  //String[6]
	  	_hm.put("productMostTwitSharesTop3", analyticsReporter.getMostSharesTop3("twitter"));  //String[6]
	  	_hm.put("productMostPinSharesTop3", analyticsReporter.getMostSharesTop3("pinterest"));  //String[6]
	  	_hm.put("productMostGoogSharesTop3", analyticsReporter.getMostSharesTop3("googlPlus"));  //String[6]
	  	
	  	_hm.put("productMostAllReferralsTop3", analyticsReporter.getMostReferralsTop3("allChannels"));  //String[6]
	  	_hm.put("productMostFBReferralsTop3", analyticsReporter.getMostReferralsTop3("facebook"));  //String[6]
	  	_hm.put("productMostTwitReferralsTop3", analyticsReporter.getMostReferralsTop3("twitter"));  //String[6]
	  	_hm.put("productMostPinReferralsTop3", analyticsReporter.getMostReferralsTop3("pinterest"));  //String[6]
	  	_hm.put("productMostGoogReferralsTop3", analyticsReporter.getMostReferralsTop3("googlePlus"));  //String[6]
	  	_hm.put("productMostOtherReferralsTop3", analyticsReporter.getMostReferralsTop3("other"));  //String[6]
	  	
		
		return _hm;
	
	}
}