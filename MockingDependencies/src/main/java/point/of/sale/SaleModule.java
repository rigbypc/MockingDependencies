package point.of.sale;

import com.google.inject.AbstractModule;

public class SaleModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Display.class).to(ConsoleDisplay.class);
		
		bind(Interac.class).toInstance(new Interac(12));
		
		HashStorage hashStorage = new HashStorage();
		hashStorage.put("123", "Milk 3.99");
		bind(Storage.class).toInstance(hashStorage);

	}

}
