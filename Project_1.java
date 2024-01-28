import java.util.Arrays;
import java.util.Random;


public class Project_1 {

    public static void main(String[] args) {
        int size = 20;
        int[] A = RandomArray(size);
        
        long startTime;
        long estimatedTime;
        
        System.out.println(Arrays.toString(A));
        
        startTime = System.nanoTime();
        int[] D = InsertionSort(A);
        estimatedTime = System.nanoTime() - startTime;
        
        System.out.println(Arrays.toString(D));
        System.out.println("Estimated time: " + estimatedTime);
        
        startTime = System.nanoTime();
        int[] B = QuickSortDoublePointer(A, 0, size-1);
        estimatedTime = System.nanoTime() - startTime;
        
        System.out.println(Arrays.toString(B));
        System.out.println("Estimated time: " + estimatedTime);
        
        startTime = System.nanoTime();
        int[] C = QuickSortSinglePointer(A, 0, size-1);
        estimatedTime = System.nanoTime() - startTime;
        
        System.out.println(Arrays.toString(C));
        System.out.println("Estimated time: " + estimatedTime);
    }
    
    public static int[] RandomArray(int size){
        
        Random rand = new Random();
        int[] A = new int[size];
        
        for(int i=0; i < size; i++){
            A[i] = rand.nextInt(size);
        }
        
        return A;
    }
    
    public static int[] InsertionSort(int[] A){
        
        int key;
        int j;
        
        for(int i=1; i < A.length; i++){
            key = A[i];
            j = i-1;
            
            while(j >= 0 && A[j] > key){
                A[j+1] = A[j];
                j = j-1;
            }
            A[j+1] = key;
        }
        
        return A;
    }
    
    public static void swap(int[] A, int lower, int upper){
        int x = A[lower];
        A[lower] = A[upper];
        A[upper] = x;
    }
    
    public static int DoublePointerPartition(int[] A, int first, int last){
        int pivot = A[last];
        int lower = first;
        int upper = last-1;
        
        while(lower <= upper){
            while(lower <= upper && A[upper] >= pivot){
                upper--;
            }
            while(lower <= upper && A[lower] <= pivot){
                lower++;
            }
            if(lower < upper){
                swap(A, lower, upper);
            }            
        }
        
        swap(A, lower, last);
        return lower;
        
    }
    
    public static int SinglePointerPartition(int[] A, int first, int last){
        int pivot = A[last];
        int lowPointer = first - 1;
        
        for(int pointer = first; pointer <= last - 1; pointer++){
            if(A[pointer] <= pivot){
                lowPointer = lowPointer + 1;
                swap(A, lowPointer, pointer);
            }
        }
        
        lowPointer = lowPointer + 1;
        swap(A, lowPointer, last);
        return lowPointer;
    }
    
    public static int[] QuickSortDoublePointer(int[] A, int first, int last){
        if(first<last){
            int mid = DoublePointerPartition(A, first, last);
            QuickSortDoublePointer(A, first, mid-1);
            QuickSortDoublePointer(A, mid+1, last);
        }
        return A;
    }
    
    public static int[] QuickSortSinglePointer(int[] A, int first, int last){
        if(first<last){
            int mid = SinglePointerPartition(A, first, last);
            QuickSortSinglePointer(A, first, mid-1);
            QuickSortSinglePointer(A, mid+1, last);
        }
        return A;
    }    
    
}
