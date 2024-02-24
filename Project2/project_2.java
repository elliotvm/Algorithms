import java.util.*;

public class Project_2 {
     
    public static void bottomUp(int[] denomination, int[] targets){
        
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
            prev[i] = i - 1;
            
            for(int j = 0; j < denomination.length; j++){
                
                int c = denomination[j];
                
                if(c <= i){                
                    
                    if(table[i - c] + 1 < table[i]){
                        table[i] = table[i - c] + 1;
                        prev[i] = i - c;
                    }
                    
                }
                
            }
        }
        
        //System.out.println(Arrays.toString(table));
        //System.out.println(Arrays.toString(prev));
        /*TODO: Print results
        //keeps track of how many counts of each currency
        int counts[] = new int[denomination.length];
        
        //get and print results
        for(int i = 0; i < targets.length; i++){
            
            //get pointer to target
            int p = targets[i];
            
            System.out.print(targets[i] + " cents = ");
            
            while(p != -1){
                
            }
            
            
        }
        */
    }
    
    public static void main(String[] args) {
        
        //int denomination[] = {1, 7, 17, 23, 37, 37};
        //int target = 21;
        
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
