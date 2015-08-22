//NWEN 303 Assignment 1
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian

package ass1;

import java.util.Random;

import org.junit.Test;

/*
 * Test double method for JUnit
 */

public class TestDouble {
	Double[][] dataset = {{1.6,3.6,5.4,2.2,9.2,15.3}, {5.1,8.1,4.3,58.2,145.2,45.5},
			{9.4,58.2,45.1,79.3,25.2,54.2}, {97.2,14.2,64.5,69.5,42.2,67.7},
			{62.2,48.5,73.1,54.5,47.2,64.6}, {},
			manyOrdered(10000),
		    manyReverse(10000),
		    manyRandom(10000)
	};

	private Double[] manyRandom(int size) {
		Random r = new Random(0);
		Double[] result = new Double[size];
	    for(int i = 0; i < size; i++){result[i]=r.nextDouble();}
	    return result;
	}

	private Double[] manyReverse(int size) {
		Double[] result = new Double[size];
	    for(int i = 0; i < size; i++){result[i]=(size-i)+0.42d;}
	    return result;
	}

	private Double[] manyOrdered(int size) {
		Double[] result = new Double[size];
	    for(int i = 0; i < size; i++){result[i]=i+0.42d;}
	    return result;
	}

	@Test
	public void testISequentialSorter() {
		Sorter s = new ISequentialSorter();
	    for(Double[]l:dataset){TestHelper.testData(l,s);}
	}

	@Test
	public void testMSequentialSorter() {
		Sorter s = new MSequentialSorter();
	    for(Double[]l:dataset){TestHelper.testData(l,s);}
	}

	@Test
	public void testMParallelSorter1() {
		Sorter s = new MParallelSorter1();
	    for(Double[]l:dataset){TestHelper.testData(l,s);}
	}

	@Test
	public void testMParallelSorter2() {
		Sorter s = new MParallelSorter2();
	    for(Double[]l:dataset){TestHelper.testData(l,s);}
	}
}
