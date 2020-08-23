package com.graph;

import java.util.HashMap;
import java.util.Map;

public class DisJointSet {

    class Node{
        long data;
        Node parent;
        int rank;


    }


    // keep a map to store data and its corresponding node
    Map<Long,Node> map=new HashMap<>();

    public void makeSet(long data){
        Node node=new Node();
        node.data=data;
        node.parent=node;
        node.rank=0;
        map.put(data,node);
    }

    //this will return the representative of the node
    public long findSet(long data){
        return findSet(map.get(data)).data;
    }


    public Node findSet(Node node){
        if(node.parent==node) {
            return node;
        }
        node.parent=findSet(node.parent);
        return node.parent;
    }

    //return if union is required or not.
    public boolean union(long data1,long data2){
        Node node1=map.get(data1);
        Node node2=map.get(data2);

        //check if both the nodes have the same parent or not

        Node parent1=findSet(node1);
        Node parent2=findSet(node2);
        if(parent1.data==parent2.data){
            return false;
        }

        if(parent1.rank>=parent2.rank){
            if(parent1.rank==parent2.rank){
                parent1.rank+=1;
            }
            parent2.parent=parent1;
        }else{
            parent1.parent=parent2;
        }
        return true;

    }

    public static void main(String[] args) {
        DisJointSet ds = new DisJointSet();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));
    }

}
