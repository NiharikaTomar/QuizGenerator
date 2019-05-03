/**
 * Filename: HashTable.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */
package application;

import java.util.*;

/**
 * This class uses open addressing: linear probing for the hash table implementation and rehashing
 * as the collision resolution.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 *
 * @param <K> Key of the node
 * @param <V> Value of the node
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

  /**
   * Private Node class for hash table implementation
   * 
   * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
   *
   * @param <K> Key of the node
   * @param <V> Value of the node
   */
  private static class Node<K, V> {
    private K key;
    private V value;

    /**
     * Constructs a Node Object; initializes key and value
     * 
     * @param key   of the node
     * @param value of the node
     */
    private Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    /**
     * Public getter of the key.
     * 
     * @return key of the node
     */
    public K getKey() {
      return this.key;
    }
  }

  /**
   * DATA FIELD MEMBERS needed for the implementation
   */
  private double thresh; // threshold factor
  private int tableSize; // starting current size
  private int size; // total number of elements in the hash table
  private Node<K, V> table[]; // hash table

  // a default no-argument constructor
  /**
   * A default no-argument constructor.
   */
  public HashTable() {
    // Initialize hash table to a prime size
    this.tableSize = 49157;
    // Threshold factor
    this.thresh = 0.75;
    // Total number of elements in the hash table initialized
    this.size = 0;
    // Hash table initialization
    this.table = (Node<K, V>[]) new Node<?, ?>[tableSize];
    for (int i = 0; i < tableSize; i++) {
      // Set all indices of table to be null
      table[i] = null;
    }
  }

  /**
   * A constructor that accepts initial capacity and load factor threshold The threshold is the load
   * factor that causes a resize and rehash.
   * 
   * @param initialCapacity     initial size of HashTable
   * @param loadFactorThreshold Signal that rehashing needs to happen
   */
  public HashTable(int initialCapacity, double loadFactorThreshold) {
    // Check for initial capacity to be positive
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException();
    }
    // Initialize data
    this.tableSize = initialCapacity;
    this.thresh = loadFactorThreshold;
    this.size = 0;
    this.table = (Node<K, V>[]) new Node<?, ?>[tableSize];
    for (int i = 0; i < tableSize; i++) {
      table[i] = null; // setting all indices of table to be null
    }
  }

  /**
   * Private helper method to get the hashIndex of the key.
   * 
   * @param key of the node
   * @return the index where the key is located
   */
  private int getHashIndex(K key) {
    int hashCode = Math.abs(key.hashCode());

    return hashCode % this.tableSize;
  }

  /**
   * Inserts a key and value pair into the HashTable.
   * 
   * @param key   is the key of the node
   * @param value is the value of the node
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // Check if the key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    int index = getHashIndex(key);
    if (index < 0) {
      // Get a negative index if it is less than 0
      index = -index;
    }
    // Check if key is duplicate
    if (contains(key)) {
      throw new DuplicateKeyException();
    }
    int hashIndex = lookup(key, index);

    // Put Key value pair in hash table
    if (hashTable(key, value, hashIndex)) {
      // increment number of elements in table
      this.size++;
    }
    // checking if load factor exceeds threshold
    if (getLoadFactor() > getLoadFactorThreshold()) {
      // Resize a table
      int newTableSize = (this.tableSize * 2) + 1;
      int newHashIndex = 0;

      // new hash table
      Node<K, V> newTable[] = (Node<K, V>[]) new Node<?, ?>[newTableSize];
      Node<K, V>[] tempTable = table;
      this.table = newTable;
      int tempSize = tableSize;
      tableSize = newTableSize;
      // Rehashing the table
      for (int i = 0; i <= tempSize - 1; i++) {
        // Check if key is not null
        if (tempTable[i] != null) {
          Node<K, V> temp = tempTable[i];
          newHashIndex = lookup(temp.key, getHashIndex(temp.key));
          this.table[newHashIndex] = temp;
        }
      }
    }
  }

  /*
   * Private helper method to set the hash table.
   */
  private boolean hashTable(K key, V value, int hash) {
    this.table[hash] = new Node<K, V>(key, value);
    return true;
  }

  /*
   * Private helper method to lookup a key at a given index.
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

  /**
   * Removes a node from the HashTable.
   * 
   * @param key of the node
   * @return true if removed successfully
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    // Check if the key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    // Check if the key is duplicate
    if (!contains(key)) {
      return false;
    }

    // Getting hash index
    int hashIndex = getHashIndex(key);
    int i = hashIndex;

    while (this.table[i] != null && !this.table[i].key.equals(key)) {
      if (i == this.tableSize - 1) {
        i = -1;
      }
      i++;
    }

    if (this.table[i] != null && this.table[i].key.equals(key)) {
      // removing a node/key at a given index
      table[i] = null;
      // decrementing table size
      this.size--;
      return true;
    }
    return false;
  }

  /**
   * Private helper method to check whether the table contains a key or not. If duplicate found,
   * 
   * @param key of the node
   * @return true if key is in the HashTable, false otherwise
   */
  public boolean contains(K key) {
    int hashIndex = getHashIndex(key);

    int i = hashIndex;

    while (table[i] != null && !table[i].key.equals(key)) {
      if (i == tableSize - 1) {
        i = -1;
      }
      i++;
    }

    if (table[i] != null && table[i].key.equals(key)) {
      return true;
    }

    return false;
  }

  /**
   * This method returns a value of the node with the specified key.
   * 
   * @param key of the node
   * @return value of the node
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // Check if the key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    // Check if the key is absent
    if (!contains(key)) {
      throw new KeyNotFoundException();
    }

    // getting value of a key
    V value = getVal(key);
    return value;
  }


  /**
   * Private helper method to get the value of a certain key.
   * 
   * @param key of the node
   * @return value of the node
   */
  private V getVal(K key) {
    int hashIndex = getHashIndex(key); // get hash index

    int i = hashIndex;

    while (this.table[i] != null && !this.table[i].key.equals(key)) {
      if (i == tableSize - 1) {
        i = -1;
      }
      i++;
    }

    if (this.table[i] != null && this.table[i].key.equals(key)) {
      return this.table[i].value; // returning value at the given key
    }
    return null;
  }

  /**
   * Returns the number of keys in the HashTable
   * 
   * @return number of keys
   */
  @Override
  public int numKeys() {
    return this.size;
  }

  /**
   * Returns the loadFactorThreshold.
   * 
   * @return loadFactorThreshold
   */
  @Override
  public double getLoadFactorThreshold() {
    return this.thresh;
  }

  /**
   * Returns the loadFactor.
   * 
   * @return loadFactor
   */
  @Override
  public double getLoadFactor() {
    return (double) this.size / this.tableSize;
  }

  /**
   * Returns the capacity of the HashTable.
   * 
   * @return the capacity of the HashTable
   */
  @Override
  public int getCapacity() {
    return this.tableSize;
  }

  /**
   * Returns a list of the keys in the HashTable
   * 
   * @return ArrayList of keys
   */
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
