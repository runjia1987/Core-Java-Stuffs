package org.jackJew.algorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * 
 * Description: 排序算法实现
 * 
 * @author zhurunjia
 *
 */
public class SortAlgorithm {

	private static int[] array = { 9999, 450, 0, 8, -20, 45555, 12, 45, 67, 90, 103, 666, 2, 103, 88, 344, 45, 1000 };
	final static Random rand = new Random();

	/**
	 * 归并排序, 递归
	 */
	public static void mergeSort(int left, int right, int[] array, int[] temp) {
		if (left < right) {
			int middle = (left + right) / 2; // 中间点

			mergeSort(left, middle, array, temp); // 左侧排序
			mergeSort(middle + 1, right, array, temp); // 右侧排序

			doMerge(left, right, array, temp); // 合并已排序的左侧与右侧数据
		}
	}

	/**
	 * 归并已经从中点分割有序的一个数组
	 */
	private static void doMerge(int left, int right, int[] array, int[] temp) {
		int i = left, mid = (left + right) / 2, j = mid + 1, index = 0;
		while (i <= mid && j <= right) {
			if (array[i] < array[j])
				temp[index++] = array[i++];
			else
				temp[index++] = array[j++];
		}
		while (i <= mid)
			temp[index++] = array[i++];
		while (j <= right)
			temp[index++] = array[j++];

		for (i = 0; i < index; i++)
			array[left + i] = temp[i];
	}
	
	/**
	 * 归并排序两个各自有序的数组
	 */
	public static void mergeArray(int[] array1, int[] array2, int[] target) {
		int i1 = 0, i2 = 0;
		int index = 0;
		while (i1 < array1.length && i2 < array2.length) {
			if (array1[i1] < array2[i2])
				target[index++] = array1[i1++];
			else
				target[index++] = array2[i2++];
		}
		while (i1 < array1.length)
			target[index++] = array1[i1++];
		while (i2 < array2.length)
			target[index++] = array2[i2++];
	}

	/**
	 * merge and find out the identical elements from two sorted arrays
	 */
	public static void mergeCommon(int[] array1, int[] array2) {
		int a1 = 0, a2 = array1.length, b1 = 0, b2 = array2.length;
		int c1, c2;
		while (a1 < a2 && b1 < b2) {
			c1 = array1[a1];
			c2 = array2[b1];
			if (c1 == c2) {
				System.out.println(c1);
				a1++;
				b1++;
			} else if (c1 < c2) {
				a1++;
			} else {
				b1++;
			}
		}
	}
	
	/**
	 * merge and find out the identical elements from two sorted arrays,
	 * time complexity: O(m+n)/2
	 */
	public static int mergeForMedianValue(int[] array1, int[] array2) {
		int a1 = array1[0], a2 = array2[0];
		int i1 = 0, i2 = 0;
		int limit = (array1.length + array2.length) / 2 + 1, count = 0;
		System.out.println("limit: " + limit);
		while (count++ < limit) { // 循环次数
			if(i1 == array1.length) {
				a1 = a2;
				a2 = array2[i2++];
				continue;
			}
			if(i2 == array2.length) {
				a1 = a2;
				a2 = array1[i1++];
				continue;
			}
			if (array1[i1] < array2[i2]) { // less than	
				a1 = a2;
				a2 = array1[i1++];
			} else if (array1[i1] > array2[i2]){  // greater than	
				a1 = a2;
				a2 = array2[i2++];
			} else {  // equal
				a1 = a2;
				a2 = array1[i1++];
			}
		}
		System.out.println(a1 + "," + a2);
		if((array1.length + array2.length) % 2 == 0)
			return (a1 + a2) / 2;
		else
			return a2;
	}
	
	/**
	 * 快排查找中位数, ASC
	 * @param array
	 */
	public static int quickSortForMedianValue(int[] array, int left, int right, int limit){
		if (left < right) {
			int i = findPivotIndex(array, left, right);
			System.out.println("limit: " + limit + ", i: " + i + ", array[i]: " + array[i]);
			for(int j : array) {
				System.out.print(j + ", ");
			}
			System.out.println();
			if ( i == limit) {
				return array[i];
			} else if (i > limit) {
				// 位置大于中间的位置，则查找左半部分中第(i-limit)大的数字
				return quickSortForMedianValue(array, left, i - 1, limit);
			} else {
				// 位置小于中间的位置，则查找右半部分中第(limit - i)小的数字
				return quickSortForMedianValue(array, i + 1, right, limit);
			}
		} else if(left == right) {
			return array[left];
		} else {
			throw new RuntimeException("left > right error.");
		}
	}

	/**
	 * 快速排序, ASC
	 */
	public static void quickSort(int[] array, int left, int right) {
		int i = left, j = right, pivot = array[left]; // 选择一个参照数

		if (left < right) {
			while (i < j) {
				while (i < j && array[j] >= pivot)
					// 从右端点向左查找第一个小于参照数的值
					j--;
				if (i < j)
					array[i] = array[j];
				while (i < j && array[i] < pivot)
					// 从左端点向右查找第一个大于=参照数的值
					i++;
				if (i < j)
					array[j] = array[i];
			}

			array[i] = pivot; // 第一轮排序后, i左侧的数都小于i右侧的数据

			quickSort(array, left, i - 1); // 排序左侧数据
			quickSort(array, i + 1, right); // 排序右侧数据
		}
	}

	/**
	 * 快速排序, ASC, 随机化轴值
	 */
	public static void quickSort_random(int[] array, int left, int right) {
		if (left < right) {
			int randomPos = left + rand.nextInt(right - left);
			int i = left, j = right, X = array[i], pivot = array[randomPos]; // 随机的参照数
			// swap
			array[randomPos] = X;
			array[i] = pivot;

			while (i < j) {
				while (i < j && array[j] >= pivot)
					// 从右端点向左查找第一个小于参照数的值
					j--;
				if (i < j)
					array[i] = array[j];
				while (i < j && array[i] < pivot)
					// 从左端点向右查找第一个大于参照数的值
					i++;
				if (i < j)
					array[j] = array[i];
			}

			array[i] = pivot; // 第一轮排序后, i左侧的数都小于i右侧的数据

			quickSort_random(array, left, i - 1); // 排序左侧数据
			quickSort_random(array, i + 1, right); // 排序右侧数据
		}
	}

	/**
	 * 快速排序, 非递归. <br>
	 * 快排可用于查找中位数 median value.
	 */
	public static void quickSort_nonRecursive(int[] array, int left, int right) {
		Stack<Integer> stack = new Stack<Integer>();
		int index = findPivotIndex(array, left, right);

		if (index != -1 && left < right) {
			if (left + 1 < index) {
				stack.push(left);
				stack.push(index - 1);
			}
			if (index + 1 < right) {
				stack.push(index + 1);
				stack.push(right);
			}

			while (stack.size() > 0) {
				int highBound = (Integer) stack.pop();
				int lowBound = (Integer) stack.pop();
				index = findPivotIndex(array, lowBound, highBound);
				if (index == -1)
					break;
				// repeat
				if (lowBound + 1 < index) {
					stack.push(lowBound);
					stack.push(index - 1);
				}
				if (index + 1 < highBound) {
					stack.push(index + 1);
					stack.push(highBound);
				}
			}
		}
	}

	// find the index for pivot
	private static int findPivotIndex(int[] array, int left, int right) {
		if (left < right) {
			int i = left, j = right;
			// random pivot pos
			int randomPos = left + new Random().nextInt(right - left);
			int pivot = array[i];
			// switch with left-most pos
			array[i] = array[randomPos];
			array[randomPos] = pivot;
			pivot = array[i];

			while (i < j) {
				while (i < j && array[j] >= pivot)
					j--;
				if (i < j)
					array[i] = array[j];

				while (i < j && array[i] < pivot)
					i++;
				if (i < j)
					array[j] = array[i];
			}
			array[i] = pivot;
			return i;
		} else {
			return -1;
		}
	}

	/**
	 * 冒泡排序
	 */
	public static void bubbleSort(int[] array, int length) {
		int temp;
		for (int i = 1; i < length - 1; i++) {
			for (int j = 0; j < length - i; j++) {
				if (array[j] > array[j + 1]) { // 相邻元素不断比较交换
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 选择排序
	 */
	public static void selectSort(int[] array, int length) {
		int temp, minJ;
		for (int i = 0; i < length - 1; i++) {
			temp = array[i];
			minJ = i;
			for (int j = i + 1; j < length; j++) { // 将本次循环的最小值与当前首元素交换
				if (array[i] > array[j]) {
					minJ = j;
				}
			}
			array[i] = array[minJ];
			array[minJ] = temp;
		}
	}

	/**
	 * 排序测试
	 */
	public static void main(String[] args) throws Exception {
		int[] temp = new int[array.length];

		// mergeSort(0, array.length-1, array, temp);

		// quickSort(array, 0, array.length-1);
		quickSort_nonRecursive(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));

		// bubbleSort(array, array.length);
		selectSort(array, array.length);
		System.out.println(Arrays.toString(array));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		System.out.println(sdf.parse("2013-12-16 20:09:33.0"));

		System.out.println("merge common: ");
		int[] array1 = { -9, 22, 100, 600 }, array2 = { 7, 22, 45, 92, 100, 321, 500, 600, 1000, 4500, 9999 };
		mergeCommon(array1, array2);
		
		int totalCount = array1.length + array2.length;
		int[] mergedSort = new int[totalCount];
		mergeArray(array1, array2, mergedSort);
		for (int i : mergedSort) {
			System.out.print(i + ", ");
		}
		System.out.println("totalCount: " + totalCount);
		
		System.out.println("mergeForMedianValue: " + mergeForMedianValue(array1, array2));
		
		array2 = new int[] {20, 9999, 600, 7, 22, 92, 100, 321, 500, 1000, 4500 };
		// 只考虑数组长度为奇数的情况
		System.out.println("quickSortForMedianValue: " + quickSortForMedianValue(array2, 0, array2.length - 1, array2.length / 2));
	}

}
