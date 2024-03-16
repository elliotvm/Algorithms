package elliot.project_3;

import java.util.*;

public class Project_3 {

    static void makeSet(Dominion x, List<Dominion> empire){
        empire.add(x);
        x.parent = x;
        x.rank = 0;
    }
    
        
    static Dominion findSet(Dominion x){//returns the representative dominion
        if(x != x.parent){
            x.parent = findSet(x.parent);
        }
        return x.parent;
    }
    
    static void union(Dominion x, Dominion y, List<Dominion> empire){
        
        Dominion repX = findSet(x);
        Dominion repY = findSet(y);
        
        if(repX.rank >= repY.rank){
            empire.remove(y);
            repY.parent = repX;
            repX.rank += 1;
        }else{
            empire.remove(x);
            repX.parent = repY;
            repY.rank += 1;
        }
    }

    static boolean sameComponent(Dominion u, Dominion v){
        return findSet(u) == findSet(v);
    }
    
    static Dominion returnNeighbor(Dominion galaxy[], int n, int m, int k, int loc, int dir) {
        switch (dir) {
            case 0 -> {
                // Right (n+)
                if (loc % n < n - 1) {
                    return galaxy[loc + 1];
                }
                else return null;
            }
            case 1 -> {
                // Left (n-)
                if (loc % n > 0) {
                    return galaxy[loc - 1];
                }
                else return null;
            }
            case 2 -> {
                // Out (m+)
                if ((loc % (n * m)) < (n * (m - 1))) {
                    return galaxy[loc + n];
                }
                else return null;
            }
            case 3 -> {
                // In (m-)
                if ((loc % (n * m)) > m) {
                    return galaxy[loc - n];
                }
                else return null;
            }
            case 4 -> {
                // Up (k+)
                if (loc < (n * m * (k - 1))) {
                    return galaxy[loc + (m * n)];
                } else {
                    return null;
                }
            }
            case 5 -> {
                // Down (k-)
                if (loc > (m * n)) {
                    return galaxy[loc - (m * n)];
                } else
                    return null;
            }
            default -> {
                return null;
            }
        }
    }
    
    public static void main(String[] args) {
        
        List<Dominion> empire = new LinkedList<>();
        
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int l = in.nextInt();
        
        Dominion galaxy[] = new Dominion[n*m*k];
        //List<Dominion> monarchies;
        Stack<Dominion> allDominions = new Stack<>();
        
        //keep track of inputted sized of monarchy
        Stack<Integer> MonarchySize = new Stack<Integer>();
        
        for (int a = 0; a < l; a++) {
            int p = in.nextInt();
            MonarchySize.push(p);
            for (int b = 0; b < p; b++) {
                int d = in.nextInt();
                Dominion newDom = new Dominion(d);
                allDominions.push(newDom);
            }
        }
        
        int numDisconnectedMonths = 0;
        while (!allDominions.isEmpty()) {
            // Insert dominion
            int p = MonarchySize.pop(); //size of monarchy
            
            for(int i = 0; i < p; i++){
                Dominion d = allDominions.pop();
                makeSet(d, empire);
                
                //check if a dominion of a different set is in there, if yes, union...
                for(int direction = 0; direction < 6; direction++){
                    Dominion neighbor = returnNeighbor(galaxy, n, m, k , d.location, direction);
                    if(neighbor != null){
                        if(!sameComponent(d, neighbor)){
                           union(d, neighbor, empire);
                        }
                    }
                }

            }
            
            if(empire.size() != 1){
                numDisconnectedMonths += 1;
            }
            
            
        }
        
        System.out.println(numDisconnectedMonths);
        
    }
}

//Dominion: dominion parent, position, 
//Monarchy: size, set[]
