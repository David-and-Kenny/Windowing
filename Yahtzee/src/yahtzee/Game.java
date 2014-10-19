/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package yahtzee;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 *
 * @author DavidPC
 */
class Game {
    private int rollsLeft=3;
    //private boolean GameOver=false;
    private int dice[]={1,2,3,4,5};
   private boolean p1Turn;
   private boolean p2Turn;
   private boolean scoreSelected=false;
    YahtzeeGUI gui;
    public Game(){
    gui= new YahtzeeGUI(this);
      gui.setMinimumSize(new Dimension(425, 650));
      gui.setVisible(true);
      gui.rollDice(this);
     p1Turn=true;
     p2Turn=false;
    
}
    public int[] getDice(){
        return this.dice;
    }
    public void randomDice(){
       boolean arr[]={this.gui.get1().isSelected(),
                      this.gui.get2().isSelected(),
                      this.gui.get3().isSelected(),
                      this.gui.get4().isSelected(),
                      this.gui.get5().isSelected(),};
        for(int i=0;i<5;i++){
            if(!arr[i]){
            int a=(int)((Math.random() * 6) + 1);
            this.dice[i]=a;}
         }
    }
    
    public void minusRoll(){
        this.rollsLeft--;
        this.gui.setRollInfo("Rolls Remaining: "+rollsLeft);
    }
    public void resetRolls(){
        this.rollsLeft=3;
        this.gui.setRollInfo("Rolls Remaining: 3");
    }
    public boolean getRolls(){
        if(this.rollsLeft==0){
            return false;
        }else return true;
    }
   public void setP1Turn(boolean a){
        this.p1Turn=a;
    }
    public void setP2Turn(boolean a){
        this.p2Turn=a;
    }
    public boolean getP1Turn(){
        return this.p1Turn;
    }
    public boolean getP2Turn(){
        return this.p2Turn;
    }
    public void populateTable(){
         ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int index = 0; index < dice.length; index++)
            {
                temp.add(dice[index]);
            }
            System.out.println(temp);
        int column= (p1Turn)? 1 : 2;
        //Score Singles
        for(int i=1;i<=6;i++){
        if(temp.contains(i)){
            int count=0;
            for(int num: temp){
                if(num==i){count+=i;}
            }
        gui.getTable().setValueAt(count, i, column);
    }else gui.getTable().setValueAt(0, i, column);}
    }
}

