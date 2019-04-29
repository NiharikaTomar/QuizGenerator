package application;
/////////////////////////////////////////////////////////////////////////////////////////////
// Course:         		CS400, Spring 2019
//
// Author:          		Ved Kale
// Email:					vpkale@wisc.edu
// Lecturer's Name: 	Deb Deppler
// Due Date: 			21 February 2019
/////////////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
//
// TODO: explain your hashing algorithm here 
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	
	private class HashNode<K, V>
	{ 
	    private K key; 
	    private V value; 
	    private boolean removed;
	  
	    // Constructor 
	    public HashNode(K key, V value) 
	    { 
	        this.key = key; 
	        this.value = value; 
	        removed = false;
	    }

		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public String toString()
		{
			return key+ " " + value;
		}
		
		public boolean isRemoved()
		{
			return removed;
		}
		
		public void setRemoved(boolean rm)
		{
			removed = rm;
		}
	}
	
	private double loadFactorThreshold;
	private int numKeys;
	private int capacity;
	
	private Object[] table;
		
	/**
	 * default constructor
	 */
	public HashTable() {
		capacity = 11;
		table = new Object[capacity];
		loadFactorThreshold = 0.75;
		numKeys = 0;
	}
	
	/**
	 * @param initialCapacity, capacity of hash table
	 * @param loadFactorThreshold, factor that causes a resize and rehash
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		capacity = initialCapacity;
		table = new Object[capacity];
		this.loadFactorThreshold = loadFactorThreshold;
		numKeys = 0;
	}

	/** 
	 * @param key to insert
	 * @param value to insert
	 * @throws IllegalNullKeyException if key is null
	 * @throws DuplicateKeyException if key is already in hash table
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		if (key == null)
			throw new IllegalNullKeyException();
		if (contains(key))
			throw new DuplicateKeyException();
		
		if (getLoadFactor() >= getLoadFactorThreshold()) // rehash 
		{
			capacity = (capacity * 2) + 1;
			Object[] temp = new Object[capacity];
			
			for (int i=0;i<numKeys;i++) // resize
			{
				if (table[i] == null)
					break;
				HashNode<K, V> node = (HashNode<K, V>) table[i];
				temp[Math.abs(node.getKey().hashCode())%getCapacity()] = table[i]; // copy nodes
			}
			table = temp;
		}

		for (int i = Math.abs(key.hashCode()%getCapacity());i<getCapacity(); i++) // linear probing
		{
			if (table[i] == null || ((HashNode<K, V>) table[i]).isRemoved())
			{
				table[i] = new HashNode<K, V>(key, value);
				numKeys++;
				break;
			}
		}
	}
	
	/** 
	 * @param key to insert
	 * @throws IllegalNullKeyException if key is null
	 * @return true if remove worked, false otherwise
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		if (key == null)
			throw new IllegalNullKeyException();
		if (!contains(key))
			return false;
		
		for (int i=Math.abs(key.hashCode()%getCapacity());i<getCapacity();i++)
		{
			if (table[i] == null)
				return false;
			if (key.compareTo(((HashNode<K, V>) table[i]).getKey()) == 0)
			{
				((HashNode<K, V>) table[i]).setRemoved(true);
				numKeys--;
				return true;
			}
		}
		return false;
	}

	/** 
	 * @param key to insert
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException if key not in hash table
	 * @return value of key in hash table
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null)
			throw new IllegalNullKeyException();
		for (int i=Math.abs(key.hashCode()%getCapacity());i<getCapacity();i++)
		{
			if (table[i] == null)
				throw new KeyNotFoundException();
			HashNode<K, V> node = (HashNode<K, V>) table[i];
			if (node.getKey().compareTo(key) == 0)
			{
				return node.getValue();
			}
		}
		throw new KeyNotFoundException();
	}
	
	/** 
	 * @param key to insert
	 * @return true if key in table, false otherwise
	 */
	public boolean contains(K key) 
	{
		for (int i=Math.abs(key.hashCode()%getCapacity());i<getCapacity();i++)
		{
			if (table[i] == null)
				return false;
			HashNode<K, V> node = (HashNode<K, V>) table[i];
			if (node.getKey().compareTo(key) == 0)
			{
				return true;
			}
		}
		return false;
	}

	/** 
	 * @return number of keys in hashTable
	 */
	@Override
	public int numKeys() {
		return numKeys;
	}

	/** 
	 * @return load Factor Threshold
	 */
	@Override
	public double getLoadFactorThreshold() {
		return loadFactorThreshold;
	}

	/** 
	 * @return load Factor
	 */
	@Override
	public double getLoadFactor() {
		return ((double)numKeys/capacity);
	}

	/** 
	 * @return capacity of hash table
	 */
	@Override
	public int getCapacity() {
		return capacity;
	}

	/** 
	 * @return Collision Resolution
	 */
	@Override
	public int getCollisionResolution() {
		return 1;
	}
	
	public ArrayList<String> keySet()
	{
		ArrayList<String> keys = new ArrayList<String>();
		for (int i=0;i<table.length;i++)
		{
			keys.add((String) table[i]);
		}
		return keys;
	}
}
