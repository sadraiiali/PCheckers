/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcheckers;

import java.util.ArrayList;

/**
 *
 * @author piker
 */
public class AITreeNode {
    AITreeNode parent;
    ArrayList<AITreeNode> childrens ;
    int alpha;
    int beta;
    Boolean MaxOrMin ;
    public AITreeNode(AITreeNode parent) {
        this.parent = parent;
    }
    
}
