package point.of.sale.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import point.of.sale.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class TestBarcodeSale {

	@Test
	public void testSupersedeInterac() {
		//create the mock Interac
		Interac mockInterac = mock(Interac.class);
		
		//create the mock Display
		Display mockDisplay = mock(Display.class);

		//create the mock of Storage
		Storage mockStorage = mock(Storage.class);
		//stub it
		when(mockStorage.barcode("123")).thenReturn("Milk, 3.99");

		//create the class under test 
		// a real interac connection
		Sale sale = new Sale(mockDisplay, mockStorage);
		
		sale.supersedeInterac(mockInterac);
		
		//internally this will use the mock object
		sale.scan("123");
		sale.completePurchase();
		
		//verify that Storage.barcode was called by the sale object
		verify(mockStorage).barcode("123");
		
		//verify that we displayed the barcode
		verify(mockDisplay).showLine("123");
		//verify display.showLine called with Milk, 3.99
		verify(mockDisplay).showLine("Milk, 3.99");
		
		//verify without checking the contents of the list
		verify(mockInterac).pay(any(ArrayList.class));
		
		//verify the call to Interact for "Milk, 3.99"
		ArrayList<String> items = new ArrayList<>();
		items.add("Milk, 3.99");		
		verify(mockInterac).pay(items);
		
	}
	
	@Test
	public void testSetStoreName() {
		StoreInfo storeInfo = StoreInfo.getInstance();
		storeInfo.setName("Concordia");
		assertEquals("Concordia", storeInfo.getName());
	}
	
	@Test
	public void testSimpleMockStorage() {
		//create the mock Interac
		Interac mockInterac = mock(Interac.class);
		
		//create the mock Display
		Display mockDisplay = mock(Display.class);

		//create the mock of Storage
		Storage mockStorage = mock(Storage.class);
		//stub it
		when(mockStorage.barcode("123")).thenReturn("Milk, 3.99");

		//create the class under test 
		//Add the mockInterac
		Sale sale = new Sale(mockDisplay, mockStorage, mockInterac);
		//internally this will use the mock object
		
		sale.scan("123");
		sale.completePurchase();
		
		//verify that the store name is being displayed
		verify(mockDisplay).showLine("No Name");
		
		//verify that Storage.barcode was called by the sale object
		verify(mockStorage).barcode("123");
		
		//verify that we displayed the barcode
		verify(mockDisplay).showLine("123");
		//verify display.showLine called with Milk, 3.99
		verify(mockDisplay).showLine("Milk, 3.99");
		
		//verify without checking the contents of the list
		verify(mockInterac).pay(any(ArrayList.class));
		
		//verify the call to Interact for "Milk, 3.99"
		ArrayList<String> items = new ArrayList<>();
		items.add("Milk, 3.99");		
		verify(mockInterac).pay(items);
		
	}
	
	@Test
	public void testInOrder() {
		//create the mock Display
		Display mockDisplay = mock(Display.class);

		//create the mock of Storage
		Storage mockStorage = mock(Storage.class);
		//stub it
		when(mockStorage.barcode("123")).thenReturn("Milk, 3.99");

		//Setup an InOnder
		InOrder inOrder = inOrder(mockDisplay, mockStorage);
		
		//call the class under test
		Sale sale = new Sale(mockDisplay, mockStorage);
		//internally this will use the mock object
		sale.scan("123");
		
		//verify that we displayed the barcode
		inOrder.verify(mockDisplay).showLine("123");
		
		//verify that Storage.barcode was called by the sale object
		inOrder.verify(mockStorage).barcode("123");
		
		//verify display.showLine called with Milk, 3.99
		inOrder.verify(mockDisplay).showLine("Milk, 3.99");
	}
	
	@Test
	public void testCaptorMockStorage() {
		//create the mock Display
		Display mockDisplay = mock(Display.class);

		//create the mock of Storage
		Storage mockStorage = mock(Storage.class);
		//stub it
		when(mockStorage.barcode("123")).thenReturn("Milk, 3.99");

		//capture a String argument, which will be passed to Storage.barcode(<here>)
		ArgumentCaptor<String> argCaptor = ArgumentCaptor.forClass(String.class);
				
		//call the class under test
		Sale sale = new Sale(mockDisplay, mockStorage);
		//internally this will use the mock object
		sale.scan("123");
		
		//verify, verifies interactions, but also can record the args passed in
		verify(mockStorage).barcode(argCaptor.capture());
		//used the captured arg to ensure that it gets displayed
		verify(mockDisplay).showLine(argCaptor.getValue());
		
		//verify display.showLine called with Milk, 3.99
		verify(mockDisplay).showLine("Milk, 3.99");
	}
	
	@Test
	public void testEverythingMockStorage() {
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
		//verify(mockDisplay).showLine("123");
		verify(mockDisplay).showLine(argCaptor.getValue());
		
		//verify display.showLine called with Milk, 3.99
		verify(mockDisplay).showLine("Milk, 3.99");
		
		//verify that Storage.barcode was called by the sale object
		verify(mockStorage).barcode("123");
				
		
		sale.scan("124");
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
