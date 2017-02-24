ReadMe for Assignment 6 Project

2/23/17
JJ Garzella and Mark Van der Merwe
Assginment 6 - Linked Lists, Arrays, and Recursion

Summary:
This project includes two implementations of the Linked List data structure, one using Nodes in a linked
list manner and one using an array as the backing to the implementation. Both used a List interface that
defined the functionality necessary for a proper list. We implemented using a test first methodology, where
we wrote tests for all functionality, then wrote the code to pass those tests. Finally we created a timing
class that allowed us to succinctly run through all necessary tests and write the time the tests took to
a file that could be used to analyze performance.

Notes to the TA:
1. Our Timing class is the most custom of our code. It allows us a simple interface to test the code through
without tedious code. All results are outputted onto CSV files that are stored in the project directory.
2. In linked list we chose to store pointers to the first and last node in the list as well as the size
of the list. This allowed us to optimize several functions down to O(1) in a simple, non-data heavy 
manner.
3. For array list, we created several functions that acted as the "interface" (word used non-literally)
with the backing store array. That allowed us to treat the array indices as we would like to if it were
all in order with no wrap-arounds.