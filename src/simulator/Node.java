/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator;

import java.util.ArrayList;
import java.util.List;


public abstract class Node {
    
    //these variables can be read and modified by algorithms
    protected String input;
    protected String output;
    protected boolean stopped;

    public String getOutput() {
        return output;
    }
    
    //list of ports is not usable by algorithms
    private List<Port> ports;
    
    //definitely not usable by algorithms! only for printing purposes
    private int id;

    
    
    public Node(String input, int id){
        this.input = input;
        this.id = id;
        ports = new ArrayList<>();
    }
    
    
    public abstract void init();
    
    public abstract void send();
    
    public abstract void receive();

    
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
        Port port = new Port(connectedNode);
        ports.add(port);
        return port;
    }
    public boolean isStopped(){
        return stopped;
    }
    public int degree(){
        return ports.size();
    }
    public int getId(){
        return id;
    }
    @Override
    public String toString(){
        String ret = "node "+this.id+" input: "+input+" output: "+output;
        if(stopped){
            ret += " STOPPED ";
        }
        for (int i = 0; i < ports.size(); i++) {
            ret += " [port "+i+":"+ports.get(i) + "] ";
        }
        return ret;
    }
}
