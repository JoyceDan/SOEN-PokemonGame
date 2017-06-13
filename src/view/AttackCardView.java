package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Attack;
import model.Card;
import model.Pokemon;

public class AttackCardView extends JPanel implements Coordinate {

	   
     private Pokemon card ;
     private Font smallFont;    // Font that will be used to draw the cards.
     private boolean player;
     private JButton attackButoon;
  
     private JComboBox<Attack> jComboBox ; 
     
     public AttackCardView( boolean player){
    	 this.player = player ; 
    	 smallFont = new Font("SansSerif",Font.PLAIN, 14);
    	 setLayout(null);  

         inti();
	   }
     
     void inti() {
    	 
    	 if(player){
    		 jComboBox = new JComboBox<Attack>();
    		 attackButoon = new JButton("Attack");
	    	
	    	add(jComboBox);
	    	add(attackButoon);
	    	
	    	jComboBox.setBounds(510, 40, 120, 20);
	    	attackButoon.setBounds(510,70, 120, 20);
    	 }
     } 

     public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setFont(smallFont);
         drawCard(g, card, getWidth()/2 - 80 , 10);
     }


 	   /**
 	    * Draws a card as a 80 by 100 rectangle with upper left corner at (x,y).
 	    * The card is drawn in the graphics context g.  If card is null, then
 	    * a face-down card is drawn.  (The cards are rather primitive!)
 	    */
 	   public  void drawCard(Graphics g, Card card, int xCoor, int yCoor) {
 	       if (card == null) {  
 	           // Draw a face-down card
 	           g.setColor(Color.RED);
 	           g.fillRect(xCoor , yCoor,80,100);
 	           g.setColor(Color.WHITE);
 	           g.drawRect(xCoor+3 ,yCoor+3 ,73,93);
 	           g.drawRect(xCoor+4 ,yCoor+4 ,71,91);
 	       }
 	       else {
 	           g.setColor(Color.WHITE);
 	           g.fillRect(xCoor ,yCoor ,80,100);
 	           g.setColor(Color.GRAY);
 	           g.drawRect(xCoor ,yCoor ,79,99);
 	           g.drawRect(xCoor+1 ,yCoor+1  ,77,97);
 	           g.setColor(Color.RED);
 	           g.setFont(smallFont);
 	           g.drawString("Name:", xCoor + 5 , yCoor + 15 );
 	           g.setColor(Color.BLACK);
 	           g.drawString( card.getName(), xCoor + 10 , yCoor + 30 );
 	           g.setColor(Color.RED);
 	           g.drawString("Type:", xCoor + 5, yCoor + 50 );
 	           g.setColor(Color.BLACK);
 	           g.drawString(card.getType().toString(), xCoor + 10, yCoor + 65 );
 	       }
 	   } //

 		public Pokemon getCard() {
 			return card;
 		}

 		public void setCard(Pokemon card) {
 			this.card = card;
 			if(this.card != null && player){
	 			Attack [] attackList = new Attack[card.getAttakcs().size()] ;
	 			this.card.getAttakcs().toArray(attackList);
	 			jComboBox.setModel(new JComboBox<Attack>(attackList).getModel());
 			}
 		}
 		
 	//----------	implements Coordinate

	@Override
	public void refresh() {
		repaint();
	}
	@Override
	public void setBackgroundColor(Color color) {
		setBackground(color);
	}
	@Override
	public void setViewLocation(int x, int y, int width, int height) {
		setBounds(x , y, width , height );
	}

	///--------------------- getter and setter 
	
	
	public boolean isPlayer() {
		return player;
	}

	public void setPlayer(boolean player) {
		this.player = player;
	}

	public JButton getAttackButoon() {
		return attackButoon;
	}

	public void setAttackButoon(JButton attackButoon) {
		this.attackButoon = attackButoon;
	}

	public JComboBox<Attack> getjComboBox() {
		return jComboBox;
	}

	public void setjComboBox(JComboBox<Attack> jComboBox) {
		this.jComboBox = jComboBox;
	}
	

	
}
