public interface HashTableADT <K extends Comparable<K>, V> extends DataStructureADT<K,V>{

  /**
   * Returns the load factor threshold that was passed into the constructor when creating the
   * instance of the HashTable. When the current load factor is greater than or equal to the
   * specified load factor threshold, the table is resized and elements are rehashed.
   * 
   * @return load factor threshold passed into the constructor
   */
  public double getLoadFactorThreshold();

  /**
   * Return the current load factor for this hash table. Load Factor = number of items / current
   * table size.
   * 
   * @return current load factor for hash table
   */
  public double getLoadFactor();

  /**
   * Return the Current Capacity of the hash table array.
   * 
   * @return table size of the hash table array.
   */
  public int getCapacity();
}
