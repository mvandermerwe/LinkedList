/**
 * JJ Garzella and Mark Van der Merwe
 */
package lists_2420;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Linked_List_2420<Type> implements List_2420<Type> {

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
			if(this.next == null) {
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
			if(data.equals(item)) {
				return true;
			}else if(next == null) {
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
			if(next == null) {
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
			if(next == null) {
				return "null";
			}
			return "[" + data + "]--> " + next.toString();
		}
	}

	@Override
	public void add_first(Type data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add_last(Type data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add_middle(int after, Type data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains_recursive(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type get_first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type get_last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type remove_first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type remove_last() throws NoSuchElementException {
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
	public ArrayList<Type> to_ArrayList_post_recurse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Type> to_ArrayList() {
		// TODO Auto-generated method stub
		return null;
	}

}
