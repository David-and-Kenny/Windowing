/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package yahtzee;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.*;

/*Basically just overwritting the default table renderer so
we can do cool stuff like highlighting certain rows/columns and
change text properties based on other outside factors.
*/

public class RollOverTable extends JTable {
    private Game game;
    private int rollOverRowIndex = -1;
    private int rollOverColumnIndex = -1;
    private Color tableColor = new Color(159, 252, 249);
    //Overrides JTable initializer with our new one
    public RollOverTable(TableModel model, Game g) {
        super(model);
        this.game=g;
        RollOverListener lst = new RollOverListener();
        addMouseMotionListener(lst);
        addMouseListener(lst);
    }
    @Override // makes it where user cant type stuff in
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
    //This is what is actually rendering the table.
    //It renders each cell individually so the logic inside tells how to
    //format the cells
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
       
        if(isRowSelected(row)&&isColumnSelected(column)){
            c.setForeground(Color.RED);
                c.setBackground(Color.YELLOW);
        }
        
        else if((row == rollOverRowIndex)||
            (column == rollOverColumnIndex)){
                c.setForeground(Color.GRAY);
                c.setBackground(tableColor);
            if((row == rollOverRowIndex)&&
            (column == rollOverColumnIndex)){
                c.setForeground(Color.RED);
                c.setBackground(Color.YELLOW);
                
            }
        }
        else {
            if(row>0 && column>0){
            c.setForeground(Color.GRAY);
            c.setBackground(getBackground());}
            else{
                c.setForeground(getForeground());
            c.setBackground(getBackground());}
            }
        
        if(row>0 && column>0){
          if(game.getCellArray()[row-1][column-1]==true){
            c.setForeground(Color.BLACK);
        }  
        }
        if(row==0 || column ==0){
            c.setForeground(Color.BLACK);
        }
        if((row==0 && column==1 && game.getP1Turn())||(row==0 && column==2 && !game.getP1Turn())){
            c.setBackground(Color.RED);
        }
        
        return c;
    }
    
    //Rerenders the table every time the mouse is moved on the table
    private class RollOverListener extends MouseInputAdapter {

        public void mouseExited(MouseEvent e) {
            rollOverRowIndex = -1;
            rollOverColumnIndex = -1;
            repaint();
        }

        public void mouseMoved(MouseEvent e) {
            int row = rowAtPoint(e.getPoint());
            int column= columnAtPoint(e.getPoint());
            if( row != rollOverRowIndex ) {
                rollOverRowIndex = row;
            }
            if(column!= rollOverColumnIndex ) {
                rollOverColumnIndex = column;
            }
            repaint();
        }
    }
}