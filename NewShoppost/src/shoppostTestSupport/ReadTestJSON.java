package shoppostTestSupport;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;



import shoppostBeans.TestData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReadTestJSON {
	private static String _name;
	private static String _description;
	private static String _mediaType1;
	private static String _tag1;
	private static String _option1value;
	private static TestData td;
	
	public ReadTestJSON () {}
	
	
	public static void read() throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\DIY\\Dropbox\\MarkFolder\\TestingEclipseJava\\dataShoppost.json"));    //standard testing
		//BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\DIY\\Dropbox\\MarkFolder\\TestingEclipseJava\\dataDDT_dbTest.json"));  //testing of large DB's 
		
		Gson gson = new GsonBuilder().create();
		td = gson.fromJson(br, TestData.class);
		//Product product = pd.getProduct();
		
		_name = td.getProducts().getName();
		_description = td.getProducts().getDescription();
		_mediaType1 = td.getProducts().getMedia().get(0).getType();
		_tag1 = td.getProducts().getTags().get(0);
		_option1value = td.getProducts().getOptions().get(0).getValues().get(0);
		//System.out.println(_name);
		//System.out.println(_description);
		//System.out.println(_mediaType1);
		//System.out.println(_tag1);
		//System.out.println(_option1value);
		
		br.close();
		
		
		
	}
	
	public static TestData get_td() { return td; }
	

}
