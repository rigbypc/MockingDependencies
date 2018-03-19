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
	
	HashStorage hashStorage;
	
	Sale sale;
	
	@Before
	public void setupSale() {
		hashStorage = new HashStorage();
		hashStorage.put("123", "Milk 3.99");
		hashStorage.put("124", "Beer 10.00");
		hashStorage.put("125", "Eggs 2.99");
		
		sale = new Sale(mockDisplay, hashStorage, mockInterac);
	}
	
	@Test
	public void test() {
		//simple test of storage
		sale.scan("123");	
		verify(mockDisplay).showLine("123");
		verify(mockDisplay).showLine("Milk 3.99");
		
		//We want the new storage to behave in exactly the same way
		//Create a new ArrayStorage that implements HashStorage

		//forklift, copy old to new
		
		//check consistency of new with old
		
		//ensure that inconsistencies are fixed, ie new.put(old)
		
		//shadow writes: any changes are written directly to old
		//consistency should be checked after each write
		
		//Shadow Reads for Validation (read will access both old and new)
		// old will provide response
		// consistency check that old == new
		
		//read and write from new datastore
		
		//toggle over to new datastore only
	}

}
