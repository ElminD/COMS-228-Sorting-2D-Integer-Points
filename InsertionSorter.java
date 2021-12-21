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
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		// TODO
		super(pts);
		super.algorithm = "insertion sort";
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		// TODO
		//Goes through the array of points
		for(int i = 1; i < points.length; i++)
		{
			int j = i;
			//J > 0 so we can go to the left and checks if we need to swap j with j -1
			while((j > 0) && (points[j - 1].compareTo(points[j]) < 0))
			{
				swap(j,j-1);
				j--;
			}
		}

	}		
}
