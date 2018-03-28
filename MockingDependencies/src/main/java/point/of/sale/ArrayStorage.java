package point.of.sale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayStorage extends HashStorage {

	private static Logger migrationLog = LogManager.getLogger("migration");
	
	int readInconsistencies = 0;
	public int getReadInconsistencies() {
		return readInconsistencies;
	}

	int size = 999;
	String[] array;
	public ArrayStorage() {
		if (SaleToggles.isEnabledArray) {
			array = new String[size];
		}
		migrationLog.info("isEnabledArray = " + SaleToggles.isEnabledArray);
		migrationLog.info("isEnabledHash = " + SaleToggles.isEnabledHash);
	}
	
	public void forklift() {
		if(!(SaleToggles.isEnabledArray & SaleToggles.isEnabledHash)) {
			return;
		}
		
		for (String barcode : hashMap.keySet()) {
			array[Integer.parseInt(barcode)] = hashMap.get(barcode);
		}
	}
	
	@Override
	public void loadStorage(String filename) {
		if (SaleToggles.isEnabledHash) {
			super.loadStorage(filename);
		}
	}

	//need this for testing to insert inconsistencies
	public void testOnlyPutHashOnly(String barcode, String item) {
		if (!SaleToggles.isUnderTest) {
			return;
		}
		
		if (SaleToggles.isEnabledHash) {
			super.put(barcode, item);
		}
	}
	
	@Override
	public void put(String barcode, String item) {
		if (SaleToggles.isEnabledArray) {
			//shadow write
			array[Integer.parseInt(barcode)] = item;
		}
		
		if (SaleToggles.isEnabledHash) {
			//actual write to old data store
			super.put(barcode, item);
		}
		
		return;
		
	}

	@Override
	public String barcode(String barcode) {
		
		if (SaleToggles.isEnabledArray & SaleToggles.isEnabledHash) {
			String expected = hashMap.get(barcode);
			//shadow read
			String actual = array[Integer.parseInt(barcode)];
			
			if(! expected.equals(actual)) {
				migrationLog.error("Read Inconsistency");
				//fix the inconsistency
				array[Integer.parseInt(barcode)] = expected;
				violation(barcode, expected, actual);
			}
		}
				
		if (SaleToggles.isEnabledHash) {
			//return the expected value from the hash
			return super.barcode(barcode);
		}
		
		if (SaleToggles.isEnabledArray) {
			return array[Integer.parseInt(barcode)];
		}
		
		return null;
	}

	@Override
	public void persistStorage(String filename) {
		if (SaleToggles.isEnabledHash) {
			super.persistStorage(filename);
		}
	}

	
	//new should be the same as the old
	public int checkConsistency() {
		if(! (SaleToggles.isEnabledHash & SaleToggles.isEnabledArray)) {
			return 0;
		}
		
		int inconsistencies = 0;
		for (String barcode : hashMap.keySet()) {
			String expected = hashMap.get(barcode);
			String actual = array[Integer.parseInt(barcode)];
			if (!expected.equals(actual)) {
				
				migrationLog.error("Inconsistency in full check");
				//fix the inconsistency
				array[Integer.parseInt(barcode)] = expected;
				
				inconsistencies++;
				violation(barcode, expected, actual);		
			}
		}
		return inconsistencies;
	}
	
	private void violation(String barcode, String expected, String actual) {
		if(! (SaleToggles.isEnabledHash & SaleToggles.isEnabledArray)) {
			return;
		}

		migrationLog.info("Inconsistency:" + 
				"barcode = " + barcode +
				"\n\t expected = " + expected
				+ "\n\t actual = " + actual);
	}

}
