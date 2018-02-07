package point.of.sale;

import java.util.ArrayList;

public class Sale {

	Storage storage;
	Display display;
	Interac interac;
	ArrayList<String> items = new ArrayList<>();
	
	public Sale(Display display, Storage storage) {
		this(display, storage, new Interac(12));
	}
	
	public Sale(Display display, Storage storage, Interac interac) {
		this.display = display;
		this.storage = storage;
		this.interac = interac;
	}
	
	public void completePurchase() {
		interac.pay(items);
	}
	
	public void scan(String barcode) {
		//display the barcode
		display.showLine(barcode);
		
		//lookup barcode in postgres and get item
		String item = storage.barcode(barcode);
		display.showLine(item);
		items.add(item);
		
	}
	
	//allows us to replace the private interac object dependency with a mock
	//should never be used in production TESTING ONLY
	public void supersedeInterac(Interac newInterac) {
		interac = newInterac;
	}

}
