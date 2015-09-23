package org.jackJew.algorithm;

/**
 * algorithm for Math.sqrt implementation
 * 
 * @author Jack
 *
 */
public class MathSqrt {

	/**
	 * newton way
	 * 
	 * @return
	 */
	double newtonSqrt(double d, double precision) {
		long startTime = System.currentTimeMillis();
		double s = d / 2, last = 0d;
		do {
			last = s;
			s = (s + d / s) / 2;
		} while (Math.abs(s - last) > precision);

		System.out.println("newtonSqrt time cost: " + (System.currentTimeMillis() - startTime) + " ms.");
		return s;
	}

	/**
	 * black tech
	 * 
	 * @return
	 */
	double blackTechSqrt(double d) {
		long startTime = System.currentTimeMillis();
		double half = d / 2;
		long t = 0x5f375a86L - (Double.doubleToLongBits(d) >>> 1);
		double s = Double.longBitsToDouble(t);
		System.out.println("s: " + s);
		int times = 0;
		while (times++ < 3) {
			s = s * (1.5f - half * s * s);
		}

		System.out.println("blackTechSqrt time cost: " + (System.currentTimeMillis() - startTime) + " ms.");
		return 1 / s;
	}

	/**
	 * more efficient, but also more risky, eg. for d with 40bit, the first binary search will probably get (40-1)*2=78bit pow, causes overflow.
	 */
	double sqrt1(double d, int scale) {
		long startTime = System.currentTimeMillis();
		// use binary search all the way
		int i = 0, maxBinarySearchCount = 64; // IEEE754 spec, extended double-precision floating point
		double a = 0d, b = d, mid = getClosestMidValue(d);
		boolean firstLoop = true;
		String sa, sb, smid;
		while (true) {
			if (!firstLoop) {
				mid = (a + b) / 2;
			} else {
				firstLoop = false;
			}
			// optimize
			sa = String.valueOf(a);
			sb = String.valueOf(b);
			smid = String.valueOf(mid);
			if (sa.equals(smid) || sb.equals(smid)) {
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
		System.out.println("sqrt1 time cost: " + (System.currentTimeMillis() - startTime) + " ms.");
		return trim(mid, scale);
	}

	/**
	 * optimize
	 */
	private double getClosestMidValue(double d) {
		if (d < 10000)
			return d / 2;
		else if (d < 1000000) {
			double d1 = d / 10000 + 1;
			double d2 = sqrt1(d1, 1);
			return (d2 + 1) * 100d;
		} else {
			double d1 = d / 1000000 + 1;
			double d2 = sqrt1(d1, 1);
			return (d2 + 1) * 1000d;
		}
	}
	
	private double trim(double result, int scale) {
		String s = String.valueOf(result);
		String[] array = s.split("\\.");
		if (array.length == 2) {
			StringBuilder builder = new StringBuilder(array[0]);
			builder.append(".");
			int i = 0;
			while (i < scale) {
				builder.append(array[1].charAt(i++));
			}
			return Double.valueOf(builder.toString());
		}
		return result;
	}

	public static void main(String[] args) {
		MathSqrt ms = new MathSqrt();

		System.out.println(ms.sqrt1(91000009d, 6));

		System.out.println(ms.newtonSqrt(91000009d, 0.01d));

		System.out.println(ms.blackTechSqrt(91000009d));
	}
}
