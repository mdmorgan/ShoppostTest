package shoppostPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.WebElement;

public class ProductReporter {
	private int DRIVER_WAIT = 15;


	@FindBy (id = "navDashboard")
	private WebElement dashboardIcon;
	
	@FindBy (id = "navProducts")
	private WebElement productsIcon;
	
	@FindBy (className = "chart")
	private WebElement donutChart;
	
	//socialReactions
	@FindBy (xpath ="//li/strong[@id='statsSocialFacebookCount0']")
	private WebElement fbViewCount;
	@FindBy (xpath ="//li/strong[@id='statsSocialFacebookCount1']")
	private WebElement fbShareCount;
	@FindBy (xpath ="//li/strong[@id='statsSocialFacebookCount2']")
	private WebElement fbLikeCount;
	@FindBy (xpath ="//li/strong[@id='statsSocialTwitterCount0']")
	private WebElement twitterViewCount;
	@FindBy (xpath ="//li/strong[@id='statsSocialTwitterCount1']")
	private WebElement twitterTweetCount;
	@FindBy (xpath ="//li/strong[@id='statsSocialPinterestCount0']")
	private WebElement pinterestViewCount;
	@FindBy (xpath ="//li/strong[@id='statsSocialPinterestCount1']")
	private WebElement pinterestPinCount;
	@FindBy (xpath ="//li/strong[@id='statsSocialGoogleplusCount0']")
	private WebElement googleViewCount;
	@FindBy (xpath ="//li/strong[@id='statsSocialGoogleplusCount1']")
	private WebElement googlePlus1Count;
	@FindBy (xpath ="//li/strong[@id='statsSocialGoogleplusCount2']")
	private WebElement googleShareCount;
	
	//geoLocations
	@FindBy (id = "statsLocationName0")
	private WebElement topGeoLocationName;
	@FindBy (id = "statsLocationPercent0")
	private WebElement topGeoLocationPercent;
	@FindBy (id = "statsLocationName1")
	private WebElement secondGeoLocationName;
	@FindBy (id = "statsLocationPercent1")
	private WebElement secondGeoLocationPercent;
	
	//referrals
	@FindBy (id = "statsTotalReferrals")
	private WebElement allRedirects;
	
	@FindBy (xpath = "//tr[@class='facebook']/td[@id='statsReferralPercent0']")
	private WebElement redirectsFBPercent;
	@FindBy (xpath = "//tr[@class='facebook']/td[@id='statsReferralValue0']")
	private WebElement redirectsFBCount;
	@FindBy (xpath = "//tr[@class='twitter']/td[@id='statsReferralPercent1']")
	private WebElement redirectsTwitterPercent;
	@FindBy (xpath = "//tr[@class='twitter']/td[@id='statsReferralValue1']")
	private WebElement redirectsTwitterCount;
	@FindBy (xpath = "//tr[@class='pinterest']/td[@id='statsReferralPercent2']")
	private WebElement redirectsPinterestPercent;
	@FindBy (xpath = "//tr[@class='pinterest']/td[@id='statsReferralValue2']")
	private WebElement redirectsPinterestCount;
	@FindBy (xpath = "//tr[@class='googleplus']/td[@id='statsReferralPercent3']")
	private WebElement redirectsGoogleplusPercent;
	@FindBy (xpath = "//tr[@class='googleplus']/td[@id='statsReferralValue3']")
	private WebElement redirectsGoogleplusCount;
	@FindBy (xpath = "//tr[@class='other']/td[@id='statsReferralPercent4']")
	private WebElement redirectsOtherPercent;
	@FindBy (xpath = "//tr[@class='other']/td[@id='statsReferralValue4']")
	private WebElement redirectsOtherCount;
	
	
	@FindBy (id ="dateRange")
	private WebElement dateRangeBtn;
	@FindBy (id ="dateRange0")
	private WebElement dateRange7;
	@FindBy (id ="dateRange1")
	private WebElement dateRange14;
	@FindBy (id ="dateRange2")
	private WebElement dateRange30;
	
	public ProductReporter (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}
	public boolean isDonutVisible() {
		return donutChart.isDisplayed();
	}
	public void toCatalog() {
		productsIcon.click();
	}
	public void toDashboard() {
		dashboardIcon.click();
	}
	public void selectDateRange(String range) {
		dateRangeBtn.click();
		switch (range) {
		case "7":
			dateRange7.click();  //
			break;

		case "14":
			dateRange14.click();  //
			break;
			
		case "30":
			dateRange30.click();
			break;
		}
	}
	public String getDateRange() {
		return dateRangeBtn.getText();
	}
	public String[] getSocialReactions() {
		String _socialData[] = new String[10];
		_socialData[0] = fbViewCount.getText();
		_socialData[1] = fbShareCount.getText();
		_socialData[2] = fbLikeCount.getText();
		_socialData[3] = twitterViewCount.getText();
		_socialData[4] = twitterTweetCount.getText();
		_socialData[5] = pinterestViewCount.getText();
		_socialData[6] = pinterestPinCount.getText();
		_socialData[7] = googleViewCount.getText();
		_socialData[8] = googlePlus1Count.getText();
		_socialData[9] = googleShareCount.getText();
		return _socialData;
	}
	public String[] getGeoLocation() {
		String _geoLocData[] = new String[4];
		_geoLocData[0] = topGeoLocationName.getText();
		_geoLocData[1] = topGeoLocationPercent.getText();
		_geoLocData[2] = secondGeoLocationName.getText();
		_geoLocData[3] = secondGeoLocationPercent.getText();
		return _geoLocData;
	}
	
	public String getRedirectTotal() {
		return allRedirects.getText();
	}
	public String[] getFBRedirects() {
		String _fbRedirectData[] = new String[2];
		_fbRedirectData[0] = redirectsFBPercent.getText();
		_fbRedirectData[1] = redirectsFBCount.getText();
		return _fbRedirectData;
	}
	public String[] getTwitRedirects() {
		String _twitRedirectData[] = new String[2];
		_twitRedirectData[0] = redirectsTwitterPercent.getText();
		_twitRedirectData[1] = redirectsTwitterCount.getText();
		return _twitRedirectData;
	}
	public String[] getPinRedirects() {
		String _pinRedirectData[] = new String[2];
		_pinRedirectData[0] = redirectsPinterestPercent.getText();
		_pinRedirectData[1] = redirectsPinterestCount.getText();
		return _pinRedirectData;
	}
	public String[] getGoogRedirects() {
		String _googRedirectData[] = new String[2];
		_googRedirectData[0] = redirectsGoogleplusPercent.getText();
		_googRedirectData[1] = redirectsGoogleplusCount.getText();
		return _googRedirectData;
	}
	public String[] getOtherRedirects() {
		String _otherRedirectData[] = new String[2];
		_otherRedirectData[0] = redirectsOtherPercent.getText();
		_otherRedirectData[1] = redirectsOtherCount.getText();
		return _otherRedirectData;
	}
	
	

}

