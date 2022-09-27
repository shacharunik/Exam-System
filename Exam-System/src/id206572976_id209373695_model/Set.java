package id206572976_id209373695_model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Set<T> implements Cloneable, Serializable {
	private final int ENLARGE_FACTOR = 2;
	private int currentSize;
	private T[] array;

	public Set() {
		currentSize = 0;
		array = (T[]) new Object[ENLARGE_FACTOR];
	}

	public boolean add(T other) {
		if (this.contain(other))
			return false;
		if (currentSize == array.length)
			enlargeArray();
		array[currentSize++] = other;
		return true;
	}

	public void enlargeArray() {
		array = Arrays.copyOf(array, array.length * ENLARGE_FACTOR);
	}

	public Set<T> clone() throws CloneNotSupportedException {
		return (Set<T>) super.clone();
	}

	public int length() {
		return currentSize;
	}

	public T get(int index) {
		return array[index];
	}

	public boolean contain(T other) {
		for (int i = 0; i < currentSize; i++) {
			if (other.equals(array[i]))
				return true;
		}
		return false;
	}

	public void remove(int i) {
		for (int j = i; j < currentSize - 1; j++) {
			array[j] = array[j + 1];
		}
		array[--currentSize] = null;
	}

	public boolean equals(Set<T> other) {
		if (!(other instanceof Set<T>))
			return false;
		return super.equals(other);
	}
}
