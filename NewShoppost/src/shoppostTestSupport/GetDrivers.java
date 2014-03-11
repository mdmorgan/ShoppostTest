package shoppostTestSupport;



import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import shoppostBeans.LPData;
import shoppostBeans.TestData;
import shoppostBeans.SauceLabData;

public class GetDrivers {

	//note: counter is a global variable
	private String _browser;
	private WebDriver driver;
	private static TestData _td;
	private static SauceLabData _sld;
	//private static AcctSetData _asd;
	private static LPData _lpd;
	private String userName, _username, _password, _freshUser;
	private String accessCode, _passkey, _email, _currUrl, _testCase;
	private DesiredCapabilities capabilities;
	private int m;
	private WebDriverWait wait, wait2;
	private java.awt.Dimension screenSize;
	private Dimension dim;
	private int counter, r;
	//private SignUpIn signup;
	
	public GetDrivers( String browser ) {

		_browser = browser;
		
	}
	
	public WebDriver set() throws Exception {
		
		switch (_browser){
		case "*firefox":
			//String firebugPath = "C:\\Users\\DIY\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\am1fl87a.default\\extensions\\firebug@software.joehewitt.com.xpi";
			FirefoxProfile profile = new FirefoxProfile();
			//profile.addExtension(new File(firebugPath));
			//profile.setPreference("extensions.firebug.currentVersion", "1.11.2");
			driver = new FirefoxDriver(profile);
			driver.manage().window().setPosition(new Point(0,0));
			screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
			//driver.manage().window().setSize(dim);
			driver.manage().window().maximize();
			break;
			
		case "*safari":
			driver = new SafariDriver();
			break;
			
		case "*internetexplorer":
			System.setProperty("webdriver.ie.driver", "C:\\Users\\DIY\\Dropbox\\MarkFolder\\TestingEclipseJava\\TestPhysical\\EclipseWork\\IEDriverServer_x64_2.33.0\\iedriverserver.exe");
			driver = new InternetExplorerDriver();
			//driver.manage().window().maximize();
			break;
			
		case "*chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\DIY\\Dropbox\\MarkFolder\\TestingEclipseJava\\TestPhysical\\EclipseWork\\chromedriver_win32_2.8\\chromedriver.exe");
			driver = new ChromeDriver();
			//driver.manage().window().maximize();
			break;
			
		case "*sauceLabsRemote1":
			SauceLabsRemote sauceLabsRemote1 = new SauceLabsRemote(1);
			sauceLabsRemote1.setup();
			break;
		case "*sauceLabsRemote2":
			SauceLabsRemote sauceLabsRemote2 = new SauceLabsRemote(2);
			sauceLabsRemote2.setup();
			break;
		case "*sauceLabsRemote3":
			SauceLabsRemote sauceLabsRemote3 = new SauceLabsRemote(3);
			sauceLabsRemote3.setup();
			break;
		case "*sauceLabsRemote4":
			SauceLabsRemote sauceLabsRemote4 = new SauceLabsRemote(4);
			sauceLabsRemote4.setup();
			break;
		}
		
		return driver;

	}
	public String getPlatform() throws Exception {
		String _platform="";	
		switch(_browser) {
		case "*firefox":
			_platform = "Win7FF26";
			break;
			
		case "*safari":
			_platform = "Win7Safari5";
			break;
			
		case "*internetexplorer":
			_platform = "Win7IE9";
			break;
			
		case "*chrome":
			_platform = "Win7Chrome33";
			break;
			
		}
		
		
		return _platform;
	}

}

