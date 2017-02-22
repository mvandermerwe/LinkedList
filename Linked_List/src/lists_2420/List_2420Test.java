/**
 * JJ Garzella and Mark Van der Merwe
 */
package lists_2420;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class List_2420Test {

	private List_2420<Integer> sampleList;
	private List_2420<Integer> smallList;

	/**
	 * allow changing between linked and array lists
	 */
	public List_2420<Integer> new_list() {
//		return new Linked_List_2420<Integer>();
		return new Array_List_2420(3);
	}

	@Before
	public void setUp() {
		sampleList = new_list();
		sampleList.add_first(3);
		sampleList.add_first(2);
		sampleList.add_first(5);

		smallList = new_list();
		smallList.add_first(4);
	}

	/**
	 * Test adding val to front of list.
	 */
	@Test
	public void testAddFirst() {
		List_2420<Integer> newList = new_list();
		newList.add_first(5);
		try {
			Integer firstVal = newList.get_first();
			assertTrue(5 == firstVal);
		} catch (NoSuchElementException e) {
			fail("get_first threw an exception");
		}
	}

	/**
	 * Test adding val to end of list.
	 */
	@Test
	public void testAddLast() {
		sampleList.add_last(5);
		try {
			Integer lastVal = sampleList.get_last();
			assertTrue(5 == lastVal);
		} catch (NoSuchElementException e) {
			fail("get_last threw an exception");
		}
	}

	/**
	 * Test clearing our list.
	 */
	@Test
	public void testClear() {
		sampleList.clear();
		try {
			sampleList.get_first();
			// If we reach here, val exists so we fail.
			fail("Value found in empty array.");
		} catch (NoSuchElementException e) {
			// Test passes.
		}

	}

	/**
	 * Test if we can tell if val exists in list.
	 */
	@Test
	public void testContains() {
		// Test on normal size list.
		assertTrue(sampleList.contains(2));
		assertTrue(sampleList.contains(3));
		assertTrue(sampleList.contains(5));
		assertFalse(sampleList.contains(9));

		// Test on small size list.
		assertTrue(smallList.contains(4));
	}

	/**
	 * Test if we can tell if val exists in list using recursion. Does the same
	 * as testsContains because functionality should be identical.
	 */
	@Test
	public void testContainsRecursive() {
		// Test on normal size list.
		assertTrue(sampleList.contains_recursive(2));
		assertTrue(sampleList.contains_recursive(3));
		assertTrue(sampleList.contains_recursive(5));
		assertFalse(sampleList.contains_recursive(9));

		// Test on small size list.
		assertTrue(smallList.contains(4));
	}

	/**
	 * Test that we can get the first value in a list.
	 */
	@Test
	public void testGetFirst() {
		// Test on normal size list.
		Integer firstVal = sampleList.get_first();
		assertTrue(5 == firstVal);

		// Test on empty list.
		List_2420<Integer> emptyList = new_list();
		try {
			emptyList.get_first();
			// If we reach here, val exists so we fail.
			fail("Value found in empty array.");
		} catch (NoSuchElementException e) {
			// Test passes.
		}

		// Test on size 1 list.
		firstVal = smallList.get_first();
		assertTrue(4 == firstVal);
	}

	/**
	 * Test that we can get the last val in a list.
	 */
	@Test
	public void testGetLast() {
		// Test on normal size array.
		Integer lastVal = sampleList.get_last();
		assertTrue(3 == lastVal);

		// Test on zero size array.
		List_2420<Integer> emptyList = new_list();
		try {
			emptyList.get_last();
			// If we reach here, val exists so we fail.
			fail("Value found in empty array.");
		} catch (NoSuchElementException e) {
			// Test passes.
		}

		// Test for one size array.
		lastVal = smallList.get_last();
		assertTrue(4 == lastVal);
	}

	/**
	 * Test that we can remove the first value in a list.
	 */
	@Test
	public void testRemoveFirst() {
		// Normal size array test.
		Integer firstVal = sampleList.remove_first();
		assertTrue(5 == firstVal);
		assertEquals(2, sampleList.size());

		// Empty array test.
		List_2420<Integer> emptyList = new_list();
		try {
			emptyList.remove_first();
			// If we reach here, val exists so we fail.
			fail("Value found in empty array.");
		} catch (NoSuchElementException e) {
			// Test passes.
		}

		// one size array test.
		firstVal = smallList.remove_first();
		assertTrue(new Integer(4).equals(firstVal));
		assertEquals(0, smallList.size());
	}

	/**
	 * Test that we can remove the last value in a list.
	 */
	@Test
	public void testRemoveLast() {
		// Normal size array test.
		Integer lastVal = sampleList.remove_last();
		assertTrue(3 == lastVal);
		assertEquals(2, sampleList.size());

		// Empty array test.
		List_2420<Integer> emptyList = new_list();
		try {
			emptyList.remove_last();
			// If we reach here, val exists so we fail.
			fail("Value found in empty array.");
		} catch (NoSuchElementException e) {
			// Test passes.
		}

		// one size array test.
		lastVal = smallList.remove_last();
		assertTrue(4 == lastVal);
		assertEquals(0, smallList.size());
	}

	/**
	 * Test getting the size of our list.
	 */
	@Test
	public void testGetSize() {
		assertEquals(3, sampleList.size());

		assertEquals(0, new_list().size());

		assertEquals(1, smallList.size());
	}

	/**
	 * Test getting the size of our list recursively.
	 */
	@Test
	public void testComputeSizeRecursive() {
		assertEquals(3, sampleList.compute_size_recursive());

		assertEquals(0, new_list().compute_size_recursive());

		assertEquals(1, smallList.compute_size_recursive());
	}

	/**
	 * Make sure we can reverse the vals in our list.
	 */
	@Test
	public void testReverse() {
		// normal size array test.
		sampleList.reverse();
		try {
			assertTrue(3 == sampleList.get_first());
			assertTrue(5 == sampleList.get_last());
		} catch (NoSuchElementException e) {
			fail("No element found.");
		}

		// If this throws an exception we know somethings up.
		// Tests zero size array.
		List_2420<Integer> zeroList = new_list();
		zeroList.reverse();

		// One size array test.
		smallList.reverse();
		try {
			assertTrue(4 == smallList.get_first());
		} catch (NoSuchElementException e) {
			fail("No element found.");
		}

	}

	/**
	 * Turn list into ArrayList of reverse order.
	 */
	@Test
	public void testToArrayListPostRecurse() {
		if (sampleList instanceof Linked_List_2420) {
			ArrayList<Integer> reversedData = sampleList.to_ArrayList_post_recurse();
			assertTrue(3 == reversedData.get(0));
			assertTrue(2 == reversedData.get(1));
			assertTrue(5 == reversedData.get(2));

			reversedData = smallList.to_ArrayList_post_recurse();
			assertTrue(4 == reversedData.get(0));
		}
	}

	/**
	 * Turns list into ArrayList.
	 */
	@Test
	public void testToArrayList() {
		// Normal size array.
		ArrayList<Integer> listData = sampleList.to_ArrayList();
		assertTrue(5 == listData.get(0));
		assertTrue(2 == listData.get(1));
		assertTrue(3 == listData.get(2));

		// Zero size array test.
		List_2420<Integer> zeroList = new_list();
		listData = zeroList.to_ArrayList();
		assertTrue(0 == listData.size());

		// One size array.
		listData = smallList.to_ArrayList();
		assertTrue(4 == listData.get(0));
	}

	/**
	 * Turn list into word representation test.
	 */
	@Test
	public void testToString() {
		// Test for normal size array.
		String shouldBe = "(3) [5]--> [2]--> [3]--> null";
		assertEquals(shouldBe, sampleList.toString());

		// Test for zero size array.
		List_2420<Integer> zeroList = new_list();
		shouldBe = "(0) null";
		assertEquals(shouldBe, zeroList.toString());

		// Test for one size array.
		shouldBe = "(1) [4]--> null";
		assertEquals(shouldBe, smallList.toString());

	}

	// ----------------- NODE INNER CLASS TESTS ---------------
	// Note : Mosts tests are completed implicitly through the testing of the
	// corresponding recursion tests for List_2420.

	/**
	 * Test toString of Node.
	 */
	@Test
	public void testToStringNode() {
		Linked_List_2420.Node<Integer> node = new Linked_List_2420.Node<Integer>(5, null);
		String nodeString = "[5]--> null";
		assertEquals(nodeString, node.toString());

		Linked_List_2420.Node<Integer> node2 = new Linked_List_2420.Node<Integer>(3, node);
		String node2String = "[3]--> [5]--> null";
		assertEquals(node2String, node2.toString());
	}

	// TODO constructor
}
