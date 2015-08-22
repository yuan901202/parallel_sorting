package ass1;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Test;

/*
 * Merge sort with ForkJoin framework perform better on Float datatype.
 * Merge sort with ForkJoin framework perform better on Double datatype.
 * Merge sort with ForkJoin framework perform better on PointT datatype.
 * Merge sort with ForkJoin framework perform better on BigInteger datatype.
 *
 * Overall, use merge sort and ForkJoin framework to sort data can get faster
 * speed that Future interface and sequential merge sort method.
 */

/*
 * This is test can be test all sorter class, and print out the result on the console.
 * Just simply run as JUnit Test. :)
 */

class PointT implements Comparable<PointT>{
	  public PointT(long x, long y) {
		  super();
		  this.x = x;
		  this.y = y;
	  }
	  long x;
	  long y;
	  @Override
	  public int compareTo(PointT other) {
		  return new Long(this.x*this.x+this.y*this.y).compareTo(other.x*other.x+other.y*other.y);
	  }
}

public class TestAll {
		BigInteger[][] datasetBI={
		    {new BigInteger("999101101"),new BigInteger("999101201"),new BigInteger("999101301"),new BigInteger("999101401"),new BigInteger("999101501")},
		    {new BigInteger("999191101"),new BigInteger("999181201"),new BigInteger("999171301"),new BigInteger("999161401"),new BigInteger("999151501")},
		    {new BigInteger("-999101101"),new BigInteger("-999101201"),new BigInteger("999101301"),new BigInteger("-999101401"),new BigInteger("999101501")},
		    {new BigInteger("0"),new BigInteger("-999101201"),new BigInteger("1"),new BigInteger("-999101401"),new BigInteger("999101501")},
		    {},
		    manyOrderedBI(10000),
		    manyReverseBI(10000),
		    manyRandomBI(10000)
		};

		Double[][] datasetD = {{1.6,3.6,5.4,2.2,9.2,15.3}, {5.1,8.1,4.3,58.2,145.2,45.5},
				{9.4,58.2,45.1,79.3,25.2,54.2}, {97.2,14.2,64.5,69.5,42.2,67.7},
				{62.2,48.5,73.1,54.5,47.2,64.6}, {},
				manyOrderedD(10000),
			    manyReverseD(10000),
			    manyRandomD(10000)
		};

		Float[][] datasetF={
			    {1f,2f,3f,4f,5f,6f},
			    {7f,6f,5f,4f,3f,2f},
			    {-7f,6f,-5f,-4f,3f,-2f},
			    {7f/0f,-6f/0f,5f,4f,3f,2f},
			    {7f/0f,0f/0f,0f/0f,0f/0f,0f/0f,-5f/0f,4f,3f,2f},
			    {},
			    manyOrderedF(10000),
			    manyReverseF(10000),
			    manyRandomF(10000)
		};

		PointT[][] datasetP={
			    {new PointT(10,10),new PointT(20,30),new PointT(30,30),new PointT(40,40),new PointT(50,50),new PointT(60,60)},
			    {new PointT(110,10),new PointT(120,3),new PointT(130,3),new PointT(140,140),new PointT(-50,150),new PointT(3260,-260)},
			    {new PointT(0,0),new PointT(0,3),new PointT(-130,3),new PointT(-140,-140)},
			    {},
			    manyOrderedP(10000),
			    manyReverseP(10000),
			    manyRandomP(10000)
		};

		private BigInteger[] manyRandomBI(int size) {
		    Random r=new Random(0);
		    BigInteger[] result=new BigInteger[size];
		    for(int i=0;i<size;i++){result[i]=new BigInteger(250,r);}
		    return result;
		}
		private BigInteger[] manyReverseBI(int size) {
		    BigInteger[] result=new BigInteger[size];
		    for(int i=0;i<size;i++){result[i]=new BigInteger("99999"+(size-i));}
		    return result;
		}
		private BigInteger[] manyOrderedBI(int size) {
		    BigInteger[] result=new BigInteger[size];
		    for(int i=0;i<size;i++){result[i]=new BigInteger("99999"+i);}
		    return result;
		}

		private Double[] manyRandomD(int size) {
			Random r = new Random(0);
			Double[] result = new Double[size];
		    for(int i = 0; i < size; i++){result[i]=r.nextDouble();}
		    return result;
		}
		private Double[] manyReverseD(int size) {
			Double[] result = new Double[size];
		    for(int i = 0; i < size; i++){result[i]=(size-i)+0.42d;}
		    return result;
		}
		private Double[] manyOrderedD(int size) {
			Double[] result = new Double[size];
		    for(int i = 0; i < size; i++){result[i]=i+0.42d;}
		    return result;
		}

		private Float[] manyRandomF(int size) {
		    Random r=new Random(0);
		    Float[] result=new Float[size];
		    for(int i=0;i<size;i++){result[i]=r.nextFloat();}
		    return result;
		}
		private Float[] manyReverseF(int size) {
		    Float[] result=new Float[size];
		    for(int i=0;i<size;i++){result[i]=(size-i)+0.42f;}
		    return result;
		}
		private Float[] manyOrderedF(int size) {
		    Float[] result=new Float[size];
		    for(int i=0;i<size;i++){result[i]=i+0.42f;}
		    return result;
	    }

		private PointT[] manyRandomP(int size) {
		    Random r=new Random(0);
		    PointT[] result=new PointT[size];
		    for(int i=0;i<size;i++){result[i]=new PointT(r.nextLong(),r.nextLong());}
		    return result;
		}
		private PointT[] manyReverseP(int size) {
		    PointT[] result=new PointT[size];
		    for(int i=0;i<size;i++){result[i]=new PointT(size-i,size*3-i);}
		    return result;
		}
		private PointT[] manyOrderedP(int size) {
		    PointT[] result=new PointT[size];
		    for(int i=0;i<size;i++){result[i]=new PointT(i*3,i*2);}
		    return result;
		}

		@Test
		public void testBigInteger(){

			System.out.println("BigInteger Test: \n");

			long startTime = System.currentTimeMillis();
			MSequentialSorter sorter = new MSequentialSorter();
			for(BigInteger[]l:datasetBI){TestHelper.testData(l,sorter);}
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("SequentialMergesort test bigInteger: " + elapsedTime);

			long startTime1 = System.currentTimeMillis();
			MParallelSorter1 sorter1 = new MParallelSorter1();
			for(BigInteger[]l:datasetBI){TestHelper.testData(l,sorter1);}
			long stopTime1 = System.currentTimeMillis();
			long elapsedTime1 = stopTime1 - startTime1;
			System.out.println("FutureMergesort test bigInteger: " + elapsedTime1);

			long startTime2 = System.currentTimeMillis();
			MParallelSorter2 sorter2 = new MParallelSorter2();
			for(BigInteger[]l:datasetBI){TestHelper.testData(l,sorter2);}
			long stopTime2 = System.currentTimeMillis();
			long elapsedTime2 = stopTime2 - startTime2;
			System.out.println("ForkJoinMergesort test bigInteger: " + elapsedTime2);

			System.out.println("");
		}

		@Test
		public void testDouble() {

			System.out.println("Double Test: \n");

			long startTime = System.currentTimeMillis();
			MSequentialSorter sorter = new MSequentialSorter();
			for(Double[]l:datasetD){TestHelper.testData(l,sorter);}
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("SequentialMergesort test double: " + elapsedTime);

			long startTime1 = System.currentTimeMillis();
			MParallelSorter1 sorter1 = new MParallelSorter1();
			for(Double[]l:datasetD){TestHelper.testData(l,sorter1);}
			long stopTime1 = System.currentTimeMillis();
			long elapsedTime1 = stopTime1 - startTime1;
			System.out.println("FutureMergesort test double: " + elapsedTime1);

			long startTime2 = System.currentTimeMillis();
			MParallelSorter2 sorter2 = new MParallelSorter2();
			for(Double[]l:datasetD){TestHelper.testData(l,sorter2);}
			long stopTime2 = System.currentTimeMillis();
			long elapsedTime2 = stopTime2 - startTime2;
			System.out.println("ForkJoinMergesort test double: " + elapsedTime2);

			System.out.println("");
		}

		@Test
		public void testFloat() {

			System.out.println("Float Test: \n");

			long startTime = System.currentTimeMillis();
			MSequentialSorter sorter = new MSequentialSorter();
			for(Float[]l:datasetF){TestHelper.testData(l,sorter);}
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("SequentialMergesort test float: " + elapsedTime);

			long startTime1 = System.currentTimeMillis();
			MParallelSorter1 sorter1 = new MParallelSorter1();
			for(Float[]l:datasetF){TestHelper.testData(l,sorter1);}
			long stopTime1 = System.currentTimeMillis();
			long elapsedTime1 = stopTime1 - startTime1;
			System.out.println("FutureMergesort test float: " + elapsedTime1);

			long startTime2 = System.currentTimeMillis();
			MParallelSorter2 sorter2 = new MParallelSorter2();
			for(Float[]l:datasetF){TestHelper.testData(l,sorter2);}
			long stopTime2 = System.currentTimeMillis();
			long elapsedTime2 = stopTime2 - startTime2;
			System.out.println("ForkJoinMergesort test float: " + elapsedTime2);

			System.out.println("");
		}

		@Test
		public void testPoint() {

			System.out.println("Float Point: \n");

			long startTime = System.currentTimeMillis();
			MSequentialSorter sorter = new MSequentialSorter();
			for(PointT[]l:datasetP){TestHelper.testData(l,sorter);}
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("SequentialMergesort test point: " + elapsedTime);

			long startTime1 = System.currentTimeMillis();
			MParallelSorter1 sorter1 = new MParallelSorter1();
			for(PointT[]l:datasetP){TestHelper.testData(l,sorter1);}
			long stopTime1 = System.currentTimeMillis();
			long elapsedTime1 = stopTime1 - startTime1;
			System.out.println("FutureMergesort test point: " + elapsedTime1);

			long startTime2 = System.currentTimeMillis();
			MParallelSorter2 sorter2 = new MParallelSorter2();
			for(PointT[]l:datasetP){TestHelper.testData(l,sorter2);}
			long stopTime2 = System.currentTimeMillis();
			long elapsedTime2 = stopTime2 - startTime2;
			System.out.println("ForkJoinMergesort test point: " + elapsedTime2);

			System.out.println("");
		}
}