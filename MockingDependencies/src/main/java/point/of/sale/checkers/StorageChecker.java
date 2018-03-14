package point.of.sale.checkers;

import com.google.inject.Inject;

import point.of.sale.Storage;

public class StorageChecker {

	//inject the actual Storage from the application
	@Inject
	Storage storage;
	
	@Inject
	public StorageChecker(Storage storage) {
		this.storage = storage;
	}
	
	//goal is to check that each item remains consistent
	//for example "123" maps to "Milk 3.99"
	public void check() {
		System.out.println("check");
		System.out.println(storage.barcode("123"));
	}
	
	

}
