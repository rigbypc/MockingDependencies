package point.of.sale;

import com.google.inject.AbstractModule;

public class SaleModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Display.class).to(ConsoleDisplay.class);
		bind(Interac.class).toInstance(new Interac(12));
		bind(Storage.class).to(HashStorage.class);

	}

}
