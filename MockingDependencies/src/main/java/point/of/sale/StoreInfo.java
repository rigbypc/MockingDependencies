package point.of.sale;

public class StoreInfo {

	private static StoreInfo instance = null;
	private String storeName = "No Name";
	
	//private constructor so no outside class can make an instance
	private StoreInfo() {
		
	}
	
	public static StoreInfo getInstance() {
		if (instance == null) {
			instance = new StoreInfo();
		}
		return instance;
	}
	
	public String getName() {
		return storeName;
	}
	
	public void setName(String storeName) {
		this.storeName = storeName;
	}
	
	//For testing purposes only! 
	// Decouples tests that are dependent on a singleton
	public static void reset() {
		instance = new StoreInfo();
	}
	
}
