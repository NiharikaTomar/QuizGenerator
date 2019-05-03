package application;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P3a HashTable
// Files: HashTable.java
// Course and lecture: CS 400 lecture 004, Spring 2019
// Due date: march 14 2019
// Author: Niharika Tomar
// Email: ntomar@wisc.edu
// Lecturer's Name: Andrew Kuemmel
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 100 COLUMNS WIDE ///////////////////////////////
/**
 * This class aims to implement the HashTableADT class using OPEN ADDRESSING: with linear probing.
 * 
 * @author niharikatomar
 */

import java.util.*;

// This class uses open addressing: linear probing for the hash table implementation
// and rehashing as the collision resolution.
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

  // private Node class for hash table implementation
  private static class Node<K, V> {
    private K key;
    private V value;

    private Node(K key, V value) { // initializing key and value
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      // TODO Auto-generated method stub
      return this.key;
    }
  }

  // Adding DATA FIELD MEMBERS needed for the implementation
  private double thresh; // threshold factor
  private int tableSize; // starting current size
  private int size; // total number of elements in the hash table
  private Node<K, V> table[]; // hash table

  // a default no-argument constructor
  public HashTable() {
    this.tableSize = 49157; // initializing hash table to a prime size
    this.thresh = 0.75; // threshold factor
    this.size = 0; // total number of elements in the hash table initialized
    this.table = (Node<K, V>[]) new Node<?, ?>[tableSize]; // hash table initialization
    for (int i = 0; i < tableSize; i++) {
      table[i] = null; // setting all indices of table to be null
    }
  }

  /*
   * A constructor that accepts initial capacity and load factor threshold The threshold is the load
   * factor that causes a resize and rehash
   */
  public HashTable(int initialCapacity, double loadFactorThreshold) {
    if (initialCapacity <= 0) { // checking for initial capacity to be positive
      throw new IllegalArgumentException();
    }
    // initializing data
    this.tableSize = initialCapacity;
    this.thresh = loadFactorThreshold;
    this.size = 0;
    this.table = (Node<K, V>[]) new Node<?, ?>[tableSize];
    for (int i = 0; i < tableSize; i++) {
      table[i] = null; // setting all indices of table to be null
    }
  }

  /*
   * private helper method to get the hashIndex of the key
   */
  private int getHashIndex(K key) {
    int hashCode = Math.abs(key.hashCode());
    return hashCode % this.tableSize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // check if the key is not null
    if (key == null) {
      throw new IllegalNullKeyException(); // throw exception
    }
    int index = getHashIndex(key);
    if (index < 0) {
      index = -index; // getting a negative index if it is less than 0
    }
    // checking for a duplicate key
    if (contains(key)) { // if duplicate found
      throw new DuplicateKeyException();// throw exception
    }
    int hashIndex = lookup(key, index);
    // putting Key value pair in hash table
    if (hashTable(key, value, hashIndex)) {
      this.size++; // incrementing number of elements in table
    }
    // checking if load factor exceeds threshold
    if (getLoadFactor() > getLoadFactorThreshold()) {
      int newTableSize = (this.tableSize * 2) + 1; // resizing table
      int newHashIndex = 0;
      Node<K, V> newTable[] = (Node<K, V>[]) new Node<?, ?>[newTableSize]; // new hash table
      Node<K, V>[] tempTable = table;
      this.table = newTable;
      int tempSize = tableSize;
      tableSize = newTableSize;
      // rehashing the table
      for (int i = 0; i <= tempSize - 1; i++) {
        if (tempTable[i] != null) { // if key is not null
          Node<K, V> temp = tempTable[i];
          newHashIndex = lookup(temp.key, getHashIndex(temp.key));
          this.table[newHashIndex] = temp;
        }
      }
    }
  }

  /*
   * private helper method to set the hash table
   */
  private boolean hashTable(K key, V value, int hash) {
    this.table[hash] = new Node<K, V>(key, value);
    return true;
  }

  /*
   * private helper method to lookup a key at a given index.
   */
  public int lookup(K key, int hashIndex) {
    int i = hashIndex;
    while (table[i] != null) {
      if (i == this.tableSize - 1) {
        i = -1;
      }
      i++;
    }
    return i;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    if (key == null) { // null key check
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // duplicate key check
      return false;
    }
    int hashIndex = getHashIndex(key); // getting hash index
    int i = hashIndex;
    while (this.table[i] != null && !this.table[i].key.equals(key)) {
      if (i == this.tableSize - 1) {
        i = -1;
      }
      i++; // incrementing counter i
    }
    if (this.table[i] != null && this.table[i].key.equals(key)) {
      table[i] = null; // removing a node/key at a given index
      this.size--; // decrementing table size
      return true;
    }
    return false;
  }

  /*
   * private helper method to check whether the table contains a key or not. If duplicate found,
   * returns -1
   */
  public boolean contains(K key) {
    int hashIndex = getHashIndex(key); // getting hash index for key
    int i = hashIndex;
    while (table[i] != null && !table[i].key.equals(key)) {
      if (i == tableSize - 1) {
        i = -1;
      }
      i++; // incrementing counter i
    }
    if (table[i] != null && table[i].key.equals(key))
      return true;
    return false;

  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#get(java.lang.Comparable)
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) { // null check
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // absent key check
      throw new KeyNotFoundException();
    }
    V value = getVal(key); // getting value of a key
    return value;
  }

  /*
   * private helper method to get the value of a certain key
   */
  private V getVal(K key) {
    int hashIndex = getHashIndex(key); // get hash index
    int i = hashIndex;
    while (this.table[i] != null && !this.table[i].key.equals(key)) {
      if (i == tableSize - 1) {
        i = -1;
      }
      i++; // incrementing counter i
    }
    if (this.table[i] != null && this.table[i].key.equals(key)) {
      return this.table[i].value; // returning value at the given key
    }
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#numKeys()
   */
  @Override
  public int numKeys() {
    return this.size;
  }

  /*
   * (non-Javadoc)
   * 
   * @see HashTableADT#getLoadFactorThreshold()
   */
  @Override
  public double getLoadFactorThreshold() {
    return this.thresh;
  }

  /*
   * (non-Javadoc)
   * 
   * @see HashTableADT#getLoadFactor()
   */
  @Override
  public double getLoadFactor() {
    return (double) this.size / this.tableSize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see HashTableADT#getCapacity()
   */
  @Override
  public int getCapacity() {
    return this.tableSize;
  }

  /*
   * (non-Javadoc) uses OPEN ADDRESSING: linear probe
   * 
   * @see HashTableADT#getCollisionResolution()
   */
  @Override
  public int getCollisionResolution() {
    return 1;
  }



  public ArrayList<K> keySet() {
    ArrayList<K> keys = new ArrayList<K>();
    for (int i = 0; i < table.length; i++) {
      if (!(table[i] == null)) {
        Node<K, V> node = (Node<K, V>) table[i];
        keys.add(node.getKey());
      }
    }
    return keys;
  }
}
