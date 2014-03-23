/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnm.simulator;

import java.util.ArrayList;
import java.util.List;
import pnm.simulator.algorithms.BMM;

/**
 *
 * @author topi
 */
public class PNMSimulator {

    
    public static void run(List<Node> nodes){
        
        for (Node node : nodes) {
            node.init();
        }
        
        while(!allNodesStopped(nodes)){
            
            for (Node node : nodes) {
                node.send();
            }
            for (Node node : nodes) {
                node.receive();
            }
        }
    }
    
    public static void main(String[] args) {
        List<Node> nodes = createNetwork();
        run(nodes);
        printNodes(nodes);
    }
    
    private static List<Node> createNetwork(){
        List<Node> network = new ArrayList<>();
        Node node1 = new BMM("white");
        Node node2 = new BMM("black");
        Node node3 = new BMM("white");
        Node node4 = new BMM("black");
        Node node5 = new BMM("black");
        
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
