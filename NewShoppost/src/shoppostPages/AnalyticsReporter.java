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

	@FindBy (id = "navLogo")
	private WebElement logoBtn;
	
	@FindBy (id = "navDashboard")
	private WebElement dashboardIcon;
	
	@FindBy (id = "navProducts")
	private WebElement productsIcon;
	
	@FindBy (id = "addButton")
	private WebElement addButton;
	
	@FindBy (id = "productUrl")
	private WebElement productURLField;
	
	@FindBy (id = "createButton")
	private WebElement shoppostBtn;
	
	@FindBy (id = "closeButton")
	private WebElement closeAddProduct;

	
	
	
	public void signOut() {
		signOut.click();
	}
	public void openAcctMenu() {
		acctBtn.click();
	}
	public void viaLogo() {
		logoBtn.click();
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
	public void addProduct() {
		addButton.click();
	}
	public void scrape(String productURL) {
		productURLField.sendKeys(productURL);
		shoppostBtn.click();
	}
	public void closeAdd() {
		closeAddProduct.click();
	}


}
