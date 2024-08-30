package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import queue_delegate.FifoQueue;
import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> myIntQueue1;
	private FifoQueue<Integer> myIntQueue2;
	
	@Before
	public void setUp() throws Exception {
		myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		myIntQueue1 = null;
		myIntQueue2 = null;
	}
	
	/** test två tomma köer */
	@Test
	public final void testTwoEmpty() {
		myIntQueue1.append(myIntQueue2);
		
		assertTrue("Wrong size on queue 1 after append", myIntQueue1.isEmpty());
		assertTrue("Wrong size on queue 2 after append", myIntQueue2.isEmpty());
	}
	
	/** Test tom kö som konkateneras till icke-tom kö */
	@Test
	public final void testEmptyToNonEmpty() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		myIntQueue1.append(myIntQueue2);
		
		assertTrue("Wrong size on queue 1 after append", myIntQueue1.size() == 3);
		assertTrue("Wrong size on queue 2 after append", myIntQueue2.size() == 0);
		
		for (int i = 1; i <= 3; i++) {
			int k = myIntQueue1.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Queue not empty", myIntQueue1.isEmpty());
	}

	/** Test icke-tom kö som konkateneras till tom kö */
	@Test
	public final void testNonEmptyToEmpty() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		
		myIntQueue2.append(myIntQueue1);
		
		assertTrue("Wrong size on queue 1 after append", myIntQueue1.size() == 0);
		assertTrue("Wrong size on queue 2 after append", myIntQueue2.size() == 3);
		
		for (int i = 1; i <= 3; i++) {
			int k = myIntQueue2.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Queue not empty", myIntQueue1.isEmpty());
	}
	
	/** Test icke-tom kö som konkateneras till icke-tom kö */
	@Test
	public final void testNonEmptytoNonEmpty() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		myIntQueue2.offer(4);
		myIntQueue2.offer(5);
		myIntQueue1.append(myIntQueue2);
		
		assertTrue("Wrong size on queue 1 after append", myIntQueue1.size() == 5);
		assertTrue("Wrong size on queue 2 after append", myIntQueue2.size() == 0);
		
		for (int i = 1; i <= 5; i++) {
			int k = myIntQueue1.poll();
			assertEquals("poll returns incorrect element", i, k);
		}
		assertTrue("Queue not empty", myIntQueue1.isEmpty());
	}
	
	/** Test kö konkateneras till sig själv */
	@Test
	public final void testSameQueue() {
		myIntQueue1.offer(1);
		try {
			myIntQueue1.append(myIntQueue1);
		} catch(IllegalArgumentException exception) {}
	}
}