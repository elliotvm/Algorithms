package com.mycompany.project_3;

import java.util.*;

public class Project_3 {

    
    void makeSet(Dominion x, List<Dominion> empire){
        empire.add(x);
        x.parent = x;
        x.rank = 0;
    }
    
        
    Dominion findSet(Dominion x){//returns the representative dominion
        if(x != x.parent){
            x.parent = findSet(x.parent);
        }
        return x.parent;
    }
    
    void union(Dominion x, Dominion y, List<Dominion> empire){
        
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

    boolean sameComponent(Dominion u, Dominion v){
        return findSet(u) == findSet(v);
    }
    
    void connectedComponents(Dominion u, Dominion v){
        
    }

    Dominion returnNeighbor(Dominion galaxy[], int n, int m, int k, int loc, int dir) {
        switch (dir) {
            case 0: // Right (n+)
                if ((loc % k) % m < n - 1) {
                    return galaxy[loc + 1];
                }
                else return null;
            case 1: // Left (n-)
                if ((loc % k) % m > 0) {
                    return galaxy[loc - 1];
                }
                else return null;
            case 2: // Out (m+)
                if ((loc % k) % n < m - 1) {
                    return galaxy[loc + n];
                }
                else return null;
            case 3: // In (m-)
                if ((loc % k) % n > 0) {
                    return galaxy[loc - n];
                }
                else return null;
            case 4: // Up (k+)
                if ((loc % m) % n < k - 1) {
                    return galaxy[loc + (m * n)];
                } else {
                    return null;
                }
            case 5: // Down (k-)
                if ((loc % m) % n > 0) {
                    return galaxy[loc - (m * n)];
                } else
                    return null;
            default:
                return null;
        }
    }
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int l = in.nextInt();
        
        Dominion galaxy[] = new Dominion[n*m*k];
        List<Dominion> monarchies;
        Stack allDominions = new Stack();
        
        for (int a = 0; a < l; a++) {
            int p = in.nextInt();
            
            for (int b = 0; b < p; b++) {
                int d = in.nextInt();
                Dominion newDom = new Dominion(d);
                allDominions.push(newDom);
            }
        }
        
        int numMonths = 0;
        while (!allDominions.isEmpty()) {
            // Insert dominion
            // Evaluate dominion
        }
        
    }
}
