package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.Card;
import model.Deck;
import model.CardCollection;


public class HandView extends JPanel implements Coordinate {


	private CardCollection<Card> cardCollection;       // The cards that have been dealt.
	private int x ;
	private int y ;
    Font smallFont;    // Font that will be used to draw the cards.
    
     boolean player;

    /**
     * Constructor creates fonts, sets the foreground and background
     * colors and starts the first game.  It also sets a "preferred
     * size" for the panel.  This size is respected when the program
     * is run as an application, since the pack() method is used to
     * set the size of the window.
     */
	   public HandView(boolean player) { 
		this.player = player;
        smallFont = new Font("SansSerif", Font.PLAIN, 14);
    } 



    
    /**
     * This method draws the message at the bottom of the
     * panel, and it draws all of the dealt cards spread out
     * across the canvas.  If the game is in progress, an extra
     * card is drawn face down representing the card to be dealt next.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(smallFont);
        
        if(cardCollection != null){
        	int cardCt = cardCollection.getCardCount();
        	for (int i = 0; i < cardCt; i++){
        		if(player)	// do not show card if not player 
        			drawCard(g, cardCollection.getCard(i), 10 +  (i + 2) * 90, 10);
        		else
        			drawCard(g, cardCollection.getCard(i), 10 +  (i + 2) * 90, 10);//TODO cardCollection.getCard(i) should be null
        		}
        	}
        // the deck view 
        drawCard(g, null, 10 + 10 * 90, 10);
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
	           g.fillRect(xCoor , yCoor ,80,100);
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


	public CardCollection<Card> getCardCollection() {
		return cardCollection;
	}

	public void setCardCollection(CardCollection cardCollection) {
		this.cardCollection = cardCollection;
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
} 
