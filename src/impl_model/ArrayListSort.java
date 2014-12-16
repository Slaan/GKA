package impl_model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class ArrayListSort {

	private ArrayList<NamedWeightedEdge> _array;
	
	public ArrayListSort(ArrayList<NamedWeightedEdge> list) {
		if(list == null) throw new IllegalArgumentException();
		_array = list;
	}

	public ArrayList<NamedWeightedEdge> quicksort() {
		quicksort(0, _array.size()-1);
		return _array;
	}
	
	private void quicksort(int ilinks, int irechts) {
		if(ilinks < irechts) {
			int i = quickswap(ilinks, irechts);
			quicksort(ilinks, i-1);
			quicksort(i+1, irechts);
		}
	}
	
	private int quickswap(int ilinks, int irechts) {
		int i 		= ilinks;
		int j 		= irechts-1;
		Double pivot= _array.get(irechts).getthisWeight(); 
		while(i <= j) {
			while((_array.get(i).getthisWeight() <= pivot) && (i < irechts)) i++;
			while((ilinks <= j) && (_array.get(j).getthisWeight() > pivot)) j--;
			if(i < j) swap(i, j);
		}
		swap(i, irechts);
		return i;
	}
	
	private void swap(int i, int j) {
		NamedWeightedEdge tmpi = _array.get(i);
		NamedWeightedEdge tmpj = _array.get(j);
		_array.set(j, tmpi);
		_array.set(i, tmpj);
	}
}
