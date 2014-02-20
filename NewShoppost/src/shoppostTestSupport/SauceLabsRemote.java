package shoppostTestSupport;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import shoppostBeans.SauceLabData;

public class SauceLabsRemote {

	private DesiredCapabilities capabilities;
	private String userName, accessCode;
	private SauceLabData _sld;
	private Integer m, h, counter, slnumber;
	private RemoteWebDriver remoteDriver;
	
	public SauceLabsRemote(int n) {
		
		slnumber = n;
		
	}
	
	public void setup() throws Exception {
	
		ReadSauceJson.read();
		_sld = ReadSauceJson.get_sld();
		userName = _sld.getSauceUsername();
		accessCode = _sld.getSauceAccessCode();

		for (int h=0; h<_sld.getSauceBrowser().size(); h++){   //
		
			if (_sld.getSauceBrowser().get(h).getThisBrowser()==slnumber+counter){  
				m = h;
				if (("internetExplorer").equals(_sld.getSauceBrowser().get(h).getSL_browser())) {
					capabilities = DesiredCapabilities.internetExplorer(); 
				}
			else if (("iphone").equals(_sld.getSauceBrowser().get(h).getSL_browser())) {
					capabilities = DesiredCapabilities.iphone();
				}
			else {
					capabilities =  new DesiredCapabilities();
				}
			
			
			if (!("internetExplorer").equals(_sld.getSauceBrowser().get(h).getSL_browser())&&!("iphone").equals(_sld.getSauceBrowser().get(h).getSL_browser())) 
			//if (!("internetExplorer").equals(_sld.getSauceBrowser().get(h).getSL_browser()))
			 {
				capabilities.setCapability("browserName", _sld.getSauceBrowser().get(h).getSL_browser());
			 }
			capabilities.setCapability("version", _sld.getSauceBrowser().get(h).getSL_browserVersion());
			capabilities.setCapability("platform", _sld.getSauceBrowser().get(h).getSL_platform());
			capabilities.setCapability("name", _sld.getlpName()+_sld.getSauceBrowser().get(h).getSL_name());
			capabilities.setCapability("screen-resolution", _sld.getSauceBrowser().get(h).getSL_resolution());
			
			System.out.println(userName + " : " + accessCode +" , " + _sld.getSauceBrowser().size());
			remoteDriver = new RemoteWebDriver (new URL("http://" + userName + ":" + accessCode + "@ondemand.saucelabs.com:80/wd/hub" ), capabilities);
			//driver.manage().window().maximize();
			break;
		}
	  }
		
	}

	public RemoteWebDriver getRemoteDriver() { return remoteDriver; }
	
	

}
