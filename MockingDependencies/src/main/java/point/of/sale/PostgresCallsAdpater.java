package point.of.sale;

public class PostgresCallsAdpater implements Storage {

	PostgresCalls postgres;
	@Override
	public String barcode(String barcode) {
		return postgres.barcode(barcode)
	}

}
