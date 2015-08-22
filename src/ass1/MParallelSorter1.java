//NWEN 303 Assignment 1
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian

package ass1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Benefits:
 *
 * The benefit of using merge sort with Future interface in Java (like MParallelSorter1)
 * is get faster speed than the sequential merge sort as it can be make use of the modern
 * computer system to execute multiple threads at the same time. However, that depends on
 * the amount data need to sort and the code efficiency using by the computer system.
 */

/*
 * Implementing merge sort in Future helps me understand Callable and the using of
 * ExecutorSerivce, and in Fork/Join helps me learned RecursiveSort and the using of Fork/Join.
 */

/*
 * Use merge sort and futures interface to sort data
 */

public class MParallelSorter1 implements Sorter {

	//get basic idea from "rma350" (http://rma350.scripts.mit.edu/home/?p=64)

	//Parallelism starts here
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @SuppressWarnings("unchecked")
	public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        int threadCounter = 0; //threads start

        //parallelism continues here: the thread know the future, so when the task is completed, the future is informed
        List<Future<List>> result = new ArrayList<Future<List>>();

        //the sorter that represent a functionally, ready to fire and produces a value of type list
        Callable<List> sorter;

        //1 or less 1 element in list to sort, otherwise implement sequential merge sort
        if (list.size() <= 1) {
        	return list;
        }
        else if (list.size() <= 20) {
        	return new MSequentialSorter().sort(list);
        }

        int div = list.size() / 10;
        List<T> temp = new ArrayList<T>();

        sorter = null;
        for (int i = 0; i < 10; i++) {
        	temp = new ArrayList<T>();

        	for (int j = 0; j < div; j++) {
            	temp.add(list.get(i * div + j));
            }

            sorter = new CallableList(temp);
            result.add(threadPool.submit(sorter));
            threadCounter++;
        }

        temp = new ArrayList<T>();
        int end = 10 * div;

        while (end < list.size()) {
        	temp.add(list.get(end));
        	end++;
        }

        //create hashset to sort the list
        Set<List<T>> setList = new HashSet<List<T>>();

        try {
        	for (int i = 0; i < threadCounter; i++) {
                setList.add(result.get(i).get());
        	}
        } catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
        	throw new Error(e);
        } catch (ExecutionException e) {
        	throw new Error(e);
        }

        //threadPool.shutdownNow();
        return merge(setList);
    }

    <T extends Comparable<? super T>> List<T> merge(Set<List<T>> setList) {
    	int counter = 0;

    	//read element in set
    	for (List<T> l : setList) {
            counter += l.size();
    	}

    	List<T> result = new ArrayList<T>(counter);

    	while (result.size() < counter) {
    		List<T> small = null;
            for (List<T> l : setList) {
            	if (!l.isEmpty()) {
                    if (small == null) {
                    	small = l;
                    }
                    else if (l.get(0).compareTo(small.get(0)) <= 0) {
                    	small = l;
                    }
            	}
            }
            result.add(small.get(0));
            small.remove(0);
    	}
    	return result;
    }

    //callable -> pool -> future
    class CallableList implements Callable<List> {
    	List helper;

    	public <T extends Comparable<? super T>> CallableList(List<T> list) {
        	List<T> temp = new ArrayList<T>();
            for (int x = 0; x < list.size(); x++) {
                    temp.add(list.get(x));
            }
            helper = temp;
            //return helper;
        }

        @Override
        public List call() throws Exception {
        	return mergeSort(helper);
        }

        public <T extends Comparable<? super T>> List<T> mergeSort(List<T> list) {
        	if (list.size() <= 1) {
                return list;
        	}

        	//recursive
        	List<T> left = new ArrayList<T>();
        	List<T> right = new ArrayList<T>();

        	int mid = list.size() / 2; //midpoint

        	//left list
        	for (int i = 0; i < mid; i++) {
                left.add(list.get(i));
        	}

        	//right list
        	for (int j = mid; j < list.size(); j++) {
                right.add(list.get(j));
        	}

        	left = mergeSort(left);
        	right = mergeSort(right);

        	return merge(left, right);
        }

        <T extends Comparable<? super T>> List<T> merge(List<T> list1, List<T> list2) {
			List<T> helper = new ArrayList<T>();

			while (list1.size() > 0 || list2.size() > 0) {
				if (list1.size() > 0 && list2.size() > 0) {
					if (list1.get(0).compareTo(list2.get(0)) <= 0) {
						helper.add(list1.get(0));
						list1.remove(0);
					}
					else {
						helper.add(list2.get(0));
						list2.remove(0);
					}
				}
				else if (list1.size() > 0) {
					helper.add(list1.get(0));
					list1.remove(0);
				}
				else if (list2.size() > 0) {
					helper.add(list2.get(0));
					list2.remove(0);
				}
			}
			return helper;
		}
    }
}