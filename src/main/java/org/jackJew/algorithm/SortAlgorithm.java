package org.jackJew.algorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * Description: 排序算法实现 
 * @author zhurunjia
 *
 */
public class SortAlgorithm {
	
	private static int[] array = {12,45,67,90,103,666,2,103,88,344,45,1000};
	final static Random rand = new Random();
	
	/**
	 * 归并排序, 递归
	 */
	public static void mergeSort(int left, int right, int[] array, int[] temp){
		if(left < right){
			int middle = (left + right)/2;  //中间点
			
			mergeSort(left, middle, array, temp);	//左侧排序
			mergeSort(middle+1, right, array, temp); //右侧排序
			
			mergeArray(left, right, array, temp);	//合并已排序的左侧与右侧数据
		}
	}
	/**
	 * 递增有序
	 */
	public static void mergeArray(int left, int right, int[] array, int[] temp){
		int i = left, mid = (left+right)/2, j = mid+1, index = 0;
		while(i <= mid && j <= right) {
			if(array[i] < array[j])
				temp[index++] = array[i++];
			else
				temp[index++] = array[j++];
		}
		while(i <= mid)
			temp[index++] = array[i++];
		while(j <= right)
			temp[index++] = array[j++];
		
		for(i=0; i<index; i++)
			array[left+i] = temp[i];
	}
	
	/**
	 * merge and find out the common elements from two sorted arrays
	 */
	public static void mergeCommon(int[] array1, int[] array2){
		int a1 = 0, a2 = array1.length, b1 = 0 ,b2 = array2.length;
		int c1, c2;
		while(a1 < a2 && b1 < b2) {
			c1 = array1[a1];
			c2 = array2[b1];
			if (c1 == c2) {
				System.out.println(c1);
				a1++;
				b1++;
			} else if( c1 < c2) {
				a1++;
			} else {
				b1++;
			}
		}
	}
	
	
	/**
	 * 快速排序, ASC
	 */
	public static void quickSort(int[] array, int left, int right){
		int i = left, j = right, pivot = array[left];	//选择一个参照数
		
		if(left < right){			
			while (i < j){
				while(i < j && array[j] >= pivot)	//从右端点向左查找第一个小于参照数的值
					j--;
				if(i < j)
					array[i] = array[j];
				while(i < j && array[i] < pivot)	//从左端点向右查找第一个大于=参照数的值
					i++;
				if(i < j)
					array[j] = array[i];
			}
			
			array[i] = pivot;	//第一轮排序后, i左侧的数都小于i右侧的数据
			
			quickSort(array, left, i-1);	//排序左侧数据
			quickSort(array, i+1, right);	//排序右侧数据
		}
	}
	
	/**
	 * 快速排序, ASC, 随机化轴值
	 */
	public static void quickSort_random(int[] array, int left, int right){
		if(left < right){
			int randomPos = left + rand.nextInt(right-left);
			int i = left, j = right, X = array[i], pivot = array[randomPos];	//随机的参照数
			// swap
			array[randomPos] = X;
			array[i] = pivot;
			
			while (i < j){
				while(i < j && array[j] >= pivot)	//从右端点向左查找第一个小于参照数的值
					j--;
				if(i < j)
					array[i] = array[j];
				while(i < j && array[i] < pivot)	//从左端点向右查找第一个大于参照数的值
					i++;
				if(i < j)
					array[j] = array[i];
			}
			
			array[i] = pivot;	//第一轮排序后, i左侧的数都小于i右侧的数据
			
			quickSort(array, left, i-1);	//排序左侧数据
			quickSort(array, i+1, right);	//排序右侧数据
		}
	}
	
	/**
	 * 冒泡排序
	 */
	public static void bubbleSort(int[] array, int length){
		int temp;
		for(int i = 1; i < length -1; i++){
			for(int j = 0; j < length - i; j++){
				if(array[j] > array[j+1]){	//相邻元素不断比较交换
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}
	
	/**
	 * 选择排序
	 */
	public static void selectSort(int[] array, int length){
		int temp, minJ;
		for(int i = 0; i < length-1; i++){
			temp = array[i]; minJ = i;
			for(int j = i+1; j < length; j++){  //将本次循环的最小值与当前首元素交换
				if(array[i] > array[j]){
					minJ = j;
					array[i] = array[j];
				}
			}
			array[minJ] = temp;
		}
	}
	
	/**
	 * 排序测试
	 */
	public static void main(String[] args) throws Exception {
		int[] temp = new int[array.length];
		
		long startTime = System.currentTimeMillis();
		//mergeSort(0, array.length-1, array, temp);
		
		quickSort(0, array.length-1, array);
		quickSort(array, 0, array.length-1);
		//bubbleSort(array, array.length);
		//selectSort(array, array.length);
		
		for(int i : array)
			System.out.print(i + ",");
		
		System.out.println("\ntime elapsed： " + (System.currentTimeMillis() - startTime) + "ms.");
		
		//System.out.println(2.0 / 5.0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		System.out.println(sdf.parse("2013-12-16 20:09:33.0"));
		
		int[] array1 = {-9, 1, 7, 45, 234, 321}, array2 = {7, 56, 321, 500, 600, 1000, 4500, };
		mergeCommon(array1, array2);
		
	}

}
