package com.mycompany.project_3;

import java.util.*;

public class Project_3 {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int l = in.nextInt();
        
        Dominion galaxy[] = new Dominion()[n*m*k];
        Vector monarchies = new Vector();
        
        for (int a = 0; a < l; a++) {
            int p = in.nextInt();
            
            int d1 = in.nextInt();
            Dominion rep = new Dominion(d1);
            rep.setParent(rep);
            galaxy[d1] = rep;
            monarchies.add(rep);
                        
            for (int b = 1; b < p; b++) {
                int d = in.nextInt();
                Dominion newDom = new Dominion(d);
                newDom.setParent(rep);
                galaxy[d] = newDom;
            }
        }
        
    }
}
