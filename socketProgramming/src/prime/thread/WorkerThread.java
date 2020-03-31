package prime.thread;

import java.io.IOException;

import prime.calculation.IsPrimeI;
import prime.result.PrimeDetectrResultsI;
import prime.util.FileProcessor;
import prime.util.MyLogger;
import prime.util.MyLogger.DebugLevel;

/**
 * WorkerThread class is for implementing the worker thread which reads the
 * line, checks if the number is prime and appends the number to the results
 * class.
 * 
 * @author Akshay Anvekar and Kenneth Fernandes
 */
public class WorkerThread implements Runnable {
	private FileProcessor fp;
	private IsPrimeI isPrime;
	private PrimeDetectrResultsI results;

	public WorkerThread(FileProcessor fpIn, IsPrimeI isPrimeObj, PrimeDetectrResultsI results2) {
		MyLogger.writeMessage("WorkerThread()", DebugLevel.CONSTRUCTOR);
		this.fp = fpIn;
		this.isPrime = isPrimeObj;
		this.results = results2;
	}

	/**
	 * This is a function which Invoke a method in the FileProcessor to retrieve one
	 * line, as string from the input file and Check if it is a prime number. Also
	 * stores the prime number in a data structure in the Results instance
	 */
	@Override
	public void run() {
		MyLogger.writeMessage("run()", DebugLevel.THREAD_RUN);
		try {
			String line = null;
			/*
			 * This reads the file line by line until the end of the file is reached
			 */
			while ((line = fp.readLine()) != null) {
				// Check if the number is prime
				boolean prime = isPrime.checkNum(Integer.parseInt(line));

				if (prime)
					results.addPrimeNum(Integer.parseInt(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public String toString() {
		return "This is a WorkerThread (fp=" + fp + ", results=" + results + ", isPrime=" + isPrime + ")";
	}
}
