
public interface DataStructureADT<K extends Comparable<K>, V> {
  void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException;


  /**
   * If key is found, remove the key,value pair from the data structure and decrease number of keys.
   * If key is null, throw IllegalNullKeyException. If key is not found, return false
   * 
   * @param key of the node
   * @throws IllegalNullKeyException
   */
  boolean remove(K key) throws IllegalNullKeyException;

  /**
   * Returns the number of key,value pairs in the data structure.
   * 
   * @return number of key, value pairs
   */
  int numKeys();
}
