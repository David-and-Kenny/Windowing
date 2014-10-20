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
    private int dice[]={1,2,3,4,5};
   private boolean p1Turn;//keeps track of whose turn it is
   private boolean scoreSelected=false;
    YahtzeeGUI gui;
    public Game(){
    gui= new YahtzeeGUI(this);
      gui.setMinimumSize(new Dimension(425, 650));
      gui.setVisible(true);
      gui.rollDice(this);//initial dice roll
     p1Turn=true;
    
}   //returns the dice array
    public int[] getDice(){
        return this.dice;
    }//clears cells that arent "set" after a turn is over
    public void clearTable(){
        int column= (p1Turn)? 1 : 2;
        for(int i=0;i<16;i++){
            if(selectedCells[i][column-1]!=true){
                gui.getTable().setValueAt("",i+1,column);
            }
        }
    }//rolls the dice
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
    }//returns the boolean 2d array; used in gui and table renderer
    public boolean[][] getCellArray(){
        return this.selectedCells;
    }//decrement roll
    public void minusRoll(){
        this.rollsLeft--;
        this.gui.setRollInfo("Rolls Remaining: "+rollsLeft);
    }//resets the roll count to 3
    public void resetRolls(){
        this.rollsLeft=3;
        this.gui.setRollInfo("Rolls Remaining: 3");
    }//checks if user had any rolls left
    public boolean getRolls(){
        if(this.rollsLeft==0){
            return false;
        }else return true;
    }
   public void setP1Turn(boolean a){
        this.p1Turn=a;
    }
    public boolean getP1Turn(){
        return this.p1Turn;
    }//populates the scorecard with possible values that the user can choose
    public void populateTable(){
        int numCount[]={0,0,0,0,0,0,0};
        for(int i=0;i<5;i++){
            numCount[dice[i]]++;
        }
        int column= (p1Turn)? 1 : 2;
        //Score Singles
        for(int index=1;index<7;index++){
            if(selectedCells[index-1][column-1]!=true&&numCount[index]!=0){
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
	
          if(count!=0)gui.getTable().setValueAt(count, 9, column);
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
	
          if(count!=0)gui.getTable().setValueAt(count, 10, column);
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
        if(score!=0)gui.getTable().setValueAt(score, 11, column);  
         }
    
    
    
    
    
    
    }
    
}
	
