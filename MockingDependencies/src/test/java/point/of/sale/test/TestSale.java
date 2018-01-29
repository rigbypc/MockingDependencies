package point.of.sale.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import point.of.sale.*;
import static org.mockito.Mockito.*;

public class TestSale {

	@Test
	public void testMockStorage() {
		//create the mock Display
		Display mockDisplay = mock(Display.class);
				
		//create the mock of Storage
		Storage mockStorage = mock(Storage.class);
		
		//stub barcode("123") to return Milk, 3.99
		when(mockStorage.barcode("123")).thenReturn("Milk, 3.99");
		when(mockStorage.barcode("124")).thenReturn("Eggs, 4.99");
		
		//Order
		InOrder inOrder = inOrder(mockDisplay, mockStorage); 
		
		//ArgCaptor: Can capture a string
		ArgumentCaptor<String> argCaptor = ArgumentCaptor.forClass(String.class); 
				
		//call the class under test
		Sale sale = new Sale(mockDisplay, mockStorage);
		//internally this will use the mock object
		sale.scan("123");
		//checks that the call was made, but also tells what the parameter value was
		verify(mockStorage).barcode(argCaptor.capture());
		System.out.println(argCaptor.getValue());
		
		//verify display was called with "123"
		verify(mockDisplay).showLine(argCaptor.getValue());
		
		//verify display.showLine called with Milk, 3.99
		verify(mockDisplay).showLine("Milk, 3.99");
		
		sale.scan("124");
		
		//verify that Storage.barcode was called by the sale object
		verify(mockStorage).barcode("123");
		
		inOrder.verify(mockStorage).barcode("124");
		inOrder.verify(mockDisplay).showLine("Eggs, 4.99");
		
		
	}
	
	public void testFake() {
		FakeDisplay fakeDisplay = new FakeDisplay();
		FakeStorage fakeStorage = new FakeStorage();
		Sale sale = new Sale(fakeDisplay, fakeStorage);
		sale.scan("123");
		assertEquals("Milk, 3.99", fakeDisplay.getLastLine());
		
	}
	
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
