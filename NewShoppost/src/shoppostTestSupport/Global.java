package shoppostTestSupport;

public class Global {
	
	public static int sauceCounter = 0;

	public Global() {}
	
	public static int increment() {
		sauceCounter = sauceCounter+1;
		return sauceCounter;
	}

}
