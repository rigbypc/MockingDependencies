package point.of.sale.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import point.of.sale.*;

@RunWith(MockitoJUnitRunner.class)
public class TestSale {

	@Mock
	Display mockDisplay;
	@Mock
	Storage mockStorage;
	@Mock
	Interac mockInterac;
	
	Sale sale;
	
	//before each test
	@Before
	public void setupSale() {
		sale = new Sale(mockDisplay, mockStorage, mockInterac);
	}
	
	@Test
	public void test() {
		//setup the storage mock to return milk
		when(mockStorage.barcode("123")).thenReturn("Milk 3.99");
		//call the class under test
		sale.scan("123");
		
		//verify that storage mock is called
		verify(mockStorage).barcode("123");
		//verify that display is called
		verify(mockDisplay).showLine("Milk 3.99");
	}
	
}
