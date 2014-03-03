package shoppostPages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class ShareSetUp {

	@FindBy(id = "u_0_4")
	private WebElement shareMessage;
	
	@FindBy(id = "u_0_0")
	private WebElement cancelButton;
	
	@FindBy (name = "share")
	private WebElement shareLink;
	
	@FindBy (id = "u_0_d")
	private WebElement timelineMenu;
	
	@FindBy (id = "u_0_h")
	private WebElement friendEntry;
	
	@FindBy (id = "typeahead_list_u_0_f")
	private WebElement pickFriend;
	
	@FindBy (className = "UIShareStage_Title")
	private WebElement shareTitleFB;
	
	@FindBy (id = "email")
	private WebElement emailFB;
	
	@FindBy (id = "pass")
	private WebElement fbPassword;
	
	@FindBy(id = "u_0_1")
	private WebElement fbLogin;
	
	@FindBy(id = "status")
	private WebElement tweet;
	
	@FindBy(id = "username_or_email")
	private WebElement twitterUser;
	
	@FindBy(id = "password")
	private WebElement twitterPass;
	
	@FindBy(xpath = "//fieldset[@class='submit']/input[@id='char-count']/following-sibling::input")
	private WebElement twitterSignin;
	
	@FindBy(className = "alreadyAPinner")
	private WebElement gotPinAcct;
	
	@FindBy(className = "email")
	private WebElement pinEmail;
	
	@FindBy(name = "password")
	private WebElement pinPass;
	
	@FindBy(id = "pinFormDescription")
	private WebElement pinDescrip;
	
	@FindBy(xpath = "//div[@class='formFooterButtons']/button[contains(@class,'primary')]")
	private WebElement pinItLogin;
	
	@FindBy(id = "Email")
	private WebElement emailGoog;
	
	@FindBy(id = "Passwd")
	private WebElement googPassword;
	
	@FindBy(id = "signIn")
	private WebElement googSigninBtn;
	
	@FindBy(id = ":0.f")
	private WebElement googComment;
	
	@FindBy(className = "wI")
	private WebElement googTitle;
	
	@FindBy(xpath = "//td[@class='bI']/div[contains(@class,'d-k-l')]")
	private WebElement shareGoogBtn;
	

	public void loginFacebook(String fbUsername, String fbPass) {
		emailFB.sendKeys(fbUsername);
		fbPassword.sendKeys(fbPass);
		fbLogin.click();
	}
	public void shareToTimeline(String message) {   //sign up with passed-in credentials
		shareMessage.sendKeys(message);
		shareLink.click();
	}
	public void openTimelineMenu() {
		timelineMenu.click();
	}
	public void selectFriend(String friend) {
		friendEntry.sendKeys(friend);
		pickFriend.click();
	}
	public String getShareTitleFB() {
		return shareTitleFB.getText();
	}
	public String getTweet() {
		return tweet.getText();
	}
	public String getPinTitle() {
		return pinDescrip.getText();
	}
	public void pinToPinterest() {
		pinItLogin.click();
	}
	public void addMoreTweet(String moreTweet) {
		tweet.click();
		tweet.sendKeys(moreTweet);
	}
	public void loginTweetTwitter(String twitUsername, String twitPass) {
		twitterUser.sendKeys(twitUsername);
		twitterPass.sendKeys(twitPass);
		twitterSignin.click();
	}
	public void havePinAcct() {
		gotPinAcct.click();
	}
	public void loginPinterest(String username, String password) {
		pinEmail.sendKeys(username);
		pinPass.sendKeys(password);
		pinItLogin.click();
	}
	public void signinGoogplus(String username, String password) {
		emailGoog.sendKeys(username);
		googPassword.sendKeys(password);
		googSigninBtn.click();
	}
	public String getGoogTitle() {
		return googTitle.getText();
	}
	public void shareToGoog(String comment) {
		googComment.sendKeys(comment);
		shareGoogBtn.click();
	}
	
	
	
	

	
}