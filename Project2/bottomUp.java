import java.util.*;

public class bottomUp {
    
    public static void printResult(int[] denomination, int[] targets, int[] prev, long startTime){
        
        for(int i = 0; i < targets.length; i++){
            int counts[] = new int[denomination.length];
            
            //get pointer to target
            int p = targets[i];
            
            System.out.print(targets[i] + " cents = ");
            
            while(prev[p] != -1){
                int c = prev[p];
                
                for(int j = 0; j < denomination.length; j++){
                    if(denomination[j] == c){
                        counts[j]++;
                    }
                }
                
                p = p - c;
            }   
            
            for(int k = denomination.length - 1; k >= 0; k--){
                if(counts[k] != 0){
                    System.out.print(denomination[k] + ":" + counts[k] + " ");
                }
            }
            System.out.print("\n");
            
            //calculate time
            System.out.println(System.nanoTime() - startTime);
            
        }
    }
    
     
    public static void bottomUp(int[] denomination, int[] targets){
        
        //Calculate time
        long startTime = System.nanoTime();
        
        int size = 0;
        
        //find biggest target and use that to find and fill table
        for(int i = 0; i < targets.length; i++){
            if(targets[i] > size){
                size = targets[i];
            }
        }
        
        size++; //account for 0
        
        //holds least amount of coins necessary to get to index i
        int table[] = new int[size];
        
        //holds pointer to previous value in least coin possible
        int prev[] = new int[size];
        prev[0] = -1;
 
        for(int i = 1; i < size; i++){
            
            //fill original value with +1 cent (we assume this will always be in denomination);
            table[i] = table[i - 1] + 1;
            prev[i] = 1;
            
            for(int j = 0; j < denomination.length; j++){
                
                int c = denomination[j];
                
                if(c <= i){                
                    
                    if(table[i - c] + 1 < table[i]){
                        table[i] = table[i - c] + 1;
                        //prev[i] = i - c;
                        prev[i] = c;
                    }
                    
                }
                
            }
        }
        
        printResult(denomination, targets, prev, startTime);

    }
    
    public static void main(String[] args) {

        //parse input
        Scanner in = new Scanner(System.in);

        int size = in.nextInt();
        int denomination[] = new int[size];
            
        for(int i = 0; i < size; i++){
            denomination[i] = in.nextInt();
        }
        
        size = in.nextInt();
        int targets[] = new int[size];
        
        for(int i = 0; i < size; i++){
            targets[i] = in.nextInt();
        }

        bottomUp(denomination, targets);
    }
}
