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
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int l = in.nextInt();
        
        Dominion galaxy[] = new Dominion()[n*m*k];
        List monarchies = new List();
        Stack allDominions = new Stack();
        
        for (int a = 0; a < l; a++) {
            int p = in.nextInt();
            
            int d1 = in.nextInt();
            Dominion rep = new Dominion(d1);
            rep.setParent(rep);
            monarchies.add(rep);
                        
            for (int b = 1; b < p; b++) {
                int d = in.nextInt();
                Dominion newDom = new Dominion(d);
                newDom.setParent(rep);
                allDominions.push(newDom);
            }
            
            allDominions.push(rep);
        }
        
        int numMonths = 0;
        while (!allDominions.isEmpty()) {
            // Insert dominion
            // Evaluate dominion
            // If next dominion is rep, evaluate connectedness
        }
        
    }
}
