/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pnm.simulator;

import java.util.ArrayList;
import java.util.List;


public abstract class Node {
    
    //these variables can be read and modified by algorithms
    protected String input;
    protected String output;
    protected boolean stopped;
    
    private List<Port> ports;

    
    
    public Node(String input){
        this.input = input;
        ports = new ArrayList<>();
    }
    
    
    public abstract void init();
    
    public abstract void send();
    
    public abstract void receive();
    
//    public void sendMessages(List<String> messages){
//        
//        //this assumes that algorithm sends some message to each port.
//        for (int i = 0; i < ports.size(); i++) {
//            ports.get(i).sendMessage(messages.get(i));
//        }
//    }
    public void sendMessage(String message, int portNumber){
        ports.get(portNumber).sendMessage(message);
    }
    
    public void sendAll(String message){
        for (Port port : ports) {
            port.sendMessage(message);
        }
    }
    
    public List<String> receiveMessages(){
        
        List<String> messages = new ArrayList<>();
        for (Port port : ports) {
            messages.add(port.getReceivedMessage());
        }
        
        return messages;
    }
    public Port addPort(Node connectedNode){
        Port port = new Port();
        ports.add(port);
        return port;
    }
    public boolean isStopped(){
        return stopped;
    }
    public int degree(){
        return ports.size();
    }
}
