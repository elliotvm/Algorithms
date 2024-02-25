package com.mycompany.project_2;

import java.util.*;

public class Project_2 {
    
    public static void printResult(int[] denomination, int[] targets, int[] prev){
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
        }
    }
    
    public static void printResultRecursive(int[] denomination, Node head) {
        int counts[] = new int[denomination.length];
        System.out.print(head.value + " cents = ");

        while (head.prevNode != null) {
            for(int j = 0; j < denomination.length; j++){
                if(denomination[j] == head.value - head.prevNode.value){
                    counts[j]++;
                }
            }
            head = head.prevNode;
        }
        
        for (int k = denomination.length - 1; k >= 0; k--) {
            if (counts[k] != 0) {
                System.out.print(denomination[k] + ":" + counts[k] + " ");
            }
        }
        System.out.println();
    }
     
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
        
        printResult(denomination, targets, prev);

    }
    
    public static void pureRecursive( int[] denomination, int[] targets) {
        Node table[] = new Node[targets.length];
        
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] < 100) {
                Node prev = pureRecursiveCall(denomination, targets[i]);
                table[i] = new Node(prev.numCoins + 1, targets[i], prev);
                printResultRecursive(denomination, table[i]);
            }
        }
    }
    
    public static Node pureRecursiveCall( int[] denomination, int target) {
        Node bestNode = new Node(10000, 0, null);
        for (int j = denomination.length - 1; j >= 0; j--) {
            if (target - denomination[j] == 0) {
                bestNode = new Node(0, 0, null);
            }
            else if (target - denomination[j] < 0) {
            }
            else {
                Node currNode = pureRecursiveCall(denomination, target - denomination[j]);
                currNode.prevNode = new Node(currNode);
                currNode.numCoins++;
                currNode.value = target - denomination[j];
                if (currNode.numCoins < bestNode.numCoins) {
                    bestNode = currNode;
                }
            }
        }
        //System.out.println(bestNode.numCoins + " " + bestNode.value);
        return bestNode;
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

        pureRecursive(denomination, targets);
        bottomUp(denomination, targets);
    }
}
