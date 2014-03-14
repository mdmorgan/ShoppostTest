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

public class ShareModal {
	private int DRIVER_WAIT = 15;
	
	@FindBy (id = "closeProduct")
	private WebElement closeShareBtn;

	@FindBy (id = "tabNavShare")
	private WebElement modalShareTab;
	
	@FindBy (id = "tabNavStats")
	private WebElement modalStatsTab;
	
	@FindBy (id = "productUrl")
	private WebElement productUrl;
	
	@FindBy (id = "deleteProduct")
	private WebElement modalProductDelete;
	
	@FindBy (id = "confirmDelete")
	private WebElement confirmD;
	
	@FindBy (className = "close")
	private WebElement cancelDelete;
	
	@FindBy (id = "tabNavSocial")
	private WebElement modalSocialTab;
	
	@FindBy (id = "tabNavLink")
	private WebElement modalLinkTab;
	
	@FindBy (id = "tabNavEmbed")
	private WebElement modalEmbedTab;
	
	@FindBy (id = "shareFacebook")
	private WebElement modalFacebook;
	
	@FindBy (id = "shareTwitter")
	private WebElement modalTwitter;
	
	@FindBy (id = "sharePinterest")
	private WebElement modalPinterest;
	
	@FindBy (id = "shareGooglePlus")
	private WebElement modalGooglePlus;
	
	@FindBy (id = "previewButton")
	private WebElement previewBtn;

	@FindBy (id = "productName")
	private WebElement productName;
	
	@FindBy (id = "linkInput")
	private WebElement lpUrl;
	
	@FindBy (id = "embedCode")
	private WebElement embedField;
	
	@FindBy (className = "chart")
	private WebElement donutChart;

	public ShareModal (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}

	
	
	public void closeShareModal() {
		closeShareBtn.click();
	}
	public void goStats() {
		modalStatsTab.click();
	}
	public String getProductUrl() {
		return productUrl.getText();
	}
	public void deleteProdViaModal() {
		modalProductDelete.click();
		confirmD.click();
		System.out.println("deleted successful.");
	}
	public void deleteProdViaModalCancel() {
		modalProductDelete.click();
		cancelDelete.click();
		System.out.println("deleted successfully cancelled.");
	}
	public void shareFB() {
		modalFacebook.click();
	}
	public void shareTwitter() {
		modalTwitter.click();
	}
	public void sharePinterest() {
		modalPinterest.click();
	}
	public void shareGooglePlus() {
		modalGooglePlus.click();
	}
	public String getProductName() {
		return productName.getText();
	}
	public void goLink() {
		modalLinkTab.click();
	}
	public void goShare() {
		modalSocialTab.click();
	}
	public void goEmbed() {
		modalEmbedTab.click();
	}
	public String getLPUrl() {
		return lpUrl.getText();
	}
	public String getEmbedCode() {
		return embedField.getText();
	}
	public boolean isPreviewVisible() {
		return previewBtn.isDisplayed();
	}
	public boolean isEmbedVisible() {
		return embedField.isDisplayed();
	}
	public boolean isFBVisible() {
		return modalFacebook.isDisplayed();
	}
	public boolean isDonutVisible() {
		return donutChart.isDisplayed();
	}
	public void openLandingPage() {
		previewBtn.click();
	}

}
