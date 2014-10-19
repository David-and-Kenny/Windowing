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
    private boolean [][] selectedCells=new boolean[16][2];
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
    public void clearTable(){
        int column= (p1Turn)? 1 : 2;
        for(int i=0;i<16;i++){
            if(selectedCells[i][column-1]!=true){
                gui.getTable().setValueAt("",i+1,column);
            }
        }
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
    public boolean[][] getCellArray(){
        return this.selectedCells;
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
        int numCount[]={0,0,0,0,0,0,0};
        for(int i=0;i<5;i++){
            numCount[dice[i]]++;
        }
        int column= (p1Turn)? 1 : 2;
        //Score Singles
        for(int index=1;index<7;index++){
            if(selectedCells[index-1][column-1]!=true){
            gui.getTable().setValueAt((index*numCount[index]), index, column);}
        }
      //3 of  akind
        {
        int count=0;
        boolean set=false;
	for (int i=1; i<7; i++){
          if (numCount[i] >= 3)
              set= true;
        }if(set){
		for (int i=1; i<7; i++)
	  	count = count + (i * numCount[i]);
                }
	
          gui.getTable().setValueAt(count, 9, column);
        }
        //4of a kind
        {
        int count=0;
        boolean set=false;
	for (int i=1; i<7; i++){
          if (numCount[i] >= 4)
              set= true;
        }if(set){
		for (int i=1; i<7; i++)
	  	count = count + (i * numCount[i]);
                }
	
          gui.getTable().setValueAt(count, 10, column);
        }
        //Full House
        {
        int arr[]=numCount;
        int isSet=0;
        int score=0;
        for (int i=1; i<7; i++){
	  if (arr[i] >= 3)
		 isSet = i; 
	}
	if (isSet > 0){
		arr[isSet] = arr[isSet] - 3;
		isSet = 0;
		for (int i=1; i<7; i++){
		  if (arr[i] >= 2)
		 	isSet = i; 
		}
		if (isSet > 0)
                    score=25;
		
	}
        gui.getTable().setValueAt(score, 11, column);  
         }
    
    
    
    
    
    
    }
    
}
	
