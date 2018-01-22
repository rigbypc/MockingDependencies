package point.of.sale;

public class FakeStorage implements Storage {

	@Override
	public String barcode(String barcode) {
		return "Milk, 3.99";
	}

}
