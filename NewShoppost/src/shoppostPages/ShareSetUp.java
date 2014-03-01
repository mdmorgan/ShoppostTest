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
	private WebElement shareTitle;
	
	@FindBy (id = "email")
	private WebElement fbEmail;
	
	@FindBy (id = "pass")
	private WebElement fbPassword;
	
	@FindBy(id = "u_0_1")
	private WebElement fbLogin;
	
	@FindBy(id = "status")
	private WebElement twitterStatus;
	
	@FindBy(id = "username_or_email")
	private WebElement twitterUser;
	
	@FindBy(id = "password")
	private WebElement twitterPass;
	
	@FindBy(xpath = "//fieldset[@class='submit']/input[@id='char-count']/following-sibling::input")
	private WebElement twitterSignin;
	

	public void loginFacebook(String fbUsername, String fbPass) {
		fbEmail.sendKeys(fbUsername);
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
	public String getShareTitle() {
		return shareTitle.getText();
	}
	public void addMoreTweet(String moreTweet) {
		twitterStatus.click();
		twitterStatus.sendKeys(moreTweet);
	}
	public void loginTweetTwitter(String twitUsername, String twitPass) {
		twitterUser.sendKeys(twitUsername);
		twitterPass.sendKeys(twitPass);
		twitterSignin.click();
	}
	
	
	

	
}