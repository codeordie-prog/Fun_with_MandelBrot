

/* This class finds out whether a given complex number is part of the mandelbrotset,
 * does so by checking  the number of iterations it takes for a given number to escape to infinity.
 * if the iterations == max_iterations it means the number hasn't escaped to infinity after various iterations
 * if iterations < max_iterations that means the number has already escaped to infinity prior to even reaching the MAX_ITERATIONS. **/

public class MandelBrotSet{

	private int MAX_ITERATIONS = 1000;
	private double ESCAPE_THRESHOLD = 2.0;
	private int iterations;


	//the algorithm
	//returns iterations

	public int calculateSets(double xo, double yo) {

		double xTemp;

		//set the starting point
		double x = 0;
		double y = 0;

		//initialize iterations at 0.

		iterations = 0;

		while(x*x+y*y<ESCAPE_THRESHOLD*ESCAPE_THRESHOLD && iterations<MAX_ITERATIONS) {

			xTemp = x*x - y*y + xo;
			y = 2*x*y + yo;
			x = xTemp;
			iterations++;
		}

		return iterations;
	}
	public int getMaxIterations() {
		return MAX_ITERATIONS;
	}
}
