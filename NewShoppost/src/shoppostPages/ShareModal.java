package shoppostPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;

public class ShareModal {
	
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
	
	@FindBy (id = "linkInput")
	private WebElement landingPageLink;
	
	@FindBy (id = "previewButton")
	private WebElement previewBtn;


	
	
	
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


}
