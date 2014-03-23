/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pnm.simulator.algorithms;

import java.util.ArrayList;
import java.util.List;
import pnm.simulator.Node;
import pnm.simulator.Port;

/**
 * algorithm vc3 simulates two nodes running BMM. The computation is done 
 * by BMM algorithm, this code just transfers messages between simulated nodes and
 * real ndoes in the network.
 * 
 * Messages sent are encoded as follows: 'white message':'black message'
 * 
 * @author topi
 */
public class VC3 extends Node {
    
    private BMM black;
    private BMM white;
    private List<Port> blackPorts;
    private List<Port> whitePorts;

    @Override
    public void init() {
        black = new BMM("black", 0);
        white = new BMM("white", 1);
        
        Port portInWhite, portInBlack, portToWhite, portToBlack;
        blackPorts = new ArrayList<>();
        whitePorts = new ArrayList<>();
        
        for (int i = 0; i < degree(); i++) {
            
            portInWhite = white.addPort(null);
            portInBlack = black.addPort(null);
            
            portToWhite = new Port(null);
            portToBlack = new Port(null);
            
            portInWhite.setConnectedPort(portToWhite);
            portInBlack.setConnectedPort(portToBlack);
        
            portToWhite.setConnectedPort(portInWhite);
            portToBlack.setConnectedPort(portInBlack);
            
            whitePorts.add(portToWhite);
            blackPorts.add(portToBlack);
        }
        black.init();
        white.init();
    }

    @Override
    public void send() {
        white.send();
        black.send();
        List<String> whiteMessages = getMessages(whitePorts);
        List<String> blackMessages = getMessages(blackPorts);
        String message;
        for (int i = 0; i < degree(); i++) {
            message = whiteMessages.get(i)+":"+blackMessages.get(i);
            sendMessage(message, i);
        }
    }

    public VC3(String input, int id) {
        super(input, id);
    }

    @Override
    public void receive() {
        
        List<String> receivedMessages = receiveMessages();
        for (int i = 0; i < degree(); i++) {
            String[] messages = receivedMessages.get(i).split(":");
            //message from a white node is given to black node and vice versa
            whitePorts.get(i).sendMessage(messages[1]);
            blackPorts.get(i).sendMessage(messages[0]);
        }
        white.receive();
        black.receive();
        
        if(white.isStopped() && black.isStopped()){
            setOutput();
            stopped = true;
        }
    }
    
    private List<String> getMessages(List<Port> ports){
        List<String> messages = new ArrayList<>();
        for (Port port : ports) {
            messages.add(port.getReceivedMessage());
        }
        return messages;
    }

    private void setOutput() {
        if(white.getOutput().contains("1") || black.getOutput().contains("1")){
            output = "1";
        }
        else{
            output = "0";
        }
    }
 
   
    

}
