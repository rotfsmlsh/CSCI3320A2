/**
 * Daniel O'Connell
 * Data Structures
 * Assignment 2
 * Due 26 October 2016
 */

package assignment2.datastructures;

import java.util.ArrayList;
import java.util.List;

public class ConcordanceBST<Key extends Comparable<Key>,Value> {
    private Node root;

    /**
     * allows for external instantiation
     */
    public ConcordanceBST(){
        this.root = null;
    }
    
    /**
     * public access to the put method
     * 
     * @param key: key to be added
     * @param value: lines the key appears on
     */
    public void put(String key, int value){
    	root = put(root,key,value);
    }
    
    /**
     * recursively adds nodes to the tree as necessary
     * 
     *@param root: the root of the current subtree
     *@param key: the key String representing the node
     *@param value: the line number that the key string was found on
     */
    private Node put(Node root, String key, int value){
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(value);        
        if(root == null){
        	return new Node(key,ints);
        }
        int cmp = key.compareTo(root.keyString);
        if(cmp == 0){
        	if(!root.lines.contains(value)){
        		root.lines.add(value);
        	}
        }
        else if(cmp < 0){
        	root.leftChild = put(root.leftChild,key,value);
        }
        else if(cmp > 0){
        	root.rightChild = put(root.rightChild,key,value);
        }
        return root;
    }
    
    /**
     * this method recursively searches the tree to see if 
     * a node with the given key exists
     * 
     * @param root: the node to searched from
     * @param key:  the key to search for
     * @return a Node to be added 
     */
    public Node find(Node root, String key){
    	if(root == null){
    		return null;
    	}
    	if(root.keyString == key){
    		return root;
    	}
    	if(root.keyString.compareTo(key) > 0){
    		return find(root.leftChild,key);
    	}
    	else{
    		return find(root.rightChild,key);
    	}
    }
    /**
     * public method to print the tree
     */
    public void print(){
        print(this.root);
    }
    
    /**
     * private method that iterates over all elements in the 
     * tree, formats their information, then prints 
     * 
     * @param root: root of the tree to print
     */
    private void print(Node root){
        if(root != null){
            print(root.leftChild);
            System.out.print(root.keyString + ": {");
            boolean flag = true;
            for (int i: root.lines){
                if(flag){
                    System.out.print(i);
                    flag = false;
                }
                else { 
                    System.out.print(", " + i);
                }
            }
            System.out.print("}\n");
            print(root.rightChild);
        }
    }
    
    /**
     * class that defines a node for the binary search tree
     */
    private class Node{
        String keyString;
        List<Integer> lines;
        Node leftChild, rightChild;
        
        /**
         * Identifies what a node in the tree is
         * 
         * @param keyString: the key of the node
         * @param linesList: the list of lines the key appears on
         */
        public Node(String keyString, List<Integer> linesList){
            this.keyString = keyString;
            this.lines = linesList;
        }
    }
}
