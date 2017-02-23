/**
 * JJ Garzella and Mark Van der Merwe
 */
package lists_2420;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Linked_List_2420<Type> implements List_2420<Type> {

	private Node<Type> first;
	private Node<Type> last;
	private int size;

	/**
	 * Pictorially, a node is:
	 *
	 * data next ---------- | 5 |---+---> ----------
	 * 
	 * Note, while a 5 is used above any "Type" could be contained in the node
	 * 
	 * Our node will be the helper class for a linked list. Each node stores
	 * data for the list and stores a pointer to the next Node in the list.
	 */
	static class Node<Type> {

		private Type data;
		private Node<Type> next;

		/**
		 * Constructor adds data and points to next node.
		 * 
		 * @param the_data
		 *            - data for current node.
		 * @param after_me
		 *            - next node in list.
		 */
		Node(Type the_data, Node<Type> after_me) {
			data = the_data;
			next = after_me;
		}

		/**
		 * 
		 * Determines the length of our list.
		 * 
		 * @return the length of this "chain of nodes", including self.
		 * 
		 *         Note: 1) it doesn't matter if something (or multiple
		 *         somethings) points to this node, 2) if this node doesn't
		 *         point at anything, then the size would be 1.
		 */
		int length() {
			if (this.next == null) {
				return 1;
			}

			return 1 + next.length();
		}

		/**
		 * recursive determine if the item is in this node or the nodes after
		 * 
		 * @param item
		 *            - needle
		 * @return true if item in chain
		 */
		boolean contains_recursive(Type item) {
			if (data.equals(item)) {
				return true;
			} else if (next == null) {
				return false;
			}
			return next.contains_recursive(item);
		}

		/**
		 * 
		 * Creates an array list containing the data from this node and all
		 * nodes after it, after reversing the order.
		 */
		ArrayList<Type> to_ArrayList_post_recursive() {
			return to_ArrayList_post_recursive(new ArrayList<Type>());
		}

		/**
		 * Helper function to add elements in reverse order to an array list.
		 * 
		 * @param arrayList
		 *            - array list to add elements to.
		 * @return arraylist with this element and all elements after it added
		 *         in reverse order.
		 */
		private ArrayList<Type> to_ArrayList_post_recursive(ArrayList<Type> arrayList) {
			if (next == null) {
				arrayList.add(data);
				return arrayList;
			}
			ArrayList<Type> elementsBefore = next.to_ArrayList_post_recursive(arrayList);
			elementsBefore.add(data);
			return elementsBefore;
		}

		/**
		 * Creates a string that describes the current node and all following
		 * nodes, for example, a list of the nubmers 0, 1, 2, 3 would print as:
		 * 
		 * "[0]--> [1]--> [2]--> [3]--> null"
		 *
		 * WARNING: the syntax must be exact. "open square bracket, data, close
		 * square bracket, hyphen, hyphen, greater than space, ... null"
		 *
		 * @return a string representation of this chain of nodes
		 */
		public String toString() {
			String nextString;
			if (next == null) {
				nextString = "null";
			} else {
				nextString = next.toString();
			}
			return "[" + data + "]--> " + nextString;
		}
	}

	/**
	 * Default constructor - empty list.
	 */
	public Linked_List_2420() {
		first = null;
		size = 0;
	}

	/**
	 * Add node to list.
	 * 
	 * Note: node may already point to other nodes.
	 * 
	 * @param first
	 */
	public Linked_List_2420(Node<Type> firstNode) {
		first = firstNode;
		last = firstNode;
		size = first.length();
	}

	/**
	 * Add element data as a new node in the front of our LinkedList.
	 * 
	 * @param data
	 *            - data to be put into our new node.
	 */
	@Override
	public void add_first(Type data) {
		Node<Type> newFirst = new Node<>(data, first);
		first = newFirst;
		size++;
		// If this is our first node, place last pointer here too.
		if (size == 1) {
			last = newFirst;
		}
	}

	/**
	 * Add element data as a new node at the end of our LinkedList.
	 * 
	 * @param data
	 *            - data to be put into our new node.
	 */
	@Override
	public void add_last(Type data) {
		// If nothing in list, adds new first.
		if (first == null) {
			add_first(data);
		}

		// Create and add.
		Node<Type> newLast = new Node<>(data, null);
		last.next = newLast;
		last = newLast;

		size++;
	}

	/**
	 * Add element as a new node at the after the provided index.
	 * 
	 * @param after
	 *            - index to place new node after.
	 * @param data
	 *            - data to be put into our new node.
	 */
	@Override
	public void add_middle(int after, Type data) {
		if (first == null || after < 0) {
			if (after == 0) {
				add_first(data);
			} else {
				throw new IndexOutOfBoundsException();
			}
		}
		Node<Type> currentNode = first;
		for (int index = 0; index < after; index++) {
			if (currentNode.next == null) {
				// Die a horrible death.
				throw new IndexOutOfBoundsException();
			}
			currentNode = currentNode.next;
		}

		Node<Type> tempNext = currentNode.next;

		Node<Type> newNode = new Node<>(data, tempNext);
		currentNode.next = newNode;

		size++;
	}

	/**
	 * Clears array.
	 */
	@Override
	public void clear() {
		// Null pointer let garbage collecter do its thing.
		first = null;
		last = null;
		size = 0;
	}

	/**
	 * Test if our list contains the given data.
	 * 
	 * @param item
	 *            - item to look for inside of our list.
	 */
	@Override
	public boolean contains(Type item) {
		for (Node<Type> currentNode = first; currentNode != null; currentNode = currentNode.next) {
			if (currentNode.data.equals(item))
				return true;
		}
		return false;
	}

	/**
	 * Test if our list contains the given data (recursively).
	 * 
	 * @param item
	 *            - item to look for inside of our list.
	 */
	@Override
	public boolean contains_recursive(Type item) {
		return first.contains_recursive(item);
	}

	/**
	 * Grab the value of the first Node in the list.
	 */
	@Override
	public Type get_first() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException();
		}
		return first.data;
	}

	/**
	 * Grab the value of the last Node in the list.
	 */
	@Override
	public Type get_last() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException();
		}

		return last.data;
	}

	/**
	 * Grab first value and delete the first node.
	 */
	@Override
	public Type remove_first() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException();
		}
		Type firstValue = first.data;
		first = first.next;
		size--;
		return firstValue;
	}

	/**
	 * Grab last value and delete last node.
	 */
	@Override
	public Type remove_last() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException();
		}

		Type lastValue = last.data;

		if (first == last) {
			clear();
		} else {
			Node<Type> currentNode = first;
			while (currentNode.next != last) {
				currentNode = currentNode.next;
			}
			currentNode.next = null;
			last = currentNode;
			size--;
		}

		return lastValue;
	}

	/**
	 * Returns size of list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns list in reverse order.
	 */
	@Override
	public void reverse() {
		Node<Type> before = null;
		Node<Type> current = first;

		while (current != null) {
			Node<Type> temp = current.next;
			current.next = before;

			before = current;
			current = temp;
		}

		last = first;
		first = before;
	}

	/**
	 * Returns size of list (computed recursively).
	 */
	@Override
	public int compute_size_recursive() {
		if (first == null)
			return 0;
		return first.length();
	}

	/**
	 * Return the list as an arraylist in reverse.
	 */
	@Override
	public ArrayList<Type> to_ArrayList_post_recurse() {
		return first.to_ArrayList_post_recursive();
	}

	/**
	 * Return the list as an array list.
	 */
	@Override
	public ArrayList<Type> to_ArrayList() {
		ArrayList<Type> toArrayList = new ArrayList<>();
		for (Node<Type> currentNode = first; currentNode != null; currentNode = currentNode.next) {
			toArrayList.add(currentNode.data);
		}
		return toArrayList;
	}

	/**
	 *
	 * Creates a string that describes the contents of the list, starting with
	 * the size in parentheses for example, a list of the nubmers 0, 1, 2, 3
	 * would print as:
	 * 
	 * "(4) [0]--> [1]--> [2]--> [3]--> null"
	 *
	 * an empty list should simply return the string "empty"
	 *
	 * WARNING: the syntax must be exact. "open parenthesis, size, close
	 * parenthesis, space, open square bracket, data, close square bracket,
	 * hyphen, hyphen, greater than space, ... null"
	 *
	 * @return a string representation of this chain of nodes
	 */
	@Override
	public String toString() {
		String description = "(" + size + ") ";
		for (Node<Type> currentNode = first; currentNode != null; currentNode = currentNode.next) {
			description += "[" + currentNode.data + "]--> ";
		}
		description += "null";
		return description;
	}

}
