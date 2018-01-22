package point.of.sale;

public class Sale {

	Storage storage;
	Display display;
	
	public Sale(Display display, Storage storage) {
		this.display = display;
		this.storage = storage;
	}
	
	public void scan(String barcode) {
		//display the barcode
		display.showLine(barcode);
		
		//lookup barcode in postgres and get item
		display.showLine(storage.barcode(barcode));
		
	}

}
