package shoppostBeans;

import java.util.List;

public class TestData {
	
	private String baseUrl;
	private String shopifyShareUrl;
	private String shopifyDashUrl;
	private String lpUrlPre;
	private String username;
	private String password;
	private String usernameFB;
	private String passwordFB;
	private String usernameTwitter;
	private String passwordGoog;
	
	private AcctSetTests acctSetTests;
	private SignupinTests signupinTests;
	private SignupinFBTests signupinFBTests;
	private UIData uiData;
	private CatalogTests catalogTests;
	private ShareData shareData;
	private AnalyticsTests analyticsTests;
	private NewUserShareTests newUserShareTests;
	private LandingPageTests landingPageTests;
	private OrdersTests ordersTests;
	private WidgetTests widgetTests;
	private List<LoginData> loginData;
	private Products products;
	private Price price;
	
	public String getBaseUrl() { return baseUrl; }
	public String getShopifyShareUrl() { return shopifyShareUrl; }
	public String getShopifyDashUrl() { return shopifyDashUrl; }
	public String getLpUrlPre() { return lpUrlPre; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public String getUsernameFB() { return usernameFB; }
	public String getPasswordFB() { return passwordFB; }
	public String getUsernameTwitter() { return usernameTwitter; }
	public String getPasswordGoog() { return passwordGoog; }
	
	public AcctSetTests getAcctSetTests() { return acctSetTests; }
	public SignupinTests getSignupinTests() { return signupinTests; }
	public SignupinFBTests getSignupinFBTests() { return signupinFBTests; }
	public UIData getUIData() { return uiData; }
	public CatalogTests getCatalogTests() { return catalogTests; }
	public ShareData getShareData() { return shareData; }
	public AnalyticsTests getAnalyticsTests() { return analyticsTests; }
	public NewUserShareTests getNewUserShareTests() { return newUserShareTests; }
	public LandingPageTests getLandingPageTests() { return landingPageTests; }
	public OrdersTests getOrdersTests() { return ordersTests; }
	public WidgetTests getWidgetTests() { return widgetTests; }
	public List<LoginData> getLoginData() { return loginData; }
	public Products getProducts() { return products; }
	public Price getPrice() { return price; }
	
}
