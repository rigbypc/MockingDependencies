package mockito.example;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MockitoTest {
  @Test
  public void test() {
    List<String> mockList = mock(List.class);
    mockList.add("First");
    when(mockList.get(0)).thenReturn("Mockito");
    when(mockList.get(1)).thenReturn("JCG");
    assertEquals("Mockito", mockList.get(0));
    assertEquals("JCG", mockList.get(1));
  }
}

