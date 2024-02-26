import java.util.*;

public class memoRecursive {

    public static void printResultRecursive(int[] denomination, Node head, long startTime) {
        int counts[] = new int[denomination.length];
        //System.out.print(head.value + " cents = ");

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
                //System.out.print(denomination[k] + ":" + counts[k] + " ");
            }
        }
        //System.out.println();
        
        System.out.println(System.nanoTime() - startTime);
    }

    public static void memoRecursive( int[] denomination, int[] targets) {
        
        long startTime = System.nanoTime();
        
        int size = 0;
        //find biggest target and use that to find and fill table
        for(int i = 0; i < targets.length; i++){
            if(targets[i] > size){
                size = targets[i];
            }
        }
        
        Node table[] = new Node[targets.length];
        Node allNodes[] = new Node[size + 1];
        
        for (int i = 0; i < targets.length; i++) {
            Node prev = memoRecursiveCall(denomination, targets[i], allNodes);
            table[i] = new Node(prev.numCoins + 1, targets[i], prev);
            printResultRecursive(denomination, table[i], startTime);
        }
    }
    
    public static Node memoRecursiveCall( int[] denomination, int target, Node[] allNodes) {
        Node bestNode = new Node(10000, 0, null);
        for (int j = denomination.length - 1; j >= 0; j--) {
            
            if (target - denomination[j] == 0) {
                bestNode = new Node(0, 0, null);
            }
            else if (target - denomination[j] < 0) {
            }
            else {
                Node currNode;
                if (allNodes[target - denomination[j]] != null) {
                    currNode = new Node(allNodes[target - denomination[j]]);
                }
                else {
                    currNode = memoRecursiveCall(denomination, target - denomination[j], allNodes);
                    currNode.prevNode = new Node(currNode);
                    currNode.numCoins++;
                    currNode.value = target - denomination[j];
                }
                
                if (currNode.numCoins < bestNode.numCoins) {
                    bestNode = currNode;
                }
            }
        }
        allNodes[bestNode.value] = new Node(bestNode);
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

        memoRecursive(denomination, targets);
    }
}
