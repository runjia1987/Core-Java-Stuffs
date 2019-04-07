package org.jackJew.algorithm;

/**
 * algorithm for Math.sqrt implementation
 * 
 * @author Jack
 *
 */
public class MathSqrt {

	/**
	 * sqrt in newton way.
	 */
	double newtonSqrt(double d, double precision) {
		if(d == 0) {
			return 0;
		}
		long startTime = System.currentTimeMillis();
		double s = d / 2;
		while(true) {
			s = (s + d / s) / 2;
			if (Math.abs(s * s - d) <= precision) {
				break;
			}
		}
		System.out.println("newtonSqrt time cost: " + (System.currentTimeMillis() - startTime) + " ms.");
		return s;
	}
	
	/**
	 * binary sqrt.
	 */
	double binarySqrt(double d, int scale) {
		long startTime = System.currentTimeMillis();
		// use binary search all the way
		int i = 0, maxBinarySearchCount = 64; // IEEE754 spec, extended double-precision floating point
		double a = 0d, b = d, mid = getClosestMidValue(d);
		boolean firstLoop = true;
		String sa, sb;
		while (true) {
			if (!firstLoop) {
				mid = (a + b) / 2;
			} else {
				firstLoop = false;
			}
			// optimize
			sa = String.valueOf(a);
			sb = String.valueOf(b);
			if (sa.equals(sb)) {
				break;
			}

			double pow = mid * mid;
			if (pow == d) {
				break;
			} else if (pow > d) {
				b = mid;
			} else {
				a = mid;
			}
			i++;
			if (i == maxBinarySearchCount)
				break;
		}
		System.out.println("binarySqrt time cost: " + (System.currentTimeMillis() - startTime) + " ms.");
		return mid;
	}
	
	/**
	 * cube in newton way.
	 */
	public double cube(double d, double precision) {
		if(d == 0) {
			return 0;
		}
		long startTime = System.currentTimeMillis();
		double s = d / 3;
		while(true) {
			double square = s * s;
			s = (2 * s + d / square) / 3;
			double newCube = s * s * s;
			if(Math.abs(newCube - d) <= precision) {
				break;
			}
		}
		System.out.println("cube time cost: " + (System.currentTimeMillis() - startTime) + " ms.");
		return s;
	}

	/**
	 * optimize
	 */
	private double getClosestMidValue(double d) {
		if (d < 10000)
			return d / 2;
		else if (d < 1000000) {
			double d1 = d / 10000 + 1;
			double d2 = binarySqrt(d1, 1);
			return (d2 + 1) * 100d;
		} else {
			double d1 = d / 1000000 + 1;
			double d2 = binarySqrt(d1, 1);
			return (d2 + 1) * 1000d;
		}
	}

	public static void main(String[] args) {
		MathSqrt ms = new MathSqrt();

		System.out.println(ms.binarySqrt(91000009d, 11) +"\n");

		System.out.println(ms.newtonSqrt(91000009d, 0.01d)  +"\n");
		
		System.out.println(ms.cube(27000, 0.01d));
	}
}
