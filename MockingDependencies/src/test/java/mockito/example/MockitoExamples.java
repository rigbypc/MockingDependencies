package mockito.example;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.junit.Test;

public class MockitoExamples {

	@Test
	public void testMockLinkedList() {
		System.out.println("Mock List");
		LinkedList<String> mockList = mock(LinkedList.class);
		
		
		mockList.add("Hello world");
		verify(mockList).add("Hello world");
		
		//setup for use
		when(mockList.get(0)).thenReturn("Hello world");
		
		//actual
		mockList.get(0);
		
		//verifing the use
		verify(mockList).get(0);
		
		
		
		System.out.println(mockList.get(0));
	}
	
	@Test
	public void testSpyLinkedList() {
		//real list
		LinkedList<String> list = new LinkedList<String>();
		//mock list
		LinkedList<String> spyList = spy(list);
		
		//stub
		when(spyList.size()).thenReturn(100);
		
		System.out.println("Real List");
		
		//real
		spyList.add("Hello world");
		System.out.println(spyList.get(0));
		
		//stub
		System.out.println(spyList.size());
		//assert, we call the mock, which always returns a size of 100
		assertEquals(100, spyList.size());
	}

}
