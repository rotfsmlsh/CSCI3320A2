/**
 * Daniel O'Connell
 * Data Structures
 * Assignment 2
 * Due 26 October 2016
 */

package assignment2.processor;

import java.io.File;

import assignment2.datastructures.ConcordanceBST;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConcordanceProcessor {
    
	/**
	 * prinary function of the program
	 * 
	 * @param args[0] holds the filename to use
	 */
    public static void main(String[] args) {
        String filename = "";
        try{
            filename = args[0];
            File inputText = new File(filename);
            parseFile(inputText);
        }
        catch(ArrayIndexOutOfBoundsException aiobe){
            System.out.println("Please enter a valid filename as the first argument to the executable.");
        }
    }
    
    /**
     * parses the file and sends key-value pairs to the binary search tree
     * 
     * @param input: the whole file to be parsed
     */
    private static void parseFile(File input){
        int currentLineNum = 1;
        String[] currentLine;
        Scanner fileScanner = null;
        ConcordanceBST<String, Integer> bst = new ConcordanceBST<>();
        try {
            fileScanner = new Scanner(input);
            while (fileScanner.hasNext()){
            	//strip down the strings and put them in a string array for easy parsing
                currentLine = fileScanner.nextLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                //iterate over each word and put it in the map/tree
                for(String s : currentLine){
                    bst.put(s, currentLineNum);
                }
                ++currentLineNum;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            if(fileScanner != null){
                fileScanner.close();
            }
            bst.print();
        }
    }
}
