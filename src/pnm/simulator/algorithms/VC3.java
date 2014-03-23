/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pnm.simulator.algorithms;

import java.util.ArrayList;
import java.util.List;
import pnm.simulator.Node;
import pnm.simulator.Port;


public class VC3 extends Node {
    
    private BMM black;
    private BMM white;
    private List<Port> blackPorts;
    private List<Port> whitePorts;

    @Override
    public void init() {
        black = new BMM("black");
        white = new BMM("white");
        black.init();
        white.init();
        
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

    public VC3(String input) {
        super(input);
    }

    @Override
    public void receive() {
        List<String> receivedMessages = receiveMessages();
        for (String string : receivedMessages) {
            
        }
    }
    
    private List<String> getMessages(List<Port> ports){
        List<String> messages = new ArrayList<>();
        for (Port port : ports) {
            messages.add(port.getReceivedMessage());
        }
        return messages;
    }
   
    

}
