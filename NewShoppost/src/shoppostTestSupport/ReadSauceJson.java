package shoppostTestSupport;


import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;



import shoppostBeans.SauceLabData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReadSauceJson {
	private static String _username;
	private static String _browser;
	private static String _browserVersion;
	private static String _platform;
	
	private static SauceLabData sld;
	
	public ReadSauceJson () {}
	
	public static void read() throws Exception {
		
		BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\DIY\\Dropbox\\MarkFolder\\TestingEclipseJava\\dataSL.json"));
		
		Gson gson2 = new GsonBuilder().create();
		sld = gson2.fromJson(br2, SauceLabData.class);
		
		_username = sld.getSauceUsername();
		_browser = sld.getSauceBrowser().get(0).getSL_browser();
		_browserVersion = sld.getSauceBrowser().get(0).getSL_browserVersion();
		_platform = sld.getSauceBrowser().get(0).getSL_platform();
		//System.out.println(_username);
		//System.out.println(_browser);
		//System.out.println(_browserVersion);
		//System.out.println(_platform);
		
		br2.close();
		
		
	}
	
	public static SauceLabData get_sld() { return sld; }
	

}


