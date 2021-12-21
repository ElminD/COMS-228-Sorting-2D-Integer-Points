package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Elmin Didic
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		// TODO
		super(pts);
		super.algorithm = "mergesort";

	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		// TODO
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int n = pts.length;

		if(n <= 1)
		{
			return;
		}
		int m = n / 2;
		Point[] ptsLeft = new Point[m];
		Point[] ptsRight = new Point[n - m];
		int i = 0;

		for(int j = 0; j < ptsLeft.length; j++)
		{
			ptsLeft[j] = pts[i];
			i++;
		}

		for(int j = 0; j < ptsRight.length; j++)
		{
			ptsRight[j] = pts[i];
			i++;
		}

		mergeSortRec(ptsLeft);
		mergeSortRec(ptsRight);

		merge(pts, ptsLeft, ptsRight);
	}

	
	// Other private methods if needed ...

	/**
	 *
	 * @param pts
	 * @param ptsLeft
	 * @param ptsRight
	 *
	 * merges arrays together from sudo code pdf
	 */

	private void merge(Point[] pts, Point[] ptsLeft, Point[] ptsRight)
	{
		int i = 0, j = 0, k = 0, L = ptsLeft.length, R = ptsRight.length;

		while(i < L && j < R)
		{
			if(ptsLeft[i].compareTo(ptsRight[j]) < 0)
			{
				pts[k] = ptsLeft[i];
				i++;
				k++;
			}
			else
			{
				pts[k] = ptsRight[j];
				j++;
				k++;
			}
		}

		while(j < R)
		{
			pts[k] = ptsRight[j];
			j++;
			k++;
		}

		while(i < L)
		{
			pts[k] = ptsLeft[i];
			i++;
			k++;
		}
	}
}
