package edu.iastate.cs228.hw2;

/**
 *  
 * @author Elmin Didic
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// TODO 
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		PointScanner[] scanners = new PointScanner[4];
		Random rand = new Random();
		Scanner scnr = new Scanner(System.in);


		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");

		int choice = 0, trail = 1;

		while(choice != 3)
		{
			System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");

			choice = scnr.nextInt();

			if(choice == 1)
			{
				System.out.println("Trial " + trail + ": " + choice);
				System.out.println("Enter number of random points: ");
				int randNumPoints = scnr.nextInt();

				randPoint(randNumPoints, rand, scanners);

				trail++;
			}
			if(choice == 2)
			{

				System.out.println("Trial " + trail + ": " + choice);
				System.out.println("Points From a File");
				System.out.println("File Name: ");
				String fileString = scnr.next();

				filePoint(fileString, scanners);

				scanners[3].writeMCPToFile();

				trail++;

			}

		}


		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description.



		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{

		// TODO
		// TEST

		if(numPts < 1) {
			throw new IllegalArgumentException();
		}

		Point[] randPoints = new Point[numPts];

		for(int i = 0; i < numPts; i++)
		{
			Point p = new Point(rand.nextInt(101)-50, rand.nextInt(101)-50);
			randPoints[i] = p;

		}
		return randPoints;

	}

	/**
	 *
	 * this method fills the 4 scanners with the PointScanner constructor for random points
	 * then runs scan and prints the stats.
	 * @param numPoints
	 * @param rand
	 * @param scanners
	 */
	public static void randPoint(int numPoints, Random rand, PointScanner[] scanners)
	{
		scanners[0] = new PointScanner(generateRandomPoints(numPoints,rand), Algorithm.QuickSort);
		scanners[1] = new PointScanner(generateRandomPoints(numPoints,rand), Algorithm.MergeSort);
		scanners[2] = new PointScanner(generateRandomPoints(numPoints,rand), Algorithm.InsertionSort);
		scanners[3] = new PointScanner(generateRandomPoints(numPoints,rand), Algorithm.SelectionSort);



		System.out.println("algorithm   size  time (ns)");
		System.out.println("----------------------------------");
		for(int i = 0; i < 4; i++)
		{
			scanners[i].scan();
			System.out.println(scanners[i].stats());
		}
	}

	/**
	 *
	 * this method fills the 4 scanner objects with the PointScanner constructor with the file version
	 * constructor and then runs scan and prints the stats
	 * @param fileName
	 * @param scanners
	 * @throws FileNotFoundException
	 */
	public static void filePoint(String fileName,PointScanner[] scanners) throws FileNotFoundException {


		scanners[0] = new PointScanner(fileName, Algorithm.QuickSort);
		scanners[1] = new PointScanner(fileName, Algorithm.MergeSort);
		scanners[2] = new PointScanner(fileName, Algorithm.InsertionSort);
		scanners[3] = new PointScanner(fileName, Algorithm.SelectionSort);



		System.out.println("algorithm   size  time (ns)");
		System.out.println("----------------------------------");
		for(int i = 0; i < 4; i++)
		{
			scanners[i].scan();
			System.out.println(scanners[i].stats());
		}
	}
	
}
