/**
 * JJ Garzella and Mark Van der Merwe
 */
package lists_2420;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Linked_List_2420<Type> implements List_2420<Type> {

	private Node<Type> first;
	private int size;

	/**
	 * FIXME: comments
	 *
	 * Pictorially, a node is:
	 *
	 * data next ---------- | 5 |---+---> ----------
	 * 
	 * Note, while a 5 is used above any "Type" could be contained in the node
	 */
	static class Node<Type> {
		//
		private Type data;
		private Node<Type> next;

		/**
		 * 
		 * @param the_data
		 * @param after_me
		 */
		Node(Type the_data, Node<Type> after_me) {
			data = the_data;
			next = after_me;
		}

		/**
		 * 
		 * This function must be written recursively.
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
		 * This function must be written recursively (using a helper method,
		 * with the arraylist as a parameter, to do the recursion)
		 * 
		 * Create an array list containing the data from this node and all nodes
		 * after it.
		 * 
		 * In the helper method, add the data to the array list after the
		 * recursive call, thus "reversing" the list.
		 *
		 */
		ArrayList<Type> to_ArrayList_post_recursive() {
			return to_ArrayList_post_recursive(new ArrayList<Type>());
		}

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
		 *
		 * FIXME: this method must be implemented using recursion
		 *
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
		size = first.length();
	}

	@Override
	public void add_first(Type data) {
		Node<Type> newFirst = new Node<>(data, first);
		first = newFirst;
		size++;
	}

	@Override
	public void add_last(Type data) {
		if (first == null) {
			add_first(data);
		}
		Node<Type> currentNode = first;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		Node<Type> newLast = new Node<>(data, null);
		currentNode.next = newLast;
		size++;
	}

	@Override
	public void add_middle(int after, Type data) {
		if (first == null) {
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
		size = 0;
	}

	@Override
	public boolean contains(Type item) {
		for (Node<Type> currentNode = first; currentNode != null; currentNode = currentNode.next) {
			if (currentNode.data.equals(item)) return true;
		}
		return false;
	}

	@Override
	public boolean contains_recursive(Type item) {
		return first.contains_recursive(item);
	}

	@Override
	public Type get_first() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException();
		}
		return first.data;
	}

	@Override
	public Type get_last() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException();
		}
		Node<Type> currentNode = first;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		return currentNode.data;
	}

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

	@Override
	public Type remove_last() throws NoSuchElementException {
		if (first == null) {
			throw new NoSuchElementException();
		}
		Type lastValue;
		if (first.next == null) {
			lastValue = first.data;
			first = null;
		} else {
			Node<Type> currentNode = first;
			while (currentNode.next.next != null) {
				currentNode = currentNode.next;
			}
			lastValue = currentNode.next.data;
			currentNode.next = null;
		}		
		size--;
		return lastValue;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void reverse() {
		Node<Type> before = null;
		Node<Type> current = first;
		
		while(current != null) {
			Node<Type> temp = current.next;
			current.next = before;
			
			before = current;
			current = temp;
		}
		
		first = before;
	}

	@Override
	public int compute_size_recursive() {
		if (first == null) return 0;
		return first.length();
	}

	@Override
	public ArrayList<Type> to_ArrayList_post_recurse() {
		return first.to_ArrayList_post_recursive();
	}

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
	 * FIXME: this method must NOT use recursion 
	 * FIXME: for our purposes DO NOT use the Node toString method here
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
