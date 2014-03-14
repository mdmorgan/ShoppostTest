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

public class LandingPage {
	private int DRIVER_WAIT = 15;
	
	@FindBy (className = "media-gallery")
	private WebElement mediaGallery;

	

	public LandingPage (WebDriver driver) {
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, DRIVER_WAIT);
		PageFactory.initElements(finder, this);
	}

	public boolean isMediaGalleryVisible() {
		return mediaGallery.isDisplayed();
	}
	

}
