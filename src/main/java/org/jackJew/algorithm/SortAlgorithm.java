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

	private static int[] array1 = { 1, 100, 2, 3,60, 55, 33 };

	final static Random rand = new Random();

	/**
	 * 归并排序, 递归
	 */
	public static void mergeSort(int left, int right, int[] array) {
		if (left < right) {
			int middle = (left + right) / 2; // 中间点

			mergeSort(left, middle, array); // 左侧排序
			mergeSort(middle + 1, right, array); // 右侧排序

			doMerge(left, right, array, new int[array.length]); // 合并已排序的左侧与右侧数据
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
	 * merge and find out the median from two sorted arrays,
	 * time complexity: O(m+n)/2
	 */
	public static int mergeForMedianValue(int[] array1, int[] array2) {
		int a1 = array1[0], a2 = array2[0];
		int i1 = 0, i2 = 0;
		int limit = (array1.length + array2.length) / 2 + 1, count = 0;
		System.out.println("limit: " + limit);
		while (count++ < limit) { // 循环次数
			a1 = a2;
			if(i1 == array1.length) {
				a2 = array2[i2++];
				continue;
			}
			if(i2 == array2.length) {
				a2 = array1[i1++];
				continue;
			}
			if (array1[i1] <= array2[i2]) { // less equal
				a2 = array1[i1++];
			} else if (array1[i1] > array2[i2]){  // greater than
				a2 = array2[i2++];
			}
		}
		System.out.println(a1 + "," + a2);
		if((array1.length + array2.length) % 2 == 0)
			return (a1 + a2) / 2;
		else
			return a2;
	}

	static class IndexAndValue {
		int index;
		int value;

		public IndexAndValue(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	/**
	 * 快排查找中位数, ASC, 亦可解决第k小的数值问题
	 * @param array
	 */
	public static IndexAndValue quickSortForMedianValue(int[] array, int left, int right, int kIndex){
		if (left < right) {
			int i = findPivotIndex(array, left, right);
			System.out.println("limit: " + kIndex + ", i: " + i + ", array[i]: " + array[i]);
			for(int j : array) {
				System.out.print(j + ", ");
			}
			System.out.println();
			if ( i == kIndex) {
				return new IndexAndValue(i, array[i]);
			} else if (i > kIndex) {
				// 位置大于中间的位置，则查找左半部分中第(i-limit)大的数字
				return quickSortForMedianValue(array, left, i - 1, kIndex);
			} else {
				// 位置小于中间的位置，则查找右半部分中第(limit - i)小的数字
				return quickSortForMedianValue(array, i + 1, right, kIndex);
			}
		} else if(left == right) {
			return new IndexAndValue(left, array[left]);
		} else {
			throw new RuntimeException("left > right error.");
		}
	}

	/**
	 * 快速排序, ASC
	 */
	public static void quickSort(int[] array, int left, int right) {
		int index = findPivotIndex(array, left, right);

		if (index != -1 && left < right) {
			quickSort(array, left, index - 1); // 排序左侧数据
			quickSort(array, index + 1, right); // 排序右侧数据
		}
	}

	/**
	 * 快速排序, 非递归. <br>
	 * 快排可用于查找中位数 median value.
	 */
	public static void quickSort_nonRecursive(int[] array, int left, int right) {
		Stack<Integer> stack = new Stack<>();
		stack.push(right);
		stack.push(left);
		while (stack.size() > 0) {
			int lowBound = stack.pop();
			int highBound = stack.pop();
			int index = findPivotIndex(array, lowBound, highBound);
			if (index == -1)
				break;
			// repeat
			if (lowBound + 1 < index) {
				stack.push(index - 1);
				stack.push(lowBound);
			}
			if (index + 1 < highBound) {
				stack.push(highBound);
				stack.push(index + 1);
			}
		}
	}

	// find the index for pivot, ASC
	private static int findPivotIndex(int[] array, int left, int right) {
		if (left < right) {
			int i = left, j = right;
			// random pivot pos
			int randomPos = left + new Random().nextInt(right - left);
			int temp = array[i];
			// switch with left-most pos
			array[i] = array[randomPos];
			array[randomPos] = temp;
			int pivot = array[i];

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
		for (int i = 0; i < length - 1; i++) {
			int temp = array[i];
			int minJ = i;
			for (int j = i + 1; j < length; j++) { // 将本次循环的最小值与当前首元素交换
				if (temp > array[j]) {
					minJ = j;
					temp = array[j];
				}
			}
			array[minJ] = array[i];
			array[i] = temp;
		}
	}

	/**
	 * 排序测试
	 */
	public static void main(String[] args) throws Exception {
		// mergeSort(0, array.length-1, array);

		quickSort(array1, 0, array1.length-1);
		//quickSort_nonRecursive(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array1));

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

		/**
		 * find median value in odd-sized array
		 */
		array2 = new int[] {20, 9999, 600, 7, 22, 92, 100, 321, 500, 1000, 4500 };
		System.out.println("array2 quickSortForMedianValue: " + quickSortForMedianValue(array2, 0, array2.length - 1, array2.length / 2));

		int k = 3;
		System.out.println("quickSortForMedianValue the " + k +  " least value: "
				+ quickSortForMedianValue(array2, 0, array2.length - 1, k - 1));

		/**
		 * find median value in even-sized array
		 */
		array2 = new int[] {20, 9999, 600, 7, 22, 92, 100, 321, 500, 1000, 4500, 323 };
		IndexAndValue result = quickSortForMedianValue(array2, 0, array2.length - 1, array2.length / 2);
		int upperValue = result.value;
		int lowerValue = quickSortForMedianValue(array2, 0, result.index, result.index - 1).value;
		// expected value is 322
		System.out.println("even array2 quickSortForMedianValue: " + (lowerValue + upperValue) / 2);
	}

}
