package shoppostPages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.WebElement;

public class ProductCatalog {
	private int DRIVER_WAIT = 15;
	
	private int _index;
	Random rand = new Random();
	
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
	
	@FindBy (id = "errorMessage")
	private WebElement errorMsg;
	
	
	@FindBy (xpath = "//a[contains(@id,'stats')]")  //accurate product count
	private List<WebElement> productStatsButtons;
	
	@FindBy (xpath = "//a[contains(@id,'share')]")  //accurate product count
	private List<WebElement> productShareButtons;

	@FindBy (xpath = "//a[contains(@id,'delete')]")  //note this will return an inflated product count, use stats or share size() for number
	private List<WebElement> productDeleteButtons;
	
	@FindBy (id = "confirmDelete")
	private WebElement confirmDelete;

	@FindBy (className = "thumbnail")
	private List<WebElement> thumbnails;
	

	public ProductCatalog (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}
	
	
	
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
	public String getEmailAddress() {
		return acctBtn.getText();
	}
	public int getProductCount() {
		//System.out.println("number of products: "+productStatsButtons.size());
		return productStatsButtons.size();
	}
	public WebElement hoverRandomProduct() {   //this hovers a random product to activate buttons
		_index = rand.nextInt(productStatsButtons.size()-1);
		return thumbnails.get(_index);
	}
	public WebElement hoverProductAgain() {
		return thumbnails.get(_index);
	}
	public void getShare() {  //this opens share modal of the random product
		productShareButtons.get(_index).click();
	}
	public void getRandomStats() {
		productStatsButtons.get(_index).click();
	}
	public void getRandomDelete() {
		productDeleteButtons.get(_index).click();
		confirmDelete.click();
		System.out.println("deleted successful.");
	}
	public void addProduct() {
		addButton.click();
	}
	public void scrape(String productURL) {
		productURLField.sendKeys(productURL);
		shoppostBtn.click();
	}
	public void scrapeCancel(String productURL) {
		productURLField.sendKeys(productURL);
		closeAddProduct.click();
	}
	public void closeAdd() {
		closeAddProduct.click();
	}
	public String checkError() {
		return errorMsg.getText();
	}
	


}
