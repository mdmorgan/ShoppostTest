package shoppostPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class AnalyticsReporter {

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
	@FindBy (id ="socialFacebookCount0")
	private WebElement fbViewCount;
	@FindBy (id ="socialFacebookCount1")
	private WebElement fbShareCount;
	@FindBy (id ="socialFacebookCount2")
	private WebElement fbLikeCount;
	@FindBy (id ="socialTwitterCount0")
	private WebElement twitterViewCount;
	@FindBy (id ="socialTwitterCount1")
	private WebElement twitterTweetCount;
	@FindBy (id ="socialPinterestCount0")
	private WebElement pinterestViewCount;
	@FindBy (id ="socialPinterestCount1")
	private WebElement pinterestPinCount;
	@FindBy (id ="socialGooglePlusCount0")
	private WebElement googleViewCount;
	@FindBy (id ="socialGooglePlusCount1")
	private WebElement googlePlus1Count;
	@FindBy (id = "socialGooglePlusCount2")
	private WebElement googleShareCount;
	
	//referrals
	@FindBy (id = "totalReferrals")
	private WebElement allReferrals;
	
	@FindBy (xpath = "//tr[@class='facebook']/td[@id='referralPercent0']")
	private WebElement referralsFBPercent;
	@FindBy (xpath = "//tr[@class='facebook']/td[@id='referralValue0']")
	private WebElement referralsFBCount;
	@FindBy (xpath = "//tr[@class='twitter']/td[@id='referralPercent1']")
	private WebElement referralsTwitterPercent;
	@FindBy (xpath = "//tr[@class='twitter']/td[@id='referralValue1']")
	private WebElement referralsTwitterCount;
	@FindBy (xpath = "//tr[@class='pinterest']/td[@id='referralPercent2']")
	private WebElement referralsPinterestPercent;
	@FindBy (xpath = "//tr[@class='pinterest']/td[@id='referralValue2']")
	private WebElement referralsPinterestCount;
	@FindBy (xpath = "//tr[@class='googleplus']/td[@id='referralPercent3']")
	private WebElement referralsGoogleplusPercent;
	@FindBy (xpath = "//tr[@class='googleplus']/td[@id='referralValue3']")
	private WebElement referralsGoogleplusCount;
	@FindBy (xpath = "//tr[@class='other']/td[@id='referralPercent4']")
	private WebElement referralsOtherPercent;
	@FindBy (xpath = "//tr[@class='other']/td[@id='referralValue4']")
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
	@FindBy (id = "mostreferralsDropDown0")
	private WebElement mostAllReferralsMenu;
	@FindBy (id = "mostreferralsDropDown1")
	private WebElement mostFBReferralsMenu;
	@FindBy (id = "mostreferralsDropDown2")
	private WebElement mostTwitReferralsMenu;
	@FindBy (id = "mostreferralsDropDown3")
	private WebElement mostPinReferralsMenu;
	@FindBy (id = "mostreferralsDropDown4")
	private WebElement mostGoogReferralsMenu;
	@FindBy (id = "mostreferralsDropDown5")
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
	
	@FindBy (id ="dateRangeButton")
	private WebElement dateRangeBtn;
	@FindBy (id ="dateRange0")
	private WebElement dateRange7;
	@FindBy (id ="dateRange1")
	private WebElement dateRange14;
	@FindBy (id ="dateRange2")
	private WebElement dateRange30;
	

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
		_otherReferralData[0] = referralsGoogleplusPercent.getText();
		_otherReferralData[1] = referralsGoogleplusCount.getText();
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
