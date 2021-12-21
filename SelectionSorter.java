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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ...

	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		// TODO
		super(pts);
		super.algorithm = "selection sort";



	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		// TODO

		//Traverses List
		for(int i = 0; i < points.length; i++)
		{
			//sets the smallest value to point[0]

			int min = i;

			//another for loop to check if there is a value smaller then "smallest"
			for(int j = i + 1; j < points.length; j++)
			{
				//Compares "smallest" to the Point at points[k]
				if(points[j].compareTo(points[min]) < 0)
				{


					min = j;
				}




			}
			//swaps where the old smallest point is with the new one
			swap(i, min);
		}
	}	
}
