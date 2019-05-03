/**
 * Filename: HashTableADT.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */

package application;

/**
 * This is a public interface for HashTable implementation. It extends DataStructureADT.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 *
 * @param <K> Key of the node
 * @param <V> Value of the node
 */
public interface HashTableADT<K extends Comparable<K>, V> extends DataStructureADT<K, V> {

  /**
   * This method returns the load factor threshold that was passed into the constructor.
   * 
   * @return load factor threshold
   */
  public double getLoadFactorThreshold();

  /**
   * This method returns the current load factor for this hash table.
   * 
   * @return current load factor
   */
  public double getLoadFactor();

  /**
   * This method returns the current capacity of the hash table array.
   * 
   * @return table size of the hash table array
   */
  public int getCapacity();
}
