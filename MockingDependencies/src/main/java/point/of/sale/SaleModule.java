package point.of.sale;

import com.google.inject.AbstractModule;

public class SaleModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Display.class).to(ConsoleDisplay.class);
		
		bind(Interac.class).toInstance(new Interac(12));
		
		HashStorage hashStorage = new HashStorage();
		hashStorage.put("123", "Milk 3.99");
		hashStorage.put("124", "Beer 10.00");
		hashStorage.put("125", "Eggs 2.99");
		String filename = System.getProperty("user.dir") + "/" + "SaleStorage.ser";
		hashStorage.persistStorage(filename);
		hashStorage.loadStorage(filename);
		bind(Storage.class).toInstance(hashStorage);

	}

}
