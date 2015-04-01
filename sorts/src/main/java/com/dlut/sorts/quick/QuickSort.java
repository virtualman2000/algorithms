package com.dlut.sorts.quick;

public class QuickSort {


     private QuickSort() {}

     public static void sort(int[] A) {
          sort(A, 0, A.length - 1);
     }

     private static void sort(int[] A, int low, int hign) {

          /*
          if (low < hign) {
               int key = partition(A, low, hign);
               sort(A, low, key - 1);
               sort(A, key + 1, hign);
          }*/

          // this method is better
          int key = 0;
          while (low < hign) {
               key = partition(A, low, hign);
               sort(A, low, key - 1);
               low = key + 1;
          }
     }

     private static int partition(int[] A, int low, int hign) {

          int key = A[low];

          while (low < hign) {
               while (low < hign && A[hign] > key) {
                    hign--;
               }
               swap(A, low, hign);

               while (low < hign && A[low] < key) {
                    low++;
               }
               swap(A, low, hign);
          }
          return low;
     }


     public static void swap(int[] A, int i, int k) {
          int temp = A[i];
          A[i] = A[k];
          A[k] = temp;
     }


     public static void main(String[] args) {

          int[] A = new int[]{230, 1, 122, 6, 2, 55, 22, 66, 33, 5, 9, 65, 79, 90, 0, 101, 120, 4};
          QuickSort.sort(A);

          for (int i = 0; i < A.length; i++) {
               System.out.println(A[i]);
          }
     }

}