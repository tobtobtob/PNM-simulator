/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnm.simulator;

import java.util.ArrayList;
import java.util.List;
import pnm.simulator.algorithms.BMM;
import pnm.simulator.algorithms.VC3;

/**
 *Simple simulator for port-numbering model. Currently graphs have to be given in 
 * hard-coded way. Reading from files is not yet possible.
 * 
 */
public class PNMSimulator {

    
    public static void run(List<Node> nodes){
        
        for (Node node : nodes) {
            node.init();
        }
        int round = 0;
        
        while(!allNodesStopped(nodes)){
            
            System.out.println("round "+round);
            for (Node node : nodes) {
                node.send();
               
            }        
            for (Node node : nodes) {
                node.receive();
                
            }
            printNodes(nodes);
            System.out.println("");
            round++;
        }
    }
    
    public static void main(String[] args) {
        //List<Node> nodes = createBMMNetwork();
        
        List<Node> nodes = createVC3Network();       
        run(nodes);
    }
    
    private static List<Node> createBMMNetwork(){
        List<Node> network = new ArrayList<>();
        Node node1 = new BMM("white", 1);
        Node node2 = new BMM("black", 2);
        Node node3 = new BMM("white", 3);
        Node node4 = new BMM("black", 4);
        Node node5 = new BMM("black", 5);
        
        addConnection(node1, node2);
        addConnection(node1, node4);
        addConnection(node2, node3);
        addConnection(node3, node4);
        addConnection(node3, node5);
        
        network.add(node1);
        network.add(node2);
        network.add(node3);
        network.add(node4);
        network.add(node5);
           
        
        return network;
    }
    private static List<Node> createVC3Network(){
        List<Node> network = new ArrayList<>();
        Node node1 = new VC3("", 1);
        Node node2 = new VC3("", 2);
        Node node3 = new VC3("", 3);
        Node node4 = new VC3("", 4);
        Node node5 = new VC3("", 5);
        Node node6 = new VC3("", 6);
        Node node7 = new VC3("", 7);
        
        addConnection(node1, node2);
        addConnection(node1, node3);
        addConnection(node1, node4);
        addConnection(node1, node5);
        addConnection(node1, node6);
        addConnection(node6, node7);
        
        network.add(node1);
        network.add(node2);
        network.add(node3);
        network.add(node4);
        network.add(node5);
        network.add(node6);
        network.add(node7);
           
        
        return network;
    }
    private static void printNodes(List<Node> nodes){
        for (Node node : nodes) {
            System.out.println(node);
        }
    }

    private static boolean allNodesStopped(List<Node> nodes) {
        
        for (Node node : nodes) {
            if(!node.isStopped()){
                return false;
            }
        }
        return true;
    }
    
    private static void addConnection(Node node1, Node node2){
         Port port1, port2;
         port1 = node1.addPort(node2);
         port2 = node2.addPort(node1);
         port1.setConnectedPort(port2);
         port2.setConnectedPort(port1);
         
    }
}
