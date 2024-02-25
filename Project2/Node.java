package com.mycompany.project_2;

public class Node {
    public int numCoins, value;
    public Node prevNode;
    
    Node(int num, int val, Node prev) {
        numCoins = num;
        value = val;
        prevNode = prev;
    }
    Node(Node node) {
        this(node.numCoins, node.value, node.prevNode);
    }
}
