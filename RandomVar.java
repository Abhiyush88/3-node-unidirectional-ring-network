/* File: RandomVar.java
 * Author: Abhiyush Mittal (axm159230@utdallas.edu)
 */

import java.io.*;
import java.util.*;
import java.math.*;

/*This class contains functions to generate 
 * Uniform Random Variable and Exponential Random Variable
 */
public class RandomVar {

	double seed = 1234.0;
	
	/*******************************************/
	/* returns a uniform (0,1) random variable */
	/*******************************************/
	public double uniform_rv()
	{
		double k = 16807.0;
	    double m = 2.147483647e9;
	    double uni_rv;
	    
	    seed = ((k*seed) % m);
	    uni_rv = seed/m;
	    return uni_rv;
	}
	
	/*******************************/
	/* given arrival rate lambda   */
	/* returns an exponential r.v. */ 
	/*******************************/
	public double exponential_rv(double lambda)
	{
		double exp_rv;
		exp_rv = ((-1)/lambda) * Math.log(uniform_rv());
		return exp_rv;
	}
}
