//NWEN 303 Assignment 1
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian

package ass1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/*
 * Benefits:
 *
 * The benefit of using merge sort with Fork/Join framework in Java (like MParallelSorter2) is
 * get faster speed than the sequential merge sort as it can be utilize every processor executing
 * thread concurrently on multiprocessor machines. Comparing with Future, the Fork/Join is more
 * faster than Future, because it not like Future create threads during the initialization.
 */

/*
 * Implementing merge sort in Future helps me understand Callable and the using of
 * ExecutorSerivce, and in Fork/Join helps me learned RecursiveSort and the using of Fork/Join.
 */

/*
 * Use merge sort and the Forkjoin framework to sort data
 */

public class MParallelSorter2 implements Sorter {

	private static final ForkJoinPool mainPool = new ForkJoinPool(10);

	@Override
    public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
		//final int blockSize = (int) Math.sqrt(list.size());

		final ForkJoinSorter forkjoinsorter = new ForkJoinSorter(list);
        mainPool.invoke(forkjoinsorter);
        return forkjoinsorter.returnToList();
    }

	//public <T extends Comparable<? super T>> void sort2(List<T> list1, List<T> list2) {
	//	final int range = list2.size() - list1.size();
	//	if (range > 0) {
	//		int mid = list1.size() + (range / 2);
	//		sort2();
	//		merge(list1, list2);
	//	}
	//}

	private class ForkJoinSorter<T extends Comparable<? super T>> extends RecursiveAction {
		//private final int start;
        //private final int end;

		//public ForkJoinSorter (int start, int end, int blockSize) {
		//	this.start = start;
		//	this.end = end;
		//	this.blockSize = blockSize;
		//}

		private List<T> forkjoinList;

		public List<T> returnToList() {
            return forkjoinList;
		}

		public ForkJoinSorter(List<T> list) {
            List<T> temp = new ArrayList<T>();
            for (int x = 0; x < list.size(); x++) {
            	temp.add(list.get(x));
            }
            this.forkjoinList = temp;
		}

		@Override
		protected void compute() {
            // TODO Auto-generated method stub

			//final int range = end - start;

			//if (range > blockSize) {
			//}

			/*
			 * To solve a problem using fork join:
			 * if the problem is small enough {
			 * 		solve problem directly (in this case, use sequential algorithm)
			 * }
			 * else{
			 * 		divide - the problem in sub-parts
			 * 		fork - sub-task to solve each sub-part
			 * 		join - all sub-tasks spawned in previous loop
			 * 		combine - results from sub-tasks
			 * }
			 */

            if (forkjoinList.size() <= 1) {
            	//return new MSequentialSorter().sort(forkjoinList);
            	return;
            }

            List<T> left = new ArrayList<T>();
            List<T> right = new ArrayList<T>();

            //divided
            int mid = forkjoinList.size() / 2; //midpoint

            for (int i = 0; i < mid; i++) {
            	left.add(forkjoinList.get(i));
            }

            for (int j = mid; j < forkjoinList.size(); j++) {
            	right.add(forkjoinList.get(j));
            }

            //implementing forkjoin framwork
            ForkJoinSorter leftFork = new ForkJoinSorter(left);
            //fork
            leftFork.fork();

            ForkJoinSorter rightFork = new ForkJoinSorter(right);
            rightFork.compute();
            //join
            leftFork.join();

            //combine
            forkjoinList = merge(leftFork.returnToList(), rightFork.returnToList());
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