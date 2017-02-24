/**
 * JJ Garzella and Mark Van der Merwe
 */
package lists_2420;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Timing {

	static enum InsertType {
		BEGINNING, MIDDLE, END, REMOVEBEGIN, REMOVEEND;

		@Override
		public String toString() {
			switch (this) {
			case BEGINNING:
				return "beginning";
			case MIDDLE:
				return "middle";
			case END:
				return "end";
			case REMOVEBEGIN:
				return "removebegin";
			case REMOVEEND:
				return "removeend";
			default:
				throw new RuntimeException("Unknown case of InsertType enum");
			}
		}
	}

	private static int NUM_ITERATIONS = 100;
	private static Random generator = new Random();
	private static final Integer[] N_VALUES = { 400_000, 800_000, 1_200_000, 1_600_000, 2_000_000, 2_400_000, 2_800_000,
			3_200_000, 3_600_000, 4_000_000 };

	public static void insertIntoBeginning(List_2420<Integer> list) {
		for (int counter = 0; counter < NUM_ITERATIONS; counter++) {
			list.add_first(1);
		}
	}

	public static void removeFromBeginning(List_2420<Integer> list) {
		list.remove_first();
	}

	public static void insertIntoMiddle(List_2420<Integer> list, int index) {
		for (int counter = 0; counter < NUM_ITERATIONS; counter++) {
			list.add_middle(index, 1);
		}
	}

	public static void insertAtEnd(List_2420<Integer> list) {
		list.remove_last();
	}

	public static void removeFromEnd(List_2420<Integer> list) {
		list.remove_last();
	}

	public static void createArray(List_2420<Integer> list, int numberOfElements) {
		for (int counter = 0; counter < numberOfElements; counter++) {
			list.add_last(1);
		}
	}

	public static void testInsertMethod(Class<?> listClass, InsertType methodType, String fileSuffix) {

		// use a string builder, so we only have to write to file once.
		StringBuilder fileData = new StringBuilder();

		// Make a new array of the proper concrete subclass
		List_2420<Integer> testArray;
		if (Array_List_2420.class.equals(listClass)) {
			testArray = new Array_List_2420();
		} else if (Linked_List_2420.class.equals(listClass)) {
			testArray = new Linked_List_2420<Integer>();
		} else {
			throw new IllegalArgumentException("The listClass paramater must be a List_2420");
		}

		// in add_middle, the random generator for the index
		// cannot generate a number between 0 and 0. Add one
		// element to the array to avoid this
		// testArray.add_first(1);

		// perform the test for all specified values of N in the array N_VALUES
		for (int numElements : N_VALUES) {
			System.out.println(
					"Starting test: " + numElements + " " + listClass.getSimpleName() + " " + methodType.toString());
			long start;
			long end;

			createArray(testArray, numElements);

			switch (methodType) {
			case BEGINNING:
				start = System.nanoTime();
				insertIntoBeginning(testArray);
				end = System.nanoTime();
				break;
			case MIDDLE:
				int index = generator.nextInt(testArray.size());
				start = System.nanoTime();
				insertIntoMiddle(testArray, index);
				end = System.nanoTime();
				break;
			case END:
				start = System.nanoTime();
				insertAtEnd(testArray);
				end = System.nanoTime();
				break;
			case REMOVEBEGIN:
				start = System.nanoTime();
				removeFromBeginning(testArray);
				end = System.nanoTime();
				break;
			case REMOVEEND:
				start = System.nanoTime();
				removeFromEnd(testArray);
				end = System.nanoTime();
				break;
			default:
				throw new RuntimeException("Unknown case of InsertType inner enum");
			}

			double elapsedSeconds = ((end - start) / ((double) 1_000_000_000)) / NUM_ITERATIONS;

			fileData.append(numElements + "," + elapsedSeconds + "\n");
		}

		// Write the data we now have to a csv file
		String fileName = listClass.getSimpleName() + "_" + methodType.toString() + "_" + fileSuffix + ".csv";
		try {
			FileWriter csvWriter = new FileWriter(fileName);
			csvWriter.write(fileData.toString());
			csvWriter.close();
		} catch (IOException e) {
			System.out.println("Unable to write to file. Here is the test data, though:");
			System.out.print(fileData.toString());
		}
	}

	public static void main(String[] args) {

		String suffix = "norand_2";

//		testInsertMethod(Linked_List_2420.class, InsertType.BEGINNING, suffix);
//		testInsertMethod(Linked_List_2420.class, InsertType.MIDDLE, suffix);
//		testInsertMethod(Linked_List_2420.class, InsertType.END, suffix);
//
//		testInsertMethod(Array_List_2420.class, InsertType.BEGINNING, suffix);
//		testInsertMethod(Array_List_2420.class, InsertType.MIDDLE, suffix);
//		testInsertMethod(Array_List_2420.class, InsertType.END, suffix);
		
		//testInsertMethod(Linked_List_2420.class, InsertType.REMOVEBEGIN, suffix);
		testInsertMethod(Linked_List_2420.class, InsertType.REMOVEEND, suffix);
		
		//testInsertMethod(Array_List_2420.class, InsertType.REMOVEBEGIN, suffix);
		testInsertMethod(Array_List_2420.class, InsertType.REMOVEEND, suffix);
	}

}
