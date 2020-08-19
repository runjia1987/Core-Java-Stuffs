package org.jackJew.algorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * top K algorithm
 *
 * @author Jack
 */
public class TopK_Algorithm {

  private final int Range = 999999;

  /**
   * create a random string array
   *
   * @param n length
   * @return
   */
  public int[] createRandomString(int n) {
    int[] array = new int[n];
    int i = 0;
    Random rand = new Random();
    while (i < n) {
      array[i++] = rand.nextInt(Range);
    }
    return array;
  }

  /**
   * use k-size array and array shifting to find top k asc.
   */
  public void getTopKByArray(int[] array, int k) {
    // desc sort the store array by selection.
    int i, j;
    for (i = 0; i < k; i++) {
      int temp = array[i], indexJ = i;
      for (j = i + 1; j < array.length; j++) {
        if (array[j] > temp) {
          temp = array[indexJ = j];
        }
      }
      array[indexJ] = array[i];
      array[i] = temp;
    }

    int[] store = new int[k];
    System.arraycopy(array, 0, store, 0, k);
    System.out.println("getTopKByArray final result: " + Arrays.toString(store));
  }

  /**
   * use min heap and sift up & down to find top k asc <br>
   * time cost: n*logK
   */
  public void getTopKByMinHeap(int[] array, int k) {
    int i = 0, max = array.length;
    int[] store = new int[k];
    // set up k-size array
    while (i < k) {
      if (i == 0) {
        store[0] = array[0];
      } else {
        siftUp(store, i, array[i]);
      }
      i++;
    }
    System.out.println("k-size heap is setUp: " + Arrays.toString(store));

    while (i < max) {
      int element = array[i++];
      if (element > store[0]) {
        siftDown(store, element);
      }
    }
    System.out.println(Arrays.toString(store));
  }

  /**
   * @param i
   * @param element
   * @see java.util.PriorityQueue#siftUp
   */
  public void siftUp(int[] array, int i, int element) {
    while (i > 0) {
      int parent = (i - 1) >>> 1;
      if (element >= array[parent]) {
        break;
      }
      // parent is greater than children
      array[i] = array[parent];
      i = parent;
    }
    array[i] = element;
  }

  /**
   * @param array
   * @param i
   * @param element
   * @see java.util.PriorityQueue#siftDown
   */
  public void siftDown(int[] array, int element) {
    int i = 0;
    int threshold = array.length >>> 1;
    while (i < threshold) {
      int child1 = (i << 1) + 1, child2 = child1 + 1;
      int childElement = array[child1];
      // find the least child
      if (child2 < array.length && array[child2] < childElement) {
        childElement = array[child1 = child2];
      }
      if (element <= childElement) {
        break;
      }
      array[i] = childElement;
      i = child1;
    }
    array[i] = element;
  }

  public void getTopKByQuickSortRecursive(int[] array, int k, int left, int right) {
    if (left < right) {
      int index = findPivotIndex(array, left, right);
      //System.out.println(index + ": " + k);
      if (index == k - 1) {
        // found
        for (int i = 0; i <= index; i++) {
          //System.out.print(array[i] + ", ");
        }
      } else if (index > k - 1) {
        getTopKByQuickSortRecursive(array, k, 0, index - 1);
      } else {
        getTopKByQuickSortRecursive(array, k, index + 1, array.length - 1);
      }
    }
  }

  public void getTopLByQuickSortNonRecursive(int[] array, int k, int left, int right) {
    if (left < right) {
      Stack<Integer> stack = new Stack<>();
      stack.push(right);
      stack.push(left);
      while (!stack.isEmpty()) {
        int _lowBound = stack.pop(), _highBound = stack.pop();
        int index = findPivotIndex(array, _lowBound, _highBound);
        if (index == -1 || index == k - 1) {
          return;
        }
        if (index - _lowBound > 1) {
          stack.push(index - 1);
          stack.push(_lowBound);
        }
        if (_highBound - index > 1) {
          stack.push(_highBound);
          stack.push(index + 1);
        }
      }
    }
  }

  // desc sort
  private int findPivotIndex(int[] array, int left, int right) {
    if (left < right) {
      int i = left, j = right;
      final int pivot = array[left];
      while (i < j) {
        while (i < j && array[j] <= pivot) {
          j--;
        }
        array[i] = array[j];
        while (i < j && array[i] > pivot) {
          i++;
        }
        array[j] = array[i];
      }
      array[i] = pivot;
      return i;
    } else {
      return -1;
    }
  }

  public static void main(String[] args) {
    TopK_Algorithm ta = new TopK_Algorithm();
    // create an array - 100k size
    int[] array = ta.createRandomString(100000);

    // by array
    long startTime = System.nanoTime();
    ta.getTopKByArray(array, 10);
    long endTime = System.nanoTime();
    System.out.println("getTopKByArray cost: " + (endTime - startTime) + "ns.");

    // by min heap
    startTime = System.nanoTime();
    ta.getTopKByMinHeap(array, 10);
    endTime = System.nanoTime();
    System.out.println("getTopKByMinHeap cost: " + (endTime - startTime) + "ns.");

    // by quickSort
    startTime = System.nanoTime();
    ta.getTopKByQuickSortRecursive(array, 10, 0, array.length - 1);
    int index = 0;
    while (index < 10) {
      System.out.print(array[index++] + ", ");
    }
    System.out.println();
    endTime = System.nanoTime();
    System.out.println("getTopKByQuickSortRecursive cost: " + (endTime - startTime) + "ns.");

    // by quickSort - non recursive
    startTime = System.nanoTime();
    ta.getTopLByQuickSortNonRecursive(array, 10, 0, array.length - 1);
    index = 0;
    while (index < 10) {
      System.out.print(array[index++] + ", ");
    }
    System.out.println();
    endTime = System.nanoTime();
    System.out.println("getTopLByQuickSortNonRecursive cost: " + (endTime - startTime) + "ns.");

    /*
     * <br> getTopLByQuickSortNonRecursive is best.
     */
  }

}
