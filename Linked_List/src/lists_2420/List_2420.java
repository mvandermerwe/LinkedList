/**
 * JJ Garzella and Mark Van der Merwe
 */
package lists_2420;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 
 * This interface represents common actions done to Collections, whether
 * they are stacks, queues, bags, etc.
 * 
 * All operations should list expected Big O time
 *
 * You are to implement this interface as a Generic Linked List and as an Integer Array List
 */

public interface List_2420<Type>
{
	/*
	 * The following functions should behave in the same manner
	 * as the java.LinkedList Class
	 */
	void add_first( Type data );
	void add_last( Type data );

	/*
	 * add a node after the given node index
	 * 
	 * @param after
	 *            (for example, after = 0, would mean after the first node)
	 *            if after is larger or equal to the length of the list, then
	 *            put the new node at the end of the list. if the value of after is larger than the
	 *            length of the list, then add to the end.
	 */
	void add_middle( int after, Type data );

	
	// remove all nodes
	void clear(); 

	// return true if the item is in the list - iterative
	boolean contains( Type item ); 

	// return true if the item is in the list - recursive
	boolean contains_recursive( Type item ); 

	// the following methods throw an exception if the data does not exist (rather than return null)
	Type get_first() throws NoSuchElementException; 
	Type get_last()  throws NoSuchElementException; 
	
	Type remove_first() throws NoSuchElementException; // get value of first item and remove the node from the list
	Type remove_last()  throws NoSuchElementException;

	int size(); // return the size of the list (non-recursive)

	/**
	 * with a single traversal of the list, in place,
	 * swap the order of the entire list, such that
	 * that which was last is first and that which was first is last.
	 */
	void reverse();

	/**
	 * @return the length of the list
	 * 
	 *         for linked list: determine the size of a list using the Node<Type>.size_recursive() function
	 *         for array list: write a recursive size method
	 */
	int compute_size_recursive();

	/**
	 * @return an ArrayList of the data in the linked list in reverse order.
	 * 
	 *  recursively traverse the list, building up an ArrayList of the data.
	 * 
	 *      use the Node<Type>.to_ArrayList_post_recurse() function
	 * 
	 */
	ArrayList<Type> to_ArrayList_post_recurse();

	/**
	 * iteratively traverse the list, building up an ArrayList of the data in order.
	 * do not use Node methods.
	 */
	ArrayList<Type> to_ArrayList();


}
