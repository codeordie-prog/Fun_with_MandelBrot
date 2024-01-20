
		#THE MANDELBROT SET ALGORITHM
		
The Mandelbrot set stands as a captivating mathematical concept driven by the equation
Z=Z^2+C. Before delving into this equation, let's revisit the fundamentals: an imaginary number, denoted as i, arises from the square root of a negative real number, symbolized as
√(−1) . On the other hand, a complex number, such as 5+i, combines a real part (like the familiar 5) with an imaginary part. Now armed with this foundational knowledge, let's unravel the Mandelbrot equation. Starting with a number Z, squaring it, adding a complex number C, and repeating this process through several iterations, we discern between numbers that spiral into infinity and those that stay grounded. To visualize this, a computational program plots each non-escaping number on a plane. Points that withstand the infinity allure earn the distinguished color of BLACK, while those succumbing to infinity's grasp receive varying bright colors, reflecting their distance from the Mandelbrot set. It's a mesmerizing dance of numbers and hues.
derivation of the equation and the computation algorithm

- 1. ##How do we write a complex number like C or Z?

		C = xo + yoi -whereby x is the real part and yoi is the imaginary part.
		Therefore; Z = x + yi
		
-2. ##Substitute Z in the equation - Z=Z2+C
	Z = (x+yi)2 + C
	Z = (x +yi)(x+yi) + C
	
-3. ##Little need for Algebra
	Z = x2 + xyi + xyi + y^2i^2 + C
	
-4. ##simplify
	Z = x2 + 2xyi + y2i2 + C
	but remember the value of i which is an imaginary number is √(−1)  therefore i2 = -1.
	
-5. ##substitute i
	Z = x2 + 2xyi + y2(-1) + C
	therefore;
	Z = x2 + 2xyi - y2 + C
	
-6. ##Lets rewrite C
remember C is a complex number written as - C = xo + yoi 
therefore; 	Z = x2 + 2xyi - y2 + xo + yoi 

-7. ##add the real parts
	x2 - y2 + xo
	
-8. ##add the imaginary parts
	 2xy + yo
	(x2 - y2 + xo) + (2xy + yo) - this is the complex number
    whereby x the real part is  (x2 - y2 + xo) while y the imaginary part will be (2xy + yo) 
    
-9. ##writing the algorithm
This is the most crucial part, now that we have the complex number, we need a condition that will be used to determine whether a number is in the mandelbrot set or not, for this, we can say, if a number is within the mandelbrot set, then even after 1000 iterations of repeatedly squaring and adding C, it should not escape to infinity, thus we can set an escape threshold of 2. this means after 1000 iterations of squaring and adding C the final number should be within the bounds of the escape threshold in our case 2.0 on the x axis.
let me illustrate using a real number 3. now square 3 you get 9, square 9 you get 81 and so on, you see in such a case, its easy to escape to infinity, but lets consider a different number like -1. square -1 you get 1, square 1 you get 1 even after repeating 1000 times, the product will still be stuck at 1. Now the idea with mandelbrot set is similar but with complex numbers and not real numbers.
10. Magnitude - how far the other points are from the mandelbrotsets
	|Z| = √(x2+y2)  
	thus;
	|Z|2 = x2+y2
according to the condition if |Z| is less than escapethreshold(2) then |Z|2 shold be less than escapethreshold2.
	|Z|2 <escapethreshold*escapethreshold
but we have the value for |Z|2 = x2+y2
therefore; x2+y2<escapethreshold2 and also we want iterations to not exceed maximum iterations so; x2+y2 < escape_threshold2 && iterations < maximum_iterations - the condition of the loop.

-11.## writing the algorithm
	starting points will be: double x = 0.0; #set the starting points
			             double y = 0.0;
	int maximum_iterations = 1000; #set maximum iterations
	double escape_threshold = 2.0 # set escape_threshold
	int iterations = 0 #set iterations to 0

	public int computeSets(double xo, double yo){
		double x = 0.0;
		double y = 0.0;
		iterations = 0;
	       while(x2+y2 < escape_threshold2 && iterations < maximum_iterations) { 
			double xTemp = x2 - y2 + xo; #for the real part we derived above
			double y = 2*x*y + yo; #for the imaginary part we derived above
			double x = xTemp;
			iterations ++; #increase iteration count
		}
	      return iterations;