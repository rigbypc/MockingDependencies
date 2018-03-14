package point.of.sale;

import java.util.HashMap;

public class HashStorage implements Storage {

	HashMap<String, String> hashMap = new HashMap<>();
	
	
	public void loadStorage(String filename) {
		hashMap = SerializeHashMap.loadMap(filename);
	}
	
	public void put(String barcode, String item) {
		hashMap.put(barcode, item);
	}
		
	@Override
	public String barcode(String barcode) {
		return hashMap.get(barcode);
	}
	
	public void persistStorage(String filename) {
		SerializeHashMap.persistMap(hashMap, filename);
	}

}
