/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simulator;


public class Port {
    
    private Port connectedPort;
    private Node connectedNode;

    private String receivedMessage;
    
    public Port(Node connectedNode) {
        this.connectedNode = connectedNode;
        
    }
    
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
    
    @Override
    public String toString(){
        return "to node "+connectedNode.getId();
    }
    
}
