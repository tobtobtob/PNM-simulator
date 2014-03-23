/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pnm.simulator.algorithms;

import java.util.List;
import pnm.simulator.Node;


public class BMM extends Node {
    
    private int round;
    private boolean matched;
    
    //these arrays are only initialised if the node is black
    private boolean[] matchedNeighbours;
    private boolean[] proposals;
    

    public BMM(String input) {
        super(input);
    }

    @Override
    public void init() {
        matched = false;
        round = 0;
        if("black".equals(super.input)){
            matchedNeighbours = new boolean[degree()];
            proposals = new boolean[degree()];
        }
       
    }

    @Override
    public void send() {
        
        //first an empty message is sent to each port. If the node sends another
        //message, the empty message will be overwritten
        sendAll("");
        
        if(stopped){
            return;
        }
        
        if(round % 2 == 0){
            
            if("white".equals(super.input)){
                if(matched){
                    sendAll("matched");
                    stopped = true;
                }
                else if(round >= degree()){
                    stopped = true;
                            
                }
                else{
                    sendMessage("proposal", round);
                }
            }
        }
        else{
            if("black".equals(super.input)){
                if(allNeighboursMatched()){
                    stopped = true;
                }
                for (int i = 0; i < degree(); i++) {
                    if(proposals[i]){
                        sendMessage("accept", i);
                        stopped = true;
                    }
                }
            }
        }
        round++;
    }

    @Override
    public void receive() {
        
        if(stopped){
            return;
        }
        if(round % 2 == 0){
            if("black".equals(super.input)){
                List<String> messages = receiveMessages();
                for (int i = 0; i < degree(); i++) {
                    if("matched".equals(messages.get(i))){
                        matchedNeighbours[i] = true;
                    }
                    if("proposal".equals(messages.get(i))){
                        proposals[i] = true;
                    }
                }
            }
        }
        else{
            if("white".equals(super.input)){
                List<String> messages = receiveMessages();
                for (int i = 0; i < degree(); i++) {
                    if("accept".equals(messages.get(i))){
                        matched = true;
                    }
                }
            }
        }
        
    }

    private boolean allNeighboursMatched() {
       for (int i = 0; i < degree(); i++) {
            if(!matchedNeighbours[i]){
                 return false;
            }
        }
       return true;
    }

}
