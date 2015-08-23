# parallel_sorting
[NWEN303] Parallel sorting with Futures and Fork Join

## Goal:
In this project is using Futures and Fork Join to implement Parallel Sorting.

## Note:
JUint test files provided for this project.

## Task:
### part a:
Implement the MSequentialSorter class so that it uses merge sort and a sequential algorithm to sort data.

### part b:
Implement the MParallelSorter1 class so that it uses merge sort and futures to sort data.

You are allowed to delegate to a MSequentialSorter or a ISequentialSorter for the cases shorter than a threshold of 20 elements.

### part c:
Implement the MParallelSorter1 class so that it uses merge sort and the forkJoin framework to sort the data.

You are allowed to delegate to a MSequentialSorter or a ISequentialSorter for the cases shorter than a threshold of 20 elements.
