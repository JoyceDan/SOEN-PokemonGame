package unitTest;

import model.Card;
import model.Deck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Fish on 2017/5/24.
 */
public class DeckTest {

    Deck deck;
    Card card1, card2, card3, card4, card5;
    Card[] deckToBeTested;

    @Before
    public void before(){
        deck = new Deck();
        card1 = new Card("pokemon1", Card.CardType.POKEMON);
        card2 = new Card("pokemon2", Card.CardType.POKEMON);
        card3 = new Card("energy1", Card.CardType.ENERGY);
        card4 = new Card("energy2", Card.CardType.ENERGY);
        card5 = new Card("energy3", Card.CardType.ENERGY);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        deck.addCard(card4);
        deck.addCard(card5);
        deckToBeTested = deck.getDeck();
    }

    @Test
    public void testAddCard(){
        assertEquals(card3, deckToBeTested[2]);
    }

    @Test
    public void testDealCard(){
        assertEquals(card1, deck.dealCard());
        assertEquals(card2, deck.dealCard());
    }
}
