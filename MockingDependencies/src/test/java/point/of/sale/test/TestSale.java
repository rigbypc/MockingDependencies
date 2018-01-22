package point.of.sale.test;

import static org.junit.Assert.*;

import org.junit.Test;

import point.of.sale.FakeDisplay;
import point.of.sale.FakeStorage;
import point.of.sale.HashStorage;
import point.of.sale.Sale;

public class TestSale {

	@Test
	public void testFake() {
		FakeDisplay fakeDisplay = new FakeDisplay();
		FakeStorage fakeStorage = new FakeStorage();
		Sale sale = new Sale(fakeDisplay, fakeStorage);
		sale.scan("123");
		assertEquals("Milk, 3.99", fakeDisplay.getLastLine());
		
	}
	
	@Test
	public void testHashStorage() {
		FakeDisplay fakeDisplay = new FakeDisplay();
		
		HashStorage hashStorage = new HashStorage();
		hashStorage.put("1", "Milk, 3.99");
		hashStorage.put("2", "Smokes, 10.99");
		
		Sale sale = new Sale(fakeDisplay, hashStorage);
		sale.scan("1");
		assertEquals("Milk, 3.99", fakeDisplay.getLastLine());
		
		sale.scan("2");
		assertEquals("Smokes, 10.99", fakeDisplay.getLastLine());
		
		
		
		
	}

}
