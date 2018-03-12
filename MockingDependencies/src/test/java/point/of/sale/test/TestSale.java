package point.of.sale.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
		//fail("Not yet implemented");
	}

}
