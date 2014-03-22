/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pnm.simulator;


public class Port {
    
    private Port connectedPort;
    private String receivedMessage;
    
    public void sendMessage(String message){
        connectedPort.setReceivedMessage(message);
    }
    
    public String getReceivedMessage(){
        return receivedMessage;
    }
    
    public void setConnectedPort(Port connectedPort) {
        this.connectedPort = connectedPort;
    }

    public void setReceivedMessage(String receivedMessage) {
        this.receivedMessage = receivedMessage;
    }
    
}
