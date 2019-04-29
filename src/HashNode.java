public class HashNode<K, V> {

  // Unique key of the node
  private K key;
  // Value of the node
  private V value;

  /**
   * Construct a HashNode object
   */
  public HashNode(K k, V v) {
    key = k;
    value = v;
  }

  /**
   * Getter method for key of the node.
   * 
   * @return key of the node
   */
  public K getKey() {
    return key;
  }
  
  /**
   * Getter method for value of the node.
   * @return
   */
  public V getValue() {
    return value;
  }
}
