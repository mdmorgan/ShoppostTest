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

public class AnalyticsReporter {
	private int DRIVER_WAIT = 15;

	@FindBy(id = "accountMenu")
	private WebElement acctmenu;
	
	@FindBy (id = "accountButton")
	private WebElement acctBtn;
	
	@FindBy (className = "name")
	private WebElement acctName;
	
	@FindBy (id = "accountSettings")
	private WebElement acctSettings;
	
	@FindBy (id = "accountSignOut")
	private WebElement signOut;

	@FindBy (id = "navDashboard")
	private WebElement dashboardIcon;
	
	@FindBy (id = "navProducts")
	private WebElement productsIcon;
	
	//socialReactions
	@FindBy (xpath ="//li/strong[@id='dashboardSocialFacebookCount0']")
	private WebElement fbViewCount;
	@FindBy (xpath ="//li/strong[@id='dashboardSocialFacebookCount1']")
	private WebElement fbShareCount;
	@FindBy (xpath ="//li/strong[@id='dashboardSocialFacebookCount2']")
	private WebElement fbLikeCount;
	@FindBy (xpath ="//li/strong[@id='dashboardSocialTwitterCount0']")
	private WebElement twitterViewCount;
	@FindBy (xpath ="//li/strong[@id='dashboardSocialTwitterCount1']")
	private WebElement twitterTweetCount;
	@FindBy (xpath ="//li/strong[@id='dashboardSocialPinterestCount0']")
	private WebElement pinterestViewCount;
	@FindBy (xpath ="//li/strong[@id='dashboardSocialPinterestCount1']")
	private WebElement pinterestPinCount;
	@FindBy (xpath ="//li/strong[@id='dashboardSocialGoogleplusCount0']")
	private WebElement googleViewCount;
	@FindBy (xpath ="//li/strong[@id='dashboardSocialGoogleplusCount1']")
	private WebElement googlePlus1Count;
	@FindBy (xpath ="//li/strong[@id='dashboardSocialGoogleplusCount2']")
	private WebElement googleShareCount;
	
	//geoLocations
	@FindBy (id = "dashboardLocationName0")
	private WebElement topGeoLocationName;
	@FindBy (id = "dashboardLocationPercent0")
	private WebElement topGeoLocationPercent;
	@FindBy (id = "dashboardLocationName1")
	private WebElement secondGeoLocationName;
	@FindBy (id = "dashboardLocationPercent1")
	private WebElement secondGeoLocationPercent;
	
	//referrals
	@FindBy (id = "dashboardTotalReferrals")
	private WebElement allReferrals;
	
	@FindBy (xpath = "//tr[@class='facebook']/td[@id='dashboardReferralPercent0']")
	private WebElement referralsFBPercent;
	@FindBy (xpath = "//tr[@class='facebook']/td[@id='dashboardReferralValue0']")
	private WebElement referralsFBCount;
	@FindBy (xpath = "//tr[@class='twitter']/td[@id='dashboardReferralPercent1']")
	private WebElement referralsTwitterPercent;
	@FindBy (xpath = "//tr[@class='twitter']/td[@id='dashboardReferralValue1']")
	private WebElement referralsTwitterCount;
	@FindBy (xpath = "//tr[@class='pinterest']/td[@id='dashboardReferralPercent2']")
	private WebElement referralsPinterestPercent;
	@FindBy (xpath = "//tr[@class='pinterest']/td[@id='dashboardReferralValue2']")
	private WebElement referralsPinterestCount;
	@FindBy (xpath = "//tr[@class='googleplus']/td[@id='dashboardReferralPercent3']")
	private WebElement referralsGoogleplusPercent;
	@FindBy (xpath = "//tr[@class='googleplus']/td[@id='dashboardReferralValue3']")
	private WebElement referralsGoogleplusCount;
	@FindBy (xpath = "//tr[@class='other']/td[@id='dashboardReferralPercent4']")
	private WebElement referralsOtherPercent;
	@FindBy (xpath = "//tr[@class='other']/td[@id='dashboardReferralValue4']")
	private WebElement referralsOtherCount;
	
	//most shared single product drop menu
	@FindBy (id = "mostSharesDropDownButton")
	private WebElement mostSharesMenuBtn;
	@FindBy (id = "mostSharesDropDown0")
	private WebElement mostAllSharesMenu;
	@FindBy (id = "mostSharesDropDown1")
	private WebElement mostFBSharesMenu;
	@FindBy (id = "mostSharesDropDown2")
	private WebElement mostTwitSharesMenu;
	@FindBy (id = "mostSharesDropDown3")
	private WebElement mostPinSharesMenu;
	@FindBy (id = "mostSharesDropDown4")
	private WebElement mostGoogSharesMenu;
	
	
	//most shared product
	@FindBy (id = "mostSharesName0")
	private WebElement productNameMostShares;
	@FindBy (id = "mostSharesValue0")
	private WebElement productValueMostShares;
	@FindBy (id = "mostSharesName1")
	private WebElement productName2ndMostShares;
	@FindBy (id = "mostSharesValue1")
	private WebElement productValue2ndMostShares;
	@FindBy (id = "mostSharesName2")
	private WebElement productName3rdMostShares;
	@FindBy (id = "mostSharesValue2")
	private WebElement productValue3rdMostShares;
	
	
	//most referred single product drop menu
	@FindBy (id = "mostReferralsDropDownButton")
	private WebElement mostReferralsMenuBtn;
	@FindBy (id = "mostReferralsDropDown0")
	private WebElement mostAllReferralsMenu;
	@FindBy (id = "mostReferralsDropDown1")
	private WebElement mostFBReferralsMenu;
	@FindBy (id = "mostReferralsDropDown2")
	private WebElement mostTwitReferralsMenu;
	@FindBy (id = "mostReferralsDropDown3")
	private WebElement mostPinReferralsMenu;
	@FindBy (id = "mostReferralsDropDown4")
	private WebElement mostGoogReferralsMenu;
	@FindBy (id = "mostReferralsDropDown5")
	private WebElement mostOtherReferralsMenu;
	
	//most referred product
	@FindBy (id = "mostReferralsName0")
	private WebElement productNameMostReferrals;
	@FindBy (id = "mostReferralsValue0")
	private WebElement productValueMostReferrals;
	@FindBy (id = "mostReferralsName1")
	private WebElement productName2ndMostReferrals;
	@FindBy (id = "mostReferralsValue1")
	private WebElement productValue2ndMostReferrals;
	@FindBy (id = "mostReferralsName2")
	private WebElement productName3rdMostReferrals;
	@FindBy (id = "mostReferralsValue2")
	private WebElement productValue3rdMostReferrals;
	
	@FindBy (id ="dateRange")
	private WebElement dateRangeBtn;
	@FindBy (id ="dateRange0")
	private WebElement dateRange7;
	@FindBy (id ="dateRange1")
	private WebElement dateRange14;
	@FindBy (id ="dateRange2")
	private WebElement dateRange30;
	
	public AnalyticsReporter (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}

	

	public void signOut() {
		signOut.click();
	}
	public void openAcctMenu() {
		acctBtn.click();
	}
	public void toAcctSet() {
		acctSettings.click();
	}
	public void toCatalog() {
		productsIcon.click();
	}
	public String getEmailAddress() {
		String emailAddress = "";
		emailAddress = acctName.getText();
		return emailAddress;
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
	
	public String getReferralTotal() {
		return allReferrals.getText();
	}
	public String[] getFBReferrals() {
		String _fbReferralData[] = new String[2];
		_fbReferralData[0] = referralsFBPercent.getText();
		_fbReferralData[1] = referralsFBCount.getText();
		return _fbReferralData;
	}
	public String[] getTwitReferrals() {
		String _twitReferralData[] = new String[2];
		_twitReferralData[0] = referralsTwitterPercent.getText();
		_twitReferralData[1] = referralsTwitterCount.getText();
		return _twitReferralData;
	}
	public String[] getPinReferrals() {
		String _pinReferralData[] = new String[2];
		_pinReferralData[0] = referralsPinterestPercent.getText();
		_pinReferralData[1] = referralsPinterestCount.getText();
		return _pinReferralData;
	}
	public String[] getGoogReferrals() {
		String _googReferralData[] = new String[2];
		_googReferralData[0] = referralsGoogleplusPercent.getText();
		_googReferralData[1] = referralsGoogleplusCount.getText();
		return _googReferralData;
	}
	public String[] getOtherReferrals() {
		String _otherReferralData[] = new String[2];
		_otherReferralData[0] = referralsOtherPercent.getText();
		_otherReferralData[1] = referralsOtherCount.getText();
		return _otherReferralData;
	}
	
	public String[] getMostSharesTop3(String network)  {
		String _mostSharesTop3[] = new String[6];
		mostSharesMenuBtn.click();  //pulls down menu
		switch (network) {
			case "allNetworks":
				mostAllSharesMenu.click();  //selects all networks
				break;

			case "facebook":
				mostFBSharesMenu.click();  //selects facebook
				break;
				
			case "twitter":
				mostTwitSharesMenu.click();
				break;
				
			case "pinterest":
				mostPinSharesMenu.click();
				break;
				
			case "googlePlus":
				mostGoogSharesMenu.click();
				break;
		}
		_mostSharesTop3[0] = productNameMostShares.getText();
		_mostSharesTop3[1] = productValueMostShares.getText();
		_mostSharesTop3[2] = productName2ndMostShares.getText();
		_mostSharesTop3[3] = productValue2ndMostShares.getText();
		_mostSharesTop3[4] = productName3rdMostShares.getText();
		_mostSharesTop3[5] = productValue3rdMostShares.getText();
		return _mostSharesTop3;
	}
	public String[] getMostReferralsTop3(String channel)  {
		String _mostReferralsTop3[] = new String[6];
		mostReferralsMenuBtn.click();  //pulls down menu
		switch (channel) {
			case "allChannels":
				mostAllReferralsMenu.click();  //selects all networks
				break;

			case "facebook":
				mostFBReferralsMenu.click();  //selects facebook
				break;
				
			case "twitter":
				mostTwitReferralsMenu.click();
				break;
				
			case "pinterest":
				mostPinReferralsMenu.click();
				break;
				
			case "googlePlus":
				mostGoogReferralsMenu.click();
				break;
				
			case "other":
				mostOtherReferralsMenu.click();
				break;
		}
		_mostReferralsTop3[0] = productNameMostReferrals.getText();
		_mostReferralsTop3[1] = productValueMostReferrals.getText();
		_mostReferralsTop3[2] = productName2ndMostReferrals.getText();
		_mostReferralsTop3[3] = productValue2ndMostReferrals.getText();
		_mostReferralsTop3[4] = productName3rdMostReferrals.getText();
		_mostReferralsTop3[5] = productValue3rdMostReferrals.getText();
		return _mostReferralsTop3;
	}
	
	

}
