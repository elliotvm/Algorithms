import java.util.Arrays;

public class Alghw4 {

    //recursive function to print result
    public static void print(int A[], int Prev[], int p){
        if(p != -1){
            print(A, Prev, Prev[p]);
            System.out.print(A[p] + " ");
        }       
    }
    
    public static void find(int A[], int size){
        
        //end pointer of LMI sequence for recursive print function
        int overallLongest = 0;
        //holds pointers to previous integer in LMI sequence
        int Prev[] = new int[size];
        
        //holds size of longest LMI sequence possible at given index
        int LMI[] = new int[size];
        //fill LMI with initial value of 1 and prev with -1
        for(int i = 0; i < size; i++){
            //LMI[i] = 1;
            Prev[i] = -1;
        }
                
        for(int i = 0; i < size; i++){
            
            int l = 0;
            
            for(int j = 0; j < i; j++){ 
                
                if(A[j] < A[i] && LMI[j] + 1 > l){
                    Prev[i] = j;
                    LMI[i] = LMI[j] + 1;
                    l = LMI[i];
                    if(l > LMI[overallLongest]){
                        overallLongest = i;
                    }
                }
            }
        }
        
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(LMI));
        System.out.println(Arrays.toString(Prev));
        
        print(A, Prev, overallLongest);
        
    }
    
    public static void main(String[] args) {
        //int A[] = {1, 3, 2, 5, 4, 7};
        //int A[] = {9, 2, 6, 3, 7, 4, 5, 8, 1};
        int A[] = {5, 7, 3, 8, 6, 7, 8, 9, 1};
        
        find(A, A.length);
        
    }
}
