package com.interviews.algo;

import java.util.Arrays;

class QuickSort {

    public static void main(String[] args) {

        int[] test = {15, 5, 6, 2, 4, 8, 5, 5, 1, 4, 6, 6, 9, 7};

        QuickSort quickSort = new QuickSort();
        quickSort.sort(test);
        System.out.println(Arrays.asList(test));

    }

    public void sort(int[] values) {

        if (values == null || values.length == 0) {
            return;
        }

        int number = values.length;
        quickSort(values, 0, number - 1);
    }

    public void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public int partition(int arr[], int left, int right) {
        int pivot = arr[(left + right) / 2]; // Pick a pivot point. Can be an element.

        while (left <= right) { // Until we've gone through the whole array
            // Find element on left that should be on right
            while (arr[left] < pivot) {
                left++;
            }

            // Find element on right that should be on left
            while (arr[right] > pivot) {
                right--;
            }

            // Swap elements, and move left and right indices
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    public void quickSort(int arr[], int left, int right) {

        int index = partition(arr, left, right);
        if (left < index - 1) { // Sort left half
            quickSort(arr, left, index - 1);
        }
        if (index < right) { // Sort right half
            quickSort(arr, index, right);
        }
    }

}
