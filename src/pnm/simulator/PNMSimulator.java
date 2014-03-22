/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pnm.simulator;

import java.util.ArrayList;
import java.util.List;

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
        // TODO code application logic here
    }
    
    private static List<Node> createNetwork(){
        List<Node> network = new ArrayList<>();
        return network;
    }

    private static boolean allNodesStopped(List<Node> nodes) {
        
        for (Node node : nodes) {
            if(!node.isStopped()){
                return false;
            }
        }
        return true;
    }
}
