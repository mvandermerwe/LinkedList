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
	 * The array contains the elements, with the rest of the elements being null.
	 */
	private Integer[] backingStore = new Integer[1024]; // TODO change to 2 for testing
	// both add methods must be tested when they will make the size of the array bigger
	private int size = 0;
	private int start = 0;

	
	/**
	 * Get an element out from the backing store
	 * 
	 * Used to compute the true indices of the array, from 
	 * "normal indices" which range from 0 to size.
	 * 
	 * This abstracts away having to use the modulo every
	 * time we get an element of the array.
	 * 
	 * @param index an index from 0 to size
	 * @return the value at index
	 */
	private Integer backingStoreGet(int index) {
		int backingStoreIndex = (start + index ) % backingStore.length;
		return backingStore[backingStoreIndex];
	}
	
	/**
	 * Set the element at index to be item
	 * 
	 * Used to compute true indices of the array, from 
	 * "normal indices" which range from 0 to size.
	 * 
	 * This abstracts away having to use the modulo every
	 * time we get an element of the array.
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
	 * @param integers an array of integers (can have null values in it)
	 */
	public Array_List_2420(Integer[] integers) {
		for (Integer integer : integers) {
			add_last(integer);
		}
	}
	
	public Array_List_2420() {
		// we don't need to do anything, all of the fields have good default values.
	}
	
	/**
	 * Double the size of the backing store, in order to fit new elements
	 */
	private void expandArray() {
		// allocate and copy new array
		Integer[] newBackingStore = new Integer[backingStore.length*2];
		for (int index = 0; index < backingStore.length; index++) {
			newBackingStore[index] = backingStoreGet(index);
		}
		backingStore = newBackingStore;
		start = 0;
	}
	
	/**
	 * Swap the values at two indexes in the array
	 * 
	 * If the two indices are the same, this method is a
	 * pointless waste of a few computer cycles.
	 * 
	 * @param firstIndex a valid index in the array
	 * @param secondIndex a valid index, distinct from firstIndex
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
		add_middle(0,data);
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
			add_first(data);
		}

		// move each elment in the array over one index
		for (int index = size; after < index; index--) {
			swap(index,index-1);
		}
		// now, there is a 'null' at index after
		backingStoreSet(after, data);
		size++;
	}

	/**
	 * Remove all elments from the array
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
	 * Determines if 'item' is contained between
	 * index and the end of the array.
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
		return contains_recursive_helper(item,index);
	}
	
	/**
	 * Recursive implementation of contains()
	 */
	@Override
	public boolean contains_recursive(Integer item) {
		return contains_recursive_helper(item,0);
	}

	@Override
	public Integer get_first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer get_last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer remove_first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer remove_last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reverse() {
		// TODO Auto-generated method stub

	}

	@Override
	public int compute_size_recursive() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList to_ArrayList_post_recurse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList to_ArrayList() {
		// TODO Auto-generated method stub
		return null;
	}

}
