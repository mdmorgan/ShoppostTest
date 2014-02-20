package shoppostBeans;

import java.util.List;

import shoppostBeans.Media;
import shoppostBeans.Options;

public class Products {

	
		//private String baseUrl;
		private String name;
		private String description;
		private String variations;
		private List<Media> media;
		private List<String> tags;
		private List<Options> options;
		
	
		//public String getBaseUrl() { return baseUrl; }
		public String getName() { return name; }
		public String getDescription() { return description; }
		public String getVariations() { return variations; }
		public List<Media> getMedia() { return media; }
		public List<String> getTags() { return tags; }
		public List<Options> getOptions() { return options; }
		
	

}