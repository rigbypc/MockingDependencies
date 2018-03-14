package point.of.sale;

import com.google.inject.Guice;
import com.google.inject.Injector;

import point.of.sale.checkers.StorageChecker;

public class MainSale {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new SaleModule());
		Sale sale = injector.getInstance(Sale.class);
		StorageChecker storageChecker = injector.getInstance(StorageChecker.class);
		storageChecker.check();
		
		sale.scan("123");

	}

}
