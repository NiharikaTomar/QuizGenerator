/**
 * Filename: DataStructureADT.java
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 */

package application;

/**
 * This is a public interface of the data structure.
 * 
 * @author Ved Kale, Miriam Lebowitz, Elizaveta Stepanova, and Niharika Tomar
 *
 * @param <K> Key of the node
 * @param <V> Value of the node
 */
public interface DataStructureADT<K extends Comparable<K>, V> {

  /**
   * Add the key, value pair to the data structure and increase the number of keys.
   * 
   * @param key   of the node
   * @param value of the node
   * @throws IllegalNullKeyException if key is null
   * @throws DuplicateKeyException   if already exists in data structure
   */
  void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException;

  /**
   * Removes the key, value pair from the data structure.
   * 
   * @param key of the node
   * @return true when remove was successful, false otherwise
   * @throws IllegalNullKeyException if key is null
   */
  boolean remove(K key) throws IllegalNullKeyException;

  /**
   * This method returns the value associated with the specified key.
   * 
   * @param key of the node
   * @return the value associated with the specified key
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if key is not found
   */
  V get(K key) throws IllegalNullKeyException, KeyNotFoundException;

  /**
   * This method the number of key, value pairs in the data stracture.
   * 
   * @return number of key, value pairs
   */
  int numKeys();
}
