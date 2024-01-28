
import java.util.Arrays;
import java.util.Random;


public class Project_1 {

    public static void main(String[] args) {
        int size = 20;
        int[] A = RandomArray(size);
        
        System.out.println(Arrays.toString(A));
        
        int[] B = QuickSortDoublePointer(A, 0, size-1);
        int[] C = QuickSortSinglePointer(A, 0, size-1);
        
        System.out.println(Arrays.toString(B));
        System.out.println(Arrays.toString(C));
    }
    
    public static int[] RandomArray(int size){
        
        Random rand = new Random();
        int[] A = new int[size];
        
        for(int i=0; i < size; i++){
            A[i] = rand.nextInt(size);
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
