package com.mycompany.project_1;

import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;


public class Project_1 {

    public static void main(String[] args) {
        int size = 20;
//        int[] sizes = new int[] {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 
//            10000, 20000, 50000, 100000, 200000, 500000, 1000000};
        int[] sizes = new int[]{10, 20, 50, 100, 200, 500, 1000, 2000, 5000,
            10000};
        
        try {
            FileWriter writer = new FileWriter("C:\\Users\\kelee\\Documents\\Project1.csv");
            
            long startTime;
            long estimatedTime;

            for (int j = 0; j < sizes.length; j++) {
                size = sizes[j];
                writer.append(String.valueOf(size));
                writer.append(",");
                
                int[] A = RandomArray(size);    
                int[] B = new int[size];        // Random
                int[] C = new int[size];        // Random
                int[] D = new int[size];        // Forward sorted
                int[] E = new int[size];        // Reverse sorted
                int[] F = new int[size];        // Reverse sorted
                int[] G = new int[size];        // Reverse sorted

                
                for (int i = 0; i < size; i++) {
                    B[i] = A[i];
                    C[i] = A[i];
                    D[i] = i;
                    E[i] = size - i;
                    F[i] = size - i;
                    G[i] = size - i;
                }
                
                startTime = System.nanoTime();
                InsertionSort(A);
                estimatedTime = System.nanoTime() - startTime;
                writer.append(String.valueOf(estimatedTime));
                writer.append(",");

                System.out.println("Estimated time: " + estimatedTime);
                
                startTime = System.nanoTime();
                InsertionSort(D);
                estimatedTime = System.nanoTime() - startTime;
                writer.append(String.valueOf(estimatedTime));
                writer.append(",");

                System.out.println("Estimated time: " + estimatedTime);
                
                startTime = System.nanoTime();
                InsertionSort(E);
                estimatedTime = System.nanoTime() - startTime;
                writer.append(String.valueOf(estimatedTime));
                writer.append(",");

                System.out.println("Estimated time: " + estimatedTime);

                startTime = System.nanoTime();
                QuickSortSinglePointer(B, 0, size - 1);
                estimatedTime = System.nanoTime() - startTime;
                writer.append(String.valueOf(estimatedTime));
                writer.append(",");

                System.out.println("Estimated time: " + estimatedTime);

                startTime = System.nanoTime();
                QuickSortSinglePointer(D, 0, size - 1);
                estimatedTime = System.nanoTime() - startTime;
                writer.append(String.valueOf(estimatedTime));
                writer.append(",");

                System.out.println("Estimated time: " + estimatedTime);

                startTime = System.nanoTime();
                QuickSortSinglePointer(F, 0, size - 1);
                estimatedTime = System.nanoTime() - startTime;
                writer.append(String.valueOf(estimatedTime));
                writer.append(",");

                System.out.println("Estimated time: " + estimatedTime);
                
                startTime = System.nanoTime();
                QuickSortDoublePointer(C, 0, size - 1);
                estimatedTime = System.nanoTime() - startTime;
                writer.append(String.valueOf(estimatedTime));
                writer.append(",");

                System.out.println("Estimated time: " + estimatedTime);
                
                startTime = System.nanoTime();
                QuickSortDoublePointer(D, 0, size - 1);
                estimatedTime = System.nanoTime() - startTime;
                writer.append(String.valueOf(estimatedTime));
                writer.append(",");

                System.out.println("Estimated time: " + estimatedTime);
                
                startTime = System.nanoTime();
                QuickSortDoublePointer(G, 0, size - 1);
                estimatedTime = System.nanoTime() - startTime;
                writer.append(String.valueOf(estimatedTime));
                writer.append(",");

                System.out.println("Estimated time: " + estimatedTime);
                
                
                writer.append("\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

    public static int[] RandomArray(int size) {

        Random rand = new Random();
        int[] A = new int[size];

        for (int i = 0; i < size; i++) {
            A[i] = rand.nextInt(size);
        }

        return A;
    }

    public static int[] InsertionSort(int[] A) {

        int key;
        int j;

        for (int i = 1; i < A.length; i++) {
            key = A[i];
            j = i - 1;

            while (j >= 0 && A[j] > key) {
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }

        return A;
    }

    public static void swap(int[] A, int lower, int upper) {
        int x = A[lower];
        A[lower] = A[upper];
        A[upper] = x;
    }

    public static int DoublePointerPartition(int[] A, int first, int last) {
        int pivot = A[last];
        int lower = first;
        int upper = last - 1;

        while (lower <= upper) {
            while (lower <= upper && A[upper] >= pivot) {
                upper--;
            }
            while (lower <= upper && A[lower] <= pivot) {
                lower++;
            }
            if (lower < upper) {
                swap(A, lower, upper);
            }
        }

        swap(A, lower, last);
        return lower;

    }

    public static int SinglePointerPartition(int[] A, int first, int last) {
        int pivot = A[last];
        int lowPointer = first - 1;

        for (int pointer = first; pointer <= last - 1; pointer++) {
            if (A[pointer] <= pivot) {
                lowPointer = lowPointer + 1;
                swap(A, lowPointer, pointer);
            }
        }

        lowPointer = lowPointer + 1;
        swap(A, lowPointer, last);
        return lowPointer;
    }

    public static int[] QuickSortDoublePointer(int[] A, int first, int last) {
        if (first < last) {
            int mid = DoublePointerPartition(A, first, last);
            QuickSortDoublePointer(A, first, mid - 1);
            QuickSortDoublePointer(A, mid + 1, last);
        }
        return A;
    }

    public static int[] QuickSortSinglePointer(int[] A, int first, int last) {
        if (first < last) {
            int mid = SinglePointerPartition(A, first, last);
            QuickSortSinglePointer(A, first, mid - 1);
            QuickSortSinglePointer(A, mid + 1, last);
        }
        return A;
    }
}
