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

	/**
	 * allow changing between linked and array lists
	 */
	public List_2420<Integer> new_list() {
		return new Linked_List_2420<Integer>();
		// return new Array_List_2420();
	}

	@Before
	public void setUp() {
		sampleList = new_list();
		sampleList.add_first(3);
		sampleList.add_first(2);
		sampleList.add_first(5);
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
		assertTrue(sampleList.contains(2));
		assertTrue(sampleList.contains(3));
		assertTrue(sampleList.contains(5));
		assertFalse(sampleList.contains(9));
	}

	/**
	 * Test if we can tell if val exists in list using recursion. Does the same
	 * as testsContains because functionality should be identical.
	 */
	@Test
	public void testContainsRecursive() {
		if (sampleList instanceof Linked_List_2420) {
			assertTrue(sampleList.contains_recursive(2));
			assertTrue(sampleList.contains_recursive(3));
			assertTrue(sampleList.contains_recursive(5));
			assertFalse(sampleList.contains_recursive(9));
		}
	}

	/**
	 * Test that we can get the first value in a list.
	 */
	@Test
	public void testGetFirst() {
		Integer firstVal = sampleList.get_first();
		assertTrue(5 == firstVal);

		List_2420<Integer> emptyList = new_list();
		try {
			emptyList.get_first();
			// If we reach here, val exists so we fail.
			fail("Value found in empty array.");
		} catch (NoSuchElementException e) {
			// Test passes.
		}
	}

	/**
	 * Test that we can get the last val in a list.
	 */
	@Test
	public void testGetLast() {
		Integer lastVal = sampleList.get_last();
		assertTrue(3 == lastVal);

		List_2420<Integer> emptyList = new_list();
		try {
			emptyList.get_last();
			// If we reach here, val exists so we fail.
			fail("Value found in empty array.");
		} catch (NoSuchElementException e) {
			// Test passes.
		}
	}

	/**
	 * Test that we can remove the first value in a list.
	 */
	@Test
	public void testRemoveFirst() {
		Integer firstVal = sampleList.remove_first();
		assertTrue(5 == firstVal);
		assertEquals(2, sampleList.size());

		List_2420<Integer> emptyList = new_list();
		try {
			emptyList.remove_first();
			// If we reach here, val exists so we fail.
			fail("Value found in empty array.");
		} catch (NoSuchElementException e) {
			// Test passes.
		}
	}

	/**
	 * Test that we can remove the last value in a list.
	 */
	@Test
	public void testRemoveLast() {
		Integer lastVal = sampleList.remove_last();
		assertTrue(3 == lastVal);
		assertEquals(2, sampleList.size());

		List_2420<Integer> emptyList = new_list();
		try {
			emptyList.remove_last();
			// If we reach here, val exists so we fail.
			fail("Value found in empty array.");
		} catch (NoSuchElementException e) {
			// Test passes.
		}
	}

	/**
	 * Test getting the size of our list.
	 */
	@Test
	public void testGetSize() {
		assertEquals(3, sampleList.size());

		assertEquals(0, new_list().size());
	}

	/**
	 * Test getting the size of our list recursively.
	 */
	@Test
	public void testComputeSizeRecursive() {
		if (sampleList instanceof Linked_List_2420) {
			assertEquals(3, sampleList.compute_size_recursive());

			assertEquals(0, new_list().compute_size_recursive());
		}
	}

	/**
	 * Make sure we can reverse the vals in our list.
	 */
	@Test
	public void testReverse() {
		sampleList.reverse();
		try {
			assertTrue(3 == sampleList.get_first());
			assertTrue(5 == sampleList.get_last());
		} catch (NoSuchElementException e) {
			fail("No element found.");
		}
	}

	/**
	 * Turn list into ArrayList of reverse order.
	 */
	@Test
	public void testToArrayListPostRecurse() {
		if(sampleList instanceof Linked_List_2420) {
			ArrayList<Integer> reversedData = sampleList.to_ArrayList_post_recurse();
			assertTrue(3 == reversedData.get(0));
			assertTrue(2 == reversedData.get(1));
			assertTrue(5 == reversedData.get(2));
		}
	}
	
	/**
	 * Turns list into ArrayList.
	 */
	@Test
	public void testToArrayList() {
		ArrayList<Integer> reversedData = sampleList.to_ArrayList();
		assertTrue(5 == reversedData.get(0));
		assertTrue(2 == reversedData.get(1));
		assertTrue(3 == reversedData.get(2));
	}
	
	// TODO test list tostring
	
	// ----------------- NODE INNER CLASS TESTS ---------------
	
	// TODO toString
	// TODO constructor
}
