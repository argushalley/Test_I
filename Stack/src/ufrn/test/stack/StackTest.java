package ufrn.test.stack;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ufrn.src.stack.Stack;

import static org.hamcrest.CoreMatchers.*;

@RunWith(Parameterized.class)
public class StackTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{1, 1}, {2, 2}, {3, 3}, {4, 4}
		});
	}
	
	private Stack<Integer> stack;
	private int number;
	private int expected;
	
	public StackTest(int number, int expected) {
		this.number = number;
		this.expected = expected;
	}
	
	@Before
	public void setUp() {
		stack = new Stack<Integer>();
	}
	
	@After
	public void tearDown() {
		stack = null;
	}
	
	@Test
	public void testPush() {
		int actual = stack.push(number);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testPop() {
		stack.push(number);
		int actual = stack.pop();
		
		assertEquals(expected, actual);
	}

	@Test(expected = EmptyStackException.class)
	public void testPeekThrowException() {
		stack.peek();
	}

	@Test
	public void testPeek() {
		stack.push(number);
		int actual = stack.peek();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEmpty() {
		assertTrue(stack.empty());
		
		stack.push(number);
		
		assertFalse(stack.empty());
	}

	@Test
	public void testSearch() {
		stack.push(number);
		int actual = stack.search(number);
		
		assertEquals(stack.size(), actual);
		
		actual = stack.search(number * 2);
		
		assertThat(stack.size(), is(not(actual)));
	}

}
