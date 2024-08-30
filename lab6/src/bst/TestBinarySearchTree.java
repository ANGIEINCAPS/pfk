package bst;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class TestBinarySearchTree {
	private BinarySearchTree<Integer> bstInteger;
	private BinarySearchTree<String> bstString;
	Random rand;
	
	@BeforeEach
	void setUp() {
		bstInteger = new BinarySearchTree<Integer>();
		bstString = new BinarySearchTree<String>((s1, s2) -> s1.length() - s2.length());
		rand = new Random();
	}

	@AfterEach
	void tearDown() {
		bstInteger = null;
		rand = null;
	}

	@Test
	void testHeight() {
		assertEquals(0, bstInteger.height(), "New tree, height not 0");
		bstInteger.add(rand.nextInt(100));
		assertEquals(1, bstInteger.height(), "One node, height not 1");
		bstInteger.clear();
	}

	@Test
	void testSize() {
		assertEquals(0, bstInteger.size(), "Wrong size for new tree.");
		bstInteger.add(rand.nextInt(100));
		assertEquals(1, bstInteger.size(), "Wrong size for one node.");
		bstInteger.clear();
	}
	
	@Test
	void testAdd() {
		bstInteger.add(5);
		bstInteger.add(4);
		bstInteger.add(6);
		bstInteger.add(2);
		bstInteger.add(1);
		assertEquals(5, bstInteger.size(), "Size not 5");
		assertEquals(4, bstInteger.height(), "Height not 5");
		
		bstString.add("ABCDE");
		bstString.add("ABCD");
		bstString.add("ABCDEF");
		bstString.add("AB");
		bstString.add("A");
		assertEquals(5, bstString.size(), "Wrong size.");
		assertEquals(4, bstString.height(), "Wrong height");
		
		bstInteger.add(5);
		assertEquals(5, bstInteger.size(), "Duplicates.");
		assertEquals(4, bstInteger.height(), "Duplicates.");
		
		bstString.add("XYZK");
		assertEquals(5, bstString.size(), "Duplicates.");
		assertEquals(4, bstString.height(), "Duplicates.");
		
		assertEquals(true, bstString.add("XYZKJLKSK"), "Does not return true.");
		assertEquals(false, bstString.add("XYZK"), "Does not return false.");
		
		assertEquals(true, bstInteger.add(12), "Does not return true.");
		assertEquals(false, bstInteger.add(5), "Does not return false.");
	}

	@Test
	void testClear() {
		for(int i = 0; i < 10; i++) {
			bstInteger.add(rand.nextInt(100));
		}
		bstInteger.clear();
		assertEquals(0, bstInteger.size(), "Cleared tree, size not 0");
		assertEquals(0, bstInteger.height(), "Cleared tree, height not 0");
	}

}