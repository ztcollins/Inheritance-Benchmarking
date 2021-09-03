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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * All methods and fields in the Benchmark class will be class methods (static) This class contains
 * test code in the main method (commented out). The class also contains 3 methods in order to show
 * the efficiency of the CleverBag and SimpleBag's load and remove methods with the output of a file
 * containing the efficiency of each.
 * 
 * @author Zach C
 *
 */
public class Benchmark {

  /**
   * main method used to test code (all commented out)
   * 
   * @param args
   */
  public static void main(String[] args) {


    File f = new File("frank.txt");
    File test = new File("test.txt");
    int[] nVals = {10, 100, 1000, 10000, 50000};

    createResultsFile(f, test, nVals);


    /*
     * TESTS FOR COMPARE METHODS File f = new File("frank.txt"); SimpleBag baggy = new SimpleBag(7);
     * CleverBag bagger = new CleverBag(7); System.out.println(compareLoadData(f, baggy, bagger));
     * System.out.println(compareRemove(20000, baggy, bagger));
     */

    /*
     * TESTS FOR CLEVER AND SIMPLE BAG CLASSES File f = new File("testy.txt"); SimpleBag baggy = new
     * SimpleBag(7); baggy.loadData(f); System.out.println("LOAD TEST #1"); for(int i = 0; i < 10;
     * i++) { System.out.println(baggy.data[i]); } baggy.removeRandom();
     * System.out.println("REMOVE TEST #1"); for(int i = 0; i < 10; i++) {
     * System.out.println(baggy.data[i]); }
     * 
     * CleverBag bagger = new CleverBag(5); bagger.loadData(f); System.out.println("LOAD TEST #2");
     * for(int i = 0; i < 10; i++) { System.out.println(bagger.data[i]); } bagger.removeRandom();
     * System.out.println("REMOVE TEST #2"); for(int i = 0; i < 10; i++) {
     * System.out.println(bagger.data[i]); }
     */

  }


  /**
   * Runs both classes loadData() implementations on the same text file. Tracks the time spent in
   * milliseconds to complete each loadData(). Returns a formatted String with the elapsed times for
   * each of the bag types.
   * 
   * @param f - File that is passed in and read
   * @param s - SimpleBag object that uses simple methods
   * @param c - CleverBag object extends SimpleBag and uses more effecient methods
   * @return String - formatted String with the time effeciencies
   */
  public static String compareLoadData(File f, SimpleBag s, CleverBag c) {
    long simpleTime1 = System.currentTimeMillis();
    s.loadData(f);
    long simpleTime2 = System.currentTimeMillis();

    long cleverTime1 = System.currentTimeMillis();
    c.loadData(f);
    long cleverTime2 = System.currentTimeMillis();

    long simpleTime = simpleTime2 - simpleTime1;
    long cleverTime = cleverTime2 - cleverTime1;

    return "load:\t" + simpleTime + "\t" + cleverTime + "\n";

  }


  /**
   * Runs both classes removeRandom() method n times. Tracks the time spent in milliseconds to
   * complete each type of remove. Returns a formatted string with n and the elapsed times for each
   * of the bag types.
   * 
   * @param n - int n value that tells how many times to remove in removeRandom()
   * @param s - SimpleBag object that uses simple methods
   * @param c - CleverBag object extends SimpleBag and uses more effecient methods
   * @return String - formatted String with the time effeciencies
   */
  public static String compareRemove(int n, SimpleBag s, CleverBag c) {
    long simpleTime1 = System.currentTimeMillis();
    for (int i = 0; i < n; i++) {
      s.removeRandom();
    }
    long simpleTime2 = System.currentTimeMillis();

    long cleverTime1 = System.currentTimeMillis();
    for (int i = 0; i < n; i++) {
      c.removeRandom();
    }
    long cleverTime2 = System.currentTimeMillis();

    long simpleTime = simpleTime2 - simpleTime1;
    long cleverTime = cleverTime2 - cleverTime1;

    return n + "\t" + simpleTime + "\t" + cleverTime + "\n";

  }


  /**
   * Creates one instance each of a SimpleBag and a CleverBag. Calls compareLoadData() to compare
   * the two different data loads using the in parameter. Calls compareRemove() on each of the
   * provided nValues to compare the two different remove implementations. Writes the results of the
   * data load comparison followed by the remove comparisons to a file specified by the out
   * parameter. Handles any exceptions raised by the methods it uses.
   * 
   * @param in      - File that is passed in and read
   * @param out     - File that is output with new data
   * @param nValues - Array of int that contains all n values for compareRemove
   */
  public static void createResultsFile(File in, File out, int[] nValues) {
    SimpleBag simpBag = new SimpleBag(10000);
    CleverBag clevBag = new CleverBag(10000);

    String compareLoad = compareLoadData(in, simpBag, clevBag);
    String[] compareRemove = new String[nValues.length];
    for (int i = 0; i < nValues.length; i++) {
      compareRemove[i] = compareRemove(nValues[i], simpBag, clevBag);
    }

    try {
      FileWriter fileWriter = new FileWriter(out);
      PrintWriter printWriter = new PrintWriter(fileWriter);
      printWriter.print(compareLoad);
      for (int i = 0; i < compareRemove.length; i++) {
        printWriter.print(compareRemove[i]);
      }
      printWriter.close();
    } catch (IOException e) {
      System.out.println("ERROR: IOEXCEPTION");
      e.printStackTrace();
    }

  }

}
