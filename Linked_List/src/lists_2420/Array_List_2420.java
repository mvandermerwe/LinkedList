/**
 * JJ Garzella and Mark Van der Merwe
 */
package lists_2420;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author markvandermerwe
 *
 */
public class Array_List_2420 implements List_2420<Integer> {

	/**
	 * The array contains the elements, with the rest of the elements being
	 * null.
	 */
	private Integer[] backingStore = new Integer[2]; // TODO change to 2 for
														// testing
	// both add methods must be tested when they will make the size of the array
	// bigger
	private int size = 0;
	private int start = 0;

	/**
	 * Get an element out from the backing store
	 * 
	 * Used to compute the true indices of the array, from "normal indices"
	 * which range from 0 to size.
	 * 
	 * This abstracts away having to use the modulo every time we get an element
	 * of the array.
	 * 
	 * @param index
	 *            an index from 0 to size
	 * @return the value at index
	 */
	private Integer backingStoreGet(int index) {
		int backingStoreIndex = (start + index) % backingStore.length;
		return backingStore[backingStoreIndex];
	}

	/**
	 * Set the element at index to be item
	 * 
	 * Used to compute true indices of the array, from "normal indices" which
	 * range from 0 to size.
	 * 
	 * This abstracts away having to use the modulo every time we get an element
	 * of the array.
	 * 
	 * @param index
	 * @param item
	 */
	private void backingStoreSet(int index, Integer item) {
		int backingStoreIndex = (start + index) % backingStore.length;
		backingStore[backingStoreIndex] = item;
	}

	/**
	 * Construct an array from an array of integers
	 * 
	 * @param integers
	 *            an array of integers (can have null values in it)
	 */
	public Array_List_2420(Integer[] integers) {
		for (Integer integer : integers) {
			add_last(integer);
		}
	}

	public Array_List_2420() {
		// we don't need to do anything, all of the fields have good default
		// values.
	}

	/**
	 * Double the size of the backing store, in order to fit new elements
	 */
	private void expandArray() {
		// allocate and copy new array
		Integer[] newBackingStore = new Integer[backingStore.length * 2];
		for (int index = 0; index < backingStore.length; index++) {
			newBackingStore[index] = backingStoreGet(index);
		}
		backingStore = newBackingStore;
		start = 0;
	}

	/**
	 * Swap the values at two indexes in the array
	 * 
	 * If the two indices are the same, this method is a pointless waste of a
	 * few computer cycles.
	 * 
	 * @param firstIndex
	 *            a valid index in the array
	 * @param secondIndex
	 *            a valid index, distinct from firstIndex
	 */
	private void swap(int firstIndex, int secondIndex) {
		Integer temp = backingStoreGet(firstIndex);
		backingStoreSet(firstIndex, backingStoreGet(secondIndex));
		backingStoreSet(secondIndex, temp);
	}

	/**
	 * Add an Integer at the beginning of the array
	 * 
	 * Takes O(n) time
	 */
	@Override
	public void add_first(Integer data) {
		add_middle(0, data);
	}

	/**
	 * Add an Integer at the end of the array
	 * 
	 * Takes O(1) time
	 */
	@Override
	public void add_last(Integer data) {

		if (size >= backingStore.length) {
			expandArray();
			// recurse once, now with more room in the array.
			add_last(data);
		}

		backingStoreSet(size, data);
		size++;
	}

	/**
	 * Add an Integer somewhere int the middle of the array
	 * 
	 * On average, O(n) time
	 */
	@Override
	public void add_middle(int after, Integer data) {

		if (size >= backingStore.length) {
			expandArray();
			// recurse once, now with more room in the array.
			add_middle(after, data);
		}

		// move each elment in the array over one index
		for (int index = size; after < index; index--) {
			swap(index, index - 1);
		}
		// now, there is a 'null' at index after
		backingStoreSet(after, data);
		size++;
	}

	/**
	 * Remove all elements from the array
	 */
	@Override
	public void clear() {
		for (int index = 0; index < size; index++) {
			backingStoreSet(index, null);
		}
		size = 0;
	}

	/**
	 * Does the array contain 'item'?
	 */
	@Override
	public boolean contains(Integer item) {
		for (int index = 0; index < size; index++) {
			if (backingStoreGet(index) == item) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Recursive helper method for contains()
	 * 
	 * Determines if 'item' is contained between index and the end of the array.
	 * 
	 * @param item
	 * @param index
	 * @return
	 */
	private boolean contains_recursive_helper(Integer item, int index) {
		if (index == size) {
			return false;
		}
		if (backingStoreGet(index) == item) {
			return true;
		}
		return contains_recursive_helper(item, index + 1);
	}

	/**
	 * Recursive implementation of contains()
	 */
	@Override
	public boolean contains_recursive(Integer item) {
		return contains_recursive_helper(item, 0);
	}

	/**
	 * Return the value of the first position in the array.
	 * 
	 * Takes O(1) time.
	 */
	@Override
	public Integer get_first() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return backingStoreGet(0);
	}

	/**
	 * Return the val at end of array_list.
	 * 
	 * Takes O(1) time.
	 */
	@Override
	public Integer get_last() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return backingStoreGet(size - 1);
	}

	/**
	 * Returns, then removes, the val at the first position in the array.
	 * 
	 * Takes O(n) time.
	 */
	@Override
	public Integer remove_first() throws NoSuchElementException {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		
		Integer first = get_first();

		// Set first val to null.
		backingStoreSet(0, null);

		// Move all elements to left one.
//		for (int index = 1; backingStore[index] != null; index++) {
//			swap(index, index - 1);
//		}

		start++;
		size--;
		return first;
	}

	/**
	 * Returns, then removes, the val at the last position in the array.
	 * 
	 * Takes O(1) time.
	 */
	@Override
	public Integer remove_last() throws NoSuchElementException {
		Integer last = get_last();

		// Set last val to null.
		backingStoreSet(size - 1, null);

		size--;
		return last;
	}

	/**
	 * Return the size of our array. Note: this is not the size of the backing
	 * array but rather that of the actual vals.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Reverse the order of the list.
	 */
	@Override
	public void reverse() {
		if (size == 0 || size == 1) {
			return;
		}
		
		int middle = size / 2;

		for (int index = 0; index <= middle; index++) {
			swap(index, size - 1 - index);
		}
	}

	/**
	 * Computes the size of our array_list through recursion.
	 * 
	 * @param index
	 *            - index in array.
	 * @return 1 + size of array after it.
	 */
	private int compute_size_recursive_helper(int index) {
		if (backingStoreGet(index) == null) {
			return 0;
		}

		return 1 + compute_size_recursive_helper(index + 1);
	}

	/**
	 * Computes the size of array_list through recursion.
	 */
	@Override
	public int compute_size_recursive() {
		// Use helper function. Let it do its thing.
		return compute_size_recursive_helper(0);
	}

	@Override
	public ArrayList<Integer> to_ArrayList_post_recurse() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Return our list as an ArrayList.
	 */
	@Override
	public ArrayList<Integer> to_ArrayList() {
		ArrayList<Integer> toArrayList = new ArrayList<Integer>();
		// Simply add each element to an arraylist.
		for (int index = 0; index < size; index++) {
			toArrayList.add(backingStoreGet(index));
		}
		return toArrayList;
	}

	@Override
	public String toString() {
		String toString = "(" + size + ") ";
		for(int index = 0; index < size; index++) {
			toString += "[" + backingStoreGet(index) + "]--> ";
		}
		toString += "null";
		return toString;
	}
}
