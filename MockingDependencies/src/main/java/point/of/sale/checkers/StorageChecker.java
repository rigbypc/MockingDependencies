package point.of.sale.checkers;

import java.util.HashMap;

import com.google.inject.Inject;

import point.of.sale.SerializeHashMap;
import point.of.sale.Storage;

public class StorageChecker {

	//inject the actual Storage from the application
	@Inject
	Storage storage;
	
	HashMap<String, String> consistentMap;
	
	@Inject
	public StorageChecker(Storage storage) {
		this.storage = storage;
		String filename = System.getProperty("user.dir") + "/" + "SaleStorage.ser";
		//loads in the expected values
		consistentMap = SerializeHashMap.loadMap(filename);
	}
	
	//goal is to check that each item remains consistent
	//for example "123" maps to "Milk 3.99"
	public void check() {
		for (String barcode : consistentMap.keySet()) {
			String actual = storage.barcode(barcode);
			if (!actual.equals(consistentMap.get(barcode))) {
				//inconsistency
				System.out.println("Consistency Violation!\n" + 
						"barcode = " + barcode +
						"\n\t expected = " + consistentMap.get(barcode)
						+ "\n\t actual = " + actual);
			}
		}
		
	}
	
	

}
