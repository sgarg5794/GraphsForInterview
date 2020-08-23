package com.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindMST {

    static class Connection{
        char from;
        char to;
        int cost;

        Connection(char f , char t, int w){
            this.from=f;
            this.to=t;
            this.cost=w;
        }

        public int getCost(){
            return this.cost;
        }
        public char getFrom(){
            return this.from;
        }
        public char getTo(){
            return this.to;
        }
    }

public static ArrayList<Connection> minimumCostConnection(int num, ArrayList<Connection> connections){
    ArrayList<Connection> res=new ArrayList<>();

    Collections.sort(connections, Comparator.comparing(Connection::getCost));
    Set<Character> vertices=new HashSet<>();
    for(Connection c:connections){
        vertices.add(c.getFrom());
        vertices.add(c.getTo());
    }
        DisJointSet set=new DisJointSet();

    //create set for each vertex which we will union later based on cost
        for(char  v : vertices){
            set.makeSet((v-'A')+1);
        }
        System.out.println(set.findSet(1));

        for(Connection c:connections){
            long root1=set.findSet((c.getFrom()-'A')+1);
            long root2=set.findSet((c.getTo()-'A')+1);
            if(root1!=root2){
                res.add(c);
                set.union((c.getFrom()-'A')+1,(c.getTo()-'A')+1);
            }
        }
        for(Connection r:res){
            System.out.println(r.getFrom()+"--"+r.getTo()+"--"+r.cost);
        }
        return res;
}

    public static void main(String[] args) {
        FindMST m=new FindMST();
        ArrayList<Connection> input=new ArrayList<>();
        input.add(new Connection('A','B',1));
        input.add(new Connection('B','C',4));
        input.add(new Connection('B','D',6));
        input.add(new Connection('D','E',5));
        input.add(new Connection('C','E',1));
        System.out.println(m.minimumCostConnection(5,input));
    }


}
