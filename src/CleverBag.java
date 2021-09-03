//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Simple Benchmarking
// Course: CS 300 Spring 2021
//
// Author: Zachary Collins
// Email: ztcollins@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: -
// Online Sources: -
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * methods and fields in the Bag classes will be (non-static) instance methods. This Class is a
 * child class to SimpleBag and overrides loadData()/removeRandom().
 * 
 * @author Zach C
 *
 */
public class CleverBag extends SimpleBag {
  private Integer size;

  /**
   * Calls the super class constructor with appropriate arguments. Initializes the private integer
   * field , size, which will track the current number of initialized Strings in the parent class
   * data array.
   * 
   * @param seed - int used to keep the Random objects the same
   */
  public CleverBag(int seed) {
    super(seed);
    size = 0;
  }


  /**
   * Reads the contents of the file as in the parent class, but instead inserts the new words at the
   * end of the array and then updates the size field accordingly. If you encounter any exceptions
   * while reading the File, simply return from the method.
   * 
   * Complexity: O(log(N))
   * 
   * @param f - File that is passed in and read
   */
  @Override
  public void loadData(File f) {

    try {
      Scanner scnr = new Scanner(f);
      scnr.nextLine();

      while (scnr.hasNext()) { // loops through the entire text file
        data[size] = scnr.next();
        size++;
      }
    } catch (Exception e) {
      return;
    }
  }


  /**
   * Generates a random integer between 0 and the current size. Removes and returns the String at
   * that index. Fills gaps by moving the last String into the gap and decrementing size. If the bag
   * contains no strings, this method returns null.
   * 
   * Complexity: O(1)
   * 
   * @return String removed - contains the String removed from the array
   */
  @Override
  public String removeRandom() {
    if (size == 0) {
      return null;
    }
    int randIndex = random.nextInt(size);
    String removed = data[randIndex];
    data[randIndex] = data[size - 1];
    data[size - 1] = null;
    size--;

    return removed;

  }



}
