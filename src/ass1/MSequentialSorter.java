//NWEN 303 Assignment 1
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian

package ass1;

import java.util.ArrayList;
import java.util.List;

/*
 * Use merge sort and a sequential algorithm to sort data
 */

public class MSequentialSorter implements Sorter {

	@Override
	public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
		//define the list size is 1 of less, otherwise just sort two half sublists
		if (list.size() <= 1) {
			return list;
		}
		else {
			int mid = list.size() / 2; //the half of the size of list
			return mergeSort(sort(list.subList(0, mid)), sort(list.subList(mid, list.size()))); //merge the two sorted sublists
		}
	}

	<T extends Comparable<? super T>> List<T> mergeSort(List<T> list1, List<T> list2) {

		if (list1.isEmpty()) {
			return list2;
		}
		else if (list2.isEmpty()) {
			return list1;
		}
		else {
			List<T> temp = new ArrayList<T>(Math.max(list1.size(), list2.size())); //create a temp list

			//compare two element in the list
			if (list1.get(0).compareTo(list2.get(0)) <= 0) {
				temp.add(list1.get(0));
				temp.addAll(mergeSort(list1.subList(1, list1.size()), list2));
			}
			else {
				temp.add(list2.get(0));
				temp.addAll(mergeSort(list1, list2.subList(1, list2.size())));
			}
			return temp;
		}
	}
}