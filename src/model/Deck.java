package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import model.Card.PokmonType;

/**
 *  An object of type Deck represents a deck of playing cards.
 */
public class Deck {
	public HashMap<Integer, Card> hm = new HashMap<Integer, Card>();
	
	int hashkey = 0;
	
	public void addToHash (Card card) {
		hm.put(hashkey, card);
		hashkey++;
	}
	
    /**
     * An array of 60 cards. 
     */
    private Card[] deck;
    
    private int cardNum;

    /**
     * Keeps track of the number of cards that have been dealt from
     * the deck so far.
     */
    private int cardsUsed;

    /**
     * Constructs a 
 	The shuffle() method can be called to
     * randomize the order.  (Note that "new Deck()" is equivalent
     * to "new Deck(false)".)
     */
    public Deck() {
    	deck = new Card[60];
    	cardsUsed = 0;
    	cardNum = 0;
    }

    
    public void addCard(Card card){
    	if(cardNum >= 60)
    		 throw new IllegalArgumentException("Deck only contain 60 This card you are adding is number" + cardNum);
    	deck[cardNum] = card;
    	cardNum++;
    }
    /**
     * Put all the used cards back into the deck (if any), and
     * shuffle the deck into a random order.
     */
    public void shuffle() {
        for ( int i = deck.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }

    /**
     * As cards are dealt from the deck, the number of cards left
     * decreases.  This function returns the number of cards that
     * are still left in the deck.
     */
    public int cardsLeft() {
        return deck.length - cardsUsed;
    }

    /**
     * Removes the next card from the deck and return it.  It is illegal
     * to call this method if there are no more cards in the deck.  You can
     * check the number of cards remaining by calling the cardsLeft() function.
     * @return the card which is removed from the deck.
     * @throws IllegalStateException if there are no cards left in the deck
     */
    public Card dealCard() {
        if (cardsUsed == deck.length)
            throw new IllegalStateException("No cards are left in the deck.");
        cardsUsed++;
        if(deck[cardsUsed - 1] == null){
        }
        deck[cardsUsed - 1].setLocation("Hand");
        return deck[cardsUsed - 1];
        // Programming note:  Cards are not literally removed from the array
        // that represents the deck.  We just keep track of how many cards
        // have been used.
    }

    public Card[] getDeck() {
        return deck;
    }
} // end class Deck
