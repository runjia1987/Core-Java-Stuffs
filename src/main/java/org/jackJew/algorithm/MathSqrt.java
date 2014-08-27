package org.jackJew.algorithm;

import java.math.BigDecimal;

/**
 * algorithm for Math.sqrt implementation
 * @author Jack
 *
 */
public class MathSqrt {
	
	/**
	 * more efficient, but also more risky, eg. for d with 40bit, the first binary search will probably get (40-1)*2=78bit pow, causes overflow.
	 */
	double sqrt1(double d, int scale) {
		// use binary search all the way
		int  i = 0, maxBinarySearchCount = 64; // IEEE754 spec, extended double-precision floating point
		double a = 0d , b = d, mid = getClosestMidValue(d);
		boolean firstLoop = true;
		System.out.println("mid: " + mid);
		String sa, sb, smid;
		while ( true ) {
			if ( ! firstLoop) {
				mid = (a + b) / 2;
			} else {
				firstLoop = false;
			}
			// optimize
			sa = String.valueOf(a);
			sb = String.valueOf(b);
			smid = String.valueOf(mid);
			if ( sa.equals(smid) || sb.equals(smid) ) {
				return mid;
			}
			
			double pow = mid * mid;
			if ( pow == d) {
				break;
			} else if ( pow > d) {
				b = mid;
			} else {
				a = mid;
			}		
			
			System.out.println("mid: " + mid);
			i++;
			if( i == maxBinarySearchCount)
				break;
			System.out.println("binarySerach for the " + i + "times.");
		}
		
		return trim(mid, scale);
	}
	
	/**
	 * optimize
	 */
	private double getClosestMidValue(double d){
		if (d < 10000)
			return d / 2;
		else if( d < 1000000) {
			System.out.println("> 1w");
			double d1 = d / 10000 + 1;
			double d2 = sqrt1(d1, 1);
			return (d2 + 1) * 100d;
		} else {
			System.out.println("> 100w");
			double d1 = d / 1000000 + 1;
			double d2 = sqrt1(d1, 1);
			return (d2 + 1) * 1000d;
		}
	}
	
	/**
	 * less efficient, takes more time when computing.
	 */
	double sqrt2(double d, int scale) {
		// first listing from 0
		int i = 0, count = 0;
		while(true) {
			long pow = i * i;
			if(pow == d) {
				return i;
			} else if(pow > d) {
				i--;
				break;
			} else {
				i++;
			}
			count++;
			System.out.println(count);
		}
		
		int j = i + 1;
		// use binary search
		int maxBinarySearchCount = 64;
		double a = i , b = j, mid = 0d;
		String sa, sb, smid;
		count = 0;
		while ( true ) {
			mid = (a + b) / 2;
			sa = String.valueOf(a);
			sb = String.valueOf(b);
			smid = String.valueOf(mid);
			if ( sa.equals(smid) || sb.equals(smid) ) {
				return mid;
			}
			
			double pow = mid * mid;
			if ( pow == d) {
				break;
			} else if ( pow > d) {
				b = mid;
			} else {
				a = mid;
			}
			System.out.println(a + ", " + b);
			count++;
			if( count == maxBinarySearchCount)
				break;
			System.out.println("binarySerach for the " + count + "times.");
		}
		
		return trim(mid, scale);
	}
	
	/**
	 * get step value
	 */
	private BigDecimal getStepValue(int scale){
		BigDecimal b = new BigDecimal(1);
		long s = 1;
		int i = 0;
		while( i < scale) {
			s = 10 * s;
			i++;
		}		
		return b.divide(new BigDecimal(s));
	}
	
	private double trim(double result, int scale) {
		String s = String.valueOf(result);
		String[] array = s.split("\\.");
		if( array.length == 2) {
			StringBuilder builder = new StringBuilder(array[0]);
			builder.append(".");
			int i = 0;
			while(i < scale) {
				builder.append(array[1].charAt(i));
				i++;
			}
			return Double.valueOf(builder.toString());
		}		
		return result;
	}

	
	public static void main(String[] args){
		MathSqrt ms = new MathSqrt();
		
		System.out.println(ms.sqrt1(91000009d, 6));
		
		//r = ms.sqrt2(900009, 8);
		//System.out.println(r);
		
		System.out.println(ms.getStepValue(5).toPlainString());
		System.out.println(ms.trim(948.6880414551456d, 7));
		
		System.out.println(String.valueOf(1000.449898737432d));
	}
}
