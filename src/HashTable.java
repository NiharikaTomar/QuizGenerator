public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

 // Load Factor of the hash table
 private double loadFactor;
 // Capacity of the chainedList
 private int capacity;
 // Number of items in the hash table
 private int numberOfItems;

 /**
  * No-argument constructor, which sets load factor to 0.75 and table size to 11.
  */
 public HashTable() {
   // Set up default values for load factor and capacity
   loadFactor = 0.75;
   capacity = 11;
 }

 /**
  * Constructor with two parameters (initialCapacity and loadFactorThreshold).
  * 
  * @param initialCapacity     capacity of the chainedList
  * @param loadFactorThreshold load factor that causes a resize and rehash
  */
 public HashTable(int initialCapacity, double loadFactorThreshold) {
   loadFactor = loadFactorThreshold;
   capacity = initialCapacity;
 }
  
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {

  }

  /**
   * This method adds the key/value pair to the data structure, increases the numberOfItems. If key
   * is null, throws IllegalNullKeyException. If key is already in data structure, throws
   * DuplicateKeyException.
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    return false;
  }


  /**
   * Returns the number of key,value pairs in the data structure.
   * 
   * @return number of key, value pairs
   */
  @Override
  public int numKeys() {
    return numberOfItems;
  }

  /**
   * Returns the load factor threshold that was passed into the constructor when creating the
   * instance of the HashTable. When the current load factor is greater than or equal to the
   * specified load factor threshold, the table is resized and elements are rehashed.
   * 
   * @return load factor threshold passed into the constructor
   */
  @Override
  public double getLoadFactorThreshold() {
    return loadFactor;
  }


  /**
   * Return the current load factor for this hash table. Load Factor = number of items / current
   * table size.
   * 
   * @return current load factor for hash table
   */
  @Override
  public double getLoadFactor() {
    return (double) numberOfItems / (double) capacity;
  }

  /**
   * Return the Current Capacity of the hash table array.
   * 
   * @return table size of the hash table array.
   */
  @Override
  public int getCapacity() {
    return capacity;
  }

}
