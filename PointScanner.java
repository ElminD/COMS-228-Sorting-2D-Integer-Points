package edu.iastate.cs228.hw2;

/**
 * 
 * @author Elmin Didic
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;

	String fileName;
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			points = pts;
			sortingAlgorithm = algo;
		}


	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		// TODO
		sortingAlgorithm = algo;
		fileName = inputFileName;
		int count = 0;

		try {
			File file = new File(inputFileName);
			Scanner fscan = new Scanner(file);


			int t;
			while (fscan.hasNext()) {
				t = fscan.nextInt();
				count++;
			}
			fscan.close();
			if (count % 2 != 0) {
				throw new InputMismatchException();
			}
			points = new Point[count / 2];
			Scanner fscan2 = new Scanner(file);

			int i = 0;
			while(fscan2.hasNext())
			{
				int x = fscan2.nextInt();
				int y = fscan2.nextInt();
				points[i] = new Point(x, y);
				i++;
			}
			fscan2.close();


		}


		catch(FileNotFoundException e)
		{
			throw new FileNotFoundException();
		}




	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 *
	 * @return
	 */
	public void scan()
	{
		// TODO
		AbstractSorter aSorter = null;
		long startTime;
		long endTime;

		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime.



		if(sortingAlgorithm == Algorithm.MergeSort)
		{
			aSorter = new MergeSorter(points);
		}
		if(sortingAlgorithm == Algorithm.InsertionSort)
		{
			aSorter = new InsertionSorter(points);
		}
		if(sortingAlgorithm == Algorithm.SelectionSort)
		{
			aSorter = new SelectionSorter(points);
		}
		if(sortingAlgorithm == Algorithm.QuickSort)
		{
			aSorter = new QuickSorter(points);
		}


		//Compare by X
		aSorter.setComparator(0);
		startTime = System.nanoTime();
		aSorter.sort();
		endTime = System.nanoTime();
		long xtime = endTime - startTime;


		int x = aSorter.getMedian().getX();

		//Compare by Y
		aSorter.setComparator(1);
		startTime = System.nanoTime();
		aSorter.sort();
		endTime = System.nanoTime();
		long ytime = endTime - startTime;

		scanTime = xtime +ytime;


		int y = aSorter.getMedian().getY();



		medianCoordinatePoint = new Point(x,y);









		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String stats = null;


		if(sortingAlgorithm == Algorithm.MergeSort)
		{
			stats = "<merge sort>" + "         " + points.length + "  " + scanTime;
		}
		if(sortingAlgorithm == Algorithm.InsertionSort)
		{
			stats = "<insertion sort>" + "     " + points.length + "  " + scanTime;
		}
		if(sortingAlgorithm == Algorithm.SelectionSort)
		{
			stats = "<selection sort>" + "     " + points.length + "  " + scanTime;
		}
		if(sortingAlgorithm == Algorithm.QuickSort)
		{
			stats = "<quick sort>" + "         " + points.length + "  " + scanTime;
		}




		return stats;
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
			//TODO
			//TEST

		return medianCoordinatePoint.toString();
//"MCP: " +

	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		// TODO
		try {
			FileWriter file = new FileWriter(fileName);

			file.write(toString());

			file.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}


	}



		
}
