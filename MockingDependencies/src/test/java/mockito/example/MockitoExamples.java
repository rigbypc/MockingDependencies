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
		System.out.println(mockList.get(0));
	}
	
	@Test
	public void testLinkedList() {
		LinkedList<String> list = new LinkedList<String>();
		
		System.out.println("Real List");
		list.add("Hello world");
		System.out.println(list.get(0));
	}

}
