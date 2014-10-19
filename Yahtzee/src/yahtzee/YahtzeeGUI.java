/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package yahtzee;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author DavidPC
 */
class YahtzeeGUI extends JFrame{
    private JToggleButton dice1;
    private JToggleButton dice2;
    private JToggleButton dice3;
    private JToggleButton dice4;
    private JToggleButton dice5;
    private JTable scorecard;
    private JPanel dicePanel;
    private JPanel scorePanel;
    private JPanel buttonPanel;
    private JLabel logoLabel;
    private JLabel playerInfo;
    private JLabel rollInfo;
    private JButton endTurn;
    private JButton reroll;
    private ImageIcon d1= new ImageIcon(getClass().getResource("/resources/dice1.jpg"));
    private ImageIcon d2= new ImageIcon(getClass().getResource("/resources/dice2.jpg"));
    private ImageIcon d3= new ImageIcon(getClass().getResource("/resources/dice3.jpg"));
    private ImageIcon d4= new ImageIcon(getClass().getResource("/resources/dice4.jpg"));
    private ImageIcon d5= new ImageIcon(getClass().getResource("/resources/dice5.jpg"));
    private ImageIcon d6= new ImageIcon(getClass().getResource("/resources/dice6.jpg"));
    private ImageIcon hd1= new ImageIcon(getClass().getResource("/resources/hdice1.jpg"));
    private ImageIcon hd2= new ImageIcon(getClass().getResource("/resources/hdice2.jpg"));
    private ImageIcon hd3= new ImageIcon(getClass().getResource("/resources/hdice3.jpg"));
    private ImageIcon hd4= new ImageIcon(getClass().getResource("/resources/hdice4.jpg"));
    private ImageIcon hd5= new ImageIcon(getClass().getResource("/resources/hdice5.jpg"));
    private ImageIcon hd6= new ImageIcon(getClass().getResource("/resources/hdice6.jpg"));
    
    
    public YahtzeeGUI(Game g){
        Game game=g;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(425,680);
        getContentPane().setBackground(Color.GREEN);
        ImageIcon logo= new ImageIcon(getClass().getResource("/resources/logo.png"));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);
        dicePanel= new JPanel(new GridLayout(5,1));
        dicePanel.setSize(105,500);
        dicePanel.setBackground(Color.green);
        logoLabel= new JLabel(logo);
        /* contentPane.setLayout(layout);
         contentPane.setSize(300,600);*/
        dice1= new JIconButton();
        dice2= new JIconButton();
        dice3= new JIconButton();
        dice4= new JIconButton();
        dice5= new JIconButton();
        dicePanel.add(dice1);
        dicePanel.add(dice2);
        dicePanel.add(dice3);
        dicePanel.add(dice4);
        dicePanel.add(dice5);
        playerInfo=new JLabel("Player 1's turn");
        rollInfo=new JLabel("Rolls Remaining: 3");
        scorePanel= new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setSize(200,500);
        scorePanel.setBackground(Color.GREEN);
        DefaultTableModel model = new DefaultTableModel(new Object[][] { {"", "Player 1", "Player 2"},
                                { "Ones", "", ""},
                                { "Twos", "", ""}, 
                                { "Threes", "", ""},
                                { "Fours", "", ""},
                                { "Fives", "", ""},
                                { "Sixes", "", ""},
                                { "Sum", "", ""},
                                { "Bonus", "", ""},
                                { "Three of a kind", "", ""},
                                { "Four of a kind", "", ""},
                                { "Full House", "", ""},
                                { "Small Straight", "", ""},
                                { "Large Straight", "", ""},
                                { "Chance", "", ""},
                                { "YAHTZEE", "", ""},
                                { "TOTAL", "", ""},},
      new Object[]{ "", "", ""});
        scorecard= new RollOverTable(model,game);
        scorecard.setSize(200,500);
        scorecard.setRowHeight(20);
        scorecard.getColumnModel().getColumn(1).setPreferredWidth(40);
        scorecard.getColumnModel().getColumn(2).setPreferredWidth(40);
        scorecard.setFont(new Font("Serif", Font.BOLD, 14));
        scorePanel.add(scorecard);
        scorePanel.add(logoLabel);
        scorePanel.add(playerInfo);
        scorePanel.add(rollInfo);
        buttonPanel= new JPanel();
        buttonPanel.setSize(300,100);
        buttonPanel.setBackground(Color.green);
        endTurn=new JButton("End Turn");
        reroll= new JButton("Roll");
        ActionListener roll= new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(game.getRolls()){
                    game.minusRoll();
                    scrambleDice(game);
               // rollDice(game);
               // game.populateTable();
               }}
        };
        ActionListener end= new ActionListener(){
            public void actionPerformed(ActionEvent e){
            game.resetRolls();
            if(game.getP1Turn()){
                p2Turn(game);
            }else{
                p1Turn(game);
            }
            }
        };
        scorecard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              if (e.getClickCount() == 1) {
                JTable target = (JTable)e.getSource();
                int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
                if(row>0&&column>0){
                boolean a=confirmation();
                if(a){
                    game.getCellArray()[row-1][column-1]=true;
                    scorecard.getSelectionModel().clearSelection();
                    scorecard.repaint();
                    game.clearTable();
                    game.resetRolls();
                    if(game.getP1Turn()){
                        p2Turn(game);
                    }else{
                        p1Turn(game);
                    }
                }
                else System.out.println(false);
              }}
            }
          });
        reroll.addActionListener(roll);
        endTurn.addActionListener(end);
        buttonPanel.add(endTurn);
        buttonPanel.add(reroll);
       // logoPanel.add(logo);
        c.insets = new Insets(10,10,10,10);
        c.weightx = 1;
         c.weighty = 1;
        c.gridx=0;
        c.gridy=0;
        c.anchor = GridBagConstraints.NORTHWEST;
        this.add(dicePanel,c);
        c.gridx=1;
        c.gridy=0;
        c.anchor = GridBagConstraints.NORTHEAST;
        this.add(scorePanel,c);
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        c.anchor = GridBagConstraints.SOUTH;
        this.add(buttonPanel,c);
        //this.setContentPane(contentPane);
         ItemListener changeColor=new ItemListener(){
               public void itemStateChanged(ItemEvent ie){
                     int state= ie.getStateChange();
                     if(state==ie.SELECTED){
                     highlight(ie);
                        //(JToggleButton) ie.getSource().setBackground(Color.RED);
                     }else if(state==ie.DESELECTED){
                        unhighlight(ie);
                     }
                 }};
         dice1.addItemListener(changeColor);
         dice2.addItemListener(changeColor);
         dice3.addItemListener(changeColor);
         dice4.addItemListener(changeColor);
         dice5.addItemListener(changeColor);
    }
    public void paintDice(int num,JToggleButton b){
        switch(num){
            case 1:b.setIcon(d1);break;
            case 2:b.setIcon(d2);break;
            case 3:b.setIcon(d3);break;
            case 4:b.setIcon(d4);break;
            case 5:b.setIcon(d5);break;
            case 6:b.setIcon(d6);break;
            default:break;
        }
       
    }
    public boolean confirmation(){
         int answer = JOptionPane.showConfirmDialog(new JFrame(), "are you sure");
            if (answer == JOptionPane.YES_OPTION) {
              return true;
            } else if (answer == JOptionPane.NO_OPTION) {
              return false;
            }
    return false;
    }
    public void setRollInfo(String str){
         this.rollInfo.setText(str);
    }
    public JToggleButton get1(){
        return this.dice1;
    }
    public JToggleButton get2(){
        return this.dice2;
    }
    public JToggleButton get3(){
        return this.dice3;
    }
    public JToggleButton get4(){
        return this.dice4;
    }
    public JToggleButton get5(){
        return this.dice5;
    }
    public void set1(int num){
        paintDice(num,this.dice1);
    }
    public void set2(int num){
        paintDice(num,this.dice2);
    }
    public void set3(int num){
       paintDice(num,this.dice3);
    }
    public void set4(int num){
        paintDice(num,this.dice4);
    }
    public void set5(int num){
        paintDice(num,this.dice5);
    }
    public void highlight(ItemEvent ie){
        JToggleButton jtb=(JToggleButton) ie.getSource();
        if(jtb.getIcon()==d1)jtb.setIcon(hd1);
        if(jtb.getIcon()==d2)jtb.setIcon(hd2);
        if(jtb.getIcon()==d3)jtb.setIcon(hd3);
        if(jtb.getIcon()==d4)jtb.setIcon(hd4);
        if(jtb.getIcon()==d5)jtb.setIcon(hd5);
        if(jtb.getIcon()==d6)jtb.setIcon(hd6);   
    }
     public void unhighlight(ItemEvent ie){
        JToggleButton jtb=(JToggleButton) ie.getSource();
        if(jtb.getIcon()==hd1)jtb.setIcon(d1);
        if(jtb.getIcon()==hd2)jtb.setIcon(d2);
        if(jtb.getIcon()==hd3)jtb.setIcon(d3);
        if(jtb.getIcon()==hd4)jtb.setIcon(d4);
        if(jtb.getIcon()==hd5)jtb.setIcon(d5);
        if(jtb.getIcon()==hd6)jtb.setIcon(d6);   
    }
    public void p1Turn(Game g){
        g.setP1Turn(true);
        unselectAll();
        rollDice(g);
        g.populateTable();
        playerInfo.setText("Player 1's turn");
    }
    public void p2Turn(Game g){
      g.setP1Turn(false); 
      unselectAll();
      rollDice(g);
      g.populateTable();
        playerInfo.setText("Player 2's turn");
    }
    private void unselectAll(){
        dice1.setSelected(false);
        dice2.setSelected(false);
        dice3.setSelected(false);
        dice4.setSelected(false);
        dice5.setSelected(false);
    }
    public void rollDice(Game game){
        
                game.randomDice();
               int arr[]= game.getDice();
               if(!dice1.isSelected()){set1(arr[0]);}
               if(!dice2.isSelected()){set2(arr[1]);}
               if(!dice3.isSelected()){set3(arr[2]);}
               if(!dice4.isSelected()){set4(arr[3]);}
               if(!dice5.isSelected()){set5(arr[4]);}
    }
    public JTable getTable(){
        return this.scorecard;
    }
    private void scrambleDice(Game g){
      Thread t = new Thread()
{
     public void run()
     {
      for(int i=0;i<15;i++){
          rollDice(g);
          try {
    Thread.sleep(20);
} catch (Exception e) {
    e.printStackTrace();
}
          
      }
      g.populateTable();    
     }
};
t.start();

    }
    private static final class JIconButton extends JToggleButton{
        private static final long serialVersionUID = 7274140930080397481L;

        public JIconButton(){
            Dimension d= new Dimension(105,105);
            //super(UIManager.getIcon("OptionPane.informationIcon"));
            setMinimumSize(d);
            setMaximumSize(d);
            setPreferredSize(d);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder());
        }
    }
}

