package exam_gen.model;

import java.io.Serializable;

public class Set<T> implements Serializable {
	private final int ENLARGE_FACTOR = 2;
	private T[] arr;
	private int currentSize;
	
	public Set() {
		arr = (T[]) new Object[ENLARGE_FACTOR];
		currentSize = 0;
	}
	
	public String toString() {
		int counter = 1;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < currentSize; i++) {
			if (!(arr[i] == null)) {
				sb.append(counter++ + ") " + arr[i] + "\n");
			}
		}
		return sb.toString();
	}
	
	public T get(int index) {
		return arr[index];
	}
	
	private void enlargeArray() {
		T[] temp = (T[])new Object[arr.length*ENLARGE_FACTOR];
		for (int i = 0; i < arr.length; i++) {
		temp[i] = arr[i];
		}
		arr = temp;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public T[] getArr() {
		return arr;
	}

	public  <T> boolean equals(Set<T> other) {
		if (this == other) {
			return true;
		}
		return false;
	}
	
	public String add(T newValue) {
		String res = ("Answer added successfully");
		if (currentSize == arr.length) {
			enlargeArray();
		}
		for (int i = 0; i<arr.length; i++) {
			if (arr[i] != null && arr[i].equals(newValue)) {
			res = ("The value is already exists!");
			return res;
			}
			else if(arr[i] == null){
				arr[i] = newValue;
				currentSize++;
				return res;
			}
		}
		return res;	
	}
	
	
	
	
	

}
