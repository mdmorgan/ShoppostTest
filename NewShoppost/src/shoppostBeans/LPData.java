package shoppostBeans;

import java.util.List;

import shoppostBeans.LPInput;
import shoppostBeans.LoginData;

public class LPData {
	
	private String lpName;
	private int width;
	private List<LoginData> loginData;
	private List<LPInput> lPInput;
	
	public String getlpName() { return lpName; }
	public int getWidth() { return width; }
	public List<LoginData> getLoginData() { return loginData; }
	public List<LPInput> getInput() { return lPInput; }
	
	
}	
