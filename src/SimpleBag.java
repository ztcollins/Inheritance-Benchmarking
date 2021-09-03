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
import java.util.Random;
import java.util.Scanner;


/**
 * methods and fields in the Bag classes will be (non-static) instance methods. This is the parent
 * class of CleverBag and serves as an object with much more rudimentary methods. There is a
 * constructor which creates an array and instantiates the object. There is also loadData() which
 * stores all words from a File into an array within the object. The last method is removeRandom()
 * which takes a random index from the array's object and removes it and shifts all other Strings to
 * compact.
 * 
 * Complexity: O(N)
 * 
 * @author Zach C
 *
 */
public class SimpleBag {
  protected String data[];
  protected Random random;

  /**
   * Initializes a protected field, data, which is an array of Strings with capacity 80,000. We will
   * not provide files with more than 80,000 words. Initializes a protected Random object, random,
   * using the provided seed value.
   * 
   * @param seed - int used to keep the Random objects the same
   */
  public SimpleBag(int seed) {
    data = new String[80000];
    random = new Random(seed); // seed could be something else?

  }


  /**
   * Reads the text contents of the provided file, inserting each new space-separated word at the
   * beginning of the data array. All strings currently in the array should be shifted to the right
   * by one index to make room. That is, the string at index N should be moved to index N+1, and so
   * forth. If you encounter any exceptions while reading the File, simply return from the method.
   * 
   * Complexity: O(N^2)
   * 
   * @param f - File that is passed in and read
   */
  public void loadData(File f) {
    int currentWords = 0;


    try {
      Scanner scnr = new Scanner(f);
      scnr.nextLine();

      while (scnr.hasNext()) { // loops through the entire text file
        for (int i = currentWords - 1; i > -1; i--) { // loops through the data array backwards
          data[i + 1] = data[i];
        }
        data[0] = scnr.next();
        currentWords++;
      }
    } catch (Exception e) {
      return;
    }
  }


  /**
   * Counts the number of Strings (i.e. non-null) values in the data array and generates a random
   * index between 0 and the number of Strings stored in this bag (exclusive).. Removes and returns
   * the String at that index. Fills gaps by moving all following strings to the left by one index.
   * N -> N-1, etc. If the bag contains no strings, this method returns null.
   * 
   * Complexity: O(log(N)+N)
   * 
   * @return String removed - String that is contained within the removed index
   */
  public String removeRandom() {
    int count = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i] != null) {
        count++;
      }
    }
    if (count == 0) {
      return null;
    }
    int randIndex = random.nextInt(count);
    String removed = data[randIndex];
    data[randIndex] = null;

    for (int i = randIndex; i < count; i++) {
      data[i] = data[i + 1];
    }

    return removed;

  }

}
