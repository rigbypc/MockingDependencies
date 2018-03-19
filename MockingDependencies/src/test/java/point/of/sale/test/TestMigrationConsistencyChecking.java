package point.of.sale.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import point.of.sale.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class TestMigrationConsistencyChecking {

	@Mock
	Interac mockInterac;
	@Mock
	Display mockDisplay;
	
	//We want the new storage to behave in exactly the same way
	//Create a new ArrayStorage that implements HashStorage	
	ArrayStorage arrayStorage;
	
	Sale sale;
	
	@Before
	public void setupSale() {
		arrayStorage = new ArrayStorage();
		arrayStorage.put("123", "Milk 3.99");
		arrayStorage.put("124", "Beer 10.00");
		arrayStorage.put("125", "Eggs 2.99");
		
		sale = new Sale(mockDisplay, arrayStorage, mockInterac);
	}
	
	@Test
	public void test() {
				
		//forklift, copy old to new
		arrayStorage.forklift();
		
		//check consistency of new with old
		
		//ensure that inconsistencies are fixed, ie new.put(old)
		
		//shadow writes: any changes are written directly to old
		//consistency should be checked after each write
		
		//Shadow Reads for Validation (read will access both old and new)
		// old will provide response
		// consistency check that old == new
		
		//read and write from new datastore
		
		//toggle over to new datastore only
		
		//simple test of storage
		sale.scan("123");	
		verify(mockDisplay).showLine("123");
		verify(mockDisplay).showLine("Milk 3.99");
				
	}

}
